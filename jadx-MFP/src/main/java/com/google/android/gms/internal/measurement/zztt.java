package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zztt implements zzwk {
    private int tag;
    private final zztq zzbuk;
    private int zzbul;
    private int zzbum = 0;

    public static zztt zza(zztq zztq) {
        if (zztq.zzbud != null) {
            return zztq.zzbud;
        }
        return new zztt(zztq);
    }

    private zztt(zztq zztq) {
        this.zzbuk = (zztq) zzuq.zza(zztq, "input");
        this.zzbuk.zzbud = this;
    }

    public final int zzvh() throws IOException {
        int i = this.zzbum;
        if (i != 0) {
            this.tag = i;
            this.zzbum = 0;
        } else {
            this.tag = this.zzbuk.zzuj();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzbul) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzvi() throws IOException {
        if (!this.zzbuk.zzuz()) {
            int i = this.tag;
            if (i != this.zzbul) {
                return this.zzbuk.zzaq(i);
            }
        }
        return false;
    }

    private final void zzav(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzuv.zzwu();
        }
    }

    public final double readDouble() throws IOException {
        zzav(1);
        return this.zzbuk.readDouble();
    }

    public final float readFloat() throws IOException {
        zzav(5);
        return this.zzbuk.readFloat();
    }

    public final long zzuk() throws IOException {
        zzav(0);
        return this.zzbuk.zzuk();
    }

    public final long zzul() throws IOException {
        zzav(0);
        return this.zzbuk.zzul();
    }

    public final int zzum() throws IOException {
        zzav(0);
        return this.zzbuk.zzum();
    }

    public final long zzun() throws IOException {
        zzav(1);
        return this.zzbuk.zzun();
    }

    public final int zzuo() throws IOException {
        zzav(5);
        return this.zzbuk.zzuo();
    }

    public final boolean zzup() throws IOException {
        zzav(0);
        return this.zzbuk.zzup();
    }

    public final String readString() throws IOException {
        zzav(2);
        return this.zzbuk.readString();
    }

    public final String zzuq() throws IOException {
        zzav(2);
        return this.zzbuk.zzuq();
    }

    public final <T> T zza(zzwl<T> zzwl, zzub zzub) throws IOException {
        zzav(2);
        return zzc(zzwl, zzub);
    }

    public final <T> T zzb(zzwl<T> zzwl, zzub zzub) throws IOException {
        zzav(3);
        return zzd(zzwl, zzub);
    }

    private final <T> T zzc(zzwl<T> zzwl, zzub zzub) throws IOException {
        int zzus = this.zzbuk.zzus();
        if (this.zzbuk.zzbua < this.zzbuk.zzbub) {
            int zzas = this.zzbuk.zzas(zzus);
            T newInstance = zzwl.newInstance();
            this.zzbuk.zzbua++;
            zzwl.zza(newInstance, this, zzub);
            zzwl.zzy(newInstance);
            this.zzbuk.zzap(0);
            this.zzbuk.zzbua--;
            this.zzbuk.zzat(zzas);
            return newInstance;
        }
        throw zzuv.zzwv();
    }

    private final <T> T zzd(zzwl<T> zzwl, zzub zzub) throws IOException {
        int i = this.zzbul;
        this.zzbul = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzwl.newInstance();
            zzwl.zza(newInstance, this, zzub);
            zzwl.zzy(newInstance);
            if (this.tag == this.zzbul) {
                return newInstance;
            }
            throw zzuv.zzww();
        } finally {
            this.zzbul = i;
        }
    }

    public final zzte zzur() throws IOException {
        zzav(2);
        return this.zzbuk.zzur();
    }

    public final int zzus() throws IOException {
        zzav(0);
        return this.zzbuk.zzus();
    }

    public final int zzut() throws IOException {
        zzav(0);
        return this.zzbuk.zzut();
    }

    public final int zzuu() throws IOException {
        zzav(5);
        return this.zzbuk.zzuu();
    }

    public final long zzuv() throws IOException {
        zzav(1);
        return this.zzbuk.zzuv();
    }

    public final int zzuw() throws IOException {
        zzav(0);
        return this.zzbuk.zzuw();
    }

    public final long zzux() throws IOException {
        zzav(0);
        return this.zzbuk.zzux();
    }

    public final void zzi(List<Double> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzty) {
            zzty zzty = (zzty) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzus = this.zzbuk.zzus();
                    zzaw(zzus);
                    int zzva = this.zzbuk.zzva() + zzus;
                    do {
                        zzty.zzd(this.zzbuk.readDouble());
                    } while (this.zzbuk.zzva() < zzva);
                    return;
                default:
                    throw zzuv.zzwu();
            }
            do {
                zzty.zzd(this.zzbuk.readDouble());
                if (!this.zzbuk.zzuz()) {
                    zzuj2 = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj2 == this.tag);
            this.zzbum = zzuj2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzus2 = this.zzbuk.zzus();
                zzaw(zzus2);
                int zzva2 = this.zzbuk.zzva() + zzus2;
                do {
                    list.add(Double.valueOf(this.zzbuk.readDouble()));
                } while (this.zzbuk.zzva() < zzva2);
                return;
            default:
                throw zzuv.zzwu();
        }
        do {
            list.add(Double.valueOf(this.zzbuk.readDouble()));
            if (!this.zzbuk.zzuz()) {
                zzuj = this.zzbuk.zzuj();
            } else {
                return;
            }
        } while (zzuj == this.tag);
        this.zzbum = zzuj;
    }

    public final void zzj(List<Float> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzul) {
            zzul zzul = (zzul) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzus = this.zzbuk.zzus();
                zzax(zzus);
                int zzva = this.zzbuk.zzva() + zzus;
                do {
                    zzul.zzc(this.zzbuk.readFloat());
                } while (this.zzbuk.zzva() < zzva);
            } else if (i == 5) {
                do {
                    zzul.zzc(this.zzbuk.readFloat());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzus2 = this.zzbuk.zzus();
                zzax(zzus2);
                int zzva2 = this.zzbuk.zzva() + zzus2;
                do {
                    list.add(Float.valueOf(this.zzbuk.readFloat()));
                } while (this.zzbuk.zzva() < zzva2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzbuk.readFloat()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzk(List<Long> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvj.zzbe(this.zzbuk.zzuk());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzvj.zzbe(this.zzbuk.zzuk());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzbuk.zzuk()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Long.valueOf(this.zzbuk.zzuk()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzl(List<Long> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvj.zzbe(this.zzbuk.zzul());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzvj.zzbe(this.zzbuk.zzul());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzbuk.zzul()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Long.valueOf(this.zzbuk.zzul()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbuk.zzum());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzup.zzbo(this.zzbuk.zzum());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzum()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzum()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzus = this.zzbuk.zzus();
                    zzaw(zzus);
                    int zzva = this.zzbuk.zzva() + zzus;
                    do {
                        zzvj.zzbe(this.zzbuk.zzun());
                    } while (this.zzbuk.zzva() < zzva);
                    return;
                default:
                    throw zzuv.zzwu();
            }
            do {
                zzvj.zzbe(this.zzbuk.zzun());
                if (!this.zzbuk.zzuz()) {
                    zzuj2 = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj2 == this.tag);
            this.zzbum = zzuj2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzus2 = this.zzbuk.zzus();
                zzaw(zzus2);
                int zzva2 = this.zzbuk.zzva() + zzus2;
                do {
                    list.add(Long.valueOf(this.zzbuk.zzun()));
                } while (this.zzbuk.zzva() < zzva2);
                return;
            default:
                throw zzuv.zzwu();
        }
        do {
            list.add(Long.valueOf(this.zzbuk.zzun()));
            if (!this.zzbuk.zzuz()) {
                zzuj = this.zzbuk.zzuj();
            } else {
                return;
            }
        } while (zzuj == this.tag);
        this.zzbum = zzuj;
    }

    public final void zzo(List<Integer> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzus = this.zzbuk.zzus();
                zzax(zzus);
                int zzva = this.zzbuk.zzva() + zzus;
                do {
                    zzup.zzbo(this.zzbuk.zzuo());
                } while (this.zzbuk.zzva() < zzva);
            } else if (i == 5) {
                do {
                    zzup.zzbo(this.zzbuk.zzuo());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzus2 = this.zzbuk.zzus();
                zzax(zzus2);
                int zzva2 = this.zzbuk.zzva() + zzus2;
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzuo()));
                } while (this.zzbuk.zzva() < zzva2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzuo()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzp(List<Boolean> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zztc) {
            zztc zztc = (zztc) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zztc.addBoolean(this.zzbuk.zzup());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zztc.addBoolean(this.zzbuk.zzup());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzbuk.zzup()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Boolean.valueOf(this.zzbuk.zzup()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzq(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzuj;
        int zzuj2;
        if ((this.tag & 7) != 2) {
            throw zzuv.zzwu();
        } else if (!(list instanceof zzve) || z) {
            do {
                list.add(z ? zzuq() : readString());
                if (!this.zzbuk.zzuz()) {
                    zzuj = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj == this.tag);
            this.zzbum = zzuj;
        } else {
            zzve zzve = (zzve) list;
            do {
                zzve.zzc(zzur());
                if (!this.zzbuk.zzuz()) {
                    zzuj2 = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj2 == this.tag);
            this.zzbum = zzuj2;
        }
    }

    public final <T> void zza(List<T> list, zzwl<T> zzwl, zzub zzub) throws IOException {
        int zzuj;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzwl, zzub));
                if (!this.zzbuk.zzuz() && this.zzbum == 0) {
                    zzuj = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj == i);
            this.zzbum = zzuj;
            return;
        }
        throw zzuv.zzwu();
    }

    public final <T> void zzb(List<T> list, zzwl<T> zzwl, zzub zzub) throws IOException {
        int zzuj;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzwl, zzub));
                if (!this.zzbuk.zzuz() && this.zzbum == 0) {
                    zzuj = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj == i);
            this.zzbum = zzuj;
            return;
        }
        throw zzuv.zzwu();
    }

    public final void zzr(List<zzte> list) throws IOException {
        int zzuj;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzur());
                if (!this.zzbuk.zzuz()) {
                    zzuj = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj == this.tag);
            this.zzbum = zzuj;
            return;
        }
        throw zzuv.zzwu();
    }

    public final void zzs(List<Integer> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbuk.zzus());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzup.zzbo(this.zzbuk.zzus());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzus()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzus()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzt(List<Integer> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbuk.zzut());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzup.zzbo(this.zzbuk.zzut());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzut()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzut()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzu(List<Integer> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzus = this.zzbuk.zzus();
                zzax(zzus);
                int zzva = this.zzbuk.zzva() + zzus;
                do {
                    zzup.zzbo(this.zzbuk.zzuu());
                } while (this.zzbuk.zzva() < zzva);
            } else if (i == 5) {
                do {
                    zzup.zzbo(this.zzbuk.zzuu());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzus2 = this.zzbuk.zzus();
                zzax(zzus2);
                int zzva2 = this.zzbuk.zzva() + zzus2;
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzuu()));
                } while (this.zzbuk.zzva() < zzva2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzuu()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzv(List<Long> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzus = this.zzbuk.zzus();
                    zzaw(zzus);
                    int zzva = this.zzbuk.zzva() + zzus;
                    do {
                        zzvj.zzbe(this.zzbuk.zzuv());
                    } while (this.zzbuk.zzva() < zzva);
                    return;
                default:
                    throw zzuv.zzwu();
            }
            do {
                zzvj.zzbe(this.zzbuk.zzuv());
                if (!this.zzbuk.zzuz()) {
                    zzuj2 = this.zzbuk.zzuj();
                } else {
                    return;
                }
            } while (zzuj2 == this.tag);
            this.zzbum = zzuj2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzus2 = this.zzbuk.zzus();
                zzaw(zzus2);
                int zzva2 = this.zzbuk.zzva() + zzus2;
                do {
                    list.add(Long.valueOf(this.zzbuk.zzuv()));
                } while (this.zzbuk.zzva() < zzva2);
                return;
            default:
                throw zzuv.zzwu();
        }
        do {
            list.add(Long.valueOf(this.zzbuk.zzuv()));
            if (!this.zzbuk.zzuz()) {
                zzuj = this.zzbuk.zzuj();
            } else {
                return;
            }
        } while (zzuj == this.tag);
        this.zzbum = zzuj;
    }

    public final void zzw(List<Integer> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbuk.zzuw());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzup.zzbo(this.zzbuk.zzuw());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzuw()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Integer.valueOf(this.zzbuk.zzuw()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    public final void zzx(List<Long> list) throws IOException {
        int zzuj;
        int zzuj2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvj.zzbe(this.zzbuk.zzux());
                    if (!this.zzbuk.zzuz()) {
                        zzuj2 = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj2 == this.tag);
                this.zzbum = zzuj2;
            } else if (i == 2) {
                int zzva = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    zzvj.zzbe(this.zzbuk.zzux());
                } while (this.zzbuk.zzva() < zzva);
                zzay(zzva);
            } else {
                throw zzuv.zzwu();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzbuk.zzux()));
                    if (!this.zzbuk.zzuz()) {
                        zzuj = this.zzbuk.zzuj();
                    } else {
                        return;
                    }
                } while (zzuj == this.tag);
                this.zzbum = zzuj;
            } else if (i2 == 2) {
                int zzva2 = this.zzbuk.zzva() + this.zzbuk.zzus();
                do {
                    list.add(Long.valueOf(this.zzbuk.zzux()));
                } while (this.zzbuk.zzva() < zzva2);
                zzay(zzva2);
            } else {
                throw zzuv.zzwu();
            }
        }
    }

    private static void zzaw(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzuv.zzww();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (zzvi() != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        throw new com.google.android.gms.internal.measurement.zzuv("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.measurement.zzvo<K, V> r7, com.google.android.gms.internal.measurement.zzub r8) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 2
            r5.zzav(r0)
            com.google.android.gms.internal.measurement.zztq r0 = r5.zzbuk
            int r0 = r0.zzus()
            com.google.android.gms.internal.measurement.zztq r1 = r5.zzbuk
            int r0 = r1.zzas(r0)
            K r1 = r7.zzcal
            V r2 = r7.zzbrr
        L_0x0014:
            int r3 = r5.zzvh()     // Catch:{ all -> 0x0065 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x005c
            com.google.android.gms.internal.measurement.zztq r4 = r5.zzbuk     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.zzuz()     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x005c
            switch(r3) {
                case 1: goto L_0x003a;
                case 2: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r5.zzvi()     // Catch:{ zzuw -> 0x004d }
            goto L_0x0042
        L_0x002d:
            com.google.android.gms.internal.measurement.zzxs r3 = r7.zzcam     // Catch:{ zzuw -> 0x004d }
            V r4 = r7.zzbrr     // Catch:{ zzuw -> 0x004d }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzuw -> 0x004d }
            java.lang.Object r2 = r5.zza(r3, r4, r8)     // Catch:{ zzuw -> 0x004d }
            goto L_0x0014
        L_0x003a:
            com.google.android.gms.internal.measurement.zzxs r3 = r7.zzcak     // Catch:{ zzuw -> 0x004d }
            r4 = 0
            java.lang.Object r1 = r5.zza(r3, r4, r4)     // Catch:{ zzuw -> 0x004d }
            goto L_0x0014
        L_0x0042:
            if (r3 == 0) goto L_0x0045
            goto L_0x0014
        L_0x0045:
            com.google.android.gms.internal.measurement.zzuv r3 = new com.google.android.gms.internal.measurement.zzuv     // Catch:{ zzuw -> 0x004d }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzuw -> 0x004d }
            throw r3     // Catch:{ zzuw -> 0x004d }
        L_0x004d:
            boolean r3 = r5.zzvi()     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x0054
            goto L_0x0014
        L_0x0054:
            com.google.android.gms.internal.measurement.zzuv r6 = new com.google.android.gms.internal.measurement.zzuv     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x0065 }
            throw r6     // Catch:{ all -> 0x0065 }
        L_0x005c:
            r6.put(r1, r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.measurement.zztq r6 = r5.zzbuk
            r6.zzat(r0)
            return
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.measurement.zztq r7 = r5.zzbuk
            r7.zzat(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zztt.zza(java.util.Map, com.google.android.gms.internal.measurement.zzvo, com.google.android.gms.internal.measurement.zzub):void");
    }

    private final Object zza(zzxs zzxs, Class<?> cls, zzub zzub) throws IOException {
        switch (zzxs) {
            case BOOL:
                return Boolean.valueOf(zzup());
            case BYTES:
                return zzur();
            case DOUBLE:
                return Double.valueOf(readDouble());
            case ENUM:
                return Integer.valueOf(zzut());
            case FIXED32:
                return Integer.valueOf(zzuo());
            case FIXED64:
                return Long.valueOf(zzun());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT32:
                return Integer.valueOf(zzum());
            case INT64:
                return Long.valueOf(zzul());
            case MESSAGE:
                zzav(2);
                return zzc(zzwh.zzxt().zzi(cls), zzub);
            case SFIXED32:
                return Integer.valueOf(zzuu());
            case SFIXED64:
                return Long.valueOf(zzuv());
            case SINT32:
                return Integer.valueOf(zzuw());
            case SINT64:
                return Long.valueOf(zzux());
            case STRING:
                return zzuq();
            case UINT32:
                return Integer.valueOf(zzus());
            case UINT64:
                return Long.valueOf(zzuk());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzax(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzuv.zzww();
        }
    }

    private final void zzay(int i) throws IOException {
        if (this.zzbuk.zzva() != i) {
            throw zzuv.zzwq();
        }
    }
}
