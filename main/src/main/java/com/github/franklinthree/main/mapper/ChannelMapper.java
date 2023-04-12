package com.github.franklinthree.main.mapper;


import com.github.franklinthree.main.model.Channel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 通道映射器
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className ChannelMapper
 * @see
 * @since 1.0.0
 */
@Mapper
public interface ChannelMapper {
    @Insert("insert into te_channel(name, ownerId, description) values(#{name}, #{ownerId}, #{description})")
    int insert(Channel channel);

    @Delete("delete from te_channel where id = #{id}")
    int deleteById(Long id);

    @Delete("delete from te_channel where name = #{name}")
    int deleteByName(String name);

    @Update("update te_channel set name = #{name}, ownerId = #{ownerId}, description = #{description} where id = #{id}")
    int update(Channel channel);

    @Select("select * from te_channel where id = #{id}")
    Channel selectById(Long id);

    @Select("select * from te_channel where name = #{name}")
    Channel selectByName(String name);

    @Select("select * from te_channel where ownerId = #{ownerId}")
    List<Channel> selectByOwnerId(Long ownerId);

    @Select("select * from te_channel")
    List<Channel> selectAll();

}
