package com.poptok.android.poptok.model.post;

/**
 * Created by BIT on 2018-01-25.
 */

public class PostWriteParam {
    int userNo;
    int locationNo;
    String content;
    String image;
    String kakaoLink;
    String tag;
    double latitude;
    double longitude;
    int posttype;
    int opentype;

    public PostWriteParam() {
        this.userNo = 0;
        this.locationNo = 0;
        this.content = "";
        this.image = "";
        this.kakaoLink = "";
        this.tag = "";
        this.latitude = 0;
        this.longitude = 0;
        this.posttype = 0;
        this.opentype = 0;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(int locationNo) {
        this.locationNo = locationNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKakaoLink() {
        return kakaoLink;
    }

    public void setKakaoLink(String kakaoLink) {
        this.kakaoLink = kakaoLink;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getPosttype() {
        return posttype;
    }

    public void setPosttype(int posttype) {
        this.posttype = posttype;
    }

    public int getOpentype() {
        return opentype;
    }

    public void setOpentype(int opentype) {
        this.opentype = opentype;
    }
}
