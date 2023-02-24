package com.rabbi.e_commercewithpaymentsystem.models;

import java.io.Serializable;

public class PopularProductModel implements Serializable {
    private String image_url, product_name, rating, desc;
    private int product_price;

    public PopularProductModel() {
    }

    public PopularProductModel(String image_url, String product_name, String rating, String desc, int product_price) {
        this.image_url = image_url;
        this.product_name = product_name;
        this.rating = rating;
        this.desc = desc;
        this.product_price = product_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
