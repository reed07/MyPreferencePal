package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzl extends zzbq {
    private static final String ID = zza.APP_VERSION_NAME.toString();
    private final Context zzri;

    public zzl(Context context) {
        super(ID, new String[0]);
        this.zzri = context;
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        try {
            return zzgj.zzj(this.zzri.getPackageManager().getPackageInfo(this.zzri.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            String packageName = this.zzri.getPackageName();
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(message).length());
            sb.append("Package name ");
            sb.append(packageName);
            sb.append(" not found. ");
            sb.append(message);
            zzdi.e(sb.toString());
            return zzgj.zzqq();
        }
    }
}
