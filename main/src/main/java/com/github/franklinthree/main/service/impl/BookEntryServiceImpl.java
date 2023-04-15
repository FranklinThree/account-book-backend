package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.BookEntryMapper;
import com.github.franklinthree.main.model.server.BookEntry;
import com.github.franklinthree.main.model.server.Channel;
import com.github.franklinthree.main.model.server.EntryTag;
import com.github.franklinthree.main.model.server.Picture;
import com.github.franklinthree.main.service.BookEntryService;
import com.github.franklinthree.main.service.ChannelService;
import com.github.franklinthree.main.service.EntryTagService;
import com.github.franklinthree.main.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 账本条目服务实现类
 *
 * @author FranklinThree
 * @date 2023/04/11
 * @className BookEntryServiceImpl
 * @see @see BookEntryService
 * @since 1.0.0
 */
@Service("bookEntryService")
public class BookEntryServiceImpl implements BookEntryService {

    @Autowired
    private BookEntryMapper bookEntryMapper;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private EntryTagService entryTagService;
    @Override
    @Transactional
    public int saveBookEntry(BookEntry bookEntry) throws RuntimeException {
        // 保存图片
        Picture picture = bookEntry.getPicture();
        picture.setId(null);
        pictureService.savePicture(picture);

        // 检查渠道是否存在
        List<Channel> allChannels = channelService.getAllChannels();
        boolean isExist = false;
        Channel channel = bookEntry.getChannel();
        for (Channel aChannel : allChannels) {
            if (aChannel.getName().equals(channel.getName())) {
                isExist = true;
                bookEntry.setChannel(aChannel);
                break;
            }
        }
        if (!isExist) {
            // 不存在渠道，报错
            throw new RuntimeException("不存在的渠道" + channel);
        }


        // EntryTags查重
        Set<EntryTag> entryTags = bookEntry.getEntryTags();
        Map<String,Integer> entryTagEnumNameCounts = new HashMap<>();

        boolean entryTagsNotNull = entryTags != null;
        if (entryTagsNotNull) {
            for (EntryTag entryTag : entryTags) {
                String ETE_Name = entryTag.getEntryTagEnum().getName();
                entryTagEnumNameCounts.put(ETE_Name,
                        entryTagEnumNameCounts.getOrDefault(ETE_Name, 0) + 1);
                Integer count = entryTagEnumNameCounts.get(ETE_Name);
                if (count == null) {
                    throw new RuntimeException("发生了不可思议的错误");

                } else if (count > 1) {
                    // 重复的EntryTag
                    throw new RuntimeException("重复的EntryTag" + entryTag);
                }
            }
        }

        // 设置createTime和updateTime
        long createTime = System.currentTimeMillis();

        // 保存BookEntry，获取id
        int count = bookEntryMapper.insert(bookEntry,createTime,createTime);
        if (count == 1) {
            bookEntry.setCreateTime(createTime);
            bookEntry.setUpdateTime(createTime);
            // 保存EntryTags
            if (entryTagsNotNull) {
                entryTags.forEach(entryTag -> {
                    entryTag.setGroupId(bookEntry.getId());
                    entryTagService.saveEntryTag(entryTag);
                });
            }
        }
        return count;
    }

