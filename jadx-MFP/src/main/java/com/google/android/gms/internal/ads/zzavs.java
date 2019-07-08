package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzark
public final class zzavs extends zzawa {
    private volatile zzavt zzeeq;
    private volatile zzavq zzefe;
    private volatile zzavr zzeff;
    private volatile zzavx zzefg;

    public zzavs(zzavr zzavr) {
        this.zzeff = zzavr;
    }

    public final void zza(zzavq zzavq) {
        this.zzefe = zzavq;
    }

    public final void zza(zzavt zzavt) {
        this.zzeeq = zzavt;
    }

    public final void zza(zzavx zzavx) {
        this.zzefg = zzavx;
    }

    public final void zzs(IObjectWrapper iObjectWrapper) {
        if (this.zzefe != null) {
            this.zzefe.zzxl();
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzefe != null) {
            this.zzefe.zzct(i);
        }
    }

    public final void zzt(IObjectWrapper iObjectWrapper) {
        if (this.zzeeq != null) {
            this.zzeeq.zzde(ObjectWrapper.unwrap(iObjectWrapper).getClass().getName());
        }
    }

    public final void zzu(IObjectWrapper iObjectWrapper) {
        if (this.zzeff != null) {
            this.zzeff.onRewardedVideoAdOpened();
        }
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        if (this.zzeff != null) {
            this.zzeff.onRewardedVideoStarted();
        }
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        if (this.zzeff != null) {
            this.zzeff.onRewardedVideoAdClosed();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzawd zzawd) {
        if (this.zzeff != null) {
            this.zzeff.zzc(zzawd);
        }
    }

    public final void zzx(IObjectWrapper iObjectWrapper) {
        if (this.zzeff != null) {
            this.zzeff.zzkh();
        }
    }

    public final void zzd(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzeeq != null) {
            this.zzeeq.zza(ObjectWrapper.unwrap(iObjectWrapper).getClass().getName(), i);
        }
    }

    public final void zzy(IObjectWrapper iObjectWrapper) {
        if (this.zzeff != null) {
            this.zzeff.onRewardedVideoAdLeftApplication();
        }
    }

    public final void zzz(IObjectWrapper iObjectWrapper) {
        if (this.zzeff != null) {
            this.zzeff.onRewardedVideoCompleted();
        }
    }

    public final void zzc(Bundle bundle) {
        if (this.zzefg != null) {
            this.zzefg.zzc(bundle);
        }
    }
}
