package charles.sc.provider.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by issuser on 2017/1/13.
 */
@FeignClient(value = "provider")
@RequestMapping("/user")
public interface UserService {
    @RequestMapping(method = RequestMethod.GET)
    public List<User> query();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User query(@PathVariable("id") Long id);

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<User> query(@RequestBody User user);

    @RequestMapping(method = RequestMethod.POST)
    public User insert(@RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.PUT)
    public User update(@RequestBody User user);
}
