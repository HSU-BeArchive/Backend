package com.likelion.moamoa.domain.reference.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    // s3에 저장하여 이미지 주소로 반환
    String uploadImageToS3(MultipartFile img);
}
