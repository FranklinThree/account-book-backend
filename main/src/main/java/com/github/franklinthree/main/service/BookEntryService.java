package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.BookEntry;

import java.util.List;

/**
 * 账本条目服务
 *
 * @author FranklinThree
 * @date 2023/04/11
 * @className BookEntryService
 * @see
 * @since 1.0.0
 */
public interface BookEntryService {

    /**
     * 保存账本条目
     * 在保存账本条目时，应当确保Channel和EntryTag.EntryTagEnum已经插入到数据库中
     *
     * @param bookEntry 账本条目
     * @return int 影响数量
     */
    int saveBookEntry(BookEntry bookEntry);

    /**
     * 按id删除账本条目
     *
     * @param id 账本条目id
     * @return int 影响数量
     */
    int removeBookEntryById(Long id);

    /**
     * 按groupId删除账本条目
     *
     * @param groupId 账本条目groupId
     * @return int 影响数量
     */
    int removeBookEntryByGroupId(Long groupId);

    /**
     * 按pictureId删除账本条目
     *
     * @param pictureId 账本条目pictureId
     * @return int 影响数量
     */
    int removeBookEntryByPictureId(Long pictureId);

    /**
     * 按channelId删除账本条目
     *
     * @param channelId 账本条目channelId
     * @return int 影响数量
     */
    int removeBookEntryByChannelId(Long channelId);

    /**
     * 更新账本条目
     *
     * @param bookEntry 账本条目
     * @return int 影响数量
     */
    int updateBookEntry(BookEntry bookEntry);

    /**
     * 按id查询账本条目
     *
     * @param id 本条目id
     * @return BookEntry 账本条目
     */
    BookEntry getBookEntryById(Long id);

    /**
     * 按pictureId查询账本条目
     *
     * @param pictureId 账本条目pictureId
     * @return BookEntry 账本条目
     */
    BookEntry getBookEntryByPictureId(Long pictureId);

    /**
     * 按groupId查询账本条目
     *
     * @param groupId 账本条目groupId
     * @return BookEntry 账本条目
     */
    List<BookEntry> getBookEntryByGroupId(Long groupId);

}
