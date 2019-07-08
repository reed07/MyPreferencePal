package com.google.android.gms.ads.internal;

import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.overlay.zza;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzu;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.ads.zzaas;
import com.google.android.gms.internal.ads.zzahm;
import com.google.android.gms.internal.ads.zzaie;
import com.google.android.gms.internal.ads.zzajw;
import com.google.android.gms.internal.ads.zzakz;
import com.google.android.gms.internal.ads.zzaom;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzarl;
import com.google.android.gms.internal.ads.zzaub;
import com.google.android.gms.internal.ads.zzaww;
import com.google.android.gms.internal.ads.zzaxk;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzayu;
import com.google.android.gms.internal.ads.zzayv;
import com.google.android.gms.internal.ads.zzayw;
import com.google.android.gms.internal.ads.zzayx;
import com.google.android.gms.internal.ads.zzayy;
import com.google.android.gms.internal.ads.zzayz;
import com.google.android.gms.internal.ads.zzaza;
import com.google.android.gms.internal.ads.zzazj;
import com.google.android.gms.internal.ads.zzbaf;
import com.google.android.gms.internal.ads.zzbag;
import com.google.android.gms.internal.ads.zzbap;
import com.google.android.gms.internal.ads.zzbcm;
import com.google.android.gms.internal.ads.zzbct;
import com.google.android.gms.internal.ads.zzbfj;
import com.google.android.gms.internal.ads.zzbgm;
import com.google.android.gms.internal.ads.zzst;
import com.google.android.gms.internal.ads.zztp;
import com.google.android.gms.internal.ads.zztq;
import com.google.android.gms.internal.ads.zzul;

@zzark
public final class zzbv {
    private static zzbv zzbrf = new zzbv();
    private final zza zzbrg;
    private final zzarl zzbrh;
    private final zzl zzbri;
    private final zzapl zzbrj;
    private final zzayh zzbrk;
    private final zzbgm zzbrl;
    private final zzayp zzbrm;
    private final zzst zzbrn;
    private final zzaxk zzbro;
    private final zzaza zzbrp;
    private final zztp zzbrq;
    private final zztq zzbrr;
    private final Clock zzbrs;
    private final zzad zzbrt;
    private final zzaas zzbru;
    private final zzazj zzbrv;
    private final zzaub zzbrw;
    private final zzaie zzbrx;
    private final zzbcm zzbry;
    private final zzahm zzbrz;
    private final zzajw zzbsa;
    private final zzbaf zzbsb;
    private final zzu zzbsc;
    private final zzv zzbsd;
    private final zzakz zzbse;
    private final zzbag zzbsf;
    private final zzaom zzbsg;
    private final zzul zzbsh;
    private final zzaww zzbsi;
    private final zzbap zzbsj;
    private final zzbfj zzbsk;
    private final zzbct zzbsl;

