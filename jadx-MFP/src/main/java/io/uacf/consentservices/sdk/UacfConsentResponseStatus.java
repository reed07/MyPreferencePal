package io.uacf.consentservices.sdk;

public enum UacfConsentResponseStatus {
    UNDEFINED,
    OK,
    MISSING_REQUIRED,
    NEW_USER,
    AGE_GATE_FAIL,
    MISSING_TOS,
    NEVER_CONSENTED
}
