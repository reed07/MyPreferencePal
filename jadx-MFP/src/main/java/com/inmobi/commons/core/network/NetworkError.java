package com.inmobi.commons.core.network;

import com.facebook.stetho.server.http.HttpStatus;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;

public final class NetworkError {
    public ErrorCode a;
    public String b;

    public enum ErrorCode {
        NETWORK_UNAVAILABLE_ERROR(0),
        UNKNOWN_ERROR(-1),
        NETWORK_IO_ERROR(-2),
        OUT_OF_MEMORY_ERROR(-3),
        INVALID_ENCRYPTED_RESPONSE_RECEIVED(-4),
        RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT(-5),
        GZIP_DECOMPRESSION_FAILED(-6),
        BAD_REQUEST(-7),
        GDPR_COMPLIANCE_ENFORCED(-8),
        GENERIC_HTTP_2XX(-9),
        HTTP_NO_CONTENT(RequestCodes.VIEW_FOOD),
        HTTP_NOT_MODIFIED(304),
        HTTP_SEE_OTHER(303),
        HTTP_SERVER_NOT_FOUND(HttpStatus.HTTP_NOT_FOUND),
        HTTP_MOVED_TEMP(302),
        HTTP_INTERNAL_SERVER_ERROR(500),
        HTTP_NOT_IMPLEMENTED(HttpStatus.HTTP_NOT_IMPLEMENTED),
        HTTP_BAD_GATEWAY(502),
        HTTP_SERVER_NOT_AVAILABLE(503),
        HTTP_GATEWAY_TIMEOUT(504),
        HTTP_VERSION_NOT_SUPPORTED(505);
        
        private int a;

        private ErrorCode(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }

        public static ErrorCode fromValue(int i) {
            ErrorCode[] values;
            if (400 <= i && 500 > i) {
                return BAD_REQUEST;
            }
            if (200 < i && 300 > i) {
                return GENERIC_HTTP_2XX;
            }
            for (ErrorCode errorCode : values()) {
                if (errorCode.a == i) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    public NetworkError(ErrorCode errorCode, String str) {
        this.a = errorCode;
        this.b = str;
    }
}
