package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RedeemItem implements Parcelable {

    private String id;
    private String status;
    private String title;
    private String pointsRequired;
    private String imgUrl;

    public RedeemItem() {
    }

    public RedeemItem(String id, String status, String title, String pointsRequired, String imgUrl) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.pointsRequired = pointsRequired;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(String pointsRequired) {
        this.pointsRequired = pointsRequired;
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
        dest.writeString(this.id);
        dest.writeString(this.status);
        dest.writeString(this.title);
        dest.writeString(this.pointsRequired);
        dest.writeString(this.imgUrl);
    }

    protected RedeemItem(Parcel in) {
        this.id = in.readString();
        this.status = in.readString();
        this.title = in.readString();
        this.pointsRequired = in.readString();
        this.imgUrl = in.readString();
    }

    public static final Creator<RedeemItem> CREATOR = new Creator<RedeemItem>() {
        @Override
        public RedeemItem createFromParcel(Parcel source) {
            return new RedeemItem(source);
        }

        @Override
        public RedeemItem[] newArray(int size) {
            return new RedeemItem[size];
        }
    };
}
