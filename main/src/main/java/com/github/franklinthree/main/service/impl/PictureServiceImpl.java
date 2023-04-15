package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.PictureMapper;
import com.github.franklinthree.main.model.server.Picture;
import com.github.franklinthree.main.model.server.PictureFactory;
import com.github.franklinthree.main.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private PictureFactory pictureFactory;

    @Override
    @Transactional
    public int savePicture(Picture picture) {
        long createTime = System.currentTimeMillis();
        String name = pictureFactory.generateName(picture.getName(),picture.getGroupId());
        int count = pictureMapper.insert(picture, createTime, name);
        if (count == 1){
            picture.setCreateTime(createTime);
            picture.setName(name);
        }

        return count;


    }

    @Override
    @Transactional
    public int removePictureById(Long id) throws RuntimeException{
        int count = pictureMapper.deleteById(id);
        if (count != 1){
            throw new RuntimeException("删除图片失败，id=" + id + ",count=" + count);
        }
        return count;
    }

    @Override
    @Transactional
    public int removePictureByName(String name) throws RuntimeException{
        int count = pictureMapper.deleteByName(name);
        if (count != 1){
            throw new RuntimeException("删除图片失败，name=" + name + ",count=" + count);
        }
        return count;
    }

    @Override
    @Transactional

    public int removePictureByGroupId(Long groupId) {
        return pictureMapper.deleteByGroupId(groupId);
    }

    @Override
    @Transactional

    public Picture getPictureById(Long id) {
        return pictureMapper.selectById(id);
    }

    @Override
    @Transactional

    public Picture getPictureByName(String name) {
        return pictureMapper.selectByName(name);
    }

    @Override
    @Transactional

    public List<Picture> getPicturesByGroupId(Long groupId) {
        return pictureMapper.selectByGroupId(groupId);
    }

}
