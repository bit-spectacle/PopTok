package com.poptok.android.poptok.service.user;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = {CookiePreserveHttpRequestInterceptor.class})
public interface IUserFinder {

    @Get("/users/userinfo/{email}")
    JSONResult<UserInfo> findUser(@Path String email);

    @Get("/auth/login/{email}/{password}")
    JSONResult<UserInfo> userLogin(@Path String email, @Path String password);

    @Get("/auth/join/{email}/{password}/{nickname}")
    JSONResult<Integer> userJoin(@Path String email, @Path String password, @Path String nickname);

    @Get("/auth/logout/{userNo}")
    List<UserInfo> userLogout(@Path int userNo );

    @Get("/auth/changeNickname/{nickname}/{userNo}")
    JSONResult<UserInfo> changeNickname(@Path String nickname, @Path int userNo);

    @Get("/auth/checkNickname/{nickname}")
    JSONResult<Integer> checkNickname(@Path String nickname);

    @Get("/auth/changeStatus/{status}/{userNo}")
    JSONResult<UserInfo> changeStatus(@Path String status, @Path int userNo);

//    @Get("/auth/UpdateImage/{post}/{imageUrl}")
//    JSONResult<String> updateImage(@Path int userNo, @Path String imageUrl);

}
