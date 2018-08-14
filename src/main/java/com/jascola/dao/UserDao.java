package com.jascola.dao;

import com.jascola.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int insert(User user);

    User selectByPhone(String phone);

    int delete(String phone);

    int update(User user);

    User selectById(Integer id);

    List<User> selectAll();
}
