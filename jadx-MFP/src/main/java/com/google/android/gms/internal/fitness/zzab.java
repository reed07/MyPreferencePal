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

public final class zzab extends zzn<zzbx> {
    public static final Api<NoOptions> API = new Api<>("Fitness.GOALS_API", new zzad(), CLIENT_KEY);
    private static final ClientKey<zzab> CLIENT_KEY = new ClientKey<>();
    public static final Api<FitnessOptions> zzew = new Api<>("Fitness.GOALS_CLIENT", new zzaf(), CLIENT_KEY);

    private zzab(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 125, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi";
    }

    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.GoalsApi";
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
        if (queryLocalInterface instanceof zzbx) {
            return (zzbx) queryLocalInterface;
        }
        return new zzby(iBinder);
    }
}
