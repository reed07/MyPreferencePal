package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.internal.zzan;
import com.google.android.gms.measurement.internal.zzbw;
import com.google.android.gms.measurement.internal.zzcu;
import com.google.android.gms.measurement.internal.zzcv;
import com.google.android.gms.measurement.internal.zzcw;
import com.google.android.gms.measurement.internal.zzcx;
import com.google.android.gms.measurement.internal.zzcy;
import com.google.android.gms.measurement.internal.zzdw;
import com.google.android.gms.measurement.internal.zzfu;
import java.util.List;
import java.util.Map;

@ShowFirstParty
@Deprecated
public class AppMeasurement {
    @ShowFirstParty
    @KeepForSdk
    public static final String CRASH_ORIGIN = "crash";
    @ShowFirstParty
    @KeepForSdk
    public static final String FCM_ORIGIN = "fcm";
    @ShowFirstParty
    @KeepForSdk
    public static final String FIAM_ORIGIN = "fiam";
    private final zzbw zzada;

    @ShowFirstParty
    @KeepForSdk
    public static class ConditionalUserProperty {
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public boolean mActive;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public String mAppId;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public long mCreationTimestamp;
        @Keep
        public String mExpiredEventName;
        @Keep
        public Bundle mExpiredEventParams;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public String mName;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public String mOrigin;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public long mTimeToLive;
        @Keep
        public String mTimedOutEventName;
        @Keep
        public Bundle mTimedOutEventParams;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public String mTriggerEventName;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public long mTriggerTimeout;
        @Keep
        public String mTriggeredEventName;
        @Keep
        public Bundle mTriggeredEventParams;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public long mTriggeredTimestamp;
        @Keep
        @ShowFirstParty
        @KeepForSdk
        public Object mValue;

