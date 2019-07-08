package com.mopub.common.privacy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.GpsHelper;
import com.mopub.common.GpsHelper.AdvertisingInfo;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import java.util.Calendar;

public class MoPubIdentifier {
    private boolean initialized;
    @NonNull
    private AdvertisingId mAdInfo;
    @NonNull
    private final Context mAppContext;
    @Nullable
    private AdvertisingIdChangeListener mIdChangeListener;
    @Nullable
    private volatile SdkInitializationListener mInitializationListener;
    /* access modifiers changed from: private */
    public boolean mRefreshingAdvertisingInfo;

    public interface AdvertisingIdChangeListener {
        void onIdChanged(@NonNull AdvertisingId advertisingId, @NonNull AdvertisingId advertisingId2);
    }

    private class RefreshAdvertisingInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        private RefreshAdvertisingInfoAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            MoPubIdentifier.this.refreshAdvertisingInfoBackgroundThread();
            MoPubIdentifier.this.mRefreshingAdvertisingInfo = false;
            return null;
        }
    }

    public MoPubIdentifier(@NonNull Context context) {
        this(context, null);
    }

    @VisibleForTesting
    MoPubIdentifier(@NonNull Context context, @Nullable AdvertisingIdChangeListener advertisingIdChangeListener) {
        Preconditions.checkNotNull(context);
        this.mAppContext = context;
        this.mIdChangeListener = advertisingIdChangeListener;
        this.mAdInfo = readIdFromStorage(this.mAppContext);
        if (this.mAdInfo == null) {
            this.mAdInfo = AdvertisingId.generateExpiredAdvertisingId();
        }
        refreshAdvertisingInfo();
    }

    @NonNull
    public AdvertisingId getAdvertisingInfo() {
        AdvertisingId advertisingId = this.mAdInfo;
        refreshAdvertisingInfo();
        return advertisingId;
    }

    private void refreshAdvertisingInfo() {
        if (!this.mRefreshingAdvertisingInfo) {
            this.mRefreshingAdvertisingInfo = true;
            AsyncTasks.safeExecuteOnExecutor(new RefreshAdvertisingInfoAsyncTask(), new Void[0]);
        }
    }

    /* access modifiers changed from: 0000 */
    public void refreshAdvertisingInfoBackgroundThread() {
        AdvertisingId advertisingId;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        AdvertisingId advertisingId2 = this.mAdInfo;
        AdvertisingInfo fetchAdvertisingInfoSync = GpsHelper.fetchAdvertisingInfoSync(this.mAppContext);
        if (fetchAdvertisingInfoSync == null || TextUtils.isEmpty(fetchAdvertisingInfoSync.advertisingId)) {
            advertisingId = getAmazonAdvertisingInfo(this.mAppContext);
        } else {
            advertisingId = new AdvertisingId(fetchAdvertisingInfoSync.advertisingId, advertisingId2.mMopubId, fetchAdvertisingInfoSync.limitAdTracking, advertisingId2.mLastRotation.getTimeInMillis());
        }
        if (advertisingId != null) {
            String generateIdString = advertisingId2.isRotationRequired() ? AdvertisingId.generateIdString() : advertisingId2.mMopubId;
            if (!advertisingId2.isRotationRequired()) {
                timeInMillis = advertisingId2.mLastRotation.getTimeInMillis();
            }
            setAdvertisingInfo(advertisingId.mAdvertisingId, generateIdString, advertisingId.mDoNotTrack, timeInMillis);
        }
        rotateMopubId();
    }

    @Nullable
    static synchronized AdvertisingId readIdFromStorage(@NonNull Context context) {
        synchronized (MoPubIdentifier.class) {
            Preconditions.checkNotNull(context);
            Calendar instance = Calendar.getInstance();
            try {
                SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(context, "com.mopub.settings.identifier");
                String string = sharedPreferences.getString("privacy.identifier.ifa", "");
                String string2 = sharedPreferences.getString("privacy.identifier.mopub", "");
                long j = sharedPreferences.getLong("privacy.identifier.time", instance.getTimeInMillis());
                boolean z = sharedPreferences.getBoolean("privacy.limit.ad.tracking", false);
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    AdvertisingId advertisingId = new AdvertisingId(string, string2, z, j);
                    return advertisingId;
                }
            } catch (ClassCastException unused) {
                MoPubLog.e("Cannot read identifier from shared preferences");
            }
            return null;
        }
    }

    private static synchronized void writeIdToStorage(@NonNull Context context, @NonNull AdvertisingId advertisingId) {
        synchronized (MoPubIdentifier.class) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(advertisingId);
            Editor edit = SharedPreferencesHelper.getSharedPreferences(context, "com.mopub.settings.identifier").edit();
            edit.putBoolean("privacy.limit.ad.tracking", advertisingId.mDoNotTrack);
            edit.putString("privacy.identifier.ifa", advertisingId.mAdvertisingId);
            edit.putString("privacy.identifier.mopub", advertisingId.mMopubId);
            edit.putLong("privacy.identifier.time", advertisingId.mLastRotation.getTimeInMillis());
            edit.apply();
        }
    }

    /* access modifiers changed from: 0000 */
    public void rotateMopubId() {
        if (!this.mAdInfo.isRotationRequired()) {
            setAdvertisingInfo(this.mAdInfo);
        } else {
            setAdvertisingInfo(AdvertisingId.generateFreshAdvertisingId());
        }
    }

    private void setAdvertisingInfo(@NonNull String str, @NonNull String str2, boolean z, long j) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        AdvertisingId advertisingId = new AdvertisingId(str, str2, z, j);
        setAdvertisingInfo(advertisingId);
    }

    /* access modifiers changed from: 0000 */
    public void setAdvertisingInfo(@NonNull AdvertisingId advertisingId) {
        AdvertisingId advertisingId2 = this.mAdInfo;
        this.mAdInfo = advertisingId;
        writeIdToStorage(this.mAppContext, this.mAdInfo);
        if (!this.mAdInfo.equals(advertisingId2) || !this.initialized) {
            notifyIdChangeListener(advertisingId2, this.mAdInfo);
        }
        this.initialized = true;
        reportInitializationComplete();
    }

    public void setIdChangeListener(@Nullable AdvertisingIdChangeListener advertisingIdChangeListener) {
        this.mIdChangeListener = advertisingIdChangeListener;
    }

    /* access modifiers changed from: 0000 */
    public void setInitializationListener(@Nullable SdkInitializationListener sdkInitializationListener) {
        this.mInitializationListener = sdkInitializationListener;
        if (this.initialized) {
            reportInitializationComplete();
        }
    }

    private synchronized void reportInitializationComplete() {
        SdkInitializationListener sdkInitializationListener = this.mInitializationListener;
        if (sdkInitializationListener != null) {
            this.mInitializationListener = null;
            sdkInitializationListener.onInitializationFinished();
        }
    }

    private void notifyIdChangeListener(@NonNull AdvertisingId advertisingId, @NonNull AdvertisingId advertisingId2) {
        Preconditions.checkNotNull(advertisingId2);
        AdvertisingIdChangeListener advertisingIdChangeListener = this.mIdChangeListener;
        if (advertisingIdChangeListener != null) {
            advertisingIdChangeListener.onIdChanged(advertisingId, advertisingId2);
        }
    }

    @Nullable
    private AdvertisingId getAmazonAdvertisingInfo(@NonNull Context context) {
        NoThrow.checkNotNull(context);
        ContentResolver contentResolver = context.getContentResolver();
        int i = Secure.getInt(contentResolver, Events.LIMIT_AD_TRACKING, -1);
        String string = Secure.getString(contentResolver, Attributes.ADVERTISING_ID);
        if (i == -1 || TextUtils.isEmpty(string)) {
            return null;
        }
        boolean z = i != 0;
        AdvertisingId advertisingId = this.mAdInfo;
        AdvertisingId advertisingId2 = new AdvertisingId(string, advertisingId.mMopubId, z, advertisingId.mLastRotation.getTimeInMillis());
        return advertisingId2;
    }
}
