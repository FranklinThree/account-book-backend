package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.server.Picture;

import java.util.List;

public interface PictureService {

    /**
     * 保存图片
     *
     * @param picture 图片
     * @return int
     */
    int savePicture(Picture picture);

    /**
     * 根据id删除图片
     *
     * @param id id
     * @return int 影响数量
     */
    int removePictureById(Long id);

    /**
     * 根据名字删除图片
     *
     * @param name 名字
     * @return int 影响数量
     */
    int removePictureByName(String name);

    /**
     * 根据组id删除图片
     *
     * @param groupId 组id
     * @return int 影响数量
     */
    int removePictureByGroupId(Long groupId);



//    /**
//     * 更新图片
//     *
//     * @param picture 图片
//     * @return int 影响数量
//     */
//    int updatePicture(Picture picture);

    /**
     * 根据id获取图片
     *
     * @param id id
     * @return {@link Picture } 图片
     */
    Picture getPictureById(Long id);

    /**
     * 根据名字获取图片
     *
     * @param name 名字
     * @return {@link Picture } 图片
     */
    Picture getPictureByName(String name);

    /**
     * 根据组id获取图片
     *
     * @param groupId 组id
     * @return {@link List }<{@link Picture }>
     */
    List<Picture> getPicturesByGroupId(Long groupId);



}
