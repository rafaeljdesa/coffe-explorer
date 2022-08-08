package br.com.coffe.explorer.coffe.service.gateway.error;

import br.com.coffe.explorer.core.domain.exception.FlavorNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            throw new FlavorNotFoundException();
        }
        return errorDecoder.decode(methodKey, response);
    }
}
