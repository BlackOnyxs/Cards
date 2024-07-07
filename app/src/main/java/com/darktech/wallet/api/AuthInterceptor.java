package com.darktech.wallet.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
public class AuthInterceptor implements Interceptor {
    private String token;

    public AuthInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalUrl = originalRequest.url();

        HttpUrl urlWithToken = originalUrl.newBuilder()
                .addQueryParameter("token", this.token)
                .build();

        Request requestWithToken = originalRequest.newBuilder()
                .url(urlWithToken)
                .build();

        return chain.proceed(requestWithToken);
    }
}
