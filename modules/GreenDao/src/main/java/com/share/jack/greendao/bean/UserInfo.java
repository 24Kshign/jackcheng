package com.share.jack.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 *
 */
@Entity
public class UserInfo {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String username;

    private String password;

    private Date date;

    @Generated(hash = 1020062579)
    public UserInfo(Long id, String username, String password, Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
