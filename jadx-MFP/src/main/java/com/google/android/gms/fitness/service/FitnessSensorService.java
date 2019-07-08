package com.google.android.gms.fitness.service;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.fitness.zzbk;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzeu;
import com.google.android.gms.internal.fitness.zzew;
import com.google.android.gms.internal.fitness.zzez;
import java.util.List;

public abstract class FitnessSensorService extends Service {
    public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
    private zza zzix;

    private static class zza extends zzez {
        private final FitnessSensorService zziy;

        private zza(FitnessSensorService fitnessSensorService) {
            this.zziy = fitnessSensorService;
        }

        public final void zza(zzeu zzeu, zzbk zzbk) throws RemoteException {
            this.zziy.zzab();
            zzbk.zza(new DataSourcesResult(this.zziy.onFindDataSources(zzeu.getDataTypes()), Status.RESULT_SUCCESS));
        }

        public final void zza(FitnessSensorServiceRequest fitnessSensorServiceRequest, zzcq zzcq) throws RemoteException {
            this.zziy.zzab();
            if (this.zziy.onRegister(fitnessSensorServiceRequest)) {
                zzcq.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcq.onResult(new Status(13));
            }
        }

        public final void zza(zzew zzew, zzcq zzcq) throws RemoteException {
            this.zziy.zzab();
            if (this.zziy.onUnregister(zzew.getDataSource())) {
                zzcq.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcq.onResult(new Status(13));
            }
        }
    }

    public abstract List<DataSource> onFindDataSources(List<DataType> list);

    public abstract boolean onRegister(FitnessSensorServiceRequest fitnessSensorServiceRequest);

    public abstract boolean onUnregister(DataSource dataSource);

    @CallSuper
    public void onCreate() {
        super.onCreate();
        this.zzix = new zza();
    }

    @Nullable
    @CallSuper
    public IBinder onBind(Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        if (Log.isLoggable("FitnessSensorService", 3)) {
            String valueOf = String.valueOf(intent);
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20 + String.valueOf(name).length());
            sb.append("Intent ");
            sb.append(valueOf);
            sb.append(" received by ");
            sb.append(name);
            Log.d("FitnessSensorService", sb.toString());
        }
        return this.zzix.asBinder();
    }

    /* access modifiers changed from: protected */
    @TargetApi(19)
    @VisibleForTesting
    public final void zzab() throws SecurityException {
        int callingUid = Binder.getCallingUid();
        if (PlatformVersion.isAtLeastKitKat()) {
            ((AppOpsManager) getSystemService("appops")).checkPackage(callingUid, "com.google.android.gms");
            return;
        }
        String[] packagesForUid = getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            int length = packagesForUid.length;
            int i = 0;
            while (i < length) {
                if (!packagesForUid[i].equals("com.google.android.gms")) {
                    i++;
                } else {
                    return;
                }
            }
        }
        throw new SecurityException("Unauthorized caller");
    }
}
