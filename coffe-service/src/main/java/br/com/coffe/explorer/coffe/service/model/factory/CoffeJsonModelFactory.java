package br.com.coffe.explorer.coffe.service.model.factory;

import br.com.coffe.explorer.coffe.service.model.CoffeJsonModel;
import br.com.coffe.explorer.core.domain.model.CoffeModel;

public class CoffeJsonModelFactory {

    public static CoffeJsonModel create(CoffeModel coffeModel) {
        return new CoffeJsonModel(
                coffeModel.id(),
                coffeModel.description(),
                coffeModel.createdBy(),
                coffeModel.flavorCode()
        );
    }

    public static CoffeModel create(CoffeJsonModel coffeJsonModel) {
        return new CoffeModel(
                coffeJsonModel.id(),
                coffeJsonModel.description(),
                coffeJsonModel.createdBy(),
                coffeJsonModel.flavorCode()
        );
    }

}
