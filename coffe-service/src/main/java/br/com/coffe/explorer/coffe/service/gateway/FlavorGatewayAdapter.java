package br.com.coffe.explorer.coffe.service.gateway;

import br.com.coffe.explorer.coffe.service.gateway.model.FlavorJsonModel;
import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FlavorGatewayAdapter implements FlavorRepository {

    private final FlavorHttpClient flavorHttpClient;

    public FlavorGatewayAdapter(FlavorHttpClient flavorHttpClient) {
        this.flavorHttpClient = flavorHttpClient;
    }

    @Override
    public Optional<List<Flavor>> findFlavors() {
        return Optional.empty();
    }

    @Override
    public Optional<Flavor> findByCode(String flavorCode) {
        FlavorJsonModel flavorResponse = flavorHttpClient.findByCode(flavorCode);
        return Optional.of(new Flavor(flavorResponse.code(), flavorResponse.name(), null));
    }

}
