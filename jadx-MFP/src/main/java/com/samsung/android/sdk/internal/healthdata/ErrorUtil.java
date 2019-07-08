package com.samsung.android.sdk.internal.healthdata;

public final class ErrorUtil {
    public static String getNullArgumentMessage() {
        return "Argument is null";
    }

    public static String getRemoteExceptionMessage(Exception exc) {
        StringBuilder sb = new StringBuilder("A remote-invocation error occurs on the connection: ");
        sb.append(exc.toString());
        return sb.toString();
    }
}
