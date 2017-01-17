package charles.sc.php.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by issuser on 2017/1/17.
 */
@FeignClient(value = "sidecar")
@RequestMapping("/php")
public interface PhpService {
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> query();

    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    public Map<String, Object> provider();
}
