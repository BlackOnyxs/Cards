package com.darktech.wallet.api;

public class ApiManager {

    private static AuthService authService;
    public static AuthService getAuthService() {
        if (authService == null) {
            authService = ApiClient.getClient().create(AuthService.class);
        }
        return authService;
    }
}
