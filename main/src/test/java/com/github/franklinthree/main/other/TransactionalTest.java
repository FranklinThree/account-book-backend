package com.github.franklinthree.main.other;

import com.github.franklinthree.main.model.server.User;
import com.github.franklinthree.main.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class TransactionalTest {
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
//        int count = userService.removeUserById(user.getId());
        int count = 0;
        try{
            count = removeUser(user);

        }catch (Exception e){
            e.printStackTrace();
        }
//        被删除的用户信息：User{id=36, uid='FranklinThree123456', realName='FranklinThree', nickName='3', email='222@qq.com', phone='15922222222', password='113322'}
        System.out.println("删除用户：" + count);
        System.out.println("被删除的用户信息：" + user);
//        assert count == 1;

        //说明@Transactional并不能阻止set方法回滚
    }

    @Transactional
    protected int removeUser(User user) throws RuntimeException{
        user.setNickName("3");
        int count = userService.removeUserByUid(user.getUid());
        user.setPassword("113322");
        if (1 == 1){
            throw new RuntimeException("测试事务回滚");
        }
        return count;
    }

}
