package charles.sc.sidecarconsumer.service;

import charles.sc.php.service.PhpService;
import charles.sc.provider.service.User;
import charles.sc.provider.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private PhpService phpService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        log.info("test start");

        log.info("get " +
                restTemplate.getForEntity("http://provider/user", User[].class).getBody());

        log.info("get id " +
                restTemplate.getForEntity("http://provider/user/1", User.class).getBody());

        log.info("post search " +
                restTemplate.postForEntity("http://provider/user/search", new User(1L, "a1", "p1"), User[].class).getBody());

        log.info("post " +
                restTemplate.postForEntity("http://provider/user", new User(2L, "a2", "p2"), User.class).getBody());

        log.info("delete " +
                restTemplate.exchange("http://provider/user/1", HttpMethod.DELETE, HttpEntity.EMPTY, User.class).getBody());

        log.info("put " +
                restTemplate.exchange("http://provider/user", HttpMethod.PUT, new HttpEntity(new User(1L, "a1", "p1")), User.class).getBody());

        log.info("test end");
        return result;
    }

    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public Map<String, Object> feign() {
        Map<String, Object> result = new HashMap<>();
        log.info("feign start");

        log.info("get " + userService.query());

        log.info("get id " + userService.query(1L));

        log.info("post search " + userService.query(new User(1L, "a1", "p1")));

        log.info("post " + userService.insert(new User(2L, "a2", "p2")));

        log.info("delete " + userService.delete(1L));

        log.info("put " + userService.update(new User(1L, "a1", "p1")));

        log.info("feign end");
        return result;
    }

    @RequestMapping(value = "/php", method = RequestMethod.GET)
    public Map<String, Object> php() {
        Map<String, Object> result = phpService.query();
        log.info("php "+result);
        return result;
    }
}
