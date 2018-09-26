package com.jascola.dao;

import com.jascola.dto.PicQueryDto;
import com.jascola.entity.CollectionEntity;
import com.jascola.entity.PicturesEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureDao {

    int insert(PicturesEntity entity);

    List<PicturesEntity> selectByPicName(String picname);

    List<PicturesEntity> selectByAuName(PicQueryDto dto);

    int deleteByPicName(String picname);

    int deleteById(String id);

    int deleteByAuName(String authorname);

    int update(PicturesEntity entity);

    List<PicturesEntity> selectAll(PicQueryDto dto);

    List<PicturesEntity> selectById(String id);

    Integer selectCount();

    Integer selectCountByAuName(String authorname);

    List<PicturesEntity> checkCollected(String phone);

    int collect(CollectionEntity entity);

    int outCollect(CollectionEntity entity);

    List<PicturesEntity> selectNoLimit(PicQueryDto dto);

    Integer selectNoCount(PicQueryDto dto);
}
