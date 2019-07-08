package com.twitter.sdk.android.core.internal;

import android.content.Context;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStore;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStoreImpl;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class IdManager {
    private static final String FORWARD_SLASH_REGEX = Pattern.quote("/");
    private static final Pattern ID_PATTERN = Pattern.compile("[^\\p{Alnum}]");
    AdvertisingInfo advertisingInfo;
    AdvertisingInfoProvider advertisingInfoProvider;
    private final String appIdentifier;
    private final boolean collectHardwareIds;
    boolean fetchedAdvertisingInfo;
    private final ReentrantLock installationIdLock;
    private final PreferenceStore preferenceStore;

    public IdManager(Context context) {
        this(context, new PreferenceStoreImpl(context, "com.twitter.sdk.android.AdvertisingPreferences"));
    }

    IdManager(Context context, PreferenceStore preferenceStore2) {
        this(context, preferenceStore2, new AdvertisingInfoProvider(context, preferenceStore2));
    }

    IdManager(Context context, PreferenceStore preferenceStore2, AdvertisingInfoProvider advertisingInfoProvider2) {
        this.installationIdLock = new ReentrantLock();
        if (context != null) {
            this.appIdentifier = context.getPackageName();
            this.advertisingInfoProvider = advertisingInfoProvider2;
            this.preferenceStore = preferenceStore2;
            this.collectHardwareIds = CommonUtils.getBooleanResourceValue(context, "com.twitter.sdk.android.COLLECT_IDENTIFIERS_ENABLED", true);
            if (!this.collectHardwareIds) {
                StringBuilder sb = new StringBuilder();
                sb.append("Device ID collection disabled for ");
                sb.append(context.getPackageName());
                Twitter.getLogger().d("Twitter", sb.toString());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("appContext must not be null");
    }

    private String formatId(String str) {
        if (str == null) {
            return null;
        }
        return ID_PATTERN.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public String getDeviceUUID() {
        String str = "";
        if (!this.collectHardwareIds) {
            return str;
        }
        String string = this.preferenceStore.get().getString("installation_uuid", null);
        return string == null ? createInstallationUUID() : string;
    }

    private String createInstallationUUID() {
        this.installationIdLock.lock();
        try {
            String string = this.preferenceStore.get().getString("installation_uuid", null);
            if (string == null) {
                string = formatId(UUID.randomUUID().toString());
                this.preferenceStore.save(this.preferenceStore.edit().putString("installation_uuid", string));
            }
            return string;
        } finally {
            this.installationIdLock.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized AdvertisingInfo getAdvertisingInfo() {
        if (!this.fetchedAdvertisingInfo) {
            this.advertisingInfo = this.advertisingInfoProvider.getAdvertisingInfo();
            this.fetchedAdvertisingInfo = true;
        }
        return this.advertisingInfo;
    }

    public String getAdvertisingId() {
        if (this.collectHardwareIds) {
            AdvertisingInfo advertisingInfo2 = getAdvertisingInfo();
            if (advertisingInfo2 != null) {
                return advertisingInfo2.advertisingId;
            }
        }
        return null;
    }
}
