package com.darktech.wallet.common.utils;

import com.darktech.wallet.common.pojo.User;

public class UserSingleton {
    private static final UserSingleton instance = new UserSingleton();

    private User user;
    private boolean successUpdate;

    private UserSingleton() {}

    public static UserSingleton getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User currentUser) {
        this.user = currentUser;
    }

    public void updateUser(User updatedUser) {
        this.user = updatedUser;
        this.successUpdate = true;
    }

    public boolean isSuccessUpdate() {
        return successUpdate;
    }

    public void reset() {
        user = null;
    }
}

