package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

final class zzcs {
    private int zzabo;
    private ByteArrayOutputStream zzabp = new ByteArrayOutputStream();
    private final /* synthetic */ zzcr zzabq;

    public zzcs(zzcr zzcr) {
        this.zzabq = zzcr;
    }

    public final boolean zze(zzck zzck) {
        Preconditions.checkNotNull(zzck);
        if (this.zzabo + 1 > zzbx.zzec()) {
            return false;
        }
        String zza = this.zzabq.zza(zzck, false);
        if (zza == null) {
            this.zzabq.zzby().zza(zzck, "Error formatting hit");
            return true;
        }
        byte[] bytes = zza.getBytes();
        int length = bytes.length;
        if (length > zzbx.zzdy()) {
            this.zzabq.zzby().zza(zzck, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzabp.size() > 0) {
            length++;
        }
        if (this.zzabp.size() + length > ((Integer) zzcf.zzzv.get()).intValue()) {
            return false;
        }
        try {
            if (this.zzabp.size() > 0) {
                this.zzabp.write(zzcr.zzabn);
            }
            this.zzabp.write(bytes);
            this.zzabo++;
            return true;
        } catch (IOException e) {
            this.zzabq.zze("Failed to write payload when batching hits", e);
            return true;
        }
    }

    public final int zzfe() {
        return this.zzabo;
    }

    public final byte[] getPayload() {
        return this.zzabp.toByteArray();
    }
}
