package com.mopub.mobileads;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.ConsentData;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.network.TrackingRequest;
import com.mopub.network.TrackingRequest.Listener;
import com.mopub.volley.VolleyError;

public class MoPubConversionTracker {
    @NonNull
    private final Context mContext;
    /* access modifiers changed from: private */
    @NonNull
    public final String mIsTrackedKey;
    /* access modifiers changed from: private */
    @NonNull
    public SharedPreferences mSharedPreferences = SharedPreferencesHelper.getSharedPreferences(this.mContext);
    /* access modifiers changed from: private */
    @NonNull
    public final String mWantToTrack;

    public MoPubConversionTracker(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        this.mContext = context.getApplicationContext();
        String packageName = this.mContext.getPackageName();
        StringBuilder sb = new StringBuilder();
        sb.append(packageName);
        sb.append(" wantToTrack");
        this.mWantToTrack = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(packageName);
        sb2.append(" tracked");
        this.mIsTrackedKey = sb2.toString();
    }

    public void reportAppOpen() {
        reportAppOpen(false);
    }

    public void reportAppOpen(boolean z) {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager == null) {
            MoPubLog.w("Cannot report app open until initialization is done");
        } else if (!z && isAlreadyTracked()) {
            MoPubLog.d("Conversion already tracked");
        } else if (z || MoPub.canCollectPersonalInformation()) {
            ConsentData consentData = personalInformationManager.getConsentData();
            TrackingRequest.makeTrackingHttpRequest(new ConversionUrlGenerator(this.mContext).withGdprApplies(personalInformationManager.gdprApplies()).withForceGdprApplies(consentData.isForceGdprApplies()).withCurrentConsentStatus(personalInformationManager.getPersonalInfoConsentStatus().getValue()).withConsentedPrivacyPolicyVersion(consentData.getConsentedPrivacyPolicyVersion()).withConsentedVendorListVersion(consentData.getConsentedVendorListVersion()).withSessionTracker(z).generateUrlString(Constants.HOST), this.mContext, (Listener) new Listener() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onResponse(@NonNull String str) {
                    MoPubConversionTracker.this.mSharedPreferences.edit().putBoolean(MoPubConversionTracker.this.mIsTrackedKey, true).putBoolean(MoPubConversionTracker.this.mWantToTrack, false).apply();
                }
            });
        } else {
            this.mSharedPreferences.edit().putBoolean(this.mWantToTrack, true).apply();
        }
    }

    public boolean shouldTrack() {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        boolean z = false;
        if (personalInformationManager == null) {
            return false;
        }
        if (personalInformationManager.canCollectPersonalInformation() && this.mSharedPreferences.getBoolean(this.mWantToTrack, false)) {
            z = true;
        }
        return z;
    }

    private boolean isAlreadyTracked() {
        return this.mSharedPreferences.getBoolean(this.mIsTrackedKey, false);
    }
}
