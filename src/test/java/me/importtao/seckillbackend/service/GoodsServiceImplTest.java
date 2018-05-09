package me.importtao.seckillbackend.service;

import com.alibaba.fastjson.JSON;
import me.importtao.seckillbackend.model.Goods;
import me.importtao.seckillbackend.model.GoodsInfo;
import me.importtao.seckillbackend.model.GoodsModel;
import me.importtao.seckillbackend.model.Seller;
import me.importtao.seckillbackend.util.GeneratorId;
import me.importtao.seckillbackend.util.SellerToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.service
 * Class GoodsServiceImplTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/9 16:53
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImplTest.class);
    @Resource
    private GoodsService goodsService;
    @Resource
    private SellerService sellerService;
    @Resource
    private SellerToken sellerToken;
    @Resource
    private GeneratorId generatorId;
    @Resource
    private Goods goods;
    @Resource
    private GoodsInfo goodsInfo;
    @Resource
    private GoodsModel goodsModel;
    @Test
    public void addGoods() throws Exception {
        String token = login();
        HashMap sellerModel = sellerToken.getSellerByToken(token);
        Seller seller= (Seller) sellerModel.get("seller");
        goods.setSellerId(seller.getSellerId());
        String goodsId = generatorId.getGoodsId();
        goods.setGoodsId(goodsId);
        goods.setDetail("detail阿斯达所大所多");
        goods.setImage("img.png");
        goods.setName("测试商品01");
        goodsInfo.setGoodsId(goodsId);
        goodsInfo.setImage("img.png");
        goodsInfo.setPrice(99.120);
        HashMap result = goodsService.addGoods(token,goods,goodsInfo);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void addGoodsModel() throws Exception {
        String token = login();
        goodsModel.setGoodsId("201805092000000001");
        goodsModel.setDiscription("黑色");
        goodsModel.setInventry(12);
        Byte b = 1;
        goodsModel.setModelCode(++b);
        HashMap result =goodsService.addGoodsModel(token,goodsModel);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void getGoodsList() throws Exception {
        String token = login();
        HashMap result = goodsService.getGoodsList(token);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void getGoodsByKeyword() throws Exception {
        String keyword = "切sdasdasasd";
        HashMap result = goodsService.getGoodsByKeyword(keyword);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void deleteGoods() throws Exception {
        String token = login();
        HashMap result = goodsService.deleteGoods(token,"201805092000000002");
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void updateGoods() throws Exception {
        String token = login();
        goods.setGoodsId("201805092000000001");
        goods.setDetail("detailupdate");
        goods.setImage("img.png");
        goods.setName("update");
        goodsInfo.setGoodsId("201805092000000001");
        goodsInfo.setImage("update.png");
        goodsInfo.setPrice(99.210);
        HashMap result = goodsService.updateGoods(token,goods,goodsInfo);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void updateModel() throws Exception {
        String token = login();
        goodsModel.setGoodsId("201805092000000001");
        goodsModel.setDiscription("update");
        goodsModel.setInventry(12);
        goodsModel.setId(47L);
        Byte b = 9;
        goodsModel.setModelCode(++b);
        HashMap result = goodsService.updateModel(token,goodsModel);
        System.out.println(JSON.toJSON(result));

    }
    public String login(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("parameter","18342962398");
        request.setParameter("password","18342962398");
        HashMap login = sellerService.login(request);
        System.out.println(JSON.toJSON(login));
        String token = (String)login.get("sellerToken");
        System.out.println(token);
        return token;
    }
}