        public ConditionalUserProperty() {
        }

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                this.mValue = zzdw.zze(obj);
                if (this.mValue == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            Bundle bundle = conditionalUserProperty.mTimedOutEventParams;
            if (bundle != null) {
                this.mTimedOutEventParams = new Bundle(bundle);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            Bundle bundle2 = conditionalUserProperty.mTriggeredEventParams;
            if (bundle2 != null) {
                this.mTriggeredEventParams = new Bundle(bundle2);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            Bundle bundle3 = conditionalUserProperty.mExpiredEventParams;
            if (bundle3 != null) {
                this.mExpiredEventParams = new Bundle(bundle3);
            }
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public static final class Event extends zzcu {
        @ShowFirstParty
        @KeepForSdk
        public static final String AD_REWARD = "_ar";
        @ShowFirstParty
        @KeepForSdk
        public static final String APP_EXCEPTION = "_ae";

        private Event() {
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public interface EventInterceptor extends zzcx {
        @WorkerThread
        @ShowFirstParty
        @KeepForSdk
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    @ShowFirstParty
    @KeepForSdk
    public interface OnEventListener extends zzcy {
        @WorkerThread
        @ShowFirstParty
        @KeepForSdk
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    @ShowFirstParty
    @KeepForSdk
    public static final class Param extends zzcv {
        @ShowFirstParty
        @KeepForSdk
        public static final String FATAL = "fatal";
        @ShowFirstParty
        @KeepForSdk
        public static final String TIMESTAMP = "timestamp";
        @ShowFirstParty
        @KeepForSdk
        public static final String TYPE = "type";

        private Param() {
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public static final class UserProperty extends zzcw {
        @ShowFirstParty
        @KeepForSdk
        public static final String FIREBASE_LAST_NOTIFICATION = "_ln";

        private UserProperty() {
        }
    }

    @Keep
    @ShowFirstParty
    @RequiresPermission
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zzbw.zza(context, (zzan) null).zzkm();
    }

    @ShowFirstParty
    public final void logEvent(@Size @NonNull String str, Bundle bundle) {
        this.zzada.zzgj().zza("app", str, bundle, true);
    }

    @ShowFirstParty
    public final void setUserProperty(@Size @NonNull String str, @Nullable @Size String str2) {
        this.zzada.zzgj().zzb("app", str, (Object) str2, false);
    }

    @KeepForSdk
    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.zzada.zzgj().setMeasurementEnabled(z);
    }

    public final void zzd(boolean z) {
        this.zzada.zzgj().zzd(z);
    }

    @ShowFirstParty
    @Deprecated
    public final void setMinimumSessionDuration(long j) {
        this.zzada.zzgj().setMinimumSessionDuration(j);
    }

    @ShowFirstParty
    public final void setSessionTimeoutDuration(long j) {
        this.zzada.zzgj().setSessionTimeoutDuration(j);
    }

    public AppMeasurement(zzbw zzbw) {
        Preconditions.checkNotNull(zzbw);
        this.zzada = zzbw;
    }

    @Keep
    @ShowFirstParty
    public void logEventInternal(String str, String str2, Bundle bundle) {
        this.zzada.zzgj().logEvent(str, str2, bundle);
    }

    @ShowFirstParty
    @KeepForSdk
    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zzada.zzgj().logEvent(str, str2, bundle, true, false, j);
    }

    @ShowFirstParty
    @KeepForSdk
    public void setUserPropertyInternal(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        this.zzada.zzgj().zzb(str, str2, obj, true);
    }

    @ShowFirstParty
    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        List<zzfu> zzk = this.zzada.zzgj().zzk(z);
        ArrayMap arrayMap = new ArrayMap(zzk.size());
        for (zzfu zzfu : zzk) {
            arrayMap.put(zzfu.name, zzfu.getValue());
        }
        return arrayMap;
    }

    @WorkerThread
    @ShowFirstParty
    @KeepForSdk
    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzada.zzgj().zza((zzcx) eventInterceptor);
    }

    @ShowFirstParty
    @KeepForSdk
    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzada.zzgj().zza((zzcy) onEventListener);
    }

    @ShowFirstParty
    @KeepForSdk
    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzada.zzgj().zzb((zzcy) onEventListener);
    }

    @Keep
    @Nullable
    public String getCurrentScreenName() {
        return this.zzada.zzgj().getCurrentScreenName();
    }

    @Keep
    @Nullable
    public String getCurrentScreenClass() {
        return this.zzada.zzgj().getCurrentScreenClass();
    }

    @Keep
    @Nullable
    public String getAppInstanceId() {
        return this.zzada.zzgj().zzgc();
    }

    @Keep
    @Nullable
    public String getGmpAppId() {
        return this.zzada.zzgj().getGmpAppId();
    }

    @Keep
    public long generateEventId() {
        return this.zzada.zzgr().zzmj();
    }

    @Keep
    public void beginAdUnitExposure(@Size @NonNull String str) {
        this.zzada.zzgi().beginAdUnitExposure(str, this.zzada.zzbx().elapsedRealtime());
    }

    @Keep
    public void endAdUnitExposure(@Size @NonNull String str) {
        this.zzada.zzgi().endAdUnitExposure(str, this.zzada.zzbx().elapsedRealtime());
    }

    @Keep
    @ShowFirstParty
    @KeepForSdk
    public void setConditionalUserProperty(@NonNull ConditionalUserProperty conditionalUserProperty) {
        this.zzada.zzgj().setConditionalUserProperty(conditionalUserProperty);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public void setConditionalUserPropertyAs(@NonNull ConditionalUserProperty conditionalUserProperty) {
        this.zzada.zzgj().setConditionalUserPropertyAs(conditionalUserProperty);
    }

    @Keep
    @ShowFirstParty
    @KeepForSdk
    public void clearConditionalUserProperty(@Size @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.zzada.zzgj().clearConditionalUserProperty(str, str2, bundle);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public void clearConditionalUserPropertyAs(@Size @NonNull String str, @Size @NonNull String str2, @Nullable String str3, @Nullable Bundle bundle) {
        this.zzada.zzgj().clearConditionalUserPropertyAs(str, str2, str3, bundle);
    }

    /* access modifiers changed from: protected */
    @Keep
    @WorkerThread
    @VisibleForTesting
    public Map<String, Object> getUserProperties(@Nullable String str, @Nullable @Size String str2, boolean z) {
        return this.zzada.zzgj().getUserProperties(str, str2, z);
    }

    /* access modifiers changed from: protected */
    @Keep
    @WorkerThread
    @VisibleForTesting
    public Map<String, Object> getUserPropertiesAs(@Size @NonNull String str, @Nullable String str2, @Nullable @Size String str3, boolean z) {
        return this.zzada.zzgj().getUserPropertiesAs(str, str2, str3, z);
    }

    @Keep
    @ShowFirstParty
    @WorkerThread
    @KeepForSdk
    public List<ConditionalUserProperty> getConditionalUserProperties(@Nullable String str, @Nullable @Size String str2) {
        return this.zzada.zzgj().getConditionalUserProperties(str, str2);
    }

    /* access modifiers changed from: protected */
    @Keep
    @WorkerThread
    @VisibleForTesting
    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(@Size @NonNull String str, @Nullable String str2, @Nullable @Size String str3) {
        return this.zzada.zzgj().getConditionalUserPropertiesAs(str, str2, str3);
    }

    @Keep
    @ShowFirstParty
    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@Size @NonNull String str) {
        this.zzada.zzgj();
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    @KeepForSdk
    public Boolean getBoolean() {
        return this.zzada.zzgj().zzkx();
    }

    @KeepForSdk
    public String getString() {
        return this.zzada.zzgj().zzky();
    }

    @KeepForSdk
    public Long getLong() {
        return this.zzada.zzgj().zzkz();
    }

    @KeepForSdk
    public Integer getInteger() {
        return this.zzada.zzgj().zzla();
    }

    @KeepForSdk
    public Double getDouble() {
        return this.zzada.zzgj().zzlb();
    }
}
