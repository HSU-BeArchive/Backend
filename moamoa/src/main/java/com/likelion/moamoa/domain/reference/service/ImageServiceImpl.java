package com.likelion.moamoa.domain.reference.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadImageToS3(MultipartFile img) {
        try {
            // 파일 이름
            String imgName = img.getOriginalFilename();
            // S3 URL 생성
            // 예시: 파일 이름을 기준으로 경로를 생성 (디렉토리 없이 URL 생성)
            String imgUrl = "https://" + bucket + ".s3.amazonaws.com/" + imgName;

            // 메타데이터 설정
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(img.getContentType());
            metadata.setContentLength(img.getSize());

            // 파일 S3에 업로드
            amazonS3Client.putObject(bucket, imgName, img.getInputStream(), metadata);

            // 업로드 후 접근 가능한 URL 생성
            return amazonS3Client.getUrl(bucket, imgName).toString();

        } catch (Exception e) {
            e.printStackTrace();
            // 예외 시 null 또는 적절한 값 반환
            return null;
        }
    }

    @Override
    public void deleteImageFromS3(String imgUrl) {
        try {
            // https://[bucket].s3.amazonaws.com/[filename] 에서 파일명 추출
            String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);

            amazonS3Client.deleteObject(bucket, fileName);
        } catch (Exception e) {
            e.printStackTrace(); // 로그 출력 (실무에선 Logger 권장)
        }
    }
}
