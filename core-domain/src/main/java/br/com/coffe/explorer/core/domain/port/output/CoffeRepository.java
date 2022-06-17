package br.com.coffe.explorer.core.domain.port.output;

import br.com.coffe.explorer.core.domain.entity.Coffe;

import java.util.List;
import java.util.Optional;

public interface CoffeRepository {

    Optional<Coffe> findById(String coffeId);
    Optional<List<Coffe>> findByFlavorCode(String flavorCode);
    void deleteCoffe(String coffeId);
    void createCoffe(Coffe coffe);
    void updateCoffe(Coffe coffe);
}
