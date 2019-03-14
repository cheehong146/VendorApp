package com.example.itp.vendorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PromotionItem extends Item implements Parcelable {

    private String validityDate;
    private String termsAndCondition;

    public PromotionItem() {
    }

    public PromotionItem(String validityDate, String termsAndCondition) {
        this.validityDate = validityDate;
        this.termsAndCondition = termsAndCondition;
    }

    public PromotionItem(int id, String name, String price, String pointRequired, String type, String subType, String desc, String imgUrl, String validityDate, String termsAndCondition) {
        super(id, name, price, pointRequired, type, subType, desc, imgUrl);
        this.validityDate = validityDate;
        this.termsAndCondition = termsAndCondition;
    }

    public PromotionItem(Parcel in, String validityDate, String termsAndCondition) {
        super(in);
        this.validityDate = validityDate;
        this.termsAndCondition = termsAndCondition;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public void setTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.validityDate);
        dest.writeString(this.termsAndCondition);
    }

    protected PromotionItem(Parcel in) {
        super(in);
        this.validityDate = in.readString();
        this.termsAndCondition = in.readString();
    }

    public static final Creator<PromotionItem> CREATOR = new Creator<PromotionItem>() {
        @Override
        public PromotionItem createFromParcel(Parcel source) {
            return new PromotionItem(source);
        }

        @Override
        public PromotionItem[] newArray(int size) {
            return new PromotionItem[size];
        }
    };
}
