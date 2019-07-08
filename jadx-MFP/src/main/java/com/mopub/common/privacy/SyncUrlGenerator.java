package com.mopub.common.privacy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.network.PlayServicesUrlRewriter;

public class SyncUrlGenerator extends BaseUrlGenerator {
    @Nullable
    private String mAdUnitId;
    @Nullable
    private String mCachedVendorListIabHash;
    @Nullable
    private String mConsentChangeReason;
    @Nullable
    private String mConsentedPrivacyPolicyVersion;
    @Nullable
    private String mConsentedVendorListVersion;
    @NonNull
    private final Context mContext;
    @NonNull
    private final String mCurrentConsentStatus;
    @Nullable
    private String mExtras;
    private boolean mForceGdprApplies;
    @Nullable
    private Boolean mForceGdprAppliesChanged;
    @Nullable
    private Boolean mGdprApplies;
    @Nullable
    private String mLastChangedMs;
    @Nullable
    private String mLastConsentStatus;
    @Nullable
    private String mUdid;

    public SyncUrlGenerator(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        this.mContext = context.getApplicationContext();
        this.mCurrentConsentStatus = str;
    }

    public SyncUrlGenerator withAdUnitId(@Nullable String str) {
        this.mAdUnitId = str;
        return this;
    }

    public SyncUrlGenerator withUdid(@Nullable String str) {
        this.mUdid = str;
        return this;
    }

    public SyncUrlGenerator withGdprApplies(@Nullable Boolean bool) {
        this.mGdprApplies = bool;
        return this;
    }

    public SyncUrlGenerator withForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
        return this;
    }

    public SyncUrlGenerator withForceGdprAppliesChanged(@Nullable Boolean bool) {
        this.mForceGdprAppliesChanged = bool;
        return this;
    }

    public SyncUrlGenerator withLastChangedMs(@Nullable String str) {
        this.mLastChangedMs = str;
        return this;
    }

    public SyncUrlGenerator withLastConsentStatus(@Nullable ConsentStatus consentStatus) {
        this.mLastConsentStatus = consentStatus == null ? null : consentStatus.getValue();
        return this;
    }

    public SyncUrlGenerator withConsentChangeReason(@Nullable String str) {
        this.mConsentChangeReason = str;
        return this;
    }

    public SyncUrlGenerator withConsentedVendorListVersion(@Nullable String str) {
        this.mConsentedVendorListVersion = str;
        return this;
    }

    public SyncUrlGenerator withConsentedPrivacyPolicyVersion(@Nullable String str) {
        this.mConsentedPrivacyPolicyVersion = str;
        return this;
    }

    public SyncUrlGenerator withCachedVendorListIabHash(@Nullable String str) {
        this.mCachedVendorListIabHash = str;
        return this;
    }

    public SyncUrlGenerator withExtras(@Nullable String str) {
        this.mExtras = str;
        return this;
    }

    public String generateUrlString(@NonNull String str) {
        initUrlString(str, Constants.GDPR_SYNC_HANDLER);
        addParam("id", this.mAdUnitId);
        addParam("nv", "5.4.1");
        addParam("last_changed_ms", this.mLastChangedMs);
        addParam("last_consent_status", this.mLastConsentStatus);
        addParam("current_consent_status", this.mCurrentConsentStatus);
        addParam("consent_change_reason", this.mConsentChangeReason);
        addParam("consented_vendor_list_version", this.mConsentedVendorListVersion);
        addParam("consented_privacy_policy_version", this.mConsentedPrivacyPolicyVersion);
        addParam("cached_vendor_list_iab_hash", this.mCachedVendorListIabHash);
        addParam("extras", this.mExtras);
        addParam("udid", this.mUdid);
        addParam("gdpr_applies", this.mGdprApplies);
        addParam("force_gdpr_applies", Boolean.valueOf(this.mForceGdprApplies));
        addParam("forced_gdpr_applies_changed", this.mForceGdprAppliesChanged);
        addParam("bundle", ClientMetadata.getInstance(this.mContext).getAppPackageName());
        addParam("dnt", PlayServicesUrlRewriter.DO_NOT_TRACK_TEMPLATE);
        return getFinalUrlString();
    }
}
