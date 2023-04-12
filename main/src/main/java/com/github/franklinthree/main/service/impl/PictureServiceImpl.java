package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.PictureMapper;
import com.github.franklinthree.main.model.Picture;
import com.github.franklinthree.main.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;


    public int savePicture(Picture picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    public int removePictureById(Long id) {
        return pictureMapper.deleteById(id);
    }

    @Override
    public int removePictureByName(String name) {
        return pictureMapper.deleteByName(name);
    }

    @Override
    public int removePictureByGroupId(Long groupId) {
        return pictureMapper.deleteByGroupId(groupId);
    }

    @Override
    public Picture getPictureById(Long id) {
        return pictureMapper.selectById(id);
    }

    @Override
    public Picture getPictureByName(String name) {
        return pictureMapper.selectByName(name);
    }

    @Override
    public List<Picture> getPicturesByGroupId(Long groupId) {
        return pictureMapper.selectByGroupId(groupId);
    }

}
