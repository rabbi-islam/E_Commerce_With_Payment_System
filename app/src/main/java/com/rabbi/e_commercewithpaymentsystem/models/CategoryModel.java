package com.rabbi.e_commercewithpaymentsystem.models;

public class CategoryModel {
    private String imgUrl,name,type;

    public CategoryModel() {
    }

    public CategoryModel(String imgUrl, String name, String type) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
