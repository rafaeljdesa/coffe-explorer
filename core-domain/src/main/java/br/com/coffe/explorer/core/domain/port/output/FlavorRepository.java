package br.com.coffe.explorer.core.domain.port.output;

import br.com.coffe.explorer.core.domain.entity.Flavor;

import java.util.List;
import java.util.Optional;

public interface FlavorRepository {

    Optional<List<Flavor>> findFlavors();
    Optional<Flavor> findByCode(String flavorCode);
}
