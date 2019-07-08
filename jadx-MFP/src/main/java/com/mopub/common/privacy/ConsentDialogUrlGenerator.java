package com.mopub.common.privacy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;

public class ConsentDialogUrlGenerator extends BaseUrlGenerator {
    @NonNull
    private final String mAdUnitId;
    @Nullable
    private String mConsentedPrivacyPolicyVersion;
    @Nullable
    private String mConsentedVendorListVersion;
    @NonNull
    private final Context mContext;
    @NonNull
    private final String mCurrentConsentStatus;
    private boolean mForceGdprApplies;
    @Nullable
    private Boolean mGdprApplies;

    ConsentDialogUrlGenerator(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mContext = context.getApplicationContext();
        this.mAdUnitId = str;
        this.mCurrentConsentStatus = str2;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withGdprApplies(@Nullable Boolean bool) {
        this.mGdprApplies = bool;
        return this;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
        return this;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withConsentedVendorListVersion(@Nullable String str) {
        this.mConsentedVendorListVersion = str;
        return this;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withConsentedPrivacyPolicyVersion(@Nullable String str) {
        this.mConsentedPrivacyPolicyVersion = str;
        return this;
    }

    public String generateUrlString(String str) {
        initUrlString(str, Constants.GDPR_CONSENT_HANDLER);
        addParam("id", this.mAdUnitId);
        addParam("current_consent_status", this.mCurrentConsentStatus);
        addParam("nv", "5.4.1");
        addParam("language", ClientMetadata.getCurrentLanguage(this.mContext));
        addParam("gdpr_applies", this.mGdprApplies);
        addParam("force_gdpr_applies", Boolean.valueOf(this.mForceGdprApplies));
        addParam("consented_vendor_list_version", this.mConsentedVendorListVersion);
        addParam("consented_privacy_policy_version", this.mConsentedPrivacyPolicyVersion);
        addParam("bundle", ClientMetadata.getInstance(this.mContext).getAppPackageName());
        return getFinalUrlString();
    }
}
