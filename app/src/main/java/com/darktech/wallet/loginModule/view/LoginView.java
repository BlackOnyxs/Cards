package com.darktech.wallet.loginModule.view;

public interface LoginView {
    void toggleProgress();
    void onLoginSuccess(String token);
    void onLoginFail(String error);

}
