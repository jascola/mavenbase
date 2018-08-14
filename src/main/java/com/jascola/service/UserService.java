package com.jascola.service;


import com.jascola.dao.UserDao;
import com.jascola.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 86085359 on 2018/4/16.
 */
@Service
public class UserService {
    @Autowired
    private UserDao dao;
    @Transactional
    public int insert(User user){
        return dao.insert(user);
    }
    public User selectByPhone(String phone){
        return dao.selectByPhone(phone);
    }
    @Transactional
    public int delete(String phone){
        return this.dao.delete(phone);
    }
    @Transactional
    public int update(User user){
        return this.dao.update(user);
    }
    public User selectById(Integer id){
        return  this.dao.selectById(id);
    }
    public List<User> selectAll(){return this.dao.selectAll();}
}
