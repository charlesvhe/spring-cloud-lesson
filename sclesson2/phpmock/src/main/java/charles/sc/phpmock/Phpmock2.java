package charles.sc.phpmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by issuser on 2017/1/17.
 */
@SpringBootApplication
public class Phpmock2 {

    @Bean
    public WebMvcConfigurer corsConfigurer() {  // 允许跨域
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "DELETE", "PUT", "GET", "OPTIONS");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Phpmock2.class, args);
    }
}
