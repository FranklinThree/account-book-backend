package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.server.User;

public interface UserService {


    /**
     * 保存用户
     *
     * @param user 用户
     * @return int 影响数量
     */
    int saveUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id id
     * @return int 影响数量
     */
    int removeUserById(Long id);

    /**
     * 根据uid删除用户
     *
     * @param uid uid 用户的uid
     * @return int 影响数量
     */
    int removeUserByUid(String uid);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return int 影响数量
     */
    int modifyUser(User user);


    /**
     * 根据email获取用户
     *
     * @param email 电子邮件
     * @return {@link User } 用户
     */
    User getUserByEmail(String email);

    /**
     * 根据phone获取用户
     *
     * @param phone 电话
     * @return {@link User } 用户
     */
    User getUserByPhone(String phone);

    /**
     * 根据uid获取用户
     *
     * @param uid 用户的uid
     * @return {@link User} 用户
     */
    User getUserByUid(String uid);


}
