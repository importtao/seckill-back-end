package me.importtao.seckillbackend.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class SuccessKilledKey implements Serializable {
    private Long seckillId;

    private Long userPhone;

    private static final long serialVersionUID = 1L;

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }
}