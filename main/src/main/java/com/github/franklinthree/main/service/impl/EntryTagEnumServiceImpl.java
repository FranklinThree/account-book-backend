package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.EntryTagEnumMapper;
import com.github.franklinthree.main.model.EntryTagEnum;
import com.github.franklinthree.main.service.EntryTagEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 条目标签服务实现类
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className EntryTagServiceImpl
 * @see @see EntryTagService
 * @since 1.0.0
 */
@Service("entryTagEnumService")
public class EntryTagEnumServiceImpl implements EntryTagEnumService {

    @Autowired
    private EntryTagEnumMapper entryTagEnumMapper;

    @Override
    public int saveEntryTagEnum(EntryTagEnum entryTagEnum) {
        return entryTagEnumMapper.insert(entryTagEnum);
    }

    @Override
    public int removeEntryTagEnumById(Long id) {
        return entryTagEnumMapper.deleteById(id);
    }

    @Override
    public int removeEntryTagEnumByName(String name) {
        return entryTagEnumMapper.deleteByName(name);
    }

    @Override
    public int modifyEntryTagEnum(EntryTagEnum entryTagEnum) {
        return entryTagEnumMapper.update(entryTagEnum);
    }

    @Override
    public EntryTagEnum getEntryTagEnumById(Long id) {
        return entryTagEnumMapper.selectById(id);
    }

    @Override
    public EntryTagEnum getEntryTagEnumByName(String name) {
        return entryTagEnumMapper.selectByName(name);
    }

    @Override
    public List<EntryTagEnum> getEntryTagEnumsByOwnerId(Long ownerId) {
        return entryTagEnumMapper.selectByOwnerId(ownerId);
    }

    @Override
    public List<EntryTagEnum> getEntryTagEnumsByIsIncome(Boolean isIncome) {
        return entryTagEnumMapper.selectByIsIncome(isIncome);
    }

    @Override
    public List<EntryTagEnum> getAllEntryTagEnums() {
        return entryTagEnumMapper.selectAll();
    }
}
