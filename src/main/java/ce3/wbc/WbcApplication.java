package ce3.wbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbcApplication.class, args);
    }

}
