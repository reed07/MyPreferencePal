package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@zzark
public final class zzaza {
    private float zzdwx = 1.0f;
    private boolean zzdxd = false;

    public final synchronized void setAppVolume(float f) {
        this.zzdwx = f;
    }

    public final synchronized float zzkj() {
        if (!zzaac()) {
            return 1.0f;
        }
        return this.zzdwx;
    }

    public final synchronized void setAppMuted(boolean z) {
        this.zzdxd = z;
    }

    public final synchronized boolean zzkk() {
        return this.zzdxd;
    }

    private final synchronized boolean zzaac() {
        return this.zzdwx >= BitmapDescriptorFactory.HUE_RED;
    }

    public static float zzbb(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamMaxVolume == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return ((float) streamVolume) / ((float) streamMaxVolume);
    }
}
