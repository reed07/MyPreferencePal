package io.uacf.consentservices.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import java.util.List;
import java.util.Map;

public class ConsentStatus {
    public static String CONSENT_ACCEPTED = "accepted";
    public static String CONSENT_UNACCEPTED = "unselected";
    @SerializedName("adConsentsLastSeen")
    @Expose
    private GsonMappableIso8601Date adConsentsLastSeen;
    @SerializedName("consentMatrixVersion")
    @Expose
    private String consentMatrixVersion;
    @SerializedName("standard")
    @Expose
    private String consentStandard;
    private List<Consent> consents;
    @SerializedName("content")
    @Expose
    private Map<String, Consent> contentMap;
    @SerializedName("gdprIsoCode")
    @Expose
    private String gdprIsoCode;
    @SerializedName("hasAccepted")
    @Expose
    private boolean hasAccepted;
    @SerializedName("selections")
    @Expose
    private Map<String, ConsentSelection> selections;
    @Expose
    private Status status;
    private Consent tos;

    public enum Status {
        UNDEFINED,
        OK,
        MISSING_REQUIRED,
        NEW_USER,
        AGE_GATE_FAIL,
        MISSING_TOS,
        NEVER_CONSENTED
    }

    public String getConsentMatrixVersion() {
        return this.consentMatrixVersion;
    }

    public boolean hasAccepted() {
        return this.hasAccepted;
    }

    public Status getStatus() {
        return this.status;
    }

    public Map<String, Consent> getContentMap() {
        return this.contentMap;
    }

    public List<Consent> getConsents() {
        return this.consents;
    }

    public void setConsents(List<Consent> list) {
        this.consents = list;
    }

    public Consent getTos() {
        return this.tos;
    }

    public void setTos(Consent consent) {
        this.tos = consent;
    }

    public String getGdprIsoCode() {
        return this.gdprIsoCode;
    }

    public Map<String, ConsentSelection> getSelections() {
        return this.selections;
    }

    public String getConsentStandard() {
        return this.consentStandard;
    }

    public GsonMappableIso8601Date getAdConsentsLastSeen() {
        return this.adConsentsLastSeen;
    }
}
