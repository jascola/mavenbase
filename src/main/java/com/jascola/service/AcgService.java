package com.jascola.service;


import com.jascola.dao.AcgDao;
import com.jascola.entity.AcgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcgService {
    @Autowired
    private AcgDao dao;
    public int insert(AcgEntity entity){
        return dao.insert(entity);
    }
    public int update(AcgEntity entity){
        return dao.update(entity);
    }

}
