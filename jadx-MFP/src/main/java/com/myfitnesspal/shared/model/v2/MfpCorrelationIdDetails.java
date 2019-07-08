package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;

public class MfpCorrelationIdDetails {
    @Expose
    private String correlationId;
    @Expose
    private String id;
    @Expose
    private String scope;
    @Expose
    private String version;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpCorrelationIdDetails> {
    }

    public MfpCorrelationIdDetails() {
    }

    public MfpCorrelationIdDetails(String str, String str2) {
        this.correlationId = str;
        this.scope = str2;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public void setCorrelationId(String str) {
        this.correlationId = str;
    }

    public String getId() {
        return this.id;
    }

    public String getScope() {
        return this.scope;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }
}
