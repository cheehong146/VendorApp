package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private int id;
    private String name;
    private String price;//It's str because, we won't be using it to calculate anything hence for display only
    private String pointRequired;
    private String type;
    private String subType;
    private String desc;
    private String imgUrl;

    public Item() {
    }

    public Item(int id, String name, String price, String pointRequired, String type, String subType, String desc, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pointRequired = pointRequired;
        this.type = type;
        this.subType = subType;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPointRequired() {
        return pointRequired;
    }

    public void setPointRequired(String pointRequired) {
        this.pointRequired = pointRequired;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.pointRequired);
        dest.writeString(this.type);
        dest.writeString(this.subType);
        dest.writeString(this.desc);
        dest.writeString(this.imgUrl);
    }

    protected Item(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readString();
        this.pointRequired = in.readString();
        this.type = in.readString();
        this.subType = in.readString();
        this.desc = in.readString();
        this.imgUrl = in.readString();
    }

}
