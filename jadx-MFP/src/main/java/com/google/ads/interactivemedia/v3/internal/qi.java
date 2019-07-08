package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: IMASDK */
public class qi implements qu {
    public static String A() {
        return "MR/p4pF6zrM2yOWsh7Kq5dbwUT5Plh7L/wEZR20DFJc=";
    }

    public static String B() {
        return "R75TFH8UxzX6KzwmMOIAiEOgZ3w/LZFVVBMgCkszL4MS7jU6KCq3bFshhYLpG/lm";
    }

    public static String C() {
        return "SOQAY/Yc8NWIdkLAhBUoY0hR/vEv4aG+caNKA29L5T8=";
    }

    public static String D() {
        return "52UTclpblbgPZlv7Nj6v0rtDMPh43h349e01ntb69/chxdGasn3EykJHT0GFkNS+";
    }

    public static String E() {
        return "H2mF8ih5ZQ8pxTr+44xGtS8h3iHalDiydTmNQvvB9PE=";
    }

    public static String F() {
        return "lO2J0nQ8iUbwpSP7x7JVTRl9z6TOAokJraLnSEcbLEoX9aCYnEQiu/7d/0ADK+Kt";
    }

    public static String G() {
        return "yTAAp+clNbNF5Td/QjC7p5K6b33nl9lorC0oZXbsQ+A=";
    }

    public static String f() {
        return "dmFUD4yixjCLeIFssMn8+bpXZchyM7ZZjdHVe+zeIw0=";
    }

    public static String g() {
        return "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";
    }

    public static String h() {
        return "eHOtFwMll5L1Neq7FYSb/mAfmCK3mjwD+kcR5jCosVyfkrvUDmIwRjmV8+q2NHYn";
    }

    public static String i() {
        return "wzEQNL6fxZpDqLLHWbHIQaIaTEFKm5CSEQgkOmhzams=";
    }

    public static String j() {
        return "6LQAb447ICiHxbmOtmji3D4hpfwXiEjse1ir9OC50xqyamJ/KbX1/nX8AsL+bHcb";
    }

    public static String k() {
        return "1BCDmMTvausZ3wHdSO9fsXVYA9qCh8/EnuL2Cru5aZ8=";
    }

    public static String l() {
        return "/RcUmTOFoVKQmYTx4SXUI3MZ+V8YeEVVMyskmyn2LKriaejeXpUmZci+LjJRw7Qi";
    }

    public static String m() {
        return "b42TKHdy7hWPdEEpyazy/kll9Hb0px+6KEorIMPWagk=";
    }

    public static String n() {
        return "6Op3Osq/ycP+Tw/h0nZIOiaxu9PqhRTncsN3gXFfkXzFNVbOmFRaKWvs9L9+aZBY";
    }

    public static String o() {
        return "q9p7XgEInfiuZrB/emAOSeQGTPIFmNmpVflkcIcq4bg=";
    }

    public static String p() {
        return "13+D8YKo7JA4YclLYFLTkDoLYm79WC6PTAAgd/iaV5l4mfBjRCrtCqwg533KI4Aw";
    }

    public static String q() {
        return "GTcx0b8AlfY1ZowRGVcxzzHzJO55sJlZwAMsaBOasD4=";
    }

    public static String r() {
        return "3STGKgBH0KW29J9eL7SkydCEoMJ5wVqqxnqpt7l1d/UdAE49dT9zzc+xme8GwL0S";
    }

    public static String s() {
        return "qb4zfYvhFYr4xgO9UX/SM6bCi7jkPggWD8USiOhVgzo=";
    }

    public static String t() {
        return "BVu9+hfoVnCGMY+PLQku1FUwfoIHRy389bNZXV62hXZUkceukjyax4oNU/CRj5DY";
    }

    public static String u() {
        return "dx4bb6UNwy6nC3HkcVDqL3Rsfu1d3Z1Pe6GiKK6hZrw=";
    }

    public static String v() {
        return "OJV6xVIR1eh7tYy9riemmYtkPaU/NBCWvnOl95ehf5cPSRNoet0V98Y76fm3gKIx";
    }

