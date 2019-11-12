package com.example.moneymanager.models;

import com.example.moneymanager.R;

import java.util.HashMap;

public class App {
    private HashMap<String, Integer>icons;
    public App(){
        icons = new HashMap<>();
        initialize();
    }
    public void initialize(){
        icons.put("Food", R.mipmap.food);
        icons.put("Bills", R.mipmap.bills);
        icons.put("Transportation", R.mipmap.transportation);
        icons.put("Home", R.mipmap.home);
        icons.put("Car", R.mipmap.car);
        icons.put("Entertainment", R.mipmap.entertainment);
        icons.put("Shopping", R.mipmap.shopping);
        icons.put("Clothing", R.mipmap.clothing);
        icons.put("Insurance", R.mipmap.insurance);
        icons.put("Tax", R.mipmap.tax);
        icons.put("Telephone", R.mipmap.telephone);
        icons.put("Cigarette", R.mipmap.cigar);
        icons.put("Health", R.mipmap.health);
        icons.put("Sport", R.mipmap.sport);
        icons.put("Baby", R.mipmap.baby);
        icons.put("Pet", R.mipmap.pet);
        icons.put("Add", R.mipmap.add_item);
    }

    public HashMap<String, Integer> geticons() {
        return icons;
    }

    public void seticons(HashMap<String, Integer> icons) {
        this.icons = icons;
    }

    public int getICons(String key){
        return icons.get(key);
    }
}
