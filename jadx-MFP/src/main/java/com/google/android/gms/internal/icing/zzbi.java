package com.google.android.gms.internal.icing;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

final class zzbi implements zzbf {
    @GuardedBy
    static zzbi zzdb;
    private final Context zzdc;

    static zzbi zzc(Context context) {
        zzbi zzbi;
        synchronized (zzbi.class) {
            if (zzdb == null) {
                zzdb = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzbi(context) : new zzbi();
            }
            zzbi = zzdb;
        }
        return zzbi;
    }

    private zzbi(Context context) {
        this.zzdc = context;
        this.zzdc.getContentResolver().registerContentObserver(zzay.CONTENT_URI, true, new zzbk(this, null));
    }

    private zzbi() {
        this.zzdc = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzo */
    public final String zzn(String str) {
        if (this.zzdc == null) {
            return null;
        }
        try {
            return (String) zzbg.zza(new zzbj(this, str));
        } catch (SecurityException e) {
            String str2 = "GservicesLoader";
            String str3 = "Unable to read GServices for: ";
            String valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zzp(String str) {
        return zzay.zza(this.zzdc.getContentResolver(), str, (String) null);
    }
}
