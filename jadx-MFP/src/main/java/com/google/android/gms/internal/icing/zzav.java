package com.google.android.gms.internal.icing;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.search.SearchAuth;
import com.google.android.gms.search.SearchAuthApi.GoogleNowAuthResult;

final class zzav extends ApiMethodImpl<GoogleNowAuthResult, zzaq> {
    private final String zzbz;
    /* access modifiers changed from: private */
    public final boolean zzca = Log.isLoggable("SearchAuth", 3);
    private final String zzcc;

    protected zzav(GoogleApiClient googleApiClient, String str) {
        super(SearchAuth.API, googleApiClient);
        this.zzcc = str;
        this.zzbz = googleApiClient.getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzaq zzaq = (zzaq) anyClient;
        if (this.zzca) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl started");
        }
        ((zzao) zzaq.getService()).zza(new zzaw(this), this.zzbz, this.zzcc);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        if (this.zzca) {
            String str = "SearchAuth";
            String str2 = "GetGoogleNowAuthImpl received failure: ";
            String valueOf = String.valueOf(status.getStatusMessage());
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        return new zzax(status, null);
    }
}
