package com.inmobi.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.a.o;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.b.g;
import com.myfitnesspal.shared.db.table.ProfileImagesTable.Columns;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public final class InMobiSdk {
    private static final ExecutorService COMPONENT_SERVICE = Executors.newSingleThreadExecutor();
    public static final String IM_GDPR_CONSENT_AVAILABLE = "gdpr_consent_available";
    /* access modifiers changed from: private */
    public static final String TAG = "InMobiSdk";

    public enum AgeGroup {
        BELOW_18("below18"),
        BETWEEN_18_AND_24("between18and24"),
        BETWEEN_25_AND_29("between25and29"),
        BETWEEN_30_AND_34("between30and34"),
        BETWEEN_35_AND_44("between35and44"),
        BETWEEN_45_AND_54("between45and54"),
        BETWEEN_55_AND_65("between55and65"),
        ABOVE_65("above65");
        
        private String a;

        private AgeGroup(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Education {
        HIGH_SCHOOL_OR_LESS("highschoolorless"),
        COLLEGE_OR_GRADUATE("collegeorgraduate"),
        POST_GRADUATE_OR_ABOVE("postgraduateorabove");
        
        private String a;

        private Education(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Gender {
        FEMALE("f"),
        MALE("m");
        
        private String a;

        private Gender(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum LogLevel {
        NONE,
        ERROR,
        DEBUG
    }

    public static String getVersion() {
        return "7.2.6";
    }

    public static void init(Context context, String str) {
        init(context, str, null);
    }

    public static void init(Context context, String str, JSONObject jSONObject) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            e.a(jSONObject);
            if (VERSION.SDK_INT < 14) {
                Logger.a(InternalLogLevel.ERROR, TAG, "The minimum supported Android API level is 14, SDK could not be initialized.");
            } else if (context == null) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Context supplied as null, SDK could not be initialized.");
            } else {
                if (str != null) {
                    if (str.trim().length() != 0) {
                        Intent intent = new Intent();
                        intent.setClassName(context.getPackageName(), "com.inmobi.rendering.InMobiAdActivity");
                        if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                            Logger.a(InternalLogLevel.ERROR, TAG, "The activity com.inmobi.rendering.InMobiAdActivity not present in AndroidManifest. SDK could not be initialized.");
                            return;
                        }
                        if (com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.INTERNET")) {
                            if (com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_NETWORK_STATE")) {
                                if (!com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_COARSE_LOCATION") && !com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_FINE_LOCATION")) {
                                    Logger.a(InternalLogLevel.ERROR, TAG, "Please grant the location permissions (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION, or both) for better ad targeting.");
                                }
                                str = str.trim();
                                if (!(str.length() == 32 || str.length() == 36)) {
                                    Logger.a(InternalLogLevel.DEBUG, TAG, "Invalid account id passed to init. Please provide a valid account id.");
                                }
                                if (a.a()) {
                                    try {
                                        b.a();
                                        b.a("root", "InitRequested", null);
                                        return;
                                    } catch (Exception e) {
                                        StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                                        sb.append(e.getMessage());
                                        sb.append(")");
                                        return;
                                    }
                                } else {
                                    if (hasSdkVersionChanged(context)) {
                                        com.inmobi.commons.a.b.a(context, a.a(context));
                                        c.a(context, "sdk_version_store").a("sdk_version", "7.2.6");
                                        resetMediaCache(context.getApplicationContext());
                                    }
                                    a.a(context, str);
                                    com.inmobi.commons.core.configs.b.a().b();
                                    b.a().b();
                                    if (c.a(context, "sdk_version_store").b("db_deletion_failed", false)) {
                                        List<String> b = a.b(context);
                                        for (String sendDbDeletionTelemetryEvent : b) {
                                            sendDbDeletionTelemetryEvent(sendDbDeletionTelemetryEvent);
                                        }
                                        if (b.isEmpty()) {
                                            com.inmobi.commons.a.b.a(context, false);
                                        }
                                    }
                                    g.b();
                                    initComponents();
                                    com.inmobi.commons.core.configs.b.a();
                                    com.inmobi.commons.core.configs.b.d();
                                    COMPONENT_SERVICE.execute(new Runnable() {
                                        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f9, code lost:
                                            return;
                                         */
                                        /* Code decompiled incorrectly, please refer to instructions dump. */
                                        public final void run() {
                                            /*
                                                r19 = this;
                                                com.inmobi.ads.b.c()     // Catch:{ Exception -> 0x00fd }
                                                com.inmobi.ads.cache.AssetStore r0 = com.inmobi.ads.cache.AssetStore.a()     // Catch:{ Exception -> 0x00fd }
                                                r0.b()     // Catch:{ Exception -> 0x00fd }
                                                com.inmobi.ads.cache.AssetStore r0 = com.inmobi.ads.cache.AssetStore.a()     // Catch:{ Exception -> 0x00fd }
                                                java.lang.Object r1 = com.inmobi.ads.cache.AssetStore.e     // Catch:{ Exception -> 0x00fd }
                                                monitor-enter(r1)     // Catch:{ Exception -> 0x00fd }
                                                com.inmobi.ads.cache.d r2 = r0.a     // Catch:{ all -> 0x00fa }
                                                java.util.List r2 = com.inmobi.ads.cache.d.b()     // Catch:{ all -> 0x00fa }
                                                boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x00fa }
                                                if (r3 == 0) goto L_0x001f
                                                monitor-exit(r1)     // Catch:{ all -> 0x00fa }
                                                return
                                            L_0x001f:
                                                java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x00fa }
                                            L_0x0023:
                                                boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00fa }
                                                r5 = 1
                                                r6 = 0
                                                if (r4 == 0) goto L_0x0043
                                                java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00fa }
                                                com.inmobi.ads.cache.a r4 = (com.inmobi.ads.cache.a) r4     // Catch:{ all -> 0x00fa }
                                                long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00fa }
                                                long r9 = r4.h     // Catch:{ all -> 0x00fa }
                                                int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                                                if (r11 <= 0) goto L_0x003c
                                                goto L_0x003d
                                            L_0x003c:
                                                r5 = 0
                                            L_0x003d:
                                                if (r5 == 0) goto L_0x0023
                                                com.inmobi.ads.cache.AssetStore.a(r4)     // Catch:{ all -> 0x00fa }
                                                goto L_0x0023
                                            L_0x0043:
                                                java.util.List r3 = com.inmobi.ads.cache.d.b()     // Catch:{ all -> 0x00fa }
                                                r7 = 0
                                                java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00fa }
                                            L_0x004d:
                                                boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00fa }
                                                if (r4 == 0) goto L_0x0066
                                                java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00fa }
                                                com.inmobi.ads.cache.a r4 = (com.inmobi.ads.cache.a) r4     // Catch:{ all -> 0x00fa }
                                                java.io.File r9 = new java.io.File     // Catch:{ all -> 0x00fa }
                                                java.lang.String r4 = r4.e     // Catch:{ all -> 0x00fa }
                                                r9.<init>(r4)     // Catch:{ all -> 0x00fa }
                                                long r9 = r9.length()     // Catch:{ all -> 0x00fa }
                                                long r7 = r7 + r9
                                                goto L_0x004d
                                            L_0x0066:
                                                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fa }
                                                java.lang.String r4 = "MAX CACHESIZE "
                                                r3.<init>(r4)     // Catch:{ all -> 0x00fa }
                                                com.inmobi.ads.c$b r4 = r0.b     // Catch:{ all -> 0x00fa }
                                                long r9 = r4.d     // Catch:{ all -> 0x00fa }
                                                r3.append(r9)     // Catch:{ all -> 0x00fa }
                                                com.inmobi.ads.c$b r3 = r0.b     // Catch:{ all -> 0x00fa }
                                                long r3 = r3.d     // Catch:{ all -> 0x00fa }
                                                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                                                if (r9 <= 0) goto L_0x00a9
                                                com.inmobi.commons.core.d.b r10 = com.inmobi.commons.core.d.b.a()     // Catch:{ all -> 0x00fa }
                                                java.lang.String r11 = "asset"
                                                java.lang.String[] r12 = com.inmobi.ads.cache.d.a     // Catch:{ all -> 0x00fa }
                                                r13 = 0
                                                r14 = 0
                                                r15 = 0
                                                r16 = 0
                                                java.lang.String r17 = "ts ASC "
                                                r18 = 0
                                                java.util.List r3 = r10.a(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x00fa }
                                                int r4 = r3.size()     // Catch:{ all -> 0x00fa }
                                                if (r4 != 0) goto L_0x0099
                                                r3 = 0
                                                goto L_0x00a3
                                            L_0x0099:
                                                java.lang.Object r3 = r3.get(r6)     // Catch:{ all -> 0x00fa }
                                                android.content.ContentValues r3 = (android.content.ContentValues) r3     // Catch:{ all -> 0x00fa }
                                                com.inmobi.ads.cache.a r3 = com.inmobi.ads.cache.d.a(r3)     // Catch:{ all -> 0x00fa }
                                            L_0x00a3:
                                                if (r3 == 0) goto L_0x00a9
                                                com.inmobi.ads.cache.AssetStore.a(r3)     // Catch:{ all -> 0x00fa }
                                                goto L_0x0043
                                            L_0x00a9:
                                                android.content.Context r0 = com.inmobi.commons.a.a.b()     // Catch:{ all -> 0x00fa }
                                                java.io.File r0 = com.inmobi.commons.a.a.a(r0)     // Catch:{ all -> 0x00fa }
                                                boolean r3 = r0.exists()     // Catch:{ all -> 0x00fa }
                                                if (r3 == 0) goto L_0x00f8
                                                java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x00fa }
                                                if (r0 == 0) goto L_0x00f8
                                                int r3 = r0.length     // Catch:{ all -> 0x00fa }
                                                r4 = 0
                                            L_0x00bf:
                                                if (r4 >= r3) goto L_0x00f8
                                                r7 = r0[r4]     // Catch:{ all -> 0x00fa }
                                                java.util.Iterator r8 = r2.iterator()     // Catch:{ all -> 0x00fa }
                                            L_0x00c7:
                                                boolean r9 = r8.hasNext()     // Catch:{ all -> 0x00fa }
                                                if (r9 == 0) goto L_0x00e1
                                                java.lang.Object r9 = r8.next()     // Catch:{ all -> 0x00fa }
                                                com.inmobi.ads.cache.a r9 = (com.inmobi.ads.cache.a) r9     // Catch:{ all -> 0x00fa }
                                                java.lang.String r10 = r7.getAbsolutePath()     // Catch:{ all -> 0x00fa }
                                                java.lang.String r9 = r9.e     // Catch:{ all -> 0x00fa }
                                                boolean r9 = r10.equals(r9)     // Catch:{ all -> 0x00fa }
                                                if (r9 == 0) goto L_0x00c7
                                                r8 = 1
                                                goto L_0x00e2
                                            L_0x00e1:
                                                r8 = 0
                                            L_0x00e2:
                                                if (r8 != 0) goto L_0x00f5
                                                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fa }
                                                java.lang.String r9 = "found Orphan file "
                                                r8.<init>(r9)     // Catch:{ all -> 0x00fa }
                                                java.lang.String r9 = r7.getAbsolutePath()     // Catch:{ all -> 0x00fa }
                                                r8.append(r9)     // Catch:{ all -> 0x00fa }
                                                r7.delete()     // Catch:{ all -> 0x00fa }
                                            L_0x00f5:
                                                int r4 = r4 + 1
                                                goto L_0x00bf
                                            L_0x00f8:
                                                monitor-exit(r1)     // Catch:{ all -> 0x00fa }
                                                return
                                            L_0x00fa:
                                                r0 = move-exception
                                                monitor-exit(r1)     // Catch:{ all -> 0x00fa }
                                                throw r0     // Catch:{ Exception -> 0x00fd }
                                            L_0x00fd:
                                                r0 = move-exception
                                                com.inmobi.sdk.InMobiSdk.TAG
                                                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                                                java.lang.String r2 = "Error in starting Asset Cache : ("
                                                r1.<init>(r2)
                                                java.lang.String r0 = r0.getMessage()
                                                r1.append(r0)
                                                java.lang.String r0 = ")"
                                                r1.append(r0)
                                                return
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.sdk.InMobiSdk.AnonymousClass1.run():void");
                                        }
                                    });
                                    if (context instanceof Activity) {
                                        com.inmobi.commons.core.utilities.a a = com.inmobi.commons.core.utilities.a.a();
                                        if (a != null) {
                                            a.a((com.inmobi.commons.core.utilities.a.b) new com.inmobi.commons.core.utilities.a.b() {
                                                public final void a(boolean z) {
                                                    a.b(z);
                                                    if (z) {
                                                        try {
                                                            InMobiSdk.initComponents();
                                                        } catch (Exception e) {
                                                            InMobiSdk.TAG;
                                                            new StringBuilder("Encountered unexpected error in the onFocusChanged handler: ").append(e.getMessage());
                                                            Logger.a(InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered an unexpected error; some components may not work as advertised");
                                                        }
                                                    } else {
                                                        InMobiSdk.deInitComponents();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    try {
                                        b.a();
                                        b.a("root", "InitRequested", null);
                                    } catch (Exception e2) {
                                        StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                                        sb2.append(e2.getMessage());
                                        sb2.append(")");
                                    }
                                    InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                                    String str2 = TAG;
                                    StringBuilder sb3 = new StringBuilder("InMobi SDK initialized with account id: ");
                                    sb3.append(str);
                                    Logger.a(internalLogLevel, str2, sb3.toString());
                                    try {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                                        b.a();
                                        b.a("root", "SdkInitialized", hashMap);
                                    } catch (Exception e3) {
                                        StringBuilder sb4 = new StringBuilder("Error in submitting telemetry event : (");
                                        sb4.append(e3.getMessage());
                                        sb4.append(")");
                                    }
                                    printGrantedPermissions();
                                    return;
                                }
                            }
                        }
                        Logger.a(InternalLogLevel.ERROR, TAG, "Please grant the mandatory permissions : INTERNET and ACCESS_NETWORK_STATE, SDK could not be initialized.");
                        return;
                    }
                }
                Logger.a(InternalLogLevel.ERROR, TAG, "Account id cannot be null or empty. Please provide a valid account id.");
            }
        } catch (Exception e4) {
            a.c();
            Logger.a(InternalLogLevel.ERROR, TAG, "SDK could not be initialized; an unexpected error was encountered");
            new StringBuilder("Encountered unexpected error while initializing the SDK: ").append(e4.getMessage());
        }
    }

    public static void updateGDPRConsent(JSONObject jSONObject) {
        e.a(jSONObject);
    }

    public static void setApplicationMuted(boolean z) {
        a.a(z);
    }

    private static void sendDbDeletionTelemetryEvent(String str) {
        if (a.a()) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put(Columns.FILENAME, str);
                StringBuilder sb = new StringBuilder("DB Deleted : ");
                sb.append(str);
                hashMap.put("description", sb.toString());
                b.a();
                b.a("ads", "PersistentDataCleanFail", hashMap);
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                sb2.append(e.getMessage());
                sb2.append(")");
            }
        }
    }

    private static boolean hasSdkVersionChanged(Context context) {
        return com.inmobi.commons.a.b.a(context) == null || !com.inmobi.commons.a.b.a(context).equals("7.2.6");
    }

    private static void printGrantedPermissions() {
        COMPONENT_SERVICE.execute(new Runnable() {
            public final void run() {
                String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.VIBRATE", "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"};
                StringBuilder sb = new StringBuilder("Permissions granted to SDK are :\nandroid.permission.INTERNET\nandroid.permission.ACCESS_NETWORK_STATE");
                for (int i = 0; i < 8; i++) {
                    String str = strArr[i];
                    if (com.inmobi.commons.core.utilities.e.a(a.b(), "ads", str)) {
                        sb.append("\n");
                        sb.append(str);
                    }
                }
                Logger.a(InternalLogLevel.DEBUG, InMobiSdk.TAG, sb.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public static void initComponents() {
        try {
            COMPONENT_SERVICE.execute(new Runnable() {
                public final void run() {
                    try {
                        com.inmobi.commons.core.utilities.uid.c a = com.inmobi.commons.core.utilities.uid.c.a();
                        try {
                            com.inmobi.commons.core.utilities.uid.c.c();
                            a.b();
                        } catch (Exception e) {
                            new StringBuilder("SDK encountered an unexpected error while initializing the UID helper component; ").append(e.getMessage());
                        }
                        com.inmobi.commons.core.utilities.uid.c.a().b();
                        com.inmobi.commons.core.configs.b.a().b();
                        com.inmobi.rendering.a.c.a().b();
                        com.inmobi.commons.core.a.a a2 = com.inmobi.commons.core.a.a.a();
                        com.inmobi.commons.core.a.a.b.set(false);
                        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) a2.c, (com.inmobi.commons.core.configs.b.c) a2);
                        a2.d = a2.c.a;
                        a2.a.execute(new Runnable() {
                            public final void run() {
                                
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.inmobi.commons.core.a.a
                                      0x0000: IGET  (r0v0 com.inmobi.commons.core.a.a) = (r1v0 'this' com.inmobi.commons.core.a.a$2 A[THIS]) com.inmobi.commons.core.a.a.2.a com.inmobi.commons.core.a.a) com.inmobi.commons.core.a.a.b(com.inmobi.commons.core.a.a):void type: STATIC in method: com.inmobi.commons.core.a.a.2.run():void, dex: classes3.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                    	... 67 more
                                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                                    	at java.base/java.lang.Class.forName0(Native Method)
                                    	at java.base/java.lang.Class.forName(Unknown Source)
                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                                    	... 84 more
                                    */
                                /*
                                    this = this;
                                    com.inmobi.commons.core.a.a r0 = com.inmobi.commons.core.a.a.this
                                    
                                    // error: 0x0002: INVOKE  (r0 I:com.inmobi.commons.core.a.a) com.inmobi.commons.core.a.a.b(com.inmobi.commons.core.a.a):void type: STATIC
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.a.a.AnonymousClass2.run():void");
                            }
                        });
                        b.a().b();
                        com.inmobi.b.a a3 = com.inmobi.b.a.a();
                        com.inmobi.b.a.b.set(false);
                        e.c();
                        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) a3.c, (com.inmobi.commons.core.configs.b.c) a3);
                        a3.d = a3.c.b;
                        a3.a.execute(new Runnable() {
                            public final void run() {
                                a.a(a.this);
                            }
                        });
                        com.inmobi.ads.b.a();
                        o.a().b();
                        com.inmobi.ads.c.b.d().a();
                        com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a();
                        AssetStore.a().b();
                    } catch (Exception e2) {
                        InMobiSdk.TAG;
                        new StringBuilder("Encountered unexpected error in starting SDK components: ").append(e2.getMessage());
                        Logger.a(InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered unexpected error while starting internal components");
                    }
                }
            });
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in starting SDK components: ").append(e.getMessage());
            Logger.a(InternalLogLevel.DEBUG, TAG, "SDK encountered unexpected error while starting internal components");
        }
    }

    /* access modifiers changed from: private */
    public static void deInitComponents() {
        try {
            COMPONENT_SERVICE.execute(new Runnable() {
                public final void run() {
                    try {
                        com.inmobi.commons.core.configs.b.a().c();
                        b a = b.a();
                        b.b.set(true);
                        a.a.execute(new Runnable() {
                            public final void run() {
                                if (b.this.j != null) {
                                    b.this.j.a();
                                    b.this.j = null;
                                }
                            }
                        });
                        com.inmobi.b.a a2 = com.inmobi.b.a.a();
                        com.inmobi.b.a.b.set(true);
                        a2.a.execute(new Runnable() {
                            public final void run() {
                                if (a.this.i != null) {
                                    a.this.i.a();
                                    a.this.i = null;
                                }
                            }
                        });
                        o.a().c();
                        com.inmobi.ads.c.b.d().b();
                        com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).b();
                        AssetStore a3 = AssetStore.a();
                        a3.d.set(true);
                        a3.c();
                    } catch (Exception e) {
                        InMobiSdk.TAG;
                        new StringBuilder("Encountered unexpected error in stopping SDK components; ").append(e.getMessage());
                        Logger.a(InternalLogLevel.ERROR, InMobiSdk.TAG, "SDK encountered unexpected error while stopping internal components");
                    }
                }
            });
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in stopping SDK components; ").append(e.getMessage());
            Logger.a(InternalLogLevel.ERROR, TAG, "SDK encountered unexpected error while stopping internal components");
        }
    }

    private static void resetMediaCache(final Context context) {
        final File a = a.a(context);
        COMPONENT_SERVICE.execute(new Runnable() {
            public final void run() {
                a.a(a);
                a.b(context);
            }
        });
        if (!a.mkdir()) {
            a.isDirectory();
        }
    }

    public static void setLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case NONE:
                Logger.a(InternalLogLevel.NONE);
                return;
            case ERROR:
                Logger.a(InternalLogLevel.ERROR);
                return;
            case DEBUG:
                Logger.a(InternalLogLevel.DEBUG);
                break;
        }
    }

    public static void setAge(int i) {
        g.a(i);
    }

    public static void setAgeGroup(AgeGroup ageGroup) {
        g.a(ageGroup.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setAreaCode(String str) {
        g.b(str);
    }

    public static void setPostalCode(String str) {
        g.c(str);
    }

    public static void setLocationWithCityStateCountry(String str, String str2, String str3) {
        g.d(str);
        g.e(str2);
        g.f(str3);
    }

    public static void setYearOfBirth(int i) {
        g.b(i);
    }

    public static void setGender(Gender gender) {
        g.g(gender.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setEducation(Education education) {
        g.h(education.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setLanguage(String str) {
        g.i(str);
    }

    public static void setInterests(String str) {
        g.j(str);
    }

    public static void setLocation(Location location) {
        g.a(location);
    }
}
