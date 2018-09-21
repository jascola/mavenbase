package com.jascola.entity;

import java.io.Serializable;

/**
 * 管理员
 */
public class AdminEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 密码
     */
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
