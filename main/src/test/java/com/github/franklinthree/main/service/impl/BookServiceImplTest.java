package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.server.*;
import com.github.franklinthree.main.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookEntryService bookEntryService;

    @Autowired
    private EntryTagEnumService entryTagEnumService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureFactory pictureFactory;
    private static final long groupId = -1L;
    private static final long ownerId = 1L;


    //    public static final long groupId = 111L;
//    public static final long groupId2 = 222L;
    @BeforeEach
    void setUp() {
        System.out.println("BookServiceImplTest------------------------------------------");
    }

    @Test
    @Order(1)
//    @Transactional
    void saveBook() {

        // 初始化子数据

        Picture picture = pictureFactory.createPicture("123.jpg", new Byte[]{1, 2, 3}, 0L);
        Channel channel = new Channel("饿了么外卖凭证", 0L, "饿了么app或饿了么小程序截图得到的外卖小票");
        Channel channel2 = new Channel("京东商城凭证", 0L, "京东商城app或京东小程序截图得到的购物小票");
        Channel channel3 = new Channel("淘宝商城凭证", 0L, "淘宝商城app或淘宝小程序截图得到的购物小票");

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


        BookEntry bookEntry = new BookEntry(picture, channel, 999., "晚饭外卖塔可星", entryTags);
        BookEntry bookEntry2 = new BookEntry(picture, channel2, 599., "买了一双耐克鞋", entryTags2);
        BookEntry bookEntry4 = new BookEntry(picture, channel, 19.9, "KFC", null);
        BookEntry bookEntry5 = new BookEntry(picture, channel, 19.9, "KFC", new HashSet<>());
        BookEntry bookEntry6 = new BookEntry(picture, channel, 19.9, "KFC", new HashSet<>());


        BookEntry bookEntry3 = new BookEntry(picture, channel3, 299., "买了一瓶香水", entryTags3, groupId);

        List<BookEntry> bookEntryList = new ArrayList<>();
        bookEntryList.add(bookEntry);
        bookEntryList.add(bookEntry2);
//        bookEntryList.add(bookEntry3);
        bookEntryList.add(bookEntry4);
        bookEntryList.add(bookEntry5);
        bookEntryList.add(bookEntry6);


        Book book = new Book(ownerId, bookEntryList, "我的账本", "默认账本", picture);
        int count = bookService.saveBook(book);
        System.out.println("插入账本：" + count);

        assert count == 1;
    }

    @Test
    @Order(6)
//    @Transactional
    void removeBook() {
        List<Book> bookByOwnerId = bookService.getBookByOwnerId(ownerId);
        Book book = bookByOwnerId.get(0);
        int count = bookService.removeBook(book);
        System.out.println("删除账本：" + count);
        assert count == 1;


        int pictureByGroupId = pictureService.removePictureByGroupId(0L);

        List<Channel> allChannels = channelService.getAllChannels();

        for (Channel channel : allChannels) {
            channelService.removeChannelById(channel.getId());
        }

        List<EntryTagEnum> allEntryTagEnums = entryTagEnumService.getAllEntryTagEnums();
        for (EntryTagEnum entryTagEnum : allEntryTagEnums) {
            entryTagEnumService.removeEntryTagEnumById(entryTagEnum.getId());
        }

    }

    @Test
    @Order(5)
    void updateBookInfo() {
        List<Book> bookByOwnerId = bookService.getBookByOwnerId(ownerId);
        Book book = bookByOwnerId.get(0);
        book.setDescription("我的账本2333");
        book.setTitle("默认账本2333");
        int count = bookService.updateBookInfo(book);
        System.out.println("更新账本：" + count);
        assert count == 1;
    }


    @Test
    @Order(4)
    void updateBookPicture() {
        List<Book> bookByOwnerId = bookService.getBookByOwnerId(ownerId);
        Book book = bookByOwnerId.get(0);
        Picture picture = pictureFactory.createPicture("1236666.jpg", new Byte[]{1, 2, 3, 4, 6, 7, 8, 9, 111}, 0L);
        int count = bookService.updateBookPicture(book, picture);
        System.out.println("更新账本：" + count);
        assert count == 1;
    }


    @Test
    @Order(3)
    void getBookById() {
        List<Book> bookByOwnerId = bookService.getBookByOwnerId(ownerId);
        Long id = bookByOwnerId.get(0).getId();
        Book bookById = bookService.getBookById(id);
        System.out.println(bookById);
        assert bookById != null;
    }


    @Test
    @Order(2)
    void getBookByOwnerId() {
        List<Book> bookByOwnerId = bookService.getBookByOwnerId(ownerId);
        for (Book book : bookByOwnerId) {
            System.out.println(book);
            assert book != null;
        }
    }
}