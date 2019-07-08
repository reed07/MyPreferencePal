package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;

public class MfpUrl {
    @Expose
    private String url;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpUrl> {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
