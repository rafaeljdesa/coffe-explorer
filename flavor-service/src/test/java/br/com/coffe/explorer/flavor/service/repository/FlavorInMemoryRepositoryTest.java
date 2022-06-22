package br.com.coffe.explorer.flavor.service.repository;

import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FlavorInMemoryRepositoryTest {

    private FlavorRepository flavorRepository;

    @BeforeEach
    public void init() {
        flavorRepository = new FlavorInMemoryRepository();
    }

    @Test
    public void findFlavorsTest() {
        Optional<List<Flavor>> optionalFlavors = flavorRepository.findFlavors();
        assertTrue(optionalFlavors.isPresent());
        assertTrue(optionalFlavors.get().size() > 0);
    }

    @Test
    public void findFlavorByCodeTest() {
        String flavorCode = "CHOCOLATE";
        Optional<Flavor> optionalFlavor = flavorRepository.findByCode(flavorCode);
        assertTrue(optionalFlavor.isPresent());
        assertEquals(flavorCode, optionalFlavor.get().getCode());
    }

    @Test
    public void findFlavorByCode_NotFoundTest() {
        String flavorCode = "INVALID_CODE";
        Optional<Flavor> optionalFlavor = flavorRepository.findByCode(flavorCode);
        assertTrue(optionalFlavor.isEmpty());
    }
}