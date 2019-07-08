package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.internal.fitness.zzag;
import com.google.android.gms.internal.fitness.zzdj;
import com.google.android.gms.tasks.Task;

public class HistoryClient extends GoogleApi<FitnessOptions> {
    private static final HistoryApi zzv = new zzdj();

    HistoryClient(@NonNull Context context, @NonNull FitnessOptions fitnessOptions) {
        super(context, zzag.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    HistoryClient(@NonNull Activity activity, @NonNull FitnessOptions fitnessOptions) {
        super(activity, zzag.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    public Task<DataReadResponse> readData(DataReadRequest dataReadRequest) {
        return PendingResultUtil.toResponseTask(zzv.readData(asGoogleApiClient(), dataReadRequest), new DataReadResponse());
    }

    public Task<DataSet> readDailyTotal(DataType dataType) {
        return PendingResultUtil.toTask(zzv.readDailyTotal(asGoogleApiClient(), dataType), zzi.zzf);
    }

    public Task<DataSet> readDailyTotalFromLocalDevice(DataType dataType) {
        return PendingResultUtil.toTask(zzv.readDailyTotalFromLocalDevice(asGoogleApiClient(), dataType), zzj.zzf);
    }

    public Task<Void> insertData(DataSet dataSet) {
        return PendingResultUtil.toVoidTask(zzv.insertData(asGoogleApiClient(), dataSet));
    }

    public Task<Void> deleteData(DataDeleteRequest dataDeleteRequest) {
        return PendingResultUtil.toVoidTask(zzv.deleteData(asGoogleApiClient(), dataDeleteRequest));
    }

    public Task<Void> updateData(DataUpdateRequest dataUpdateRequest) {
        return PendingResultUtil.toVoidTask(zzv.updateData(asGoogleApiClient(), dataUpdateRequest));
    }

    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    public Task<Void> registerDataUpdateListener(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return PendingResultUtil.toVoidTask(zzv.registerDataUpdateListener(asGoogleApiClient(), dataUpdateListenerRegistrationRequest));
    }

    public Task<Void> unregisterDataUpdateListener(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzv.unregisterDataUpdateListener(asGoogleApiClient(), pendingIntent));
    }
}
