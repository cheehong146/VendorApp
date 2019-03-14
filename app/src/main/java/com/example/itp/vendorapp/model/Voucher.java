package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Voucher implements Parcelable {

    private String id;
    private String imgUrl;
    private String title;
    private String pointsRequired;

    public Voucher() {
    }

    public Voucher(String id, String imgUrl, String title, String pointsRequired) {
        this.id = id;
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
        dest.writeString(this.imgUrl);
        dest.writeString(this.title);
        dest.writeString(this.pointsRequired);
    }

    protected Voucher(Parcel in) {
        this.id = in.readString();
        this.imgUrl = in.readString();
        this.title = in.readString();
        this.pointsRequired = in.readString();
    }

    public static final Creator<Voucher> CREATOR = new Creator<Voucher>() {
        @Override
        public Voucher createFromParcel(Parcel source) {
            return new Voucher(source);
        }

        @Override
        public Voucher[] newArray(int size) {
            return new Voucher[size];
        }
    };
}
