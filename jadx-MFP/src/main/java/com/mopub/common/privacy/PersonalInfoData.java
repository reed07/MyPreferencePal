package com.mopub.common.privacy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.VisibleForTesting;
import java.util.Locale;

class PersonalInfoData implements ConsentData {
    @NonNull
    private String mAdUnitId;
    @NonNull
    private final Context mAppContext;
    @Nullable
    private String mConsentChangeReason;
    @NonNull
    private ConsentStatus mConsentStatus = ConsentStatus.UNKNOWN;
    @Nullable
    private ConsentStatus mConsentStatusBeforeDnt;
    @Nullable
    private String mConsentedPrivacyPolicyVersion;
    @Nullable
    private String mConsentedVendorListIabFormat;
    @Nullable
    private String mConsentedVendorListVersion;
    @Nullable
    private String mCurrentPrivacyPolicyLink;
    @Nullable
    private String mCurrentPrivacyPolicyVersion;
    @Nullable
    private String mCurrentVendorListIabFormat;
    @Nullable
    private String mCurrentVendorListIabHash;
    @Nullable
    private String mCurrentVendorListLink;
    @Nullable
    private String mCurrentVendorListVersion;
    @Nullable
    private String mExtras;
    private boolean mForceGdprApplies;
    @Nullable
    private Boolean mGdprApplies;
    private boolean mIsWhitelisted;
    @Nullable
    private String mLastChangedMs;
    @Nullable
    private ConsentStatus mLastSuccessfullySyncedConsentStatus;
    private boolean mReacquireConsent;
    @Nullable
    private String mUdid;

    PersonalInfoData(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        this.mAppContext = context.getApplicationContext();
        getStateFromDisk();
        this.mAdUnitId = str;
    }

