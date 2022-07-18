package br.com.coffe.explorer.coffe.service.controller;

import br.com.coffe.explorer.core.domain.model.CoffeModel;
import br.com.coffe.explorer.core.domain.port.input.CoffeInbound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/coffes")
public class CoffeController {

    private final CoffeInbound coffeInbound;

    public CoffeController(CoffeInbound coffeInbound) {
        this.coffeInbound = coffeInbound;
    }

    @PostMapping
    public ResponseEntity<?> createCoffe(@RequestBody CoffeModel coffeModel) {
        coffeInbound.createCoffe(coffeModel, coffeModel.createdBy());
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<CoffeModel>> getByFlavorCode(@RequestParam(name = "flavorCode") String flavorCode) {
        List<CoffeModel> coffes = coffeInbound.findByFlavorCode(flavorCode);
        if (CollectionUtils.isEmpty(coffes)) {
            return ResponseEntity.notFound()
                    .build();
        }
        return ResponseEntity.ok(coffes);
    }
}
