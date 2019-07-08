package com.facebook.ads.internal.k;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.w.b.o;
import com.facebook.ads.internal.w.b.s;
import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.w.f.a;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    private static final String a = "com.facebook.ads.internal.k.e";
    private static final Object b = new Object();
    private static final Set<String> c = Collections.synchronizedSet(new HashSet());
    private static final Map<String, Integer> d = Collections.synchronizedMap(new HashMap());
    private static AtomicInteger e = new AtomicInteger();

    public static d a(Exception exc, Context context, Map<String, String> map) {
        try {
            d dVar = new d(o.b(), o.c(), new b(s.a((Throwable) exc), map, true).a());
            try {
                a(dVar, context);
                return dVar;
            } catch (Exception unused) {
                return dVar;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @WorkerThread
    public static JSONArray a(Context context) {
        return a(context, -1);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [int] */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r9v1, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r9v2 */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r9v5, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r9v8 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r9v9, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r3v7, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r9v14, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13, types: [java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r4v5, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r9v16, types: [int] */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r9v18, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r9v19 */
    /* JADX WARNING: type inference failed for: r9v20, types: [int] */
    /* JADX WARNING: type inference failed for: r9v22 */
    /* JADX WARNING: type inference failed for: r9v23 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r2v20 */
    /* JADX WARNING: type inference failed for: r2v21 */
    /* JADX WARNING: type inference failed for: r9v24 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r9v25 */
    /* JADX WARNING: type inference failed for: r9v26 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r9v27 */
    /* JADX WARNING: type inference failed for: r9v28 */
    /* JADX WARNING: type inference failed for: r9v29 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r9v30 */
    /* JADX WARNING: type inference failed for: r9v31 */
    /* JADX WARNING: type inference failed for: r9v32 */
    /* JADX WARNING: type inference failed for: r9v33 */
    /* JADX WARNING: type inference failed for: r9v34 */
    /* JADX WARNING: type inference failed for: r9v35 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r9v0, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r9v2
  assigns: []
  uses: []
  mth insns count: 150
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f1 A[SYNTHETIC, Splitter:B:64:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f9 A[Catch:{ IOException -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00fe A[Catch:{ IOException -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x010d A[SYNTHETIC, Splitter:B:79:0x010d] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0117 A[Catch:{ IOException -> 0x0113 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x011c A[Catch:{ IOException -> 0x0113 }] */
    /* JADX WARNING: Unknown variable types count: 21 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray a(android.content.Context r8, int r9) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.lang.Object r1 = b
            monitor-enter(r1)
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            java.io.File r4 = r8.getFilesDir()     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            java.lang.String r5 = "debuglogs"
            java.lang.String r5 = com.facebook.ads.internal.w.f.a.a(r5, r8)     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            r3.<init>(r4, r5)     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            boolean r3 = r3.exists()     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            if (r3 == 0) goto L_0x00c1
            java.lang.String r3 = "debuglogs"
            java.lang.String r3 = com.facebook.ads.internal.w.f.a.a(r3, r8)     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            java.io.FileInputStream r8 = r8.openFileInput(r3)     // Catch:{ IOException -> 0x00e5, JSONException -> 0x00e3, all -> 0x00de }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00be, JSONException -> 0x00bc, all -> 0x00b9 }
            r3.<init>(r8)     // Catch:{ IOException -> 0x00be, JSONException -> 0x00bc, all -> 0x00b9 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00b7, JSONException -> 0x00b5 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00b7, JSONException -> 0x00b5 }
        L_0x0032:
            java.lang.String r2 = r4.readLine()     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r2 == 0) goto L_0x00ab
            if (r9 == 0) goto L_0x00ab
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            r5.<init>(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.lang.String r2 = "attempt"
            boolean r2 = r5.has(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r2 != 0) goto L_0x0051
            java.lang.String r2 = "attempt"
            r6 = 0
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            r5.put(r2, r6)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
        L_0x0051:
            java.lang.String r2 = "id"
            java.lang.String r2 = r5.getString(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.util.Set<java.lang.String> r6 = c     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            boolean r6 = r6.contains(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r6 != 0) goto L_0x0032
            java.lang.String r6 = "attempt"
            int r6 = r5.getInt(r6)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            boolean r7 = r7.containsKey(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r7 == 0) goto L_0x007d
            java.lang.String r6 = "attempt"
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.lang.Object r2 = r7.get(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            r5.put(r6, r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            goto L_0x009b
        L_0x007d:
            java.util.Set<java.lang.String> r7 = c     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            boolean r7 = r7.contains(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r7 != 0) goto L_0x00a3
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            boolean r7 = r7.containsKey(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r7 == 0) goto L_0x0092
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            r7.remove(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
        L_0x0092:
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            r7.put(r2, r6)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
        L_0x009b:
            r0.put(r5)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            if (r9 <= 0) goto L_0x0032
            int r9 = r9 + -1
            goto L_0x0032
        L_0x00a3:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            java.lang.String r2 = "finished event should not be updated to OngoingEvent."
            r9.<init>(r2)     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
            throw r9     // Catch:{ IOException -> 0x00b2, JSONException -> 0x00b0, all -> 0x00ad }
        L_0x00ab:
            r2 = r4
            goto L_0x00c3
        L_0x00ad:
            r9 = move-exception
            goto L_0x010b
        L_0x00b0:
            r9 = move-exception
            goto L_0x00b3
        L_0x00b2:
            r9 = move-exception
        L_0x00b3:
            r2 = r4
            goto L_0x00e8
        L_0x00b5:
            r9 = move-exception
            goto L_0x00e8
        L_0x00b7:
            r9 = move-exception
            goto L_0x00e8
        L_0x00b9:
            r9 = move-exception
            r3 = r2
            goto L_0x00e1
        L_0x00bc:
            r9 = move-exception
            goto L_0x00bf
        L_0x00be:
            r9 = move-exception
        L_0x00bf:
            r3 = r2
            goto L_0x00e8
        L_0x00c1:
            r8 = r2
            r3 = r8
        L_0x00c3:
            if (r2 == 0) goto L_0x00cb
            r2.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00cb
        L_0x00c9:
            r8 = move-exception
            goto L_0x00d6
        L_0x00cb:
            if (r3 == 0) goto L_0x00d0
            r3.close()     // Catch:{ IOException -> 0x00c9 }
        L_0x00d0:
            if (r8 == 0) goto L_0x0107
            r8.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x0107
        L_0x00d6:
            java.lang.String r9 = a     // Catch:{ all -> 0x0111 }
            java.lang.String r2 = "Failed to close buffers"
        L_0x00da:
            android.util.Log.e(r9, r2, r8)     // Catch:{ all -> 0x0111 }
            goto L_0x0107
        L_0x00de:
            r9 = move-exception
            r8 = r2
            r3 = r8
        L_0x00e1:
            r4 = r3
            goto L_0x010b
        L_0x00e3:
            r9 = move-exception
            goto L_0x00e6
        L_0x00e5:
            r9 = move-exception
        L_0x00e6:
            r8 = r2
            r3 = r8
        L_0x00e8:
            java.lang.String r4 = a     // Catch:{ all -> 0x0109 }
            java.lang.String r5 = "Failed to read crashes"
            android.util.Log.e(r4, r5, r9)     // Catch:{ all -> 0x0109 }
            if (r2 == 0) goto L_0x00f7
            r2.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x00f7
        L_0x00f5:
            r8 = move-exception
            goto L_0x0102
        L_0x00f7:
            if (r3 == 0) goto L_0x00fc
            r3.close()     // Catch:{ IOException -> 0x00f5 }
        L_0x00fc:
            if (r8 == 0) goto L_0x0107
            r8.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x0107
        L_0x0102:
            java.lang.String r9 = a     // Catch:{ all -> 0x0111 }
            java.lang.String r2 = "Failed to close buffers"
            goto L_0x00da
        L_0x0107:
            monitor-exit(r1)     // Catch:{ all -> 0x0111 }
            return r0
        L_0x0109:
            r9 = move-exception
            r4 = r2
        L_0x010b:
            if (r4 == 0) goto L_0x0115
            r4.close()     // Catch:{ IOException -> 0x0113 }
            goto L_0x0115
        L_0x0111:
            r8 = move-exception
            goto L_0x0128
        L_0x0113:
            r8 = move-exception
            goto L_0x0120
        L_0x0115:
            if (r3 == 0) goto L_0x011a
            r3.close()     // Catch:{ IOException -> 0x0113 }
        L_0x011a:
            if (r8 == 0) goto L_0x0127
            r8.close()     // Catch:{ IOException -> 0x0113 }
            goto L_0x0127
        L_0x0120:
            java.lang.String r0 = a     // Catch:{ all -> 0x0111 }
            java.lang.String r2 = "Failed to close buffers"
            android.util.Log.e(r0, r2, r8)     // Catch:{ all -> 0x0111 }
        L_0x0127:
            throw r9     // Catch:{ all -> 0x0111 }
        L_0x0128:
            monitor-exit(r1)     // Catch:{ all -> 0x0111 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.k.e.a(android.content.Context, int):org.json.JSONArray");
    }

    @WorkerThread
    public static void a(d dVar, Context context) {
        if (!(dVar == null || context == null)) {
            synchronized (b) {
                try {
                    String a2 = a.a("debuglogs", context);
                    File file = new File(context.getFilesDir(), a2);
                    if (file.exists()) {
                        int ae = com.facebook.ads.internal.r.a.ae(context);
                        long length = file.length();
                        if (ae > 0 && length > ((long) ae)) {
                            boolean delete = file.delete();
                            b(context, 0);
                            c.clear();
                            d.clear();
                            if (!delete) {
                                Log.e(AudienceNetworkAds.TAG, "Can't delete debug events file.");
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Purge debug events file. File size: ");
                                sb.append(length);
                                sb.append(", DropCounter: ");
                                sb.append(e.getAndIncrement());
                                a(new Exception(sb.toString()), context, new HashMap());
                                return;
                            }
                        }
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", UUID.randomUUID().toString());
                    jSONObject.put("type", dVar.a());
                    jSONObject.put(TimestampAnalyticsHelper.ATTR_TIME, v.a(dVar.b()));
                    jSONObject.put("session_time", v.a(dVar.c()));
                    jSONObject.put(Attributes.SESSION_ID, dVar.d());
                    jSONObject.put("data", dVar.e() != null ? new JSONObject(dVar.e()) : new JSONObject());
                    jSONObject.put("attempt", String.valueOf(0));
                    FileOutputStream openFileOutput = context.openFileOutput(a2, 32768);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(jSONObject.toString());
                    sb2.append("\n");
                    openFileOutput.write(sb2.toString().getBytes());
                    openFileOutput.close();
                    b(context, context.getApplicationContext().getSharedPreferences(a.a("DEBUG_PREF", context), 0).getInt("EventCount", 0) + 1);
                } catch (Exception e2) {
                    Log.e(a, "Failed to store crash", e2);
                }
            }
        }
    }

    public static void a(String str) {
        Integer num = (Integer) d.get(str);
        if (num == null) {
            num = Integer.valueOf(0);
        } else {
            d.remove(str);
        }
        d.put(str, Integer.valueOf(num.intValue() + 1));
    }

    @WorkerThread
    public static void b(Context context) {
        synchronized (b) {
            File file = new File(context.getFilesDir(), a.a("debuglogs", context));
            if (file.exists()) {
                file.delete();
            }
            b(context, 0);
            c.clear();
            d.clear();
        }
    }

    private static void b(Context context, int i) {
        Editor edit = context.getApplicationContext().getSharedPreferences(a.a("DEBUG_PREF", context), 0).edit();
        String str = "EventCount";
        if (i < 0) {
            i = 0;
        }
        edit.putInt(str, i).apply();
    }

    public static void b(String str) {
        if (d.containsKey(str)) {
            d.remove(str);
        }
        c.add(str);
    }

    public static int c(Context context) {
        return context.getApplicationContext().getSharedPreferences(a.a("DEBUG_PREF", context), 0).getInt("EventCount", 0) - c.size();
    }

    public static boolean c(String str) {
        return c.contains(str) || d.containsKey(str);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r6v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r6v1 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r5v6, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: type inference failed for: r5v10, types: [java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r5v11 */
    /* JADX WARNING: type inference failed for: r5v12 */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: type inference failed for: r5v15 */
    /* JADX WARNING: type inference failed for: r5v16 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0109 A[SYNTHETIC, Splitter:B:65:0x0109] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0111 A[Catch:{ IOException -> 0x010d }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0116 A[Catch:{ IOException -> 0x010d }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x011b A[Catch:{ IOException -> 0x010d }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0137 A[SYNTHETIC, Splitter:B:83:0x0137] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0141 A[Catch:{ IOException -> 0x013d }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0146 A[Catch:{ IOException -> 0x013d }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x014b A[Catch:{ IOException -> 0x013d }] */
    /* JADX WARNING: Unknown variable types count: 13 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean d(android.content.Context r11) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.lang.Object r1 = b
            monitor-enter(r1)
            r2 = 0
            r3 = 0
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            java.io.File r5 = r11.getFilesDir()     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            java.lang.String r6 = "debuglogs"
            java.lang.String r6 = com.facebook.ads.internal.w.f.a.a(r6, r11)     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            r4.<init>(r5, r6)     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            boolean r4 = r4.exists()     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            if (r4 == 0) goto L_0x00bb
            java.lang.String r4 = "debuglogs"
            java.lang.String r4 = com.facebook.ads.internal.w.f.a.a(r4, r11)     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            java.io.FileInputStream r4 = r11.openFileInput(r4)     // Catch:{ IOException -> 0x00fc, JSONException -> 0x00fa, all -> 0x00f5 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00b7, JSONException -> 0x00b5, all -> 0x00b2 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x00b7, JSONException -> 0x00b5, all -> 0x00b2 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00ae, JSONException -> 0x00ac, all -> 0x00a8 }
            r6.<init>(r5)     // Catch:{ IOException -> 0x00ae, JSONException -> 0x00ac, all -> 0x00a8 }
        L_0x0033:
            java.lang.String r7 = r6.readLine()     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            if (r7 == 0) goto L_0x0067
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r8.<init>(r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.lang.String r7 = "id"
            java.lang.String r7 = r8.getString(r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.util.Set<java.lang.String> r9 = c     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            boolean r9 = r9.contains(r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            if (r9 != 0) goto L_0x0033
            java.util.Map<java.lang.String, java.lang.Integer> r9 = d     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            boolean r9 = r9.containsKey(r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            if (r9 == 0) goto L_0x0063
            java.lang.String r9 = "attempt"
            java.util.Map<java.lang.String, java.lang.Integer> r10 = d     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.lang.Object r7 = r10.get(r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r8.put(r9, r7)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
        L_0x0063:
            r0.put(r8)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            goto L_0x0033
        L_0x0067:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r7.<init>()     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            int r8 = r0.length()     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r9 = 0
        L_0x0071:
            if (r9 >= r8) goto L_0x0086
            org.json.JSONObject r10 = r0.getJSONObject(r9)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r7.append(r10)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r10 = 10
            r7.append(r10)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            int r9 = r9 + 1
            goto L_0x0071
        L_0x0086:
            java.lang.String r0 = "debuglogs"
            java.lang.String r0 = com.facebook.ads.internal.w.f.a.a(r0, r11)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.io.FileOutputStream r3 = r11.openFileOutput(r0, r2)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            java.lang.String r0 = r7.toString()     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r3.write(r0)     // Catch:{ IOException -> 0x00a3, JSONException -> 0x00a1, all -> 0x009e }
            r0 = r3
            r3 = r6
            goto L_0x00be
        L_0x009e:
            r11 = move-exception
            goto L_0x0135
        L_0x00a1:
            r11 = move-exception
            goto L_0x00a4
        L_0x00a3:
            r11 = move-exception
        L_0x00a4:
            r0 = r3
            r3 = r6
            goto L_0x0100
        L_0x00a8:
            r11 = move-exception
            r6 = r3
            goto L_0x0135
        L_0x00ac:
            r11 = move-exception
            goto L_0x00af
        L_0x00ae:
            r11 = move-exception
        L_0x00af:
            r0 = r3
            goto L_0x0100
        L_0x00b2:
            r11 = move-exception
            r5 = r3
            goto L_0x00f8
        L_0x00b5:
            r11 = move-exception
            goto L_0x00b8
        L_0x00b7:
            r11 = move-exception
        L_0x00b8:
            r0 = r3
            r5 = r0
            goto L_0x0100
        L_0x00bb:
            r0 = r3
            r4 = r0
            r5 = r4
        L_0x00be:
            int r6 = c(r11)     // Catch:{ IOException -> 0x00f3, JSONException -> 0x00f1 }
            b(r11, r6)     // Catch:{ IOException -> 0x00f3, JSONException -> 0x00f1 }
            if (r3 == 0) goto L_0x00cd
            r3.close()     // Catch:{ IOException -> 0x00cb }
            goto L_0x00cd
        L_0x00cb:
            r11 = move-exception
            goto L_0x00dd
        L_0x00cd:
            if (r5 == 0) goto L_0x00d2
            r5.close()     // Catch:{ IOException -> 0x00cb }
        L_0x00d2:
            if (r4 == 0) goto L_0x00d7
            r4.close()     // Catch:{ IOException -> 0x00cb }
        L_0x00d7:
            if (r0 == 0) goto L_0x00e4
            r0.close()     // Catch:{ IOException -> 0x00cb }
            goto L_0x00e4
        L_0x00dd:
            java.lang.String r0 = a     // Catch:{ all -> 0x013b }
            java.lang.String r2 = "Failed to close buffers"
            android.util.Log.e(r0, r2, r11)     // Catch:{ all -> 0x013b }
        L_0x00e4:
            java.util.Set<java.lang.String> r11 = c     // Catch:{ all -> 0x013b }
            r11.clear()     // Catch:{ all -> 0x013b }
            java.util.Map<java.lang.String, java.lang.Integer> r11 = d     // Catch:{ all -> 0x013b }
            r11.clear()     // Catch:{ all -> 0x013b }
            monitor-exit(r1)     // Catch:{ all -> 0x013b }
            r11 = 1
            return r11
        L_0x00f1:
            r11 = move-exception
            goto L_0x0100
        L_0x00f3:
            r11 = move-exception
            goto L_0x0100
        L_0x00f5:
            r11 = move-exception
            r4 = r3
            r5 = r4
        L_0x00f8:
            r6 = r5
            goto L_0x0135
        L_0x00fa:
            r11 = move-exception
            goto L_0x00fd
        L_0x00fc:
            r11 = move-exception
        L_0x00fd:
            r0 = r3
            r4 = r0
            r5 = r4
        L_0x0100:
            java.lang.String r6 = a     // Catch:{ all -> 0x0132 }
            java.lang.String r7 = "Failed to rewrite File."
            android.util.Log.e(r6, r7, r11)     // Catch:{ all -> 0x0132 }
            if (r3 == 0) goto L_0x010f
            r3.close()     // Catch:{ IOException -> 0x010d }
            goto L_0x010f
        L_0x010d:
            r11 = move-exception
            goto L_0x011f
        L_0x010f:
            if (r5 == 0) goto L_0x0114
            r5.close()     // Catch:{ IOException -> 0x010d }
        L_0x0114:
            if (r4 == 0) goto L_0x0119
            r4.close()     // Catch:{ IOException -> 0x010d }
        L_0x0119:
            if (r0 == 0) goto L_0x0126
            r0.close()     // Catch:{ IOException -> 0x010d }
            goto L_0x0126
        L_0x011f:
            java.lang.String r0 = a     // Catch:{ all -> 0x013b }
            java.lang.String r3 = "Failed to close buffers"
            android.util.Log.e(r0, r3, r11)     // Catch:{ all -> 0x013b }
        L_0x0126:
            java.util.Set<java.lang.String> r11 = c     // Catch:{ all -> 0x013b }
            r11.clear()     // Catch:{ all -> 0x013b }
            java.util.Map<java.lang.String, java.lang.Integer> r11 = d     // Catch:{ all -> 0x013b }
            r11.clear()     // Catch:{ all -> 0x013b }
            monitor-exit(r1)     // Catch:{ all -> 0x013b }
            return r2
        L_0x0132:
            r11 = move-exception
            r6 = r3
            r3 = r0
        L_0x0135:
            if (r6 == 0) goto L_0x013f
            r6.close()     // Catch:{ IOException -> 0x013d }
            goto L_0x013f
        L_0x013b:
            r11 = move-exception
            goto L_0x0161
        L_0x013d:
            r0 = move-exception
            goto L_0x014f
        L_0x013f:
            if (r5 == 0) goto L_0x0144
            r5.close()     // Catch:{ IOException -> 0x013d }
        L_0x0144:
            if (r4 == 0) goto L_0x0149
            r4.close()     // Catch:{ IOException -> 0x013d }
        L_0x0149:
            if (r3 == 0) goto L_0x0156
            r3.close()     // Catch:{ IOException -> 0x013d }
            goto L_0x0156
        L_0x014f:
            java.lang.String r2 = a     // Catch:{ all -> 0x013b }
            java.lang.String r3 = "Failed to close buffers"
            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x013b }
        L_0x0156:
            java.util.Set<java.lang.String> r0 = c     // Catch:{ all -> 0x013b }
            r0.clear()     // Catch:{ all -> 0x013b }
            java.util.Map<java.lang.String, java.lang.Integer> r0 = d     // Catch:{ all -> 0x013b }
            r0.clear()     // Catch:{ all -> 0x013b }
            throw r11     // Catch:{ all -> 0x013b }
        L_0x0161:
            monitor-exit(r1)     // Catch:{ all -> 0x013b }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.k.e.d(android.content.Context):boolean");
    }
}
