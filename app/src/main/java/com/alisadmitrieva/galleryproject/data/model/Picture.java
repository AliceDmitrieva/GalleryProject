package com.alisadmitrieva.galleryproject.data.model;

public class Picture {

    private String pictureURL;

    public Picture(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

}
