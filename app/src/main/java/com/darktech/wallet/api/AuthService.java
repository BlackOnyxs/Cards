package com.darktech.wallet.api;

import com.darktech.wallet.AuthResponse;
import com.darktech.wallet.loginModule.event.LoginRequest;
import com.darktech.wallet.loginModule.event.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/register")
    Call<AuthResponse> register(@Body UserDto user);

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);
    @GET("auth/verify")
    Call<AuthResponse> verify();
}
