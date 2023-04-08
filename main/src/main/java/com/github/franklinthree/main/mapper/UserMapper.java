package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * 用户映射器
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className UserMapper
 * @see
 * @since 1.0.0
 */
@Mapper
public interface UserMapper {
    /**
     * 注册用户
     *
     * @param user 用户
     * @return int 影响数量
     */
    @Insert("insert into t_user(uid, real_name, nick_name, email, phone, password) values(#{uid}, #{realName}, #{nickName}, #{email}, #{phone}, #{password})")
    int insert(User user);

    /**
     * 按id删除用户
     *
     * @param id id
     * @return int 影响数量
     */
    @Delete("delete from t_user where id = #{id}")
    int deleteById(Long id);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return int 影响数量
     */
    @Update("update t_user set real_name = #{realName}, nick_name = #{nickName}, email = #{email}, phone = #{phone}, password = #{password} where id = #{id}")
    int update(User user);

    /**
     * 按id查询用户
     *
     * @param id id
     * @return {@link User}
     */
    @Select("select * from t_user where id = #{id}")
    User selectById(Long id);

    /**
     * 按email查询用户
     *
     * @param email email
     * @return {@link User}
     */
    @Select("select * from t_user where email = #{email}")
    User selectByEmail(String email);

    /**
     * 按phone查询用户
     *
     * @param phone phone
     * @return {@link User}
     */
    @Select("select * from t_user where phone = #{phone}")
    User selectByPhone(String phone);

    /**
     * 按uid查询用户
     *
     * @param uid uid
     * @return {@link User }
     */
    @Select("select * from t_user where uid = #{uid}")
    User selectByUid(String uid);

}