package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuDetail implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String imgUrl;
    private String price;//not using for calculation hence the string
    private boolean displayPrice;

    public MenuDetail() {
    }

    public MenuDetail(String id, String name, String description, String imgUrl, String price, boolean displayPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.displayPrice = displayPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(boolean displayPrice) {
        this.displayPrice = displayPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.imgUrl);
        dest.writeString(this.price);
        dest.writeByte(this.displayPrice ? (byte) 1 : (byte) 0);
    }

    protected MenuDetail(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.imgUrl = in.readString();
        this.price = in.readString();
        this.displayPrice = in.readByte() != 0;
    }

    public static final Creator<MenuDetail> CREATOR = new Creator<MenuDetail>() {
        @Override
        public MenuDetail createFromParcel(Parcel source) {
            return new MenuDetail(source);
        }

        @Override
        public MenuDetail[] newArray(int size) {
            return new MenuDetail[size];
        }
    };
}
