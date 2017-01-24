package charles.sc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.util.regex.Pattern;

/**
 * Created by issuser on 2017/1/13.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Zuul1 {
    // 正则表达式更精确，但是速度稍慢，百万次 500ms左右
    public static final Pattern publicPattern = Pattern.compile("^/[\\w-]+/[\\w]+/pb");
    public static final Pattern protectedPattern = Pattern.compile("^/[\\w-]+/[\\w]+/pt");
    public static final Pattern privatePattern = Pattern.compile("^/[\\w-]+/[\\w]+/pv");

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Zuul1.class, args);
    }
}
