package br.com.coffe.explorer.coffe.service.repository;

import br.com.coffe.explorer.coffe.service.entity.CoffeImageTableEntity;
import br.com.coffe.explorer.coffe.service.entity.CoffeTableEntity;
import br.com.coffe.explorer.core.domain.entity.Coffe;
import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.port.output.CoffeRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CoffeRepositoryJpaAdapter implements CoffeRepository {

    private final CoffeTableRepository coffeTableRepository;
    private final CoffeImageTableRepository coffeImageTableRepository;

    public CoffeRepositoryJpaAdapter(CoffeTableRepository coffeTableRepository,
                                     CoffeImageTableRepository coffeImageTableRepository) {
        this.coffeTableRepository = coffeTableRepository;
        this.coffeImageTableRepository = coffeImageTableRepository;
    }

    @Override
    public Optional<Coffe> findById(String coffeId) {
        return coffeTableRepository.findById(coffeId)
                .map(this::from);
    }

    @Override
    public Optional<List<Coffe>> findByFlavorCode(String flavorCode) {
        return coffeTableRepository.findByFlavorCode(flavorCode)
                .map(this::from);
    }

    @Override
    public void deleteCoffe(String coffeId) {
        coffeTableRepository.deleteById(coffeId);
        coffeImageTableRepository.deleteByCoffeId(coffeId);
    }

    @Override
    public void createCoffe(Coffe coffe) {
        CoffeTableEntity coffeTableEntity = CoffeTableEntity.builder()
                .id(coffe.getId())
                .description(coffe.getDescription())
                .creationDateTime(coffe.getCreationDateTime())
                .createdBy(coffe.getCreatedBy())
                .flavorCode(coffe.getFlavor().getCode())
                .build();

        coffeTableEntity = coffeTableRepository.save(coffeTableEntity);

        if (CollectionUtils.isEmpty(coffe.getImagesUrls())) {
            return;
        }

        String coffeId = coffeTableEntity.getId();

        List<CoffeImageTableEntity> imagesTableEntities = coffe.getImagesUrls().stream()
                .map(url -> CoffeImageTableEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .url(url)
                        .coffeId(coffeId)
                        .build()
                )
                .toList();

        coffeImageTableRepository.saveAll(imagesTableEntities);
    }

    @Override
    public void updateCoffe(Coffe coffe) {
        createCoffe(coffe);
    }

    private Coffe from(CoffeTableEntity entity) {
        return new Coffe.Builder()
                .withId(entity.getId())
                .withDescription(entity.getDescription())
                .withCreationDateTime(entity.getCreationDateTime())
                .createdBy(entity.getCreatedBy())
                .withFlavor(new Flavor(entity.getFlavorCode(), null, null))
                .withImages(
                    entity.getImages().stream()
                        .map(CoffeImageTableEntity::getUrl)
                        .toList()
                )
                .build();
    }

    private List<Coffe> from(List<CoffeTableEntity> entities) {
        return entities.stream()
                .map(this::from)
                .toList();
    }

}
