package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.UserMapper;
import com.github.franklinthree.main.model.User;
import com.github.franklinthree.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int removeUserById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int modifyUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public User getUserByUid(String uid) {
        return userMapper.selectByUid(uid);
    }
}
