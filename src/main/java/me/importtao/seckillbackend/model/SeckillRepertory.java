package me.importtao.seckillbackend.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class SeckillRepertory implements Serializable {
    private Long seckillId;

    private String seckillName;

    private Integer seckillNumber;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public String getSeckillName() {
        return seckillName;
    }

    public void setSeckillName(String seckillName) {
        this.seckillName = seckillName == null ? null : seckillName.trim();
    }

    public Integer getSeckillNumber() {
        return seckillNumber;
    }

    public void setSeckillNumber(Integer seckillNumber) {
        this.seckillNumber = seckillNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}