package br.com.coffe.explorer.coffe.service.gateway;

import br.com.coffe.explorer.core.domain.port.output.ImageRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

@Slf4j
@Component
public class ImageAmazonS3Adapter implements ImageRepository {

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    public ImageAmazonS3Adapter(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadImage(Object image) {
        try {
            MultipartFile multipartFile = (MultipartFile) image;
            InputStream inputStream = multipartFile.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getBytes().length);
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    multipartFile.getOriginalFilename(),
                    inputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.Private);

            amazonS3.putObject(putObjectRequest);

            inputStream.close();

            return "https://" + bucketName + ".s3.amazonaws.com/" + multipartFile.getOriginalFilename();
        } catch (Exception e) {
            log.error("Occurred an error to upload file to Amazon S3", e);
        }
        return null;
    }

    @Override
    public Object downloadImage(String path) {
        return null;
    }
}
