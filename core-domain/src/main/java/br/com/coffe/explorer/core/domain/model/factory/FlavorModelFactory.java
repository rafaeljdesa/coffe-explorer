package br.com.coffe.explorer.core.domain.model.factory;

import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.model.FlavorModel;

public class FlavorModelFactory {

    public static FlavorModel create(Flavor flavor) {
        if (flavor == null) return null;
        return new FlavorModel(flavor.getCode(), flavor.getName(), create(flavor.getParentFlavor()));
    }
}
