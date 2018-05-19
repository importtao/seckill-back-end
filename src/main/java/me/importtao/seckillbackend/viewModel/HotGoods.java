package me.importtao.seckillbackend.viewModel;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Package me.importtao.seckillbackend.viewModel
 * Class HotGoods
 * Description: 最热商品
 *
 * @author importtao
 * date 2018/5/19 17:58
 * @version V1.0
 */
@Component
public class HotGoods implements Serializable {
    private static final long serialVersionUID = 1L;

    private String goodsId;
    private String name;
    private String detail;
    private String img;
    private Double price;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return img;
    }

    public void setImage(String image) {
        this.img = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
