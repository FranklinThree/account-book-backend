package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.BookEntryMapper;
import com.github.franklinthree.main.model.BookEntry;
import com.github.franklinthree.main.model.Channel;
import com.github.franklinthree.main.model.EntryTag;
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
    public int saveBookEntry(BookEntry bookEntry) throws RuntimeException{
        // 保存图片
        pictureService.savePicture(bookEntry.getPicture());

        // 检查渠道是否存在
        List<Channel> allChannels = channelService.getAllChannels();
        boolean isExist = false;
        Channel channel = bookEntry.getChannel();
        for (Channel aChannel : allChannels) {
            if (aChannel.getName().equals(channel.getName())) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            // 不存在渠道，报错
            throw new RuntimeException("不存在的渠道" + channel);
        }

        // EntryTags去重
        Set<EntryTag> entryTags = bookEntry.getEntryTags();
        Map<String,Integer> entryTagEnumNameCounts = new HashMap<>();
        for (EntryTag entryTag : entryTags) {
            Integer had = entryTagEnumNameCounts.put(entryTag.getEntryTagEnum().getName(),
                    entryTagEnumNameCounts.getOrDefault(entryTag.getEntryTagEnum().getName(), 0) + 1);
            if (had == null) {
                throw new RuntimeException("发生了不可思议的错误");

            }else if(had == 0){
                entryTag.setGroupId(bookEntry.getId());
                entryTagService.saveEntryTag(entryTag);
            }else{

            }

        }

        bookEntry.setCreateTime(System.currentTimeMillis());
        return bookEntryMapper.insert(bookEntry);
    }

    @Override
    public int removeBookEntryById(Long id) {
        return bookEntryMapper.deleteById(id);
    }

    @Override
    public int removeBookEntryByGroupId(Long groupId) {
        return bookEntryMapper.deleteByGroupId(groupId);
    }

    @Override
    public int removeBookEntryByPictureId(Long pictureId) {
        return bookEntryMapper.deleteByPictureId(pictureId);
    }

    @Override
    public int removeBookEntryByChannelId(Long channelId) {
        return bookEntryMapper.deleteByChannelId(channelId);
    }

    @Override
    public int updateBookEntry(BookEntry bookEntry) {
        bookEntry.setUpdateTime(System.currentTimeMillis());
        return bookEntryMapper.update(bookEntry);
    }

    @Override
    public BookEntry getBookEntryById(Long id) {
        return bookEntryMapper.selectById(id);
    }

    @Override
    public BookEntry getBookEntryByPictureId(Long pictureId) {
        return bookEntryMapper.selectByPictureId(pictureId);
    }

    @Override
    public List<BookEntry> getBookEntryByGroupId(Long groupId) {
        return bookEntryMapper.selectByGroupId(groupId);
    }
}
