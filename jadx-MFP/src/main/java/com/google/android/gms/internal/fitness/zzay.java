package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzay extends zzn<zzcf> {
    public static final Api<NoOptions> API = new Api<>("Fitness.SESSIONS_API", new zzba(), CLIENT_KEY);
    private static final ClientKey<zzay> CLIENT_KEY = new ClientKey<>();
    public static final Api<FitnessOptions> zzew = new Api<>("Fitness.SESSIONS_CLIENT", new zzbc(), CLIENT_KEY);

    private zzay(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 58, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi";
    }

    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.SessionsApi";
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
        if (queryLocalInterface instanceof zzcf) {
            return (zzcf) queryLocalInterface;
        }
        return new zzcg(iBinder);
    }
}
