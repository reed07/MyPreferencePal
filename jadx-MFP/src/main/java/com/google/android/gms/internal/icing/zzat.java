package com.google.android.gms.internal.icing;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.search.SearchAuth;

final class zzat extends ApiMethodImpl<Status, zzaq> {
    private final String zzbw;
    private final String zzbz;
    /* access modifiers changed from: private */
    public final boolean zzca = Log.isLoggable("SearchAuth", 3);

    protected zzat(GoogleApiClient googleApiClient, String str) {
        super(SearchAuth.API, googleApiClient);
        this.zzbw = str;
        this.zzbz = googleApiClient.getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzaq zzaq = (zzaq) anyClient;
        if (this.zzca) {
            Log.d("SearchAuth", "ClearTokenImpl started");
        }
        ((zzao) zzaq.getService()).zzb(new zzau(this), this.zzbz, this.zzbw);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        if (this.zzca) {
            String str = "SearchAuth";
            String str2 = "ClearTokenImpl received failure: ";
            String valueOf = String.valueOf(status.getStatusMessage());
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        return status;
    }
}
