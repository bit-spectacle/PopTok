package com.poptok.android.poptok.service.friend;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by BIT on 2018-01-26.
 */
@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = {CookiePreserveHttpRequestInterceptor.class})
public interface IFriendFinder {

    @Get("/friend/checkFriend/{userNo}/{userNo2}")
    JSONResult<Integer> checkFriend(@Path int userNo, @Path int userNo2);

    @Get("/friend/RejectFriendStatus/{userNo}/{userNo2}")
    JSONResult<Integer> RejectFriend(@Path int userNo, @Path int userNo2);

    @Get("/friend/AcceptFriendStatus/{userNo}/{userNo2}")
    JSONResult<Integer> AcceptFriend(@Path int userNo, @Path int userNo2);

    @Get("/friend/RequestFriendStatus/{userNo}/{userNo2}")
    JSONResult<Integer> RequestFriend(@Path int userNo, @Path int userNo2);

    @Get("/friend/AddMeFriend/{userNo}")
    JSONResult<List<FriendList>> addMeFriend(@Path int userNo);

    @Get("/friend/GetFriendProfile/{userNo}")
    JSONResult<List<FriendList>> getFriendProfile(@Path int userNo);

}
