package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzyf implements Cloneable {
    private Object value;
    private zzyd<?, ?> zzcfc;
    private List<zzyk> zzcfd = new ArrayList();

    zzyf() {
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzyk zzyk) throws IOException {
        Object obj;
        List<zzyk> list = this.zzcfd;
        if (list != null) {
            list.add(zzyk);
            return;
        }
        Object obj2 = this.value;
        if (obj2 instanceof zzyi) {
            byte[] bArr = zzyk.zzbtz;
            zzxz zzj = zzxz.zzj(bArr, 0, bArr.length);
            int zzvb = zzj.zzvb();
            if (zzvb == bArr.length - zzya.zzbe(zzvb)) {
                obj = ((zzyi) this.value).zza(zzj);
            } else {
                throw zzyh.zzzd();
            }
        } else if (obj2 instanceof zzyi[]) {
            zzyi[] zzyiArr = (zzyi[]) this.zzcfc.zzai(Collections.singletonList(zzyk));
            zzyi[] zzyiArr2 = (zzyi[]) this.value;
            Object obj3 = (zzyi[]) Arrays.copyOf(zzyiArr2, zzyiArr2.length + zzyiArr.length);
            System.arraycopy(zzyiArr, 0, obj3, zzyiArr2.length, zzyiArr.length);
            obj = obj3;
        } else if (obj2 instanceof zzvv) {
            obj = ((zzvv) this.value).zzwh().zza((zzvv) this.zzcfc.zzai(Collections.singletonList(zzyk))).zzwo();
        } else if (obj2 instanceof zzvv[]) {
            zzvv[] zzvvArr = (zzvv[]) this.zzcfc.zzai(Collections.singletonList(zzyk));
            zzvv[] zzvvArr2 = (zzvv[]) this.value;
            Object obj4 = (zzvv[]) Arrays.copyOf(zzvvArr2, zzvvArr2.length + zzvvArr.length);
            System.arraycopy(zzvvArr, 0, obj4, zzvvArr2.length, zzvvArr.length);
            obj = obj4;
        } else {
            obj = this.zzcfc.zzai(Collections.singletonList(zzyk));
        }
        this.zzcfc = this.zzcfc;
        this.value = obj;
        this.zzcfd = null;
    }

    /* access modifiers changed from: 0000 */
    public final <T> T zzb(zzyd<?, T> zzyd) {
        if (this.value == null) {
            this.zzcfc = zzyd;
            this.value = zzyd.zzai(this.zzcfd);
            this.zzcfd = null;
        } else if (!this.zzcfc.equals(zzyd)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: 0000 */
    public final int zzf() {
        Object obj = this.value;
        if (obj != null) {
            zzyd<?, ?> zzyd = this.zzcfc;
            if (!zzyd.zzcex) {
                return zzyd.zzao(obj);
            }
            int length = Array.getLength(obj);
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                Object obj2 = Array.get(obj, i2);
                if (obj2 != null) {
                    i += zzyd.zzao(obj2);
                }
            }
            return i;
        }
        int i3 = 0;
        for (zzyk zzyk : this.zzcfd) {
            i3 += zzya.zzbl(zzyk.tag) + 0 + zzyk.zzbtz.length;
        }
        return i3;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzya zzya) throws IOException {
        Object obj = this.value;
        if (obj != null) {
            zzyd<?, ?> zzyd = this.zzcfc;
            if (zzyd.zzcex) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzyd.zza(obj2, zzya);
                    }
                }
                return;
            }
            zzyd.zza(obj, zzya);
            return;
        }
        for (zzyk zzyk : this.zzcfd) {
            zzya.zzcd(zzyk.tag);
            zzya.zzp(zzyk.zzbtz);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzyf)) {
            return false;
        }
        zzyf zzyf = (zzyf) obj;
        if (this.value == null || zzyf.value == null) {
            List<zzyk> list = this.zzcfd;
            if (list != null) {
                List<zzyk> list2 = zzyf.zzcfd;
                if (list2 != null) {
                    return list.equals(list2);
                }
            }
            try {
                return Arrays.equals(toByteArray(), zzyf.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            zzyd<?, ?> zzyd = this.zzcfc;
            if (zzyd != zzyf.zzcfc) {
                return false;
            }
            if (!zzyd.zzcew.isArray()) {
                return this.value.equals(zzyf.value);
            }
            Object obj2 = this.value;
            if (obj2 instanceof byte[]) {
                return Arrays.equals((byte[]) obj2, (byte[]) zzyf.value);
            }
            if (obj2 instanceof int[]) {
                return Arrays.equals((int[]) obj2, (int[]) zzyf.value);
            }
            if (obj2 instanceof long[]) {
                return Arrays.equals((long[]) obj2, (long[]) zzyf.value);
            }
            if (obj2 instanceof float[]) {
                return Arrays.equals((float[]) obj2, (float[]) zzyf.value);
            }
            if (obj2 instanceof double[]) {
                return Arrays.equals((double[]) obj2, (double[]) zzyf.value);
            }
            if (obj2 instanceof boolean[]) {
                return Arrays.equals((boolean[]) obj2, (boolean[]) zzyf.value);
            }
            return Arrays.deepEquals((Object[]) obj2, (Object[]) zzyf.value);
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
        byte[] bArr = new byte[zzf()];
        zza(zzya.zzo(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzzc */
    public final zzyf clone() {
        zzyf zzyf = new zzyf();
        try {
            zzyf.zzcfc = this.zzcfc;
            if (this.zzcfd == null) {
                zzyf.zzcfd = null;
            } else {
                zzyf.zzcfd.addAll(this.zzcfd);
            }
            if (this.value != null) {
                if (this.value instanceof zzyi) {
                    zzyf.value = (zzyi) ((zzyi) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzyf.value = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzyf.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        zzyf.value = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        zzyf.value = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        zzyf.value = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        zzyf.value = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        zzyf.value = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzyi[]) {
                        zzyi[] zzyiArr = (zzyi[]) this.value;
                        zzyi[] zzyiArr2 = new zzyi[zzyiArr.length];
                        zzyf.value = zzyiArr2;
                        while (i < zzyiArr.length) {
                            zzyiArr2[i] = (zzyi) zzyiArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzyf;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
