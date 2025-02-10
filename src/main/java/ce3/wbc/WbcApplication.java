package ce3.wbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.awspring.cloud.autoconfigure.s3.S3AutoConfiguration;


@EnableJpaAuditing
@SpringBootApplication//(exclude = { S3AutoConfiguration.class }) // exclude 없애기
public class WbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbcApplication.class, args);
    }

}
