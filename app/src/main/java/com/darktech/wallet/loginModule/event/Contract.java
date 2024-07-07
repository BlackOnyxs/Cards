package com.darktech.wallet.loginModule.event;

public interface Contract {
    interface View {
        void showLoading(boolean isLoading);
        void showError(String errorMessage);
        void  navigateToMain();
    }

    interface Presenter {
        void loginUser(String username, String password);
    }
}
