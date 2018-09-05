package com.jascola.dao;

import com.jascola.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int insert(UserEntity user);

    UserEntity selectByPhone(String phone);

    int delete(String phone);

    int update(UserEntity user);

    UserEntity selectById(Integer id);

    List<UserEntity> selectAll();
}
