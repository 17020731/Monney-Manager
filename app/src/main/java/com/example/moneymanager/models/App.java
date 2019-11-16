package com.example.moneymanager.models;

import android.util.Pair;

import com.example.moneymanager.R;

import java.util.HashMap;

public class App {
    private HashMap<String, Pair<Integer, Integer>>icons;
    public App(){
        icons = new HashMap<>();
        initialize();
    }
    public void initialize(){
        icons.put("food", new Pair<Integer, Integer>(R.mipmap.food, R.drawable.circle_image_pink));
        icons.put("bills", new Pair<Integer, Integer>(R.mipmap.bills, R.drawable.circle_image_blue_1));
        icons.put("transportation", new Pair<Integer, Integer>(R.mipmap.transportation, R.drawable.circle_image_green_2));
        icons.put("home", new Pair<Integer, Integer>(R.mipmap.home, R.drawable.circle_image_orange_1));
        icons.put("car", new Pair<Integer, Integer>(R.mipmap.car, R.drawable.circle_image_purple));
        icons.put("entertainment", new Pair<Integer, Integer>(R.mipmap.entertainment, R.drawable.circle_image_blue_2));
        icons.put("shopping", new Pair<Integer, Integer>(R.mipmap.shopping, R.drawable.circle_image_pink));
        icons.put("clothing", new Pair<Integer, Integer>(R.mipmap.clothing, R.drawable.circle_image_green_2));
        icons.put("insurance", new Pair<Integer, Integer>(R.mipmap.insurance, R.drawable.circle_image_purple));
        icons.put("tax", new Pair<Integer, Integer>(R.mipmap.tax, R.drawable.circle_image_green_2));
        icons.put("telephone", new Pair<Integer, Integer>(R.mipmap.telephone, R.drawable.circle_image_green_1));
        icons.put("cigarette", new Pair<Integer, Integer>(R.mipmap.cigar, R.drawable.circle_image_orange_2));
        icons.put("health", new Pair<Integer, Integer>(R.mipmap.health, R.drawable.circle_image_orange_2));
        icons.put("sport", new Pair<Integer, Integer>(R.mipmap.sport, R.drawable.circle_image_blue_1));
        icons.put("baby", new Pair<Integer, Integer>(R.mipmap.baby, R.drawable.circle_image_purple));
        icons.put("pet", new Pair<Integer, Integer>(R.mipmap.pet, R.drawable.circle_image_orange_2));
        icons.put("add", new Pair<Integer, Integer>(R.mipmap.add_item, R.drawable.circle_image_unselect));
        icons.put("award", new Pair<Integer, Integer>(R.mipmap.awards, R.drawable.circle_image_orange_1));
        icons.put("beauty", new Pair<Integer, Integer>(R.mipmap.beauty, R.drawable.circle_image_pink));
        icons.put("hamburger", new Pair<Integer, Integer>(R.mipmap.hamburger, R.drawable.circle_image_orange_2));
        icons.put("vegetables", new Pair<Integer, Integer>(R.mipmap.vegetables, R.drawable.circle_image_green_1));
        icons.put("snacks", new Pair<Integer, Integer>(R.mipmap.snack, R.drawable.circle_image_pink));
        icons.put("gift", new Pair<Integer, Integer>(R.mipmap.gift, R.drawable.circle_image_orange_2));
        icons.put("social", new Pair<Integer, Integer>(R.mipmap.social, R.drawable.circle_image_purple));
        icons.put("travel", new Pair<Integer, Integer>(R.mipmap.travel, R.drawable.circle_image_green_2));
        icons.put("education", new Pair<Integer, Integer>(R.mipmap.education, R.drawable.circle_image_orange_1));

    }


    public HashMap<String, Pair<Integer, Integer>> geticons() {
        return icons;
    }

    public void setIcons(HashMap<String, Pair<Integer, Integer>> icons) {
        this.icons = icons;
    }
    public Pair<Integer, Integer> getICons(String key){
        return icons.get(key);
    }
}
