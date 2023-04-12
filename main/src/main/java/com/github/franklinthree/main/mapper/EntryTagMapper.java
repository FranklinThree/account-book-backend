package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.model.EntryTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 条目标记映射器
 *
 * @author FranklinThree
 * @date 2023/04/12
 * @className EntryTagMapper
 * @see
 * @since 1.0.0
 */
@Mapper
public interface EntryTagMapper {

    @Insert("INSERT INTO tg_entryTag(entryTagEnumId, groupId) VALUES (#{entryTagEnum.id}, #{groupId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(EntryTag entryTag);

    @Delete("DELETE FROM tg_entryTag WHERE id = #{id}")
    int deleteById(Long id);

    @Delete("DELETE FROM tg_entryTag WHERE groupId = #{groupId}")
    int deleteByGroupId(Long groupId);

    @Delete("DELETE FROM tg_entryTag WHERE entryTagEnumId = #{entryTagEnumId}")
    int deleteByEntryTagEnumId(Long entryTagEnumId);
    @Results(id = "EntryTagResult", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "entryTagEnum", column = "entryTagEnumId", one = @One(select = "com.github.franklinthree.main.mapper.EntryTagEnumMapper.selectById")),
            @Result(property = "groupId", column = "groupId")
    })
    @Select("SELECT * FROM tg_entryTag WHERE id = #{id}")
    EntryTag selectById(Long id);

    @ResultMap("EntryTagResult")
    @Select("SELECT * FROM tg_entryTag WHERE groupId = #{groupId}")
    List<EntryTag> selectByGroupId(Long groupId);



}
