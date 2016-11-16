package com.david.practice.ResClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2016/10/13.
 */

public class FoodShop {
    private String name = "一只酸奶牛";
    List<Integer> imageList = new ArrayList<Integer>();
    List<Shop> shopList = new ArrayList<Shop>();

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public List<Integer> getImageList() {
        return imageList;
    }

    public void setImageList(List<Integer> imageList) {
        this.imageList = imageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
