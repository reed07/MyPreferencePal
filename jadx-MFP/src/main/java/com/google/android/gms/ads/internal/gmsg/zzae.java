package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzae implements zzu<Object> {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private final Map<String, zzaf> zzdgg = new HashMap();

    public final void zza(String str, zzaf zzaf) {
        synchronized (this.mLock) {
            this.zzdgg.put(str, zzaf);
        }
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str;
        String str2 = (String) map.get("id");
        String str3 = (String) map.get("fail");
        String str4 = (String) map.get("fail_reason");
        String str5 = (String) map.get("fail_stack");
        String str6 = (String) map.get("result");
        if (TextUtils.isEmpty(str5)) {
            str4 = "Unknown Fail Reason.";
        }
        if (TextUtils.isEmpty(str5)) {
            str = "";
        } else {
            String str7 = "\n";
            String valueOf = String.valueOf(str5);
            str = valueOf.length() != 0 ? str7.concat(valueOf) : new String(str7);
        }
        synchronized (this.mLock) {
            zzaf zzaf = (zzaf) this.zzdgg.remove(str2);
            if (zzaf == null) {
                String str8 = "Received result for unexpected method invocation: ";
                String valueOf2 = String.valueOf(str2);
                zzaxz.zzeo(valueOf2.length() != 0 ? str8.concat(valueOf2) : new String(str8));
            } else if (!TextUtils.isEmpty(str3)) {
                String valueOf3 = String.valueOf(str4);
                String valueOf4 = String.valueOf(str);
                zzaf.zzbw(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3));
            } else if (str6 == null) {
                zzaf.zzd(null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str6);
                    if (zzaxz.zzza()) {
                        String str9 = "Result GMSG: ";
                        String valueOf5 = String.valueOf(jSONObject.toString(2));
                        zzaxz.v(valueOf5.length() != 0 ? str9.concat(valueOf5) : new String(str9));
                    }
                    zzaf.zzd(jSONObject);
                } catch (JSONException e) {
                    zzaf.zzbw(e.getMessage());
                }
            }
        }
    }
}
