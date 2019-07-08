package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.internal.icing.zze;
import com.google.firebase.FirebaseExceptionMapper;

final class zzq extends GoogleApi<NoOptions> {
    zzq(Context context) {
        super(context, zze.zzg, null, (StatusExceptionMapper) new FirebaseExceptionMapper());
    }
}
