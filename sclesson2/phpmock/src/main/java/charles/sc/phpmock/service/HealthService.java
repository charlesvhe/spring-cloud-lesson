package charles.sc.phpmock.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by issuser on 2017/1/17.
 */
@Service
@ResponseBody
@RequestMapping("/health")
public class HealthService {
    private Log log = LogFactory.getLog(HealthService.class);

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> query() {
        log.info("health");
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        return result;
    }
}
