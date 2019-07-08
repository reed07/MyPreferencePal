package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzakm extends zzbil {
    private static final Object lock = new Object();
    @GuardedBy("lock")
    private static zzakm zzdkq;
    private final AppMeasurementSdk zzdkr;

    private zzakm(AppMeasurementSdk appMeasurementSdk) {
        this.zzdkr = appMeasurementSdk;
    }

    public static void zza(Context context, String str, Bundle bundle) {
        synchronized (lock) {
            if (zzdkq == null) {
                zzakm zzakm = new zzakm(AppMeasurementSdk.getInstance(context, AdRequest.LOGTAG, "am", str, bundle));
                zzdkq = zzakm;
                new Thread(new zzakn(context, zzakm)).start();
            }
        }
    }

    public final void performAction(Bundle bundle) throws RemoteException {
        this.zzdkr.performAction(bundle);
    }

    public final Bundle performActionWithResponse(Bundle bundle) throws RemoteException {
        return this.zzdkr.performActionWithResponse(bundle);
    }

    public final void logEvent(String str, String str2, Bundle bundle) throws RemoteException {
        this.zzdkr.logEvent(str, str2, bundle);
    }

    public final void zza(String str, String str2, IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzdkr.setUserProperty(str, str2, iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null);
    }

    public final Map getUserProperties(String str, String str2, boolean z) throws RemoteException {
        return this.zzdkr.getUserProperties(str, str2, z);
    }

    public final int getMaxUserProperties(String str) throws RemoteException {
        return this.zzdkr.getMaxUserProperties(str);
    }

    public final void setConditionalUserProperty(Bundle bundle) throws RemoteException {
        this.zzdkr.setConditionalUserProperty(bundle);
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        this.zzdkr.clearConditionalUserProperty(str, str2, bundle);
    }

    public final List getConditionalUserProperties(String str, String str2) throws RemoteException {
        return this.zzdkr.getConditionalUserProperties(str, str2);
    }

    public final String getAppInstanceId() throws RemoteException {
        return this.zzdkr.getAppInstanceId();
    }

    public final String getGmpAppId() throws RemoteException {
        return this.zzdkr.getGmpAppId();
    }

    public final long generateEventId() throws RemoteException {
        return this.zzdkr.generateEventId();
    }

    public final void beginAdUnitExposure(String str) throws RemoteException {
        this.zzdkr.beginAdUnitExposure(str);
    }

    public final void endAdUnitExposure(String str) throws RemoteException {
        this.zzdkr.endAdUnitExposure(str);
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        this.zzdkr.setCurrentScreen(iObjectWrapper != null ? (Activity) ObjectWrapper.unwrap(iObjectWrapper) : null, str, str2);
    }

    public final String getCurrentScreenName() throws RemoteException {
        return this.zzdkr.getCurrentScreenName();
    }

    public final String getCurrentScreenClass() throws RemoteException {
        return this.zzdkr.getCurrentScreenClass();
    }

    public final String getAppIdOrigin() throws RemoteException {
        return this.zzdkr.getAppIdOrigin();
    }

    static final /* synthetic */ void zza(Context context, zzakm zzakm) {
        try {
            ((zzbim) zzbbe.zza(context, "com.google.android.gms.ads.measurement.DynamiteMeasurementManager", zzako.zzccb)).zza(zzakm);
        } catch (RemoteException | zzbbg | NullPointerException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
