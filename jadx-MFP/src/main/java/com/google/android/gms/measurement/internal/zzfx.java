package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzdq;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.myfitnesspal.shared.constants.UtmParams;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzfx extends zzcs {
    private static final String[] zzauq = {"firebase_", "google_", "ga_"};
    private int zzado;
    private SecureRandom zzaur;
    private final AtomicLong zzaus = new AtomicLong(0);
    private Integer zzaut = null;

    zzfx(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzgz() {
        zzaf();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzgt().zzjj().zzby("Utils falling back to Random for random id");
            }
        }
        this.zzaus.set(nextLong);
    }

    public final long zzmj() {
        long andIncrement;
        long j;
        if (this.zzaus.get() == 0) {
            synchronized (this.zzaus) {
                long nextLong = new Random(System.nanoTime() ^ zzbx().currentTimeMillis()).nextLong();
                int i = this.zzado + 1;
                this.zzado = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.zzaus) {
            this.zzaus.compareAndSet(-1, 1);
            andIncrement = this.zzaus.getAndIncrement();
        }
        return andIncrement;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final SecureRandom zzmk() {
        zzaf();
        if (this.zzaur == null) {
            this.zzaur = new SecureRandom();
        }
        return this.zzaur;
    }

    static boolean zzct(String str) {
        Preconditions.checkNotEmpty(str);
        if (str.charAt(0) != '_' || str.equals("_ep")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final Bundle zza(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                str4 = uri.getQueryParameter("utm_campaign");
                str3 = uri.getQueryParameter("utm_source");
                str2 = uri.getQueryParameter("utm_medium");
                str = uri.getQueryParameter("gclid");
            } else {
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString("campaign", str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("source", str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString("medium", str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("gclid", str);
            }
            String queryParameter = uri.getQueryParameter(UtmParams.UTM_TERM);
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.TERM, queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter(UtmParams.UTM_CONTENT);
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(Param.CONTENT, queryParameter2);
            }
            String queryParameter3 = uri.getQueryParameter(Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(Param.ACLID, queryParameter3);
            }
            String queryParameter4 = uri.getQueryParameter(Param.CP1);
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString(Param.CP1, queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("anid", queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            zzgt().zzjj().zzg("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    static boolean zzc(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzs(String str, String str2) {
        if (str2 == null) {
            zzgt().zzjg().zzg("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzgt().zzjg().zzg("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzgt().zzjg().zze("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzgt().zzjg().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    private final boolean zzt(String str, String str2) {
        if (str2 == null) {
            zzgt().zzjg().zzg("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzgt().zzjg().zzg("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzgt().zzjg().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzgt().zzjg().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, String[] strArr, String str2) {
        boolean z;
        boolean z2;
        if (str2 == null) {
            zzgt().zzjg().zzg("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr2 = zzauq;
        int length = strArr2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            zzgt().zzjg().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            Preconditions.checkNotNull(strArr);
            int length2 = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z2 = false;
                    break;
                } else if (zzv(str2, strArr[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2) {
                zzgt().zzjg().zze("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, int i, String str2) {
        if (str2 == null) {
            zzgt().zzjg().zzg("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzgt().zzjg().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final int zzcu(String str) {
        if (!zzt("event", str)) {
            return 2;
        }
        if (!zza("event", zzcu.zzaqt, str)) {
            return 13;
        }
        if (!zza("event", 40, str)) {
            return 2;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final int zzcv(String str) {
        if (!zzt("user property", str)) {
            return 6;
        }
        if (!zza("user property", zzcw.zzaqx, str)) {
            return 15;
        }
        if (!zza("user property", 24, str)) {
            return 6;
        }
        return 0;
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        Parcelable[] parcelableArr;
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            String valueOf = String.valueOf(obj);
            if (valueOf.codePointCount(0, valueOf.length()) <= i) {
                return true;
            }
            zzgt().zzjj().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
            return false;
        } else if ((obj instanceof Bundle) && z) {
            return true;
        } else {
            if ((obj instanceof Parcelable[]) && z) {
                for (Parcelable parcelable : (Parcelable[]) obj) {
                    if (!(parcelable instanceof Bundle)) {
                        zzgt().zzjj().zze("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str2);
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof ArrayList) || !z) {
                return false;
            } else {
                ArrayList arrayList = (ArrayList) obj;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList.get(i2);
                    i2++;
                    if (!(obj2 instanceof Bundle)) {
                        zzgt().zzjj().zze("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzu(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (!zzcw(str)) {
                if (this.zzada.zzkn()) {
                    zzgt().zzjg().zzg("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzas.zzbw(str));
                }
                return false;
            }
        } else if (TextUtils.isEmpty(str2)) {
            if (this.zzada.zzkn()) {
                zzgt().zzjg().zzby("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        } else if (!zzcw(str2)) {
            zzgt().zzjg().zzg("Invalid admob_app_id. Analytics disabled.", zzas.zzbw(str2));
            return false;
        }
        return true;
    }

    static boolean zza(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            return !str.equals(str2);
        }
        if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        }
        if (isEmpty || !isEmpty2) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
        if (TextUtils.isEmpty(str4)) {
            return false;
        }
        return TextUtils.isEmpty(str3) || !str3.equals(str4);
    }

    @VisibleForTesting
    private static boolean zzcw(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String zza(String str, int i, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final Object zzh(String str, Object obj) {
        int i = 256;
        if ("_ev".equals(str)) {
            return zza(256, obj, true);
        }
        if (!zzcy(str)) {
            i = 100;
        }
        return zza(i, obj, false);
    }

    static Bundle[] zzf(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        } else if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            return (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zza(java.lang.String r18, java.lang.String r19, android.os.Bundle r20, @android.support.annotation.Nullable java.util.List<java.lang.String> r21, boolean r22, boolean r23) {
        /*
            r17 = this;
            r6 = r17
            r7 = r20
            r8 = r21
            r9 = 0
            if (r7 == 0) goto L_0x0183
            android.os.Bundle r10 = new android.os.Bundle
            r10.<init>(r7)
            java.util.Set r0 = r20.keySet()
            java.util.Iterator r11 = r0.iterator()
            r12 = 0
            r13 = 0
        L_0x0018:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x0184
            java.lang.Object r0 = r11.next()
            r14 = r0
            java.lang.String r14 = (java.lang.String) r14
            r15 = 40
            r0 = 3
            if (r8 == 0) goto L_0x0033
            boolean r1 = r8.contains(r14)
            if (r1 != 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r1 = 0
            goto L_0x007b
        L_0x0033:
            r1 = 14
            if (r22 == 0) goto L_0x0058
            java.lang.String r2 = "event param"
            boolean r2 = r6.zzs(r2, r14)
            if (r2 != 0) goto L_0x0041
            r2 = 3
            goto L_0x0059
        L_0x0041:
            java.lang.String r2 = "event param"
            boolean r2 = r6.zza(r2, r9, r14)
            if (r2 != 0) goto L_0x004c
            r2 = 14
            goto L_0x0059
        L_0x004c:
            java.lang.String r2 = "event param"
            boolean r2 = r6.zza(r2, r15, r14)
            if (r2 != 0) goto L_0x0056
            r2 = 3
            goto L_0x0059
        L_0x0056:
            r2 = 0
            goto L_0x0059
        L_0x0058:
            r2 = 0
        L_0x0059:
            if (r2 != 0) goto L_0x007a
            java.lang.String r2 = "event param"
            boolean r2 = r6.zzt(r2, r14)
            if (r2 != 0) goto L_0x0065
            r1 = 3
            goto L_0x007b
        L_0x0065:
            java.lang.String r2 = "event param"
            boolean r2 = r6.zza(r2, r9, r14)
            if (r2 != 0) goto L_0x006e
            goto L_0x007b
        L_0x006e:
            java.lang.String r1 = "event param"
            boolean r1 = r6.zza(r1, r15, r14)
            if (r1 != 0) goto L_0x0078
            r1 = 3
            goto L_0x007b
        L_0x0078:
            r1 = 0
            goto L_0x007b
        L_0x007a:
            r1 = r2
        L_0x007b:
            r5 = 1
            if (r1 == 0) goto L_0x0097
            boolean r2 = zza(r10, r1)
            if (r2 == 0) goto L_0x0092
            java.lang.String r2 = zza(r14, r15, r5)
            java.lang.String r3 = "_ev"
            r10.putString(r3, r2)
            if (r1 != r0) goto L_0x0092
            zza(r10, r14)
        L_0x0092:
            r10.remove(r14)
            goto L_0x0135
        L_0x0097:
            java.lang.Object r4 = r7.get(r14)
            r17.zzaf()
            if (r23 == 0) goto L_0x00d6
            java.lang.String r0 = "param"
            boolean r1 = r4 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x00ab
            r1 = r4
            android.os.Parcelable[] r1 = (android.os.Parcelable[]) r1
            int r1 = r1.length
            goto L_0x00b6
        L_0x00ab:
            boolean r1 = r4 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x00cf
            r1 = r4
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r1 = r1.size()
        L_0x00b6:
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 <= r2) goto L_0x00cd
            com.google.android.gms.measurement.internal.zzas r2 = r17.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjj()
            java.lang.String r3 = "Parameter array is too long; discarded. Value kind, name, array length"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.zzd(r3, r0, r14, r1)
            r0 = 0
            goto L_0x00d0
        L_0x00cd:
            r0 = 1
            goto L_0x00d0
        L_0x00cf:
            r0 = 1
        L_0x00d0:
            if (r0 != 0) goto L_0x00d6
            r0 = 17
            r9 = 1
            goto L_0x0112
        L_0x00d6:
            com.google.android.gms.measurement.internal.zzq r0 = r17.zzgv()
            r3 = r18
            boolean r0 = r0.zzav(r3)
            if (r0 == 0) goto L_0x00e8
            boolean r0 = zzcy(r19)
            if (r0 != 0) goto L_0x00ee
        L_0x00e8:
            boolean r0 = zzcy(r14)
            if (r0 == 0) goto L_0x00ff
        L_0x00ee:
            java.lang.String r1 = "param"
            r16 = 256(0x100, float:3.59E-43)
            r0 = r17
            r2 = r14
            r3 = r16
            r9 = 1
            r5 = r23
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
            goto L_0x010d
        L_0x00ff:
            r9 = 1
            java.lang.String r1 = "param"
            r3 = 100
            r0 = r17
            r2 = r14
            r5 = r23
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
        L_0x010d:
            if (r0 == 0) goto L_0x0111
            r0 = 0
            goto L_0x0112
        L_0x0111:
            r0 = 4
        L_0x0112:
            if (r0 == 0) goto L_0x0138
            java.lang.String r1 = "_ev"
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x0138
            boolean r0 = zza(r10, r0)
            if (r0 == 0) goto L_0x0132
            java.lang.String r0 = zza(r14, r15, r9)
            java.lang.String r1 = "_ev"
            r10.putString(r1, r0)
            java.lang.Object r0 = r7.get(r14)
            zza(r10, r0)
        L_0x0132:
            r10.remove(r14)
        L_0x0135:
            r9 = 0
            goto L_0x0018
        L_0x0138:
            boolean r0 = zzct(r14)
            if (r0 == 0) goto L_0x017e
            int r13 = r13 + 1
            r0 = 25
            if (r13 <= r0) goto L_0x017b
            r0 = 48
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Event can't contain more than 25 params"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.google.android.gms.measurement.internal.zzas r1 = r17.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()
            com.google.android.gms.measurement.internal.zzaq r2 = r17.zzgq()
            r3 = r19
            java.lang.String r2 = r2.zzbt(r3)
            com.google.android.gms.measurement.internal.zzaq r4 = r17.zzgq()
            java.lang.String r4 = r4.zzd(r7)
            r1.zze(r0, r2, r4)
            r0 = 5
            zza(r10, r0)
            r10.remove(r14)
            r9 = 0
            goto L_0x0018
        L_0x017b:
            r3 = r19
            goto L_0x0180
        L_0x017e:
            r3 = r19
        L_0x0180:
            r9 = 0
            goto L_0x0018
        L_0x0183:
            r10 = 0
        L_0x0184:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfx.zza(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean, boolean):android.os.Bundle");
    }

    private static boolean zza(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    private static void zza(Bundle bundle, Object obj) {
        Preconditions.checkNotNull(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    private static int zzcx(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        return "_id".equals(str) ? 256 : 36;
    }

    /* access modifiers changed from: 0000 */
    public final int zzi(String str, Object obj) {
        boolean z;
        if ("_ldl".equals(str)) {
            z = zza("user property referrer", str, zzcx(str), obj, false);
        } else {
            z = zza("user property", str, zzcx(str), obj, false);
        }
        return z ? 0 : 7;
    }

    /* access modifiers changed from: 0000 */
    public final Object zzj(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zza(zzcx(str), obj, true);
        }
        return zza(zzcx(str), obj, false);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else {
                if (str != null) {
                    zzgt().zzjl().zze("Not putting event parameter. Invalid value type. name, type", zzgq().zzbu(str), obj != null ? obj.getClass().getSimpleName() : null);
                }
            }
        }
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zza(bundle, i);
        if (zzgv().zze(str, zzai.zzali)) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                bundle.putString(str2, str3);
            }
        } else if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzada.zzgw();
        this.zzada.zzgj().logEvent("auto", "_err", bundle);
    }

    static MessageDigest getMessageDigest() {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    @VisibleForTesting
    static long zzc(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i = 0;
        Preconditions.checkState(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    static boolean zza(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        if (VERSION.SDK_INT >= 24) {
            return zzc(context, "com.google.android.gms.measurement.AppMeasurementJobService");
        }
        return zzc(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    private static boolean zzc(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
            return false;
        } catch (NameNotFoundException unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzx(String str) {
        zzaf();
        if (Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzgt().zzjn().zzg("Permission not granted", str);
        return false;
    }

    static boolean zzcy(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzv(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzcz(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzid = zzgv().zzid();
        zzgw();
        return zzid.equals(str);
    }

    /* access modifiers changed from: 0000 */
    public final Bundle zze(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzh = zzh(str, bundle.get(str));
                if (zzh == null) {
                    zzgt().zzjj().zzg("Param value can't be null", zzgq().zzbu(str));
                } else {
                    zza(bundle2, str, zzh);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: 0000 */
    public final zzag zza(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzcu(str2) == 0) {
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            bundle2.putString("_o", str3);
            zzag zzag = new zzag(str2, new zzad(zze(zza(str, str2, bundle2, CollectionUtils.listOf("_o"), false, false))), str3, j);
            return zzag;
        }
        zzgt().zzjg().zzg("Invalid conditional property event name", zzgq().zzbv(str2));
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final long zzd(Context context, String str) {
        zzaf();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest messageDigest = getMessageDigest();
        if (messageDigest == null) {
            zzgt().zzjg().zzby("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (zze(context, str)) {
                    return 0;
                }
                PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(getContext().getPackageName(), 64);
                if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                    return zzc(messageDigest.digest(packageInfo.signatures[0].toByteArray()));
                }
                zzgt().zzjj().zzby("Could not get signatures");
                return -1;
            } catch (NameNotFoundException e) {
                zzgt().zzjg().zzg("Package name not found", e);
            }
        }
        return 0;
    }

    @VisibleForTesting
    private final boolean zze(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzgt().zzjg().zzg("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzgt().zzjg().zzg("Package name not found", e2);
        }
        return true;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static Bundle zzf(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        if (parcelableArr[i] instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        return bundle2;
    }

    public final int zzml() {
        if (this.zzaut == null) {
            this.zzaut = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(getContext()) / 1000);
        }
        return this.zzaut.intValue();
    }

    public final int zzs(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(getContext(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public static long zzc(long j, long j2) {
        return (j + (j2 * DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS)) / 86400000;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final String zzmm() {
        byte[] bArr = new byte[16];
        zzmk().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zza(Bundle bundle, long j) {
        long j2 = bundle.getLong("_et");
        if (j2 != 0) {
            zzgt().zzjj().zzg("Params already contained engagement", Long.valueOf(j2));
        }
        bundle.putLong("_et", j + j2);
    }

    public final void zzb(zzdq zzdq, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzdq.zzb(bundle);
        } catch (RemoteException e) {
            this.zzada.zzgt().zzjj().zzg("Error returning string value to wrapper", e);
        }
    }

    public final void zza(zzdq zzdq, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j);
        try {
            zzdq.zzb(bundle);
        } catch (RemoteException e) {
            this.zzada.zzgt().zzjj().zzg("Error returning long value to wrapper", e);
        }
    }

    public final void zza(zzdq zzdq, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i);
        try {
            zzdq.zzb(bundle);
        } catch (RemoteException e) {
            this.zzada.zzgt().zzjj().zzg("Error returning int value to wrapper", e);
        }
    }

    public final void zza(zzdq zzdq, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzdq.zzb(bundle);
        } catch (RemoteException e) {
            this.zzada.zzgt().zzjj().zzg("Error returning byte array to wrapper", e);
        }
    }

    public final void zza(zzdq zzdq, Bundle bundle) {
        try {
            zzdq.zzb(bundle);
        } catch (RemoteException e) {
            this.zzada.zzgt().zzjj().zzg("Error returning bundle value to wrapper", e);
        }
    }

    public static Bundle zzd(List<zzfu> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzfu zzfu : list) {
            if (zzfu.zzamn != null) {
                bundle.putString(zzfu.name, zzfu.zzamn);
            } else if (zzfu.zzaun != null) {
                bundle.putLong(zzfu.name, zzfu.zzaun.longValue());
            } else if (zzfu.zzaup != null) {
                bundle.putDouble(zzfu.name, zzfu.zzaup.doubleValue());
            }
        }
        return bundle;
    }

    public final /* bridge */ /* synthetic */ void zzgf() {
        super.zzgf();
    }

    public final /* bridge */ /* synthetic */ void zzgg() {
        super.zzgg();
    }

    public final /* bridge */ /* synthetic */ void zzgh() {
        super.zzgh();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zzaa zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzaq zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzfx zzgr() {
        return super.zzgr();
    }

    public final /* bridge */ /* synthetic */ zzbr zzgs() {
        return super.zzgs();
    }

    public final /* bridge */ /* synthetic */ zzas zzgt() {
        return super.zzgt();
    }

    public final /* bridge */ /* synthetic */ zzbd zzgu() {
        return super.zzgu();
    }

    public final /* bridge */ /* synthetic */ zzq zzgv() {
        return super.zzgv();
    }

    public final /* bridge */ /* synthetic */ zzn zzgw() {
        return super.zzgw();
    }
}
