package br.com.coffe.explorer.flavor.service.config;

import br.com.coffe.explorer.core.domain.port.input.FlavorInbound;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import br.com.coffe.explorer.core.domain.service.FlavorService;
import br.com.coffe.explorer.flavor.service.repository.FlavorInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlavorServiceConfig {

    @Bean
    public FlavorInbound flavorInbound(FlavorRepository flavorRepository) {
        return new FlavorService(flavorRepository);
    }

    @Bean
    public FlavorRepository flavorRepository() {
        return new FlavorInMemoryRepository();
    }
}
