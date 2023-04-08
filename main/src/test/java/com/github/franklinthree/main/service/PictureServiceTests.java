package com.github.franklinthree.main.service;

import com.github.franklinthree.main.pojo.Picture;
import com.github.franklinthree.main.pojo.PictureFactory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 图片服务测试
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className PictureServiceTests
 * @see
 * @since 1.0.0
 */
@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class PictureServiceTests {
    @Autowired
    private PictureService pictureService;
    
    @Autowired
    private PictureFactory pictureFactory;




    @Test
    @Order(1)
    public void testInsert(){
        Picture picture = pictureFactory.createPicture("0.jpg", new Byte[]{0,1,2,3,3,3,3},0L);
        Picture picture1 = pictureFactory.createPicture("1.bmp", new Byte[]{0,1,2,3,3,3,33},0L);
        Picture picture2 = pictureFactory.createPicture("2.png", new Byte[]{0,1,2,3,3,3,31},0L);
        Picture picture3 = pictureFactory.createPicture("3.jpeg", new Byte[]{0,1,2,3,3,3,30},0L);
        Picture picture4 = pictureFactory.createPicture("4.webp", new Byte[]{0,1,2,3,3,3,38},0L);
        Picture picture10 = pictureFactory.createPicture("5.jpg", new Byte[]{0,1,2,31,32,33,3},1L);
        System.out.println("插入图片：----------------------");
        System.out.println(pictureService.savePicture(picture));
        System.out.println(pictureService.savePicture(picture1));
        System.out.println(pictureService.savePicture(picture2));
        System.out.println(pictureService.savePicture(picture3));
        System.out.println(pictureService.savePicture(picture4));
        System.out.println(pictureService.savePicture(picture10));
        System.out.println("--------------------------");
    }

    @Test
    @Order(4)
    public void testRemovePictureById(){
        System.out.println("删除图片：----------------------");
        Picture picture11 = pictureFactory.createPicture("6.jpg", new Byte[]{0,1,62,31,32,33,3},1L);
        pictureService.savePicture(picture11);
        System.out.println(pictureService.removePictureById(picture11.getId()));
        System.out.println("--------------------------");
    }

    @Test
    @Order(5)
    public void testRemovePictureByGroupId(){
        System.out.println("批量删除图片：----------------------");
        Picture picture = pictureFactory.createPicture("0.jpg", new Byte[]{0,1,2,3,3,3,3},0L);
        Picture picture10 = pictureFactory.createPicture("5.jpg", new Byte[]{0,1,2,31,32,33,3},1L);

        System.out.println(pictureService.removePictureByGroupId(picture.getGroupId()));
        System.out.println(pictureService.removePictureByGroupId(picture10.getGroupId()));
        System.out.println("--------------------------");
    }

    @Test
    @Order(2)
    public void testGetPictureById(){
        System.out.println("根据id获取图片：----------------------");
        Picture picture21 = pictureFactory.createPicture("6.jpg", new Byte[]{0,1,62,31,32,33,3},1L);
        pictureService.savePicture(picture21);
        System.out.println(pictureService.getPictureById(picture21.getId()));
        System.out.println("--------------------------");
    }

    @Test
    @Order(2)
    public void testGetPictureByName(){
        System.out.println("根据name获取图片：----------------------");

        Picture picture21 = pictureFactory.createPicture("6.jpg", new Byte[]{0,1,62,31,32,33,3},1L);
        pictureService.savePicture(picture21);
        System.out.println(pictureService.getPictureByName(picture21.getName()));
        System.out.println("--------------------------");
    }

    @Test
    @Order(3)
    public void testGetPictureByGroupId(){
        System.out.println("根据groupId获取图片：----------------------");
        Picture picture = pictureFactory.createPicture("0.jpg", new Byte[]{0,1,2,3,3,3,3},0L);
        List<Picture> pictureByGroupId = pictureService.getPictureByGroupId(picture.getGroupId());
        pictureByGroupId.forEach(System.out::println);
        System.out.println("--------------------------");
    }
}
