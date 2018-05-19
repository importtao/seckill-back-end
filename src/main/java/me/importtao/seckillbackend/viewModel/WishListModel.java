package me.importtao.seckillbackend.viewModel;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Package me.importtao.seckillbackend.viewModel
 * Class WishListModel
 * Description: wishList数据组装模型
 *
 * @author importtao
 * date 2018/5/19 15:32
 * @version V1.0
 */
@Component
public class WishListModel implements Serializable{
    private String goodsId;

    private String name;

    private String detail;

    private String img;

    private Double price;

    private String userId;

    private Byte model;

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Byte getModel() {
        return model;
    }

    public void setModel(Byte model) {
        this.model = model;
    }
}
