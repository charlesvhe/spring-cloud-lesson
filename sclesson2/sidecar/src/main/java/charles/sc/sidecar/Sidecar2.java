package charles.sc.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * Created by issuser on 2017/1/13.
 */
@SpringBootApplication
@EnableSidecar
public class Sidecar2 {

    public static void main(String[] args) {
        SpringApplication.run(Sidecar2.class, args);
    }
}
