package com.google.firebase.auth;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.annotations.PublicApi;
import java.util.Map;

@PublicApi
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class GetTokenResult {
    private String zza;
    private Map<String, Object> zzb;

    @KeepForSdk
    public GetTokenResult(String str, Map<String, Object> map) {
        this.zza = str;
        this.zzb = map;
    }

    @Nullable
    @PublicApi
    public String getToken() {
        return this.zza;
    }

    @PublicApi
    public long getExpirationTimestamp() {
        return zza("exp");
    }

    @PublicApi
    public long getAuthTimestamp() {
        return zza("auth_time");
    }

    @PublicApi
    public long getIssuedAtTimestamp() {
        return zza("iat");
    }

    @Nullable
    @PublicApi
    public String getSignInProvider() {
        Map map = (Map) this.zzb.get("firebase");
        if (map != null) {
            return (String) map.get("sign_in_provider");
        }
        return null;
    }

    @PublicApi
    public Map<String, Object> getClaims() {
        return this.zzb;
    }

    private long zza(String str) {
        Integer num = (Integer) this.zzb.get(str);
        if (num == null) {
            return 0;
        }
        return num.longValue();
    }
}
