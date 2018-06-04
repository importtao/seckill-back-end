package me.importtao.seckillbackend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.service
 * Class AddressServiceImplTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/19 22:39
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressServiceImplTest {
    @Resource
    private AddressService addressService;
    @Test
    public void getAddressKistByUserId() throws Exception {
        System.out.println(addressService.getAddressListByUserId("1"));
    }

    @Test
    public void addAddress() throws Exception {
        System.out.println(addressService.addAddress("1","2","2","2","2","2","2","2"));
    }

    @Test
    public void updateAddress() throws Exception {
        Long id = 47L;
        System.out.println(addressService.updateAddress(id,"1","update","2","2","2","2","2"));
    }

    @Test
    public void deleteAddress() throws Exception {
        System.out.println(addressService.deleteAddress(47L));
    }

}