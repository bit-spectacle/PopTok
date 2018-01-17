package com.poptok.android.poptok.model.post;

/**
 * Created by BIT on 2017-12-19.
 */

public class PostItem {

    private int postNo;
    private int userNo;
    private String profileImage;
    private String nickname;
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
    private int poststatus;

    public PostItem() {
    }

    public PostItem(int postNo, int userNo, String profileImage, String nickname, int viewsCnt, int likeCnt, int commentCnt,
                    String content, String image, String kakaoLink, String postDate, String tag,
                    double latitude, double longitude, int poststatus) {

        this.postNo = postNo;
        this.userNo = userNo;
        this.profileImage = profileImage;
        this.nickname = nickname;
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
        this.poststatus = poststatus;
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
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public int getPoststatus() {
        return poststatus;
    }

    public void setPoststatus(int poststatus) {
        this.poststatus = poststatus;
    }
}
