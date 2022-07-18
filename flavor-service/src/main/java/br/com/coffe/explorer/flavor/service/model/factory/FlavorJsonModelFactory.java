package br.com.coffe.explorer.flavor.service.model.factory;

import br.com.coffe.explorer.core.domain.model.FlavorModel;
import br.com.coffe.explorer.flavor.service.model.FlavorJsonModel;

public class FlavorJsonModelFactory {

    public static FlavorJsonModel create(FlavorModel flavorModel) {
        if (flavorModel == null) return null;
        return new FlavorJsonModel(
                flavorModel.code(),
                flavorModel.name(),
                FlavorJsonModelFactory.create(flavorModel.parentFlavor())
        );
    }
}
