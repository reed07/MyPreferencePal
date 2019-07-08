package io.uacf.consentservices.internal.service;

import io.uacf.consentservices.internal.model.Consent;
import io.uacf.consentservices.internal.model.ConsentSelection;
import io.uacf.consentservices.internal.model.ConsentStatus;
import java.util.Map;

public class ConsentValidator {
    public static boolean isConsentStatusValid(ConsentStatus consentStatus) {
        if (!isContentMapEmpty(consentStatus.getContentMap()) && !isConsentSelectionEmpty(consentStatus.getSelections())) {
            return true;
        }
        if (!isContentMapEmpty(consentStatus.getContentMap()) || !isConsentSelectionEmpty(consentStatus.getSelections())) {
            return false;
        }
        return true;
    }

    public static boolean hasConsents(ConsentStatus consentStatus) {
        return !isContentMapEmpty(consentStatus.getContentMap()) && !isConsentSelectionEmpty(consentStatus.getSelections());
    }

    public static boolean isConsentSelectionEmpty(Map<String, ConsentSelection> map) {
        boolean z = true;
        if (map == null || map.isEmpty()) {
            return true;
        }
        if (!isEmpty((ConsentSelection) map.get(ConsentStatus.CONSENT_ACCEPTED)) || !isEmpty((ConsentSelection) map.get(ConsentStatus.CONSENT_UNACCEPTED))) {
            z = false;
        }
        return z;
    }

    private static boolean isContentMapEmpty(Map<String, Consent> map) {
        return map == null || map.isEmpty();
    }

    private static boolean isEmpty(ConsentSelection consentSelection) {
        return (consentSelection.getOptional() == null || consentSelection.getOptional().isEmpty()) && (consentSelection.getTos() == null || consentSelection.getTos().isEmpty()) && (consentSelection.getRequired() == null || consentSelection.getRequired().isEmpty());
    }
}
