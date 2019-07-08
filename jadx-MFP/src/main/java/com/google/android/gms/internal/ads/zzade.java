package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzark
public final class zzade extends Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzdav;
    private final zzadb zzddr;

    public zzade(zzadb zzadb) {
        Drawable drawable;
        this.zzddr = zzadb;
        Uri uri = null;
        try {
            IObjectWrapper zzsa = this.zzddr.zzsa();
            drawable = zzsa != null ? (Drawable) ObjectWrapper.unwrap(zzsa) : null;
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            drawable = null;
        }
        this.mDrawable = drawable;
        try {
            uri = this.zzddr.getUri();
        } catch (RemoteException e2) {
            zzbbd.zzb("", e2);
        }
        this.mUri = uri;
        double d = 1.0d;
        try {
            d = this.zzddr.getScale();
        } catch (RemoteException e3) {
            zzbbd.zzb("", e3);
        }
        this.zzdav = d;
    }

    public final Drawable getDrawable() {
        return this.mDrawable;
    }

    public final Uri getUri() {
        return this.mUri;
    }

    public final double getScale() {
        return this.zzdav;
    }
}
