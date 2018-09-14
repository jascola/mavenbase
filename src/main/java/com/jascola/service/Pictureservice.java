package com.jascola.service;

import com.jascola.dao.PictureDao;
import com.jascola.dto.PicQueryDto;
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

    public List<PicturesEntity> selectByPicName(String picname) {
        return dao.selectByPicName(picname);
    }

    public List<PicturesEntity> selectByAuName(PicQueryDto dto) {
        return dao.selectByAuName(dto);
    }

    public int deleteByPicName(String picname) {
        return dao.deleteByPicName(picname);
    }

    public int deleteByAuName(String authorname) {
        return dao.deleteByAuName(authorname);
    }

    public int deleteById(String id) {
        return dao.deleteById(id);
    }

    public int update(PicturesEntity entity) {
        return dao.update(entity);
    }

    public List<PicturesEntity> selectAll(PicQueryDto dto) {
        return dao.selectAll(dto);
    }

    public List<PicturesEntity> selectById(String id) {
        return dao.selectById(id);
    }

    public Integer selectCount() {
        return dao.selectCount();
    }

    public Integer selectCountByAuName(String authorname) {
        return dao.selectCountByAuName(authorname);
    }
}
