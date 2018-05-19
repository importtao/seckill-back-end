package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.dao.CartMapper;
import me.importtao.seckillbackend.model.Cart;
import me.importtao.seckillbackend.viewModel.WishListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Package me.importtao.seckillbackend.service
 * Class CartServiceImpl
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/19 15:12
 * @version V1.0
 */
@Service
public class CartServiceImpl implements CartService{
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    @Resource
    private Cart cart;
    @Resource
    private CartMapper cartMapper;
    /**
     * description 添加到购物车
     *
     * @param goodsId 商品id
     * @param userId  用户Id
     * @param model   型号
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 15:14
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap addToCartList(String goodsId, String userId, Byte model) {
        HashMap<String,Object> map = new HashMap<>(16);
        cart.setGoodsId(goodsId);
        cart.setModel(model);
        cart.setUserId(userId);
        int result = cartMapper.insertSelective(cart);
        if(result == 1){
            map.put("status","0");
            map.put("msg","添加成功！");
        }else{
            map.put("status","1");
            map.put("msg","添加失败！");
        }
        return map;
    }

    /**
     * description 获取用户购物车列表
     *
     * @param userId 用户ID
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 15:17
     */
    @Override
    public HashMap getCartList(String userId) {
        HashMap<String,Object> map = new HashMap<>(16);
        List<WishListModel> wishList = cartMapper.selectByUserId(userId);
        map.put("status","0");
        map.put("wishList",wishList);
        return map;
    }

    /**
     * description 从购物车删除
     *
     * @param userId  用户ID
     * @param goodsId 商品Id
     * @return HashMap
     * @author importtao
     * @date 2018/5/19 15:19
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap deleteCartList(String userId, String goodsId) {
        HashMap<String,Object> map = new HashMap<>(16);
        cartMapper.deleteByUserGoodsId(userId,goodsId);
        map.put("status","0");
        map.put("msg","删除成功");
        return map;
    }
}
