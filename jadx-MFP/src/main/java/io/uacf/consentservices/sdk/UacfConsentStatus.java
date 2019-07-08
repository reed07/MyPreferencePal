package io.uacf.consentservices.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import io.uacf.consentservices.internal.model.Consent;
import io.uacf.core.api.UacfApiException;
import java.util.ArrayList;
import java.util.List;

public class UacfConsentStatus implements Parcelable, UacfConsentsProvider {
    public static final Creator<UacfConsentStatus> CREATOR = new Creator<UacfConsentStatus>() {
        public UacfConsentStatus createFromParcel(Parcel parcel) {
            return new UacfConsentStatus(parcel);
        }

        public UacfConsentStatus[] newArray(int i) {
            return new UacfConsentStatus[i];
        }
    };
    private GsonMappableIso8601Date adConsentsLastSeenDate;
    private String consentMatrixVersion;
    private String consentStandard;
    private List<UacfConsent> consents;
    private String gdprIsoCode;
    private boolean hasAccepted;
    private UacfConsent tos;
    private UacfConsentResponseStatus uacfConsentResponseStatus;

    static class Builder {
        private GsonMappableIso8601Date adConsentsLastSeen;
        private String consentMatrixVersion;
        private String consentStandard;
        private List<UacfConsent> consents;
        private String gdprIsoCode;
        private boolean hasAccepted;
        private UacfConsent tos;
        private UacfConsentResponseStatus uacfConsentResponseStatus;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public Builder setConsentMatrixVersion(String str) throws UacfApiException {
            if (!TextUtils.isEmpty(str)) {
                this.consentMatrixVersion = str;
                return this;
            }
            throw new UacfApiException("Error parsing content url");
        }

        /* access modifiers changed from: 0000 */
        public Builder setGdprIsoCode(String str) throws UacfApiException {
            if (!TextUtils.isEmpty(str)) {
                this.gdprIsoCode = str;
                return this;
            }
            throw new UacfApiException("Error parsing content url");
        }

        /* access modifiers changed from: 0000 */
        public Builder setAdConsentsLastSeen(GsonMappableIso8601Date gsonMappableIso8601Date) {
            this.adConsentsLastSeen = gsonMappableIso8601Date;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setTos(Consent consent) throws UacfApiException {
            if (consent != null) {
                this.tos = new Builder().setId(consent.getId()).setTitle(consent.getTitle()).setContentSummary(consent.getContentSummary()).setContentUrl(consent.getContentUrl()).setIsRequired(consent.isRequired()).setIsAccepted(consent.isAccepted()).build();
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setConsents(List<Consent> list) throws UacfApiException {
            this.consents = new ArrayList();
            for (Consent consent : list) {
                if (consent != null) {
                    this.consents.add(new Builder().setId(consent.getId()).setTitle(consent.getTitle()).setContentSummary(consent.getContentSummary()).setContentUrl(consent.getContentUrl()).setIsRequired(consent.isRequired()).setIsAccepted(consent.isAccepted()).build());
                } else {
                    throw new UacfApiException("Error parsing response");
                }
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setHasAccepted(boolean z) {
            this.hasAccepted = z;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setUacfConsentResponseStatus(UacfConsentResponseStatus uacfConsentResponseStatus2) {
            this.uacfConsentResponseStatus = uacfConsentResponseStatus2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setConsentStandard(String str) {
            this.consentStandard = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public UacfConsentStatus build() {
            UacfConsentStatus uacfConsentStatus = new UacfConsentStatus(this.consentMatrixVersion, this.gdprIsoCode, this.hasAccepted, this.adConsentsLastSeen, this.tos, this.consents, this.uacfConsentResponseStatus, this.consentStandard);
            return uacfConsentStatus;
        }
    }

    public int describeContents() {
        return 0;
    }

    private UacfConsentStatus(String str, String str2, boolean z, GsonMappableIso8601Date gsonMappableIso8601Date, UacfConsent uacfConsent, List<UacfConsent> list, UacfConsentResponseStatus uacfConsentResponseStatus2, String str3) {
        this.consentMatrixVersion = str;
        this.gdprIsoCode = str2;
        this.adConsentsLastSeenDate = gsonMappableIso8601Date;
        this.hasAccepted = z;
        this.consents = list;
        this.tos = uacfConsent;
        this.uacfConsentResponseStatus = uacfConsentResponseStatus2;
        this.consentStandard = str3;
    }

    protected UacfConsentStatus(Parcel parcel) {
        this.consentMatrixVersion = parcel.readString();
        this.hasAccepted = parcel.readByte() != 0;
        this.adConsentsLastSeenDate = (GsonMappableIso8601Date) parcel.readSerializable();
        this.gdprIsoCode = parcel.readString();
        this.consentStandard = parcel.readString();
        this.tos = (UacfConsent) parcel.readParcelable(UacfConsent.class.getClassLoader());
        this.uacfConsentResponseStatus = (UacfConsentResponseStatus) parcel.readSerializable();
        this.consents = new ArrayList();
        parcel.readList(this.consents, UacfConsent.class.getClassLoader());
    }

    public String getConsentMatrixVersion() {
        return this.consentMatrixVersion;
    }

    public List<UacfConsent> getConsents() {
        return this.consents;
    }

    public UacfConsent getTos() {
        return this.tos;
    }

    public String getGdprIsoCode() {
        return this.gdprIsoCode;
    }

    public GsonMappableIso8601Date getAdConsentsLastSeenDate() {
        return this.adConsentsLastSeenDate;
    }

    public UacfConsentResponseStatus getUacfConsentResponseStatus() {
        return this.uacfConsentResponseStatus;
    }

    public String getConsentStandard() {
        return this.consentStandard;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.consentMatrixVersion);
        parcel.writeByte(this.hasAccepted ? (byte) 1 : 0);
        parcel.writeSerializable(this.adConsentsLastSeenDate);
        parcel.writeString(this.gdprIsoCode);
        parcel.writeString(this.consentStandard);
        parcel.writeParcelable(this.tos, 0);
        parcel.writeSerializable(this.uacfConsentResponseStatus);
        parcel.writeList(this.consents);
    }
}
