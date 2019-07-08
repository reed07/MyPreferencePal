package com.inmobi.commons.core.utilities;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/* compiled from: NetworkUtils */
public class d {
    private static final String a = "d";

    @SuppressLint({"MissingPermission"})
    public static boolean a() {
        boolean z = false;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) a.b().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && !b()) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking network availability; ").append(e.getMessage());
            return false;
        }
    }

    private static boolean b() {
        try {
            PowerManager powerManager = (PowerManager) a.b().getSystemService("power");
            if (VERSION.SDK_INT > 22) {
                return powerManager.isDeviceIdleMode();
            }
            return false;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking idle mode; ").append(e.getMessage());
            return false;
        }
    }

    public static String a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(String.format(Locale.US, "%s=%s", new Object[]{a((String) entry.getKey()), a((String) entry.getValue())}));
        }
        return sb.toString();
    }

    private static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static void a(Map<String, String> map) {
        if (map != null) {
            HashMap hashMap = new HashMap();
            for (Entry entry : map.entrySet()) {
                if (!(entry.getValue() == null || ((String) entry.getValue()).trim().length() == 0 || entry.getKey() == null || ((String) entry.getKey()).trim().length() == 0)) {
                    hashMap.put(((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
                }
            }
            map.clear();
            map.putAll(hashMap);
        }
    }

    public static String a(String str, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public static byte[] a(@NonNull byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byte[] a2 = a((InputStream) gZIPInputStream);
                a((Closeable) byteArrayInputStream);
                a((Closeable) gZIPInputStream);
                return a2;
            } catch (IOException e) {
                e = e;
                try {
                    Logger.a(InternalLogLevel.DEBUG, a, "Failed to decompress response", e);
                    a((Closeable) byteArrayInputStream);
                    a((Closeable) gZIPInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    a((Closeable) byteArrayInputStream);
                    a((Closeable) gZIPInputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            gZIPInputStream = null;
            Logger.a(InternalLogLevel.DEBUG, a, "Failed to decompress response", e);
            a((Closeable) byteArrayInputStream);
            a((Closeable) gZIPInputStream);
            return null;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            gZIPInputStream = null;
            th = th3;
            a((Closeable) byteArrayInputStream);
            a((Closeable) gZIPInputStream);
            throw th;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                byteArrayOutputStream.close();
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
