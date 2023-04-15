package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.server.EntryTag;
import com.github.franklinthree.main.model.server.EntryTagEnum;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntryTagServiceImplTest {

    @Autowired
    EntryTagServiceImpl entryTagService;

    @Autowired
    EntryTagEnumServiceImpl entryTagEnumService;

    private static final long groupId1 = 10L;
    private static final long groupId2 = 11L;


    @BeforeEach
    void setUp() {
        System.out.println("EntryTagServiceImplTest-------------------------------------------------");
    }

    @Test
    @Order(1)
    void saveEntryTag() {
        EntryTagEnum entryTagEnum = new EntryTagEnum("餐饮支出", 0L, "餐饮支出", false);
        EntryTagEnum entryTagEnum2 = new EntryTagEnum("服饰支出", 0L, "服饰支出", false);
        EntryTagEnum entryTagEnum3 = new EntryTagEnum("化妆品支出", 1L, "化妆品支出", false);
        EntryTagEnum entryTagEnum4 = new EntryTagEnum("交通支出", 1L, "交通支出", false);
        EntryTagEnum entryTagEnum5 = new EntryTagEnum("工作收入", 0L, "工作收入", true);

        // 保存条目标签枚举
        entryTagEnumService.saveEntryTagEnum(entryTagEnum);
        entryTagEnumService.saveEntryTagEnum(entryTagEnum2);
        entryTagEnumService.saveEntryTagEnum(entryTagEnum3);
        entryTagEnumService.saveEntryTagEnum(entryTagEnum4);
        entryTagEnumService.saveEntryTagEnum(entryTagEnum5);

        EntryTag entryTag = new EntryTag(entryTagEnum, groupId1);
        EntryTag entryTag2 = new EntryTag(entryTagEnum2, groupId1);
        EntryTag entryTag3 = new EntryTag(entryTagEnum3,groupId2);
        EntryTag entryTag4 = new EntryTag(entryTagEnum4,groupId2);
        EntryTag entryTag5 = new EntryTag(entryTagEnum5, groupId1);
        int count = entryTagService.saveEntryTag(entryTag);
        System.out.println("保存条目标签1：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTag(entryTag2);
        System.out.println("保存条目标签2：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTag(entryTag3);
        System.out.println("保存条目标签3：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTag(entryTag4);
        System.out.println("保存条目标签4：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTag(entryTag5);
        System.out.println("保存条目标签5：" + count);
        assert count == 1;
    }

    @Test
    @Order(5)
    void removeEntryTagById() {
        List<EntryTag> entryTagsByGroupId = entryTagService.getEntryTagsByGroupId(groupId1);
        EntryTag entryTag = entryTagsByGroupId.get(0);
        Long id = entryTag.getId();
        int count = entryTagService.removeEntryTagById(id);
        System.out.println("删除id为" + id + "的条目标签：" + count);
        assert count == 1;

    }

//    @Test
//    @Order(6)
//
//    void removeEntryTagByEntryEnumId() {
//        List<EntryTag> entryTagsByGroupId = entryTagService.getEntryTagsByGroupId(groupId1);
//        EntryTag entryTag = entryTagsByGroupId.get(0);
//        long entryEnumId = entryTag.getEntryTagEnum().getId();
//        int count = entryTagService.removeEntryTagByEntryEnumId(entryEnumId);
//        System.out.println("删除entryEnumId为" + entryEnumId + "的条目标签：" + count);
//        assert count == 1;
//    }

    @Test
    @Order(7)
    void removeEntryTagByGroupId() {
        int count = entryTagService.removeEntryTagByGroupId(groupId1);
        System.out.println("删除组id为" + groupId1 + "的条目标签：" + count);
//        assert count > 0;

        count = entryTagService.removeEntryTagByGroupId(groupId2);
        System.out.println("删除组id为" + groupId2 + "的条目标签：" + count);
        assert count > 0;


        EntryTagEnum entryTagEnum = new EntryTagEnum("餐饮支出", 0L, "餐饮支出", false);
        EntryTagEnum entryTagEnum2 = new EntryTagEnum("服饰支出", 0L, "服饰支出", false);
        EntryTagEnum entryTagEnum3 = new EntryTagEnum("化妆品支出", 1L, "化妆品支出", false);
        EntryTagEnum entryTagEnum4 = new EntryTagEnum("交通支出", 1L, "交通支出", false);
        EntryTagEnum entryTagEnum5 = new EntryTagEnum("工作收入", 0L, "工作收入", true);

        // 删除条目标签枚举
        entryTagEnumService.removeEntryTagEnumByName(entryTagEnum.getName());
        entryTagEnumService.removeEntryTagEnumByName(entryTagEnum2.getName());
        entryTagEnumService.removeEntryTagEnumByName(entryTagEnum3.getName());
        entryTagEnumService.removeEntryTagEnumByName(entryTagEnum4.getName());
        entryTagEnumService.removeEntryTagEnumByName(entryTagEnum5.getName());

    }

//    @Test
//    @Order(4)
//    void modifyEntryTag() {
//        List<EntryTag> entryTagsByGroupId = entryTagService.getEntryTagsByGroupId(groupId1);
//        EntryTag entryTag = entryTagsByGroupId.get(0);
//        entryTag.setGroupId(groupId2);
//        int count = entryTagService.modifyEntryTag(entryTag);
//        System.out.println("修改条目标签：" + count);
//        assert count == 1;
//    }

    @Test
    @Order(3)
    void getEntryTagById() {
        List<EntryTag> entryTagsByGroupId = entryTagService.getEntryTagsByGroupId(groupId1);
        EntryTag entryTag = entryTagsByGroupId.get(0);
        Long id = entryTag.getId();
        EntryTag entryTagById = entryTagService.getEntryTagById(id);
        System.out.println("id为"+ id + "的条目标签是" + entryTagById);
        assert entryTagById.getEntryTagEnum() != null;

    }

    @Test
    @Order(2)
    void getEntryTagsByGroupId() {
        List<EntryTag> entryTagsByGroupId = entryTagService.getEntryTagsByGroupId(groupId1);
        System.out.println("根据组id获取条目标签：" + entryTagsByGroupId);
        assert entryTagsByGroupId.size() == 3;
    }
}