package com.rabbi.e_commercewithpaymentsystem.models;

public class NewProductsModel {

   private String description,imgUrl,name;
   private int price;
   private String rating;

    public NewProductsModel() {
    }

    public NewProductsModel(String description, String imgUrl, String name, int price, String rating) {
        this.description = description;
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
