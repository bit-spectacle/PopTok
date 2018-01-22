package com.poptok.android.poptok.service.interceptor;

import android.util.Log;

import com.poptok.android.poptok.model.auth.CookieStore;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;

@EBean
public class CookiePreserveHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final String LOG_TAG = "CookiePreserve";

    private static final String SET_COOKIE = "set-cookie";
    private static final String COOKIE = "cookie";

    @Bean
    CookieStore cookieStore;


    @Override
    public synchronized ClientHttpResponse intercept(HttpRequest request, byte[] byteArray, ClientHttpRequestExecution execution) throws IOException {
        List<String> setCookies = request.getHeaders().get(COOKIE);

        // If the header doesn't exist and we have stored cookies, add any existing, saved cookies.
        if (setCookies == null && cookieStore.hasCookies()) {
            for (String cookie : cookieStore.getCookies()) {
                Log.d(LOG_TAG, cookie);
                request.getHeaders().add(COOKIE, cookie);
            }
        }

        // Execute the request.
        ClientHttpResponse response = execution.execute(request, byteArray);

        // Pull any cookies off and store them.
        List<String> getCookies = response.getHeaders().get(SET_COOKIE);
        if(getCookies != null && getCookies.size() > 0) {
            Log.d(LOG_TAG, String.format("response cooke: %s", getCookies.get(0)));
        }
        cookieStore.setCookies(getCookies);

        return response;
    }


}