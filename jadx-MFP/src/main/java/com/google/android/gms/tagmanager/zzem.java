package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.Map;

final class zzem extends zzbq {
    private static final String ID = zza.RESOLUTION.toString();
    private final Context zzri;

    public zzem(Context context) {
        super(ID, new String[0]);
        this.zzri = context;
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.zzri.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append(AvidJSONUtil.KEY_X);
        sb.append(i2);
        return zzgj.zzj(sb.toString());
    }
}
