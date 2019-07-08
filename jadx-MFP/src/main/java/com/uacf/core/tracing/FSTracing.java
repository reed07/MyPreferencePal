package com.uacf.core.tracing;

import android.text.TextUtils;
import io.opentracing.Scope;
import io.uacf.core.api.UacfApiException;

public abstract class FSTracing {

    protected interface FSLog {

        public interface Events {
        }

        public interface Keys {
        }
    }

    protected interface FSTags {
        public static final String CAUSE_MESSAGE = "cause_message";
        public static final String ERROR = "error";
        public static final String ERROR_MESSAGE = "error_message";
        public static final String STATUS_CODE = "status_code";
    }

    public static <T extends Exception> void logException(Scope scope, T t) {
        if (t != null && scope != null && scope.span() != null) {
            scope.span().setTag("error", true);
            if (!TextUtils.isEmpty(t.getMessage())) {
                scope.span().setTag("error_message", t.getMessage());
            }
            if (t.getCause() != null && !TextUtils.isEmpty(t.getCause().getMessage())) {
                scope.span().setTag(FSTags.CAUSE_MESSAGE, t.getCause().getMessage());
            }
            if (t instanceof UacfApiException) {
                int statusCode = ((UacfApiException) t).getStatusCode();
                if (statusCode > 0) {
                    scope.span().setTag(FSTags.STATUS_CODE, String.valueOf(statusCode));
                }
            }
        }
    }

    public static <T extends Exception> void logThenThrowException(Scope scope, T t) throws Exception {
        if (t != null) {
            logException(scope, t);
            throw t;
        }
    }
}
