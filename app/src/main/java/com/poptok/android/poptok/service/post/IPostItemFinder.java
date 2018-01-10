package com.poptok.android.poptok.service.post;

import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.service.HttpBasicAuthenticatorInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "http://192.168.1.39:3000",
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = { HttpBasicAuthenticatorInterceptor.class })
public interface IPostItemFinder {

    @Get("/posting/{lastNo}")
    List<PostItem> findPost(@Path int lastNo);
}
