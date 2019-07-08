package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

@zzark
public final class zzbbe {
    public static <T> T zza(Context context, String str, zzbbf<IBinder, T> zzbbf) throws zzbbg {
        try {
            return zzbbf.apply(zzbn(context).instantiate(str));
        } catch (Exception e) {
            throw new zzbbg(e);
        }
    }

    public static Context zzbm(Context context) throws zzbbg {
        return zzbn(context).getModuleContext();
    }

    private static DynamiteModule zzbn(Context context) throws zzbbg {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
        } catch (Exception e) {
            throw new zzbbg(e);
        }
    }
}
