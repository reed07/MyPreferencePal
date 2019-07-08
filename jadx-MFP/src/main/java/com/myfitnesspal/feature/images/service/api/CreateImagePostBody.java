package com.myfitnesspal.feature.images.service.api;

import com.google.gson.annotations.Expose;

public class CreateImagePostBody {
    @Expose
    private final String checksum;
    @Expose
    private final String userId;

    public CreateImagePostBody(String str, String str2) {
        this.checksum = str;
        this.userId = str2;
    }
}
