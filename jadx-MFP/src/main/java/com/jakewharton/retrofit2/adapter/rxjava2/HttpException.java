package com.jakewharton.retrofit2.adapter.rxjava2;

import retrofit2.Response;

public final class HttpException extends Exception {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    private static String getMessage(Response<?> response2) {
        if (response2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP ");
            sb.append(response2.code());
            sb.append(" ");
            sb.append(response2.message());
            return sb.toString();
        }
        throw new NullPointerException("response == null");
    }

    public HttpException(Response<?> response2) {
        super(getMessage(response2));
        this.code = response2.code();
        this.message = response2.message();
        this.response = response2;
    }
}
