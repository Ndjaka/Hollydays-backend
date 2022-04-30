package com.ozone.hollidays.services;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import com.ozone.hollidays.config.SecurityParams;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MinIOService {
    final static String url = "http://127.0.0.1";
    final static int port = 9000;
    final static String accessKey = "minioadmin";
    final static String secretKey = "minioadmin";
    final static String bucketName = "holiday-image";

    MinioClient minioClient = MinioClient.builder()
            .endpoint(url, port, false)
            .region("eu-east-1")
            .credentials(accessKey, secretKey).build();

    public void WriteToMinIO(String fileName, InputStream file)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try {


            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(file, file.available(), -1)
                    .build();
            minioClient.putObject(args);
            log.info(fileName + " successfully uploaded to:");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public void ReadFromMinIO(String fileName)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint(url + port)
                    .credentials(accessKey, secretKey).build();
            String downloadedFile = SecurityParams.DIRECTORY + "D_" + fileName;
            DownloadObjectArgs args = DownloadObjectArgs.builder().bucket(bucketName).object(fileName)
                    .filename(downloadedFile).build();
            minioClient.downloadObject(args);

            System.out.println("Downloaded file to ");
            System.out.println(" " + downloadedFile);
            System.out.println();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public InputStream get(Path path) throws MinioException {
        MinioClient minioClient = MinioClient.builder().endpoint(url + port)
                .credentials(accessKey, secretKey).build();
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path.toString())
                    .build();
            return minioClient.getObject(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUrl(String objectUrl) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "application/json");

        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectUrl)
                        .build());
        return url;
    }

}
