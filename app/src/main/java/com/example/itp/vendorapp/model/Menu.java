package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {

    private String id;
    private String name;
    private String imgUrl;

    public Menu(String id, String name, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.imgUrl);
        dest.writeString(this.name);
    }

    protected Menu(Parcel in) {
        this.id = in.readString();
        this.imgUrl = in.readString();
        this.name = in.readString();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel source) {
            return new Menu(source);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}
