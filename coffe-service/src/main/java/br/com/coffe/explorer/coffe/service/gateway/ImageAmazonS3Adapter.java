package br.com.coffe.explorer.coffe.service.gateway;

import br.com.coffe.explorer.core.domain.exception.FileValidationException;
import br.com.coffe.explorer.core.domain.port.output.ImageRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
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

    private final AmazonS3 amazonS3;

    public ImageAmazonS3Adapter(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadImage(Object image) {
        try {
            MultipartFile multipartFile = (MultipartFile) image;

            validateImage(multipartFile);

            InputStream inputStream = multipartFile.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getBytes().length);
            objectMetadata.setContentType(multipartFile.getContentType());

            String key = folder + "/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    key,
                    inputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.Private);

            PutObjectResult putObjectResult = amazonS3.putObject(putObjectRequest);

            inputStream.close();

            return "https://" + bucketName + ".s3.amazonaws.com/" + key;
        } catch (Exception e) {
            log.error("Occurred an error to upload file to Amazon S3", e);
        }
        return null;
    }

    private void validateImage(MultipartFile file) {
        if (!Arrays.asList(VALID_IMAGE_TYPES).contains(file.getContentType())) {
            throw new FileValidationException("The content type of file is invalid");
        }
        if (file.getSize() > IMAGE_MAX_SIZE_IN_BYTES) {
            throw new FileValidationException("The file size is invalid");
        }
    }
}
