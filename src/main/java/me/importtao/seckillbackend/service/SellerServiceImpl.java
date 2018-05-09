package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.dao.SellerInfoMapper;
import me.importtao.seckillbackend.dao.SellerMapper;
import me.importtao.seckillbackend.model.Seller;
import me.importtao.seckillbackend.model.SellerInfo;
import me.importtao.seckillbackend.util.Encode;
import me.importtao.seckillbackend.util.GeneratorTimeRandomString;
import me.importtao.seckillbackend.util.GeneratorId;
import me.importtao.seckillbackend.util.SellerToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Package me.importtao.seckillbackend.service
 * Class SellerServiceImpl
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 15:07
 * @version V1.0
 */
@Service
public class SellerServiceImpl implements SellerService{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
    @Resource
    private SellerMapper sellerMapper;
    @Resource
    private SellerService sellerService;
    @Resource
    private GeneratorId generatorId;
    @Resource
    private Seller seller;
    @Resource
    private SellerInfoMapper sellerInfoMapper;
    @Resource
    private SellerInfo sellerInfo;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SellerToken sellerToken;

    @Value("${tokenExpire}")
    private String tokenExpire;

    /**
     * Description TODO商户注册
     * @author importtao
     * @date 2018/5/7 14:01
     * @param  request 封装请求信息
     * @return map
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public HashMap register(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<String,Object>(16);
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        try {
            password = Encode.encrypt(password,"MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("加密失败");
        }
        String corporation = request.getParameter("corporation");
        String idImg = request.getParameter("idImg");
        String license = request.getParameter("license");
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String logoImg = request.getParameter("logoImg");

        //系统号规则
        String sellerId;
        try {
            sellerId = generatorId.getSellerId();
        } catch (Exception e) {
            logger.error("已达当日注册限制人数");
            map.put("status","1");
            map.put("msg","已达当日注册限制人数");
            return map;
        }
        HashMap phoneCheck = sellerService.phoneCheck(request);
        boolean phoneCheckResult = phoneCheck.get("status")=="1";
        if(phoneCheckResult){
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
        int status = 4;
        Date date = new Date();
        seller.setPassword(password);
        seller.setAccount("seller_"+ GeneratorTimeRandomString.getTimeRandomString());
        seller.setStatus(status);
        seller.setSellerId(sellerId);
        seller.setName(name);
        seller.setDetail(detail);
        seller.setPhone(phone);
        seller.setLogo(logoImg);
        seller.setCreatetime(date);
        sellerMapper.insert(seller);
        sellerInfo.setSellerId(sellerId);
        sellerInfo.setCorporation(corporation);
        sellerInfo.setCreatetime(date);
        sellerInfo.setLicense(license);
        sellerInfo.setIdcard(idImg);
        sellerInfoMapper.insert(sellerInfo);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> data = new HashMap<>(16);
        data.put("seller",seller);
        data.put("sellerInfo",sellerInfo);
        String key = "";
        try {
            key = sellerToken.generatorSellerToken(seller.getSellerId());
            valueOperations.set(key,data,Long.valueOf(tokenExpire), TimeUnit.MILLISECONDS);
            map.put("status","0");
            map.put("msg","注册成功");
            map.put("sellerToken",key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            map.put("status","1");
            map.put("msg","注册失败");
            logger.info("生成sellerToken失败，sellerID"+seller.getSellerId());
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
            Seller seller = sellerMapper.selectByParameter(parameter);
            SellerInfo sellerInfo = sellerInfoMapper.selectBySellerID(seller.getSellerId());
            ValueOperations valueOperations = redisTemplate.opsForValue();
            HashMap<String,Object> data = new HashMap<>(16);
            data.put("seller",seller);
            data.put("sellerInfo",sellerInfo);
            String key = "";
            try {
                key = sellerToken.generatorSellerToken(seller.getSellerId());
                valueOperations.set(key,data,Long.valueOf(tokenExpire), TimeUnit.MILLISECONDS);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                logger.info("生成sellerToken失败，sellerID"+seller.getSellerId());
            }
            map.put("status","0");
            map.put("msg","登陆成功");
            map.put("sellerToken",key);
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
        String sellerName = request.getParameter("sellerName");
        //用户名规则
        String userNameRegular = "^([\\u4e00-\\u9fa5]|.){1,20}$";
        boolean validateUserName = sellerName.matches(userNameRegular);
        if(!validateUserName){
            map.put("status","1");
            map.put("msg","用户名格式错误");
            return map;
        }
        long result = sellerMapper.userNameCheck(sellerName);
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
        String sellerId = request.getParameter("sellerId");
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
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setPassword(password);
        sellerMapper.updateByPrimaryKeySelective(seller);
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
        int result = sellerMapper.passwordCheck(parameter, password);
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
        String phone = request.getParameter("phone");
        //电话规则
        String phoneRegular = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        boolean validatePhone = phone.matches(phoneRegular);
        if(!validatePhone){
            map.put("status","1");
            map.put("msg","手机号错误");
            return map;
        }
        long result = sellerMapper.phoneCheck(phone);
        if(result == 0){
            map.put("status","0");
            map.put("msg","手机号可用");
            return map;
        }
        map.put("status","1");
        map.put("msg","手机号已被注册");
        return map;
    }
}

