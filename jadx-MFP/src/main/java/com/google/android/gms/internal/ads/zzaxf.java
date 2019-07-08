package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzaxf {
    public final int errorCode;
    public final int orientation;
    public final boolean zzbph;
    public final boolean zzbpi;
    public final List<String> zzdlq;
    public final List<String> zzdlr;
    public final List<String> zzdls;
    @Nullable
    public final List<String> zzdlu;
    public final long zzdlx;
    @Nullable
    public final zzakq zzdnb;
    @Nullable
    public final zzalj zzdnc;
    @Nullable
    public final String zzdnd;
    @Nullable
    public final zzakt zzdne;
    @Nullable
    public final zzbgg zzdrv;
    public final zzwb zzdwg;
    public final String zzdwj;
    private final long zzdyc;
    public final boolean zzdyd;
    private final long zzdye;
    public final List<String> zzdyf;
    public final String zzdyi;
    @Nullable
    public final zzawd zzdyr;
    @Nullable
    public final List<String> zzdyt;
    public final boolean zzdyu;
    private final zzaso zzdyv;
    public final String zzdyy;
    public final boolean zzdzc;
    private final String zzdzd;
    public final boolean zzdzf;
    public final JSONObject zzehh;
    public boolean zzehi;
    public final zzakr zzehj;
    @Nullable
    public final String zzehk;
    public final zzwf zzehl;
    @Nullable
    public final List<String> zzehm;
    public final long zzehn;
    public final long zzeho;
    @Nullable
    public final zzacf zzehp;
    public boolean zzehq;
    public boolean zzehr;
    public boolean zzehs;
    public boolean zzeht;
    public boolean zzehu;
    public boolean zzehv;
    public final zzum zzehw;
    public final boolean zzehx;

    public zzaxf(zzwb zzwb, @Nullable zzbgg zzbgg, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable zzakq zzakq, @Nullable zzalj zzalj, @Nullable String str2, zzakr zzakr, @Nullable zzakt zzakt, long j2, zzwf zzwf, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable zzacf zzacf, zzawd zzawd, List<String> list4, List<String> list5, boolean z2, zzaso zzaso, @Nullable String str4, List<String> list6, String str5, zzum zzum, boolean z3, boolean z4, boolean z5, List<String> list7, boolean z6, String str6, boolean z7) {
        this.zzehq = false;
        this.zzehr = false;
        this.zzehs = false;
        this.zzeht = false;
        this.zzehu = false;
        this.zzehv = false;
        this.zzdwg = zzwb;
        this.zzdrv = zzbgg;
        this.zzdlq = zzn(list);
        this.errorCode = i;
        this.zzdlr = zzn(list2);
        this.zzdyf = zzn(list3);
        this.orientation = i2;
        this.zzdlx = j;
        this.zzdwj = str;
        this.zzdyd = z;
        this.zzdnb = zzakq;
        this.zzdnc = zzalj;
        this.zzdnd = str2;
        this.zzehj = zzakr;
        this.zzdne = zzakt;
        this.zzdye = j2;
        this.zzehl = zzwf;
        this.zzdyc = j3;
        this.zzehn = j4;
        this.zzeho = j5;
        this.zzdyi = str3;
        this.zzehh = jSONObject;
        this.zzehp = zzacf;
        this.zzdyr = zzawd;
        this.zzehm = zzn(list4);
        this.zzdyt = zzn(list5);
        this.zzdyu = z2;
        this.zzdyv = zzaso;
        this.zzehk = str4;
        this.zzdlu = zzn(list6);
        this.zzdyy = str5;
        this.zzehw = zzum;
        this.zzbph = z3;
        this.zzehx = z4;
        this.zzdzc = z5;
        this.zzdls = zzn(list7);
        this.zzbpi = z6;
        this.zzdzd = str6;
        this.zzdzf = z7;
    }

    public zzaxf(zzaxg zzaxg, @Nullable zzbgg zzbgg, @Nullable zzakq zzakq, @Nullable zzalj zzalj, @Nullable String str, @Nullable zzakt zzakt, @Nullable zzacf zzacf, @Nullable String str2) {
        zzaxg zzaxg2 = zzaxg;
        this(zzaxg2.zzeag.zzdwg, null, zzaxg2.zzehy.zzdlq, zzaxg2.errorCode, zzaxg2.zzehy.zzdlr, zzaxg2.zzehy.zzdyf, zzaxg2.zzehy.orientation, zzaxg2.zzehy.zzdlx, zzaxg2.zzeag.zzdwj, zzaxg2.zzehy.zzdyd, null, null, null, zzaxg2.zzehj, null, zzaxg2.zzehy.zzdye, zzaxg2.zzbst, zzaxg2.zzehy.zzdyc, zzaxg2.zzehn, zzaxg2.zzeho, zzaxg2.zzehy.zzdyi, zzaxg2.zzehh, null, zzaxg2.zzehy.zzdyr, zzaxg2.zzehy.zzdys, zzaxg2.zzehy.zzdys, zzaxg2.zzehy.zzdyu, zzaxg2.zzehy.zzdyv, null, zzaxg2.zzehy.zzdlu, zzaxg2.zzehy.zzdyy, zzaxg2.zzehw, zzaxg2.zzehy.zzbph, zzaxg2.zzehx, zzaxg2.zzehy.zzdzc, zzaxg2.zzehy.zzdls, zzaxg2.zzehy.zzbpi, zzaxg2.zzehy.zzdzd, zzaxg2.zzehy.zzdzf);
    }

    public final boolean zzmu() {
        zzbgg zzbgg = this.zzdrv;
        if (zzbgg == null || zzbgg.zzadl() == null) {
            return false;
        }
        return this.zzdrv.zzadl().zzmu();
    }

    @Nullable
    private static <T> List<T> zzn(@Nullable List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }
}
