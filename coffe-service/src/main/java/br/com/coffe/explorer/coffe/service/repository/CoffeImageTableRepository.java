package br.com.coffe.explorer.coffe.service.repository;

import br.com.coffe.explorer.coffe.service.entity.CoffeImageTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeImageTableRepository extends JpaRepository<CoffeImageTableEntity, String> {

    void deleteByCoffeId(String coffeId);
}
