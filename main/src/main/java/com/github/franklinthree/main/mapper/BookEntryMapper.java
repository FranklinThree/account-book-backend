package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.model.BookEntry;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账本条目映射器
 *
 * @author FranklinThree
 * @date 2023/04/11
 * @className BookEntryMapper
 * @see
 * @since 1.0.0
 */
@Mapper
public interface BookEntryMapper {
    @Insert("insert into t_bookEntry(pictureId, channelId, money, description, groupId, createTime, updateTime, previousId, nextId) " +
            "values(#{picture.id}, #{channel.id}, #{money}, #{description},  #{groupId}, #{createTime}, #{updateTime}, #{previousId}, #{nextId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(BookEntry bookEntry);

    @Delete("delete from t_bookEntry where id = #{id}")
    int deleteById(Long id);

    @Delete("delete from t_bookEntry where groupId = #{groupId}")
    int deleteByGroupId(Long groupId);

    @Delete("delete from t_bookEntry where pictureId = #{pictureId}")
    int deleteByPictureId(Long pictureId);

    @Delete("delete from t_bookEntry where channelId = #{channelId}")
    int deleteByChannelId(Long channelId);

    @Update("update t_bookEntry set pictureId = #{pictureId}, channelId = #{channelId}, money = #{money}, description = #{description}, groupId = #{groupId}, createTime = #{createTime}, updateTime = #{updateTime}, previousId = #{previousId}, nextId = #{nextId} where id = #{id}")
    int update(BookEntry bookEntry);


    @Results(id = "bookEntryResult", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "picture", column = "pictureId", one = @One(select = "com.github.franklinthree.main.mapper.PictureMapper.selectById")),
            @Result(property = "channel", column = "channelId", one = @One(select = "com.github.franklinthree.main.mapper.ChannelMapper.selectById")),
            @Result(property = "money", column = "money"),
            @Result(property = "description", column = "description"),
            @Result(property = "entryTags", column = "id",
                    many = @Many(select = "com.github.franklinthree.main.mapper.EntryTagMapper.selectByGroupId")),
            @Result(property = "groupId", column = "groupId"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "updateTime", column = "updateTime"),
            @Result(property = "previousId", column = "previousId"),
            @Result(property = "nextId", column = "nextId")
    })
    @Select("select * from t_bookEntry where id = #{id}")
    BookEntry selectById(Long id);

    @ResultMap("bookEntryResult")
    @Select("select * from t_bookEntry where pictureId = #{pictureId}")
    BookEntry selectByPictureId(Long pictureId);

    @ResultMap("bookEntryResult")
    @Select("select * from t_bookEntry where groupId = #{groupId}")
    List<BookEntry> selectByGroupId(Long groupId);




}
