package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.BleDevicesResult;

final class zzda extends zzes {
    private final ResultHolder<BleDevicesResult> zzev;

    private zzda(ResultHolder<BleDevicesResult> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void zza(BleDevicesResult bleDevicesResult) {
        this.zzev.setResult(bleDevicesResult);
    }

    /* synthetic */ zzda(ResultHolder resultHolder, zzcu zzcu) {
        this(resultHolder);
    }
}
