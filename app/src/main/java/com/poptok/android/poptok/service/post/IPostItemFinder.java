package com.poptok.android.poptok.service.post;

import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.model.post.PostMapItem;
import com.poptok.android.poptok.service.HttpBasicAuthenticatorInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "http://192.168.1.9:3000",
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = { HttpBasicAuthenticatorInterceptor.class })
public interface IPostItemFinder {

    @Get("/posting/list/{lastNo}")
    List<PostListItem> findPostList(@Path int lastNo);

    @Get("/posting/map/{topLat}/{topLong}/{botLat}/{botLong}/{zoomLevel}/{userNo}")
    List<PostMapItem> findPostMap(@Path double topLat, @Path double topLong, @Path double botLat, @Path double botLong, @Path int zoomLevel, @Path int userNo);
}
