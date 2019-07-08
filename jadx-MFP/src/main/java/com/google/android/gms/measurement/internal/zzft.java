package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzya;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzft extends zzfm {
    zzft(zzfn zzfn) {
        super(zzfn);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfz zzfz, Object obj) {
        Preconditions.checkNotNull(obj);
        zzfz.zzamn = null;
        zzfz.zzaxg = null;
        zzfz.zzaup = null;
        if (obj instanceof String) {
            zzfz.zzamn = (String) obj;
        } else if (obj instanceof Long) {
            zzfz.zzaxg = (Long) obj;
        } else if (obj instanceof Double) {
            zzfz.zzaup = (Double) obj;
        } else {
            zzgt().zzjg().zzg("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfu zzfu, Object obj) {
        Preconditions.checkNotNull(obj);
        zzfu.zzamn = null;
        zzfu.zzaxg = null;
        zzfu.zzaup = null;
        if (obj instanceof String) {
            zzfu.zzamn = (String) obj;
        } else if (obj instanceof Long) {
            zzfu.zzaxg = (Long) obj;
        } else if (obj instanceof Double) {
            zzfu.zzaup = (Double) obj;
        } else {
            zzgt().zzjg().zzg("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zza(zzfv zzfv) {
        try {
            byte[] bArr = new byte[zzfv.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzfv.zza(zzk);
            zzk.zzza();
            return bArr;
        } catch (IOException e) {
            zzgt().zzjg().zzg("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    static zzfu zza(com.google.android.gms.internal.measurement.zzft zzft, String str) {
        zzfu[] zzfuArr;
        for (zzfu zzfu : zzft.zzaxc) {
            if (zzfu.name.equals(str)) {
                return zzfu;
            }
        }
        return null;
    }

    static Object zzb(com.google.android.gms.internal.measurement.zzft zzft, String str) {
        zzfu zza = zza(zzft, str);
        if (zza != null) {
            if (zza.zzamn != null) {
                return zza.zzamn;
            }
            if (zza.zzaxg != null) {
                return zza.zzaxg;
            }
            if (zza.zzaup != null) {
                return zza.zzaup;
            }
        }
        return null;
    }

    static zzfu[] zza(zzfu[] zzfuArr, String str, Object obj) {
        for (zzfu zzfu : zzfuArr) {
            if (str.equals(zzfu.name)) {
                zzfu.zzaxg = null;
                zzfu.zzamn = null;
                zzfu.zzaup = null;
                if (obj instanceof Long) {
                    zzfu.zzaxg = (Long) obj;
                } else if (obj instanceof String) {
                    zzfu.zzamn = (String) obj;
                } else if (obj instanceof Double) {
                    zzfu.zzaup = (Double) obj;
                }
                return zzfuArr;
            }
        }
        zzfu[] zzfuArr2 = new zzfu[(zzfuArr.length + 1)];
        System.arraycopy(zzfuArr, 0, zzfuArr2, 0, zzfuArr.length);
        zzfu zzfu2 = new zzfu();
        zzfu2.name = str;
        if (obj instanceof Long) {
            zzfu2.zzaxg = (Long) obj;
        } else if (obj instanceof String) {
            zzfu2.zzamn = (String) obj;
        } else if (obj instanceof Double) {
            zzfu2.zzaup = (Double) obj;
        }
        zzfuArr2[zzfuArr.length] = zzfu2;
        return zzfuArr2;
    }

    /* access modifiers changed from: 0000 */
    public final String zzb(zzfv zzfv) {
        zzfw[] zzfwArr;
        int i;
        int i2;
        int i3;
        zzfv zzfv2 = zzfv;
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzfv2.zzaxh != null) {
            for (zzfw zzfw : zzfv2.zzaxh) {
                if (!(zzfw == null || zzfw == null)) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    zza(sb, 1, "protocol_version", (Object) zzfw.zzaxj);
                    zza(sb, 1, Http.PLATFORM, (Object) zzfw.zzaxr);
                    zza(sb, 1, "gmp_version", (Object) zzfw.zzaxv);
                    zza(sb, 1, "uploading_gmp_version", (Object) zzfw.zzaxw);
                    zza(sb, 1, "config_version", (Object) zzfw.zzayh);
                    zza(sb, 1, "gmp_app_id", (Object) zzfw.zzafi);
                    zza(sb, 1, "admob_app_id", (Object) zzfw.zzawr);
                    zza(sb, 1, "app_id", (Object) zzfw.zztt);
                    zza(sb, 1, "app_version", (Object) zzfw.zzts);
                    zza(sb, 1, "app_version_major", (Object) zzfw.zzayd);
                    zza(sb, 1, "firebase_instance_id", (Object) zzfw.zzafk);
                    zza(sb, 1, "dev_cert_hash", (Object) zzfw.zzaxz);
                    zza(sb, 1, "app_store", (Object) zzfw.zzafp);
                    zza(sb, 1, "upload_timestamp_millis", (Object) zzfw.zzaxm);
                    zza(sb, 1, "start_timestamp_millis", (Object) zzfw.zzaxn);
                    zza(sb, 1, "end_timestamp_millis", (Object) zzfw.zzaxo);
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) zzfw.zzaxp);
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) zzfw.zzaxq);
                    zza(sb, 1, "app_instance_id", (Object) zzfw.zzafh);
                    zza(sb, 1, "resettable_device_id", (Object) zzfw.zzaxx);
                    zza(sb, 1, "device_id", (Object) zzfw.zzayg);
                    zza(sb, 1, "ds_id", (Object) zzfw.zzayj);
                    zza(sb, 1, "limited_ad_tracking", (Object) zzfw.zzaxy);
                    zza(sb, 1, Attributes.OS_VERSION, (Object) zzfw.zzaxs);
                    zza(sb, 1, "device_model", (Object) zzfw.zzaxt);
                    zza(sb, 1, "user_default_language", (Object) zzfw.zzahr);
                    zza(sb, 1, "time_zone_offset_minutes", (Object) zzfw.zzaxu);
                    zza(sb, 1, "bundle_sequential_index", (Object) zzfw.zzaya);
                    zza(sb, 1, "service_upload", (Object) zzfw.zzayb);
                    zza(sb, 1, "health_monitor", (Object) zzfw.zzagm);
                    if (!(zzfw.zzayi == null || zzfw.zzayi.longValue() == 0)) {
                        zza(sb, 1, "android_id", (Object) zzfw.zzayi);
                    }
                    if (zzfw.zzayl != null) {
                        zza(sb, 1, "retry_counter", (Object) zzfw.zzayl);
                    }
                    zzfz[] zzfzArr = zzfw.zzaxl;
                    if (zzfzArr != null) {
                        for (zzfz zzfz : zzfzArr) {
                            if (zzfz != null) {
                                zza(sb, 2);
                                sb.append("user_property {\n");
                                zza(sb, 2, "set_timestamp_millis", (Object) zzfz.zzayw);
                                zza(sb, 2, "name", (Object) zzgq().zzbv(zzfz.name));
                                zza(sb, 2, "string_value", (Object) zzfz.zzamn);
                                zza(sb, 2, "int_value", (Object) zzfz.zzaxg);
                                zza(sb, 2, "double_value", (Object) zzfz.zzaup);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzfr[] zzfrArr = zzfw.zzayc;
                    String str = zzfw.zztt;
                    if (zzfrArr != null) {
                        int length = zzfrArr.length;
                        int i4 = 0;
                        while (i4 < length) {
                            zzfr zzfr = zzfrArr[i4];
                            if (zzfr != null) {
                                zza(sb, 2);
                                sb.append("audience_membership {\n");
                                zza(sb, 2, "audience_id", (Object) zzfr.zzavg);
                                zza(sb, 2, "new_audience", (Object) zzfr.zzawx);
                                StringBuilder sb2 = sb;
                                zzfr zzfr2 = zzfr;
                                i3 = i4;
                                i2 = length;
                                String str2 = str;
                                zza(sb2, 2, "current_data", zzfr.zzawv, str2);
                                zza(sb2, 2, "previous_data", zzfr2.zzaww, str2);
                                zza(sb, 2);
                                sb.append("}\n");
                            } else {
                                i3 = i4;
                                i2 = length;
                            }
                            i4 = i3 + 1;
                            length = i2;
                        }
                    }
                    com.google.android.gms.internal.measurement.zzft[] zzftArr = zzfw.zzaxk;
                    if (zzftArr != null) {
                        for (com.google.android.gms.internal.measurement.zzft zzft : zzftArr) {
                            if (zzft != null) {
                                zza(sb, 2);
                                sb.append("event {\n");
                                zza(sb, 2, "name", (Object) zzgq().zzbt(zzft.name));
                                zza(sb, 2, "timestamp_millis", (Object) zzft.zzaxd);
                                zza(sb, 2, "previous_timestamp_millis", (Object) zzft.zzaxe);
                                zza(sb, 2, "count", (Object) zzft.count);
                                zzfu[] zzfuArr = zzft.zzaxc;
                                if (zzfuArr != null) {
                                    for (zzfu zzfu : zzfuArr) {
                                        if (zzfu != null) {
                                            zza(sb, 3);
                                            sb.append("param {\n");
                                            zza(sb, 3, "name", (Object) zzgq().zzbu(zzfu.name));
                                            zza(sb, 3, "string_value", (Object) zzfu.zzamn);
                                            zza(sb, 3, "int_value", (Object) zzfu.zzaxg);
                                            zza(sb, 3, "double_value", (Object) zzfu.zzaup);
                                            zza(sb, 3);
                                            sb.append("}\n");
                                        }
                                    }
                                }
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                        i = 1;
                    } else {
                        i = 1;
                    }
                    zza(sb, i);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzfj zzfj) {
        if (zzfj == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzfj.zzavm);
        zza(sb, 0, "event_name", (Object) zzgq().zzbt(zzfj.zzavn));
        zza(sb, 1, "event_count_filter", zzfj.zzavq);
        sb.append("  filters {\n");
        for (zzfk zza : zzfj.zzavo) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzfm zzfm) {
        if (zzfm == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzfm.zzavm);
        zza(sb, 0, "property_name", (Object) zzgq().zzbv(zzfm.zzawc));
        zza(sb, 1, zzfm.zzawd);
        sb.append("}\n");
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i, String str, zzfx zzfx, String str2) {
        if (zzfx != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzfx.zzayq != null) {
                zza(sb, 4);
                sb.append("results: ");
                long[] jArr = zzfx.zzayq;
                int length = jArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    Long valueOf = Long.valueOf(jArr[i2]);
                    int i4 = i3 + 1;
                    if (i3 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i2++;
                    i3 = i4;
                }
                sb.append(10);
            }
            if (zzfx.zzayp != null) {
                zza(sb, 4);
                sb.append("status: ");
                long[] jArr2 = zzfx.zzayp;
                int length2 = jArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i5]);
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i5++;
                    i6 = i7;
                }
                sb.append(10);
            }
            if (zzgv().zzbb(str2)) {
                if (zzfx.zzayr != null) {
                    zza(sb, 4);
                    sb.append("dynamic_filter_timestamps: {");
                    zzfs[] zzfsArr = zzfx.zzayr;
                    int length3 = zzfsArr.length;
                    int i8 = 0;
                    int i9 = 0;
                    while (i8 < length3) {
                        zzfs zzfs = zzfsArr[i8];
                        int i10 = i9 + 1;
                        if (i9 != 0) {
                            sb.append(", ");
                        }
                        sb.append(zzfs.zzawz);
                        sb.append(":");
                        sb.append(zzfs.zzaxa);
                        i8++;
                        i9 = i10;
                    }
                    sb.append("}\n");
                }
                if (zzfx.zzays != null) {
                    zza(sb, 4);
                    sb.append("sequence_filter_timestamps: {");
                    zzfy[] zzfyArr = zzfx.zzays;
                    int length4 = zzfyArr.length;
                    int i11 = 0;
                    int i12 = 0;
                    while (i11 < length4) {
                        zzfy zzfy = zzfyArr[i11];
                        int i13 = i12 + 1;
                        if (i12 != 0) {
                            sb.append(", ");
                        }
                        sb.append(zzfy.zzawz);
                        sb.append(": [");
                        long[] jArr3 = zzfy.zzayu;
                        int length5 = jArr3.length;
                        int i14 = 0;
                        int i15 = 0;
                        while (i14 < length5) {
                            long j = jArr3[i14];
                            int i16 = i15 + 1;
                            if (i15 != 0) {
                                sb.append(", ");
                            }
                            sb.append(j);
                            i14++;
                            i15 = i16;
                        }
                        sb.append("]");
                        i11++;
                        i12 = i13;
                    }
                    sb.append("}\n");
                }
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzfl zzfl) {
        if (zzfl != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzfl.zzavw != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzfl.zzavw.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", (Object) str2);
            }
            zza(sb, i, "match_as_float", (Object) zzfl.zzavx);
            zza(sb, i, "comparison_value", (Object) zzfl.zzavy);
            zza(sb, i, "min_comparison_value", (Object) zzfl.zzavz);
            zza(sb, i, "max_comparison_value", (Object) zzfl.zzawa);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, zzfk zzfk) {
        String[] strArr;
        if (zzfk != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzfk.zzavu);
            zza(sb, i, "param_name", (Object) zzgq().zzbu(zzfk.zzavv));
            int i2 = i + 1;
            String str = "string_filter";
            zzfn zzfn = zzfk.zzavs;
            if (zzfn != null) {
                zza(sb, i2);
                sb.append(str);
                sb.append(" {\n");
                if (zzfn.zzawe != null) {
                    String str2 = "UNKNOWN_MATCH_TYPE";
                    switch (zzfn.zzawe.intValue()) {
                        case 1:
                            str2 = "REGEXP";
                            break;
                        case 2:
                            str2 = "BEGINS_WITH";
                            break;
                        case 3:
                            str2 = "ENDS_WITH";
                            break;
                        case 4:
                            str2 = "PARTIAL";
                            break;
                        case 5:
                            str2 = "EXACT";
                            break;
                        case 6:
                            str2 = "IN_LIST";
                            break;
                    }
                    zza(sb, i2, "match_type", (Object) str2);
                }
                zza(sb, i2, "expression", (Object) zzfn.zzawf);
                zza(sb, i2, "case_sensitive", (Object) zzfn.zzawg);
                if (zzfn.zzawh.length > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str3 : zzfn.zzawh) {
                        zza(sb, i2 + 2);
                        sb.append(str3);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzfk.zzavt);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzgt().zzjg().zzby("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.measurement.internal.zzas r5 = r4.zzgt()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjg()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.zzby(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzft.zza(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zze(zzag zzag, zzk zzk) {
        Preconditions.checkNotNull(zzag);
        Preconditions.checkNotNull(zzk);
        if (!TextUtils.isEmpty(zzk.zzafi) || !TextUtils.isEmpty(zzk.zzafv)) {
            return true;
        }
        zzgw();
        return false;
    }

    static boolean zzcs(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zza(long[] jArr, int i) {
        if (i >= (jArr.length << 6)) {
            return false;
        }
        if (((1 << (i % 64)) & jArr[i / 64]) != 0) {
            return true;
        }
        return false;
    }

    static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzb(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzbx().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zza(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzgt().zzjg().zzg("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzgt().zzjg().zzg("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final int[] zzmi() {
        Map zzm = zzai.zzm(this.zzamx.getContext());
        if (zzm == null || zzm.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = ((Integer) zzai.zzakg.get()).intValue();
        Iterator it = zzm.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (((String) entry.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) entry.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzgt().zzjj().zzg("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzgt().zzjj().zzg("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            int i3 = i2 + 1;
            iArr[i2] = ((Integer) obj).intValue();
            i2 = i3;
        }
        return iArr;
    }

    public final /* bridge */ /* synthetic */ zzft zzjr() {
        return super.zzjr();
    }

    public final /* bridge */ /* synthetic */ zzm zzjs() {
        return super.zzjs();
    }

    public final /* bridge */ /* synthetic */ zzt zzjt() {
        return super.zzjt();
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
