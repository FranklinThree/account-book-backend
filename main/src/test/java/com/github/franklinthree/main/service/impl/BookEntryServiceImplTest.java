package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.server.*;
import com.github.franklinthree.main.service.BookEntryService;
import com.github.franklinthree.main.service.ChannelService;
import com.github.franklinthree.main.service.EntryTagEnumService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class BookEntryServiceImplTest {
    @Autowired
    private BookEntryService bookEntryService;
    @Autowired
    private EntryTagEnumService entryTagEnumService;

    @Autowired
    private ChannelService channelService;
    @Autowired
    private PictureFactory pictureFactory;

    public static final long groupId = 111L;
    public static final long groupId2 = 222L;


    @BeforeEach
    void setUp() {
        System.out.println("BookEntryServiceImplTest-----------------------------------------------");
    }
    @Test
    @Order(1)
//    @Transactional
    public void saveBookEntry() {

        // 初始化子数据

        Picture picture = pictureFactory.createPicture("123.jpg", new Byte[]{1,2,3},0L);
        Channel channel = new Channel("饿了么外卖凭证", 0L , "饿了么app或饿了么小程序截图得到的外卖小票");
        Channel channel2 = new Channel("京东商城凭证", 0L , "京东商城app或京东小程序截图得到的购物小票");
        Channel channel3 = new Channel("淘宝商城凭证", 0L , "淘宝商城app或淘宝小程序截图得到的购物小票");

        // 插入channel数据
        channelService.saveChannel(channel);
        channelService.saveChannel(channel2);
        channelService.saveChannel(channel3);

        Set<EntryTag> entryTags = new HashSet<>();
        Set<EntryTag> entryTags2 = new HashSet<>();
        Set<EntryTag> entryTags3 = new HashSet<>();

        EntryTagEnum entryTagEnum = new EntryTagEnum("餐饮支出", 0L, "餐饮支出", false);
        EntryTagEnum entryTagEnum2 = new EntryTagEnum("服饰支出", 0L, "服饰支出", false);
        EntryTagEnum entryTagEnum3 = new EntryTagEnum("化妆品支出", 1L, "化妆品支出", false);

        // 插入entryTagEnum数据
        entryTagEnumService.saveEntryTagEnum(entryTagEnum);
        entryTagEnumService.saveEntryTagEnum(entryTagEnum2);
        entryTagEnumService.saveEntryTagEnum(entryTagEnum3);

        EntryTag entryTag = new EntryTag(entryTagEnum, 0L);
        EntryTag entryTag2 = new EntryTag(entryTagEnum2, 0L);
        EntryTag entryTag3 = new EntryTag(entryTagEnum3, 0L);
        entryTags.add(entryTag);
        entryTags2.add(entryTag2);
        entryTags3.add(entryTag3);


        BookEntry bookEntry = new BookEntry(picture, channel, 999., "晚饭外卖塔可星", entryTags, groupId);
        BookEntry bookEntry2 = new BookEntry(picture, channel2, 599., "买了一双耐克鞋", entryTags2, groupId);
        BookEntry bookEntry3 = new BookEntry(picture, channel3, 299., "买了一瓶香水", entryTags3, groupId2);
        BookEntry bookEntry4 = new BookEntry(picture, channel, 19.9, "KFC", null,groupId);
        BookEntry bookEntry5 = new BookEntry(picture, channel, 19.9, "KFC", new HashSet<>(),groupId);
        BookEntry bookEntry6 = new BookEntry(picture, channel, 19.9, "KFC", new HashSet<>(),groupId);


        int count = bookEntryService.saveBookEntry(bookEntry);
        System.out.println("保存条目：" + count);
        assert count == 1;

        count = bookEntryService.saveBookEntry(bookEntry2);
        System.out.println("保存条目：" + count);
        assert count == 1;

        count = bookEntryService.saveBookEntry(bookEntry3);
        System.out.println("保存条目：" + count);
        assert count == 1;

        count = bookEntryService.saveBookEntry(bookEntry4);
        System.out.println("保存条目：" + count);
        assert count == 1;

        count = bookEntryService.saveBookEntry(bookEntry5);
        System.out.println("保存条目：" + count);
        assert count == 1;

        count = bookEntryService.saveBookEntry(bookEntry6);
        System.out.println("保存条目：" + count);
        assert count == 1;

//        System.out.println(bookEntry);

    }


    @Test
    @Order(11)
//    @Transactional
    public void removeBookEntryById() {
        List<BookEntry> bookEntryByGroupId = bookEntryService.getBookEntryByGroupId(groupId);
        Long id = bookEntryByGroupId.get(0).getId();
        int count = bookEntryService.removeBookEntryById(id);
        System.out.println("删除条目：" + count);
        assert count == 1;
    }

    @Test
    @Order(12)
//    @Transactional
    public void removeBookEntryByGroupId() {
        int count = bookEntryService.removeBookEntryByGroupId(groupId);
        System.out.println("删除条目：" + count);
//        assert count > 0;

        count = bookEntryService.removeBookEntryByGroupId(groupId2);
        System.out.println("删除条目：" + count);
//        assert count > 0;

        List<Channel> allChannels = channelService.getAllChannels();
        ListIterator<Channel> iterator = allChannels.listIterator();

        synchronized (allChannels){
            while (iterator.hasNext()) {
                Channel next = iterator.next();
                channelService.removeChannelByName(next.getName());
            }
        }

        assert channelService.getAllChannels().isEmpty();

        List<EntryTagEnum> allEntryTagEnums = entryTagEnumService.getAllEntryTagEnums();
        for (EntryTagEnum anEnum : allEntryTagEnums) {
            entryTagEnumService.removeEntryTagEnumByName(anEnum.getName());
        }
        assert entryTagEnumService.getAllEntryTagEnums().isEmpty();
    }
//
//    @Test
//    void removeBookEntryByPictureId() {
//    }

//    @Test
//    void removeBookEntryByChannelId() {
//
//    }

    @Test
    @Order(10)
//    @Transactional
    public void updateBookEntryIgnoreEntryTags() {
        List<BookEntry> bookEntryByGroupId = bookEntryService.getBookEntryByGroupId(groupId);
        Long id = bookEntryByGroupId.get(0).getId();
        BookEntry bookEntryById = bookEntryService.getBookEntryById(id);
        bookEntryById.setDescription(bookEntryById.getDescription() + "---更新后的描述");
        Set<EntryTag> entryTags = new HashSet<>();
        EntryTagEnum entryTagEnum = new EntryTagEnum("交通支出", 0L, "交通支出", false);
//        entryTagEnumService.saveEntryTagEnum(entryTagEnum);
        bookEntryById.addEntryTagEnum(entryTagEnum);
        int count = bookEntryService.updateBookEntryInfo(bookEntryById);
        System.out.println("更新条目：" + count);
        assert count == 1;
    }
//    @Test
//    @Order(5)
////    @Transactional
//    public void updateBookEntry() {
//        List<BookEntry> bookEntryByGroupId = bookEntryService.getBookEntryByGroupId(groupId);
//        Long id = bookEntryByGroupId.get(0).getId();
//        BookEntry bookEntryById = bookEntryService.getBookEntryById(id);
//
//        Set<EntryTag> entryTags = new HashSet<>();
//        EntryTagEnum entryTagEnum = new EntryTagEnum("交通支出", 0L, "交通支出", false);
//        entryTagEnumService.saveEntryTagEnum(entryTagEnum);
//        bookEntryById.addEntryTagEnum(entryTagEnum);
//        int count = bookEntryService.updateBookEntry(bookEntryById);
//        System.out.println("更新条目：" + count);
//        assert count == 1;
//    }

    @Test
    @Order(6)
    public void addEntryTag(){}


    @Test
    @Order(4)
    public void getBookEntryById() {
        List<BookEntry> bookEntryByGroupId = bookEntryService.getBookEntryByGroupId(groupId);
        Long id = bookEntryByGroupId.get(0).getId();
        BookEntry bookEntryById = bookEntryService.getBookEntryById(id);
        System.out.println("id为" + id + "的条目为：" + bookEntryById);
        assert bookEntryById != null;
    }

//    @Test
//    @Order(3)
//    void getBookEntryByPictureId() {
//
//    }

    @Test
    @Order(2)
    public void getBookEntryByGroupId() {
        List<BookEntry> bookEntryByGroupId = bookEntryService.getBookEntryByGroupId(groupId);
        System.out.println("groupId为" + groupId + "的条目有：");
        for (BookEntry bookEntry : bookEntryByGroupId) {
            assert bookEntry != null;
            System.out.println(bookEntry);
        }
    }
}