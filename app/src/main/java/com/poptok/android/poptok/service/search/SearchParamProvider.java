package com.poptok.android.poptok.service.search;

import com.poptok.android.poptok.model.search.SearchParam;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class SearchParamProvider {
    private static SearchParam searchParam;

    public SearchParam getSearchParam() {
        return searchParam;
    }

    public SearchParam setSearchParam() {
        return  searchParam;
    }
}
