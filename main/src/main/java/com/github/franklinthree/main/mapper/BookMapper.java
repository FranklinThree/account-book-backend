package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.model.server.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 账本映射器
 *
 * @author FranklinThree
 * @date 2023/04/13
 * @className BookMapper
 * @see
 * @since 1.0.0
 */
@Mapper
public interface BookMapper {
    @Insert("INSERT INTO t_book(ownerId, description, createTime, updateTime , title, pictureId) " +
            "VALUES (#{book.ownerId}, #{book.description}, #{createTime}, #{updateTime}, #{book.title},#{book.picture.id})")
    @Options(useGeneratedKeys = true, keyProperty = "book.id", keyColumn = "id")
    int insert(@Param("book") Book book,@Param("createTime") Long createTime,@Param("updateTime") Long updateTime);

    @Delete("DELETE FROM t_book WHERE id = #{id}")
    int delete(Book book);

    @Update("UPDATE t_book SET ownerId = #{book.ownerId}, description = #{book.description}, createTime = #{book.createTime}, updateTime = #{updateTime}, title = #{book.title}, pictureId = #{pictureId} WHERE id = #{book.id}")
    int update(@Param("book") Book book, @Param("updateTime") Long updateTime, @Param("pictureId") Long pictureId);

    @Results(id = "bookResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "ownerId", column = "ownerId"),
            @Result(property = "bookEntries", column = "id", many = @Many(select = "com.github.franklinthree.main.mapper.BookEntryMapper.selectByGroupId")),
            @Result(property = "description", column = "description"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "updateTime", column = "updateTime"),
            @Result(property = "title", column = "title"),
            @Result(property = "picture", column = "pictureId", one = @One(select = "com.github.franklinthree.main.mapper.PictureMapper.selectById"))
    })
    @Select("SELECT * FROM t_book WHERE id = #{id}")
    Book selectById(Long id);

    @ResultMap("bookResult")
    @Select("SELECT * FROM t_book WHERE ownerId = #{ownerId}")
    List<Book> selectByOwnerId(Long ownerId);

}
