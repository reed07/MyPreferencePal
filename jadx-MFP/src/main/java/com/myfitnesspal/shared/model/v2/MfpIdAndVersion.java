package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;

public class MfpIdAndVersion {
    @Expose
    private String id;
    @Expose
    private String version;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpIdAndVersion> {
    }

    public String getId() {
        return this.id;
    }

    public String getVersion() {
        return this.version;
    }
}
