package io.uacf.core.consents;

import com.uacf.core.mapping.GsonMappableIso8601Date;
import java.util.Map;

public interface UacfUserConsentStatusProvider {
    GsonMappableIso8601Date getAdConsentsLastSeenDate();

    String getConsentMatrixVersion();

    String getIsoCode();

    Map<String, Boolean> getUpdatedConsents();
}
