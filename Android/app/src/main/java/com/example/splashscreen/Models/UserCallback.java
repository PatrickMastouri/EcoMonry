package com.example.splashscreen.Models;

import com.example.splashscreen.Models.user;

import java.util.List;

public interface UserCallback {
    void onSuccessUser(user user);
    void onSuccessUsers(List<user> users);
    void onSuccessLogin(String msg);

}
