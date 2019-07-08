package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;

public final class zzdj implements HistoryApi {
    public final PendingResult<Status> insertData(GoogleApiClient googleApiClient, DataSet dataSet) {
        Preconditions.checkNotNull(dataSet, "Must set the data set");
        Preconditions.checkState(!dataSet.getDataPoints().isEmpty(), "Cannot use an empty data set");
        Preconditions.checkNotNull(dataSet.getDataSource().zzi(), "Must set the app package name for the data source");
        return googleApiClient.enqueue(new zzdk(this, googleApiClient, dataSet, false));
    }

    public final PendingResult<Status> deleteData(GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        return googleApiClient.enqueue(new zzdl(this, googleApiClient, dataDeleteRequest));
    }

    public final PendingResult<Status> updateData(GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        Preconditions.checkNotNull(dataUpdateRequest.getDataSet(), "Must set the data set");
        Preconditions.checkNotZero(dataUpdateRequest.zzu(), (Object) "Must set a non-zero value for startTimeMillis/startTime");
        Preconditions.checkNotZero(dataUpdateRequest.zzv(), (Object) "Must set a non-zero value for endTimeMillis/endTime");
        return googleApiClient.enqueue(new zzdm(this, googleApiClient, dataUpdateRequest));
    }

    public final PendingResult<Status> registerDataUpdateListener(GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return googleApiClient.enqueue(new zzdn(this, googleApiClient, dataUpdateListenerRegistrationRequest));
    }

    public final PendingResult<Status> unregisterDataUpdateListener(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzdo(this, googleApiClient, pendingIntent));
    }

    public final PendingResult<DataReadResult> readData(GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        return googleApiClient.enqueue(new zzdp(this, googleApiClient, dataReadRequest));
    }

    public final PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient googleApiClient, DataType dataType) {
        return zza(googleApiClient, dataType, false);
    }

    public final PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(GoogleApiClient googleApiClient, DataType dataType) {
        return zza(googleApiClient, dataType, true);
    }

    private final PendingResult<DailyTotalResult> zza(GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        return googleApiClient.enqueue(new zzdq(this, googleApiClient, dataType, z));
    }
}
