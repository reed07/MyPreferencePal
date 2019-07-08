package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class zzhi implements zzgi {
    private float zzaag = 1.0f;
    private float zzaah = 1.0f;
    private ByteBuffer zzadm = zzabh;
    private int zzael = -1;
    private ByteBuffer zzaep = zzabh;
    private boolean zzaeq;
    private zzhh zzafx;
    private ShortBuffer zzafy = this.zzaep.asShortBuffer();
    private long zzafz;
    private long zzaga;
    private int zzzt = -1;

    public final int zzcp() {
        return 2;
    }

    public final float zzb(float f) {
        this.zzaag = zzqe.zza(f, 0.1f, 8.0f);
        return this.zzaag;
    }

    public final float zzc(float f) {
        this.zzaah = zzqe.zza(f, 0.1f, 8.0f);
        return f;
    }

    public final long zzdm() {
        return this.zzafz;
    }

    public final long zzdn() {
        return this.zzaga;
    }

    public final boolean zzb(int i, int i2, int i3) throws zzgj {
        if (i3 != 2) {
            throw new zzgj(i, i2, i3);
        } else if (this.zzael == i && this.zzzt == i2) {
            return false;
        } else {
            this.zzael = i;
            this.zzzt = i2;
            return true;
        }
    }

    public final boolean isActive() {
        return Math.abs(this.zzaag - 1.0f) >= 0.01f || Math.abs(this.zzaah - 1.0f) >= 0.01f;
    }

    public final int zzco() {
        return this.zzzt;
    }

    public final void zzi(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.zzafz += (long) remaining;
            this.zzafx.zza(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int zzdk = (this.zzafx.zzdk() * this.zzzt) << 1;
        if (zzdk > 0) {
            if (this.zzaep.capacity() < zzdk) {
                this.zzaep = ByteBuffer.allocateDirect(zzdk).order(ByteOrder.nativeOrder());
                this.zzafy = this.zzaep.asShortBuffer();
            } else {
                this.zzaep.clear();
                this.zzafy.clear();
            }
            this.zzafx.zzb(this.zzafy);
            this.zzaga += (long) zzdk;
            this.zzaep.limit(zzdk);
            this.zzadm = this.zzaep;
        }
    }

    public final void zzcq() {
        this.zzafx.zzcq();
        this.zzaeq = true;
    }

    public final ByteBuffer zzcr() {
        ByteBuffer byteBuffer = this.zzadm;
        this.zzadm = zzabh;
        return byteBuffer;
    }

    public final boolean zzcj() {
        if (this.zzaeq) {
            zzhh zzhh = this.zzafx;
            if (zzhh == null || zzhh.zzdk() == 0) {
                return true;
            }
        }
        return false;
    }

    public final void flush() {
        this.zzafx = new zzhh(this.zzael, this.zzzt);
        this.zzafx.setSpeed(this.zzaag);
        this.zzafx.zza(this.zzaah);
        this.zzadm = zzabh;
        this.zzafz = 0;
        this.zzaga = 0;
        this.zzaeq = false;
    }

    public final void reset() {
        this.zzafx = null;
        this.zzaep = zzabh;
        this.zzafy = this.zzaep.asShortBuffer();
        this.zzadm = zzabh;
        this.zzzt = -1;
        this.zzael = -1;
        this.zzafz = 0;
        this.zzaga = 0;
        this.zzaeq = false;
    }
}
