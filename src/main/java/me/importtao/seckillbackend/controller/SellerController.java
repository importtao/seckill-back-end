package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.service.SellerService;
import me.importtao.seckillbackend.util.SellerToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.controller
 * Class SellerController
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 13:28
 * @version V1.0
 */

@RestController
@CrossOrigin
public class SellerController {
    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
    @Resource
    private SellerService sellerService;
    @Resource
    private SellerToken sellerToken;


    @GetMapping("/seller")
    @Cacheable(value = "sellerCache", keyGenerator="keyGenerator")
    public Object login(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String parameter = request.getParameter("parameter");
        String password = request.getParameter("password");
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
        map = sellerService.login(request);
        return map;
    }

    @PostMapping("/seller")
    @Cacheable(value = "sellerCache", keyGenerator="keyGenerator")
    public Object register(HttpServletRequest request){
        //{'pass':this.registerForm.pass  ,'phone':this.registerForm.phone ,'corporation': this.registerForm.corporation,'idImg': this.registerForm.idImg,'license':this.registerForm.license,'name': this.registerForm.name,'detail':this.registerForm.detail,'logoImg': this.registerForm.logoImg}
        HashMap map = new HashMap(16);
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String corporation = request.getParameter("corporation");
        String idImg = request.getParameter("idImg");
        String license = request.getParameter("license");
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String logoImg = request.getParameter("logoImg");
        if(password == null||password.equals("")){
            map.put("status","1");
            map.put("msg","密码为空");
            return map;
        }
        if(phone == null||phone.equals("")){
            map.put("status","1");
            map.put("msg","手机为空");
            return map;
        }
        if(corporation == null||corporation.equals("")){
            map.put("status","1");
            map.put("msg","法人为空");
            return map;
        }
        if(idImg == null||idImg.equals("")){
            map.put("status","1");
            map.put("msg","法人身份证为空");
            return map;
        }
        if(license == null||license.equals("")){
            map.put("status","1");
            map.put("msg","营业执照为空");
            return map;
        }
        if(name == null||name.equals("")){
            map.put("status","1");
            map.put("msg","商户名为空");
            return map;
        }
        if(logoImg == null||logoImg.equals("")){
            map.put("status","1");
            map.put("msg","logo为空");
            return map;
        }
        if(detail == null||detail.equals("")){
            request.setAttribute("detail",detail);
        }
        map = sellerService.register(request);
        return map;
    }
    @GetMapping("/sellerModel")
    public HashMap getSellerByToken(HttpServletRequest request){
        String token = request.getParameter("sellerToken");
        HashMap map = new HashMap(16);
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = sellerToken.tokenValidate(token);
        if(tokenValidate){
            map = sellerToken.getSellerByToken(token);
            map.put("status","0");
        }else{
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
        }
        return map;
    }

}
