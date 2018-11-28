package com.jascola.dao;

import com.jascola.entity.AcgEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AcgDao {

    int insert(AcgEntity entity);
    int update(AcgEntity entity);

}
