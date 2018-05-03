package me.importtao.seckillbackend.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserInfo implements Serializable {
    private Long id;

    private String userId;

    private String userImg;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }
}