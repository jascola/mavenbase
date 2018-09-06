package com.jascola.service;

import com.jascola.dao.PictureDao;
import com.jascola.entity.PicturesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pictureservice {

    @Autowired
    private PictureDao dao;

    public int insert(PicturesEntity entity) {
        return dao.insert(entity);
    }

    public PicturesEntity selectByPicName(String picname) {
        return dao.selectByPicName(picname);
    }

    public List<PicturesEntity> selectByAuName(String authorname) {
        return dao.selectByAuName(authorname);
    }

    public int deleteByPicName(String picname) {
        return dao.deleteByPicName(picname);
    }

    public int deleteByAuName(String authorname) {
        return dao.deleteByAuName(authorname);
    }

    public int update(PicturesEntity entity) {
        return dao.update(entity);
    }

    public List<PicturesEntity> selectAll() {
        return dao.selectAll();
    }

}
