package com.darktech.wallet.splash.view;

import com.darktech.wallet.AuthResponse;

public interface VerifyView {
    void onVerifySuccess(AuthResponse response);
    void onVerifyError(String message);
}
