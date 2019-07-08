package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdmUpdateUserPasswordRequest {
    @SerializedName("password")
    @Expose
    private String password;

    public IdmUpdateUserPasswordRequest(String str) {
        this.password = str;
    }
}
