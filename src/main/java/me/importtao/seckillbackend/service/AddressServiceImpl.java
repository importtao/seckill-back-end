package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.dao.AddrMapper;
import me.importtao.seckillbackend.model.Addr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class AddressServiceImpl
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/19 20:21
 * @version V1.0
 */
@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    @Resource
    private AddrMapper addrMapper;
    @Resource
    private Addr addr;

    /**
     * description 获取用户收货地址列表
     *
     * @param userId 用户ID
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 20:22
     */
    @Override
    public HashMap getAddressKistByUserId(String userId) {
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("addList",addrMapper.selectByUserId(userId));
        map.put("satus","0");
        return map;
    }

    /**
     * description 添加收货地址
     *
     * @param userId   用户ID
     * @param province 省
     * @param city     市
     * @param county   县
     * @param town     乡镇街道
     * @param detail   详细地址
     * @param tel      电话
     * @param name     姓名
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 20:27
     */
    @Override
    public HashMap addAddress(String userId, String province, String city, String county, String town, String detail, String tel, String name) {
        HashMap<String,Object> map = new HashMap<>(16);
        addr.setProvince(province);
        addr.setCity(city);
        addr.setCounty(county);
        addr.setDetail(detail);
        addr.setTown(town);
        addr.setUserId(userId);
        addr.setName(name);
        addr.setTel(tel);
        int result = addrMapper.insertSelective(addr);
        if(result == 1){
            map.put("status","0");
            map.put("msg","插入成功");
        }else{
            map.put("status","1");
            map.put("msg","插入失败");
        }
        return map;
    }

    /**
     * description 修改收货地址
     *
     * @param id       用户ID
     * @param province 省
     * @param city     市
     * @param county   县
     * @param town     乡镇街道
     * @param detail   详细地址
     * @param tel      电话
     * @param name     姓名
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 20:27
     */
    @Override
    public HashMap updateAddress(Long id, String province, String city, String county, String town, String detail, String tel, String name) {
        HashMap<String,Object> map = new HashMap<>(16);
        addr.setProvince(province);
        addr.setCity(city);
        addr.setCounty(county);
        addr.setDetail(detail);
        addr.setTown(town);
        addr.setName(name);
        addr.setTel(tel);
        addr.setId(id);
        int result = addrMapper.updateByPrimaryKeySelective(addr);
        if(result == 1){
            map.put("status","0");
            map.put("msg","修改成功");
        }else{
            map.put("status","1");
            map.put("msg","修改失败");
        }
        return map;
    }

    /**
     * description 删除收货地址
     *
     * @param id id
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 20:35
     */
    @Override
    public HashMap deleteAddress(Long id) {
        HashMap<String,Object> map = new HashMap<>(16);

        int result = addrMapper.deleteByPrimaryKey(id);
        if(result == 1){
            map.put("status","0");
            map.put("msg","删除成功");
        }else{
            map.put("status","1");
            map.put("msg","删除失败");
        }
        return map;
    }
}
