package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzte implements Serializable, Iterable<Byte> {
    public static final zzte zzbts = new zzto(zzuq.zzbzc);
    private static final zztk zzbtt = (zztb.zzub() ? new zztp(null) : new zzti(null));
    private static final Comparator<zzte> zzbtu = new zztg();
    private int zzbsk = 0;

    zzte() {
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    /* access modifiers changed from: protected */
    public abstract int zza(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract String zza(Charset charset);

    /* access modifiers changed from: 0000 */
    public abstract void zza(zztd zztd) throws IOException;

    public abstract byte zzam(int i);

    /* access modifiers changed from: 0000 */
    public abstract byte zzan(int i);

    public abstract zzte zzb(int i, int i2);

    public abstract boolean zzue();

    public static zzte zzb(byte[] bArr, int i, int i2) {
        zzb(i, i + i2, bArr.length);
        return new zzto(zzbtt.zzc(bArr, i, i2));
    }

    static zzte zzi(byte[] bArr) {
        return new zzto(bArr);
    }

    public static zzte zzga(String str) {
        return new zzto(str.getBytes(zzuq.UTF_8));
    }

    public final String zzud() {
        return size() == 0 ? "" : zza(zzuq.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzbsk;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzbsk = i;
        }
        return i;
    }

    static zztm zzao(int i) {
        return new zztm(i, null);
    }

    /* access modifiers changed from: protected */
    public final int zzuf() {
        return this.zzbsk;
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
        return new zztf(this);
    }
}
