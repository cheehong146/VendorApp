package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {
    private String id;
    private String username;
    private String imgUrl;
    private String points;

    public Customer() {
    }

    public Customer(String id, String username, String imgUrl, String points) {
        this.id = id;
        this.username = username;
        this.imgUrl = imgUrl;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public static Creator<Customer> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.username);
        dest.writeString(this.imgUrl);
        dest.writeString(this.points);
    }

    protected Customer(Parcel in) {
        this.id = in.readString();
        this.username = in.readString();
        this.imgUrl = in.readString();
        this.points = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
