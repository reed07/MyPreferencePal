package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.UserDataStore;
import com.mopub.common.ClientMetadata.MoPubNetworkType;
import com.mopub.common.privacy.ConsentData;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.common.util.DateAndTime;
import com.myfitnesspal.shared.constants.Constants.Http;

public abstract class AdUrlGenerator extends BaseUrlGenerator {
    protected String mAdUnitId;
    @Nullable
    private final ConsentData mConsentData;
    protected Context mContext;
    protected String mKeywords;
    protected Location mLocation;
    @Nullable
    private final PersonalInfoManager mPersonalInfoManager = MoPub.getPersonalInformationManager();
    protected String mUserDataKeywords;

    @Deprecated
    public AdUrlGenerator withFacebookSupported(boolean z) {
        return this;
    }

    public AdUrlGenerator(Context context) {
        this.mContext = context;
        PersonalInfoManager personalInfoManager = this.mPersonalInfoManager;
        if (personalInfoManager == null) {
            this.mConsentData = null;
        } else {
            this.mConsentData = personalInfoManager.getConsentData();
        }
    }

    public AdUrlGenerator withAdUnitId(String str) {
        this.mAdUnitId = str;
        return this;
    }

    public AdUrlGenerator withKeywords(String str) {
        this.mKeywords = str;
        return this;
    }

    public AdUrlGenerator withUserDataKeywords(String str) {
        this.mUserDataKeywords = str;
        return this;
    }

    public AdUrlGenerator withLocation(Location location) {
        this.mLocation = location;
        return this;
    }

    /* access modifiers changed from: protected */
    public void setAdUnitId(String str) {
        addParam("id", str);
    }

    /* access modifiers changed from: protected */
    public void setSdkVersion(String str) {
        addParam("nv", str);
    }

    /* access modifiers changed from: protected */
    public void setKeywords(String str) {
        addParam(Http.Q, str);
    }

    /* access modifiers changed from: protected */
    public void setUserDataKeywords(String str) {
        if (MoPub.canCollectPersonalInformation()) {
            addParam("user_data_q", str);
        }
    }

