package com.facebook.ads.internal.l;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static SensorManager a;
    private static Sensor b;
    private static Sensor c;
    /* access modifiers changed from: private */
    public static volatile float[] d;
    /* access modifiers changed from: private */
    public static volatile float[] e;
    private static Map<String, String> f = new ConcurrentHashMap();
    private static String[] g = {AvidJSONUtil.KEY_X, "y", "z"};
    private static SensorEventListener h;
    private static SensorEventListener i;

    /* renamed from: com.facebook.ads.internal.l.a$a reason: collision with other inner class name */
    private static class C0007a implements SensorEventListener {
        private C0007a() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            a.d = sensorEvent.values;
            a.d();
        }
    }

    private static class b implements SensorEventListener {
        private b() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            a.e = sensorEvent.values;
            a.e();
        }
    }

    public static Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(f);
        a((Map<String, String>) hashMap);
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0106, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r8) {
        /*
            java.lang.Class<com.facebook.ads.internal.l.a> r0 = com.facebook.ads.internal.l.a.class
            monitor-enter(r0)
            android.app.ActivityManager$MemoryInfo r1 = new android.app.ActivityManager$MemoryInfo     // Catch:{ all -> 0x0107 }
            r1.<init>()     // Catch:{ all -> 0x0107 }
            java.lang.String r2 = "activity"
            java.lang.Object r2 = r8.getSystemService(r2)     // Catch:{ all -> 0x0107 }
            android.app.ActivityManager r2 = (android.app.ActivityManager) r2     // Catch:{ all -> 0x0107 }
            r2.getMemoryInfo(r1)     // Catch:{ all -> 0x0107 }
            java.util.Map<java.lang.String, java.lang.String> r2 = f     // Catch:{ all -> 0x0107 }
            java.lang.String r3 = "available_memory"
            long r4 = r1.availMem     // Catch:{ all -> 0x0107 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0107 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0107 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0107 }
            r3 = 16
            if (r2 < r3) goto L_0x0033
            java.util.Map<java.lang.String, java.lang.String> r2 = f     // Catch:{ all -> 0x0107 }
            java.lang.String r3 = "total_memory"
            long r4 = r1.totalMem     // Catch:{ all -> 0x0107 }
            java.lang.String r1 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0107 }
            r2.put(r3, r1)     // Catch:{ all -> 0x0107 }
        L_0x0033:
            java.io.File r1 = android.os.Environment.getDataDirectory()     // Catch:{ all -> 0x0107 }
            android.os.StatFs r2 = new android.os.StatFs     // Catch:{ all -> 0x0107 }
            java.lang.String r1 = r1.getPath()     // Catch:{ all -> 0x0107 }
            r2.<init>(r1)     // Catch:{ all -> 0x0107 }
            int r1 = r2.getBlockSize()     // Catch:{ all -> 0x0107 }
            long r3 = (long) r1     // Catch:{ all -> 0x0107 }
            int r1 = r2.getAvailableBlocks()     // Catch:{ all -> 0x0107 }
            long r1 = (long) r1     // Catch:{ all -> 0x0107 }
            java.util.Map<java.lang.String, java.lang.String> r5 = f     // Catch:{ all -> 0x0107 }
            java.lang.String r6 = "free_space"
            long r1 = r1 * r3
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0107 }
            r5.put(r6, r1)     // Catch:{ all -> 0x0107 }
            android.content.IntentFilter r1 = new android.content.IntentFilter     // Catch:{ all -> 0x0107 }
            java.lang.String r2 = "android.intent.action.BATTERY_CHANGED"
            r1.<init>(r2)     // Catch:{ all -> 0x0107 }
            r2 = 0
            android.content.Intent r1 = r8.registerReceiver(r2, r1)     // Catch:{ all -> 0x0107 }
            r3 = 1
            if (r1 != 0) goto L_0x0067
            goto L_0x00a7
        L_0x0067:
            java.lang.String r4 = "level"
            r5 = -1
            int r4 = r1.getIntExtra(r4, r5)     // Catch:{ all -> 0x0107 }
            java.lang.String r6 = "scale"
            int r6 = r1.getIntExtra(r6, r5)     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = "status"
            int r1 = r1.getIntExtra(r7, r5)     // Catch:{ all -> 0x0107 }
            r5 = 2
            if (r1 == r5) goto L_0x0083
            r5 = 5
            if (r1 != r5) goto L_0x0081
            goto L_0x0083
        L_0x0081:
            r1 = 0
            goto L_0x0084
        L_0x0083:
            r1 = 1
        L_0x0084:
            r5 = 0
            if (r6 <= 0) goto L_0x008e
            float r4 = (float) r4     // Catch:{ all -> 0x0107 }
            float r5 = (float) r6     // Catch:{ all -> 0x0107 }
            float r4 = r4 / r5
            r5 = 1120403456(0x42c80000, float:100.0)
            float r5 = r5 * r4
        L_0x008e:
            java.util.Map<java.lang.String, java.lang.String> r4 = f     // Catch:{ all -> 0x0107 }
            java.lang.String r6 = "battery"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0107 }
            r4.put(r6, r5)     // Catch:{ all -> 0x0107 }
            java.util.Map<java.lang.String, java.lang.String> r4 = f     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = "charging"
            if (r1 == 0) goto L_0x00a2
            java.lang.String r1 = "1"
            goto L_0x00a4
        L_0x00a2:
            java.lang.String r1 = "0"
        L_0x00a4:
            r4.put(r5, r1)     // Catch:{ all -> 0x0107 }
        L_0x00a7:
            android.hardware.SensorManager r1 = a     // Catch:{ all -> 0x0107 }
            if (r1 != 0) goto L_0x00bb
            java.lang.String r1 = "sensor"
            java.lang.Object r8 = r8.getSystemService(r1)     // Catch:{ all -> 0x0107 }
            android.hardware.SensorManager r8 = (android.hardware.SensorManager) r8     // Catch:{ all -> 0x0107 }
            a = r8     // Catch:{ all -> 0x0107 }
            android.hardware.SensorManager r8 = a     // Catch:{ all -> 0x0107 }
            if (r8 != 0) goto L_0x00bb
            monitor-exit(r0)
            return
        L_0x00bb:
            android.hardware.Sensor r8 = b     // Catch:{ all -> 0x0107 }
            if (r8 != 0) goto L_0x00c7
            android.hardware.SensorManager r8 = a     // Catch:{ all -> 0x0107 }
            android.hardware.Sensor r8 = r8.getDefaultSensor(r3)     // Catch:{ all -> 0x0107 }
            b = r8     // Catch:{ all -> 0x0107 }
        L_0x00c7:
            android.hardware.Sensor r8 = c     // Catch:{ all -> 0x0107 }
            if (r8 != 0) goto L_0x00d4
            android.hardware.SensorManager r8 = a     // Catch:{ all -> 0x0107 }
            r1 = 4
            android.hardware.Sensor r8 = r8.getDefaultSensor(r1)     // Catch:{ all -> 0x0107 }
            c = r8     // Catch:{ all -> 0x0107 }
        L_0x00d4:
            android.hardware.SensorEventListener r8 = h     // Catch:{ all -> 0x0107 }
            r1 = 3
            if (r8 != 0) goto L_0x00ed
            com.facebook.ads.internal.l.a$a r8 = new com.facebook.ads.internal.l.a$a     // Catch:{ all -> 0x0107 }
            r8.<init>()     // Catch:{ all -> 0x0107 }
            h = r8     // Catch:{ all -> 0x0107 }
            android.hardware.Sensor r8 = b     // Catch:{ all -> 0x0107 }
            if (r8 == 0) goto L_0x00ed
            android.hardware.SensorManager r8 = a     // Catch:{ all -> 0x0107 }
            android.hardware.SensorEventListener r3 = h     // Catch:{ all -> 0x0107 }
            android.hardware.Sensor r4 = b     // Catch:{ all -> 0x0107 }
            r8.registerListener(r3, r4, r1)     // Catch:{ all -> 0x0107 }
        L_0x00ed:
            android.hardware.SensorEventListener r8 = i     // Catch:{ all -> 0x0107 }
            if (r8 != 0) goto L_0x0105
            com.facebook.ads.internal.l.a$b r8 = new com.facebook.ads.internal.l.a$b     // Catch:{ all -> 0x0107 }
            r8.<init>()     // Catch:{ all -> 0x0107 }
            i = r8     // Catch:{ all -> 0x0107 }
            android.hardware.Sensor r8 = c     // Catch:{ all -> 0x0107 }
            if (r8 == 0) goto L_0x0105
            android.hardware.SensorManager r8 = a     // Catch:{ all -> 0x0107 }
            android.hardware.SensorEventListener r2 = i     // Catch:{ all -> 0x0107 }
            android.hardware.Sensor r3 = c     // Catch:{ all -> 0x0107 }
            r8.registerListener(r2, r3, r1)     // Catch:{ all -> 0x0107 }
        L_0x0105:
            monitor-exit(r0)
            return
        L_0x0107:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.l.a.a(android.content.Context):void");
    }

    private static void a(Map<String, String> map) {
        float[] fArr = d;
        float[] fArr2 = e;
        if (fArr != null) {
            int min = Math.min(g.length, fArr.length);
            for (int i2 = 0; i2 < min; i2++) {
                StringBuilder sb = new StringBuilder();
                sb.append("accelerometer_");
                sb.append(g[i2]);
                map.put(sb.toString(), String.valueOf(fArr[i2]));
            }
        }
        if (fArr2 != null) {
            int min2 = Math.min(g.length, fArr2.length);
            for (int i3 = 0; i3 < min2; i3++) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("rotation_");
                sb2.append(g[i3]);
                map.put(sb2.toString(), String.valueOf(fArr2[i3]));
            }
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void d() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(h);
            }
            h = null;
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void e() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(i);
            }
            i = null;
        }
    }
}
