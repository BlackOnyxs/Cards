package com.darktech.wallet.loginModule.event;

public class LoginRequest {
    private String password;
    private String email;

    public LoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
