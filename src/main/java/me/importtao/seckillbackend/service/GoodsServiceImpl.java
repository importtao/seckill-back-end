package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.dao.GoodsInfoMapper;
import me.importtao.seckillbackend.dao.GoodsMapper;
import me.importtao.seckillbackend.dao.GoodsModelMapper;
import me.importtao.seckillbackend.model.*;
import me.importtao.seckillbackend.util.GeneratorId;
import me.importtao.seckillbackend.util.SellerToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Package me.importtao.seckillbackend.service
 * Class GoodsServiceImpl
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 21:33
 * @version V1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private GoodsModelMapper goodsModelMapper;
    @Resource
    private SellerToken sellerToken;
    public GoodsServiceImpl() {
        super();
    }

    /**
     * description 添加商品
     *
     * @param token token
     * @param goods 商品实体
     * @param goodsInfo goodsInfo实体 @return HashMap
     * @author importtao
     * @date 2018/5/7 21:36
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap addGoods(String token, Goods goods, GoodsInfo goodsInfo) {
        HashMap map = new HashMap(16);
        boolean tokenAble = sellerToken.tokenValidate(token);
        if(tokenAble){
            HashMap data = sellerToken.getSellerByToken(token);
            Seller seller = (Seller) data.get("seller");
            goods.setSellerId(seller.getSellerId());
            goodsMapper.insert(goods);
            goodsInfoMapper.insert(goodsInfo);
            map.put("status",0);
            map.put("msg","添加成功");
        }else{
            map.put("status",2);
            map.put("msg","sellerToken无效或登录超时");
        }
        return map;
    }

    /**
     * description 添加商品型号及库存
     *
     * @param token token
     * @param goodsModel  @return
     * @author importtao
     * @date 2018/5/8 18:00
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap addGoodsModel(String token, GoodsModel goodsModel) {
        HashMap map = new HashMap(16);
        boolean tokenAble = sellerToken.tokenValidate(token);
        if(tokenAble){
            Byte maxCode = goodsModelMapper.selectMaxModelCode(goodsModel.getGoodsId());
            maxCode++;
            goodsModel.setModelCode(maxCode);
            goodsModelMapper.insert(goodsModel);
            map.put("status",0);
            map.put("msg","添加成功");
        }else{
            map.put("status",2);
            map.put("msg","sellerToken无效或登录超时");
        }
        return map;
    }

    /**
     * description 通过token获取商户商品列表
     *
     * @param token token
     * @return HashMap
     * @author importtao
     * @date 2018/5/8 18:29
     */
    @Override
    public HashMap getGoodsList(String token) {
        HashMap map = new HashMap(16);
        boolean tokenAble = sellerToken.tokenValidate(token);
        if(tokenAble){
            HashMap data = sellerToken.getSellerByToken(token);
            Seller seller = (Seller) data.get("seller");
            List<Goods> goodsList = goodsMapper.selectBySellerId(seller.getSellerId());
            List<HashMap> result = getModel(goodsList);
            map.put("status",0);
            map.put("result",result);
        }else {
            map.put("status",2);
            map.put("msg","sellerToken无效或登录超时");
        }
        return map;
    }

    /**
     * description 通过关键字获取商品
     *
     * @param keyWord keyWord
     * @return HashMap
     * @author importtao
     * @date 2018/5/8 20:05
     */
    @Override
    public HashMap getGoodsByKeyword(String keyWord) {
        HashMap map = new HashMap(16);
        List<Goods> goodsList = goodsMapper.selectByKeyWord(keyWord);
        List<HashMap> result = getModel(goodsList);
        map.put("status",0);
        map.put("result",result);
        return map;
    }

    /**
     * description 删除商品
     *
     * @param token token
     * @param goodsId @return
     * @author importtao
     * @date 2018/5/8 20:14
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap deleteGoods(String token, String goodsId) {
        HashMap map = new HashMap(16);
        boolean tokenAble = sellerToken.tokenValidate(token);
        if(tokenAble){
            goodsMapper.deleteByPrimaryKey(goodsId);
            goodsInfoMapper.deleteByPrimaryKey(goodsId);
            goodsModelMapper.deleteByGoodsId(goodsId);
            map.put("status",0);
            map.put("msg","删除成功");
        }else {
            map.put("status",2);
            map.put("msg","sellerToken无效或登录超时");
        }
        return map;
    }

    /**
     * description 修改商品信息
     *
     * @param token     token , goods,  goodsInfo
     * @param goods goods
     * @param goodsInfo @return HashMap
     * @author importtao
     * @date 2018/5/9 16:38
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap updateGoods(String token, Goods goods, GoodsInfo goodsInfo) {
        HashMap map = new HashMap(16);
        boolean tokenAble = sellerToken.tokenValidate(token);
        if(tokenAble){
            goodsMapper.updateByPrimaryKeySelective(goods);
            goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
            map.put("status",0);
            map.put("msg","修改成功");
        }else {
            map.put("status",2);
            map.put("msg","sellerToken无效或登录超时");
        }
        return map;
    }

    /**
     * description 修改型号
     *
     * @param token      goodsModel
     * @param goodsModel
     * @return HashMap
     * @author importtao
     * @date 2018/5/9 16:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap updateModel(String token, GoodsModel goodsModel) {
        HashMap map = new HashMap(16);
        boolean tokenAble = sellerToken.tokenValidate(token);
        if(tokenAble){
            goodsModelMapper.updateByPrimaryKeySelective(goodsModel);
            map.put("status",0);
            map.put("msg","修改成功");
        }else {
            map.put("status",2);
            map.put("msg","sellerToken无效或登录超时");
        }
        return map;
    }

    /**
     * description 获取商品信息
     *
     * @param goodsId goodsId
     * @return HashMap
     * @author importtao
     * @date 2018/5/9 22:45
     */
    @Override
    public HashMap getGoods(String goodsId) {
        HashMap map = new HashMap(16);
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
        List<GoodsModel> goodsModels = goodsModelMapper.selectByGoodsId(goodsId);
        map.put("goods",goods);
        map.put("goodsInfo",goodsInfo);
        map.put("goodsModel",goodsModels);
        return map;
    }

    public List<HashMap> getModel(List<Goods> goodsList){
        List<HashMap> result = new ArrayList<>();
        for(Goods goods:goodsList){
            HashMap model = new HashMap(16);
            model.put("goods",goods);
            GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goods.getGoodsId());
            model.put("goodsInfo",goodsInfo);
            List<GoodsModel> goodsModelList = goodsModelMapper.selectByGoodsId(goods.getGoodsId());
            model.put("goodsModel",goodsModelList);
            result.add(model);
        }
        return result;
    }
}
