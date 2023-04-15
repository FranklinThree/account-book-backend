package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.server.EntryTagEnum;

import java.util.List;

/**
 * 条目标记枚举服务
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className EntryTagService
 * @see
 * @since 1.0.0
 */
public interface EntryTagEnumService {
    /**
     * 保存条目标记
     *
     * @param entryTagEnum 条目标记
     * @return 影响数量
     */
    int saveEntryTagEnum(EntryTagEnum entryTagEnum);

    /**
     * 根据id删除条目标记
     *
     * @param id 条目标记id
     * @return 影响数量
     */
    int removeEntryTagEnumById(Long id);

    /**
     * 根据name删除条目标记
     *
     * @param name 条目标记name
     * @return 影响数量
     */
    int removeEntryTagEnumByName(String name);

    /**
     * 更新条目标记
     * 不推荐使用，由于是基础Enumerate，一旦更新，会导致所有使用该Enumerate的条目标记都会被更新
     *
     * @param entryTagEnum 条目标记
     * @return 影响数量
     */
    @Deprecated
    int modifyEntryTagEnum(EntryTagEnum entryTagEnum);

    /**
     * 根据id查询条目标记
     *
     * @param id 条目标记id
     * @return 条目标记
     */
    EntryTagEnum getEntryTagEnumById(Long id);

    /**
     * 根据name查询条目标记
     *
     * @param name 条目标记name
     * @return 条目标记
     */
    EntryTagEnum getEntryTagEnumByName(String name);

    /**
     * 根据所有者id查询条目标记
     *
     * @param ownerId 所有者id
     * @return {@link List }<{@link EntryTagEnum }> 条目标记列表
     */
    List<EntryTagEnum> getEntryTagEnumsByOwnerId(Long ownerId);

    /**
     * 根据是否为收入查询条目标记
     *
     * @param isIncome 是否为收入
     * @return {@link List }<{@link EntryTagEnum }> 条目标记列表
     */
    List<EntryTagEnum> getEntryTagEnumsByIsIncome(Boolean isIncome);

    /**
     * 查询所有条目标记
     *
     * @return {@link List }<{@link EntryTagEnum }> 条目标记列表
     */
    List<EntryTagEnum> getAllEntryTagEnums();
}
