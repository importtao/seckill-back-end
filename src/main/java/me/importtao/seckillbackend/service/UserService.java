package me.importtao.seckillbackend.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class UserService
 * Description: 用户接口
 *
 * @author importtao
 * date 2018/2/14 22:38
 * @version V1.0
 */
public interface UserService {

    /**
     * Description TODO用户注册
     * @author importtao
     * @date 2018/2/12 14:01
     * @param  request 封装请求信息
     * @return map
     */

    HashMap register(HttpServletRequest request);


    /**
     * description 修改密码
     * @author importtao
     * @date 2018/2/12 20:30
     * @param request 封装请求信息
     * @return   map
     */
    HashMap changePassword(HttpServletRequest request);

    /**
     * description 密码验证
     * @author importtao
     * @date 2018/2/12 20:33
     * @param request 封装请求信息
     * @return  map
     */
    HashMap passwordCheck(HttpServletRequest request);
    /**
     * description 手机号验证
     * @author importtao
     * @date 2018/2/12 20:33
     * @param request 封装请求信息
     * @return  map
     */
    HashMap phoneCheck(HttpServletRequest request);

    /**
     * description 验证用户名是否被注册
     * @author importtao
     * @date 2018/2/14 22:54
     * @param request 封装请求信息
     * @return map
     */
    HashMap userNameCheck(HttpServletRequest request);
    /**
     * description 用户登录
     * @author importtao
     * @date 2018/2/14 22:54
     * @param request 封装请求信息
     * @return map
     */
    HashMap login(HttpServletRequest request);
}
