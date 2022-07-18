package br.com.coffe.explorer.coffe.service.gateway;

import br.com.coffe.explorer.coffe.service.gateway.model.FlavorJsonModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "flavorservice", url = "${flavorservice.url}")
public interface FlavorHttpClient {

    @GetMapping("/v1/flavors/{flavorCode}")
    FlavorJsonModel findByCode(@PathVariable("flavorCode") String flavorCode);

}