    /* access modifiers changed from: protected */
    public void setLocation(@Nullable Location location) {
        if (MoPub.canCollectPersonalInformation()) {
            Location lastKnownLocation = LocationService.getLastKnownLocation(this.mContext, MoPub.getLocationPrecision(), MoPub.getLocationAwareness());
            if (lastKnownLocation != null && (location == null || lastKnownLocation.getTime() >= location.getTime())) {
                location = lastKnownLocation;
            }
            if (location != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(location.getLatitude());
                sb.append(",");
                sb.append(location.getLongitude());
                addParam("ll", sb.toString());
                addParam("lla", String.valueOf((int) location.getAccuracy()));
                addParam("llf", String.valueOf(calculateLocationStalenessInMilliseconds(location)));
                if (location == lastKnownLocation) {
                    addParam("llsdk", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setTimezone(String str) {
        addParam("z", str);
    }

    /* access modifiers changed from: protected */
    public void setOrientation(String str) {
        addParam("o", str);
    }

    /* access modifiers changed from: protected */
    public void setDensity(float f) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(f);
        addParam("sc", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void setMraidFlag(boolean z) {
        if (z) {
            addParam("mr", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
    }

    /* access modifiers changed from: protected */
    public void setMccCode(String str) {
        addParam("mcc", str == null ? "" : str.substring(0, mncPortionLength(str)));
    }

    /* access modifiers changed from: protected */
    public void setMncCode(String str) {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = str.substring(mncPortionLength(str));
        }
        addParam("mnc", str2);
    }

    /* access modifiers changed from: protected */
    public void setIsoCountryCode(String str) {
        addParam("iso", str);
    }

    /* access modifiers changed from: protected */
    public void setCarrierName(String str) {
        addParam("cn", str);
    }

    /* access modifiers changed from: protected */
    public void setNetworkType(MoPubNetworkType moPubNetworkType) {
        addParam(UserDataStore.CITY, moPubNetworkType);
    }

    /* access modifiers changed from: protected */
    public void setBundleId(String str) {
        if (!TextUtils.isEmpty(str)) {
            addParam("bundle", str);
        }
    }

    /* access modifiers changed from: protected */
    public void enableViewability(@NonNull String str) {
        Preconditions.checkNotNull(str);
        addParam("vv", str);
    }

    /* access modifiers changed from: protected */
    public void setAdvancedBiddingTokens() {
        addParam("abt", MoPub.getAdvancedBiddingTokensJson(this.mContext));
    }

    /* access modifiers changed from: protected */
    public void setGdprApplies() {
        PersonalInfoManager personalInfoManager = this.mPersonalInfoManager;
        if (personalInfoManager != null) {
            addParam("gdpr_applies", personalInfoManager.gdprApplies());
        }
    }

    /* access modifiers changed from: protected */
    public void setForceGdprApplies() {
        ConsentData consentData = this.mConsentData;
        if (consentData != null) {
            addParam("force_gdpr_applies", Boolean.valueOf(consentData.isForceGdprApplies()));
        }
    }

    /* access modifiers changed from: protected */
    public void setCurrentConsentStatus() {
        PersonalInfoManager personalInfoManager = this.mPersonalInfoManager;
        if (personalInfoManager != null) {
            addParam("current_consent_status", personalInfoManager.getPersonalInfoConsentStatus().getValue());
        }
    }

    /* access modifiers changed from: protected */
    public void setConsentedPrivacyPolicyVersion() {
        ConsentData consentData = this.mConsentData;
        if (consentData != null) {
            addParam("consented_privacy_policy_version", consentData.getConsentedPrivacyPolicyVersion());
        }
    }

    /* access modifiers changed from: protected */
    public void setConsentedVendorListVersion() {
        ConsentData consentData = this.mConsentData;
        if (consentData != null) {
            addParam("consented_vendor_list_version", consentData.getConsentedVendorListVersion());
        }
    }

    /* access modifiers changed from: protected */
    public void addBaseParams(ClientMetadata clientMetadata) {
        setAdUnitId(this.mAdUnitId);
        setSdkVersion(clientMetadata.getSdkVersion());
        setDeviceInfo(clientMetadata.getDeviceManufacturer(), clientMetadata.getDeviceModel(), clientMetadata.getDeviceProduct());
        setBundleId(clientMetadata.getAppPackageName());
        setKeywords(this.mKeywords);
        if (MoPub.canCollectPersonalInformation()) {
            setUserDataKeywords(this.mUserDataKeywords);
            setLocation(this.mLocation);
        }
        setTimezone(DateAndTime.getTimeZoneOffsetString());
        setOrientation(clientMetadata.getOrientationString());
        setDeviceDimensions(clientMetadata.getDeviceDimensions());
        setDensity(clientMetadata.getDensity());
        String networkOperatorForUrl = clientMetadata.getNetworkOperatorForUrl();
        setMccCode(networkOperatorForUrl);
        setMncCode(networkOperatorForUrl);
        setIsoCountryCode(clientMetadata.getIsoCountryCode());
        setCarrierName(clientMetadata.getNetworkOperatorName());
        setNetworkType(clientMetadata.getActiveNetworkType());
        setAppVersion(clientMetadata.getAppVersion());
        setAdvancedBiddingTokens();
        appendAdvertisingInfoTemplates();
        setGdprApplies();
        setForceGdprApplies();
        setCurrentConsentStatus();
        setConsentedPrivacyPolicyVersion();
        setConsentedVendorListVersion();
    }

    private void addParam(String str, MoPubNetworkType moPubNetworkType) {
        addParam(str, moPubNetworkType.toString());
    }

    private int mncPortionLength(String str) {
        return Math.min(3, str.length());
    }

    private static int calculateLocationStalenessInMilliseconds(Location location) {
        Preconditions.checkNotNull(location);
        return (int) (System.currentTimeMillis() - location.getTime());
    }
}
