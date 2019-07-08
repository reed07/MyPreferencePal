package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.fitness.result.BleDevicesResult;

final /* synthetic */ class zza implements ResultConverter {
    static final ResultConverter zzf = new zza();

    private zza() {
    }

    public final Object convert(Result result) {
        return ((BleDevicesResult) result).getClaimedBleDevices();
    }
}
