package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.model.server.BookEntry;
import org.apache.ibatis.annotations.*;

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
            "values(#{bookEntry.picture.id}, #{bookEntry.channel.id}, #{bookEntry.money}, #{bookEntry.description},  #{bookEntry.groupId}, #{createTime}, #{updateTime}, #{bookEntry.previousId}, #{bookEntry.nextId})")
    @Options(useGeneratedKeys = true, keyProperty = "bookEntry.id", keyColumn = "id")
    int insert(@Param("bookEntry") BookEntry bookEntry,@Param("createTime") Long createTime, @Param("updateTime") Long updateTime);

    @Delete("delete from t_bookEntry where id = #{id}")
    int deleteById(Long id);

    @Delete("delete from t_bookEntry where groupId = #{groupId}")
    int deleteByGroupId(Long groupId);

//    @Delete("delete from t_bookEntry where pictureId = #{pictureId}")
//    int deleteByPictureId(Long pictureId);

//    @Delete("delete from t_bookEntry where channelId = #{channelId}")
//    int deleteByChannelId(Long channelId);

    @Update("update t_bookEntry set pictureId = #{bookEntry.picture.id}, channelId = #{bookEntry.channel.id}, money = #{bookEntry.money}, description = #{bookEntry.description}, groupId = #{bookEntry.groupId}, createTime = #{bookEntry.createTime}, updateTime = #{updateTime}, previousId = #{bookEntry.previousId}, nextId = #{bookEntry.nextId} where id = #{bookEntry.id}")
    int update(@Param("bookEntry") BookEntry bookEntry, @Param("updateTime") Long updateTime);

    @Update("update t_bookEntry set channelId = #{bookEntry.channel.id}, money = #{bookEntry.money}, description = #{bookEntry.description}, groupId = #{bookEntry.groupId}, createTime = #{bookEntry.createTime}, updateTime = #{updateTime}, previousId = #{bookEntry.previousId}, nextId = #{bookEntry.nextId} where id = #{bookEntry.id}")
    int updateIgnorePictureId(@Param("bookEntry") BookEntry bookEntry, @Param("updateTime") Long updateTime);

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

//    @ResultMap("bookEntryResult")
//    @Select("select * from t_bookEntry where pictureId = #{pictureId}")
//    BookEntry selectByPictureId(Long pictureId);

    @ResultMap("bookEntryResult")
    @Select("select * from t_bookEntry where groupId = #{groupId}")
    List<BookEntry> selectByGroupId(Long groupId);

}
