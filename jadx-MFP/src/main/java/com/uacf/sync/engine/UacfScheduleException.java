package com.uacf.sync.engine;

public class UacfScheduleException extends Exception {
    private final int statusCode;

    public static class UacfScheduleFailedException extends UacfScheduleException {
        public UacfScheduleFailedException(int i, String str) {
            super(i, str);
        }
    }

    public UacfScheduleException(Throwable th) {
        this(th, -1, "");
    }

    public UacfScheduleException(int i, String str) {
        super(str);
        this.statusCode = i;
    }

    public UacfScheduleException(Throwable th, int i, String str) {
        super(str, th);
        this.statusCode = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return getMessage();
    }
}
