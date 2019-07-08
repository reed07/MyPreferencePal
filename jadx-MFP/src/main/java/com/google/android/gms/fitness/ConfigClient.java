package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.internal.fitness.zzdb;
import com.google.android.gms.internal.fitness.zzv;
import com.google.android.gms.tasks.Task;

public class ConfigClient extends GoogleApi<FitnessOptions> {
    private static final ConfigApi zzj = new zzdb();

    ConfigClient(@NonNull Context context, @NonNull FitnessOptions fitnessOptions) {
        super(context, zzv.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    ConfigClient(@NonNull Activity activity, @NonNull FitnessOptions fitnessOptions) {
        super(activity, zzv.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    public Task<DataType> createCustomDataType(DataTypeCreateRequest dataTypeCreateRequest) {
        return PendingResultUtil.toTask(zzj.createCustomDataType(asGoogleApiClient(), dataTypeCreateRequest), zzd.zzf);
    }

    public Task<DataType> readDataType(String str) {
        return PendingResultUtil.toTask(zzj.readDataType(asGoogleApiClient(), str), zze.zzf);
    }

    public Task<Void> disableFit() {
        return PendingResultUtil.toVoidTask(zzj.disableFit(asGoogleApiClient()));
    }
}
