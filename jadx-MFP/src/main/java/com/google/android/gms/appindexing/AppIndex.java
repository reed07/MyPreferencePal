package com.google.android.gms.appindexing;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.icing.zzai;
import com.google.android.gms.internal.icing.zze;

@Deprecated
@VisibleForTesting
public final class AppIndex {
    public static final Api<NoOptions> API = zze.zzg;
    public static final Api<NoOptions> APP_INDEX_API = zze.zzg;
    public static final AppIndexApi AppIndexApi = new zzai();

    private AppIndex() {
    }
}
