package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzce implements Serializable, Iterable<Byte> {
    public static final zzce zzfx = new zzco(zzdl.zzkq);
    private static final zzck zzfy = (zzcb.zzal() ? new zzcp(null) : new zzci(null));
    private static final Comparator<zzce> zzga = new zzcg();
    private int zzfz = 0;

    zzce() {
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    /* access modifiers changed from: protected */
    public abstract int zza(int i, int i2, int i3);

    public abstract zzce zza(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String zza(Charset charset);

    /* access modifiers changed from: 0000 */
    public abstract void zza(zzcd zzcd) throws IOException;

    public abstract boolean zzap();

    public abstract byte zzk(int i);

    /* access modifiers changed from: 0000 */
    public abstract byte zzl(int i);

    public static zzce zzt(String str) {
        return new zzco(str.getBytes(zzdl.UTF_8));
    }

    public final String zzao() {
        return size() == 0 ? "" : zza(zzdl.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzfz;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzfz = i;
        }
        return i;
    }

    static zzcm zzm(int i) {
        return new zzcm(i, null);
    }

    /* access modifiers changed from: protected */
    public final int zzaq() {
        return this.zzfz;
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public /* synthetic */ Iterator iterator() {
        return new zzcf(this);
    }
}
