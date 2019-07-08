package com.myfitnesspal.shared.event;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.uacf.core.util.Strings;

public class AlertEvent extends MfpEventWithStatus {
    private boolean asToast;
    private boolean isPositiveButton;

    public AlertEvent(String str) {
        withStatusText(str);
    }

    public AlertEvent(ApiResponseBase apiResponseBase, String str) {
        if (Strings.notEmpty(apiResponseBase.getErrorDescription())) {
            str = apiResponseBase.getErrorDescription();
        }
        this(str);
    }

    public AlertEvent(ApiException apiException, String str) {
        if (Strings.notEmpty(apiException.getApiError().getErrorDescription())) {
            str = apiException.getApiError().getErrorDescription();
        }
        this(str);
    }

    public String getMessage() {
        return getStatusText();
    }

    public void setMessage(String str) {
        withStatusText(str);
    }

    public boolean isToast() {
        return this.asToast;
    }

    public AlertEvent asToast() {
        return asToast(true);
    }

    public AlertEvent asToast(boolean z) {
        this.asToast = z;
        return this;
    }

    public boolean isPositiveButton() {
        return this.isPositiveButton;
    }

    public void setPositiveButton(boolean z) {
        this.isPositiveButton = z;
    }
}
