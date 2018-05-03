package me.importtao.seckillbackend.model;

import java.io.Serializable;

public class GoodsModel implements Serializable {
    private Long id;

    private String goodsId;

    private String discription;

    private Byte modelCode;

    private Integer inventry;

    private Integer version;

    private static final long serialVersionUID = 1L;

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
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }

    public Byte getModelCode() {
        return modelCode;
    }

    public void setModelCode(Byte modelCode) {
        this.modelCode = modelCode;
    }

    public Integer getInventry() {
        return inventry;
    }

    public void setInventry(Integer inventry) {
        this.inventry = inventry;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}