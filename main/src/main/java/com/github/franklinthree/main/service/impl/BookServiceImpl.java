package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.mapper.BookMapper;
import com.github.franklinthree.main.model.server.Book;
import com.github.franklinthree.main.model.server.Picture;
import com.github.franklinthree.main.service.BookEntryService;
import com.github.franklinthree.main.service.BookService;
import com.github.franklinthree.main.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 书服务
 *
 * @author FranklinThree
 * @date 2023/04/14
 * @className BookService
 * @see
 * @since 1.0.0
 */

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookEntryService bookEntryService;
    @Autowired
    private PictureService pictureService;
    @Override
    @Transactional
    public int saveBook(Book book) throws RuntimeException{

        // 保存账本对应的图片
        Picture picture = book.getPicture();
        pictureService.savePicture(picture);

        // 保存账本
        long createTime = System.currentTimeMillis();
        int count = bookMapper.insert(book, createTime, createTime);

        if (count == 1) {
            // 保存账本对应的条目
            bookEntryService.saveNewBookEntryList(book.getBookEntries(),book.getId());

            book.setCreateTime(createTime);
            book.setUpdateTime(createTime);
        }
        return count;
    }

    @Override
    @Transactional
    public int removeBook(Book book) {
        // 删除账本对应的条目
        bookEntryService.removeBookEntryByGroupId(book.getId());

        // 删除账本
        Long picId = book.getPicture().getId();
        int count = bookMapper.delete(book);

        // 删除账本对应的图片
        pictureService.removePictureById(picId);

        return count;
    }

    @Override
    public int updateBookInfo(Book book) throws RuntimeException{

        long updateTime = System.currentTimeMillis();
        int count = bookMapper.update(book, updateTime, book.getPicture().getId());
        if (count == 1) {
            book.setUpdateTime(updateTime);
        }
        return count;
    }

    @Override
    public int updateBookPicture(Book book, Picture picture) throws RuntimeException{

        if (picture == book.getPicture()){
            return 0;
        }else if(picture.getId() != null){
            return 0;
        }
        // 更新账本对应的图片
        pictureService.removePictureById(book.getPicture().getId());
        pictureService.savePicture(picture);


        long updateTime = System.currentTimeMillis();
        int count = bookMapper.update(book, updateTime, picture.getId());
        if (count == 1) {
            book.setUpdateTime(updateTime);
            book.setPicture(picture);

        }
        return count;
    }

    @Override
    public Book getBookById(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getBookByOwnerId(Long ownerId) {
        return bookMapper.selectByOwnerId(ownerId);
    }
}
