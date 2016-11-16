package com.david.practice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.david.practice.ResClass.FoodShop;
import com.david.practice.ResClass.Shop;
import com.david.practice.adapter.FoodShopAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2016/10/13.
 */

public class NewListView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_list_view);
        getData();
        ListView listView = (ListView)findViewById(R.id.new_list_view);
        FoodShopAdapter foodShopAdapter = new FoodShopAdapter(NewListView.this,foodShopList);
        listView.setAdapter(foodShopAdapter);
    }
    List<FoodShop> foodShopList = new ArrayList<FoodShop>();
    public void getData(){
        getShopData();
        for (int i=0;i<3;i++){
            FoodShop foodShop = new FoodShop();
            foodShop.setShopList(shopList);
            foodShopList.add(foodShop);
        }

    }
    List<Shop> shopList = new ArrayList<Shop>();
    public void getShopData(){
        for (int i = 0;i<3;i++){
            Shop shop = new Shop();
            shop.setName("肯德基");
            shopList.add(shop);
        }

    }
}
