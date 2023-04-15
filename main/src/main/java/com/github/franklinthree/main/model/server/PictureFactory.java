package com.github.franklinthree.main.model.server;

import com.github.franklinthree.main.util.Base62Encoder;

import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 以离散数据方式创建图片
     *
     * @param name    名字
     * @param data    数据
     * @param groupId 组id
     * @return {@link Picture } 图片
     */
    public Picture createPicture(String name, Byte[] data, Long groupId) throws RuntimeException{
        String saveName = generateName(name, groupId);
        PictureType type = generateType(name);
        return new Picture(saveName, data, groupId, System.currentTimeMillis(), type);
    }


    /**
     * 以图片数据方式处理图片对象
     *
     * @param picture 图片数据
     * @return {@link Picture } 图片
     */
    public Picture fixPicture(Picture picture) throws RuntimeException{

        if (picture.getType() == null){
            picture.setType(generateType(picture.getName()));
        }
        picture.setName(generateName(picture.getName(), picture.getGroupId()));
//        picture.getCreateTime();
//        if (picture.getCreateTime() == null) {
//            picture.setCreateTime(System.currentTimeMillis());
//        }
        return picture;
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

    /**
     * 生成图片名
     *
     * @param originalName 原始图片名
     * @param groupId      组id
     * @return {@link String } 图片名
     */
    public String generateName(String originalName, Long groupId){
        indexRoll();
        PictureType type = generateType(originalName);
        Date date = new Date();
        long time = date.getTime();
        if (groupId == null){
            groupId = 1L;
        }
        String encode = Base62Encoder.encode(time / (long) (Math.random() * 9 + 2)) + groupId * 13 + index;
        return encode +'-'+ sdf.format(date) + "." + type.toString();
    }
}

