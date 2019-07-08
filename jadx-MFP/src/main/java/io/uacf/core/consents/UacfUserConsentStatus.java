package io.uacf.core.consents;

import android.support.annotation.NonNull;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import java.util.HashMap;
import java.util.Map;

public class UacfUserConsentStatus implements UacfUserConsentStatusProvider {
    private GsonMappableIso8601Date adConsentsLastSeenDate;
    private String consentMatrixVersion;
    private String isoCode;
    private Map<String, Boolean> updatedConsents = new HashMap();

    public void setConsentStatus(@NonNull String str, @NonNull boolean z) {
        this.updatedConsents.put(str, Boolean.valueOf(z));
    }

    public String getConsentMatrixVersion() {
        return this.consentMatrixVersion;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public GsonMappableIso8601Date getAdConsentsLastSeenDate() {
        return this.adConsentsLastSeenDate;
    }

    public Map<String, Boolean> getUpdatedConsents() {
        return this.updatedConsents;
    }

    public void setIsoCode(String str) {
        this.isoCode = str;
    }

    public void setConsentMatrixVersion(String str) {
        this.consentMatrixVersion = str;
    }

    public void setAdConsentsLastSeenDate(GsonMappableIso8601Date gsonMappableIso8601Date) {
        this.adConsentsLastSeenDate = gsonMappableIso8601Date;
    }
}
