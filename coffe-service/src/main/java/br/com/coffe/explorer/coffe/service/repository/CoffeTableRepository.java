package br.com.coffe.explorer.coffe.service.repository;

import br.com.coffe.explorer.coffe.service.entity.CoffeTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeTableRepository extends JpaRepository<CoffeTableEntity, String> {

    Optional<List<CoffeTableEntity>> findByFlavorCode(String flavorCode);
}
