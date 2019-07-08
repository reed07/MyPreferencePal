package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.UserDataStore;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzab;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzaf;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.internal.measurement.zzah;
import com.google.android.gms.internal.measurement.zzai;
import com.google.android.gms.internal.measurement.zzaj;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzav;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzaz;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.internal.measurement.zzy;
import com.google.android.gms.internal.measurement.zzz;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzb extends zzat implements zzo {
    private static DecimalFormat zzrb;
    private final zzaw zzqx;
    private final String zzrc;
    private final Uri zzrd;

    public zzb(zzaw zzaw, String str) {
        this(zzaw, str, true, false);
    }

    private zzb(zzaw zzaw, String str, boolean z, boolean z2) {
        super(zzaw);
        Preconditions.checkNotEmpty(str);
        this.zzqx = zzaw;
        this.zzrc = str;
        this.zzrd = zzb(this.zzrc);
    }

    static Uri zzb(String str) {
        Preconditions.checkNotEmpty(str);
        Builder builder = new Builder();
        builder.scheme(ShareConstants.MEDIA_URI);
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public final Uri zzo() {
        return this.zzrd;
    }

    public final void zzb(zzg zzg) {
        Preconditions.checkNotNull(zzg);
        Preconditions.checkArgument(zzg.zzx(), "Can't deliver not submitted measurement");
        Preconditions.checkNotMainThread("deliver should be called on worker thread");
        zzg zzs = zzg.zzs();
        zzag zzag = (zzag) zzs.zzb(zzag.class);
        if (TextUtils.isEmpty(zzag.zzbc())) {
            zzby().zza(zzc(zzs), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzag.zzbd())) {
            zzby().zza(zzc(zzs), "Ignoring measurement without client id");
        } else if (!this.zzqx.zzco().getAppOptOut()) {
            double zzbj = zzag.zzbj();
            if (zzdg.zza(zzbj, zzag.zzbd())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzbj));
                return;
            }
            Map zzc = zzc(zzs);
            zzc.put("v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            zzc.put("_v", zzav.zzwa);
            zzc.put("tid", this.zzrc);
            if (this.zzqx.zzco().isDryRunEnabled()) {
                StringBuilder sb = new StringBuilder();
                for (Entry entry : zzc.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append(", ");
                    }
                    sb.append((String) entry.getKey());
                    sb.append("=");
                    sb.append((String) entry.getValue());
                }
                zzc("Dry run is enabled. GoogleAnalytics would have sent", sb.toString());
                return;
            }
            HashMap hashMap = new HashMap();
            zzdg.zzb((Map<String, String>) hashMap, "uid", zzag.zzbe());
            zzx zzx = (zzx) zzg.zza(zzx.class);
            if (zzx != null) {
                zzdg.zzb((Map<String, String>) hashMap, "an", zzx.zzaj());
                zzdg.zzb((Map<String, String>) hashMap, "aid", zzx.zzal());
                zzdg.zzb((Map<String, String>) hashMap, "av", zzx.zzak());
                zzdg.zzb((Map<String, String>) hashMap, "aiid", zzx.zzam());
            }
            zzaz zzaz = new zzaz(0, zzag.zzbd(), this.zzrc, !TextUtils.isEmpty(zzag.zzbf()), 0, hashMap);
            zzc.put("_s", String.valueOf(zzcc().zza(zzaz)));
            zzck zzck = new zzck(zzby(), zzc, zzg.zzv(), true);
            zzcc().zza(zzck);
        }
    }

    @VisibleForTesting
    private static Map<String, String> zzc(zzg zzg) {
        HashMap hashMap = new HashMap();
        zzab zzab = (zzab) zzg.zza(zzab.class);
        if (zzab != null) {
            for (Entry entry : zzab.zzaw().entrySet()) {
                Object value = entry.getValue();
                String str = null;
                if (value != null) {
                    if (value instanceof String) {
                        String str2 = (String) value;
                        if (!TextUtils.isEmpty(str2)) {
                            str = str2;
                        }
                    } else if (value instanceof Double) {
                        Double d = (Double) value;
                        if (d.doubleValue() != 0.0d) {
                            str = zza(d.doubleValue());
                        }
                    } else if (!(value instanceof Boolean)) {
                        str = String.valueOf(value);
                    } else if (value != Boolean.FALSE) {
                        str = AppEventsConstants.EVENT_PARAM_VALUE_YES;
                    }
                }
                if (str != null) {
                    hashMap.put((String) entry.getKey(), str);
                }
            }
        }
        zzag zzag = (zzag) zzg.zza(zzag.class);
        if (zzag != null) {
            zza((Map<String, String>) hashMap, "t", zzag.zzbc());
            zza((Map<String, String>) hashMap, "cid", zzag.zzbd());
            zza((Map<String, String>) hashMap, "uid", zzag.zzbe());
            zza((Map<String, String>) hashMap, "sc", zzag.zzbh());
            zza((Map<String, String>) hashMap, "sf", zzag.zzbj());
            zza((Map<String, String>) hashMap, "ni", zzag.zzbi());
            zza((Map<String, String>) hashMap, "adid", zzag.zzbf());
            zza((Map<String, String>) hashMap, "ate", zzag.zzbg());
        }
        zzah zzah = (zzah) zzg.zza(zzah.class);
        if (zzah != null) {
            zza((Map<String, String>) hashMap, "cd", zzah.zzbk());
            zza((Map<String, String>) hashMap, "a", (double) zzah.zzbl());
            zza((Map<String, String>) hashMap, "dr", zzah.zzbm());
        }
        zzae zzae = (zzae) zzg.zza(zzae.class);
        if (zzae != null) {
            zza((Map<String, String>) hashMap, "ec", zzae.zzbb());
            zza((Map<String, String>) hashMap, "ea", zzae.getAction());
            zza((Map<String, String>) hashMap, "el", zzae.getLabel());
            zza((Map<String, String>) hashMap, "ev", (double) zzae.getValue());
        }
        zzy zzy = (zzy) zzg.zza(zzy.class);
        if (zzy != null) {
            zza((Map<String, String>) hashMap, "cn", zzy.getName());
            zza((Map<String, String>) hashMap, "cs", zzy.getSource());
            zza((Map<String, String>) hashMap, "cm", zzy.zzan());
            zza((Map<String, String>) hashMap, "ck", zzy.zzao());
            zza((Map<String, String>) hashMap, "cc", zzy.zzap());
            zza((Map<String, String>) hashMap, "ci", zzy.getId());
            zza((Map<String, String>) hashMap, "anid", zzy.zzaq());
            zza((Map<String, String>) hashMap, "gclid", zzy.zzar());
            zza((Map<String, String>) hashMap, "dclid", zzy.zzas());
            zza((Map<String, String>) hashMap, Param.ACLID, zzy.zzat());
        }
        zzaf zzaf = (zzaf) zzg.zza(zzaf.class);
        if (zzaf != null) {
            zza((Map<String, String>) hashMap, "exd", zzaf.zzum);
            zza((Map<String, String>) hashMap, "exf", zzaf.zzun);
        }
        zzai zzai = (zzai) zzg.zza(zzai.class);
        if (zzai != null) {
            zza((Map<String, String>) hashMap, "sn", zzai.zzvd);
            zza((Map<String, String>) hashMap, "sa", zzai.zzve);
            zza((Map<String, String>) hashMap, UserDataStore.STATE, zzai.zzvf);
        }
        zzaj zzaj = (zzaj) zzg.zza(zzaj.class);
        if (zzaj != null) {
            zza((Map<String, String>) hashMap, "utv", zzaj.zzvg);
            zza((Map<String, String>) hashMap, "utt", (double) zzaj.zzvh);
            zza((Map<String, String>) hashMap, "utc", zzaj.mCategory);
            zza((Map<String, String>) hashMap, "utl", zzaj.zzvi);
        }
        zzz zzz = (zzz) zzg.zza(zzz.class);
        if (zzz != null) {
            for (Entry entry2 : zzz.zzau().entrySet()) {
                String zze = zzd.zze(((Integer) entry2.getKey()).intValue());
                if (!TextUtils.isEmpty(zze)) {
                    hashMap.put(zze, (String) entry2.getValue());
                }
            }
        }
        zzaa zzaa = (zzaa) zzg.zza(zzaa.class);
        if (zzaa != null) {
            for (Entry entry3 : zzaa.zzav().entrySet()) {
                String zzg2 = zzd.zzg(((Integer) entry3.getKey()).intValue());
                if (!TextUtils.isEmpty(zzg2)) {
                    hashMap.put(zzg2, zza(((Double) entry3.getValue()).doubleValue()));
                }
            }
        }
        zzad zzad = (zzad) zzg.zza(zzad.class);
        if (zzad != null) {
            ProductAction zzax = zzad.zzax();
            if (zzax != null) {
                for (Entry entry4 : zzax.build().entrySet()) {
                    if (((String) entry4.getKey()).startsWith("&")) {
                        hashMap.put(((String) entry4.getKey()).substring(1), (String) entry4.getValue());
                    } else {
                        hashMap.put((String) entry4.getKey(), (String) entry4.getValue());
                    }
                }
            }
            int i = 1;
            for (Promotion zzn : zzad.zzba()) {
                hashMap.putAll(zzn.zzn(zzd.zzk(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzn2 : zzad.zzay()) {
                hashMap.putAll(zzn2.zzn(zzd.zzi(i2)));
                i2++;
            }
            int i3 = 1;
            for (Entry entry5 : zzad.zzaz().entrySet()) {
                List<Product> list = (List) entry5.getValue();
                String zzn3 = zzd.zzn(i3);
                int i4 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzn3);
                    String valueOf2 = String.valueOf(zzd.zzl(i4));
                    hashMap.putAll(product.zzn(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry5.getKey())) {
                    String valueOf3 = String.valueOf(zzn3);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) entry5.getKey());
                }
                i3++;
            }
        }
        zzac zzac = (zzac) zzg.zza(zzac.class);
        if (zzac != null) {
            zza((Map<String, String>) hashMap, "ul", zzac.getLanguage());
            zza((Map<String, String>) hashMap, "sd", (double) zzac.zzug);
            zza(hashMap, "sr", zzac.zzuh, zzac.zzui);
            zza(hashMap, "vp", zzac.zzuj, zzac.zzuk);
        }
        zzx zzx = (zzx) zzg.zza(zzx.class);
        if (zzx != null) {
            zza((Map<String, String>) hashMap, "an", zzx.zzaj());
            zza((Map<String, String>) hashMap, "aid", zzx.zzal());
            zza((Map<String, String>) hashMap, "aiid", zzx.zzam());
            zza((Map<String, String>) hashMap, "av", zzx.zzak());
        }
        return hashMap;
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static String zza(double d) {
        if (zzrb == null) {
            zzrb = new DecimalFormat("0.######");
        }
        return zzrb.format(d);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zza(d));
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(i2);
            map.put(str, sb.toString());
        }
    }
}
