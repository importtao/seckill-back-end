package me.importtao.seckillbackend.controller;

import com.alibaba.fastjson.JSON;
import me.importtao.seckillbackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Package me.importtao.usersystem.controller
 * Class UserController
 * Description: TODO
 *
 * @author importtao
 * date 2018/1/19 13:10
 * @version V1.0
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @GetMapping("/user")
    @Cacheable(value = "userCache", keyGenerator="keyGenerator")
    public String login(HttpServletRequest request){
        HashMap map = userService.login(request);
        return JSON.toJSONString(map);
    }
    @PostMapping("/user")
    public Object register(HttpServletRequest request, @RequestParam(value = "id",required = false,defaultValue = "11")Integer id){
        Map m = new HashMap<>(16);
        return m;

    }
    @PutMapping("/user")
    public Object change(HttpServletRequest request, @RequestParam(value = "name")Integer id){
        Map m = new HashMap<>();
        return m;
    }
    @DeleteMapping("/user/{id}")
    public Object delete(HttpServletRequest request, @PathVariable("id")Integer id){
        Map m = new HashMap<>();
        return m;
    }

}
