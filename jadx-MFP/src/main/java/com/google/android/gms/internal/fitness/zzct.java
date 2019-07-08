package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zza;
import com.google.android.gms.fitness.request.zzd;
import com.google.android.gms.fitness.result.BleDevicesResult;

public final class zzct implements BleApi {
    public final PendingResult<Status> startBleScan(GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest) {
        return googleApiClient.enqueue(new zzcu(this, googleApiClient, startBleScanRequest, zzd.zzt().zza(startBleScanRequest.zzz(), googleApiClient.getLooper())));
    }

    public final PendingResult<Status> stopBleScan(GoogleApiClient googleApiClient, BleScanCallback bleScanCallback) {
        zza zzb = zzd.zzt().zzb(bleScanCallback, googleApiClient.getLooper());
        if (zzb == null) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return googleApiClient.enqueue(new zzcv(this, googleApiClient, zzb));
    }

    public final PendingResult<Status> claimBleDevice(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzcw(this, googleApiClient, str));
    }

    public final PendingResult<Status> claimBleDevice(GoogleApiClient googleApiClient, BleDevice bleDevice) {
        return googleApiClient.execute(new zzcx(this, googleApiClient, bleDevice));
    }

    public final PendingResult<Status> unclaimBleDevice(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzcy(this, googleApiClient, str));
    }

    public final PendingResult<Status> unclaimBleDevice(GoogleApiClient googleApiClient, BleDevice bleDevice) {
        return unclaimBleDevice(googleApiClient, bleDevice.getAddress());
    }

    public final PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzcz(this, googleApiClient));
    }
}
