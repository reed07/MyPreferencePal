package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe.zzb;
import com.google.android.gms.internal.measurement.zzfe.zzb.zza;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;

public final class zzfw extends zzyc<zzfw> {
    private static volatile zzfw[] zzaxi;
    public String zzafh;
    public String zzafi;
    public String zzafk;
    public String zzafp;
    public String zzagm;
    public String zzahr;
    public String zzawr;
    public Integer zzaxj;
    public zzft[] zzaxk;
    public zzfz[] zzaxl;
    public Long zzaxm;
    public Long zzaxn;
    public Long zzaxo;
    public Long zzaxp;
    public Long zzaxq;
    public String zzaxr;
    public String zzaxs;
    public String zzaxt;
    public Integer zzaxu;
    public Long zzaxv;
    public Long zzaxw;
    public String zzaxx;
    public Boolean zzaxy;
    public Long zzaxz;
    public Integer zzaya;
    public Boolean zzayb;
    public zzfr[] zzayc;
    public Integer zzayd;
    private Integer zzaye;
    private Integer zzayf;
    public String zzayg;
    public Long zzayh;
    public Long zzayi;
    public String zzayj;
    private String zzayk;
    public Integer zzayl;
    public zzb zzaym;
    public int[] zzayn;
    private Long zzayo;
    public String zzts;
    public String zztt;

    public static zzfw[] zznb() {
        if (zzaxi == null) {
            synchronized (zzyg.zzcfe) {
                if (zzaxi == null) {
                    zzaxi = new zzfw[0];
                }
            }
        }
        return zzaxi;
    }

