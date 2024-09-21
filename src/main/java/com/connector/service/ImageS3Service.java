package com.connector.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageS3Service implements ImageService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;

    @Transactional
    @Override
    public void saveImage(MultipartFile multipartFile) {
        String originalName = multipartFile.getOriginalFilename();
        String filename = getFileName(originalName);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
            System.out.println(accessUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 이미지 파일의 확장자를 추출하는 메소드
    public String extractExtension(String originName) {
        int index = originName.lastIndexOf('.');

        return originName.substring(index, originName.length());
    }

    // 이미지 파일의 이름을 저장하기 위한 이름으로 변환하는 메소드
    public String getFileName(String originName) {
        return UUID.randomUUID() + "." + extractExtension(originName);
    }
}