package me.importtao.seckillbackend.service;

import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class AddressService
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/19 20:20
 * @version V1.0
 */
public interface AddressService {

    /**
     * description 获取用户收货地址列表
     * @author importtao
     * @date 2018/5/19 20:22
     * @param userId 用户ID
     * @return   HashMap
     */
    HashMap getAddressKistByUserId(String userId);

    /**
     * description 添加收货地址
     * @author importtao
     * @date 2018/5/19 20:27
     * @param userId 用户ID
     * @param province 省
     * @param city  市
     * @param county 县
     * @param town 乡镇街道
     * @param detail 详细地址
     * @param tel 电话
     * @param name  姓名
     * @return HashMap
     */
    HashMap addAddress(String userId,String province,String city,String county,String town,String detail,String tel,String name);

    /**
     * description 修改收货地址
     * @author importtao
     * @date 2018/5/19 20:27
     * @param id 用户ID
     * @param province 省
     * @param city  市
     * @param county 县
     * @param town 乡镇街道
     * @param detail 详细地址
     * @param tel 电话
     * @param name  姓名
     * @return HashMap
     */
    HashMap updateAddress(Long id,String province,String city,String county,String town,String detail,String tel,String name);

    /**
     * description 删除收货地址
     * @author importtao
     * @date 2018/5/19 20:35
     * @param id id
     * @return   HashMap
     */
    HashMap deleteAddress(Long id);
}
