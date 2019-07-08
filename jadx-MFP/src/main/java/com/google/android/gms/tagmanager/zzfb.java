package com.google.android.gms.tagmanager;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzi;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzrg;
import com.google.android.gms.internal.measurement.zzri;
import com.google.android.gms.internal.measurement.zzrk;
import com.google.android.gms.internal.measurement.zzrm;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@VisibleForTesting
final class zzfb {
    private static final zzdz<zzp> zzbfb = new zzdz<>(zzgj.zzqq(), true);
    private final DataLayer zzazr;
    private final zzrk zzbfc;
    private final zzbo zzbfd;
    private final Map<String, zzbq> zzbfe;
    private final Map<String, zzbq> zzbff;
    private final Map<String, zzbq> zzbfg;
    private final zzp<zzri, zzdz<zzp>> zzbfh;
    private final zzp<String, zzfh> zzbfi;
    private final Set<zzrm> zzbfj;
    private final Map<String, zzfi> zzbfk;
    private volatile String zzbfl;
    private int zzbfm;

    public zzfb(Context context, zzrk zzrk, DataLayer dataLayer, zzan zzan, zzan zzan2, zzbo zzbo) {
        if (zzrk != null) {
            this.zzbfc = zzrk;
            this.zzbfj = new HashSet(zzrk.zzsg());
            this.zzazr = dataLayer;
            this.zzbfd = zzbo;
            zzfc zzfc = new zzfc(this);
            new zzq();
            this.zzbfh = zzq.zza(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzfc);
            zzfd zzfd = new zzfd(this);
            new zzq();
            this.zzbfi = zzq.zza(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzfd);
            this.zzbfe = new HashMap();
            zzb(new zzm(context));
            zzb(new zzam(zzan2));
            zzb(new zzaz(dataLayer));
            zzb(new zzgk(context, dataLayer));
            this.zzbff = new HashMap();
            zzc(new zzak());
            zzc(new zzbl());
            zzc(new zzbm());
            zzc(new zzbs());
            zzc(new zzbt());
            zzc(new zzde());
            zzc(new zzdf());
            zzc(new zzel());
            zzc(new zzfy());
            this.zzbfg = new HashMap();
            zza((zzbq) new zze(context));
            zza((zzbq) new zzf(context));
            zza((zzbq) new zzh(context));
            zza((zzbq) new zzi(context));
            zza((zzbq) new zzj(context));
            zza((zzbq) new zzk(context));
            zza((zzbq) new zzl(context));
            zza((zzbq) new zzt());
            zza((zzbq) new zzaj(this.zzbfc.getVersion()));
            zza((zzbq) new zzam(zzan));
            zza((zzbq) new zzas(dataLayer));
            zza((zzbq) new zzbc(context));
            zza((zzbq) new zzbd());
            zza((zzbq) new zzbk());
            zza((zzbq) new zzbp(this));
            zza((zzbq) new zzbu());
            zza((zzbq) new zzbv());
            zza((zzbq) new zzcv(context));
            zza((zzbq) new zzcx());
            zza((zzbq) new zzdd());
            zza((zzbq) new zzdk());
            zza((zzbq) new zzdm(context));
            zza((zzbq) new zzea());
            zza((zzbq) new zzee());
            zza((zzbq) new zzei());
            zza((zzbq) new zzek());
            zza((zzbq) new zzem(context));
            zza((zzbq) new zzfj());
            zza((zzbq) new zzfk());
            zza((zzbq) new zzge());
            zza((zzbq) new zzgl());
            this.zzbfk = new HashMap();
            for (zzrm zzrm : this.zzbfj) {
                for (int i = 0; i < zzrm.zzte().size(); i++) {
                    zzri zzri = (zzri) zzrm.zzte().get(i);
                    String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                    zzfi zzb = zzb(this.zzbfk, zza(zzri));
                    zzb.zza(zzrm);
                    zzb.zza(zzrm, zzri);
                    zzb.zza(zzrm, str);
                }
                for (int i2 = 0; i2 < zzrm.zztf().size(); i2++) {
                    zzri zzri2 = (zzri) zzrm.zztf().get(i2);
                    String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                    zzfi zzb2 = zzb(this.zzbfk, zza(zzri2));
                    zzb2.zza(zzrm);
                    zzb2.zzb(zzrm, zzri2);
                    zzb2.zzb(zzrm, str2);
                }
            }
            for (Entry entry : this.zzbfc.zztc().entrySet()) {
                for (zzri zzri3 : (List) entry.getValue()) {
                    if (!zzgj.zzg((zzp) zzri3.zzsi().get(zzb.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                        zzb(this.zzbfk, (String) entry.getKey()).zzb(zzri3);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("resource cannot be null");
    }

    public final synchronized void zzg(List<zzn> list) {
        zzi[] zziArr;
        Long l;
        synchronized (this) {
            for (zzn zzn : list) {
                if (zzn.name != null) {
                    if (zzn.name.startsWith("gaExperiment:")) {
                        DataLayer dataLayer = this.zzazr;
                        if (zzn.zzqe == null) {
                            zzdi.zzab("supplemental missing experimentSupplemental");
                        } else {
                            for (zzp zzc : zzn.zzqe.zzop) {
                                dataLayer.zzdh(zzgj.zzc(zzc));
                            }
                            zzp[] zzpArr = zzn.zzqe.zzoo;
                            int length = zzpArr.length;
                            int i = 0;
                            while (true) {
                                Map map = null;
                                if (i >= length) {
                                    break;
                                }
                                Object zzh = zzgj.zzh(zzpArr[i]);
                                if (!(zzh instanceof Map)) {
                                    String valueOf = String.valueOf(zzh);
                                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                                    sb.append("value: ");
                                    sb.append(valueOf);
                                    sb.append(" is not a map value, ignored.");
                                    zzdi.zzab(sb.toString());
                                } else {
                                    map = (Map) zzh;
                                }
                                if (map != null) {
                                    dataLayer.push(map);
                                }
                                i++;
                            }
                            for (zzi zzi : zzn.zzqe.zzoq) {
                                if (zzi.zzoj == null) {
                                    zzdi.zzab("GaExperimentRandom: No key");
                                } else {
                                    Object obj = dataLayer.get(zzi.zzoj);
                                    if (!(obj instanceof Number)) {
                                        l = null;
                                    } else {
                                        l = Long.valueOf(((Number) obj).longValue());
                                    }
                                    long j = zzi.zzok;
                                    long j2 = zzi.zzol;
                                    if (!zzi.zzom || l == null || l.longValue() < j || l.longValue() > j2) {
                                        if (j <= j2) {
                                            obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                                        } else {
                                            zzdi.zzab("GaExperimentRandom: random range invalid");
                                        }
                                    }
                                    dataLayer.zzdh(zzi.zzoj);
                                    Map zzk = DataLayer.zzk(zzi.zzoj, obj);
                                    if (zzi.zzon > 0) {
                                        if (!zzk.containsKey("gtm")) {
                                            zzk.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(zzi.zzon)));
                                        } else {
                                            Object obj2 = zzk.get("gtm");
                                            if (obj2 instanceof Map) {
                                                ((Map) obj2).put("lifetime", Long.valueOf(zzi.zzon));
                                            } else {
                                                zzdi.zzab("GaExperimentRandom: gtm not a map");
                                            }
                                        }
                                    }
                                    dataLayer.push(zzk);
                                }
                            }
                        }
                    }
                }
                String valueOf2 = String.valueOf(zzn);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 22);
                sb2.append("Ignored supplemental: ");
                sb2.append(valueOf2);
                zzdi.v(sb2.toString());
            }
        }
    }

    public final synchronized void zzde(String str) {
        zzea(str);
        zzar zzor = this.zzbfd.zzdq(str).zzor();
        for (zzri zza : (Set) zza(this.zzbfj, (Set<String>) new HashSet<String>(), (zzfg) new zzff(this), zzor.zzog()).getObject()) {
            zza(this.zzbfe, zza, (Set<String>) new HashSet<String>(), zzor.zzof());
        }
        zzea(null);
    }

    public final zzdz<zzp> zzdz(String str) {
        this.zzbfm = 0;
        return zza(str, (Set<String>) new HashSet<String>(), this.zzbfd.zzdp(str).zzoq());
    }

    @VisibleForTesting
    private final synchronized void zzea(String str) {
        this.zzbfl = str;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized String zzpt() {
        return this.zzbfl;
    }

    private static zzfi zzb(Map<String, zzfi> map, String str) {
        zzfi zzfi = (zzfi) map.get(str);
        if (zzfi != null) {
            return zzfi;
        }
        zzfi zzfi2 = new zzfi();
        map.put(str, zzfi2);
        return zzfi2;
    }

    private final zzdz<Set<zzri>> zza(Set<zzrm> set, Set<String> set2, zzfg zzfg, zzfa zzfa) {
        zzdz zzdz;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        boolean z = true;
        for (zzrm zzrm : set) {
            zzeq zzpg = zzfa.zzpg();
            Iterator it = zzrm.zzsl().iterator();
            boolean z2 = true;
            while (true) {
                if (!it.hasNext()) {
                    Iterator it2 = zzrm.zzsk().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            zzgj.zzj(Boolean.valueOf(true));
                            zzdz = new zzdz(Boolean.valueOf(true), z2);
                            break;
                        }
                        zzdz zza = zza((zzri) it2.next(), set2, zzpg.zzpb());
                        if (!((Boolean) zza.getObject()).booleanValue()) {
                            zzgj.zzj(Boolean.valueOf(false));
                            zzdz = new zzdz(Boolean.valueOf(false), zza.zzpi());
                            break;
                        }
                        z2 = z2 && zza.zzpi();
                    }
                } else {
                    zzdz zza2 = zza((zzri) it.next(), set2, zzpg.zzpa());
                    if (((Boolean) zza2.getObject()).booleanValue()) {
                        zzgj.zzj(Boolean.valueOf(false));
                        zzdz = new zzdz(Boolean.valueOf(false), zza2.zzpi());
                        break;
                    }
                    z2 = z2 && zza2.zzpi();
                }
            }
            if (((Boolean) zzdz.getObject()).booleanValue()) {
                zzfg.zza(zzrm, hashSet, hashSet2, zzpg);
            }
            z = z && zzdz.zzpi();
        }
        hashSet.removeAll(hashSet2);
        zzfa.zzb(hashSet);
        return new zzdz<>(hashSet, z);
    }

    private static String zza(zzri zzri) {
        return zzgj.zzc((zzp) zzri.zzsi().get(zzb.INSTANCE_NAME.toString()));
    }

    private static void zza(Map<String, zzbq> map, zzbq zzbq) {
        if (map.containsKey(zzbq.zzot())) {
            String str = "Duplicate function type name: ";
            String valueOf = String.valueOf(zzbq.zzot());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        map.put(zzbq.zzot(), zzbq);
    }

    @VisibleForTesting
    private final void zza(zzbq zzbq) {
        zza(this.zzbfg, zzbq);
    }

    @VisibleForTesting
    private final void zzb(zzbq zzbq) {
        zza(this.zzbfe, zzbq);
    }

    @VisibleForTesting
    private final void zzc(zzbq zzbq) {
        zza(this.zzbff, zzbq);
    }

    @VisibleForTesting
    private final zzdz<Boolean> zza(zzri zzri, Set<String> set, zzen zzen) {
        zzdz zza = zza(this.zzbff, zzri, set, zzen);
        Boolean zzg = zzgj.zzg((zzp) zza.getObject());
        zzen.zza(zzgj.zzj(zzg));
        return new zzdz<>(zzg, zza.zzpi());
    }

    private final String zzpu() {
        if (this.zzbfm <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzbfm));
        for (int i = 2; i < this.zzbfm; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private final zzdz<zzp> zza(String str, Set<String> set, zzdl zzdl) {
        zzri zzri;
        this.zzbfm++;
        zzfh zzfh = (zzfh) this.zzbfi.get(str);
        if (zzfh != null) {
            this.zzbfd.zzos();
            zza(zzfh.zzpw(), set);
            this.zzbfm--;
            return zzfh.zzpv();
        }
        zzfi zzfi = (zzfi) this.zzbfk.get(str);
        if (zzfi == null) {
            String zzpu = zzpu();
            StringBuilder sb = new StringBuilder(String.valueOf(zzpu).length() + 15 + String.valueOf(str).length());
            sb.append(zzpu);
            sb.append("Invalid macro: ");
            sb.append(str);
            zzdi.e(sb.toString());
            this.zzbfm--;
            return zzbfb;
        }
        Set zzpx = zzfi.zzpx();
        Map zzpy = zzfi.zzpy();
        Map zzpz = zzfi.zzpz();
        Map zzqb = zzfi.zzqb();
        Map zzqa = zzfi.zzqa();
        zzfa zzog = zzdl.zzog();
        zzfe zzfe = new zzfe(this, zzpy, zzpz, zzqb, zzqa);
        zzdz zza = zza(zzpx, set, (zzfg) zzfe, zzog);
        if (((Set) zza.getObject()).isEmpty()) {
            zzri = zzfi.zzqc();
        } else {
            if (((Set) zza.getObject()).size() > 1) {
                String zzpu2 = zzpu();
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzpu2).length() + 37 + String.valueOf(str).length());
                sb2.append(zzpu2);
                sb2.append("Multiple macros active for macroName ");
                sb2.append(str);
                zzdi.zzab(sb2.toString());
            }
            zzri = (zzri) ((Set) zza.getObject()).iterator().next();
        }
        if (zzri == null) {
            this.zzbfm--;
            return zzbfb;
        }
        zzdz<zzp> zza2 = zza(this.zzbfg, zzri, set, zzdl.zzoz());
        boolean z = zza.zzpi() && zza2.zzpi();
        zzdz<zzp> zzdz = zzbfb;
        if (zza2 != zzdz) {
            zzdz = new zzdz<>((zzp) zza2.getObject(), z);
        }
        zzp zzpw = zzri.zzpw();
        if (zzdz.zzpi()) {
            this.zzbfi.zza(str, new zzfh(zzdz, zzpw));
        }
        zza(zzpw, set);
        this.zzbfm--;
        return zzdz;
    }

    private final void zza(zzp zzp, Set<String> set) {
        if (zzp != null) {
            zzdz<zzp> zza = zza(zzp, set, (zzgm) new zzdx());
            if (zza != zzbfb) {
                Object zzh = zzgj.zzh((zzp) zza.getObject());
                if (zzh instanceof Map) {
                    this.zzazr.push((Map) zzh);
                } else if (zzh instanceof List) {
                    for (Object next : (List) zzh) {
                        if (next instanceof Map) {
                            this.zzazr.push((Map) next);
                        } else {
                            zzdi.zzab("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    zzdi.zzab("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private final zzdz<zzp> zza(zzp zzp, Set<String> set, zzgm zzgm) {
        if (!zzp.zzqs) {
            return new zzdz<>(zzp, true);
        }
        int i = zzp.type;
        if (i != 7) {
            switch (i) {
                case 2:
                    zzp zzk = zzrg.zzk(zzp);
                    zzk.zzqj = new zzp[zzp.zzqj.length];
                    for (int i2 = 0; i2 < zzp.zzqj.length; i2++) {
                        zzdz<zzp> zza = zza(zzp.zzqj[i2], set, zzgm.zzx(i2));
                        zzdz<zzp> zzdz = zzbfb;
                        if (zza == zzdz) {
                            return zzdz;
                        }
                        zzk.zzqj[i2] = (zzp) zza.getObject();
                    }
                    return new zzdz<>(zzk, false);
                case 3:
                    zzp zzk2 = zzrg.zzk(zzp);
                    if (zzp.zzqk.length != zzp.zzql.length) {
                        String str = "Invalid serving value: ";
                        String valueOf = String.valueOf(zzp.toString());
                        zzdi.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return zzbfb;
                    }
                    zzk2.zzqk = new zzp[zzp.zzqk.length];
                    zzk2.zzql = new zzp[zzp.zzqk.length];
                    for (int i3 = 0; i3 < zzp.zzqk.length; i3++) {
                        zzdz<zzp> zza2 = zza(zzp.zzqk[i3], set, zzgm.zzy(i3));
                        zzdz<zzp> zza3 = zza(zzp.zzql[i3], set, zzgm.zzz(i3));
                        zzdz<zzp> zzdz2 = zzbfb;
                        if (zza2 == zzdz2 || zza3 == zzdz2) {
                            return zzbfb;
                        }
                        zzk2.zzqk[i3] = (zzp) zza2.getObject();
                        zzk2.zzql[i3] = (zzp) zza3.getObject();
                    }
                    return new zzdz<>(zzk2, false);
                case 4:
                    if (set.contains(zzp.zzqm)) {
                        String str2 = zzp.zzqm;
                        String obj = set.toString();
                        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 79 + String.valueOf(obj).length());
                        sb.append("Macro cycle detected.  Current macro reference: ");
                        sb.append(str2);
                        sb.append(".  Previous macro references: ");
                        sb.append(obj);
                        sb.append(".");
                        zzdi.e(sb.toString());
                        return zzbfb;
                    }
                    set.add(zzp.zzqm);
                    zzdz<zzp> zza4 = zzgn.zza(zza(zzp.zzqm, set, zzgm.zzph()), zzp.zzqr);
                    set.remove(zzp.zzqm);
                    return zza4;
                default:
                    int i4 = zzp.type;
                    StringBuilder sb2 = new StringBuilder(25);
                    sb2.append("Unknown type: ");
                    sb2.append(i4);
                    zzdi.e(sb2.toString());
                    return zzbfb;
            }
        } else {
            zzp zzk3 = zzrg.zzk(zzp);
            zzk3.zzqq = new zzp[zzp.zzqq.length];
            for (int i5 = 0; i5 < zzp.zzqq.length; i5++) {
                zzdz<zzp> zza5 = zza(zzp.zzqq[i5], set, zzgm.zzaa(i5));
                zzdz<zzp> zzdz3 = zzbfb;
                if (zza5 == zzdz3) {
                    return zzdz3;
                }
                zzk3.zzqq[i5] = (zzp) zza5.getObject();
            }
            return new zzdz<>(zzk3, false);
        }
    }

    private final zzdz<zzp> zza(Map<String, zzbq> map, zzri zzri, Set<String> set, zzen zzen) {
        zzp zzp = (zzp) zzri.zzsi().get(zzb.FUNCTION.toString());
        if (zzp == null) {
            zzdi.e("No function id in properties");
            return zzbfb;
        }
        String str = zzp.zzqn;
        zzbq zzbq = (zzbq) map.get(str);
        if (zzbq == null) {
            zzdi.e(String.valueOf(str).concat(" has no backing implementation."));
            return zzbfb;
        }
        zzdz<zzp> zzdz = (zzdz) this.zzbfh.get(zzri);
        if (zzdz != null) {
            this.zzbfd.zzos();
            return zzdz;
        }
        HashMap hashMap = new HashMap();
        boolean z = true;
        boolean z2 = true;
        for (Entry entry : zzri.zzsi().entrySet()) {
            zzdz<zzp> zza = zza((zzp) entry.getValue(), set, zzen.zzdw((String) entry.getKey()).zzb((zzp) entry.getValue()));
            zzdz<zzp> zzdz2 = zzbfb;
            if (zza == zzdz2) {
                return zzdz2;
            }
            if (zza.zzpi()) {
                zzri.zza((String) entry.getKey(), (zzp) zza.getObject());
            } else {
                z2 = false;
            }
            hashMap.put((String) entry.getKey(), (zzp) zza.getObject());
        }
        if (!zzbq.zza(hashMap.keySet())) {
            String valueOf = String.valueOf(zzbq.zzou());
            String valueOf2 = String.valueOf(hashMap.keySet());
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Incorrect keys for function ");
            sb.append(str);
            sb.append(" required ");
            sb.append(valueOf);
            sb.append(" had ");
            sb.append(valueOf2);
            zzdi.e(sb.toString());
            return zzbfb;
        }
        if (!z2 || !zzbq.zznk()) {
            z = false;
        }
        zzdz<zzp> zzdz3 = new zzdz<>(zzbq.zzc(hashMap), z);
        if (z) {
            this.zzbfh.zza(zzri, zzdz3);
        }
        zzen.zza((zzp) zzdz3.getObject());
        return zzdz3;
    }
}
