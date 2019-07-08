package io.uacf.consentservices.internal.service;

import io.uacf.consentservices.internal.model.ConsentApiParams;
import io.uacf.consentservices.internal.model.ConsentStatus;
import io.uacf.core.api.UacfApiException;

public interface ConsentService {
    ConsentStatus getConsentStatus(ConsentApiParams consentApiParams) throws UacfApiException;
}
