package br.com.coffe.explorer.flavor.service.model.factory;

import br.com.coffe.explorer.core.domain.model.FlavorGroupedModel;
import br.com.coffe.explorer.flavor.service.model.FlavorGroupedJsonModel;

import java.util.List;

public class FlavorGroupedJsonModelFactory {

    public static FlavorGroupedJsonModel create(FlavorGroupedModel flavorGroupedModel) {
        if (flavorGroupedModel == null) return null;
        List<FlavorGroupedModel> childs = flavorGroupedModel.childFlavors();
        List<FlavorGroupedJsonModel> childsGrouped = childs != null ? childs.stream()
                .map(FlavorGroupedJsonModelFactory::create)
                .toList() : null;
        return new FlavorGroupedJsonModel(flavorGroupedModel.name(), childsGrouped);
    }
}
