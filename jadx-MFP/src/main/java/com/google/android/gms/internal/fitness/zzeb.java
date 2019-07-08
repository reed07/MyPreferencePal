package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import java.util.Collections;

final class zzeb extends zzav<DataSourcesResult> {
    private final /* synthetic */ DataSourcesRequest zzft;

    zzeb(zzea zzea, GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        this.zzft = dataSourcesRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzas) anyClient).getService()).zza(new DataSourcesRequest(this.zzft, (zzbk) new zzo(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new DataSourcesResult(Collections.emptyList(), status);
    }
}
