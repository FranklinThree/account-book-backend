package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.server.Book;
import com.github.franklinthree.main.model.server.Picture;

import java.util.List;

/**
 * 账本服务
 *
 * @author FranklinThree
 * @date 2023/04/13
 * @className BookService
 * @see
 * @since 1.0.0
 */
public interface BookService {

    /**
     * 保存账本
     *
     * @param book 账本
     * @return int 影响数量
     */
    int saveBook(Book book);

    /**
     * 删除账本
     *
     * @param book 账本
     * @return int 影响数量
     */
    int removeBook(Book book);

    /**
     * 更新账本信息
     *
     * @param book 账本
     * @return int 影响数量
     */
    int updateBookInfo(Book book);

    /**
     * 更新账本图片
     *
     * @param book    账本
     * @param picture 图片
     * @return int 影响数量
     */
    int updateBookPicture(Book book, Picture picture);

    /**
     * 通过id获取账本
     *
     * @param id id
     * @return {@link Book } 账本
     */
    Book getBookById(Long id);


    /**
     * 通过所有者id获取账本列表
     *
     * @param ownerId 所有者id
     * @return {@link List }<{@link Book }> 账本列表
     */
    List<Book> getBookByOwnerId(Long ownerId);
}
