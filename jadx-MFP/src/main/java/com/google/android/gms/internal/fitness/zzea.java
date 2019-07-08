package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzal;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.result.DataSourcesResult;

public final class zzea implements SensorsApi {
    public final PendingResult<DataSourcesResult> findDataSources(GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        return googleApiClient.enqueue(new zzeb(this, googleApiClient, dataSourcesRequest));
    }

    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        return zza(googleApiClient, sensorRequest, zzan.zzw().zza(onDataPointListener, googleApiClient.getLooper()), null);
    }

    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return zza(googleApiClient, sensorRequest, null, pendingIntent);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, SensorRequest sensorRequest, zzt zzt, PendingIntent pendingIntent) {
        zzec zzec = new zzec(this, googleApiClient, sensorRequest, zzt, pendingIntent);
        return googleApiClient.enqueue(zzec);
    }

    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, OnDataPointListener onDataPointListener) {
        zzal zzb = zzan.zzw().zzb(onDataPointListener, googleApiClient.getLooper());
        if (zzb == null) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return zza(googleApiClient, zzb, null);
    }

    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return zza(googleApiClient, null, pendingIntent);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, @Nullable zzt zzt, @Nullable PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzed(this, googleApiClient, zzt, pendingIntent));
    }
}
