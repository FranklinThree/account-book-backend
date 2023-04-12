package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.EntryTagEnum;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class EntryTagEnumServiceImplTest {

    @Autowired
    private EntryTagEnumServiceImpl entryTagService;

    @BeforeEach
    void setUp() {
        System.out.println("EntryTagEnumServiceImplTest--------------------------------------------------------------");
    }

    @Test
    @Order(1)
    void saveEntryTagEnum() {
        EntryTagEnum entryTagEnum = new EntryTagEnum("餐饮支出", 0L, "餐饮支出", false);
        EntryTagEnum entryTagEnum2 = new EntryTagEnum("服饰支出", 0L, "服饰支出", false);
        EntryTagEnum entryTagEnum3 = new EntryTagEnum("化妆品支出", 1L, "化妆品支出", false);
        EntryTagEnum entryTagEnum4 = new EntryTagEnum("交通支出", 1L, "交通支出", false);
        EntryTagEnum entryTagEnum5 = new EntryTagEnum("工作收入", 0L, "工作收入", true);


        int count = entryTagService.saveEntryTagEnum(entryTagEnum);
        System.out.println("保存条目标签枚举1：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTagEnum(entryTagEnum2);
        System.out.println("保存条目标签枚举2：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTagEnum(entryTagEnum3);
        System.out.println("保存条目标签枚举3：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTagEnum(entryTagEnum4);
        System.out.println("保存条目标签枚举4：" + count);
        assert count == 1;

        count = entryTagService.saveEntryTagEnum(entryTagEnum5);
        System.out.println("保存条目标签枚举5：" + count);
        assert count == 1;

    }

    @Test
    @Order(5)
    void removeEntryTagEnumById() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        long id = allEntryTagEnums.get(0).getId();
        int count = entryTagService.removeEntryTagEnumById(id);
        System.out.println("删除id为" + id + "的条目标签枚举：" + count);
        assert count == 1;

    }

    @Test
    @Order(6)
    void removeEntryTagEnumByName() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        for (EntryTagEnum entryTagEnum : allEntryTagEnums) {
            String name = entryTagEnum.getName();
            int count = entryTagService.removeEntryTagEnumByName(name);
            System.out.println("删除name为" + name + "的条目标签枚举：" + count);
            assert count == 1;

        }
    }

    @Test
    @Order(3)
    void modifyEntryTagEnum() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        EntryTagEnum entryTagEnum = allEntryTagEnums.get(0);
        entryTagEnum.setDescription("餐饮支出，没什么特别的。人是铁饭是钢，一顿不吃饿得慌");
        int count = entryTagService.modifyEntryTagEnum(entryTagEnum);
        System.out.println("修改条目标签枚举：" + count);
        assert count == 1;

    }

    @Test
    @Order(4)
    void getEntryTagEnumById() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        long id = allEntryTagEnums.get(0).getId();
        EntryTagEnum entryTagEnumById = entryTagService.getEntryTagEnumById(id);
        System.out.println("id为" + id + "的条目标签枚举是：" + entryTagEnumById);
        assert entryTagEnumById.getName() != null;

    }

    @Test
    @Order(2)
    void getEntryTagEnumByName() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        String name = allEntryTagEnums.get(0).getName();
        EntryTagEnum entryTagEnumByName = entryTagService.getEntryTagEnumByName(name);
        System.out.println("name为"+ name + "的条目标签枚举是：" + entryTagEnumByName);
        assert entryTagEnumByName.getId() > 0;

    }

    @Test
    @Order(2)
    void getEntryTagsByOwnerId() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        long ownerId = allEntryTagEnums.get(0).getOwnerId();
        List<EntryTagEnum> entryTagsByOwnerIdEnum = entryTagService.getEntryTagEnumsByOwnerId(ownerId);
        System.out.println("ownerId为" + ownerId + "的条目标签枚举有：");
        for (EntryTagEnum entryTagEnum : entryTagsByOwnerIdEnum) {
            assert entryTagEnum.getName() != null;
            System.out.println(entryTagEnum);
        }
    }

    @Test
    @Order(2)
    void getEntryTagsByIsIncome() {
        boolean isIncome = false;
        List<EntryTagEnum> entryTagsByIsIncomeEnum = entryTagService.getEntryTagEnumsByIsIncome(isIncome);
        System.out.println("isIncome为" + isIncome + "的条目标签枚举有：");
        for (EntryTagEnum entryTagEnum : entryTagsByIsIncomeEnum) {
            assert entryTagEnum.getName() != null;
            System.out.println(entryTagEnum);
        }


    }

    @Test
    @Order(2)
    void getAllEntryTags() {
        List<EntryTagEnum> allEntryTagEnums = entryTagService.getAllEntryTagEnums();
        System.out.println("所有条目标签枚举有：");
        for (EntryTagEnum entryTagEnum : allEntryTagEnums) {
            assert entryTagEnum.getName() != null;
            System.out.println(entryTagEnum);
        }
    }


}