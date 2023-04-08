package com.github.franklinthree.main.service;

import com.github.franklinthree.main.pojo.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户服务测试
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className UserServiceTests
 * @see
 * @since 1.0.0
 */

@SpringBootTest()
public class UserServiceTests {
    @Autowired
    private UserService userService;
    private User user = new User(null, "FranklinThree123456", "FranklinThree", "three", "222@qq.com", "15922222222", "123");


    @Test
    @Order(1)
    public void testRegisterUser(){

        System.out.println("注册用户：" + userService.registerUser(user));
        System.out.println("用户信息：" + user);
    }

    @Test
    @Order(10)
    public void testRemoveUser(){
        System.out.println("删除用户：" + userService.removeUserById(user.getId()));
        System.out.println("被删除的用户信息：" + userService.getUserByUid(user.getUid()));
    }

    @Test
    @Order(8)
    public void testUpdateUser(){
        user.setPassword("123456");
        System.out.println("更新用户：" + userService.updateUser(user));;
        System.out.println("更新后的用户信息：" + userService.getUserByUid(user.getUid()));
    }

    @Test
    @Order(3)
    public void testGetUserByUid(){
        System.out.println("Uid：" + user.getUid());
        System.out.println("用户信息：" + userService.getUserByUid(user.getUid()));
    }

    @Test
    @Order(4)
    public void testGetUserByEmail(){
        System.out.println("Email：" + user.getEmail());
        System.out.println("用户信息：" + userService.getUserByEmail(user.getEmail()));
    }

    @Test
    @Order(5)
    public void testGetUserByPhone(){
        System.out.println("Phone：" + user.getPhone());
        System.out.println("用户信息：" + userService.getUserByPhone(user.getPhone()));
    }

}
