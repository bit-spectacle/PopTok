package com.poptok.android.poptok.model.setting;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class PoptokAppSettings {
    boolean isAllAlram;
    boolean isFriendPostingAlram;
    boolean isCommentAlram;
    boolean isGpson;

    public boolean isAllAlram() {
        return isAllAlram;
    }

    public void setAllAlram(boolean allAlram) {
        isAllAlram = allAlram;
    }

    public boolean isFriendPostingAlram() {
        return isFriendPostingAlram;
    }

    public void setFriendPostingAlram(boolean friendPostingAlram) {
        isFriendPostingAlram = friendPostingAlram;
    }

    public boolean isCommentAlram() {
        return isCommentAlram;
    }

    public void setCommentAlram(boolean commentAlram) {
        isCommentAlram = commentAlram;
    }

    public boolean isGpson() {
        return isGpson;
    }

    public void setGpson(boolean gpson) {
        isGpson = gpson;
    }
}
