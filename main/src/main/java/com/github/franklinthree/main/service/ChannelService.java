package com.github.franklinthree.main.service;

import com.github.franklinthree.main.model.Channel;

import java.util.List;

/**
 * 渠道服务
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className ChannelService
 * @see
 * @since 1.0.0
 */
public interface ChannelService {

    /**
     * 保存渠道
     *
     * @param channel 渠道
     * @return int 影响数量
     */
    int saveChannel(Channel channel);

    /**
     * 按id删除渠道
     *
     * @param id id
     * @return int 影响数量
     */
    int removeChannelById(Long id);

    /**
     * 按name删除渠道
     *
     * @param name 渠道名
     * @return int 影响数量
     */
    int removeChannelByName(String name);

    /**
     * 按id修改渠道
     *
     * @param channel 渠道
     * @return int 影响数量
     */
    int modifyChannel(Channel channel);

    /**
     * 按id获取渠道
     *
     * @param id id
     * @return {@link Channel } 渠道
     */
    Channel getChannelById(Long id);

    /**
     * 按name获取渠道
     *
     * @param name 渠道名
     * @return {@link Channel } 渠道
     */
    Channel getChannelByName(String name);

    /**
     * 获取所有者的所有渠道
     *
     * @param ownerId 所有者id
     * @return {@link List }<{@link Channel }> 所有者的所有渠道
     */
    List<Channel> getChannelsByOwnerId(Long ownerId);

    /**
     * 获取所有渠道
     *
     * @return {@link List }<{@link Channel }> 所有渠道
     */
    List<Channel> getAllChannels();

}
