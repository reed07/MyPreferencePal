package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;

@TargetApi(19)
final class zzgv extends zzgu {
    private final AudioTimestamp zzaef = new AudioTimestamp();
    private long zzaeg;
    private long zzaeh;
    private long zzaei;

    public zzgv() {
        super(null);
    }

    public final void zza(AudioTrack audioTrack, boolean z) {
        super.zza(audioTrack, z);
        this.zzaeg = 0;
        this.zzaeh = 0;
        this.zzaei = 0;
    }

    public final boolean zzdf() {
        boolean timestamp = this.zzacf.getTimestamp(this.zzaef);
        if (timestamp) {
            long j = this.zzaef.framePosition;
            if (this.zzaeh > j) {
                this.zzaeg++;
            }
            this.zzaeh = j;
            this.zzaei = j + (this.zzaeg << 32);
        }
        return timestamp;
    }

    public final long zzdg() {
        return this.zzaef.nanoTime;
    }

    public final long zzdh() {
        return this.zzaei;
    }
}
