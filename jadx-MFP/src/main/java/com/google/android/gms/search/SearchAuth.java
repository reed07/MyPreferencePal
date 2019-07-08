package com.google.android.gms.search;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.internal.icing.zzaq;
import com.google.android.gms.internal.icing.zzar;

public class SearchAuth {
    public static final Api<NoOptions> API = new Api<>("SearchAuth.API", zzby, CLIENT_KEY);
    private static final ClientKey<zzaq> CLIENT_KEY = new ClientKey<>();
    public static final SearchAuthApi SearchAuthApi = new zzar();
    private static final AbstractClientBuilder<zzaq, NoOptions> zzby = new zzb();

    public static class StatusCodes {
        public static final int AUTH_DISABLED = 10000;
        public static final int AUTH_THROTTLED = 10001;
        public static final int DEVELOPER_ERROR = 10;
        public static final int INTERNAL_ERROR = 8;
        public static final int SUCCESS = 0;
    }

    private SearchAuth() {
    }
}
