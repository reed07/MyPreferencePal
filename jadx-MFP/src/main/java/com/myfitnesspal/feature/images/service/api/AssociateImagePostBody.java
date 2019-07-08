package com.myfitnesspal.feature.images.service.api;

import com.google.gson.annotations.Expose;

public class AssociateImagePostBody {
    @Expose
    private String id;
    @Expose
    private String imageId;
    @Expose
    private String resourceId;
    @Expose
    private String resourceType;
    @Expose
    private String userId;

    public AssociateImagePostBody(String str, String str2, String str3, String str4, String str5) {
        this.id = str;
        this.userId = str2;
        this.imageId = str3;
        this.resourceType = str4;
        this.resourceId = str5;
    }
}
