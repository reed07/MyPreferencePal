package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

public interface SensorsApi {
    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, PendingIntent pendingIntent);

    @SuppressLint({"InlinedApi"})
    @RequiresPermission
    PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, OnDataPointListener onDataPointListener);

    PendingResult<DataSourcesResult> findDataSources(GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest);

    PendingResult<Status> remove(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> remove(GoogleApiClient googleApiClient, OnDataPointListener onDataPointListener);
}
