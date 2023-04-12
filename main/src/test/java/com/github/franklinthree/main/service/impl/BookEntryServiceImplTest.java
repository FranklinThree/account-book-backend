package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookEntryServiceImplTest {
    @Autowired
    private BookEntryServiceImpl bookEntryService;
    @Autowired
    private PictureFactory pictureFactory;

    @BeforeEach
    void setUp() {
        System.out.println("BookEntryServiceImplTest-----------------------------------------------");
    }
    @Test
    @Order(1)
    void saveBookEntry() {
        Picture picture = pictureFactory.createPicture("123.jpg", new Byte[]{1,2,3},0L);
        Channel channel = new Channel("饿了么外卖凭证", 0L , "饿了么app或饿了么小程序截图得到的外卖小票");
        Set<EntryTag> entryTags = new HashSet<>();
        EntryTagEnum entryTagEnum = new EntryTagEnum("餐饮支出", 0L, "餐饮支出", false);
        EntryTagEnum entryTagEnum2 = new EntryTagEnum("服饰支出", 0L, "服饰支出", false);
        EntryTagEnum entryTagEnum3 = new EntryTagEnum("化妆品支出", 1L, "化妆品支出", false);
        EntryTag entryTag = new EntryTag(entryTagEnum, 0L);
        EntryTag entryTag2 = new EntryTag(entryTagEnum2, 0L);
        EntryTag entryTag3 = new EntryTag(entryTagEnum3, 0L);
        entryTags.add(entryTag);
        entryTags.add(entryTag2);
        entryTags.add(entryTag3);

        BookEntry bookEntry = new BookEntry(picture, channel, 999., "晚饭外卖塔可星", entryTags, 0L);
        int count = bookEntryService.saveBookEntry(bookEntry);
        System.out.println("保存条目：" + count);
        assert count == 1;
    }

    @Test
    void removeBookEntryById() {
    }

    @Test
    void removeBookEntryByGroupId() {
    }

    @Test
    void removeBookEntryByPictureId() {
    }

    @Test
    void removeBookEntryByChannelId() {
    }

    @Test
    void updateBookEntry() {
    }

    @Test
    void getBookEntryById() {
    }

    @Test
    void getBookEntryByPictureId() {
    }

    @Test
    void getBookEntryByGroupId() {
    }
}