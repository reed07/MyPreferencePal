package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzde;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzdc<FieldDescriptorType extends zzde<FieldDescriptorType>> {
    private static final zzdc zzhb = new zzdc(true);
    private final zzfi<FieldDescriptorType, Object> zzgy = zzfi.zzai(16);
    private boolean zzgz;
    private boolean zzha = false;

    private zzdc() {
    }

    private zzdc(boolean z) {
        zzaj();
    }

    public static <T extends zzde<T>> zzdc<T> zzbh() {
        return zzhb;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isEmpty() {
        return this.zzgy.isEmpty();
    }

    public final void zzaj() {
        if (!this.zzgz) {
            this.zzgy.zzaj();
            this.zzgz = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzgz;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdc)) {
            return false;
        }
        return this.zzgy.equals(((zzdc) obj).zzgy);
    }

    public final int hashCode() {
        return this.zzgy.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzha) {
            return new zzdx(this.zzgy.entrySet().iterator());
        }
        return this.zzgy.entrySet().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzha) {
            return new zzdx(this.zzgy.zzdj().iterator());
        }
        return this.zzgy.zzdj().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzgy.get(fielddescriptortype);
        return obj instanceof zzdu ? zzdu.zzce() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzbn()) {
            zza(fielddescriptortype.zzbl(), obj);
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
                zza(fielddescriptortype.zzbl(), obj2);
            }
            r7 = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (r7 instanceof zzdu) {
            this.zzha = true;
        }
        this.zzgy.put(fielddescriptortype, r7);
    }

    private static void zza(zzgl zzgl, Object obj) {
        zzdl.checkNotNull(obj);
        boolean z = false;
        switch (zzdd.zzhc[zzgl.zzdx().ordinal()]) {
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
                if ((obj instanceof zzce) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzdo)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzeq) || (obj instanceof zzdu)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzgy.zzdh(); i++) {
            if (!zzb(this.zzgy.zzaj(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzgy.zzdi()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzde zzde = (zzde) entry.getKey();
        if (zzde.zzbm() == zzgq.MESSAGE) {
            if (zzde.zzbn()) {
                for (zzeq isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzeq) {
                    if (!((zzeq) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzdu) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzdc<FieldDescriptorType> zzdc) {
        for (int i = 0; i < zzdc.zzgy.zzdh(); i++) {
            zzc(zzdc.zzgy.zzaj(i));
        }
        for (Entry zzc : zzdc.zzgy.zzdi()) {
            zzc(zzc);
        }
    }

    private static Object zzf(Object obj) {
        if (obj instanceof zzew) {
            return ((zzew) obj).zzcu();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        Object obj;
        zzde zzde = (zzde) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzdu) {
            value = zzdu.zzce();
        }
        if (zzde.zzbn()) {
            Object zza = zza((FieldDescriptorType) zzde);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzf : (List) value) {
                ((List) zza).add(zzf(zzf));
            }
            this.zzgy.put(zzde, zza);
        } else if (zzde.zzbm() == zzgq.MESSAGE) {
            Object zza2 = zza((FieldDescriptorType) zzde);
            if (zza2 == null) {
                this.zzgy.put(zzde, zzf(value));
                return;
            }
            if (zza2 instanceof zzew) {
                obj = zzde.zza((zzew) zza2, (zzew) value);
            } else {
                obj = zzde.zza(((zzeq) zza2).zzbu(), (zzeq) value).zzca();
            }
            this.zzgy.put(zzde, obj);
        } else {
            this.zzgy.put(zzde, zzf(value));
        }
    }

    static void zza(zzct zzct, zzgl zzgl, int i, Object obj) throws IOException {
        if (zzgl == zzgl.GROUP) {
            zzeq zzeq = (zzeq) obj;
            zzdl.zzf(zzeq);
            zzct.zzb(i, 3);
            zzeq.zzb(zzct);
            zzct.zzb(i, 4);
            return;
        }
        zzct.zzb(i, zzgl.zzdy());
        switch (zzdd.zzhd[zzgl.ordinal()]) {
            case 1:
                zzct.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzct.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzct.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzct.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzct.zzo(((Integer) obj).intValue());
                return;
            case 6:
                zzct.zzd(((Long) obj).longValue());
                return;
            case 7:
                zzct.zzr(((Integer) obj).intValue());
                return;
            case 8:
                zzct.zzg(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzeq) obj).zzb(zzct);
                return;
            case 10:
                zzct.zzb((zzeq) obj);
                return;
            case 11:
                if (obj instanceof zzce) {
                    zzct.zza((zzce) obj);
                    return;
                } else {
                    zzct.zzu((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzce) {
                    zzct.zza((zzce) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzct.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzct.zzp(((Integer) obj).intValue());
                return;
            case 14:
                zzct.zzr(((Integer) obj).intValue());
                return;
            case 15:
                zzct.zzd(((Long) obj).longValue());
                return;
            case 16:
                zzct.zzq(((Integer) obj).intValue());
                return;
            case 17:
                zzct.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (!(obj instanceof zzdo)) {
                    zzct.zzo(((Integer) obj).intValue());
                    break;
                } else {
                    zzct.zzo(((zzdo) obj).zzbk());
                    return;
                }
        }
    }

    public final int zzbi() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzgy.zzdh(); i2++) {
            Entry zzaj = this.zzgy.zzaj(i2);
            i += zzb((zzde) zzaj.getKey(), zzaj.getValue());
        }
        for (Entry entry : this.zzgy.zzdi()) {
            i += zzb((zzde) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzbj() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzgy.zzdh(); i2++) {
            i += zzd(this.zzgy.zzaj(i2));
        }
        for (Entry zzd : this.zzgy.zzdi()) {
            i += zzd(zzd);
        }
        return i;
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzde zzde = (zzde) entry.getKey();
        Object value = entry.getValue();
        if (zzde.zzbm() != zzgq.MESSAGE || zzde.zzbn() || zzde.zzbo()) {
            return zzb(zzde, value);
        }
        if (value instanceof zzdu) {
            return zzct.zzb(((zzde) entry.getKey()).zzbk(), (zzdy) (zzdu) value);
        }
        return zzct.zzb(((zzde) entry.getKey()).zzbk(), (zzeq) value);
    }

    static int zza(zzgl zzgl, int i, Object obj) {
        int zzs = zzct.zzs(i);
        if (zzgl == zzgl.GROUP) {
            zzdl.zzf((zzeq) obj);
            zzs <<= 1;
        }
        return zzs + zzb(zzgl, obj);
    }

    private static int zzb(zzgl zzgl, Object obj) {
        switch (zzdd.zzhd[zzgl.ordinal()]) {
            case 1:
                return zzct.zzb(((Double) obj).doubleValue());
            case 2:
                return zzct.zzb(((Float) obj).floatValue());
            case 3:
                return zzct.zze(((Long) obj).longValue());
            case 4:
                return zzct.zzf(((Long) obj).longValue());
            case 5:
                return zzct.zzt(((Integer) obj).intValue());
            case 6:
                return zzct.zzh(((Long) obj).longValue());
            case 7:
                return zzct.zzw(((Integer) obj).intValue());
            case 8:
                return zzct.zzh(((Boolean) obj).booleanValue());
            case 9:
                return zzct.zzd((zzeq) obj);
            case 10:
                if (obj instanceof zzdu) {
                    return zzct.zza((zzdy) (zzdu) obj);
                }
                return zzct.zzc((zzeq) obj);
            case 11:
                if (obj instanceof zzce) {
                    return zzct.zzb((zzce) obj);
                }
                return zzct.zzv((String) obj);
            case 12:
                if (obj instanceof zzce) {
                    return zzct.zzb((zzce) obj);
                }
                return zzct.zzc((byte[]) obj);
            case 13:
                return zzct.zzu(((Integer) obj).intValue());
            case 14:
                return zzct.zzx(((Integer) obj).intValue());
            case 15:
                return zzct.zzi(((Long) obj).longValue());
            case 16:
                return zzct.zzv(((Integer) obj).intValue());
            case 17:
                return zzct.zzg(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzdo) {
                    return zzct.zzy(((zzdo) obj).zzbk());
                }
                return zzct.zzy(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzde<?> zzde, Object obj) {
        zzgl zzbl = zzde.zzbl();
        int zzbk = zzde.zzbk();
        if (!zzde.zzbn()) {
            return zza(zzbl, zzbk, obj);
        }
        int i = 0;
        if (zzde.zzbo()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzbl, zzb);
            }
            return zzct.zzs(zzbk) + i + zzct.zzaa(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzbl, zzbk, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzdc zzdc = new zzdc();
        for (int i = 0; i < this.zzgy.zzdh(); i++) {
            Entry zzaj = this.zzgy.zzaj(i);
            zzdc.zza((FieldDescriptorType) (zzde) zzaj.getKey(), zzaj.getValue());
        }
        for (Entry entry : this.zzgy.zzdi()) {
            zzdc.zza((FieldDescriptorType) (zzde) entry.getKey(), entry.getValue());
        }
        zzdc.zzha = this.zzha;
        return zzdc;
    }
}
