package com.google.android.gms.ads.internal.gmsg;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.HashMap;
import java.util.Map;

final class zzt implements zzu<zzbgg> {
    zzt() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        WindowManager windowManager = (WindowManager) zzbgg.getContext().getSystemService("window");
        zzbv.zzlf();
        View view = (View) zzbgg;
        DisplayMetrics zza = zzayh.zza(windowManager);
        int i = zza.widthPixels;
        int i2 = zza.heightPixels;
        int[] iArr = new int[2];
        HashMap hashMap = new HashMap();
        view.getLocationInWindow(iArr);
        hashMap.put("xInPixels", Integer.valueOf(iArr[0]));
        hashMap.put("yInPixels", Integer.valueOf(iArr[1]));
        hashMap.put("windowWidthInPixels", Integer.valueOf(i));
        hashMap.put("windowHeightInPixels", Integer.valueOf(i2));
        zzbgg.zza("locationReady", (Map<String, ?>) hashMap);
        zzaxz.zzeo("GET LOCATION COMPILED");
    }
}
