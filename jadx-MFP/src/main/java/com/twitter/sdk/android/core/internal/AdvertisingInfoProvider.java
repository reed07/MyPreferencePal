package com.twitter.sdk.android.core.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStore;

class AdvertisingInfoProvider {
    private final Context context;
    private final PreferenceStore preferenceStore;

    AdvertisingInfoProvider(Context context2, PreferenceStore preferenceStore2) {
        this.context = context2.getApplicationContext();
        this.preferenceStore = preferenceStore2;
    }

    /* access modifiers changed from: 0000 */
    public AdvertisingInfo getAdvertisingInfo() {
        AdvertisingInfo infoFromPreferences = getInfoFromPreferences();
        if (isInfoValid(infoFromPreferences)) {
            Twitter.getLogger().d("Twitter", "Using AdvertisingInfo from Preference Store");
            refreshInfoIfNeededAsync(infoFromPreferences);
            return infoFromPreferences;
        }
        AdvertisingInfo advertisingInfoFromStrategies = getAdvertisingInfoFromStrategies();
        storeInfoToPreferences(advertisingInfoFromStrategies);
        return advertisingInfoFromStrategies;
    }

    private void refreshInfoIfNeededAsync(final AdvertisingInfo advertisingInfo) {
        new Thread(new Runnable() {
            public void run() {
                AdvertisingInfo access$000 = AdvertisingInfoProvider.this.getAdvertisingInfoFromStrategies();
                if (!advertisingInfo.equals(access$000)) {
                    Twitter.getLogger().d("Twitter", "Asychronously getting Advertising Info and storing it to preferences");
                    AdvertisingInfoProvider.this.storeInfoToPreferences(access$000);
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    @SuppressLint({"CommitPrefEdits"})
    public void storeInfoToPreferences(AdvertisingInfo advertisingInfo) {
        if (isInfoValid(advertisingInfo)) {
            PreferenceStore preferenceStore2 = this.preferenceStore;
            preferenceStore2.save(preferenceStore2.edit().putString(Attributes.ADVERTISING_ID, advertisingInfo.advertisingId).putBoolean("limit_ad_tracking_enabled", advertisingInfo.limitAdTrackingEnabled));
            return;
        }
        PreferenceStore preferenceStore3 = this.preferenceStore;
        preferenceStore3.save(preferenceStore3.edit().remove(Attributes.ADVERTISING_ID).remove("limit_ad_tracking_enabled"));
    }

    private AdvertisingInfo getInfoFromPreferences() {
        return new AdvertisingInfo(this.preferenceStore.get().getString(Attributes.ADVERTISING_ID, ""), this.preferenceStore.get().getBoolean("limit_ad_tracking_enabled", false));
    }

    private AdvertisingInfoStrategy getReflectionStrategy() {
        return new AdvertisingInfoReflectionStrategy(this.context);
    }

    private AdvertisingInfoStrategy getServiceStrategy() {
        return new AdvertisingInfoServiceStrategy(this.context);
    }

    private boolean isInfoValid(AdvertisingInfo advertisingInfo) {
        return advertisingInfo != null && !TextUtils.isEmpty(advertisingInfo.advertisingId);
    }

    /* access modifiers changed from: private */
    public AdvertisingInfo getAdvertisingInfoFromStrategies() {
        AdvertisingInfo advertisingInfo = getReflectionStrategy().getAdvertisingInfo();
        if (!isInfoValid(advertisingInfo)) {
            advertisingInfo = getServiceStrategy().getAdvertisingInfo();
            if (!isInfoValid(advertisingInfo)) {
                Twitter.getLogger().d("Twitter", "AdvertisingInfo not present");
            } else {
                Twitter.getLogger().d("Twitter", "Using AdvertisingInfo from Service Provider");
            }
        } else {
            Twitter.getLogger().d("Twitter", "Using AdvertisingInfo from Reflection Provider");
        }
        return advertisingInfo;
    }
}
