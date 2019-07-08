package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzaoq;
import com.google.android.gms.internal.ads.zzark;

@zzark
public final class zzs extends zzaoq {
    private AdOverlayInfoParcel zzdsg;
    private boolean zzdsh = false;
    private boolean zzdsi = false;
    private Activity zzug;

    public zzs(Activity activity, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzdsg = adOverlayInfoParcel;
        this.zzug = activity;
    }

    public final void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
    }

    public final void onBackPressed() throws RemoteException {
    }

    public final void onRestart() throws RemoteException {
    }

    public final void onStart() throws RemoteException {
    }

    public final void zzay() throws RemoteException {
    }

    public final void zzq(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final boolean zzvq() throws RemoteException {
        return false;
    }

    public final void onCreate(Bundle bundle) {
        boolean z = false;
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdsg;
        if (adOverlayInfoParcel == null) {
            this.zzug.finish();
        } else if (z) {
            this.zzug.finish();
        } else {
            if (bundle == null) {
                if (adOverlayInfoParcel.zzdrt != null) {
                    this.zzdsg.zzdrt.onAdClicked();
                }
                if (!(this.zzug.getIntent() == null || !this.zzug.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) || this.zzdsg.zzdru == null)) {
                    this.zzdsg.zzdru.zziw();
                }
            }
            zzbv.zzlc();
            if (!zza.zza((Context) this.zzug, this.zzdsg.zzdrs, this.zzdsg.zzdrz)) {
                this.zzug.finish();
            }
        }
    }

    public final void onResume() throws RemoteException {
        if (this.zzdsh) {
            this.zzug.finish();
            return;
        }
        this.zzdsh = true;
        if (this.zzdsg.zzdru != null) {
            this.zzdsg.zzdru.onResume();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdsh);
    }

    public final void onPause() throws RemoteException {
        if (this.zzdsg.zzdru != null) {
            this.zzdsg.zzdru.onPause();
        }
        if (this.zzug.isFinishing()) {
            zzvy();
        }
    }

    public final void onStop() throws RemoteException {
        if (this.zzug.isFinishing()) {
            zzvy();
        }
    }

    public final void onDestroy() throws RemoteException {
        if (this.zzug.isFinishing()) {
            zzvy();
        }
    }

    private final synchronized void zzvy() {
        if (!this.zzdsi) {
            if (this.zzdsg.zzdru != null) {
                this.zzdsg.zzdru.zziv();
            }
            this.zzdsi = true;
        }
    }
}
