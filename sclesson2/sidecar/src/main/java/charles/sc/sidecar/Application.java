package charles.sc.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * Created by issuser on 2017/1/13.
 */
@SpringBootApplication
@EnableSidecar
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
