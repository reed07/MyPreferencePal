package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import java.util.WeakHashMap;

@zzark
public final class zzaea implements NativeCustomTemplateAd {
    private static WeakHashMap<IBinder, zzaea> zzddy = new WeakHashMap<>();
    private final VideoController zzcml = new VideoController();
    private final zzadx zzddz;
    private final MediaView zzdea;

    @VisibleForTesting
    private zzaea(zzadx zzadx) {
        Context context;
        this.zzddz = zzadx;
        MediaView mediaView = null;
        try {
            context = (Context) ObjectWrapper.unwrap(zzadx.zzsg());
        } catch (RemoteException | NullPointerException e) {
            zzbbd.zzb("", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.zzddz.zzi(ObjectWrapper.wrap(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                zzbbd.zzb("", e2);
            }
        }
        this.zzdea = mediaView;
    }

    public static zzaea zza(zzadx zzadx) {
        synchronized (zzddy) {
            zzaea zzaea = (zzaea) zzddy.get(zzadx.asBinder());
            if (zzaea != null) {
                return zzaea;
            }
            zzaea zzaea2 = new zzaea(zzadx);
            zzddy.put(zzadx.asBinder(), zzaea2);
            return zzaea2;
        }
    }

    public final zzadx zzsx() {
        return this.zzddz;
    }

    public final CharSequence getText(String str) {
        try {
            return this.zzddz.zzbq(str);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Image getImage(String str) {
        try {
            zzadb zzbr = this.zzddz.zzbr(str);
            if (zzbr != null) {
                return new zzade(zzbr);
            }
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
        return null;
    }

    public final VideoController getVideoController() {
        try {
            zzyp videoController = this.zzddz.getVideoController();
            if (videoController != null) {
                this.zzcml.zza(videoController);
            }
        } catch (RemoteException e) {
            zzbbd.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzcml;
    }

    public final MediaView getVideoMediaView() {
        return this.zzdea;
    }

    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzddz.getAvailableAssetNames();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final String getCustomTemplateId() {
        try {
            return this.zzddz.getCustomTemplateId();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final void performClick(String str) {
        try {
            this.zzddz.performClick(str);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzddz.recordImpression();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final void destroy() {
        try {
            this.zzddz.destroy();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }
}
