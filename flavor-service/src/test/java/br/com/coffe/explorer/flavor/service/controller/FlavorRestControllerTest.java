package br.com.coffe.explorer.flavor.service.controller;

import br.com.coffe.explorer.core.domain.exception.FlavorNotFoundException;
import br.com.coffe.explorer.core.domain.model.FlavorModel;
import br.com.coffe.explorer.core.domain.port.input.FlavorInbound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
public class FlavorRestControllerTest {

    @Mock
    private FlavorInbound flavorInbound;

    private MockMvc mockMvc;

    private FlavorRestController flavorRestController;

    @BeforeEach
    public void init() {
        flavorRestController = new FlavorRestController(flavorInbound);
        mockMvc = MockMvcBuilders
                .standaloneSetup(flavorRestController)
                .build();
    }

    @Test
    public void getFlavors_successTest() throws Exception {

        FlavorModel flavorModel = new FlavorModel("SWEET", "Doce", null);

        when(flavorInbound.findFlavors())
                .thenReturn(List.of(flavorModel));

        mockMvc.perform(get("/v1/flavors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(flavorModel.name())));
    }

    @Test
    public void getFlavors_notFoundTest() throws Exception {

        when(flavorInbound.findFlavors()).thenThrow(new FlavorNotFoundException());

        mockMvc.perform(get("/v1/flavors"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Flavors not found")));
    }

    @Test
    public void getFlavorsGrouped() {
    }
}