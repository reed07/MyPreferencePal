package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzea;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class SensorsClient extends GoogleApi<FitnessOptions> {
    private static final SensorsApi zzx = new zzea();

    SensorsClient(@NonNull Context context, @NonNull FitnessOptions fitnessOptions) {
        super(context, zzas.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    SensorsClient(@NonNull Activity activity, @NonNull FitnessOptions fitnessOptions) {
        super(activity, zzas.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    public Task<List<DataSource>> findDataSources(DataSourcesRequest dataSourcesRequest) {
        return PendingResultUtil.toTask(zzx.findDataSources(asGoogleApiClient(), dataSourcesRequest), zzm.zzf);
    }

    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    public Task<Void> add(SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        ListenerHolder registerListener = registerListener(onDataPointListener, OnDataPointListener.class.getSimpleName());
        return doRegisterEventListener(new zzn(this, registerListener, registerListener, sensorRequest), new zzo(this, registerListener.getListenerKey(), registerListener));
    }

    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    public Task<Void> add(SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzx.add(asGoogleApiClient(), sensorRequest, pendingIntent));
    }

    public Task<Boolean> remove(OnDataPointListener onDataPointListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onDataPointListener, OnDataPointListener.class.getSimpleName()));
    }

    public Task<Void> remove(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzx.remove(asGoogleApiClient(), pendingIntent));
    }
}
