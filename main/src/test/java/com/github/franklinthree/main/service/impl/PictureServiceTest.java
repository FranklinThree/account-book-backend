package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.Picture;
import com.github.franklinthree.main.model.PictureFactory;
import com.github.franklinthree.main.service.PictureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 图片服务实现类测试
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className PictureServiceTest
 * @see
 * @since 1.0.0
 */
@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class PictureServiceTest {
    @Autowired
    private PictureService pictureService;
    
    @Autowired
    private PictureFactory pictureFactory;

    @BeforeEach
    void setUp() {
        System.out.println("PictureServiceTest-----------------------------------------------");
    }


    @Test
    @Order(1)
    public void savePicture(){
        Picture picture = pictureFactory.createPicture("0.jpg", new Byte[]{0,1,2,3,3,3,3},0L);
        Picture picture1 = pictureFactory.createPicture("1.bmp", new Byte[]{0,1,2,3,3,3,33},0L);
        Picture picture2 = pictureFactory.createPicture("2.png", new Byte[]{0,1,2,3,3,3,31},0L);
        Picture picture3 = pictureFactory.createPicture("3.jpeg", new Byte[]{0,1,2,3,3,3,30},0L);
        Picture picture4 = pictureFactory.createPicture("4.webp", new Byte[]{0,1,2,3,3,3,38},0L);
        Picture picture10 = pictureFactory.createPicture("5.jpg", new Byte[]{0,1,2,31,32,33,3},1L);
        System.out.println("插入图片：");

        int count = pictureService.savePicture(picture);
        System.out.println(count);
        assert count == 1;

        count = pictureService.savePicture(picture1);
        System.out.println(count);
        assert count == 1;

        count = pictureService.savePicture(picture2);
        System.out.println(count);
        assert count == 1;

        count = pictureService.savePicture(picture3);
        System.out.println(count);
        assert count == 1;

        count = pictureService.savePicture(picture4);
        System.out.println(count);
        assert count == 1;

        count = pictureService.savePicture(picture10);
        System.out.println(count);
        assert count == 1;
    }

    @Test
    @Order(4)
    public void removePictureById(){
        System.out.println("删除图片：");
        Picture picture11 = pictureFactory.createPicture("6.jpg", new Byte[]{0,1,62,31,32,33,3},1L);
        pictureService.savePicture(picture11);
        int count = pictureService.removePictureById(picture11.getId());
        System.out.println(count);
        assert count == 1;
    }

    @Test
    @Order(5)
    public void removePictureByGroupId(){
        System.out.println("批量删除图片：");
        Picture picture = pictureFactory.createPicture("0.jpg", new Byte[]{0,1,2,3,3,3,3},0L);
        Picture picture10 = pictureFactory.createPicture("5.jpg", new Byte[]{0,1,2,31,32,33,3},1L);

        int count = pictureService.removePictureByGroupId(picture.getGroupId());
        System.out.println(count);
        assert count > 0;

        int count1 = pictureService.removePictureByGroupId(picture10.getGroupId());
        System.out.println(count1);
        assert count1 > 0;
    }

    @Test
    @Order(2)
    public void getPictureById(){
        System.out.println("根据id获取图片：");
        Picture picture21 = pictureFactory.createPicture("6.jpg", new Byte[]{0,1,62,31,32,33,3},1L);
        pictureService.savePicture(picture21);
        Picture pictureById = pictureService.getPictureById(picture21.getId());
        System.out.println(pictureById);
        assert pictureById != null;
    }

    @Test
    @Order(2)
    public void getPictureByName(){
        System.out.println("根据name获取图片：");

        Picture picture21 = pictureFactory.createPicture("6.jpg", new Byte[]{0,1,62,31,32,33,3},1L);
        pictureService.savePicture(picture21);
        Picture pictureByName = pictureService.getPictureByName(picture21.getName());
        System.out.println(pictureByName);
        assert pictureByName != null;
    }

    @Test
    @Order(3)
    public void getPicturesByGroupId(){
        System.out.println("根据groupId获取图片：");
        Picture picture = pictureFactory.createPicture("0.jpg", new Byte[]{0,1,2,3,3,3,3},0L);
        List<Picture> pictureByGroupId = pictureService.getPicturesByGroupId(picture.getGroupId());
        for (Picture aPicture : pictureByGroupId) {
            assert aPicture.getName() != null;
            System.out.println(aPicture);
        }
    }
}
