package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;

@zzark
public final class zzatp {
    public final boolean zzeaj;
    public final zzatx zzeak = null;
    public final zzua zzeal;
    public final zzaxa zzeam;
    public final zzaab zzean;
    public final zzaug zzeao;
    public final zzakk zzeap;
    public final zzauh zzeaq;
    public final zzaov zzear;
    public final zzaxe zzeas;
    public final zzatu zzeat;

    private zzatp(zzatx zzatx, zzua zzua, zzaxa zzaxa, zzaab zzaab, zzaug zzaug, zzakk zzakk, zzauh zzauh, zzaov zzaov, zzaxe zzaxe, boolean z, zzatu zzatu) {
        this.zzeal = zzua;
        this.zzeam = zzaxa;
        this.zzean = zzaab;
        this.zzeao = zzaug;
        this.zzeap = zzakk;
        this.zzeaq = zzauh;
        this.zzear = zzaov;
        this.zzeas = zzaxe;
        this.zzeaj = true;
        this.zzeat = zzatu;
    }

    public static zzatp zzo(Context context) {
        zzbv.zzlr().initialize(context);
        zzauj zzauj = new zzauj(context);
        zzatp zzatp = new zzatp(null, new zzud(), new zzaxb(), new zzaaa(), new zzaue(context, zzauj.zzwp()), new zzakl(), new zzaui(), new zzaou(), new zzaxc(), true, zzauj);
        return zzatp;
    }
}
