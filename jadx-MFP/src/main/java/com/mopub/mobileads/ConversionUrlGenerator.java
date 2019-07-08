package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.appevents.UserDataStore;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;

class ConversionUrlGenerator extends BaseUrlGenerator {
    @Nullable
    private String mConsentedPrivacyPolicyVersion;
    @Nullable
    private String mConsentedVendorListVersion;
    @NonNull
    private Context mContext;
    @Nullable
    private String mCurrentConsentStatus;
    private boolean mForceGdprApplies;
    @Nullable
    private Boolean mGdprApplies;
    private boolean mSt;

    ConversionUrlGenerator(@NonNull Context context) {
        this.mContext = context;
    }

    public ConversionUrlGenerator withCurrentConsentStatus(@Nullable String str) {
        this.mCurrentConsentStatus = str;
        return this;
    }

    public ConversionUrlGenerator withGdprApplies(@Nullable Boolean bool) {
        this.mGdprApplies = bool;
        return this;
    }

    public ConversionUrlGenerator withForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
        return this;
    }

    public ConversionUrlGenerator withConsentedVendorListVersion(@Nullable String str) {
        this.mConsentedVendorListVersion = str;
        return this;
    }

    public ConversionUrlGenerator withConsentedPrivacyPolicyVersion(@Nullable String str) {
        this.mConsentedPrivacyPolicyVersion = str;
        return this;
    }

    public ConversionUrlGenerator withSessionTracker(boolean z) {
        this.mSt = z;
        return this;
    }

    public String generateUrlString(String str) {
        ClientMetadata instance = ClientMetadata.getInstance(this.mContext);
        initUrlString(str, Constants.CONVERSION_TRACKING_HANDLER);
        setApiVersion("6");
        setAppVersion(instance.getAppVersion());
        appendAdvertisingInfoTemplates();
        addParam("id", this.mContext.getPackageName());
        if (this.mSt) {
            addParam(UserDataStore.STATE, Boolean.valueOf(true));
        }
        addParam("nv", "5.4.1");
        addParam("current_consent_status", this.mCurrentConsentStatus);
        addParam("consented_vendor_list_version", this.mConsentedVendorListVersion);
        addParam("consented_privacy_policy_version", this.mConsentedPrivacyPolicyVersion);
        addParam("gdpr_applies", this.mGdprApplies);
        addParam("force_gdpr_applies", Boolean.valueOf(this.mForceGdprApplies));
        return getFinalUrlString();
    }
}
