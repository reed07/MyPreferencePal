package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import javax.annotation.Nonnull;

abstract class zzwt<T> {
    @Nullable
    private static final zzxw zzcli = zzps();

    zzwt() {
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T zza(zzxw zzxw) throws RemoteException;

    /* access modifiers changed from: protected */
    @Nonnull
    public abstract T zzpq();

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T zzpr() throws RemoteException;

    @Nullable
    private static zzxw zzps() {
        try {
            Object newInstance = zzwj.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzxx.asInterface((IBinder) newInstance);
            }
            zzbbd.zzeo("ClientApi class is not an instance of IBinder.");
            return null;
        } catch (Exception unused) {
            zzbbd.zzeo("Failed to instantiate ClientApi class.");
            return null;
        }
    }

    @Nullable
    private final T zzpt() {
        zzxw zzxw = zzcli;
        if (zzxw == null) {
            zzbbd.zzeo("ClientApi class cannot be loaded.");
            return null;
        }
        try {
            return zza(zzxw);
        } catch (RemoteException e) {
            zzbbd.zzc("Cannot invoke local loader using ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private final T zzpu() {
        try {
            return zzpr();
        } catch (RemoteException e) {
            zzbbd.zzc("Cannot invoke remote loader.", e);
            return null;
        }
    }

    public final T zzd(Context context, boolean z) {
        T t;
        boolean z2 = true;
        if (!z) {
            zzwu.zzpv();
            if (!zzbat.zzc(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                zzbbd.zzdn("Google Play Services is not available.");
                z = true;
            }
        }
        if (DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID)) {
            z = true;
        }
        zzaan.initialize(context);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwv)).booleanValue()) {
            z = false;
        }
        if (z) {
            t = zzpt();
            if (t == null) {
                t = zzpu();
            }
        } else {
            T zzpu = zzpu();
            int i = zzpu == null ? 1 : 0;
            if (i != 0) {
                if (zzwu.zzqc().nextInt(((Integer) zzwu.zzpz().zzd(zzaan.zzcyq)).intValue()) != 0) {
                    z2 = false;
                }
                if (z2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", "dynamite_load");
                    bundle.putInt("is_missing", i);
                    zzwu.zzpv().zza(context, zzwu.zzqb().zzdp, "gmob-apps", bundle, true);
                }
            }
            t = zzpu == null ? zzpt() : zzpu;
        }
        return t == null ? zzpq() : t;
    }
}
