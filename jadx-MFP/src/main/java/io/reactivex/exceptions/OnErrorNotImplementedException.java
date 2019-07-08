package io.reactivex.exceptions;

import io.reactivex.annotations.NonNull;

public final class OnErrorNotImplementedException extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public OnErrorNotImplementedException(String str, @NonNull Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(str, th);
    }

    public OnErrorNotImplementedException(@NonNull Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | ");
        sb.append(th != null ? th.getMessage() : "");
        this(sb.toString(), th);
    }
}
