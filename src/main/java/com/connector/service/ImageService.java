package com.connector.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    public void saveImage(MultipartFile multipartFile);
}
