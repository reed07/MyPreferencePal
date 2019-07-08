package com.myfitnesspal.feature.registration.exception;

import com.uacf.sync.engine.UacfScheduleException;

public class RegistrationException extends Exception {
    private final RegistrationError reason;

    public RegistrationException(RegistrationError registrationError) {
        this(registrationError, null);
    }

    public RegistrationException(Throwable th) {
        this(RegistrationError.Unknown, th);
    }

    public RegistrationException(RegistrationError registrationError, Throwable th) {
        super(th);
        this.reason = registrationError;
    }

    public UacfScheduleException getSyncException() {
        return (UacfScheduleException) getCause();
    }

    public RegistrationError getReason() {
        return this.reason;
    }
}
