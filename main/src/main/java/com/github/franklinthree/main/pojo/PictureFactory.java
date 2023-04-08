package com.github.franklinthree.main.pojo;

import com.github.franklinthree.main.pojo.Picture;
import com.github.franklinthree.main.pojo.PictureType;
import com.github.franklinthree.main.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class PictureFactory {
    private int index = 0;
    private final SimpleDateFormat sdf;


    public PictureFactory() {
        this.index = 0;
        this.sdf = new SimpleDateFormat("yyyy_MM_dd_HHmm");
    }

    public PictureFactory(int index) {
        this.index = index;
        this.sdf = new SimpleDateFormat("yyyy_MM_dd_HHmm");
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    /**
     * 创建图片
     *
     * @param name    名字
     * @param data    数据
     * @param groupId 组id
     * @return {@link Picture } 图片
     */
    public Picture createPicture(String name, Byte[] data, Long groupId) {
        indexRoll();
        PictureType type = generateType(name);
        Date date = new Date();
        long time = date.getTime();
        String encode = Base62Encoder.encode(time / (long) (Math.random() * 9 + 2)) + groupId * 13 + index;
        String saveName = encode +'-'+ sdf.format(date) + "." + type.toString();
        return new Picture(null, saveName, data, groupId, System.currentTimeMillis(), type);
    }
    /**
     * 生成图片格式
     *
     * @param name 图片名
     * @return {@link PictureType } 图片格式
     * @throws RuntimeException 无法解析的图片类型
     */
    private static PictureType generateType(String name) throws RuntimeException{
        if (name == null || name.isBlank()){
            throw new RuntimeException("无法解析的图片类型，图片名为空");
        }
        int i = name.lastIndexOf('.');
        if (i >= name.length()-1 || i == -1){
            throw new RuntimeException("无法解析的图片类型: " + name);

        }
        String type = name.substring(i + 1);
        return switch (type) {
            case "jpg", "jpeg" -> PictureType.jpg;
            case "png" -> PictureType.png;
            case "tif" -> PictureType.tif;
            case "bmp" -> PictureType.bmp;
            case "webp" -> PictureType.webp;
            default -> throw new RuntimeException("无法解析的图片类型: " + name);
        };
    }

    /**
     * 索引滚动
     */
    private void indexRoll(){
        this.index = (this.index + 66) * 66;
        if (index > 10000){
            this.index = this.index % 10000;
        }else if (this.index <= 0){
            this.index = 1;
        }
    }
}

