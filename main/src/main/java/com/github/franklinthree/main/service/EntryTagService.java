package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.EntryTag;

import java.util.List;

/**
 * 条目标记服务
 *
 * @author FranklinThree
 * @date 2023/04/12
 * @className EntryTagService
 * @see
 * @since 1.0.0
 */
public interface EntryTagService {
    /**
     * 保存条目标记
     * 在保存条目标记时，应当确保EntryTagEnum已经插入到数据库中
     *
     * @param entryTag 条目标记
     * @return int 影响数量
     */
    int saveEntryTag(EntryTag entryTag);

    /**
     * 按id删除条目标记
     *
     * @param id id
     * @return int 影响数量
     */
    int removeEntryTagById(Long id);

//    /**
//     * 按条目枚举id删除条目标记
//     *
//     * @param entryEnumId 条目枚举id
//     * @return int 影响数量
//     */
//    int removeEntryTagByEntryEnumId(Long entryEnumId);

    /**
     * 按组id删除条目标记
     *
     * @param groupId 条目标记组id
     * @return int 影响数量
     */
    int removeEntryTagByGroupId(Long groupId);

//    /**
//     * 修改条目标记
//     *
//     * @param entryTag 条目标记
//     * @return int 影响数量
//     */
//    int modifyEntryTag(EntryTag entryTag);


    /**
     * 根据id查询条目标记
     *
     * @param id id
     * @return {@link EntryTag } 条目标记
     */
    EntryTag getEntryTagById(Long id);

    /**
     * 根据组id查询条目标记
     *
     * @param groupId 组id
     * @return {@link List }<{@link EntryTag }> 条目标记列表
     */
    List<EntryTag> getEntryTagsByGroupId(Long groupId);

}
