package com.example.itp.vendorapp.model;

class RedeemItem {

    private String id;
    private String status;
    private String title;
    private String pointsRequired;
    private String imgUrl;

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
}
