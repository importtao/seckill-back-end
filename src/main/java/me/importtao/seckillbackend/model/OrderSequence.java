package me.importtao.seckillbackend.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class OrderSequence implements Serializable {
    private Date todayDate;

    private String maxSequence;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Date getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public String getMaxSequence() {
        return maxSequence;
    }

    public void setMaxSequence(String maxSequence) {
        this.maxSequence = maxSequence == null ? null : maxSequence.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}