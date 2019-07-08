package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.TextureView;

@zzark
@TargetApi(14)
public abstract class zzbdi extends TextureView implements zzbed {
    protected final zzbds zzera = new zzbds();
    protected final zzbec zzerb;

    public zzbdi(Context context) {
        super(context);
        this.zzerb = new zzbec(context, this);
    }

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void pause();

    public abstract void play();

    public abstract void seekTo(int i);

    public abstract void setVideoPath(String str);

    public abstract void stop();

    public abstract void zza(float f, float f2);

    public abstract void zza(zzbdh zzbdh);

    public abstract String zzaaz();

    public abstract void zzabd();

    public void zzcz(int i) {
    }

    public void zzda(int i) {
    }

    public void zzdb(int i) {
    }

    public void zzdc(int i) {
    }
}
