package ce3.wbc.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public S3Client s3client() {

        // S3 사용 인증 객체
        //AWSCredentials  credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

        // 리전 정보 입력 -> S3 사용 객체 생성
        return S3Client.builder()
                .region(Region.of(region)) //AWS 리전 설정
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsAccessKey, awsSecretKey))) // AWS 자격증명 설정
                .build();
    }
}