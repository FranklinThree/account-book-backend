package com.github.franklinthree.main.model;

import java.util.List;
import java.util.Set;

/**
 * 账本条目
 *
 * @author FranklinThree
 * @date 2023/04/11
 * @className BookEntry
 * @see
 * @since 1.0.0
 */
public class BookEntry {
    private Long id;
    private Picture picture;
    private Channel channel;

    private double money;

    private String description;

    private Set<EntryTag> entryTags;

    private Long groupId;

    private Long createTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    @Deprecated
    public void setId(Long id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EntryTag> getEntryTags() {
        return entryTags;
    }

    public void setEntryTags(Set<EntryTag> entryTags) {
        this.entryTags = entryTags;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


    public BookEntry() {
    }

    @Override
    public String toString() {
        return "BookEntry{" +
                "id=" + id +
                ", picture=" + picture +
                ", channel=" + channel +
                ", money=" + money +
                ", description='" + description + '\'' +
                ", entryTags=" + entryTags +
                ", groupId=" + groupId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public BookEntry(Picture picture, Channel channel, double money, String description, Set<EntryTag> entryTags, Long groupId) {
        this.id = null;
        this.picture = picture;
        this.channel = channel;
        this.money = money;
        this.description = description;
        this.entryTags = entryTags;
        this.groupId = groupId;
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
    }
}
