package com.myfitnesspal.shared.api;

import com.myfitnesspal.shared.api.v2.MfpV2ApiError;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.core.api.UacfApiException;
import java.io.IOException;

public class ApiException extends IOException {
    private MfpV2ApiError apiError;
    private String body;
    private int statusCode;

    public ApiException(UacfApiException uacfApiException) {
        this(uacfApiException.getMessage(), (Throwable) uacfApiException, uacfApiException.getStatusCode());
    }

    public ApiException(String str, int i) {
        super(str);
        this.statusCode = i;
    }

    public ApiException(String str, Throwable th, int i) {
        super(str);
        initCause(th);
        this.statusCode = i;
    }

    public ApiException(Throwable th, int i) {
        initCause(th);
        this.statusCode = i;
        if (th instanceof ApiException) {
            ApiException apiException = (ApiException) th;
            if (Strings.notEmpty(apiException.body)) {
                this.body = apiException.body;
            }
            if (apiException.getApiError() != null) {
                this.apiError = apiException.getApiError();
            }
        }
    }

    public ApiException(String str, int i, String str2) {
        super(str);
        this.statusCode = i;
        this.body = str2;
        attemptMapApiError();
    }

    private void attemptMapApiError() {
        if (Strings.notEmpty(this.body)) {
            this.apiError = (MfpV2ApiError) new ApiJsonMapper().withType(MfpV2ApiError.class).tryMapFrom(this.body);
            if (this.apiError == null) {
                Ln.e("Could not map MfpApiError from body %s", this.body);
            }
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getBody() {
        return this.body;
    }

    public MfpV2ApiError getApiError() {
        return this.apiError;
    }
}