    public static String w() {
        return "foNiPa3eaLdvcAZc5cQJrbbRL+KJK+PxPLQjHS2rrNE=";
    }

    public static String x() {
        return "PLcKoSXa/7Erhgptf0Tz500+Zs0BLV22dGPCJPuPX1GNC9pyFVkRFks19kPnvjsE";
    }

    public static String y() {
        return "6ZHRPIXbN2vTr12hv8v+TAgfU76HlS1m5AiZXFvaSto=";
    }

    public static String z() {
        return "Cd51XF2SemCUJwXZiKeIYddl4xLKDa/a258CP7t9qBxOqbBbeoj3yUZZjpA6+SrQ";
    }

    public final tv<qr> a() {
        return new qs();
    }

    public final tv<qr> a(qn qnVar) {
        return new qs(qnVar);
    }

    public static List<byte[]> a(boolean z) {
        return Collections.singletonList(new byte[]{z ? (byte) 1 : 0});
    }

    @Deprecated
    public static void b() {
        throw new UnsupportedOperationException();
    }

    public static void a(rt rtVar, long j, long j2, long j3) {
        rtVar.a(j, j2, j3);
    }

    public static int[] a(bs[] bsVarArr, int[] iArr) {
        int length = bsVarArr.length;
        if (iArr == null) {
            iArr = new int[length];
        }
        for (int i = 0; i < length; i++) {
            iArr[i] = bsVarArr[i].d;
        }
        return iArr;
    }

    public static Map c() {
        return Collections.emptyMap();
    }

