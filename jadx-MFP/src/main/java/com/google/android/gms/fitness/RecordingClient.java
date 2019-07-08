package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzam;
import com.google.android.gms.internal.fitness.zzdt;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class RecordingClient extends GoogleApi<FitnessOptions> {
    private static final RecordingApi zzw = new zzdt();

    RecordingClient(@NonNull Context context, @NonNull FitnessOptions fitnessOptions) {
        super(context, zzam.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    RecordingClient(@NonNull Activity activity, @NonNull FitnessOptions fitnessOptions) {
        super(activity, zzam.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    public Task<Void> subscribe(DataType dataType) {
        return PendingResultUtil.toVoidTask(zzw.subscribe(asGoogleApiClient(), dataType));
    }

    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    public Task<Void> subscribe(DataSource dataSource) {
        return PendingResultUtil.toVoidTask(zzw.subscribe(asGoogleApiClient(), dataSource));
    }

    public Task<Void> unsubscribe(DataType dataType) {
        return PendingResultUtil.toVoidTask(zzw.unsubscribe(asGoogleApiClient(), dataType));
    }

    public Task<Void> unsubscribe(DataSource dataSource) {
        return PendingResultUtil.toVoidTask(zzw.unsubscribe(asGoogleApiClient(), dataSource));
    }

    public Task<Void> unsubscribe(Subscription subscription) {
        return PendingResultUtil.toVoidTask(zzw.unsubscribe(asGoogleApiClient(), subscription));
    }

    public Task<List<Subscription>> listSubscriptions() {
        return PendingResultUtil.toTask(zzw.listSubscriptions(asGoogleApiClient()), zzk.zzf);
    }

    public Task<List<Subscription>> listSubscriptions(DataType dataType) {
        return PendingResultUtil.toTask(zzw.listSubscriptions(asGoogleApiClient(), dataType), zzl.zzf);
    }
}
