package com.interndemosss.hackathonassignment.login;

import com.google.gson.annotations.SerializedName;

public class loginModel {
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("message")
    private String message;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public loginModel(int user_id, String message) {
        this.user_id = user_id;
        this.message = message;
    }
}
