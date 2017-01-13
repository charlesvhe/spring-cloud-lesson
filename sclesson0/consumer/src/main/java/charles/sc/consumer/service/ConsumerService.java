package charles.sc.consumer.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by issuser on 2017/1/13.
 */
@Service
@ResponseBody
@RequestMapping("/consumer")
public class ConsumerService {
    private Log log = LogFactory.getLog(ConsumerService.class);

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        log.info("test start");

        log.info("get " +
                restTemplate.getForEntity("http://localhost:8110/user", User[].class).getBody());

        log.info("get id " +
                restTemplate.getForEntity("http://localhost:8110/user/1", User.class).getBody());

        log.info("post search " +
                restTemplate.postForEntity("http://localhost:8110/user/search", new User(1L, "a1", "p1"), User[].class).getBody());

        log.info("post " +
                restTemplate.postForEntity("http://localhost:8110/user", new User(2L, "a2", "p2"), User.class).getBody());

        log.info("delete " +
                restTemplate.exchange("http://localhost:8110/user/1", HttpMethod.DELETE, HttpEntity.EMPTY, User.class).getBody());

        log.info("put " +
                restTemplate.exchange("http://localhost:8110/user", HttpMethod.PUT, new HttpEntity(new User(1L, "a1", "p1")), User.class).getBody());

        log.info("test end");
        return result;
    }
}
