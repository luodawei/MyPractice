package com.david.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.david.practice.R;
import com.david.practice.ResClass.FoodShop;
import com.david.practice.ResClass.Shop;

import java.util.List;

/**
 * Created by David on 2016/10/13.
 */

public class FoodShopAdapter extends BaseAdapter {
    private List<FoodShop> foodShopList;
    private Context context;
    public FoodShopAdapter(Context context,List<FoodShop> foodShopList){
        this.context = context;
        this.foodShopList = foodShopList;
    }
    @Override
    public int getCount() {
        return foodShopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.food_shop,null);
        }
//        LinearLayout foodShopName = (LinearLayout)convertView.findViewById(R.id.food_shop_ad);
        FoodShop foodShop = foodShopList.get(position);

        LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.food_shop_item);
        List<Shop> shopList = foodShop.getShopList();
        for (int i = 0 ;i<shopList.size();i++){
            View view = layoutInflater.inflate(R.layout.food_shop,null);
            TextView foodShopName = (TextView)view.findViewById(R.id.food_shop_name);
            Shop shop = shopList.get(i);
            foodShopName.setText(shop.getName());
            linearLayout.addView(view);
        }

        return convertView;
    }
}
