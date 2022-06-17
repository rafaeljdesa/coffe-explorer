package br.com.coffe.explorer.core.domain.model.factory;

import br.com.coffe.explorer.core.domain.entity.Coffe;
import br.com.coffe.explorer.core.domain.model.CoffeModel;

public class CoffeModelFactory {

    public static CoffeModel create(Coffe from) {
        return new CoffeModel(
                from.getId(),
                from.getDescription(),
                from.getCreatedBy(),
                from.getFlavor().getCode()
        );
    }
}
