package br.com.coffe.explorer.core.domain.port.input;

import br.com.coffe.explorer.core.domain.model.CoffeModel;

import java.util.List;

public interface CoffeInbound {

    void createCoffe(CoffeModel coffeModel, String user);
    List<CoffeModel> findByFlavorCode(String flavorCode);
}
