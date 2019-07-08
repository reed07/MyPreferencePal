package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.data.Subscription.zza;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

public final class zzdt implements RecordingApi {
    public final PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzdu(this, googleApiClient));
    }

    public final PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient googleApiClient, DataType dataType) {
        return googleApiClient.enqueue(new zzdv(this, googleApiClient, dataType));
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, Subscription subscription) {
        return googleApiClient.enqueue(new zzdw(this, googleApiClient, subscription));
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, DataType dataType) {
        return zza(googleApiClient, new zza().zza(dataType).zzr());
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, DataSource dataSource) {
        return zza(googleApiClient, new zza().zza(dataSource).zzr());
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, DataType dataType) {
        return googleApiClient.execute(new zzdx(this, googleApiClient, dataType));
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, DataSource dataSource) {
        return googleApiClient.execute(new zzdy(this, googleApiClient, dataSource));
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, Subscription subscription) {
        if (subscription.getDataType() == null) {
            return unsubscribe(googleApiClient, subscription.getDataSource());
        }
        return unsubscribe(googleApiClient, subscription.getDataType());
    }
}
