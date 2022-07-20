package br.com.coffe.explorer.coffe.service.config;

import br.com.coffe.explorer.core.domain.port.input.CoffeInbound;
import br.com.coffe.explorer.core.domain.port.output.CoffeRepository;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import br.com.coffe.explorer.core.domain.port.output.ImageRepository;
import br.com.coffe.explorer.core.domain.service.CoffeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoffeConfig {

    @Bean
    public CoffeInbound coffeInbound(CoffeRepository coffeRepository, FlavorRepository flavorRepository, ImageRepository imageRepository) {
        return new CoffeService(coffeRepository, flavorRepository, imageRepository);
    }

}
