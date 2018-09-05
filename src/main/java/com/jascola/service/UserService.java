package com.jascola.service;


import com.jascola.dao.UserDao;
import com.jascola.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao dao;
    @Transactional
    public int insert(UserEntity user){
        return dao.insert(user);
    }
    public UserEntity selectByPhone(String phone){
        return dao.selectByPhone(phone);
    }
    @Transactional
    public int delete(String phone){
        return this.dao.delete(phone);
    }
    @Transactional
    public int update(UserEntity user){
        return this.dao.update(user);
    }
    public UserEntity selectById(Integer id){
        return  this.dao.selectById(id);
    }
    public List<UserEntity> selectAll(){return this.dao.selectAll();}
}
