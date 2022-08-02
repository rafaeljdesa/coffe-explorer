package br.com.coffe.explorer.coffe.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

    private static final String DEFAULT_REGION = "us-east-1";

    @Value("${AWS_REGION}")
    private String region;

    @Bean
    public S3Client s3Client() {
        final String awsRegion = region != null ? region : DEFAULT_REGION;
        Region region = Region.of(awsRegion);
        return S3Client.builder()
                .region(region)
                .build();
    }
}
