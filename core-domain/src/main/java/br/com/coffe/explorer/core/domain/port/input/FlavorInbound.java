package br.com.coffe.explorer.core.domain.port.input;

import br.com.coffe.explorer.core.domain.model.FlavorGroupedModel;
import br.com.coffe.explorer.core.domain.model.FlavorModel;

import java.util.List;

public interface FlavorInbound {

    List<FlavorModel> findFlavors();
    List<FlavorGroupedModel> findFlavorsGroupingByRoots();
}
