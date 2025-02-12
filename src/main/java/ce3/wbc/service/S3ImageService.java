package ce3.wbc.service;

import ce3.wbc.util.WbcFileUtil;
import io.awspring.cloud.s3.S3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class S3ImageService {
    private final S3Client s3Client;
    private static final long MAX_SIZE = 10 * 1024 * 1024;


    @Value("${s3.bucket}")
    private String bucketName;
    //임시저장 경로
/*    @Value("${storage.temp.path}")
    private String tempStoragePath;*/

    //s3업로드
    public String uploadS3(MultipartFile multipartFile, String RestEngName) {

        //파일명 생성 시 영문 가게 이름 사용 :
        String fileName = WbcFileUtil.createFileName(multipartFile.getOriginalFilename(), RestEngName);

        String s3Key = RestEngName + "/" + fileName;


        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    //.acl(ObjectCannedACL.PUBLIC_READ)
                    .contentType(multipartFile.getContentType())
                    .build();

            //파일 크기(10MB)에따라
            if (multipartFile.getSize() > MAX_SIZE) {
                File file = WbcFileUtil.MultipartFileToFile(multipartFile);
                s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
                file.delete();// 업로드 후 임시 파일 삭제
            } else {
                s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));
            }
            // S3에 저장된 파일의 키 반환
            return s3Key;
        } catch (IOException e) {
            throw new RuntimeException("s3업로드 실패", e);
        }

    }

    private boolean doesS3DirectoryExist(String directoryName) {
        try {
            return !s3Client.listObjectsV2(builder -> builder
                    .bucket(bucketName)
                    .prefix(directoryName + "/") //디렉토리처럼 보이는 키(prefix) 검색
                    .maxKeys(1) // 최소 하나의 객체라도 있으면 존재하는 것으로 간주
            ).contents().isEmpty();
        } catch (NoSuchKeyException | S3Exception e) {
            return false;
        }
    }

    private void createS3Directory(String directoryName) {
        try {
            String s3Key = directoryName + "/";
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.empty());
            log.info("S3 디렉토리 생성 완료: {}", directoryName);
        } catch (Exception e) {
            throw new RuntimeException("s3 디렉토리 생성 실패!", e);
        }
    }

}



