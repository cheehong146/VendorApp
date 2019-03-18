package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VoucherItem implements Parcelable {

    private String id;
    private String status;
    private String imgUrl;
    private String title;
    private String pointsRequired;

    public VoucherItem() {
    }

    public VoucherItem(String id, String status, String imgUrl, String title, String pointsRequired) {
        this.id = id;
        this.status = status;
        this.imgUrl = imgUrl;
        this.title = title;
        this.pointsRequired = pointsRequired;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.status);
        dest.writeString(this.imgUrl);
        dest.writeString(this.title);
        dest.writeString(this.pointsRequired);
    }

    protected VoucherItem(Parcel in) {
        this.id = in.readString();
        this.status = in.readString();
        this.imgUrl = in.readString();
        this.title = in.readString();
        this.pointsRequired = in.readString();
    }

    public static final Creator<VoucherItem> CREATOR = new Creator<VoucherItem>() {
        @Override
        public VoucherItem createFromParcel(Parcel source) {
            return new VoucherItem(source);
        }

        @Override
        public VoucherItem[] newArray(int size) {
            return new VoucherItem[size];
        }
    };
}
