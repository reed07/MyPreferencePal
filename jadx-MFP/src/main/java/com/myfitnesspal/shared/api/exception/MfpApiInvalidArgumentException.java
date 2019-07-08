package com.myfitnesspal.shared.api.exception;

import com.myfitnesspal.shared.api.ApiException;

public class MfpApiInvalidArgumentException extends ApiException {
    public MfpApiInvalidArgumentException(String str, String str2, Object obj) {
        super(String.format("Invalid Parameter for method %s: %s = %s", new Object[]{str, str2, obj}), -1);
    }
}
