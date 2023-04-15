package com.github.franklinthree.main.model.c2s;



import java.text.ParseException;

import static com.github.franklinthree.main.util.SimpleDateFormatImpl.sdf;

public class Entry {
    private long time;
    private int type;
    private String label;
    private String GoodsName;
    private String GoodsShops;
    private String GoodsPrice;
    public Entry(long time,int type, String label, String GoodsName, String GoodsShops, String GoodsPrice){
        this.time = time;
        this.type = type;
        this.label = label;
        this.GoodsName = GoodsName;
        this.GoodsShops = GoodsShops;
        this.GoodsPrice = GoodsPrice;
    }
    public Entry(String time,int type, String label, String GoodsName, String GoodsShops, String GoodsPrice){
        try {
            this.time = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        this.type = type;
        this.label = label;
        this.GoodsName = GoodsName;
        this.GoodsShops = GoodsShops;
        this.GoodsPrice = GoodsPrice;
    }
}
