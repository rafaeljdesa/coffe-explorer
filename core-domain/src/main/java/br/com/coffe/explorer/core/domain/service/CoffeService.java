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
import java.util.List;

public class CoffeService implements CoffeInbound {

    private final CoffeRepository coffeRepository;
    private final FlavorRepository flavorRepository;

    public CoffeService(CoffeRepository coffeRepository,
                        FlavorRepository flavorRepository) {
        this.coffeRepository = coffeRepository;
        this.flavorRepository = flavorRepository;
    }

    @Override
    public void createCoffe(CoffeModel coffeModel, String user) {
        Flavor flavor = flavorRepository
                .findByCode(coffeModel.flavorCode())
                .orElseThrow(FlavorNotFoundException::new);

        Coffe coffe = new Coffe.Builder()
                .createdBy(user)
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
}
