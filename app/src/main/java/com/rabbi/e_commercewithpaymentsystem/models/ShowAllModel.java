package com.rabbi.e_commercewithpaymentsystem.models;

public class ShowAllModel {
    private String img_url, name, rating, desc,type;
    private int price;

    public ShowAllModel() {
    }

    public ShowAllModel(String img_url, String name, String rating, String desc, String type, int price) {
        this.img_url = img_url;
        this.name = name;
        this.rating = rating;
        this.desc = desc;
        this.type = type;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
