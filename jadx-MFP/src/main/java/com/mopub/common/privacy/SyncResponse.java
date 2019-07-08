package com.mopub.common.privacy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.Preconditions;

public class SyncResponse {
    @Nullable
    private final String mCallAgainAfterSecs;
    @Nullable
    private final String mConsentChangeReason;
    @NonNull
    private final String mCurrentPrivacyPolicyLink;
    @NonNull
    private final String mCurrentPrivacyPolicyVersion;
    @Nullable
    private final String mCurrentVendorListIabFormat;
    @NonNull
    private final String mCurrentVendorListIabHash;
    @NonNull
    private final String mCurrentVendorListLink;
    @NonNull
    private final String mCurrentVendorListVersion;
    @Nullable
    private final String mExtras;
    private final boolean mForceExplicitNo;
    private final boolean mForceGdprApplies;
    private final boolean mInvalidateConsent;
    private final boolean mIsGdprRegion;
    private final boolean mIsWhitelisted;
    private final boolean mReacquireConsent;

    public static class Builder {
        private String callAgainAfterSecs;
        private String consentChangeReason;
        private String currentPrivacyPolicyLink;
        private String currentPrivacyPolicyVersion;
        private String currentVendorListIabFormat;
        private String currentVendorListIabHash;
        private String currentVendorListLink;
        private String currentVendorListVersion;
        private String extras;
        private String forceExplicitNo;
        private String forceGdprApplies;
        private String invalidateConsent;
        private String isGdprRegion;
        private String isWhitelisted;
        private String reacquireConsent;

        public Builder setIsGdprRegion(@NonNull String str) {
            this.isGdprRegion = str;
            return this;
        }

        public Builder setForceExplicitNo(@Nullable String str) {
            this.forceExplicitNo = str;
            return this;
        }

        public Builder setInvalidateConsent(@Nullable String str) {
            this.invalidateConsent = str;
            return this;
        }

        public Builder setReacquireConsent(@Nullable String str) {
            this.reacquireConsent = str;
            return this;
        }

        public Builder setIsWhitelisted(@NonNull String str) {
            this.isWhitelisted = str;
            return this;
        }

        public Builder setForceGdprApplies(@Nullable String str) {
            this.forceGdprApplies = str;
            return this;
        }

        public Builder setCurrentVendorListVersion(@NonNull String str) {
            this.currentVendorListVersion = str;
            return this;
        }

        public Builder setCurrentVendorListLink(@NonNull String str) {
            this.currentVendorListLink = str;
            return this;
        }

        public Builder setCurrentPrivacyPolicyVersion(@NonNull String str) {
            this.currentPrivacyPolicyVersion = str;
            return this;
        }

        public Builder setCurrentPrivacyPolicyLink(@NonNull String str) {
            this.currentPrivacyPolicyLink = str;
            return this;
        }

        public Builder setCurrentVendorListIabFormat(@Nullable String str) {
            this.currentVendorListIabFormat = str;
            return this;
        }

        public Builder setCurrentVendorListIabHash(@NonNull String str) {
            this.currentVendorListIabHash = str;
            return this;
        }

        public Builder setCallAgainAfterSecs(@Nullable String str) {
            this.callAgainAfterSecs = str;
            return this;
        }

        public Builder setExtras(@Nullable String str) {
            this.extras = str;
            return this;
        }

        public Builder setConsentChangeReason(@Nullable String str) {
            this.consentChangeReason = str;
            return this;
        }

        public SyncResponse build() {
            SyncResponse syncResponse = new SyncResponse(this.isGdprRegion, this.forceExplicitNo, this.invalidateConsent, this.reacquireConsent, this.isWhitelisted, this.forceGdprApplies, this.currentVendorListVersion, this.currentVendorListLink, this.currentPrivacyPolicyVersion, this.currentPrivacyPolicyLink, this.currentVendorListIabFormat, this.currentVendorListIabHash, this.callAgainAfterSecs, this.extras, this.consentChangeReason);
            return syncResponse;
        }
    }

    public boolean isGdprRegion() {
        return this.mIsGdprRegion;
    }

    public boolean isForceExplicitNo() {
        return this.mForceExplicitNo;
    }

    public boolean isInvalidateConsent() {
        return this.mInvalidateConsent;
    }

    public boolean isReacquireConsent() {
        return this.mReacquireConsent;
    }

    public boolean isWhitelisted() {
        return this.mIsWhitelisted;
    }

    public boolean isForceGdprApplies() {
        return this.mForceGdprApplies;
    }

    @NonNull
    public String getCurrentVendorListVersion() {
        return this.mCurrentVendorListVersion;
    }

    @NonNull
    public String getCurrentVendorListLink() {
        return this.mCurrentVendorListLink;
    }

    @NonNull
    public String getCurrentPrivacyPolicyVersion() {
        return this.mCurrentPrivacyPolicyVersion;
    }

    @NonNull
    public String getCurrentPrivacyPolicyLink() {
        return this.mCurrentPrivacyPolicyLink;
    }

    @Nullable
    public String getCurrentVendorListIabFormat() {
        return this.mCurrentVendorListIabFormat;
    }

    @NonNull
    public String getCurrentVendorListIabHash() {
        return this.mCurrentVendorListIabHash;
    }

    @Nullable
    public String getCallAgainAfterSecs() {
        return this.mCallAgainAfterSecs;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getExtras() {
        return this.mExtras;
    }

    @Nullable
    public String getConsentChangeReason() {
        return this.mConsentChangeReason;
    }

    private SyncResponse(@NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @NonNull String str5, @Nullable String str6, @NonNull String str7, @NonNull String str8, @NonNull String str9, @NonNull String str10, @Nullable String str11, @NonNull String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str5);
        Preconditions.checkNotNull(str7);
        Preconditions.checkNotNull(str8);
        Preconditions.checkNotNull(str9);
        Preconditions.checkNotNull(str10);
        Preconditions.checkNotNull(str12);
        String str16 = str;
        this.mIsGdprRegion = !"0".equals(str);
        String str17 = str2;
        this.mForceExplicitNo = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str2);
        String str18 = str3;
        this.mInvalidateConsent = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str3);
        String str19 = str4;
        this.mReacquireConsent = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str4);
        String str20 = str5;
        this.mIsWhitelisted = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str5);
        String str21 = str6;
        this.mForceGdprApplies = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str6);
        this.mCurrentVendorListVersion = str7;
        this.mCurrentVendorListLink = str8;
        this.mCurrentPrivacyPolicyVersion = str9;
        this.mCurrentPrivacyPolicyLink = str10;
        this.mCurrentVendorListIabFormat = str11;
        this.mCurrentVendorListIabHash = str12;
        this.mCallAgainAfterSecs = str13;
        this.mExtras = str14;
        this.mConsentChangeReason = str15;
    }
}
