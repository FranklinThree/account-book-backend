package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.server.User;
import com.github.franklinthree.main.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户服务实现类测试
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className UserServiceTest
 * @see
 * @since 1.0.0
 */

@SpringBootTest()
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private User user = new User("FranklinThree123456", "FranklinThree", "three", "222@qq.com", "15922222222", "123");

    @BeforeEach
    void setUp() {
        System.out.println("UserServiceTest-----------------------------------------------");
    }
    @Test
    @Order(1)
    public void testRegisterUser(){
        int count = userService.saveUser(user);
        System.out.println("注册用户：" + count);
        System.out.println("用户信息：" + user);
        assert count == 1;
    }

    @Test
    @Order(10)
    public void testRemoveUser(){
        user = userService.getUserByUid(user.getUid());
        int count = userService.removeUserById(user.getId());
        System.out.println("删除用户：" + count);
        System.out.println("被删除的用户信息：" + user);
        assert count == 1;
    }

    @Test
    @Order(8)
    public void testUpdateUser(){
        user = userService.getUserByUid(user.getUid());
        user.setPassword("123456");
        int count = userService.modifyUser(user);
        System.out.println("更新用户：" + count);
        assert count == 1;
        System.out.println("更新后的用户信息：" + userService.getUserByUid(user.getUid()));
    }

    @Test
    @Order(3)
    public void testGetUserByUid(){
        System.out.println("Uid：" + user.getUid());
        User userByUid = userService.getUserByUid(user.getUid());
        System.out.println("用户信息：" + userByUid);
        assert userByUid != null;
    }

    @Test
    @Order(4)
    public void testGetUserByEmail(){
        System.out.println("Email：" + user.getEmail());
        User userByEmail = userService.getUserByEmail(user.getEmail());
        System.out.println("用户信息：" + userByEmail);
        assert userByEmail != null;
    }

    @Test
    @Order(5)
    public void testGetUserByPhone(){
        System.out.println("Phone：" + user.getPhone());
        User userByPhone = userService.getUserByPhone(user.getPhone());
        System.out.println("用户信息：" + userByPhone);
        assert userByPhone != null;
    }

}
