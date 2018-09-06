package com.jascola.service;


import com.jascola.dao.AdminDao;
import com.jascola.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    @Autowired
    private AdminDao dao;

    @Transactional
    public AdminEntity selectByPhone(String phone) {
        return dao.selectByPhone(phone);
    }

    @Transactional
    public int update(AdminEntity entity) {
        return this.dao.update(entity);
    }

}
