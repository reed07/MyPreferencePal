package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.Map;

public class IdmAccountLink {
    @SerializedName("ad_consents_last_seen")
    @Expose
    private GsonMappableIso8601Date adConsentsLastSeen;
    @SerializedName("consent_matrix_version")
    @Expose
    private String consentMatrixVersion;
    @Expose
    private UacfUserAccountDomain domain;
    @Expose
    private String domainUserId;
    @SerializedName("consents")
    @Expose
    private Map<String, Boolean> idmConsentMap;

    public UacfUserAccountDomain getDomain() {
        return this.domain;
    }

    public IdmAccountLink setDomain(UacfUserAccountDomain uacfUserAccountDomain) {
        this.domain = uacfUserAccountDomain;
        return this;
    }

    public String getDomainUserId() {
        return this.domainUserId;
    }

    public IdmAccountLink setDomainUserId(String str) {
        this.domainUserId = str;
        return this;
    }

    public Map<String, Boolean> getIdmConsentMap() {
        return this.idmConsentMap;
    }

    public IdmAccountLink setIdmConsentMap(Map<String, Boolean> map) {
        this.idmConsentMap = map;
        return this;
    }

    public String getConsentMatrixVersion() {
        return this.consentMatrixVersion;
    }

    public IdmAccountLink setConsentMatrixVersion(String str) {
        this.consentMatrixVersion = str;
        return this;
    }

    public GsonMappableIso8601Date getAdConsentsLastSeen() {
        return this.adConsentsLastSeen;
    }

    public IdmAccountLink setAdConsentsLastSeen(GsonMappableIso8601Date gsonMappableIso8601Date) {
        this.adConsentsLastSeen = gsonMappableIso8601Date;
        return this;
    }
}