    public static void b(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static int a(int i, int i2, int i3) {
        if (i >= 0 && i < i3) {
            return i;
        }
        throw new IndexOutOfBoundsException();
    }

    public static void c(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException();
    }

    public static void d() {
        int i = 0;
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                String str = "GlUtil";
                String str2 = "glError ";
                String valueOf = String.valueOf(GLU.gluErrorString(i));
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                i = glGetError;
            } else {
                return;
            }
        }
    }

    public static void b(String str) {
        if (vf.a >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void e() {
        if (vf.a >= 18) {
            Trace.endSection();
        }
    }

    public static Uri a(String str, String str2) {
        return Uri.parse(b(str, str2));
    }

    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] d = d(str2);
        if (d[0] != -1) {
            sb.append(str2);
            a(sb, d[1], d[2]);
            return sb.toString();
        }
        int[] d2 = d(str);
        if (d[3] == 0) {
            sb.append(str, 0, d2[3]);
            sb.append(str2);
            return sb.toString();
        } else if (d[2] == 0) {
            sb.append(str, 0, d2[2]);
            sb.append(str2);
            return sb.toString();
        } else if (d[1] != 0) {
            int i = d2[0] + 1;
            sb.append(str, 0, i);
            sb.append(str2);
            return a(sb, d[1] + i, i + d[2]);
        } else if (str2.charAt(d[1]) == '/') {
            sb.append(str, 0, d2[1]);
            sb.append(str2);
            return a(sb, d2[1], d2[1] + d[2]);
        } else if (d2[0] + 2 >= d2[1] || d2[1] != d2[2]) {
            int lastIndexOf = str.lastIndexOf(47, d2[2] - 1);
            int i2 = lastIndexOf == -1 ? d2[1] : lastIndexOf + 1;
            sb.append(str, 0, i2);
            sb.append(str2);
            return a(sb, d2[1], i2 + d[2]);
        } else {
            sb.append(str, 0, d2[1]);
            sb.append('/');
            sb.append(str2);
            return a(sb, d2[1], d2[1] + d[2] + 1);
        }
    }

    private static String a(StringBuilder sb, int i, int i2) {
        int i3;
        if (i >= i2) {
            return sb.toString();
        }
        if (sb.charAt(i) == '/') {
            i++;
        }
        int i4 = i;
        int i5 = i2;
        int i6 = i4;
        while (i6 <= i5) {
            if (i6 == i5) {
                i3 = i6;
            } else if (sb.charAt(i6) == '/') {
                i3 = i6 + 1;
            } else {
                i6++;
            }
            int i7 = i4 + 1;
            if (i6 == i7 && sb.charAt(i4) == '.') {
                sb.delete(i4, i3);
                i5 -= i3 - i4;
                i6 = i4;
            } else if (i6 == i4 + 2 && sb.charAt(i4) == '.' && sb.charAt(i7) == '.') {
                i4 = sb.lastIndexOf("/", i4 - 2) + 1;
                int i8 = i4 > i ? i4 : i;
                sb.delete(i8, i3);
                i5 -= i3 - i8;
                i6 = i4;
            } else {
                i4 = i6 + 1;
                i6 = i4;
            }
        }
        return sb.toString();
    }

    private static int[] d(String str) {
        int i;
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int length = str.length();
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            length = indexOf;
        }
        int indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        int i2 = indexOf4 + 2;
        if (i2 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(i2) == '/') {
            i = str.indexOf(47, indexOf4 + 3);
            if (i == -1 || i > indexOf2) {
                i = indexOf2;
            }
        } else {
            i = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = i;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }

    public static boolean a(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return a(xmlPullParser) && xmlPullParser.getName().equals(str);
    }

    public static boolean a(XmlPullParser xmlPullParser) throws XmlPullParserException {
        return xmlPullParser.getEventType() == 3;
    }

    public static boolean b(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return b(xmlPullParser) && xmlPullParser.getName().equals(str);
    }

    public static boolean b(XmlPullParser xmlPullParser) throws XmlPullParserException {
        return xmlPullParser.getEventType() == 2;
    }

    public static boolean c(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return b(xmlPullParser) && e(xmlPullParser.getName()).equals(str);
    }

    public static String d(XmlPullParser xmlPullParser, String str) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (e(xmlPullParser.getAttributeName(i)).equals(str)) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    private static String e(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            return str;
        }
        return str.substring(indexOf + 1);
    }

    public static DateFormat a(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append(" ");
        sb.append(b(i2));
        return new SimpleDateFormat(sb.toString(), Locale.US);
    }

    private static String a(int i) {
        switch (i) {
            case 0:
                return "EEEE, MMMM d, yyyy";
            case 1:
                return "MMMM d, yyyy";
            case 2:
                return "MMM d, yyyy";
            case 3:
                return "M/d/yy";
            default:
                StringBuilder sb = new StringBuilder("Unknown DateFormat style: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private static String b(int i) {
        switch (i) {
            case 0:
            case 1:
                return "h:mm:ss a z";
            case 2:
                return "h:mm:ss a";
            case 3:
                return "h:mm a";
            default:
                StringBuilder sb = new StringBuilder("Unknown DateFormat style: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static boolean c(String str) {
        boolean z;
        if (str != null) {
            afu a = afu.a();
            int length = str.length() - 1;
            while (true) {
                if (length < 0) {
                    z = true;
                    break;
                } else if (!a.a(str.charAt(length))) {
                    z = false;
                    break;
                } else {
                    length--;
                }
            }
            if (z) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static Map<String, String> a(Uri uri) {
        if (uri == null || uri.isOpaque()) {
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || encodedQuery.length() == 0) {
            return Collections.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        int indexOf = encodedQuery.indexOf(35);
        if (indexOf == -1) {
            indexOf = encodedQuery.length();
        }
        do {
            int indexOf2 = encodedQuery.indexOf(38, i);
            if (indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            int indexOf3 = encodedQuery.indexOf(61, i);
            if (indexOf3 > indexOf2 || indexOf3 == -1) {
                indexOf3 = indexOf2;
            }
            String substring = encodedQuery.substring(i, indexOf3);
            String str = "";
            if (indexOf3 < indexOf2) {
                str = encodedQuery.substring(indexOf3 + 1, indexOf2);
            }
            linkedHashMap.put(substring, str);
            i = indexOf2 + 1;
        } while (i < indexOf);
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public static String a(Map<String, String> map) {
        if (map.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            if (i < map.size() - 1) {
                sb.append("&");
            }
            i++;
        }
        return sb.toString();
    }

    public static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }
}
