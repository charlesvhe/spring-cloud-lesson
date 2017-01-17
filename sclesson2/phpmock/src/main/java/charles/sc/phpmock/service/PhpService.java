package charles.sc.phpmock.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by issuser on 2017/1/17.
 */
@Service
@ResponseBody
@RequestMapping("/php")
public class PhpService {
    private Log log = LogFactory.getLog(PhpService.class);

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> query() {
        log.info("query all");
        Map<String, Object> result = new HashMap<>();
        result.put("data", System.getProperties());
        return result;
    }

    @RequestMapping(value="/provider", method = RequestMethod.GET)
    public Map<String, Object> provider() {
        log.info("provider");
        Map<String, Object> result = new HashMap<>();
        result.put("data", restTemplate.getForEntity("http://localhost:8200/provider/user", String.class).getBody());
        return result;
    }
}
