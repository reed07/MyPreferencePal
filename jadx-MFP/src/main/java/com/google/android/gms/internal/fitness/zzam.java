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

public final class zzam extends zzn<zzcb> {
    public static final Api<NoOptions> API = new Api<>("Fitness.RECORDING_API", new zzao(), CLIENT_KEY);
    private static final ClientKey<zzam> CLIENT_KEY = new ClientKey<>();
    public static final Api<FitnessOptions> zzew = new Api<>("Fitness.RECORDING_CLIENT", new zzaq(), CLIENT_KEY);

    private zzam(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 56, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi";
    }

    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.RecordingApi";
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
        if (queryLocalInterface instanceof zzcb) {
            return (zzcb) queryLocalInterface;
        }
        return new zzcc(iBinder);
    }
}
