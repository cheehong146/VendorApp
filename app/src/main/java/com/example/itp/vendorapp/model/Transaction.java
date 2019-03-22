package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {

    private String title;
    private String date;
    private String points;

    public Transaction() {
    }

    public Transaction(String title, String date, String points) {
        this.title = title;
        this.date = date;
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.points);
    }

    protected Transaction(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.points = in.readString();
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
