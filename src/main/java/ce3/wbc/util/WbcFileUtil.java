package ce3.wbc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class WbcFileUtil {

    public static File MultipartFileToFile(MultipartFile  multipartFile) throws IOException {
        // 임시 디렉토리 경로와 원본 파일명을 사용하여 File 객체 생성
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());

        try (FileOutputStream fops = new FileOutputStream(file)) {
            fops.write(multipartFile.getBytes());
        }
        return file;

    }

    public static String createFileName(String originalFileName, String RestEngName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IllegalArgumentException(" 파일명이 비었습니다");
        }
        //파일명과 확장자 분리
        String extension = "";// 확장자 담기
        int lastIndex = originalFileName.lastIndexOf(".");
        if (lastIndex != -1) {
            extension = originalFileName.substring(lastIndex);
        }
        //특수문자 제거 + 공백 제거
        String s3Name = RestEngName.replaceAll("[^\\p{L}\\p{N}]", "");

        // 유일한 파일명
        return UUID.randomUUID().toString().substring(0, 8) + "_" + s3Name + extension;
    }
}
