package com.jascola.service;

import com.jascola.dao.PictureDao;
import com.jascola.dto.PicQueryDto;
import com.jascola.entity.CollectionEntity;
import com.jascola.entity.PicturesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Pictureservice {

    @Autowired
    private PictureDao dao;

    @Transactional
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

    @Transactional
    public int deleteById(String id) {
        return dao.deleteById(id);
    }

    @Transactional
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

    public List<PicturesEntity> checkCollected(String phone) {
        return dao.checkCollected(phone);
    }

    @Transactional
    public int collect(CollectionEntity entity) {
        return dao.collect(entity);
    }

    @Transactional
    public int outCollect(CollectionEntity entity) {
        return dao.outCollect(entity);
    }

    public List<PicturesEntity> selectNoLimit(PicQueryDto dto) {
        return dao.selectNoLimit(dto);
    }

    public Integer selectNoCount(PicQueryDto dto) {
        return dao.selectNoCount(dto);
    }
}
