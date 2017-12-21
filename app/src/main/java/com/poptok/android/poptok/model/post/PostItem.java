package com.poptok.android.poptok.model.post;

/**
 * Created by BIT on 2017-12-19.
 */

public class PostItem {

    private int postNo;
    private int userNo;
    private String content;
    private String image;
    private String postDate;

    public PostItem () { };

    public PostItem(int postNo, int userNo, String content, String image, String postDate) {
        this.postNo = postNo;
        this.userNo = userNo;
        this.content = content;
        this.image = image;
        this.postDate = postDate;
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
