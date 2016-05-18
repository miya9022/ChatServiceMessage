package com.example.observer.chatservicemessage.model;

import java.io.Serializable;

/**
 * Created by observer on 05/13/2016.
 */
public class UserInfo implements Serializable {
    private String uid;
    private String img_url;
    private String username;
    private String name;
    private String email;

    public UserInfo() {
    }

    public UserInfo(String email) {
        this.email = email;
    }

    public UserInfo(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public UserInfo(String email, String img_url, String name, String uid) {
        this.email = email;
        this.img_url = img_url;
        this.name = name;
        this.uid = uid;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