    /**
     * 保存账本条目列表
     * 注意，这是插入一个全新的账本条目列表，而不是更新
     *
     * @param bookEntryList 账本条目列表
     * @return int
     */
    @Override
    @Transactional
    public int saveNewBookEntryList(List<BookEntry> bookEntryList) throws RuntimeException{
        // 处理特殊情况
        if (bookEntryList == null || bookEntryList.size() == 0) {
            return 0;
        }else if(bookEntryList.size() == 1){
            BookEntry firstEntry = bookEntryList.get(0);
            firstEntry.setPreviousId(null);
            firstEntry.setNextId(null);
            int count = saveBookEntry(firstEntry);
            return 1;
        }
        for (BookEntry bookEntry : bookEntryList) {
            bookEntry.setPreviousId(-1L);
            bookEntry.setNextId(-1L);
            saveBookEntry(bookEntry);
        }

        // 设置头尾条目的previousId和nextId
        BookEntry firstEntry = bookEntryList.get(0);
        firstEntry.setPreviousId(null);
        firstEntry.setNextId(bookEntryList.get(1).getId());
        saveBookEntry(firstEntry);


        BookEntry lastEntry = bookEntryList.get(bookEntryList.size() - 1);
        lastEntry.setPreviousId(bookEntryList.get(bookEntryList.size() - 2).getId());
        lastEntry.setNextId(null);
        for (int i = 1; i < bookEntryList.size() - 1; i++) {
            BookEntry prevEntry = bookEntryList.get(i - 1);
            BookEntry curEntry = bookEntryList.get(i);
            BookEntry nextEntry = bookEntryList.get(i + 1);
            curEntry.setPreviousId(prevEntry.getId());
            curEntry.setNextId(nextEntry.getId());
        }
        for (BookEntry bookEntry : bookEntryList) {
            // 排除picture，因为picture存在复用的情况
            updateBookEntryInfoWithOutPicture(bookEntry);
        }

        return bookEntryList.size();
    }

    /**
     * 保存账本条目列表，并添加groupId
     * 注意，这是插入一个全新的账本条目列表，而不是更新
     *
     * @param bookEntryList 账本条目列表
     * @return int
     */
    @Override
    @Transactional
    public int saveNewBookEntryList(List<BookEntry> bookEntryList, Long groupId) throws RuntimeException{
        // 处理特殊情况
        if (bookEntryList == null || bookEntryList.size() == 0) {
            return 0;
        }else if(bookEntryList.size() == 1){
            BookEntry firstEntry = bookEntryList.get(0);
            firstEntry.setPreviousId(null);
            firstEntry.setNextId(null);
            firstEntry.setGroupId(groupId);
            saveBookEntry(firstEntry);
            return 1;
        }
        for (BookEntry bookEntry : bookEntryList) {
            bookEntry.setPreviousId(-1L);
            bookEntry.setNextId(-1L);
            bookEntry.setGroupId(groupId);
            saveBookEntry(bookEntry);

        }

        // 设置头尾条目的previousId和nextId
        BookEntry firstEntry = bookEntryList.get(0);
        firstEntry.setPreviousId(null);
        firstEntry.setNextId(bookEntryList.get(1).getId());


        BookEntry lastEntry = bookEntryList.get(bookEntryList.size() - 1);
        lastEntry.setPreviousId(bookEntryList.get(bookEntryList.size() - 2).getId());
        lastEntry.setNextId(null);
        for (int i = 1; i < bookEntryList.size() - 1; i++) {
            BookEntry prevEntry = bookEntryList.get(i - 1);
            BookEntry curEntry = bookEntryList.get(i);
            BookEntry nextEntry = bookEntryList.get(i + 1);
            curEntry.setPreviousId(prevEntry.getId());
            curEntry.setNextId(nextEntry.getId());
        }
        for (BookEntry bookEntry : bookEntryList) {
            // 排除picture，因为picture存在复用的情况
            updateBookEntryInfoWithOutPicture(bookEntry);
        }

        return bookEntryList.size();
    }

    @Override
    @Transactional
    public int removeBookEntryById(Long id) {
        BookEntry bookEntry = bookEntryMapper.selectById(id);

        // 删除BookEntry对应的EntryTag
        entryTagService.removeEntryTagByGroupId(id);

        // 删除BookEntry对应的Picture
        pictureService.removePictureById(bookEntry.getPicture().getId());

        return bookEntryMapper.deleteById(id);
    }

    @Transactional
    public int removeBookEntry(BookEntry bookEntry){
        // 删除BookEntry对应的EntryTag
        entryTagService.removeEntryTagByGroupId(bookEntry.getId());
        // 删除BookEntry对应的Picture
        pictureService.removePictureById(bookEntry.getPicture().getId());
        return removeBookEntryById(bookEntry.getId());
    }

