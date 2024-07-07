package com.darktech.wallet.registerModule;

import com.darktech.wallet.AuthResponse;
import com.darktech.wallet.api.ApiClient;
import com.darktech.wallet.api.AuthService;
import com.darktech.wallet.loginModule.event.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private RegisterView view;
    private AuthService authService;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
        this.authService = ApiClient.getClient().create(AuthService.class);
    }

    public void register(UserDto user) {
        Call<AuthResponse> call = authService.register(user);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    view.onRegisterSuccess(response.body().getToken());
                } else {
                    System.out.print(response);
                    view.onRegisterError(response.toString());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                view.onRegisterError(t.getMessage());
            }
        });
    }


}
