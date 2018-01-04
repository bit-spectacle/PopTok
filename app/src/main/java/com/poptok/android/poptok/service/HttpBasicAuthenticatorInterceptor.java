package com.poptok.android.poptok.service;

import android.util.Log;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HttpBasicAuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {

        Log.d("intercept", String.format("byte length:%d", data.length));
        return execution.execute(request, data);
    }
}