package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.pojo.Picture;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PictureMapper {
    /**
     * 新建图片
     *
     * @param picture 图片
     * @return int 影响数量
     */
    @Insert("insert into t_pic(name, data, groupId, createTime, type) values(#{name}, #{data}, #{groupId}, #{createTime}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Picture picture);

    /**
     * 按id删除图片
     *
     * @param id id
     * @return int 影响数量
     */
    @Delete("delete from t_pic where id = #{id}")
    int deleteById(Long id);

    /**
     * 按名字删除图片
     *
     * @param name 名字
     * @return int 影响数量
     */
    @Delete("delete from t_pic where name = #{name}")
    int deleteByName(String name);

    /**
     * 按组id删除图片
     *
     * @param groupId 组id
     * @return int 影响数量
     */
    @Delete("delete from t_pic where groupId = #{groupId}")
    int deleteByGroupId(Long groupId);

//    /**
//     * 更新图片
//     *
//     * @param picture 图片
//     * @return int 影响数量
//     */
//    @Update("update t_pic set name = #{name}, data = #{data}, groupId = #{groupId}, createTime = #{createTime}, type = #{type} where id = #{id}")
//    @SuppressWarnings("notSupported")
//    int update(Picture picture);

    /**
     * 按id查询图片
     *
     * @param id id
     * @return {@link Picture } 图片
     */
    @Select("select * from t_pic where id = #{id}")
    Picture selectById(Long id);

    /**
     * 按名字查询图片
     *
     * @param name 名字
     * @return {@link Picture } 图片
     */
    @Select("select * from t_pic where name = #{name}")
    Picture selectByName(String name);

    /**
     * 按组id查询图片
     *
     * @param groupId 组id
     * @return {@link List }<{@link Picture }> 图片列表
     */
    @Select("select * from t_pic where groupId = #{groupId}")
    List<Picture> selectByGroupId(Long groupId);


}