    protected zzbv() {
        zzayp zzayp;
        zza zza = new zza();
        zzarl zzarl = new zzarl();
        zzl zzl = new zzl();
        zzapl zzapl = new zzapl();
        zzayh zzayh = new zzayh();
        zzbgm zzbgm = new zzbgm();
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            zzayp = new zzayz();
        } else if (i >= 19) {
            zzayp = new zzayy();
        } else if (i >= 18) {
            zzayp = new zzayw();
        } else if (i >= 17) {
            zzayp = new zzayv();
        } else if (i >= 16) {
            zzayp = new zzayx();
        } else {
            zzayp = new zzayu();
        }
        zzst zzst = r0;
        zzst zzst2 = new zzst();
        zzaxk zzaxk = r0;
        zzaxk zzaxk2 = new zzaxk();
        zzaza zzaza = r0;
        zzaza zzaza2 = new zzaza();
        zztp zztp = r0;
        zztp zztp2 = new zztp();
        zztq zztq = r0;
        zztq zztq2 = new zztq();
        Clock instance = DefaultClock.getInstance();
        zzad zzad = r0;
        zzad zzad2 = new zzad();
        zzaas zzaas = r0;
        zzaas zzaas2 = new zzaas();
        zzazj zzazj = r0;
        zzazj zzazj2 = new zzazj();
        zzaub zzaub = r0;
        zzaub zzaub2 = new zzaub();
        zzaie zzaie = r0;
        zzaie zzaie2 = new zzaie();
        zzbcm zzbcm = r0;
        zzbcm zzbcm2 = new zzbcm();
        zzajw zzajw = r0;
        zzajw zzajw2 = new zzajw();
        zzbaf zzbaf = r0;
        zzbaf zzbaf2 = new zzbaf();
        zzu zzu = r0;
        zzu zzu2 = new zzu();
        zzv zzv = r0;
        zzv zzv2 = new zzv();
        zzakz zzakz = r0;
        zzakz zzakz2 = new zzakz();
        zzbag zzbag = r0;
        zzbag zzbag2 = new zzbag();
        zzaom zzaom = r0;
        zzaom zzaom2 = new zzaom();
        zzul zzul = r0;
        zzul zzul2 = new zzul();
        zzaww zzaww = r0;
        zzaww zzaww2 = new zzaww();
        zzbap zzbap = r0;
        zzbap zzbap2 = new zzbap();
        zzbfj zzbfj = r0;
        zzbfj zzbfj2 = new zzbfj();
        zzbct zzbct = r0;
        zzbct zzbct2 = new zzbct();
        this(zza, zzarl, zzl, zzapl, zzayh, zzbgm, zzayp, zzst, zzaxk, zzaza, zztp, zztq, instance, zzad, zzaas, zzazj, zzaub, zzaie, zzbcm, zzajw, zzbaf, zzu, zzv, zzakz, zzbag, zzaom, zzul, zzaww, zzbap, zzbfj, zzbct);
    }

    private zzbv(zza zza, zzarl zzarl, zzl zzl, zzapl zzapl, zzayh zzayh, zzbgm zzbgm, zzayp zzayp, zzst zzst, zzaxk zzaxk, zzaza zzaza, zztp zztp, zztq zztq, Clock clock, zzad zzad, zzaas zzaas, zzazj zzazj, zzaub zzaub, zzaie zzaie, zzbcm zzbcm, zzajw zzajw, zzbaf zzbaf, zzu zzu, zzv zzv, zzakz zzakz, zzbag zzbag, zzaom zzaom, zzul zzul, zzaww zzaww, zzbap zzbap, zzbfj zzbfj, zzbct zzbct) {
        this.zzbrg = zza;
        this.zzbrh = zzarl;
        this.zzbri = zzl;
        this.zzbrj = zzapl;
        this.zzbrk = zzayh;
        this.zzbrl = zzbgm;
        this.zzbrm = zzayp;
        this.zzbrn = zzst;
        this.zzbro = zzaxk;
        this.zzbrp = zzaza;
        this.zzbrq = zztp;
        this.zzbrr = zztq;
        this.zzbrs = clock;
        this.zzbrt = zzad;
        this.zzbru = zzaas;
        this.zzbrv = zzazj;
        this.zzbrw = zzaub;
        this.zzbrx = zzaie;
        this.zzbry = zzbcm;
        this.zzbrz = new zzahm();
        this.zzbsa = zzajw;
        this.zzbsb = zzbaf;
        this.zzbsc = zzu;
        this.zzbsd = zzv;
        this.zzbse = zzakz;
        this.zzbsf = zzbag;
        this.zzbsg = zzaom;
        this.zzbsh = zzul;
        this.zzbsi = zzaww;
        this.zzbsj = zzbap;
        this.zzbsk = zzbfj;
        this.zzbsl = zzbct;
    }

    public static zzarl zzlb() {
        return zzbrf.zzbrh;
    }

    public static zza zzlc() {
        return zzbrf.zzbrg;
    }

    public static zzl zzld() {
        return zzbrf.zzbri;
    }

    public static zzapl zzle() {
        return zzbrf.zzbrj;
    }

    public static zzayh zzlf() {
        return zzbrf.zzbrk;
    }

    public static zzbgm zzlg() {
        return zzbrf.zzbrl;
    }

    public static zzayp zzlh() {
        return zzbrf.zzbrm;
    }

    public static zzst zzli() {
        return zzbrf.zzbrn;
    }

    public static zzaxk zzlj() {
        return zzbrf.zzbro;
    }

    public static zzaza zzlk() {
        return zzbrf.zzbrp;
    }

    public static zztq zzll() {
        return zzbrf.zzbrr;
    }

    public static Clock zzlm() {
        return zzbrf.zzbrs;
    }

    public static zzad zzln() {
        return zzbrf.zzbrt;
    }

    public static zzaas zzlo() {
        return zzbrf.zzbru;
    }

    public static zzazj zzlp() {
        return zzbrf.zzbrv;
    }

    public static zzaub zzlq() {
        return zzbrf.zzbrw;
    }

    public static zzaie zzlr() {
        return zzbrf.zzbrx;
    }

    public static zzbcm zzls() {
        return zzbrf.zzbry;
    }

    public static zzahm zzlt() {
        return zzbrf.zzbrz;
    }

    public static zzajw zzlu() {
        return zzbrf.zzbsa;
    }

    public static zzbaf zzlv() {
        return zzbrf.zzbsb;
    }

    public static zzaom zzlw() {
        return zzbrf.zzbsg;
    }

    public static zzu zzlx() {
        return zzbrf.zzbsc;
    }

    public static zzv zzly() {
        return zzbrf.zzbsd;
    }

    public static zzakz zzlz() {
        return zzbrf.zzbse;
    }

    public static zzbag zzma() {
        return zzbrf.zzbsf;
    }

    public static zzul zzmb() {
        return zzbrf.zzbsh;
    }

    public static zzbap zzmc() {
        return zzbrf.zzbsj;
    }

    public static zzbfj zzmd() {
        return zzbrf.zzbsk;
    }

    public static zzbct zzme() {
        return zzbrf.zzbsl;
    }

    public static zzaww zzmf() {
        return zzbrf.zzbsi;
    }
}
