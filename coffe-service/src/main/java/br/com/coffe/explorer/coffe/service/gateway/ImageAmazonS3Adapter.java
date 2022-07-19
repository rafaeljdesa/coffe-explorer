package br.com.coffe.explorer.coffe.service.gateway;

import br.com.coffe.explorer.core.domain.port.output.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ImageAmazonS3Adapter implements ImageRepository {

    @Override
    public String uploadImage(Object image) {
        return "https://teste-" + new Random().nextInt();
    }

    @Override
    public Object downloadImage(String path) {
        return null;
    }
}
