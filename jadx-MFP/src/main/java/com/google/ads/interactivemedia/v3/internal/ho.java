package com.google.ads.interactivemedia.v3.internal;

import android.media.MediaFormat;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import com.google.android.exoplayer2.C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: IMASDK */
public class ho {
    public static float a(int i, int i2) {
        if (i2 <= 0 || i <= 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f = ((float) i) / ((float) i2);
        if (f > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    public static byte[] a(UUID uuid, byte[] bArr) {
        return a(uuid, (UUID[]) null, bArr);
    }

    public static String c() {
        return "Android";
    }

    public static void e() {
    }

    public static byte[] a(UUID uuid, UUID[] uuidArr, byte[] bArr) {
        int length = (bArr != null ? bArr.length : 0) + 32;
        if (uuidArr != null) {
            length += (uuidArr.length << 4) + 4;
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        allocate.putInt(gu.af);
        allocate.putInt(uuidArr != null ? C.DEFAULT_MUXED_BUFFER_SIZE : 0);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        if (uuidArr != null) {
            allocate.putInt(uuidArr.length);
            for (UUID uuid2 : uuidArr) {
                allocate.putLong(uuid2.getMostSignificantBits());
                allocate.putLong(uuid2.getLeastSignificantBits());
            }
        }
        if (!(bArr == null || bArr.length == 0)) {
            allocate.putInt(bArr.length);
            allocate.put(bArr);
        }
        return allocate.array();
    }

    public static UUID a(byte[] bArr) {
        hp hpVar;
        ut utVar = new ut(bArr);
        if (utVar.c() < 32) {
            hpVar = null;
        } else {
            utVar.c(0);
            if (utVar.l() != utVar.b() + 4) {
                hpVar = null;
            } else if (utVar.l() != gu.af) {
                hpVar = null;
            } else {
                int a = gu.a(utVar.l());
                if (a > 1) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unsupported pssh version: ");
                    sb.append(a);
                    Log.w("PsshAtomUtil", sb.toString());
                    hpVar = null;
                } else {
                    UUID uuid = new UUID(utVar.m(), utVar.m());
                    if (a == 1) {
                        utVar.d(utVar.p() * 16);
                    }
                    int p = utVar.p();
                    if (p != utVar.b()) {
                        hpVar = null;
                    } else {
                        byte[] bArr2 = new byte[p];
                        utVar.a(bArr2, 0, p);
                        hpVar = new hp(uuid, a, bArr2);
                    }
                }
            }
        }
        if (hpVar == null) {
            return null;
        }
        return hpVar.a;
    }

    public static String a() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("; ");
        sb.append(str2);
        return sb.toString();
    }

    public static String b() {
        return Integer.toString(VERSION.SDK_INT);
    }

    public static JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        z.a(jSONObject, "deviceType", a());
        z.a(jSONObject, "osVersion", b());
        z.a(jSONObject, Http.OS, c());
        return jSONObject;
    }

    public static void a(String str, Exception exc) {
        Log.e("OMIDLIB", str, exc);
    }

    public static double f() {
        return (double) TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    public static void g() {
        if (!a.b()) {
            throw new IllegalStateException("Method called before OM SDK activation");
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void a(String str, int i, String str2) {
        if (str.length() > 256) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void a(d dVar) {
        if (dVar.equals(d.NONE)) {
            throw new IllegalArgumentException("Impression owner is none");
        }
    }

    public static float a(View view) {
        return VERSION.SDK_INT >= 21 ? view.getZ() : BitmapDescriptorFactory.HUE_RED;
    }

    public static View b(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }

    public static boolean c(View view) {
        if ((VERSION.SDK_INT >= 19 && !view.isAttachedToWindow()) || !view.isShown()) {
            return false;
        }
        while (view != null) {
            if (view.getAlpha() == BitmapDescriptorFactory.HUE_RED) {
                return false;
            }
            view = b(view);
        }
        return true;
    }

    public static boolean d(View view) {
        if ((VERSION.SDK_INT < 19 || view.isAttachedToWindow()) && view.getVisibility() == 0 && view.getAlpha() != BitmapDescriptorFactory.HUE_RED) {
            return true;
        }
        return false;
    }

    public static int a(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] != 71) {
            i++;
        }
        return i;
    }

    public static long a(ut utVar, int i, int i2) {
        utVar.c(i);
        if (utVar.b() < 5) {
            return -9223372036854775807L;
        }
        int l = utVar.l();
        if ((8388608 & l) != 0 || ((2096896 & l) >> 8) != i2) {
            return -9223372036854775807L;
        }
        boolean z = true;
        if (((l & 32) != 0) && utVar.e() >= 7 && utVar.b() >= 7) {
            if ((utVar.e() & 16) != 16) {
                z = false;
            }
            if (z) {
                byte[] bArr = new byte[6];
                utVar.a(bArr, 0, 6);
                return b(bArr);
            }
        }
        return -9223372036854775807L;
    }

    private static long b(byte[] bArr) {
        return ((((long) bArr[0]) & 255) << 25) | ((((long) bArr[1]) & 255) << 17) | ((((long) bArr[2]) & 255) << 9) | ((((long) bArr[3]) & 255) << 1) | ((255 & ((long) bArr[4])) >> 7);
    }

    public static void a(MediaFormat mediaFormat, List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder(15);
            sb.append("csd-");
            sb.append(i);
            mediaFormat.setByteBuffer(sb.toString(), ByteBuffer.wrap((byte[]) list.get(i)));
        }
    }

    public static void a(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    public static void a(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    public static void a(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public static void a(MediaFormat mediaFormat, vi viVar) {
        if (viVar != null) {
            a(mediaFormat, "color-transfer", viVar.c);
            a(mediaFormat, "color-standard", viVar.a);
            a(mediaFormat, "color-range", viVar.b);
            a(mediaFormat, "hdr-static-info", viVar.d);
        }
    }
}
