package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzbuw implements Cloneable {
    private Object value;
    private zzbuu<?, ?> zzfwq;
    private List<zzbvb> zzfwr = new ArrayList();

    zzbuw() {
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbvb zzbvb) throws IOException {
        List<zzbvb> list = this.zzfwr;
        if (list != null) {
            list.add(zzbvb);
            return;
        }
        Object obj = this.value;
        if (obj instanceof zzbuz) {
            byte[] bArr = zzbvb.zzflp;
            zzbuq zzq = zzbuq.zzq(bArr, 0, bArr.length);
            int zzalm = zzq.zzalm();
            if (zzalm == bArr.length - zzbur.zzfe(zzalm)) {
                zzbuz zza = ((zzbuz) this.value).zza(zzq);
                this.zzfwq = this.zzfwq;
                this.value = zza;
                this.zzfwr = null;
                return;
            }
            throw zzbuy.zzapo();
        } else if (obj instanceof zzbuz[]) {
            Collections.singletonList(zzbvb);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zzbvb);
            throw new NoSuchMethodError();
        }
    }

    /* access modifiers changed from: 0000 */
    public final int zzt() {
        if (this.value == null) {
            int i = 0;
            for (zzbvb zzbvb : this.zzfwr) {
                i += zzbur.zzfl(zzbvb.tag) + 0 + zzbvb.zzflp.length;
            }
            return i;
        }
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbur zzbur) throws IOException {
        if (this.value == null) {
            for (zzbvb zzbvb : this.zzfwr) {
                zzbur.zzge(zzbvb.tag);
                zzbur.zzz(zzbvb.zzflp);
            }
            return;
        }
        throw new NoSuchMethodError();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuw)) {
            return false;
        }
        zzbuw zzbuw = (zzbuw) obj;
        if (this.value == null || zzbuw.value == null) {
            List<zzbvb> list = this.zzfwr;
            if (list != null) {
                List<zzbvb> list2 = zzbuw.zzfwr;
                if (list2 != null) {
                    return list.equals(list2);
                }
            }
            try {
                return Arrays.equals(toByteArray(), zzbuw.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            zzbuu<?, ?> zzbuu = this.zzfwq;
            if (zzbuu != zzbuw.zzfwq) {
                return false;
            }
            if (!zzbuu.zzfwl.isArray()) {
                return this.value.equals(zzbuw.value);
            }
            Object obj2 = this.value;
            if (obj2 instanceof byte[]) {
                return Arrays.equals((byte[]) obj2, (byte[]) zzbuw.value);
            }
            if (obj2 instanceof int[]) {
                return Arrays.equals((int[]) obj2, (int[]) zzbuw.value);
            }
            if (obj2 instanceof long[]) {
                return Arrays.equals((long[]) obj2, (long[]) zzbuw.value);
            }
            if (obj2 instanceof float[]) {
                return Arrays.equals((float[]) obj2, (float[]) zzbuw.value);
            }
            if (obj2 instanceof double[]) {
                return Arrays.equals((double[]) obj2, (double[]) zzbuw.value);
            }
            if (obj2 instanceof boolean[]) {
                return Arrays.equals((boolean[]) obj2, (boolean[]) zzbuw.value);
            }
            return Arrays.deepEquals((Object[]) obj2, (Object[]) zzbuw.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzt()];
        zza(zzbur.zzx(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzapn */
    public final zzbuw clone() {
        zzbuw zzbuw = new zzbuw();
        try {
            zzbuw.zzfwq = this.zzfwq;
            if (this.zzfwr == null) {
                zzbuw.zzfwr = null;
            } else {
                zzbuw.zzfwr.addAll(this.zzfwr);
            }
            if (this.value != null) {
                if (this.value instanceof zzbuz) {
                    zzbuw.value = (zzbuz) ((zzbuz) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbuw.value = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzbuw.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        zzbuw.value = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        zzbuw.value = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        zzbuw.value = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        zzbuw.value = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        zzbuw.value = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzbuz[]) {
                        zzbuz[] zzbuzArr = (zzbuz[]) this.value;
                        zzbuz[] zzbuzArr2 = new zzbuz[zzbuzArr.length];
                        zzbuw.value = zzbuzArr2;
                        while (i < zzbuzArr.length) {
                            zzbuzArr2[i] = (zzbuz) zzbuzArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzbuw;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
