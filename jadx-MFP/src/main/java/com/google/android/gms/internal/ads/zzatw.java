package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzbv;
import com.google.common.net.HttpHeaders;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzatw {
    private int mOrientation = -1;
    private zzaso zzbmy;
    private String zzday;
    private boolean zzdms = false;
    private final zzasi zzdnh;
    private List<String> zzdth;
    private String zzdtk;
    private String zzebb;
    private String zzebc;
    private List<String> zzebd;
    private String zzebe;
    private String zzebf;
    private String zzebg;
    private List<String> zzebh;
    private List<String> zzebi;
    private long zzebj = -1;
    private boolean zzebk = false;
    private final long zzebl = -1;
    private long zzebm = -1;
    private boolean zzebn = false;
    private boolean zzebo = false;
    private boolean zzebp = false;
    private boolean zzebq = true;
    private boolean zzebr = true;
    private String zzebs = "";
    private boolean zzebt = false;
    private zzawd zzebu;
    private List<String> zzebv;
    private List<String> zzebw;
    private boolean zzebx = false;
    private boolean zzeby = false;
    private String zzebz;
    private List<String> zzeca;
    private boolean zzecb;
    private String zzecc;
    private zzawo zzecd;
    private boolean zzece;
    private boolean zzecf;
    private boolean zzecg;
    private boolean zzech;

    private static String zza(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length());
                sb.append("Could not parse float from ");
                sb.append(str);
                sb.append(" header: ");
                sb.append(str2);
                zzaxz.zzeo(sb.toString());
            }
        }
        return -1;
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = (String) list.get(0);
            if (str2 != null) {
                return Arrays.asList(str2.trim().split("\\s+"));
            }
        }
        return null;
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public zzatw(zzasi zzasi, String str) {
        this.zzebc = str;
        this.zzdnh = zzasi;
    }

    public final zzasm zza(long j, zzaty zzaty) {
        int i;
        String str;
        zzasi zzasi = this.zzdnh;
        String str2 = this.zzebc;
        String str3 = this.zzday;
        List<String> list = this.zzebd;
        List<String> list2 = this.zzebh;
        long j2 = this.zzebj;
        boolean z = this.zzebk;
        List<String> list3 = this.zzdth;
        long j3 = this.zzebm;
        int i2 = this.mOrientation;
        String str4 = this.zzebb;
        String str5 = this.zzebf;
        String str6 = this.zzebg;
        String str7 = str5;
        boolean z2 = this.zzebn;
        boolean z3 = this.zzebo;
        boolean z4 = this.zzebp;
        boolean z5 = this.zzebq;
        String str8 = this.zzebs;
        boolean z6 = this.zzebt;
        boolean z7 = this.zzdms;
        zzawd zzawd = this.zzebu;
        List<String> list4 = this.zzebv;
        List<String> list5 = this.zzebw;
        boolean z8 = this.zzebx;
        zzaso zzaso = this.zzbmy;
        boolean z9 = this.zzeby;
        String str9 = this.zzebz;
        List<String> list6 = this.zzeca;
        boolean z10 = this.zzecb;
        String str10 = this.zzecc;
        zzawo zzawo = this.zzecd;
        String str11 = this.zzebe;
        boolean z11 = this.zzebr;
        boolean z12 = this.zzece;
        boolean z13 = this.zzecf;
        if (zzaty.zzwu()) {
            str = str4;
            i = 2;
        } else {
            str = str4;
            i = 1;
        }
        String str12 = str10;
        zzawo zzawo2 = zzawo;
        String str13 = str11;
        boolean z14 = z11;
        boolean z15 = z12;
        zzaso zzaso2 = zzaso;
        boolean z16 = z9;
        String str14 = str9;
        List<String> list7 = list6;
        boolean z17 = z10;
        List<String> list8 = list5;
        boolean z18 = z8;
        boolean z19 = z4;
        boolean z20 = z5;
        String str15 = str8;
        boolean z21 = z6;
        String str16 = str6;
        String str17 = str7;
        boolean z22 = z2;
        boolean z23 = z3;
        String str18 = str;
        long j4 = j;
        zzasm zzasm = new zzasm(zzasi, str2, str3, list, list2, j2, z, -1, list3, j3, i2, str18, j4, str17, str16, z22, z23, z19, z20, false, str15, z21, z7, zzawd, list4, list8, z18, zzaso2, z16, str14, list7, z17, str12, zzawo2, str13, z14, z15, z13, i, this.zzecg, this.zzebi, this.zzech, this.zzdtk, zzaty.zzwv(), zzaty.zzww());
        return zzasm;
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzday = str2;
        zzl(map);
    }

    public final void zzl(Map<String, List<String>> map) {
        this.zzebb = zza(map, "X-Afma-Ad-Size");
        this.zzecc = zza(map, "X-Afma-Ad-Slot-Size");
        List<String> zzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (zzc != null) {
            this.zzebd = zzc;
        }
        this.zzebe = zza(map, "X-Afma-Debug-Signals");
        List list = (List) map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzebf = (String) list.get(0);
        }
        List<String> zzc2 = zzc(map, "X-Afma-Tracking-Urls");
        if (zzc2 != null) {
            this.zzebh = zzc2;
        }
        List<String> zzc3 = zzc(map, "X-Afma-Downloaded-Impression-Urls");
        if (zzc3 != null) {
            this.zzebi = zzc3;
        }
        long zzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (zzb != -1) {
            this.zzebj = zzb;
        }
        this.zzebk |= zzd(map, "X-Afma-Mediation");
        List<String> zzc4 = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (zzc4 != null) {
            this.zzdth = zzc4;
        }
        long zzb2 = zzb(map, "X-Afma-Refresh-Rate");
        if (zzb2 != -1) {
            this.zzebm = zzb2;
        }
        List list2 = (List) map.get("X-Afma-Orientation");
        if (list2 != null && !list2.isEmpty()) {
            String str = (String) list2.get(0);
            if (Extras.ORIENTATION_PORTRAIT.equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzlh().zzzx();
            } else if (Extras.ORIENTATION_LANDSCAPE.equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzlh().zzzw();
            }
        }
        this.zzebg = zza(map, "X-Afma-ActiveView");
        List list3 = (List) map.get("X-Afma-Use-HTTPS");
        if (list3 != null && !list3.isEmpty()) {
            this.zzebp = Boolean.valueOf((String) list3.get(0)).booleanValue();
        }
        this.zzebn |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzebo = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(zza(map, "X-Afma-Ad-Format"));
        List list4 = (List) map.get("X-Afma-Content-Url-Opted-Out");
        if (list4 != null && !list4.isEmpty()) {
            this.zzebq = Boolean.valueOf((String) list4.get(0)).booleanValue();
        }
        List list5 = (List) map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list5 != null && !list5.isEmpty()) {
            this.zzebr = Boolean.valueOf((String) list5.get(0)).booleanValue();
        }
        List list6 = (List) map.get("X-Afma-Gws-Query-Id");
        if (list6 != null && !list6.isEmpty()) {
            this.zzebs = (String) list6.get(0);
        }
        String zza = zza(map, "X-Afma-Fluid");
        if (zza != null && zza.equals("height")) {
            this.zzebt = true;
        }
        this.zzdms = "native_express".equals(zza(map, "X-Afma-Ad-Format"));
        this.zzebu = zzawd.zzdh(zza(map, "X-Afma-Rewards"));
        if (this.zzebv == null) {
            this.zzebv = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzebw == null) {
            this.zzebw = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzebx |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzeby |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzebz = zza(map, HttpHeaders.SET_COOKIE);
        String zza2 = zza(map, "X-Afma-Auto-Protection-Configuration");
        if (zza2 == null || TextUtils.isEmpty(zza2)) {
            Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzebf)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzebf);
            }
            String builder = buildUpon.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(builder).length() + 31);
            sb.append(builder);
            sb.append("&navigationURL={NAVIGATION_URL}");
            this.zzbmy = new zzaso(true, Arrays.asList(new String[]{sb.toString()}));
        } else {
            try {
                this.zzbmy = zzaso.zzl(new JSONObject(zza2));
            } catch (JSONException e) {
                zzaxz.zzc("Error parsing configuration JSON", e);
                this.zzbmy = new zzaso();
            }
        }
        List<String> zzc5 = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (zzc5 != null) {
            this.zzeca = zzc5;
        }
        String zza3 = zza(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(zza3)) {
            try {
                this.zzecd = zzawo.zzo(new JSONObject(zza3));
            } catch (JSONException e2) {
                zzaxz.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzecb |= zzd(map, "X-Afma-Render-In-Browser");
        String zza4 = zza(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(zza4)) {
            try {
                this.zzece = new JSONObject(zza4).getBoolean("never_pool");
            } catch (JSONException e3) {
                zzaxz.zzc("Error parsing interstitial pool header", e3);
            }
        }
        this.zzecf = zzd(map, "X-Afma-Custom-Close-Blocked");
        this.zzecg = zzd(map, "X-Afma-Enable-Omid");
        this.zzech = zzd(map, "X-Afma-Disable-Closable-Area");
        this.zzdtk = zza(map, "X-Afma-Omid-Settings");
    }
}
