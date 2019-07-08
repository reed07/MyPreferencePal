package io.uacf.consentservices.sdk;

import io.uacf.core.api.UacfApiException;
import io.uacf.core.consents.UacfUserConsentStatusProvider;

public interface UacfConsentServiceSdk {
    UacfConsentStatus getConsentStatus(UacfConsentIsoCodeProvider uacfConsentIsoCodeProvider) throws UacfApiException;

    UacfUserConsentStatusProvider getUacfUserConsentStatus(UacfConsentsProvider uacfConsentsProvider);

    UacfConsentResponseStatus getUserConsentStatus(UacfConsentIsoCodeProvider uacfConsentIsoCodeProvider) throws UacfApiException;
}
