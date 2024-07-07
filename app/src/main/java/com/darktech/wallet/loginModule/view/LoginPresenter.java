package com.darktech.wallet.loginModule.view;

import android.util.Log;

import com.darktech.wallet.AuthResponse;
import com.darktech.wallet.api.ApiManager;
import com.darktech.wallet.api.AuthService;
import com.darktech.wallet.loginModule.event.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView view;
    private AuthService authService;

    public LoginPresenter(LoginView loginView){
        view = loginView;
        authService = ApiManager.getAuthService();
    }

    public void login(LoginRequest request){
        Log.d("Request", request.toString());
        Call<AuthResponse> call =  authService.login(request);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.d("Response", response.toString());
                if (response.isSuccessful()){
                    view.onLoginSuccess(response.body().getToken());
                }else{
                    view.onLoginFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                view.onLoginFail(t.getMessage());
            }
        });
    }

}
