package io.uacf.consentservices.sdk;

import com.uacf.core.mapping.GsonMappableIso8601Date;
import java.util.List;

public interface UacfConsentsProvider {
    GsonMappableIso8601Date getAdConsentsLastSeenDate();

    String getConsentMatrixVersion();

    List<UacfConsent> getConsents();

    String getGdprIsoCode();

    UacfConsent getTos();
}
