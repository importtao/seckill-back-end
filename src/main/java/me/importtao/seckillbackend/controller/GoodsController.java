package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.model.Goods;
import me.importtao.seckillbackend.model.GoodsInfo;
import me.importtao.seckillbackend.model.GoodsModel;
import me.importtao.seckillbackend.service.GoodsService;
import me.importtao.seckillbackend.util.GeneratorId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.controller
 * Class GoodsController
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 21:31
 * @version V1.0
 */
@RestController
@CrossOrigin
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private GoodsService goodsService;
    @Resource
    private Goods goods;
    @Resource
    private GoodsInfo goodsInfo;
    @Resource
    private GoodsModel goodsModel;
    @Resource
    private GeneratorId generatorId;

    @PostMapping("/goods")
    public HashMap addGoods(HttpServletRequest request){
        //{'name, 'detail', 'image', 'detailImg', 'price'}
        String token = request.getParameter("sellerToken");
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String image = request.getParameter("image");
        String detailImg = request.getParameter("detailImg");
        String price = request.getParameter("price");
        HashMap map = new HashMap(16);
        String defaultImg = "img.png";
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(name == null||"".equals(name)){
            map.put("status","1");
            map.put("msg","商品名不能为空");
            return map;
        }
        if(image == null||"".equals(image)){
            image = defaultImg;
        }
        if(detailImg == null||"".equals(detailImg)){
            detailImg = defaultImg;
        }
        if(price == null||"".equals(price)){
            price = "0";
        }
        String goodsId = null;
        try {
            goodsId = generatorId.getGoodsId();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成goodsId错误");
            map.put("status","1");
            map.put("msg","系统错误：生成goodsId错误");
            return map;
        }
        goods.setGoodsId(goodsId);
        goods.setName(name);
        goods.setImage(image);
        goods.setDetail(detail);
        goodsInfo.setPrice(Double.valueOf(price));
        goodsInfo.setImage(detailImg);
        goodsInfo.setGoodsId(goodsId);
        map = goodsService.addGoods(token,goods,goodsInfo);
        return map;
    }
    @PostMapping("/goodsModel")
    public HashMap addGoodsModel(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String token = request.getParameter("sellerToken");
        String goodsId = request.getParameter("goodsId");
        String inventry = request.getParameter("inventry");
        String description = request.getParameter("description");
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","未选中商品");
            return map;
        }
        if(inventry == null||"".equals(inventry)){
            inventry = "0";
        }
        if(description == null||"".equals(description)){
            map.put("status","1");
            map.put("msg","请填写类型描述");
            return map;
        }
        goodsModel.setGoodsId(goodsId);
        goodsModel.setInventry(Integer.parseInt(inventry));
        goodsModel.setDiscription(description);
        map = goodsService.addGoodsModel(token,goodsModel);
        return map;
    }

    @GetMapping("/goodsList")
    public HashMap getGoodsListByKeyWord(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String keyWord = request.getParameter("keyWord");
        if(keyWord == null||"".equals(keyWord)){
            map.put("status","1");
            map.put("msg","请输入关键词");
            return map;
        }
        map = goodsService.getGoodsByKeyword(keyWord);
        return map;
    }

    @GetMapping("/myGoodsList")
    public HashMap getMyGoodsList(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String token = request.getParameter("sellerToken");
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        map = goodsService.getGoodsList(token);
        return map;
    }

    @DeleteMapping("/goods")
    public HashMap delete(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String token = request.getParameter("sellerToken");
        String goodsId = request.getParameter("goodsId");
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","未选中商品");
            return map;
        }
        map = goodsService.deleteGoods(token,goodsId);
        return map;
    }

    @PutMapping("/goods")
    public HashMap updateGoods(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String token = request.getParameter("sellerToken");
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String image = request.getParameter("image");
        String detailImg = request.getParameter("detailImg");
        String price = request.getParameter("price");
        String goodsId = request.getParameter("goodsId");
        String defaultImg = "img.png";
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(name == null||"".equals(name)){
            map.put("status","1");
            map.put("msg","商品名不能为空");
            return map;
        }
        if(image == null||"".equals(image)){
            image = defaultImg;
        }
        if(detailImg == null||"".equals(detailImg)){
            detailImg = defaultImg;
        }
        if(price == null||"".equals(price)){
            price = "0";
        }
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","未选中有效商品");
            return map;
        }
        goods.setGoodsId(goodsId);
        goods.setName(name);
        goods.setImage(image);
        goods.setDetail(detail);
        goodsInfo.setPrice(Double.valueOf(price));
        goodsInfo.setImage(detailImg);
        goodsInfo.setGoodsId(goodsId);
        map = goodsService.updateGoods(token,goods,goodsInfo);
        return map;
    }

    @PutMapping("/goodsModel")
    public HashMap updateGoodsModel(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String token = request.getParameter("sellerToken");
        String goodsId = request.getParameter("goodsId");
        String inventry = request.getParameter("inventry");
        String description = request.getParameter("description");
        String modelCode = request.getParameter("modelCode");
        String id = request.getParameter("id");
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","未选中商品");
            return map;
        }
        if(id == null||"".equals(id)){
            map.put("status","1");
            map.put("msg","未选中有效类型");
            return map;
        }
        if(inventry == null||"".equals(inventry)){
            inventry = "0";
        }
        if(description == null||"".equals(description)){
            map.put("status","1");
            map.put("msg","请填写类型描述");
            return map;
        }
        if(modelCode == null||"".equals(modelCode)){
            modelCode = "0";
        }
        goodsModel.setGoodsId(goodsId);
        goodsModel.setInventry(Integer.parseInt(inventry));
        goodsModel.setDiscription(description);
        goodsModel.setId(Long.valueOf(id));
        Byte b = 0;
        b = Byte.valueOf(modelCode);
        goodsModel.setModelCode(b);
        map = goodsService.updateModel(token,goodsModel);
        return map;
    }
    @GetMapping("/goods")
    public HashMap getGoods(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String goodsId = request.getParameter("goodsId");
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","未选中商品");
            return map;
        }
        map = goodsService.getGoods(goodsId);
        map.put("status","0");
        return map;
    }
}
