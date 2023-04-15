package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.server.BookEntry;
import org.springframework.transaction.annotation.Transactional;

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

    int saveNewBookEntryList(List<BookEntry> bookEntryList);

    @Transactional
    int saveNewBookEntryList(List<BookEntry> bookEntryList, Long groupId);

    /**
     * 按id获取账本条目
     *
     * @param id id
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

//    /**
//     * 按pictureId删除账本条目
//     *
//     * @param pictureId 账本条目pictureId
//     * @return int 影响数量
//     */
//    int removeBookEntryByPictureId(Long pictureId);

//    /**
//     * 按channelId删除账本条目
//     *
//     * @param channelId 账本条目channelId
//     * @return int 影响数量
//     */
//    int removeBookEntryByChannelId(Long channelId);

    /**
     * 更新账本条目忽略条目标记
     * 注意，更新EntryTags时不应使用此方法
     *
     * @param bookEntry 账本条目
     * @return int 影响数量
     */
    int updateBookEntryInfo(BookEntry bookEntry);

//    /**
//     * 更新账本条目
//     * 注意，此操作将更新EntryTags属性，可能导致性能问题
//     *
//     * @param bookEntry 账本条目
//     * @return int 影响数量
//     */
//    int updateBookEntry(BookEntry bookEntry);

//    /**
//     * 更新账本条目中的条目标记
//     *
//     * @param bookEntry 账本条目
//     * @return int 影响数量
//     */
//    int updateEntryTags(BookEntry bookEntry);

    /**
     * 按id查询账本条目
     *
     * @param id 本条目id
     * @return BookEntry 账本条目
     */
    BookEntry getBookEntryById(Long id);

//    /**
//     * 按pictureId查询账本条目
//     *
//     * @param pictureId 账本条目pictureId
//     * @return BookEntry 账本条目
//     */
//    BookEntry getBookEntryByPictureId(Long pictureId);

    /**
     * 按groupId查询账本条目
     *
     * @param groupId 账本条目groupId
     * @return BookEntry 账本条目
     */
    List<BookEntry> getBookEntryByGroupId(Long groupId);

}
