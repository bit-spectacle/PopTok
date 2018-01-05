package com.poptok.android.poptok.model.post;

/**
 * Created by BIT on 2017-12-19.
 */

public class PostItem {

    private int rownum;
    private int postNo;
    private int userNo;
    private int viewsCnt;
    private int likeCnt;
    private int commentCnt;
    private String content;
    private String image;
    private String kakaoLink;
    private String postDate;
    private String tag;
    private double latitude;
    private double longitude;

    public PostItem() {
    }

    public PostItem(int rownum, int postNo, int userNo, int viewsCnt, int likeCnt, int commentCnt,
                    String content, String image, String kakaoLink, String postDate, String tag,
                    double latitude, double longitude) {
        this.rownum = rownum;
        this.postNo = postNo;
        this.userNo = userNo;
        this.viewsCnt = viewsCnt;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
        this.content = content;
        this.image = image;
        this.kakaoLink = kakaoLink;
        this.postDate = postDate;
        this.tag = tag;
        this.latitude =latitude;
        this.longitude = longitude;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getViewsCnt() {
        return viewsCnt;
    }

    public void setViewsCnt(int viewsCnt) {
        this.viewsCnt = viewsCnt;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
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
}
