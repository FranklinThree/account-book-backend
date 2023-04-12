package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.Channel;
import com.github.franklinthree.main.service.ChannelService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.franklinthree.main.mapper.ChannelMapper;
import java.util.List;

/**
 * 渠道服务实现类
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className ChannelServiceImpl
 * @see @see ChannelService
 * @since 1.0.0
 */
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    private List<Channel> channels;

    public ChannelServiceImpl() {

    }

//    @PostConstruct
    public void init(){
        System.out.println("6");
        channels = channelMapper.selectAll();
    }

    @Override
    public int saveChannel(Channel channel) {
        return channelMapper.insert(channel);
    }

    @Override
    public int removeChannelById(Long id) {
        return channelMapper.deleteById(id);
    }

    @Override
    public int removeChannelByName(String name) {
        return channelMapper.deleteByName(name);
    }

    @Override
    public int modifyChannel(Channel channel) {
        return channelMapper.update(channel);
    }

    @Override
    public Channel getChannelById(Long id) {
        return channelMapper.selectById(id);
    }

    @Override
    public Channel getChannelByName(String name) {
        return channelMapper.selectByName(name);
    }

    @Override
    public List<Channel> getChannelsByOwnerId(Long ownerId) {
        return channelMapper.selectByOwnerId(ownerId);
    }

    @Override
    public List<Channel> getAllChannels() {
        if (channels == null){
            return channelMapper.selectAll();
        }else {
            return channels;
        }
    }
}
