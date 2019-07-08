package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class IdManager {
    private static final String FORWARD_SLASH_REGEX = Pattern.quote("/");
    private static final Pattern ID_PATTERN = Pattern.compile("[^\\p{Alnum}]");
    AdvertisingInfo advertisingInfo;
    AdvertisingInfoProvider advertisingInfoProvider;
    private final Context appContext;
    private final String appIdentifier;
    private final String appInstallIdentifier;
    private final boolean collectHardwareIds;
    private final boolean collectUserIds;
    boolean fetchedAdvertisingInfo;
    FirebaseInfo firebaseInfo;
    private final ReentrantLock installationIdLock = new ReentrantLock();
    private final InstallerPackageNameProvider installerPackageNameProvider;
    private final Collection<Kit> kits;

    public enum DeviceIdentifierType {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(101),
        ANDROID_SERIAL(102),
        ANDROID_ADVERTISING_ID(103);
        
        public final int protobufIndex;

        private DeviceIdentifierType(int i) {
            this.protobufIndex = i;
        }
    }

    public IdManager(Context context, String str, String str2, Collection<Kit> collection) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection != null) {
            this.appContext = context;
            this.appIdentifier = str;
            this.appInstallIdentifier = str2;
            this.kits = collection;
            this.installerPackageNameProvider = new InstallerPackageNameProvider();
            this.advertisingInfoProvider = new AdvertisingInfoProvider(context);
            this.firebaseInfo = new FirebaseInfo();
            this.collectHardwareIds = CommonUtils.getBooleanResourceValue(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.collectHardwareIds) {
                StringBuilder sb = new StringBuilder();
                sb.append("Device ID collection disabled for ");
                sb.append(context.getPackageName());
                Fabric.getLogger().d("Fabric", sb.toString());
            }
            this.collectUserIds = CommonUtils.getBooleanResourceValue(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.collectUserIds) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("User information collection disabled for ");
                sb2.append(context.getPackageName());
                Fabric.getLogger().d("Fabric", sb2.toString());
            }
        } else {
            throw new IllegalArgumentException("kits must not be null");
        }
    }

    public boolean canCollectUserIds() {
        return this.collectUserIds;
    }

    private String formatId(String str) {
        if (str == null) {
            return null;
        }
        return ID_PATTERN.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public String getAppInstallIdentifier() {
        String str = this.appInstallIdentifier;
        if (str != null) {
            return str;
        }
        SharedPreferences sharedPrefs = CommonUtils.getSharedPrefs(this.appContext);
        checkAdvertisingIdRotation(sharedPrefs);
        String string = sharedPrefs.getString("crashlytics.installation.id", null);
        return string == null ? createInstallationUUID(sharedPrefs) : string;
    }

    public String getAppIdentifier() {
        return this.appIdentifier;
    }

    public String getOsVersionString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOsDisplayVersionString());
        sb.append("/");
        sb.append(getOsBuildVersionString());
        return sb.toString();
    }

    public String getOsDisplayVersionString() {
        return removeForwardSlashesIn(VERSION.RELEASE);
    }

    public String getOsBuildVersionString() {
        return removeForwardSlashesIn(VERSION.INCREMENTAL);
    }

    public String getModelName() {
        return String.format(Locale.US, "%s/%s", new Object[]{removeForwardSlashesIn(Build.MANUFACTURER), removeForwardSlashesIn(Build.MODEL)});
    }

    private String removeForwardSlashesIn(String str) {
        return str.replaceAll(FORWARD_SLASH_REGEX, "");
    }

    @SuppressLint({"CommitPrefEdits"})
    private String createInstallationUUID(SharedPreferences sharedPreferences) {
        this.installationIdLock.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = formatId(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            return string;
        } finally {
            this.installationIdLock.unlock();
        }
    }

    private void checkAdvertisingIdRotation(SharedPreferences sharedPreferences) {
        AdvertisingInfo advertisingInfo2 = getAdvertisingInfo();
        if (advertisingInfo2 != null) {
            flushInstallationIdIfNecessary(sharedPreferences, advertisingInfo2.advertisingId);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void flushInstallationIdIfNecessary(SharedPreferences sharedPreferences, String str) {
        this.installationIdLock.lock();
        try {
            if (!TextUtils.isEmpty(str)) {
                String string = sharedPreferences.getString("crashlytics.advertising.id", null);
                if (TextUtils.isEmpty(string)) {
                    sharedPreferences.edit().putString("crashlytics.advertising.id", str).commit();
                } else if (!string.equals(str)) {
                    sharedPreferences.edit().remove("crashlytics.installation.id").putString("crashlytics.advertising.id", str).commit();
                }
                this.installationIdLock.unlock();
            }
        } finally {
            this.installationIdLock.unlock();
        }
    }

    public Map<DeviceIdentifierType, String> getDeviceIdentifiers() {
        HashMap hashMap = new HashMap();
        for (Kit kit : this.kits) {
            if (kit instanceof DeviceIdentifierProvider) {
                for (Entry entry : ((DeviceIdentifierProvider) kit).getDeviceIdentifiers().entrySet()) {
                    putNonNullIdInto(hashMap, (DeviceIdentifierType) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        String advertisingId = getAdvertisingId();
        if (TextUtils.isEmpty(advertisingId)) {
            putNonNullIdInto(hashMap, DeviceIdentifierType.ANDROID_ID, getAndroidId());
        } else {
            putNonNullIdInto(hashMap, DeviceIdentifierType.ANDROID_ADVERTISING_ID, advertisingId);
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public String getInstallerPackageName() {
        return this.installerPackageNameProvider.getInstallerPackageName(this.appContext);
    }

    public Boolean isLimitAdTrackingEnabled() {
        if (shouldCollectHardwareIds()) {
            return explicitCheckLimitAdTracking();
        }
        return null;
    }

    public String getAdvertisingId() {
        if (shouldCollectHardwareIds()) {
            AdvertisingInfo advertisingInfo2 = getAdvertisingInfo();
            if (advertisingInfo2 != null && !advertisingInfo2.limitAdTrackingEnabled) {
                return advertisingInfo2.advertisingId;
            }
        }
        return null;
    }

    private void putNonNullIdInto(Map<DeviceIdentifierType, String> map, DeviceIdentifierType deviceIdentifierType, String str) {
        if (str != null) {
            map.put(deviceIdentifierType, str);
        }
    }

    public String getAndroidId() {
        boolean equals = Boolean.TRUE.equals(explicitCheckLimitAdTracking());
        if (shouldCollectHardwareIds() && !equals) {
            String string = Secure.getString(this.appContext.getContentResolver(), "android_id");
            if (!"9774d56d682e549c".equals(string)) {
                return formatId(string);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean shouldCollectHardwareIds() {
        return this.collectHardwareIds && !this.firebaseInfo.isFirebaseCrashlyticsEnabled(this.appContext);
    }

    /* access modifiers changed from: 0000 */
    public synchronized AdvertisingInfo getAdvertisingInfo() {
        if (!this.fetchedAdvertisingInfo) {
            this.advertisingInfo = this.advertisingInfoProvider.getAdvertisingInfo();
            this.fetchedAdvertisingInfo = true;
        }
        return this.advertisingInfo;
    }

    private Boolean explicitCheckLimitAdTracking() {
        AdvertisingInfo advertisingInfo2 = getAdvertisingInfo();
        if (advertisingInfo2 != null) {
            return Boolean.valueOf(advertisingInfo2.limitAdTrackingEnabled);
        }
        return null;
    }
}
