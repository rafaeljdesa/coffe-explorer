package br.com.coffe.explorer.core.domain.service;

import br.com.coffe.explorer.core.domain.entity.Coffe;
import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.exception.CoffeNotFoundException;
import br.com.coffe.explorer.core.domain.exception.FlavorNotFoundException;
import br.com.coffe.explorer.core.domain.model.CoffeModel;
import br.com.coffe.explorer.core.domain.model.factory.CoffeModelFactory;
import br.com.coffe.explorer.core.domain.port.input.CoffeInbound;
import br.com.coffe.explorer.core.domain.port.output.CoffeRepository;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import br.com.coffe.explorer.core.domain.port.output.ImageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class CoffeService implements CoffeInbound {

    private final CoffeRepository coffeRepository;
    private final FlavorRepository flavorRepository;
    private final ImageRepository imageRepository;

    public CoffeService(CoffeRepository coffeRepository,
                        FlavorRepository flavorRepository,
                        ImageRepository imageRepository) {
        this.coffeRepository = coffeRepository;
        this.flavorRepository = flavorRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public void createCoffe(CoffeModel coffeModel) {
        Flavor flavor = flavorRepository
                .findByCode(coffeModel.flavorCode())
                .orElseThrow(FlavorNotFoundException::new);

        Coffe coffe = new Coffe.Builder()
                .createdBy(coffeModel.createdBy())
                .withDescription(coffeModel.description())
                .withFlavor(flavor)
                .build();

        coffeRepository.createCoffe(coffe);
    }

    @Override
    public List<CoffeModel> findByFlavorCode(String flavorCode) {
        return coffeRepository.findByFlavorCode(flavorCode)
                .orElseThrow(CoffeNotFoundException::new)
                .stream()
                .map(CoffeModelFactory::create)
                .toList();
    }

    @Override
    public void uploadImages(Object[] images, String coffeId) {
        Coffe coffe = coffeRepository.findById(coffeId)
                .orElseThrow(CoffeNotFoundException::new);

        List<String> imagesUrls = Arrays.stream(images)
                .map(imageRepository::uploadImage)
                .filter(Objects::nonNull)
                .toList();

        Coffe coffeWithImages = coffe.cloneUpdatingImages(imagesUrls);
        coffeRepository.updateCoffe(coffeWithImages);
    }
}
