package me.importtao.seckillbackend.service;

import com.alibaba.fastjson.JSON;
import me.importtao.seckillbackend.dao.UserInfoMapper;
import me.importtao.seckillbackend.dao.UserMapper;
import me.importtao.seckillbackend.model.User;
import me.importtao.seckillbackend.model.UserInfo;
import me.importtao.seckillbackend.util.Encode;
import me.importtao.seckillbackend.util.GeneratorTimeRandomString;
import me.importtao.seckillbackend.util.GeneratorId;
import me.importtao.seckillbackend.util.Token;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Package me.importtao.usersystem.service
 * Class UserService
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/12 13:58
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Resource
    private GeneratorId generatorId;
    @Resource
    private User user;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfo userInfo;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private Token token;

    @Value("${tokenExpire}")
    private String tokenExpire;

    /**
     * Description TODO用户注册
     * @author importtao
     * @date 2018/2/12 14:01
     * @param  request 封装请求信息
     * @return map
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public HashMap register(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String,Object>(16);
        String phoneString = request.getParameter("phone");
        long phone = Long.valueOf(phoneString);
        String password = request.getParameter("password");
        try {
            password = Encode.encrypt(password,"MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("加密失败");
        }

        //系统号规则
        String userId;
        try {
            userId = generatorId.getUserId();
        } catch (Exception e) {
            logger.error("已达当日注册限制人数");
            map.put("status","1");
            map.put("msg","已达当日注册限制人数");
            return map;
        }
        //电话规则
        String phoneRegular = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        boolean validatePhone = phoneString.matches(phoneRegular);
        HashMap phoneCheck = userService.phoneCheck(request);
        boolean phoneCheckResult = phoneCheck.get("status")=="1";
        if(!validatePhone){
            map.put("status","1");
            map.put("msg","手机号错误");
            return map;
        }else if(phoneCheckResult){
            map.put("status","1");
            map.put("msg",phoneCheck.get("msg"));
            return map;
        }
        if(password == null){
            map.put("status","1");
            map.put("msg","密码不能为空");
            return map;
        }
        //用户状态默认为0
        byte status = 0;
        user.setPassword(password);
        user.setUserName("user_"+GeneratorTimeRandomString.getTimeRandomString());
        user.setStatus(status);
        user.setUserId(userId);
        user.setUserName(userId);
        user.setUserPhone(phone);
        userMapper.insert(user);
        userInfo.setUserId(userId);
        userInfo.setUserImg("img.jpg");
        userInfoMapper.insert(userInfo);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> data = new HashMap<>(16);
        data.put("user",user);
        data.put("userInfo",userInfo);
        String key = "";
        try {
            key = token.generatorToken(user.getUserId());
            valueOperations.set(key,data,Long.valueOf(tokenExpire), TimeUnit.MILLISECONDS);
            map.put("status","0");
            map.put("msg","注册成功");
            map.put("token",key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            map.put("status","1");
            map.put("msg","注册失败");
            logger.info("生成token失败，userID"+user.getUserId());
            return map;
        }
        return map;
    }

    /**
     * description 用户登录
     *
     * @param request 封装请求信息
     * @return map
     * @author importtao
     * @date 2018/2/14 22:54
     */
    @Override
    public HashMap login(HttpServletRequest request) {
        HashMap<String,Object> map = passwordCheck(request);
        String parameter = request.getParameter("parameter");
        //接口调用返回标识
        String status = "status";
        //接口调用成功标识
        String success = "0";
        if(success.equals(map.get(status))){
            User user = userMapper.selectByParameter(parameter);
            UserInfo userInfo = userInfoMapper.selectByUserID(user.getUserId());
            String userInfoString = JSON.toJSONString(userInfo);
            ValueOperations valueOperations = redisTemplate.opsForValue();
            HashMap<String,Object> data = new HashMap<>(16);
            data.put("user",user);
            data.put("userInfo",userInfo);
            String key = "";
            try {
                key = token.generatorToken(user.getUserId());
                valueOperations.set(key,data,Long.valueOf(tokenExpire), TimeUnit.MILLISECONDS);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                logger.info("生成token失败，userID"+user.getUserId());
            }
            map.put("status","0");
            map.put("msg","登陆成功");
            map.put("token",key);
            return map;
        }else{
            map.put("status","1");
            map.put("msg","用户名或密码验证失败");
            return map;
        }
    }

    /**
     * description 验证用户名是否被注册
     *
     * @param request 封装请求信息
     * @return map
     * @author importtao
     * @date 2018/2/14 22:54
     */
    @Override
    public HashMap userNameCheck(HttpServletRequest request) {
        HashMap<String,String> map = new HashMap<String,String>(16);
        String userName = request.getParameter("userName");
        //用户名规则
        String userNameRegular = "^([\\u4e00-\\u9fa5]|.){1,20}$";
        boolean validateUserName = userName.matches(userNameRegular);
        if(!validateUserName){
            map.put("status","1");
            map.put("msg","用户名格式错误");
            return map;
        }
        long result = userMapper.userNameCheck(userName);
        if(result == 0){
            map.put("status","0");
            map.put("msg","用户名可用");
            return map;
        }
        map.put("status","1");
        map.put("msg","用户名已注册");
        return map;
    }


    /**
     * description 修改密码
     * @author importtao
     * @date 2018/2/12 20:30
     * @param request 封装请求信息
     * @return   map
     */
    @Override
    public HashMap changePassword(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String,Object>(16);
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        if(password == null){
            map.put("status","1");
            map.put("msg","密码不能为空");
            return map;
        }
        if(rePassword == null){
            map.put("status","1");
            map.put("msg","密码不能为空");
            return map;
        }
        if(!rePassword.equals(password)){
            map.put("status","1");
            map.put("msg","两次密码不一致");
            return map;
        }
        try {
            password = Encode.encrypt(password,"MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("加密失败");
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        userMapper.updateByPrimaryKeySelective(user);
        map.put("status","0");
        map.put("msg","修改成功");
        return map;
    }

    /**
     * description 密码验证
     * @author importtao
     * @date 2018/2/12 20:33
     * @param request 封装请求信息
     * @return  map
     */
    @Override
    public HashMap passwordCheck(HttpServletRequest request){
        HashMap<String,String> map = new HashMap<String,String>(16);
        String parameter = request.getParameter("parameter");
        String password = request.getParameter("password");
        try {
            password = Encode.encrypt(password,"MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("加密失败");
        }
        long result = userMapper.passwordCheck(parameter, password);
        if(result == 0){
            map.put("status","1");
            map.put("msg","验证不通过");
            return map;
        }
        map.put("status","0");
        map.put("msg","验证通过");
        return map;
    }

    /**
     * description 手机号验证
     * @author importtao
     * @date 2018/2/12 20:33
     * @param request 封装请求信息
     * @return  map
     */
    @Override
    public HashMap phoneCheck(HttpServletRequest request){
        HashMap<String,String> map = new HashMap<String,String>(16);
        String phoneString = request.getParameter("phone");
        //电话规则
        String phoneRegular = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        boolean validatePhone = phoneString.matches(phoneRegular);
        if(!validatePhone){
            map.put("status","1");
            map.put("msg","手机号错误");
            return map;
        }
        long phone = Long.valueOf(phoneString);
        long result = userMapper.phoneCheck(phone);
        if(result == 0){
            map.put("status","0");
            map.put("msg","手机号可用");
            return map;
        }
        map.put("status","1");
        map.put("msg","手机号已注册");
        return map;
    }
}
