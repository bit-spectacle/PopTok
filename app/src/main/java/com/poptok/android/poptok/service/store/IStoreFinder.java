package com.poptok.android.poptok.service.store;

import com.poptok.android.poptok.model.store.StoreItem;
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
public interface IStoreFinder {

    @Get("/location/store/{topLat}/{topLong}/{botLat}/{botLong}/")
    List<StoreItem> storeList(@Path double topLat, @Path double topLong, @Path double botLat, @Path double botLong);
}
