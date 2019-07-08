package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.zzbv;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@zzark
public final class zzaap {
    private Context mContext = null;
    private String zzbuk = null;
    private String zzcyt;
    private Map<String, String> zzcyu;

    public zzaap(Context context, String str) {
        String str2;
        this.mContext = context;
        this.zzbuk = str;
        this.zzcyt = (String) zzwu.zzpz().zzd(zzaan.zzcpx);
        this.zzcyu = new LinkedHashMap();
        this.zzcyu.put("s", "gmob_sdk");
        this.zzcyu.put("v", "3");
        this.zzcyu.put(Http.OS, VERSION.RELEASE);
        this.zzcyu.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, VERSION.SDK);
        zzbv.zzlf();
        this.zzcyu.put("device", zzayh.zzzt());
        Map<String, String> map = this.zzcyu;
        String str3 = "app";
        if (context.getApplicationContext() != null) {
            str2 = context.getApplicationContext().getPackageName();
        } else {
            str2 = context.getPackageName();
        }
        map.put(str3, str2);
        Map<String, String> map2 = this.zzcyu;
        String str4 = "is_lite_sdk";
        zzbv.zzlf();
        map2.put(str4, zzayh.zzav(context) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        Future zzt = zzbv.zzlq().zzt(this.mContext);
        try {
            zzt.get();
            this.zzcyu.put("network_coarse", Integer.toString(((zzatz) zzt.get()).zzedd));
            this.zzcyu.put("network_fine", Integer.toString(((zzatz) zzt.get()).zzede));
        } catch (Exception e) {
            zzbv.zzlj().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zzra() {
        return this.zzcyt;
    }

    /* access modifiers changed from: 0000 */
    public final Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: 0000 */
    public final String zzmr() {
        return this.zzbuk;
    }

    /* access modifiers changed from: 0000 */
    public final Map<String, String> zzrb() {
        return this.zzcyu;
    }
}
