package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.service.UserService;
import me.importtao.seckillbackend.util.Token;
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
@CrossOrigin
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private Token token;

    @GetMapping("/user")
    @Cacheable(value = "userCache", keyGenerator="keyGenerator")
    public HashMap login(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String password = request.getParameter("password");
        String parameter = request.getParameter("parameter");
        if(password == null||password.equals("")){
            map.put("status","1");
            map.put("msg","密码为空");
            return map;
        }
        if(parameter == null||parameter.equals("")){
            map.put("status","1");
            map.put("msg","用户名/手机/用ID为空");
            return map;
        }
        map = userService.login(request);
        return map;
    }
    @PostMapping("/user")
    public Object register(HttpServletRequest request){
        HashMap map = new HashMap<>(16);
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");logger.info("-----------"+password+"-------"+phone);
        if(password == null||password.equals("")){
            map.put("status","1");
            map.put("msg","密码为空");
            return map;
        }
        if(phone == null||phone.equals("")){
            map.put("status","1");
            map.put("msg","电话为空");
            return map;
        }
        map = userService.register(request);
        return map;

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
    @GetMapping("/userModel")
    public HashMap getUserByToken(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String userToken = request.getParameter("token");
        if(userToken == null||userToken.equals("")){
            map.put("status","2");
            map.put("msg","未登录或登录超时");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时");

        }else{
            map = token.getUserByToken(userToken);
            map.put("status","0");
        }
        return map;
    }

}
