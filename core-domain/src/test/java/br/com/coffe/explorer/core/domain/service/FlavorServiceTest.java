package br.com.coffe.explorer.core.domain.service;

import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.exception.FlavorNotFoundException;
import br.com.coffe.explorer.core.domain.model.FlavorGroupedModel;
import br.com.coffe.explorer.core.domain.model.FlavorModel;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlavorServiceTest {

    private FlavorRepository flavorRepository;

    private FlavorService flavorService;

    @BeforeEach
    public void init() {
        flavorRepository = mock(FlavorRepository.class);
        flavorService = new FlavorService(flavorRepository);
    }


    @Test
    public void findFlavorsTest() {
        Optional<List<Flavor>> mockedFlavors = mockFlavors();
        when(flavorRepository.findFlavors()).thenReturn(mockedFlavors);

        List<FlavorModel> flavors = flavorService.findFlavors();

        assertEquals(4, flavors.size());
        verify(flavorRepository, times(1)).findFlavors();
    }

    @Test
    public void findFlavorsEmptyTest() {
        when(flavorRepository.findFlavors()).thenReturn(Optional.empty());
        assertThrows(FlavorNotFoundException.class, () -> flavorService.findFlavors());
        verify(flavorRepository, times(1)).findFlavors();
    }

    @Test
    public void findFlavorsGroupingByRootsTest() {
        Optional<List<Flavor>> mockedFlavors = mockFlavors();
        when(flavorRepository.findFlavors()).thenReturn(mockedFlavors);

        List<FlavorGroupedModel> flavorsGrouped = flavorService.findFlavorsGroupingByRoots();

        assertAll(() -> {
            assertEquals(1, flavorsGrouped.size());
            FlavorGroupedModel fruity = flavorsGrouped.get(0);
            assertEquals("FRUITY", fruity.code());

            assertAll(() -> {
                assertEquals(1, fruity.childFlavors().size());
                FlavorGroupedModel berry = fruity.childFlavors().get(0);
                assertEquals("BERRY", berry.code());

                assertAll(() -> {
                    assertEquals(2, berry.childFlavors().size());
                    assertEquals("BLACKBERRY", berry.childFlavors().get(0).code());
                    assertEquals("RASPBERRY", berry.childFlavors().get(1).code());
                });
            });
        });

        verify(flavorRepository, times(1)).findFlavors();
    }

    @Test
    public void findFlavorsGroupingByRootsNotFoundTest() {
        when(flavorRepository.findFlavors()).thenReturn(Optional.empty());
        assertThrows(FlavorNotFoundException.class, () -> flavorService.findFlavorsGroupingByRoots());
        verify(flavorRepository, times(1)).findFlavors();
    }

    private Optional<List<Flavor>> mockFlavors() {
        Flavor fruity = new Flavor("FRUITY", "Frutado", null);
        Flavor berry = new Flavor("BERRY", "Frutas Vermelhas", fruity);
        Flavor blackberry = new Flavor("BLACKBERRY", "Amora-silvestre", berry);
        Flavor raspberry = new Flavor("RASPBERRY", "Framboesa", berry);
        return Optional.of(List.of(fruity, berry, blackberry, raspberry));
    }
}