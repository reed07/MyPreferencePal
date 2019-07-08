package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@zzark
@TargetApi(14)
public final class zzbec implements OnAudioFocusChangeListener {
    private final AudioManager mAudioManager;
    private boolean zzeri;
    private final zzbed zzeui;
    private boolean zzeuj;
    private boolean zzeuk;
    private float zzeul = 1.0f;

    public zzbec(Context context, zzbed zzbed) {
        this.mAudioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.zzeui = zzbed;
    }

    public final void setMuted(boolean z) {
        this.zzeuk = z;
        zzacg();
    }

    public final void setVolume(float f) {
        this.zzeul = f;
        zzacg();
    }

    public final float getVolume() {
        float f = this.zzeuk ? BitmapDescriptorFactory.HUE_RED : this.zzeul;
        if (this.zzeuj) {
            return f;
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    public final void zzacd() {
        this.zzeri = true;
        zzacg();
    }

    public final void zzace() {
        this.zzeri = false;
        zzacg();
    }

    public final void onAudioFocusChange(int i) {
        this.zzeuj = i > 0;
        this.zzeui.zzabd();
    }

    private final void zzacg() {
        boolean z = false;
        boolean z2 = this.zzeri && !this.zzeuk && this.zzeul > BitmapDescriptorFactory.HUE_RED;
        if (z2) {
            boolean z3 = this.zzeuj;
            if (!z3) {
                AudioManager audioManager = this.mAudioManager;
                if (audioManager != null && !z3) {
                    if (audioManager.requestAudioFocus(this, 3, 2) == 1) {
                        z = true;
                    }
                    this.zzeuj = z;
                }
                this.zzeui.zzabd();
                return;
            }
        }
        if (!z2) {
            boolean z4 = this.zzeuj;
            if (z4) {
                AudioManager audioManager2 = this.mAudioManager;
                if (audioManager2 != null && z4) {
                    if (audioManager2.abandonAudioFocus(this) == 0) {
                        z = true;
                    }
                    this.zzeuj = z;
                }
                this.zzeui.zzabd();
            }
        }
    }
}
