package com.google.android.gms.internal.ads;

import java.util.Random;

@zzark
public final class zzwu {
    private static zzwu zzclj = new zzwu();
    private final zzbat zzclk;
    private final zzwj zzcll;
    private final String zzclm;
    private final zzaai zzcln;
    private final zzaaj zzclo;
    private final zzaak zzclp;
    private final zzbbi zzclq;
    private final Random zzclr;

    protected zzwu() {
        zzbat zzbat = new zzbat();
        zzwj zzwj = new zzwj(new zzvz(), new zzvy(), new zzzg(), new zzafa(), new zzavf(), new zzawf(), new zzaoo(), new zzafb());
        this(zzbat, zzwj, new zzaai(), new zzaaj(), new zzaak(), zzbat.zzaas(), new zzbbi(0, 14300000, true), new Random());
    }

    private zzwu(zzbat zzbat, zzwj zzwj, zzaai zzaai, zzaaj zzaaj, zzaak zzaak, String str, zzbbi zzbbi, Random random) {
        this.zzclk = zzbat;
        this.zzcll = zzwj;
        this.zzcln = zzaai;
        this.zzclo = zzaaj;
        this.zzclp = zzaak;
        this.zzclm = str;
        this.zzclq = zzbbi;
        this.zzclr = random;
    }

    public static zzbat zzpv() {
        return zzclj.zzclk;
    }

    public static zzwj zzpw() {
        return zzclj.zzcll;
    }

    public static zzaaj zzpx() {
        return zzclj.zzclo;
    }

    public static zzaai zzpy() {
        return zzclj.zzcln;
    }

    public static zzaak zzpz() {
        return zzclj.zzclp;
    }

    public static String zzqa() {
        return zzclj.zzclm;
    }

    public static zzbbi zzqb() {
        return zzclj.zzclq;
    }

    public static Random zzqc() {
        return zzclj.zzclr;
    }
}
