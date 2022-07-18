package br.com.coffe.explorer.core.domain.service;

import br.com.coffe.explorer.core.domain.exception.FlavorNotFoundException;
import br.com.coffe.explorer.core.domain.model.FlavorGroupedModel;
import br.com.coffe.explorer.core.domain.model.FlavorModel;
import br.com.coffe.explorer.core.domain.model.factory.FlavorModelFactory;
import br.com.coffe.explorer.core.domain.port.input.FlavorInbound;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlavorService implements FlavorInbound {

    private final FlavorRepository flavorRepository;

    public FlavorService(FlavorRepository getFlavorRepository) {
        this.flavorRepository = getFlavorRepository;
    }

    @Override
    public List<FlavorModel> findFlavors() {
        return flavorRepository.findFlavors()
                .orElseThrow(FlavorNotFoundException::new)
                .stream()
                .map(FlavorModelFactory::create)
                .toList();
    }

    @Override
    public List<FlavorGroupedModel> findFlavorsGroupingByRoots() {
        List<FlavorModel> flavors = findFlavors();

        Set<FlavorModel> roots = flavors.stream()
                .filter(flavor -> flavor.parentFlavor() == null)
                .collect(Collectors.toSet());

        return roots.stream()
                .map(root -> getFlavorGroupedModel(root, flavors))
                .toList();
    }

    @Override
    public FlavorModel findByCode(String flavorCode) {
        return flavorRepository.findByCode(flavorCode)
                .map(FlavorModelFactory::create)
                .orElseThrow(FlavorNotFoundException::new);
    }

    private FlavorGroupedModel getFlavorGroupedModel(FlavorModel root, List<FlavorModel> flavors) {
        return new FlavorGroupedModel(root.code(), root.name(), getChildsRecursively(root, flavors));
    }

    private List<FlavorGroupedModel> getChildsRecursively(FlavorModel parent, List<FlavorModel> flavors) {
        return flavors.stream()
                .filter(flavor -> flavor.parentFlavor() != null && flavor.parentFlavor().equals(parent))
                .map(flavor -> getFlavorGroupedModel(flavor, flavors))
                .toList();
    }

}
