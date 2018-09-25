package com.jascola.entity;

import java.io.Serializable;

public class CollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phone;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
