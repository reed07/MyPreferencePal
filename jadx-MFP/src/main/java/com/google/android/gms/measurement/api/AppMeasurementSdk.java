package com.google.android.gms.measurement.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.measurement.zzea;
import com.google.android.gms.measurement.internal.zzcx;
import com.google.android.gms.measurement.internal.zzcy;
import java.util.List;
import java.util.Map;

@KeepForSdk
public class AppMeasurementSdk {
    private final zzea zzadd;

    @KeepForSdk
    public static final class ConditionalUserProperty {
        @KeepForSdk
        public static final String ACTIVE = "active";
        @KeepForSdk
        public static final String CREATION_TIMESTAMP = "creation_timestamp";
        @KeepForSdk
        public static final String EXPIRED_EVENT_NAME = "expired_event_name";
        @KeepForSdk
        public static final String EXPIRED_EVENT_PARAMS = "expired_event_params";
        @KeepForSdk
        public static final String NAME = "name";
        @KeepForSdk
        public static final String ORIGIN = "origin";
        @KeepForSdk
        public static final String TIMED_OUT_EVENT_NAME = "timed_out_event_name";
        @KeepForSdk
        public static final String TIMED_OUT_EVENT_PARAMS = "timed_out_event_params";
        @KeepForSdk
        public static final String TIME_TO_LIVE = "time_to_live";
        @KeepForSdk
        public static final String TRIGGERED_EVENT_NAME = "triggered_event_name";
        @KeepForSdk
        public static final String TRIGGERED_EVENT_PARAMS = "triggered_event_params";
        @KeepForSdk
        public static final String TRIGGERED_TIMESTAMP = "triggered_timestamp";
        @KeepForSdk
        public static final String TRIGGER_EVENT_NAME = "trigger_event_name";
        @KeepForSdk
        public static final String TRIGGER_TIMEOUT = "trigger_timeout";
        @KeepForSdk
        public static final String VALUE = "value";

        private ConditionalUserProperty() {
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

    @RequiresPermission
    @KeepForSdk
    public static AppMeasurementSdk getInstance(@NonNull Context context) {
        return zzea.zza(context, null, null, null, null).zzga();
    }

    @RequiresPermission
    @KeepForSdk
    public static AppMeasurementSdk getInstance(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, Bundle bundle) {
        return zzea.zza(context, str, str2, str3, bundle).zzga();
    }

    public AppMeasurementSdk(zzea zzea) {
        this.zzadd = zzea;
    }

    @KeepForSdk
    public void setMeasurementEnabled(boolean z) {
        this.zzadd.setMeasurementEnabled(z);
    }

    @KeepForSdk
    public void logEvent(String str, String str2, Bundle bundle) {
        this.zzadd.logEventInternal(str, str2, bundle);
    }

    @KeepForSdk
    public void logEventNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zzadd.logEventInternalNoInterceptor(str, str2, bundle, j);
    }

    @KeepForSdk
    public void setUserProperty(String str, String str2, Object obj) {
        this.zzadd.zza(str, str2, obj, true);
    }

    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(@Nullable String str, @Nullable @Size String str2, boolean z) {
        return this.zzadd.getUserProperties(str, str2, z);
    }

    @KeepForSdk
    public void setConditionalUserProperty(@NonNull Bundle bundle) {
        this.zzadd.setConditionalUserProperty(bundle);
    }

    @KeepForSdk
    public void clearConditionalUserProperty(@Size @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.zzadd.clearConditionalUserProperty(str, str2, bundle);
    }

    @WorkerThread
    @KeepForSdk
    public List<Bundle> getConditionalUserProperties(@Nullable String str, @Nullable @Size String str2) {
        return this.zzadd.getConditionalUserProperties(str, str2);
    }

    @Nullable
    @KeepForSdk
    public String getCurrentScreenName() {
        return this.zzadd.getCurrentScreenName();
    }

    @Nullable
    @KeepForSdk
    public String getCurrentScreenClass() {
        return this.zzadd.getCurrentScreenClass();
    }

    @Nullable
    @KeepForSdk
    public String getAppInstanceId() {
        return this.zzadd.zzgc();
    }

    @Nullable
    @KeepForSdk
    public String getGmpAppId() {
        return this.zzadd.getGmpAppId();
    }

    @KeepForSdk
    public long generateEventId() {
        return this.zzadd.generateEventId();
    }

    @KeepForSdk
    public void beginAdUnitExposure(@Size @NonNull String str) {
        this.zzadd.beginAdUnitExposure(str);
    }

    @KeepForSdk
    public void endAdUnitExposure(@Size @NonNull String str) {
        this.zzadd.endAdUnitExposure(str);
    }

    @WorkerThread
    @ShowFirstParty
    @KeepForSdk
    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzadd.zza((zzcx) eventInterceptor);
    }

    @ShowFirstParty
    @KeepForSdk
    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzadd.zza((zzcy) onEventListener);
    }

    @ShowFirstParty
    @KeepForSdk
    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzadd.zzb((zzcy) onEventListener);
    }

    @KeepForSdk
    public Bundle performActionWithResponse(Bundle bundle) {
        return this.zzadd.zza(bundle, true);
    }

    @KeepForSdk
    public void performAction(Bundle bundle) {
        this.zzadd.zza(bundle, false);
    }

    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@Size @NonNull String str) {
        return this.zzadd.getMaxUserProperties(str);
    }

    @KeepForSdk
    public void setCurrentScreen(@NonNull Activity activity, @Nullable @Size String str, @Nullable @Size String str2) {
        this.zzadd.setCurrentScreen(activity, str, str2);
    }

    @KeepForSdk
    public String getAppIdOrigin() {
        return this.zzadd.getAppIdOrigin();
    }
}
