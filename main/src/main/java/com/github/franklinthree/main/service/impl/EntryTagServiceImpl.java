package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.EntryTagEnumMapper;
import com.github.franklinthree.main.mapper.EntryTagMapper;
import com.github.franklinthree.main.model.EntryTag;
import com.github.franklinthree.main.model.EntryTagEnum;
import com.github.franklinthree.main.service.EntryTagEnumService;
import com.github.franklinthree.main.service.EntryTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 条目标记服务实现类
 *
 * @author FranklinThree
 * @date 2023/04/12
 * @className EntryTagServiceImpl
 * @see @see EntryTagService
 * @since 1.0.0
 */
@Service("entryTagService")
public class EntryTagServiceImpl implements EntryTagService {

    @Autowired
    EntryTagMapper entryTagMapper;

    @Autowired
    EntryTagEnumService entryTagEnumService;
    @Override
    @Transactional
    public int saveEntryTag(EntryTag entryTag) throws RuntimeException{
        // 检查EntryTagEnum是否存在
        EntryTagEnum entryTagEnum = entryTag.getEntryTagEnum();
        String entryTagEnumName = entryTagEnum.getName();
        if (entryTagEnumService.getEntryTagEnumByName(entryTagEnumName) == null) {
            // 如果entryTagEnum不存在，报错
            throw new RuntimeException("不存在的EntryTagEnum" + entryTagEnum);
        }
        return entryTagMapper.insert(entryTag);
    }

    @Override
    public int removeEntryTagById(Long id) {
        return entryTagMapper.deleteById(id);
    }

//    @Override
//    public int removeEntryTagByEntryEnumId(Long entryEnumId) {
//        return entryTagMapper.deleteByEntryTagEnumId(entryEnumId);
//    }

    @Override
    public int removeEntryTagByGroupId(Long groupId) {
        return entryTagMapper.deleteByGroupId(groupId);
    }

//    @Override
//    public int modifyEntryTag(EntryTag entryTag) {
////        EntryTagEnum entryTagEnumInput = entryTag.getEntryTagEnum();
////        String name = entryTagEnumInput.getName();
////        EntryTagEnum entryTagEnum = entryTagEnumMapper.selectByName(name);
////
////        // 如果entryTagEnum不存在，插入
////        if (entryTagEnum == null) {
////            entryTagEnumMapper.insert(entryTagEnumInput);
////            entryTagEnum = entryTagEnumInput;
////        }
//        EntryTagEnum entryTagEnum = entryTag.getEntryTagEnum();
//        return entryTagMapper.(entryTagEnum.getId());
//    }

    @Override
    public EntryTag getEntryTagById(Long id) {
        return entryTagMapper.selectById(id);
    }

    @Override
    public List<EntryTag> getEntryTagsByGroupId(Long groupId) {
        return entryTagMapper.selectByGroupId(groupId);
    }
}