    @Override
//    @Deprecated
    @Transactional
    public int removeBookEntryByGroupId(Long groupId) throws RuntimeException{
        bookEntryMapper.selectByGroupId(groupId).forEach(bookEntry -> {
            // 删除BookEntry对应的EntryTag
            entryTagService.removeEntryTagByGroupId(bookEntry.getId());

            // 删除BookEntry对应的Picture
            pictureService.removePictureById(bookEntry.getPicture().getId());
        });

        return bookEntryMapper.deleteByGroupId(groupId);
    }
//    @Override
//    public int removeBookEntryByPictureId(Long pictureId) {
//        return bookEntryMapper.deleteByPictureId(pictureId);
//    }
//    @Override
//    public int removeBookEntryByChannelId(Long channelId) {
//        return bookEntryMapper.deleteByChannelId(channelId);
//    }
    @Transactional
    public int updateBookEntryInfo(BookEntry bookEntry) {
        long updateTime = System.currentTimeMillis();
        int count = bookEntryMapper.update(bookEntry, updateTime);
        if (count == 1) {
            bookEntry.setUpdateTime(updateTime);
        }
        return count;
    }

    @Transactional
    public int updateBookEntryInfoWithOutPicture(BookEntry bookEntry) {
        long updateTime = System.currentTimeMillis();
        int count = bookEntryMapper.updateIgnorePictureId(bookEntry, updateTime);
        if (count == 1) {
            bookEntry.setUpdateTime(updateTime);
        }
        return count;
    }


//    @Override
//    public int removeEntryTag(BookEntry bookEntry, EntryTagEnum entryTagEnum) {
//        return 0;
//    }
//    @Override
//    @Transactional
//    public int updateBookEntry(BookEntry bookEntry) throws RuntimeException{
//        // 删除BookEntry对应的EntryTag
//        entryTagService.removeEntryTagByGroupId(bookEntry.getId());
//        // 保存EntryTag，利用BookEntry的id作为groupId
//        bookEntry.getEntryTags().forEach(entryTag -> {
//            entryTag.setGroupId(bookEntry.getId());
//            entryTagService.saveEntryTag(entryTag);
//        });
//
//        long updateTime = System.currentTimeMillis();
//        int count = bookEntryMapper.update(bookEntry, updateTime);
//        bookEntry.setUpdateTime(updateTime);
//        return count;
//
//
//    }
//    @Override
//    @Transactional
//    public int updateEntryTags(BookEntry bookEntry) throws RuntimeException{
//        // 删除BookEntry对应的EntryTag
//        entryTagService.removeEntryTagByGroupId(bookEntry.getId());
//        // 保存EntryTag，利用BookEntry的id作为groupId
//        bookEntry.getEntryTags().forEach(entryTag -> {
//            entryTag.setGroupId(bookEntry.getId());
//            entryTagService.saveEntryTag(entryTag);
//        });
//
//        long updateTime = System.currentTimeMillis();
//        bookEntryMapper.update(bookEntry,updateTime);
//        bookEntry.setUpdateTime(updateTime);
//        return 1;
//    }
//    @Transactional
//    public int updateEntryTags(BookEntry bookEntry, Set<EntryTag> entryTags) throws RuntimeException{
//        bookEntry.setEntryTags(entryTags);
//        return updateEntryTags(bookEntry);
//    }


    @Override
    public BookEntry getBookEntryById(Long id) {
        return bookEntryMapper.selectById(id);
    }
//    @Override
//    public BookEntry getBookEntryByPictureId(Long pictureId) {
//        return bookEntryMapper.selectByPictureId(pictureId);
//    }
    @Override
    public List<BookEntry> getBookEntryByGroupId(Long groupId) {
        return bookEntryMapper.selectByGroupId(groupId);
    }
}
