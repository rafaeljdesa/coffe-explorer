package br.com.coffe.explorer.coffe.service.gateway;

import br.com.coffe.explorer.core.domain.exception.FileUploadException;
import br.com.coffe.explorer.core.domain.port.output.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class ImageAmazonS3Adapter implements ImageRepository {

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    @Value("${AWS_BUCKET_FOLDER}")
    private String folder;

    private static final String[] VALID_IMAGE_TYPES = { "image/png", "image/jpeg" };

    private static final long IMAGE_MAX_SIZE_IN_BYTES = 10_000_000;

    private final S3Client amazonS3;

    public ImageAmazonS3Adapter(S3Client amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadImage(Object image) {
        try {
            MultipartFile multipartFile = (MultipartFile) image;

            validateImage(multipartFile);

            InputStream inputStream = multipartFile.getInputStream();

            String key = folder + "/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl(ObjectCannedACL.PRIVATE)
                    .build();

            RequestBody requestBody = RequestBody.fromInputStream(inputStream, multipartFile.getSize());

            amazonS3.putObject(putObjectRequest, requestBody);

            inputStream.close();

            return "https://" + bucketName + ".s3.amazonaws.com/" + key;
        } catch (Exception e) {
            String message = "Occurred an error to upload file to Amazon S3: " + e.getMessage();
            log.error(message, e);
            throw new FileUploadException(message);
        }
    }

    @Override
    public String getFileName(Object image) {
        MultipartFile multipartFile = (MultipartFile) image;
        return multipartFile.getOriginalFilename();
    }

    private void validateImage(MultipartFile file) {
        if (!Arrays.asList(VALID_IMAGE_TYPES).contains(file.getContentType())) {
            throw new FileUploadException("The content type of file is invalid");
        }
        if (file.getSize() > IMAGE_MAX_SIZE_IN_BYTES) {
            throw new FileUploadException("The file size is invalid");
        }
    }
}
