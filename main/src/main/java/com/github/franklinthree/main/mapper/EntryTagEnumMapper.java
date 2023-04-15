package com.github.franklinthree.main.mapper;

import com.github.franklinthree.main.model.server.EntryTagEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 条目标记映射器
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className EntryTagMapper
 * @see
 * @since 1.0.0
 */
@Mapper
public interface EntryTagEnumMapper {
    @Insert("insert into te_entryTag(name, ownerId, description, isIncome) values(#{name}, #{ownerId}, #{description}, #{isIncome})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(EntryTagEnum entryTagEnum);

    @Delete("delete from te_entryTag where id = #{id}")
    int deleteById(Long id);

    @Delete("delete from te_entryTag where name = #{name}")
    int deleteByName(String name);

    @Update("update te_entryTag set name = #{name}, ownerId = #{ownerId}, description = #{description}, isIncome = #{isIncome} where id = #{id}")
    int update(EntryTagEnum entryTagEnum);

    @Select("select * from te_entryTag where id = #{id}")
    EntryTagEnum selectById(Long id);

    @Select("select * from te_entryTag where name = #{name}")
    EntryTagEnum selectByName(String name);

    @Select("select * from te_entryTag where ownerId = #{ownerId}")
    List<EntryTagEnum> selectByOwnerId(Long ownerId);

    @Select("select * from te_entryTag where isIncome = #{isIncome}")
    List<EntryTagEnum> selectByIsIncome(Boolean isIncome);

    @Select("select * from te_entryTag")
    List<EntryTagEnum> selectAll();

}
