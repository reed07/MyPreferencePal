package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.fitness.zzg;
import java.util.Set;

public abstract class zzn<T extends IInterface> extends GmsClient<T> {
    protected zzn(Context context, Looper looper, int i, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings) {
        super(context, looper, i, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    public abstract T createServiceInterface(IBinder iBinder);

    public int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public abstract String getServiceDescriptor();

    public abstract String getStartServiceAction();

    public boolean requiresAccount() {
        return true;
    }

    public boolean requiresSignIn() {
        return !DeviceProperties.isWearable(getContext());
    }

    /* access modifiers changed from: protected */
    public Set<Scope> validateScopes(Set<Scope> set) {
        return zzg.zza(set);
    }
}
