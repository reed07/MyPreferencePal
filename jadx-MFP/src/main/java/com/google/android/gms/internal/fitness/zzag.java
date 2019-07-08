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

public final class zzag extends zzn<zzbz> {
    public static final Api<NoOptions> API = new Api<>("Fitness.API", new zzai(), CLIENT_KEY);
    private static final ClientKey<zzag> CLIENT_KEY = new ClientKey<>();
    public static final Api<FitnessOptions> zzew = new Api<>("Fitness.CLIENT", new zzak(), CLIENT_KEY);

    private zzag(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 57, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi";
    }

    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.HistoryApi";
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
        if (queryLocalInterface instanceof zzbz) {
            return (zzbz) queryLocalInterface;
        }
        return new zzca(iBinder);
    }
}
