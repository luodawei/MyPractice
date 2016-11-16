package com.david.practice.ResClass;

import com.david.practice.R;

/**
 * Created by David on 2016/10/13.
 */

public class Shop {
    private int resId = R.mipmap.item_img_one;
    private String name = "华莱士";

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
