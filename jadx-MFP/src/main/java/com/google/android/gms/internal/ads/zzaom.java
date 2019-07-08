package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaom {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zzdqn = false;
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zztd = false;
    @VisibleForTesting
    private zzbiz zzdqo;

    public final boolean zzk(Context context) {
        synchronized (sLock) {
            if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue()) {
                return false;
            }
            if (zztd) {
                return true;
            }
            try {
                zzl(context);
                boolean zzab = this.zzdqo.zzab(ObjectWrapper.wrap(context));
                zztd = zzab;
                return zzab;
            } catch (RemoteException e) {
                e = e;
                zzbbd.zzd("#007 Could not call remote method.", e);
                return false;
            } catch (NullPointerException e2) {
                e = e2;
                zzbbd.zzd("#007 Could not call remote method.", e);
                return false;
            }
        }
    }

    @VisibleForTesting
    private final void zzl(Context context) {
        synchronized (sLock) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue() && !zzdqn) {
                try {
                    zzdqn = true;
                    this.zzdqo = (zzbiz) zzbbe.zza(context, "com.google.android.gms.ads.omid.DynamiteOmid", zzaon.zzccb);
                } catch (zzbbg e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Nullable
    public final String getVersion(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue()) {
            return null;
        }
        try {
            zzl(context);
            String str = "a.";
            String valueOf = String.valueOf(this.zzdqo.getVersion());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        } catch (RemoteException | NullPointerException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    @Nullable
    public final IObjectWrapper zza(String str, WebView webView, String str2, String str3, String str4) {
        return zza(str, webView, str2, str3, str4, "Google");
    }

    @Nullable
    public final IObjectWrapper zza(String str, WebView webView, String str2, String str3, String str4, String str5) {
        synchronized (sLock) {
            try {
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue()) {
                    if (zztd) {
                        try {
                            return this.zzdqo.zza(str, ObjectWrapper.wrap(webView), str2, str3, str4, str5);
                        } catch (RemoteException | NullPointerException e) {
                            zzbbd.zzd("#007 Could not call remote method.", e);
                            return null;
                        }
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    public final void zzo(IObjectWrapper iObjectWrapper) {
        synchronized (sLock) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue()) {
                if (zztd) {
                    try {
                        this.zzdqo.zzo(iObjectWrapper);
                    } catch (RemoteException | NullPointerException e) {
                        zzbbd.zzd("#007 Could not call remote method.", e);
                    }
                }
            }
        }
    }

    public final void zzp(IObjectWrapper iObjectWrapper) {
        synchronized (sLock) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue()) {
                if (zztd) {
                    try {
                        this.zzdqo.zzp(iObjectWrapper);
                    } catch (RemoteException | NullPointerException e) {
                        zzbbd.zzd("#007 Could not call remote method.", e);
                    }
                }
            }
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, View view) {
        synchronized (sLock) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwy)).booleanValue()) {
                if (zztd) {
                    try {
                        this.zzdqo.zza(iObjectWrapper, ObjectWrapper.wrap(view));
                    } catch (RemoteException | NullPointerException e) {
                        zzbbd.zzd("#007 Could not call remote method.", e);
                    }
                }
            }
        }
    }
}
