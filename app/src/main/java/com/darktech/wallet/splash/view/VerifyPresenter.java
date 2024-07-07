package com.darktech.wallet.splash.view;


import android.util.Log;

import com.darktech.wallet.AuthResponse;
import com.darktech.wallet.api.ApiClient;
import com.darktech.wallet.api.ApiManager;
import com.darktech.wallet.api.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPresenter {
    private VerifyView view;
    private AuthService authService;

    public VerifyPresenter(VerifyView verifyView){
        view = verifyView;
    }

    public void verify(String token){
        Log.d("Token", token);
        authService = ApiClient.getClient(token).create(AuthService.class);
        Call<AuthResponse> call = authService.verify();
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.d("Response-Verify", response.toString());
                if (response.isSuccessful()){
                    view.onVerifySuccess(response.body());
                }else{
                    view.onVerifyError("Error Login");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                view.onVerifyError(t.getMessage());
            }
        });
    }
}
