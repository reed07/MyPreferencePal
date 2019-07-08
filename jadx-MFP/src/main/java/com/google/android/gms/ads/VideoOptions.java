package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzzw;

@zzark
public final class VideoOptions {
    private final boolean zzwd;
    private final boolean zzwe;
    private final boolean zzwf;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzwd = true;
        /* access modifiers changed from: private */
        public boolean zzwe = false;
        /* access modifiers changed from: private */
        public boolean zzwf = false;

        public final Builder setStartMuted(boolean z) {
            this.zzwd = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzwe = z;
            return this;
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzwf = z;
            return this;
        }

        public final VideoOptions build() {
            return new VideoOptions(this);
        }
    }

    public VideoOptions(zzzw zzzw) {
        this.zzwd = zzzw.zzcnf;
        this.zzwe = zzzw.zzcng;
        this.zzwf = zzzw.zzcnh;
    }

    private VideoOptions(Builder builder) {
        this.zzwd = builder.zzwd;
        this.zzwe = builder.zzwe;
        this.zzwf = builder.zzwf;
    }

    public final boolean getStartMuted() {
        return this.zzwd;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzwe;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzwf;
    }
}
