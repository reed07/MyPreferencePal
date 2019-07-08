package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuh;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzuf<FieldDescriptorType extends zzuh<FieldDescriptorType>> {
    private static final zzuf zzbvl = new zzuf(true);
    private boolean zzbqa;
    private final zzwo<FieldDescriptorType, Object> zzbvj = zzwo.zzbw(16);
    private boolean zzbvk = false;

    private zzuf() {
    }

    private zzuf(boolean z) {
        zzsw();
    }

    public static <T extends zzuh<T>> zzuf<T> zzvw() {
        return zzbvl;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isEmpty() {
        return this.zzbvj.isEmpty();
    }

    public final void zzsw() {
        if (!this.zzbqa) {
            this.zzbvj.zzsw();
            this.zzbqa = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzbqa;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzuf)) {
            return false;
        }
        return this.zzbvj.equals(((zzuf) obj).zzbvj);
    }

    public final int hashCode() {
        return this.zzbvj.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzbvk) {
            return new zzvb(this.zzbvj.entrySet().iterator());
        }
        return this.zzbvj.entrySet().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzbvk) {
            return new zzvb(this.zzbvj.zzye().iterator());
        }
        return this.zzbvj.zzye().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzbvj.get(fielddescriptortype);
        return obj instanceof zzuy ? zzuy.zzwz() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzwb()) {
            zza(fielddescriptortype.zzvz(), obj);
            r7 = obj;
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzvz(), obj2);
            }
            r7 = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (r7 instanceof zzuy) {
            this.zzbvk = true;
        }
        this.zzbvj.put(fielddescriptortype, r7);
    }

    private static void zza(zzxs zzxs, Object obj) {
        zzuq.checkNotNull(obj);
        boolean z = false;
        switch (zzug.zzbvm[zzxs.zzyv().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzte) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzur)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzvv) || (obj instanceof zzuy)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzbvj.zzyc(); i++) {
            if (!zzc(this.zzbvj.zzbx(i))) {
                return false;
            }
        }
        for (Entry zzc : this.zzbvj.zzyd()) {
            if (!zzc(zzc)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzc(Entry<FieldDescriptorType, Object> entry) {
        zzuh zzuh = (zzuh) entry.getKey();
        if (zzuh.zzwa() == zzxx.MESSAGE) {
            if (zzuh.zzwb()) {
                for (zzvv isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzvv) {
                    if (!((zzvv) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzuy) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzuf<FieldDescriptorType> zzuf) {
        for (int i = 0; i < zzuf.zzbvj.zzyc(); i++) {
            zzd(zzuf.zzbvj.zzbx(i));
        }
        for (Entry zzd : zzuf.zzbvj.zzyd()) {
            zzd(zzd);
        }
    }

    private static Object zzz(Object obj) {
        if (obj instanceof zzwb) {
            return ((zzwb) obj).zzxp();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzd(Entry<FieldDescriptorType, Object> entry) {
        Object obj;
        zzuh zzuh = (zzuh) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzuy) {
            value = zzuy.zzwz();
        }
        if (zzuh.zzwb()) {
            Object zza = zza((FieldDescriptorType) zzuh);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzz : (List) value) {
                ((List) zza).add(zzz(zzz));
            }
            this.zzbvj.put(zzuh, zza);
        } else if (zzuh.zzwa() == zzxx.MESSAGE) {
            Object zza2 = zza((FieldDescriptorType) zzuh);
            if (zza2 == null) {
                this.zzbvj.put(zzuh, zzz(value));
                return;
            }
            if (zza2 instanceof zzwb) {
                obj = zzuh.zza((zzwb) zza2, (zzwb) value);
            } else {
                obj = zzuh.zza(((zzvv) zza2).zzwh(), (zzvv) value).zzwo();
            }
            this.zzbvj.put(zzuh, obj);
        } else {
            this.zzbvj.put(zzuh, zzz(value));
        }
    }

    static void zza(zztv zztv, zzxs zzxs, int i, Object obj) throws IOException {
        if (zzxs == zzxs.GROUP) {
            zzvv zzvv = (zzvv) obj;
            zzuq.zzf(zzvv);
            zztv.zzc(i, 3);
            zzvv.zzb(zztv);
            zztv.zzc(i, 4);
            return;
        }
        zztv.zzc(i, zzxs.zzyw());
        switch (zzug.zzbun[zzxs.ordinal()]) {
            case 1:
                zztv.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zztv.zza(((Float) obj).floatValue());
                return;
            case 3:
                zztv.zzat(((Long) obj).longValue());
                return;
            case 4:
                zztv.zzat(((Long) obj).longValue());
                return;
            case 5:
                zztv.zzaz(((Integer) obj).intValue());
                return;
            case 6:
                zztv.zzav(((Long) obj).longValue());
                return;
            case 7:
                zztv.zzbc(((Integer) obj).intValue());
                return;
            case 8:
                zztv.zzs(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzvv) obj).zzb(zztv);
                return;
            case 10:
                zztv.zzb((zzvv) obj);
                return;
            case 11:
                if (obj instanceof zzte) {
                    zztv.zza((zzte) obj);
                    return;
                } else {
                    zztv.zzgb((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzte) {
                    zztv.zza((zzte) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zztv.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zztv.zzba(((Integer) obj).intValue());
                return;
            case 14:
                zztv.zzbc(((Integer) obj).intValue());
                return;
            case 15:
                zztv.zzav(((Long) obj).longValue());
                return;
            case 16:
                zztv.zzbb(((Integer) obj).intValue());
                return;
            case 17:
                zztv.zzau(((Long) obj).longValue());
                return;
            case 18:
                if (!(obj instanceof zzur)) {
                    zztv.zzaz(((Integer) obj).intValue());
                    break;
                } else {
                    zztv.zzaz(((zzur) obj).zzc());
                    return;
                }
        }
    }

    public final int zzvx() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzbvj.zzyc(); i2++) {
            Entry zzbx = this.zzbvj.zzbx(i2);
            i += zzb((zzuh) zzbx.getKey(), zzbx.getValue());
        }
        for (Entry entry : this.zzbvj.zzyd()) {
            i += zzb((zzuh) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzvy() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzbvj.zzyc(); i2++) {
            i += zze(this.zzbvj.zzbx(i2));
        }
        for (Entry zze : this.zzbvj.zzyd()) {
            i += zze(zze);
        }
        return i;
    }

    private static int zze(Entry<FieldDescriptorType, Object> entry) {
        zzuh zzuh = (zzuh) entry.getKey();
        Object value = entry.getValue();
        if (zzuh.zzwa() != zzxx.MESSAGE || zzuh.zzwb() || zzuh.zzwc()) {
            return zzb(zzuh, value);
        }
        if (value instanceof zzuy) {
            return zztv.zzb(((zzuh) entry.getKey()).zzc(), (zzvc) (zzuy) value);
        }
        return zztv.zzd(((zzuh) entry.getKey()).zzc(), (zzvv) value);
    }

    static int zza(zzxs zzxs, int i, Object obj) {
        int zzbd = zztv.zzbd(i);
        if (zzxs == zzxs.GROUP) {
            zzuq.zzf((zzvv) obj);
            zzbd <<= 1;
        }
        return zzbd + zzb(zzxs, obj);
    }

    private static int zzb(zzxs zzxs, Object obj) {
        switch (zzug.zzbun[zzxs.ordinal()]) {
            case 1:
                return zztv.zzc(((Double) obj).doubleValue());
            case 2:
                return zztv.zzb(((Float) obj).floatValue());
            case 3:
                return zztv.zzaw(((Long) obj).longValue());
            case 4:
                return zztv.zzax(((Long) obj).longValue());
            case 5:
                return zztv.zzbe(((Integer) obj).intValue());
            case 6:
                return zztv.zzaz(((Long) obj).longValue());
            case 7:
                return zztv.zzbh(((Integer) obj).intValue());
            case 8:
                return zztv.zzt(((Boolean) obj).booleanValue());
            case 9:
                return zztv.zzd((zzvv) obj);
            case 10:
                if (obj instanceof zzuy) {
                    return zztv.zza((zzvc) (zzuy) obj);
                }
                return zztv.zzc((zzvv) obj);
            case 11:
                if (obj instanceof zzte) {
                    return zztv.zzb((zzte) obj);
                }
                return zztv.zzgc((String) obj);
            case 12:
                if (obj instanceof zzte) {
                    return zztv.zzb((zzte) obj);
                }
                return zztv.zzk((byte[]) obj);
            case 13:
                return zztv.zzbf(((Integer) obj).intValue());
            case 14:
                return zztv.zzbi(((Integer) obj).intValue());
            case 15:
                return zztv.zzba(((Long) obj).longValue());
            case 16:
                return zztv.zzbg(((Integer) obj).intValue());
            case 17:
                return zztv.zzay(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzur) {
                    return zztv.zzbj(((zzur) obj).zzc());
                }
                return zztv.zzbj(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzuh<?> zzuh, Object obj) {
        zzxs zzvz = zzuh.zzvz();
        int zzc = zzuh.zzc();
        if (!zzuh.zzwb()) {
            return zza(zzvz, zzc, obj);
        }
        int i = 0;
        if (zzuh.zzwc()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzvz, zzb);
            }
            return zztv.zzbd(zzc) + i + zztv.zzbl(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzvz, zzc, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzuf zzuf = new zzuf();
        for (int i = 0; i < this.zzbvj.zzyc(); i++) {
            Entry zzbx = this.zzbvj.zzbx(i);
            zzuf.zza((FieldDescriptorType) (zzuh) zzbx.getKey(), zzbx.getValue());
        }
        for (Entry entry : this.zzbvj.zzyd()) {
            zzuf.zza((FieldDescriptorType) (zzuh) entry.getKey(), entry.getValue());
        }
        zzuf.zzbvk = this.zzbvk;
        return zzuf;
    }
}
