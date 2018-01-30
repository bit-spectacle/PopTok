package com.poptok.android.poptok.model.hash;

public class TagItem {
    String tag;
    int count;

    public TagItem() {    }

    public TagItem(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
