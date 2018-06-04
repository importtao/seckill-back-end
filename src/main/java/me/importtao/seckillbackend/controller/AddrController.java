package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.model.User;
import me.importtao.seckillbackend.service.AddressService;
import me.importtao.seckillbackend.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.controller
 * Class addrController
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/10 21:59
 * @version V1.0
 */
@RestController
@CrossOrigin
public class AddrController {
    private static final Logger logger = LoggerFactory.getLogger(AddrController.class);
    @Resource
    private AddressService addressService;
    @Resource
    private Token token;

    @PostMapping("/address")
    public HashMap addAddress(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String detail = request.getParameter("detail");
        String tel = request.getParameter("tel");
        String name = request.getParameter("name");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(province == null||"".equals(province)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(city == null||"".equals(city)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(county == null||"".equals(county)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(detail == null||"".equals(detail)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(tel == null||"".equals(tel)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(name == null||"".equals(name)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        map = addressService.addAddress(user.getUserId(),province,city,county,"乡镇",detail,tel,name);
        return map;
    }
    @PutMapping("/address")
    public HashMap updateAddressList(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String town = request.getParameter("town");
        String detail = request.getParameter("detail");
        String tel = request.getParameter("tel");
        String name = request.getParameter("name");
        String idStr = request.getParameter("id");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(province == null||"".equals(province)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(city == null||"".equals(city)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(county == null||"".equals(county)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(town == null||"".equals(town)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(detail == null||"".equals(detail)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(tel == null||"".equals(tel)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(name == null||"".equals(name)){
            map.put("status","1");
            map.put("msg","请填写完表单！");
            return map;
        }
        if(idStr == null||"".equals(idStr)){
            map.put("status","1");
            map.put("msg","请选中要修改的收货地址！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        Long id= Long.valueOf(idStr);
        map = addressService.updateAddress(id,province,city,county,town,detail,tel,name);
        return map;
    }
    @DeleteMapping("/address")
    public HashMap deleteAddressList(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String idStr = request.getParameter("id");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(idStr == null||"".equals(idStr)){
            map.put("status","1");
            map.put("msg","请选中要删除的收货地址！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        Long id= Long.valueOf(idStr);
        map = addressService.deleteAddress(id);
        return map;
    }
    @GetMapping("/addressList")
    public HashMap getAddressList(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        map = addressService.getAddressListByUserId(user.getUserId());
        return map;
    }

    @GetMapping("SimpleAddressList")
    public HashMap getSimpleAddressList(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        map = addressService.getSimpleAddressListByUserId(user.getUserId());
        return map;
    }
}