    public zzfw() {
        this.zzaxj = null;
        this.zzaxk = zzft.zzmz();
        this.zzaxl = zzfz.zznd();
        this.zzaxm = null;
        this.zzaxn = null;
        this.zzaxo = null;
        this.zzaxp = null;
        this.zzaxq = null;
        this.zzaxr = null;
        this.zzaxs = null;
        this.zzaxt = null;
        this.zzahr = null;
        this.zzaxu = null;
        this.zzafp = null;
        this.zztt = null;
        this.zzts = null;
        this.zzaxv = null;
        this.zzaxw = null;
        this.zzaxx = null;
        this.zzaxy = null;
        this.zzafh = null;
        this.zzaxz = null;
        this.zzaya = null;
        this.zzagm = null;
        this.zzafi = null;
        this.zzayb = null;
        this.zzayc = zzfr.zzmx();
        this.zzafk = null;
        this.zzayd = null;
        this.zzaye = null;
        this.zzayf = null;
        this.zzayg = null;
        this.zzayh = null;
        this.zzayi = null;
        this.zzayj = null;
        this.zzayk = null;
        this.zzayl = null;
        this.zzawr = null;
        this.zzaym = null;
        this.zzayn = zzyl.zzcaq;
        this.zzayo = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfw)) {
            return false;
        }
        zzfw zzfw = (zzfw) obj;
        Integer num = this.zzaxj;
        if (num == null) {
            if (zzfw.zzaxj != null) {
                return false;
            }
        } else if (!num.equals(zzfw.zzaxj)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzaxk, (Object[]) zzfw.zzaxk) || !zzyg.equals((Object[]) this.zzaxl, (Object[]) zzfw.zzaxl)) {
            return false;
        }
        Long l = this.zzaxm;
        if (l == null) {
            if (zzfw.zzaxm != null) {
                return false;
            }
        } else if (!l.equals(zzfw.zzaxm)) {
            return false;
        }
        Long l2 = this.zzaxn;
        if (l2 == null) {
            if (zzfw.zzaxn != null) {
                return false;
            }
        } else if (!l2.equals(zzfw.zzaxn)) {
            return false;
        }
        Long l3 = this.zzaxo;
        if (l3 == null) {
            if (zzfw.zzaxo != null) {
                return false;
            }
        } else if (!l3.equals(zzfw.zzaxo)) {
            return false;
        }
        Long l4 = this.zzaxp;
        if (l4 == null) {
            if (zzfw.zzaxp != null) {
                return false;
            }
        } else if (!l4.equals(zzfw.zzaxp)) {
            return false;
        }
        Long l5 = this.zzaxq;
        if (l5 == null) {
            if (zzfw.zzaxq != null) {
                return false;
            }
        } else if (!l5.equals(zzfw.zzaxq)) {
            return false;
        }
        String str = this.zzaxr;
        if (str == null) {
            if (zzfw.zzaxr != null) {
                return false;
            }
        } else if (!str.equals(zzfw.zzaxr)) {
            return false;
        }
        String str2 = this.zzaxs;
        if (str2 == null) {
            if (zzfw.zzaxs != null) {
                return false;
            }
        } else if (!str2.equals(zzfw.zzaxs)) {
            return false;
        }
        String str3 = this.zzaxt;
        if (str3 == null) {
            if (zzfw.zzaxt != null) {
                return false;
            }
        } else if (!str3.equals(zzfw.zzaxt)) {
            return false;
        }
        String str4 = this.zzahr;
        if (str4 == null) {
            if (zzfw.zzahr != null) {
                return false;
            }
        } else if (!str4.equals(zzfw.zzahr)) {
            return false;
        }
        Integer num2 = this.zzaxu;
        if (num2 == null) {
            if (zzfw.zzaxu != null) {
                return false;
            }
        } else if (!num2.equals(zzfw.zzaxu)) {
            return false;
        }
        String str5 = this.zzafp;
        if (str5 == null) {
            if (zzfw.zzafp != null) {
                return false;
            }
        } else if (!str5.equals(zzfw.zzafp)) {
            return false;
        }
        String str6 = this.zztt;
        if (str6 == null) {
            if (zzfw.zztt != null) {
                return false;
            }
        } else if (!str6.equals(zzfw.zztt)) {
            return false;
        }
        String str7 = this.zzts;
        if (str7 == null) {
            if (zzfw.zzts != null) {
                return false;
            }
        } else if (!str7.equals(zzfw.zzts)) {
            return false;
        }
        Long l6 = this.zzaxv;
        if (l6 == null) {
            if (zzfw.zzaxv != null) {
                return false;
            }
        } else if (!l6.equals(zzfw.zzaxv)) {
            return false;
        }
        Long l7 = this.zzaxw;
        if (l7 == null) {
            if (zzfw.zzaxw != null) {
                return false;
            }
        } else if (!l7.equals(zzfw.zzaxw)) {
            return false;
        }
        String str8 = this.zzaxx;
        if (str8 == null) {
            if (zzfw.zzaxx != null) {
                return false;
            }
        } else if (!str8.equals(zzfw.zzaxx)) {
            return false;
        }
        Boolean bool = this.zzaxy;
        if (bool == null) {
            if (zzfw.zzaxy != null) {
                return false;
            }
        } else if (!bool.equals(zzfw.zzaxy)) {
            return false;
        }
        String str9 = this.zzafh;
        if (str9 == null) {
            if (zzfw.zzafh != null) {
                return false;
            }
        } else if (!str9.equals(zzfw.zzafh)) {
            return false;
        }
        Long l8 = this.zzaxz;
        if (l8 == null) {
            if (zzfw.zzaxz != null) {
                return false;
            }
        } else if (!l8.equals(zzfw.zzaxz)) {
            return false;
        }
        Integer num3 = this.zzaya;
        if (num3 == null) {
            if (zzfw.zzaya != null) {
                return false;
            }
        } else if (!num3.equals(zzfw.zzaya)) {
            return false;
        }
        String str10 = this.zzagm;
        if (str10 == null) {
            if (zzfw.zzagm != null) {
                return false;
            }
        } else if (!str10.equals(zzfw.zzagm)) {
            return false;
        }
        String str11 = this.zzafi;
        if (str11 == null) {
            if (zzfw.zzafi != null) {
                return false;
            }
        } else if (!str11.equals(zzfw.zzafi)) {
            return false;
        }
        Boolean bool2 = this.zzayb;
        if (bool2 == null) {
            if (zzfw.zzayb != null) {
                return false;
            }
        } else if (!bool2.equals(zzfw.zzayb)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzayc, (Object[]) zzfw.zzayc)) {
            return false;
        }
        String str12 = this.zzafk;
        if (str12 == null) {
            if (zzfw.zzafk != null) {
                return false;
            }
        } else if (!str12.equals(zzfw.zzafk)) {
            return false;
        }
        Integer num4 = this.zzayd;
        if (num4 == null) {
            if (zzfw.zzayd != null) {
                return false;
            }
        } else if (!num4.equals(zzfw.zzayd)) {
            return false;
        }
        Integer num5 = this.zzaye;
        if (num5 == null) {
            if (zzfw.zzaye != null) {
                return false;
            }
        } else if (!num5.equals(zzfw.zzaye)) {
            return false;
        }
        Integer num6 = this.zzayf;
        if (num6 == null) {
            if (zzfw.zzayf != null) {
                return false;
            }
        } else if (!num6.equals(zzfw.zzayf)) {
            return false;
        }
        String str13 = this.zzayg;
        if (str13 == null) {
            if (zzfw.zzayg != null) {
                return false;
            }
        } else if (!str13.equals(zzfw.zzayg)) {
            return false;
        }
        Long l9 = this.zzayh;
        if (l9 == null) {
            if (zzfw.zzayh != null) {
                return false;
            }
        } else if (!l9.equals(zzfw.zzayh)) {
            return false;
        }
        Long l10 = this.zzayi;
        if (l10 == null) {
            if (zzfw.zzayi != null) {
                return false;
            }
        } else if (!l10.equals(zzfw.zzayi)) {
            return false;
        }
        String str14 = this.zzayj;
        if (str14 == null) {
            if (zzfw.zzayj != null) {
                return false;
            }
        } else if (!str14.equals(zzfw.zzayj)) {
            return false;
        }
        String str15 = this.zzayk;
        if (str15 == null) {
            if (zzfw.zzayk != null) {
                return false;
            }
        } else if (!str15.equals(zzfw.zzayk)) {
            return false;
        }
        Integer num7 = this.zzayl;
        if (num7 == null) {
            if (zzfw.zzayl != null) {
                return false;
            }
        } else if (!num7.equals(zzfw.zzayl)) {
            return false;
        }
        String str16 = this.zzawr;
        if (str16 == null) {
            if (zzfw.zzawr != null) {
                return false;
            }
        } else if (!str16.equals(zzfw.zzawr)) {
            return false;
        }
        zzb zzb = this.zzaym;
        if (zzb == null) {
            if (zzfw.zzaym != null) {
                return false;
            }
        } else if (!zzb.equals(zzfw.zzaym)) {
            return false;
        }
        if (!zzyg.equals(this.zzayn, zzfw.zzayn)) {
            return false;
        }
        Long l11 = this.zzayo;
        if (l11 == null) {
            if (zzfw.zzayo != null) {
                return false;
            }
        } else if (!l11.equals(zzfw.zzayo)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfw.zzcev == null || zzfw.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfw.zzcev);
    }

    public final int hashCode() {
        int i;
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzaxj;
        int i2 = 0;
        int hashCode2 = (((((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzaxk)) * 31) + zzyg.hashCode((Object[]) this.zzaxl)) * 31;
        Long l = this.zzaxm;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.zzaxn;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.zzaxo;
        int hashCode5 = (hashCode4 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.zzaxp;
        int hashCode6 = (hashCode5 + (l4 == null ? 0 : l4.hashCode())) * 31;
        Long l5 = this.zzaxq;
        int hashCode7 = (hashCode6 + (l5 == null ? 0 : l5.hashCode())) * 31;
        String str = this.zzaxr;
        int hashCode8 = (hashCode7 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzaxs;
        int hashCode9 = (hashCode8 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzaxt;
        int hashCode10 = (hashCode9 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.zzahr;
        int hashCode11 = (hashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num2 = this.zzaxu;
        int hashCode12 = (hashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str5 = this.zzafp;
        int hashCode13 = (hashCode12 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.zztt;
        int hashCode14 = (hashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.zzts;
        int hashCode15 = (hashCode14 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Long l6 = this.zzaxv;
        int hashCode16 = (hashCode15 + (l6 == null ? 0 : l6.hashCode())) * 31;
        Long l7 = this.zzaxw;
        int hashCode17 = (hashCode16 + (l7 == null ? 0 : l7.hashCode())) * 31;
        String str8 = this.zzaxx;
        int hashCode18 = (hashCode17 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Boolean bool = this.zzaxy;
        int hashCode19 = (hashCode18 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str9 = this.zzafh;
        int hashCode20 = (hashCode19 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Long l8 = this.zzaxz;
        int hashCode21 = (hashCode20 + (l8 == null ? 0 : l8.hashCode())) * 31;
        Integer num3 = this.zzaya;
        int hashCode22 = (hashCode21 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str10 = this.zzagm;
        int hashCode23 = (hashCode22 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.zzafi;
        int hashCode24 = (hashCode23 + (str11 == null ? 0 : str11.hashCode())) * 31;
        Boolean bool2 = this.zzayb;
        int hashCode25 = (((hashCode24 + (bool2 == null ? 0 : bool2.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzayc)) * 31;
        String str12 = this.zzafk;
        int hashCode26 = (hashCode25 + (str12 == null ? 0 : str12.hashCode())) * 31;
        Integer num4 = this.zzayd;
        int hashCode27 = (hashCode26 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.zzaye;
        int hashCode28 = (hashCode27 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.zzayf;
        int hashCode29 = (hashCode28 + (num6 == null ? 0 : num6.hashCode())) * 31;
        String str13 = this.zzayg;
        int hashCode30 = (hashCode29 + (str13 == null ? 0 : str13.hashCode())) * 31;
        Long l9 = this.zzayh;
        int hashCode31 = (hashCode30 + (l9 == null ? 0 : l9.hashCode())) * 31;
        Long l10 = this.zzayi;
        int hashCode32 = (hashCode31 + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str14 = this.zzayj;
        int hashCode33 = (hashCode32 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.zzayk;
        int hashCode34 = (hashCode33 + (str15 == null ? 0 : str15.hashCode())) * 31;
        Integer num7 = this.zzayl;
        int hashCode35 = (hashCode34 + (num7 == null ? 0 : num7.hashCode())) * 31;
        String str16 = this.zzawr;
        int hashCode36 = hashCode35 + (str16 == null ? 0 : str16.hashCode());
        zzb zzb = this.zzaym;
        int i3 = hashCode36 * 31;
        if (zzb == null) {
            i = 0;
        } else {
            i = zzb.hashCode();
        }
        int hashCode37 = (((i3 + i) * 31) + zzyg.hashCode(this.zzayn)) * 31;
        Long l11 = this.zzayo;
        int hashCode38 = (hashCode37 + (l11 == null ? 0 : l11.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i2 = this.zzcev.hashCode();
        }
        return hashCode38 + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzaxj;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        zzft[] zzftArr = this.zzaxk;
        if (zzftArr != null && zzftArr.length > 0) {
            int i = 0;
            while (true) {
                zzft[] zzftArr2 = this.zzaxk;
                if (i >= zzftArr2.length) {
                    break;
                }
                zzft zzft = zzftArr2[i];
                if (zzft != null) {
                    zzya.zza(2, (zzyi) zzft);
                }
                i++;
            }
        }
        zzfz[] zzfzArr = this.zzaxl;
        if (zzfzArr != null && zzfzArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfz[] zzfzArr2 = this.zzaxl;
                if (i2 >= zzfzArr2.length) {
                    break;
                }
                zzfz zzfz = zzfzArr2[i2];
                if (zzfz != null) {
                    zzya.zza(3, (zzyi) zzfz);
                }
                i2++;
            }
        }
        Long l = this.zzaxm;
        if (l != null) {
            zzya.zzi(4, l.longValue());
        }
        Long l2 = this.zzaxn;
        if (l2 != null) {
            zzya.zzi(5, l2.longValue());
        }
        Long l3 = this.zzaxo;
        if (l3 != null) {
            zzya.zzi(6, l3.longValue());
        }
        Long l4 = this.zzaxq;
        if (l4 != null) {
            zzya.zzi(7, l4.longValue());
        }
        String str = this.zzaxr;
        if (str != null) {
            zzya.zzb(8, str);
        }
        String str2 = this.zzaxs;
        if (str2 != null) {
            zzya.zzb(9, str2);
        }
        String str3 = this.zzaxt;
        if (str3 != null) {
            zzya.zzb(10, str3);
        }
        String str4 = this.zzahr;
        if (str4 != null) {
            zzya.zzb(11, str4);
        }
        Integer num2 = this.zzaxu;
        if (num2 != null) {
            zzya.zzd(12, num2.intValue());
        }
        String str5 = this.zzafp;
        if (str5 != null) {
            zzya.zzb(13, str5);
        }
        String str6 = this.zztt;
        if (str6 != null) {
            zzya.zzb(14, str6);
        }
        String str7 = this.zzts;
        if (str7 != null) {
            zzya.zzb(16, str7);
        }
        Long l5 = this.zzaxv;
        if (l5 != null) {
            zzya.zzi(17, l5.longValue());
        }
        Long l6 = this.zzaxw;
        if (l6 != null) {
            zzya.zzi(18, l6.longValue());
        }
        String str8 = this.zzaxx;
        if (str8 != null) {
            zzya.zzb(19, str8);
        }
        Boolean bool = this.zzaxy;
        if (bool != null) {
            zzya.zzb(20, bool.booleanValue());
        }
        String str9 = this.zzafh;
        if (str9 != null) {
            zzya.zzb(21, str9);
        }
        Long l7 = this.zzaxz;
        if (l7 != null) {
            zzya.zzi(22, l7.longValue());
        }
        Integer num3 = this.zzaya;
        if (num3 != null) {
            zzya.zzd(23, num3.intValue());
        }
        String str10 = this.zzagm;
        if (str10 != null) {
            zzya.zzb(24, str10);
        }
        String str11 = this.zzafi;
        if (str11 != null) {
            zzya.zzb(25, str11);
        }
        Long l8 = this.zzaxp;
        if (l8 != null) {
            zzya.zzi(26, l8.longValue());
        }
        Boolean bool2 = this.zzayb;
        if (bool2 != null) {
            zzya.zzb(28, bool2.booleanValue());
        }
        zzfr[] zzfrArr = this.zzayc;
        if (zzfrArr != null && zzfrArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzfr[] zzfrArr2 = this.zzayc;
                if (i3 >= zzfrArr2.length) {
                    break;
                }
                zzfr zzfr = zzfrArr2[i3];
                if (zzfr != null) {
                    zzya.zza(29, (zzyi) zzfr);
                }
                i3++;
            }
        }
        String str12 = this.zzafk;
        if (str12 != null) {
            zzya.zzb(30, str12);
        }
        Integer num4 = this.zzayd;
        if (num4 != null) {
            zzya.zzd(31, num4.intValue());
        }
        Integer num5 = this.zzaye;
        if (num5 != null) {
            zzya.zzd(32, num5.intValue());
        }
        Integer num6 = this.zzayf;
        if (num6 != null) {
            zzya.zzd(33, num6.intValue());
        }
        String str13 = this.zzayg;
        if (str13 != null) {
            zzya.zzb(34, str13);
        }
        Long l9 = this.zzayh;
        if (l9 != null) {
            zzya.zzi(35, l9.longValue());
        }
        Long l10 = this.zzayi;
        if (l10 != null) {
            zzya.zzi(36, l10.longValue());
        }
        String str14 = this.zzayj;
        if (str14 != null) {
            zzya.zzb(37, str14);
        }
        String str15 = this.zzayk;
        if (str15 != null) {
            zzya.zzb(38, str15);
        }
        Integer num7 = this.zzayl;
        if (num7 != null) {
            zzya.zzd(39, num7.intValue());
        }
        String str16 = this.zzawr;
        if (str16 != null) {
            zzya.zzb(41, str16);
        }
        zzb zzb = this.zzaym;
        if (zzb != null) {
            zzya.zze(44, zzb);
        }
        int[] iArr = this.zzayn;
        if (iArr != null && iArr.length > 0) {
            int i4 = 0;
            while (true) {
                int[] iArr2 = this.zzayn;
                if (i4 >= iArr2.length) {
                    break;
                }
                int i5 = iArr2[i4];
                zzya.zzc(45, 0);
                zzya.zzcd(i5);
                i4++;
            }
        }
        Long l11 = this.zzayo;
        if (l11 != null) {
            zzya.zzi(46, l11.longValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int[] iArr;
        int zzf = super.zzf();
        Integer num = this.zzaxj;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        zzft[] zzftArr = this.zzaxk;
        int i = 0;
        if (zzftArr != null && zzftArr.length > 0) {
            int i2 = zzf;
            int i3 = 0;
            while (true) {
                zzft[] zzftArr2 = this.zzaxk;
                if (i3 >= zzftArr2.length) {
                    break;
                }
                zzft zzft = zzftArr2[i3];
                if (zzft != null) {
                    i2 += zzya.zzb(2, (zzyi) zzft);
                }
                i3++;
            }
            zzf = i2;
        }
        zzfz[] zzfzArr = this.zzaxl;
        if (zzfzArr != null && zzfzArr.length > 0) {
            int i4 = zzf;
            int i5 = 0;
            while (true) {
                zzfz[] zzfzArr2 = this.zzaxl;
                if (i5 >= zzfzArr2.length) {
                    break;
                }
                zzfz zzfz = zzfzArr2[i5];
                if (zzfz != null) {
                    i4 += zzya.zzb(3, (zzyi) zzfz);
                }
                i5++;
            }
            zzf = i4;
        }
        Long l = this.zzaxm;
        if (l != null) {
            zzf += zzya.zzd(4, l.longValue());
        }
        Long l2 = this.zzaxn;
        if (l2 != null) {
            zzf += zzya.zzd(5, l2.longValue());
        }
        Long l3 = this.zzaxo;
        if (l3 != null) {
            zzf += zzya.zzd(6, l3.longValue());
        }
        Long l4 = this.zzaxq;
        if (l4 != null) {
            zzf += zzya.zzd(7, l4.longValue());
        }
        String str = this.zzaxr;
        if (str != null) {
            zzf += zzya.zzc(8, str);
        }
        String str2 = this.zzaxs;
        if (str2 != null) {
            zzf += zzya.zzc(9, str2);
        }
        String str3 = this.zzaxt;
        if (str3 != null) {
            zzf += zzya.zzc(10, str3);
        }
        String str4 = this.zzahr;
        if (str4 != null) {
            zzf += zzya.zzc(11, str4);
        }
        Integer num2 = this.zzaxu;
        if (num2 != null) {
            zzf += zzya.zzh(12, num2.intValue());
        }
        String str5 = this.zzafp;
        if (str5 != null) {
            zzf += zzya.zzc(13, str5);
        }
        String str6 = this.zztt;
        if (str6 != null) {
            zzf += zzya.zzc(14, str6);
        }
        String str7 = this.zzts;
        if (str7 != null) {
            zzf += zzya.zzc(16, str7);
        }
        Long l5 = this.zzaxv;
        if (l5 != null) {
            zzf += zzya.zzd(17, l5.longValue());
        }
        Long l6 = this.zzaxw;
        if (l6 != null) {
            zzf += zzya.zzd(18, l6.longValue());
        }
        String str8 = this.zzaxx;
        if (str8 != null) {
            zzf += zzya.zzc(19, str8);
        }
        Boolean bool = this.zzaxy;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(20) + 1;
        }
        String str9 = this.zzafh;
        if (str9 != null) {
            zzf += zzya.zzc(21, str9);
        }
        Long l7 = this.zzaxz;
        if (l7 != null) {
            zzf += zzya.zzd(22, l7.longValue());
        }
        Integer num3 = this.zzaya;
        if (num3 != null) {
            zzf += zzya.zzh(23, num3.intValue());
        }
        String str10 = this.zzagm;
        if (str10 != null) {
            zzf += zzya.zzc(24, str10);
        }
        String str11 = this.zzafi;
        if (str11 != null) {
            zzf += zzya.zzc(25, str11);
        }
        Long l8 = this.zzaxp;
        if (l8 != null) {
            zzf += zzya.zzd(26, l8.longValue());
        }
        Boolean bool2 = this.zzayb;
        if (bool2 != null) {
            bool2.booleanValue();
            zzf += zzya.zzbd(28) + 1;
        }
        zzfr[] zzfrArr = this.zzayc;
        if (zzfrArr != null && zzfrArr.length > 0) {
            int i6 = zzf;
            int i7 = 0;
            while (true) {
                zzfr[] zzfrArr2 = this.zzayc;
                if (i7 >= zzfrArr2.length) {
                    break;
                }
                zzfr zzfr = zzfrArr2[i7];
                if (zzfr != null) {
                    i6 += zzya.zzb(29, (zzyi) zzfr);
                }
                i7++;
            }
            zzf = i6;
        }
        String str12 = this.zzafk;
        if (str12 != null) {
            zzf += zzya.zzc(30, str12);
        }
        Integer num4 = this.zzayd;
        if (num4 != null) {
            zzf += zzya.zzh(31, num4.intValue());
        }
        Integer num5 = this.zzaye;
        if (num5 != null) {
            zzf += zzya.zzh(32, num5.intValue());
        }
        Integer num6 = this.zzayf;
        if (num6 != null) {
            zzf += zzya.zzh(33, num6.intValue());
        }
        String str13 = this.zzayg;
        if (str13 != null) {
            zzf += zzya.zzc(34, str13);
        }
        Long l9 = this.zzayh;
        if (l9 != null) {
            zzf += zzya.zzd(35, l9.longValue());
        }
        Long l10 = this.zzayi;
        if (l10 != null) {
            zzf += zzya.zzd(36, l10.longValue());
        }
        String str14 = this.zzayj;
        if (str14 != null) {
            zzf += zzya.zzc(37, str14);
        }
        String str15 = this.zzayk;
        if (str15 != null) {
            zzf += zzya.zzc(38, str15);
        }
        Integer num7 = this.zzayl;
        if (num7 != null) {
            zzf += zzya.zzh(39, num7.intValue());
        }
        String str16 = this.zzawr;
        if (str16 != null) {
            zzf += zzya.zzc(41, str16);
        }
        zzb zzb = this.zzaym;
        if (zzb != null) {
            zzf += zztv.zzc(44, (zzvv) zzb);
        }
        int[] iArr2 = this.zzayn;
        if (iArr2 != null && iArr2.length > 0) {
            int i8 = 0;
            while (true) {
                iArr = this.zzayn;
                if (i >= iArr.length) {
                    break;
                }
                i8 += zzya.zzbl(iArr[i]);
                i++;
            }
            zzf = zzf + i8 + (iArr.length * 2);
        }
        Long l11 = this.zzayo;
        return l11 != null ? zzf + zzya.zzd(46, l11.longValue()) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            switch (zzuj) {
                case 0:
                    return this;
                case 8:
                    this.zzaxj = Integer.valueOf(zzxz.zzvb());
                    break;
                case 18:
                    int zzb = zzyl.zzb(zzxz, 18);
                    zzft[] zzftArr = this.zzaxk;
                    int length = zzftArr == null ? 0 : zzftArr.length;
                    zzft[] zzftArr2 = new zzft[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzaxk, 0, zzftArr2, 0, length);
                    }
                    while (length < zzftArr2.length - 1) {
                        zzftArr2[length] = new zzft();
                        zzxz.zza((zzyi) zzftArr2[length]);
                        zzxz.zzuj();
                        length++;
                    }
                    zzftArr2[length] = new zzft();
                    zzxz.zza((zzyi) zzftArr2[length]);
                    this.zzaxk = zzftArr2;
                    break;
                case 26:
                    int zzb2 = zzyl.zzb(zzxz, 26);
                    zzfz[] zzfzArr = this.zzaxl;
                    int length2 = zzfzArr == null ? 0 : zzfzArr.length;
                    zzfz[] zzfzArr2 = new zzfz[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzaxl, 0, zzfzArr2, 0, length2);
                    }
                    while (length2 < zzfzArr2.length - 1) {
                        zzfzArr2[length2] = new zzfz();
                        zzxz.zza((zzyi) zzfzArr2[length2]);
                        zzxz.zzuj();
                        length2++;
                    }
                    zzfzArr2[length2] = new zzfz();
                    zzxz.zza((zzyi) zzfzArr2[length2]);
                    this.zzaxl = zzfzArr2;
                    break;
                case 32:
                    this.zzaxm = Long.valueOf(zzxz.zzvc());
                    break;
                case 40:
                    this.zzaxn = Long.valueOf(zzxz.zzvc());
                    break;
                case 48:
                    this.zzaxo = Long.valueOf(zzxz.zzvc());
                    break;
                case 56:
                    this.zzaxq = Long.valueOf(zzxz.zzvc());
                    break;
                case 66:
                    this.zzaxr = zzxz.readString();
                    break;
                case 74:
                    this.zzaxs = zzxz.readString();
                    break;
                case 82:
                    this.zzaxt = zzxz.readString();
                    break;
                case 90:
                    this.zzahr = zzxz.readString();
                    break;
                case 96:
                    this.zzaxu = Integer.valueOf(zzxz.zzvb());
                    break;
                case 106:
                    this.zzafp = zzxz.readString();
                    break;
                case 114:
                    this.zztt = zzxz.readString();
                    break;
                case 130:
                    this.zzts = zzxz.readString();
                    break;
                case 136:
                    this.zzaxv = Long.valueOf(zzxz.zzvc());
                    break;
                case 144:
                    this.zzaxw = Long.valueOf(zzxz.zzvc());
                    break;
                case PacketTypes.UsernameValidationResponse /*154*/:
                    this.zzaxx = zzxz.readString();
                    break;
                case 160:
                    this.zzaxy = Boolean.valueOf(zzxz.zzup());
                    break;
                case RequestCodes.CHALLENGES /*170*/:
                    this.zzafh = zzxz.readString();
                    break;
                case 176:
                    this.zzaxz = Long.valueOf(zzxz.zzvc());
                    break;
                case RequestCodes.SEARCH_MATCH /*184*/:
                    this.zzaya = Integer.valueOf(zzxz.zzvb());
                    break;
                case RequestCodes.BARCODE_FOOD_MULTI_ADD_EDITOR /*194*/:
                    this.zzagm = zzxz.readString();
                    break;
                case RequestCodes.RECIPE_DETAILS /*202*/:
                    this.zzafi = zzxz.readString();
                    break;
                case RequestCodes.PREMIUM_UPSELL /*208*/:
                    this.zzaxp = Long.valueOf(zzxz.zzvc());
                    break;
                case 224:
                    this.zzayb = Boolean.valueOf(zzxz.zzup());
                    break;
                case 234:
                    int zzb3 = zzyl.zzb(zzxz, 234);
                    zzfr[] zzfrArr = this.zzayc;
                    int length3 = zzfrArr == null ? 0 : zzfrArr.length;
                    zzfr[] zzfrArr2 = new zzfr[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzayc, 0, zzfrArr2, 0, length3);
                    }
                    while (length3 < zzfrArr2.length - 1) {
                        zzfrArr2[length3] = new zzfr();
                        zzxz.zza((zzyi) zzfrArr2[length3]);
                        zzxz.zzuj();
                        length3++;
                    }
                    zzfrArr2[length3] = new zzfr();
                    zzxz.zza((zzyi) zzfrArr2[length3]);
                    this.zzayc = zzfrArr2;
                    break;
                case 242:
                    this.zzafk = zzxz.readString();
                    break;
                case 248:
                    this.zzayd = Integer.valueOf(zzxz.zzvb());
                    break;
                case 256:
                    this.zzaye = Integer.valueOf(zzxz.zzvb());
                    break;
                case 264:
                    this.zzayf = Integer.valueOf(zzxz.zzvb());
                    break;
                case 274:
                    this.zzayg = zzxz.readString();
                    break;
                case 280:
                    this.zzayh = Long.valueOf(zzxz.zzvc());
                    break;
                case 288:
                    this.zzayi = Long.valueOf(zzxz.zzvc());
                    break;
                case 298:
                    this.zzayj = zzxz.readString();
                    break;
                case 306:
                    this.zzayk = zzxz.readString();
                    break;
                case 312:
                    this.zzayl = Integer.valueOf(zzxz.zzvb());
                    break;
                case 330:
                    this.zzawr = zzxz.readString();
                    break;
                case 354:
                    zzb zzb4 = (zzb) zzxz.zza(zzb.zza());
                    zzb zzb5 = this.zzaym;
                    if (zzb5 != null) {
                        zzb4 = (zzb) ((zzuo) ((zza) ((zza) zzb5.zzwf()).zza(zzb4)).zzwo());
                    }
                    this.zzaym = zzb4;
                    break;
                case 360:
                    int zzb6 = zzyl.zzb(zzxz, 360);
                    int[] iArr = this.zzayn;
                    int length4 = iArr == null ? 0 : iArr.length;
                    int[] iArr2 = new int[(zzb6 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzayn, 0, iArr2, 0, length4);
                    }
                    while (length4 < iArr2.length - 1) {
                        iArr2[length4] = zzxz.zzvb();
                        zzxz.zzuj();
                        length4++;
                    }
                    iArr2[length4] = zzxz.zzvb();
                    this.zzayn = iArr2;
                    break;
                case 362:
                    int zzas = zzxz.zzas(zzxz.zzvb());
                    int position = zzxz.getPosition();
                    int i = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i++;
                    }
                    zzxz.zzcb(position);
                    int[] iArr3 = this.zzayn;
                    int length5 = iArr3 == null ? 0 : iArr3.length;
                    int[] iArr4 = new int[(i + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzayn, 0, iArr4, 0, length5);
                    }
                    while (length5 < iArr4.length) {
                        iArr4[length5] = zzxz.zzvb();
                        length5++;
                    }
                    this.zzayn = iArr4;
                    zzxz.zzat(zzas);
                    break;
                case 368:
                    this.zzayo = Long.valueOf(zzxz.zzvc());
                    break;
                default:
                    if (super.zza(zzxz, zzuj)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }
}
