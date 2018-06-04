package me.importtao.seckillbackend.viewModel;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Package me.importtao.seckillbackend.viewModel
 * Class OrderModel
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/21 20:08
 * @version V1.0
 */
@Component
public class OrderModel implements Serializable {
    private String orderId;

    private String userId;

    private String goodsId;

    private String goodsName;

    private Byte model;

    private String modelName;

    private Date createTime;

    private Integer number;

    private Double money;

    private String addr;

    private String addrName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Byte getModel() {
        return model;
    }

    public void setModel(Byte model) {
        this.model = model;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    private String paycode;

    private Byte state;



    private static final long serialVersionUID = 1L;
}
