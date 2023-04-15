package com.github.franklinthree.main.service.impl;

import com.github.franklinthree.main.model.server.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * 渠道服务实现类测试
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className ChannelServiceImplTest
 * @see
 * @since 1.0.0
 */
@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class ChannelServiceImplTest {

    @Autowired
    private ChannelServiceImpl channelService;

    @BeforeEach
    void setUp() {
        System.out.println("ChannelServiceImplTest-----------------------------------------------");
    }

    @Test
    @Order(1)
    void saveChannel() {
        Channel channel = new Channel("饿了么外卖凭证", 0L , "饿了么app或饿了么小程序截图得到的外卖小票");
        Channel channel2 = new Channel("火车票", 0L , "12306火车订票软件上截图得到的火车票信息");
        Channel channel3 = new Channel("公交车票", 0L , "略");
        Channel channel4 = new Channel("飞机票", -1L , "略");

        int count = channelService.saveChannel(channel);
        System.out.println("保存渠道1：" + count);
        assert count == 1;

        count = channelService.saveChannel(channel2);
        System.out.println("保存渠道2：" + count);
        assert count == 1;

        count = channelService.saveChannel(channel3);
        System.out.println("保存渠道3：" + count);
        assert count == 1;

        count = channelService.saveChannel(channel4);
        System.out.println("保存渠道4：" + count);
        assert count == 1;
    }

    @Test
    @Order(4)
    void removeChannelById() {
        List<Channel> allChannels = channelService.getAllChannels();
        long id = allChannels.get(0).getId();
        int count = channelService.removeChannelById(id);
        System.out.println("删除渠道id为" + id + "的渠道：" + count);
        assert count == 1;

    }

    @Test
    @Order(5)
    void removeChannelByName() {
        List<Channel> allChannels = channelService.getAllChannels();
        for (Channel aChannel : allChannels) {
            String name = aChannel.getName();
            int count = channelService.removeChannelByName(name);
            System.out.println("删除name为"+ name + "的渠道：" + count);
            assert count == 1;
        }

    }

    @Test
    @Order(3)
    void modifyChannel() {
        List<Channel> allChannels = channelService.getAllChannels();
        Channel channel = allChannels.get(0);
        channel.setDescription("666");
        int count = channelService.modifyChannel(channel);
        System.out.println("更新渠道：" + count);
        assert count == 1;
    }

    @Test
    @Order(2)
    void getChannelById() {
        List<Channel> allChannels = channelService.getAllChannels();
        long id = allChannels.get(0).getId();
        Channel channelById = channelService.getChannelById(id);
        System.out.println("渠道id为" + id + "的渠道有：" + channelById);
        assert channelById != null;
    }

    @Test
    @Order(2)
    void getChannelByName() {
        String name = "饿了么外卖凭证";
        Channel channelByName = channelService.getChannelByName(name);
        System.out.println("渠道名字为"+ name + "的渠道有：" + channelByName);
        assert channelByName != null;
    }

    @Test
    @Order(2)
    void getChannelsByOwnerId() {
        long ownerId = 0L;
        List<Channel> channelsByOwnerId = channelService.getChannelsByOwnerId(ownerId);
        System.out.println("所有者id为" + ownerId + "的渠道有：");
        for (Channel channel : channelsByOwnerId) {
            assert channel.getName() != null;
            System.out.println(channel);
        }
    }

    @Test
    @Order(2)
    void getAllChannels() {
        List<Channel> allChannels = channelService.getAllChannels();
        System.out.println("所有渠道有：");
        for (Channel channel : allChannels) {
            assert channel.getName() != null;
            System.out.println(channel);
        }

    }
}