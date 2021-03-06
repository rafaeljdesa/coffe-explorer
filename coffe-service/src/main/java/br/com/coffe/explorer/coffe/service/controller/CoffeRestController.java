package br.com.coffe.explorer.coffe.service.controller;

import br.com.coffe.explorer.coffe.service.model.CoffeJsonModel;
import br.com.coffe.explorer.coffe.service.model.factory.CoffeJsonModelFactory;
import br.com.coffe.explorer.core.domain.model.CoffeImageResultModel;
import br.com.coffe.explorer.core.domain.model.CoffeModel;
import br.com.coffe.explorer.core.domain.port.input.CoffeInbound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/v1/coffes")
public class CoffeRestController {

    private final CoffeInbound coffeInbound;

    public CoffeRestController(CoffeInbound coffeInbound) {
        this.coffeInbound = coffeInbound;
    }

    @PostMapping
    public ResponseEntity<?> createCoffe(@RequestBody CoffeJsonModel coffeJsonModel) {
        coffeInbound.createCoffe(CoffeJsonModelFactory.create(coffeJsonModel));
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

    @PostMapping("/{coffeId}/images")
    public ResponseEntity<List<CoffeImageResultModel>> uploadImage(@PathVariable("coffeId") String coffeId,
                                                                   @RequestParam("files") MultipartFile[] files) {
        List<CoffeImageResultModel> imageResultModels = coffeInbound.uploadImages(files, coffeId);
        return ResponseEntity.ok(imageResultModels);
    }
}
