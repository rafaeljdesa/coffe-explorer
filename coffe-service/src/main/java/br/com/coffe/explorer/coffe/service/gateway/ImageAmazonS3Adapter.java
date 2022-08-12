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
import java.util.UUID;

@Slf4j
@Component
public class ImageAmazonS3Adapter implements ImageRepository<MultipartFile> {

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    @Value("${AWS_BUCKET_FOLDER}")
    private String folder;

    @Value("${IMAGE_SERVER_URL}")
    private String imageServerUrl;

    private final S3Client s3Client;

    public ImageAmazonS3Adapter(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        try {

            InputStream inputStream = multipartFile.getInputStream();

            String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

            String key = folder + "/" + fileName;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl(ObjectCannedACL.PRIVATE)
                    .contentType(multipartFile.getContentType())
                    .build();

            RequestBody requestBody = RequestBody.fromInputStream(inputStream, multipartFile.getSize());

            s3Client.putObject(putObjectRequest, requestBody);

            inputStream.close();

            return imageServerUrl + fileName;
        } catch (Exception e) {
            String message = "Occurred an error to upload file to Amazon S3: " + e.getMessage();
            log.error(message, e);
            throw new FileUploadException(message);
        }
    }

    @Override
    public String getFileName(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public String getContentType(MultipartFile multipartFile) {
        return multipartFile.getContentType();
    }

    @Override
    public long getFileSize(MultipartFile multipartFile) {
        return multipartFile.getSize();
    }
}
