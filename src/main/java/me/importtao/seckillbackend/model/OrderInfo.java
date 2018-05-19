package me.importtao.seckillbackend.model;

import java.io.Serializable;

public class OrderInfo implements Serializable {
    private Long id;

    private String orderId;

    private Double money;

    private Long addr;

    private String paycode;

    private Byte state;

    private String reservedFields;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getAddr() {
        return addr;
    }

    public void setAddr(Long addr) {
        this.addr = addr;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode == null ? null : paycode.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getReservedFields() {
        return reservedFields;
    }

    public void setReservedFields(String reservedFields) {
        this.reservedFields = reservedFields == null ? null : reservedFields.trim();
    }
}