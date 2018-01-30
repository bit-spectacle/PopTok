package com.poptok.android.poptok.service.hash;

import com.poptok.android.poptok.model.hash.TagItem;

import java.util.List;

public interface IHashfindResultHandler {
    void hashFindResultHandler(List<TagItem> result);
}
