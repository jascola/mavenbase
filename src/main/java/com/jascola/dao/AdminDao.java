package com.jascola.dao;

import com.jascola.entity.AdminEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminDao {

    AdminEntity selectByPhone(String phone);

    int update(AdminEntity entity);

}
