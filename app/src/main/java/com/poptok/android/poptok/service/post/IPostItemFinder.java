package com.poptok.android.poptok.service.post;

import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.model.post.PostMapItem;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = { CookiePreserveHttpRequestInterceptor.class })
public interface IPostItemFinder {

    @Get("/posting/list/{lastNo}")
    List<PostListItem> findPostList(@Path int lastNo);

    @Get("/posting/map/{topLat}/{topLong}/{botLat}/{botLong}/{zoomLevel}/{userNo}")
    List<PostMapItem> findPostMap(@Path double topLat, @Path double topLong, @Path double botLat, @Path double botLong, @Path int zoomLevel, @Path int userNo);

    @Get("/posting/get/{postNo}")
    PostItem findPost(@Path int postNo);
}
