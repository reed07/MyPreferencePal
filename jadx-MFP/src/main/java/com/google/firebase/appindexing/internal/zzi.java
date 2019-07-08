package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.icing.zzbl;
import com.google.firebase.FirebaseExceptionMapper;

final class zzi extends GoogleApi<NoOptions> {
    public zzi(Context context) {
        super(context, zzd.API, null, Looper.getMainLooper(), new FirebaseExceptionMapper());
        zzbl.zzd(context);
    }
}
