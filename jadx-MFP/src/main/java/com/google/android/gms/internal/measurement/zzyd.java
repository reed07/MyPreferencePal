package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzyc;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzyd<M extends zzyc<M>, T> {
    public final int tag;
    private final int type;
    private final zzuo<?, ?> zzbyi;
    protected final Class<T> zzcew;
    protected final boolean zzcex;

    public static <M extends zzyc<M>, T extends zzyi> zzyd<M, T> zza(int i, Class<T> cls, long j) {
        return new zzyd<>(11, cls, 810, false);
    }

    private zzyd(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, 810, false);
    }

    private zzyd(int i, Class<T> cls, zzuo<?, ?> zzuo, int i2, boolean z) {
        this.type = i;
        this.zzcew = cls;
        this.tag = i2;
        this.zzcex = false;
        this.zzbyi = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzyd)) {
            return false;
        }
        zzyd zzyd = (zzyd) obj;
        return this.type == zzyd.type && this.zzcew == zzyd.zzcew && this.tag == zzyd.tag && this.zzcex == zzyd.zzcex;
    }

    public final int hashCode() {
        return ((((((this.type + 1147) * 31) + this.zzcew.hashCode()) * 31) + this.tag) * 31) + (this.zzcex ? 1 : 0);
    }

    /* access modifiers changed from: 0000 */
    public final T zzai(List<zzyk> list) {
        if (list == null) {
            return null;
        }
        if (this.zzcex) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                zzyk zzyk = (zzyk) list.get(i);
                if (zzyk.zzbtz.length != 0) {
                    arrayList.add(zze(zzxz.zzn(zzyk.zzbtz)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            Class<T> cls = this.zzcew;
            T cast = cls.cast(Array.newInstance(cls.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(cast, i2, arrayList.get(i2));
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zzcew.cast(zze(zzxz.zzn(((zzyk) list.get(list.size() - 1)).zzbtz)));
        }
    }

    private final Object zze(zzxz zzxz) {
        Class<T> componentType = this.zzcex ? this.zzcew.getComponentType() : this.zzcew;
        try {
            switch (this.type) {
                case 10:
                    zzyi zzyi = (zzyi) componentType.newInstance();
                    zzxz.zza(zzyi, this.tag >>> 3);
                    return zzyi;
                case 11:
                    zzyi zzyi2 = (zzyi) componentType.newInstance();
                    zzxz.zza(zzyi2);
                    return zzyi2;
                default:
                    int i = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 33);
            sb2.append("Error creating instance of class ");
            sb2.append(valueOf);
            throw new IllegalArgumentException(sb2.toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 33);
            sb3.append("Error creating instance of class ");
            sb3.append(valueOf2);
            throw new IllegalArgumentException(sb3.toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(Object obj, zzya zzya) {
        try {
            zzya.zzcd(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzyi) obj).zza(zzya);
                    zzya.zzc(i, 4);
                    return;
                case 11:
                    zzya.zzb((zzyi) obj);
                    return;
                default:
                    int i2 = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i2);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final int zzao(Object obj) {
        int i = this.tag >>> 3;
        int i2 = this.type;
        switch (i2) {
            case 10:
                return (zzya.zzbd(i) << 1) + ((zzyi) obj).zzvx();
            case 11:
                return zzya.zzb(i, (zzyi) obj);
            default:
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
        }
    }
}