    private void getStateFromDisk() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(this.mAppContext, "com.mopub.privacy");
        this.mAdUnitId = sharedPreferences.getString("info/adunit", "");
        this.mConsentStatus = ConsentStatus.fromString(sharedPreferences.getString("info/consent_status", ConsentStatus.UNKNOWN.name()));
        String string = sharedPreferences.getString("info/last_successfully_synced_consent_status", null);
        if (TextUtils.isEmpty(string)) {
            this.mLastSuccessfullySyncedConsentStatus = null;
        } else {
            this.mLastSuccessfullySyncedConsentStatus = ConsentStatus.fromString(string);
        }
        this.mIsWhitelisted = sharedPreferences.getBoolean("info/is_whitelisted", false);
        this.mCurrentVendorListVersion = sharedPreferences.getString("info/current_vendor_list_version", null);
        this.mCurrentVendorListLink = sharedPreferences.getString("info/current_vendor_list_link", null);
        this.mCurrentPrivacyPolicyVersion = sharedPreferences.getString("info/current_privacy_policy_version", null);
        this.mCurrentPrivacyPolicyLink = sharedPreferences.getString("info/current_privacy_policy_link", null);
        this.mCurrentVendorListIabFormat = sharedPreferences.getString("info/current_vendor_list_iab_format", null);
        this.mCurrentVendorListIabHash = sharedPreferences.getString("info/current_vendor_list_iab_hash", null);
        this.mConsentedVendorListVersion = sharedPreferences.getString("info/consented_vendor_list_version", null);
        this.mConsentedPrivacyPolicyVersion = sharedPreferences.getString("info/consented_privacy_policy_version", null);
        this.mConsentedVendorListIabFormat = sharedPreferences.getString("info/consented_vendor_list_iab_format", null);
        this.mExtras = sharedPreferences.getString("info/extras", null);
        this.mConsentChangeReason = sharedPreferences.getString("info/consent_change_reason", null);
        this.mReacquireConsent = sharedPreferences.getBoolean("info/reacquire_consent", false);
        String string2 = sharedPreferences.getString("info/gdpr_applies", null);
        if (TextUtils.isEmpty(string2)) {
            this.mGdprApplies = null;
        } else {
            this.mGdprApplies = Boolean.valueOf(Boolean.parseBoolean(string2));
        }
        this.mForceGdprApplies = sharedPreferences.getBoolean("info/force_gdpr_applies", false);
        this.mUdid = sharedPreferences.getString("info/udid", null);
        this.mLastChangedMs = sharedPreferences.getString("info/last_changed_ms", null);
        String string3 = sharedPreferences.getString("info/consent_status_before_dnt", null);
        if (TextUtils.isEmpty(string3)) {
            this.mConsentStatusBeforeDnt = null;
        } else {
            this.mConsentStatusBeforeDnt = ConsentStatus.fromString(string3);
        }
    }

    /* access modifiers changed from: 0000 */
    public void writeToDisk() {
        String str;
        String str2;
        Editor edit = SharedPreferencesHelper.getSharedPreferences(this.mAppContext, "com.mopub.privacy").edit();
        edit.putString("info/adunit", this.mAdUnitId);
        edit.putString("info/consent_status", this.mConsentStatus.name());
        String str3 = "info/last_successfully_synced_consent_status";
        ConsentStatus consentStatus = this.mLastSuccessfullySyncedConsentStatus;
        String str4 = null;
        if (consentStatus == null) {
            str = null;
        } else {
            str = consentStatus.name();
        }
        edit.putString(str3, str);
        edit.putBoolean("info/is_whitelisted", this.mIsWhitelisted);
        edit.putString("info/current_vendor_list_version", this.mCurrentVendorListVersion);
        edit.putString("info/current_vendor_list_link", this.mCurrentVendorListLink);
        edit.putString("info/current_privacy_policy_version", this.mCurrentPrivacyPolicyVersion);
        edit.putString("info/current_privacy_policy_link", this.mCurrentPrivacyPolicyLink);
        edit.putString("info/current_vendor_list_iab_format", this.mCurrentVendorListIabFormat);
        edit.putString("info/current_vendor_list_iab_hash", this.mCurrentVendorListIabHash);
        edit.putString("info/consented_vendor_list_version", this.mConsentedVendorListVersion);
        edit.putString("info/consented_privacy_policy_version", this.mConsentedPrivacyPolicyVersion);
        edit.putString("info/consented_vendor_list_iab_format", this.mConsentedVendorListIabFormat);
        edit.putString("info/extras", this.mExtras);
        edit.putString("info/consent_change_reason", this.mConsentChangeReason);
        edit.putBoolean("info/reacquire_consent", this.mReacquireConsent);
        String str5 = "info/gdpr_applies";
        Boolean bool = this.mGdprApplies;
        if (bool == null) {
            str2 = null;
        } else {
            str2 = bool.toString();
        }
        edit.putString(str5, str2);
        edit.putBoolean("info/force_gdpr_applies", this.mForceGdprApplies);
        edit.putString("info/udid", this.mUdid);
        edit.putString("info/last_changed_ms", this.mLastChangedMs);
        String str6 = "info/consent_status_before_dnt";
        ConsentStatus consentStatus2 = this.mConsentStatusBeforeDnt;
        if (consentStatus2 != null) {
            str4 = consentStatus2.name();
        }
        edit.putString(str6, str4);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public ConsentStatus getConsentStatus() {
        return this.mConsentStatus;
    }

    /* access modifiers changed from: 0000 */
    public void setConsentStatus(@NonNull ConsentStatus consentStatus) {
        this.mConsentStatus = consentStatus;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ConsentStatus getLastSuccessfullySyncedConsentStatus() {
        return this.mLastSuccessfullySyncedConsentStatus;
    }

    /* access modifiers changed from: 0000 */
    public void setLastSuccessfullySyncedConsentStatus(@Nullable ConsentStatus consentStatus) {
        this.mLastSuccessfullySyncedConsentStatus = consentStatus;
    }

    /* access modifiers changed from: 0000 */
    public boolean isWhitelisted() {
        return this.mIsWhitelisted;
    }

    /* access modifiers changed from: 0000 */
    public void setWhitelisted(boolean z) {
        this.mIsWhitelisted = z;
    }

    @Nullable
    public String getCurrentVendorListVersion() {
        return this.mCurrentVendorListVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentVendorListVersion(@Nullable String str) {
        this.mCurrentVendorListVersion = str;
    }

    @NonNull
    public String getCurrentVendorListLink() {
        return getCurrentVendorListLink(null);
    }

    @NonNull
    public String getCurrentVendorListLink(@Nullable String str) {
        return replaceLanguageMacro(this.mCurrentVendorListLink, this.mAppContext, str);
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentVendorListLink(@Nullable String str) {
        this.mCurrentVendorListLink = str;
    }

    @Nullable
    public String getCurrentPrivacyPolicyVersion() {
        return this.mCurrentPrivacyPolicyVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentPrivacyPolicyVersion(@Nullable String str) {
        this.mCurrentPrivacyPolicyVersion = str;
    }

    @NonNull
    public String getCurrentPrivacyPolicyLink() {
        return getCurrentPrivacyPolicyLink(null);
    }

    @NonNull
    public String getCurrentPrivacyPolicyLink(@Nullable String str) {
        return replaceLanguageMacro(this.mCurrentPrivacyPolicyLink, this.mAppContext, str);
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentPrivacyPolicyLink(@Nullable String str) {
        this.mCurrentPrivacyPolicyLink = str;
    }

    @Nullable
    public String getCurrentVendorListIabFormat() {
        return this.mCurrentVendorListIabFormat;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentVendorListIabFormat(@Nullable String str) {
        this.mCurrentVendorListIabFormat = str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCurrentVendorListIabHash() {
        return this.mCurrentVendorListIabHash;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentVendorListIabHash(@Nullable String str) {
        this.mCurrentVendorListIabHash = str;
    }

    @Nullable
    public String getConsentedVendorListVersion() {
        return this.mConsentedVendorListVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setConsentedVendorListVersion(@Nullable String str) {
        this.mConsentedVendorListVersion = str;
    }

    @Nullable
    public String getConsentedPrivacyPolicyVersion() {
        return this.mConsentedPrivacyPolicyVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setConsentedPrivacyPolicyVersion(@Nullable String str) {
        this.mConsentedPrivacyPolicyVersion = str;
    }

    @Nullable
    public String getConsentedVendorListIabFormat() {
        return this.mConsentedVendorListIabFormat;
    }

    /* access modifiers changed from: 0000 */
    public void setConsentedVendorListIabFormat(@Nullable String str) {
        this.mConsentedVendorListIabFormat = str;
    }

    @Nullable
    public String getExtras() {
        return this.mExtras;
    }

    public void setExtras(@Nullable String str) {
        this.mExtras = str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getConsentChangeReason() {
        return this.mConsentChangeReason;
    }

    /* access modifiers changed from: 0000 */
    public void setConsentChangeReason(@Nullable String str) {
        this.mConsentChangeReason = str;
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldReacquireConsent() {
        return this.mReacquireConsent;
    }

    /* access modifiers changed from: 0000 */
    public void setShouldReacquireConsent(boolean z) {
        this.mReacquireConsent = z;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Boolean getGdprApplies() {
        return this.mGdprApplies;
    }

    /* access modifiers changed from: 0000 */
    public void setGdprApplies(@Nullable Boolean bool) {
        this.mGdprApplies = bool;
    }

    public boolean isForceGdprApplies() {
        return this.mForceGdprApplies;
    }

    /* access modifiers changed from: 0000 */
    public void setForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getUdid() {
        return this.mUdid;
    }

    /* access modifiers changed from: 0000 */
    public void setUdid(@Nullable String str) {
        this.mUdid = str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getLastChangedMs() {
        return this.mLastChangedMs;
    }

    /* access modifiers changed from: 0000 */
    public void setLastChangedMs(@Nullable String str) {
        this.mLastChangedMs = str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ConsentStatus getConsentStatusBeforeDnt() {
        return this.mConsentStatusBeforeDnt;
    }

    /* access modifiers changed from: 0000 */
    public void setConsentStatusBeforeDnt(@Nullable ConsentStatus consentStatus) {
        this.mConsentStatusBeforeDnt = consentStatus;
    }

    @NonNull
    @VisibleForTesting
    static String replaceLanguageMacro(@Nullable String str, @NonNull Context context, @Nullable String str2) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replaceAll("%%LANGUAGE%%", validateLanguage(context, str2));
    }

    @NonNull
    private static String validateLanguage(@NonNull Context context, @Nullable String str) {
        String[] iSOLanguages;
        Preconditions.checkNotNull(context);
        for (String str2 : Locale.getISOLanguages()) {
            if (str2 != null && str2.equals(str)) {
                return str;
            }
        }
        return ClientMetadata.getCurrentLanguage(context);
    }
}
