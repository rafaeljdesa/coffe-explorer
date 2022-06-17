package br.com.coffe.explorer.flavor.service.controller;

import br.com.coffe.explorer.core.domain.port.input.FlavorInbound;
import br.com.coffe.explorer.flavor.service.model.FlavorGroupedJsonModel;
import br.com.coffe.explorer.flavor.service.model.FlavorJsonModel;
import br.com.coffe.explorer.flavor.service.model.factory.FlavorGroupedJsonModelFactory;
import br.com.coffe.explorer.flavor.service.model.factory.FlavorJsonModelFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class FlavorRestController {

    private final FlavorInbound flavorInbound;

    public FlavorRestController(FlavorInbound flavorInbound) {
        this.flavorInbound = flavorInbound;
    }

    @GetMapping("/flavors")
    public List<FlavorJsonModel> getFlavors() {
        return flavorInbound.findFlavors().stream()
                .map(FlavorJsonModelFactory::create)
                .toList();
    }

    @GetMapping("/flavors/grouped")
    public List<FlavorGroupedJsonModel> getFlavorsGrouped() {
        return flavorInbound.findFlavorsGroupingByRoots().stream()
                .map(FlavorGroupedJsonModelFactory::create)
                .toList();
    }

}
