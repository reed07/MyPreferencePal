package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzwa<T> implements zzwl<T> {
    private final zzvv zzcaw;
    private final boolean zzcax;
    private final zzxd<?, ?> zzcbg;
    private final zzuc<?> zzcbh;

    private zzwa(zzxd<?, ?> zzxd, zzuc<?> zzuc, zzvv zzvv) {
        this.zzcbg = zzxd;
        this.zzcax = zzuc.zze(zzvv);
        this.zzcbh = zzuc;
        this.zzcaw = zzvv;
    }

    static <T> zzwa<T> zza(zzxd<?, ?> zzxd, zzuc<?> zzuc, zzvv zzvv) {
        return new zzwa<>(zzxd, zzuc, zzvv);
    }

    public final T newInstance() {
        return this.zzcaw.zzwi().zzwn();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzcbg.zzal(t).equals(this.zzcbg.zzal(t2))) {
            return false;
        }
        if (this.zzcax) {
            return this.zzcbh.zzw(t).equals(this.zzcbh.zzw(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzcbg.zzal(t).hashCode();
        return this.zzcax ? (hashCode * 53) + this.zzcbh.zzw(t).hashCode() : hashCode;
    }

    public final void zzd(T t, T t2) {
        zzwn.zza(this.zzcbg, t, t2);
        if (this.zzcax) {
            zzwn.zza(this.zzcbh, t, t2);
        }
    }

    public final void zza(T t, zzxy zzxy) throws IOException {
        Iterator it = this.zzcbh.zzw(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzuh zzuh = (zzuh) entry.getKey();
            if (zzuh.zzwa() != zzxx.MESSAGE || zzuh.zzwb() || zzuh.zzwc()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzva) {
                zzxy.zza(zzuh.zzc(), (Object) ((zzva) entry).zzxa().zztw());
            } else {
                zzxy.zza(zzuh.zzc(), entry.getValue());
            }
        }
        zzxd<?, ?> zzxd = this.zzcbg;
        zzxd.zzc(zzxd.zzal(t), zzxy);
    }

    public final void zza(T t, zzwk zzwk, zzub zzub) throws IOException {
        boolean z;
        zzxd<?, ?> zzxd = this.zzcbg;
        zzuc<?> zzuc = this.zzcbh;
        Object zzam = zzxd.zzam(t);
        zzuf zzx = zzuc.zzx(t);
        do {
            try {
                if (zzwk.zzvh() == Integer.MAX_VALUE) {
                    zzxd.zzg(t, zzam);
                    return;
                }
                int tag = zzwk.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzte zzte = null;
                    while (zzwk.zzvh() != Integer.MAX_VALUE) {
                        int tag2 = zzwk.getTag();
                        if (tag2 == 16) {
                            i = zzwk.zzus();
                            obj = zzuc.zza(zzub, this.zzcaw, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzuc.zza(zzwk, obj, zzub, zzx);
                            } else {
                                zzte = zzwk.zzur();
                            }
                        } else if (!zzwk.zzvi()) {
                            break;
                        }
                    }
                    if (zzwk.getTag() != 12) {
                        throw zzuv.zzwt();
                    } else if (zzte != null) {
                        if (obj != null) {
                            zzuc.zza(zzte, obj, zzub, zzx);
                        } else {
                            zzxd.zza(zzam, i, zzte);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzuc.zza(zzub, this.zzcaw, tag >>> 3);
                    if (zza != null) {
                        zzuc.zza(zzwk, zza, zzub, zzx);
                    } else {
                        z = zzxd.zza(zzam, zzwk);
                        continue;
                    }
                } else {
                    z = zzwk.zzvi();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzxd.zzg(t, zzam);
            }
        } while (z);
    }

    public final void zzy(T t) {
        this.zzcbg.zzy(t);
        this.zzcbh.zzy(t);
    }

    public final boolean zzaj(T t) {
        return this.zzcbh.zzw(t).isInitialized();
    }

    public final int zzai(T t) {
        zzxd<?, ?> zzxd = this.zzcbg;
        int zzan = zzxd.zzan(zzxd.zzal(t)) + 0;
        return this.zzcax ? zzan + this.zzcbh.zzw(t).zzvy() : zzan;
    }
}
