package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;

public class b {
    private static final String a;
    private static final String[] b = {"ANY", "ONE_ACCESSORY", "ONE_PEERAGENT"};
    private static b c;
    private final Context d;
    private String e = null;
    private HashMap<String, k> f;

    static {
        StringBuilder sb = new StringBuilder("[SA_SDK]");
        sb.append(b.class.getSimpleName());
        a = sb.toString();
    }

    private b(Context context) {
        this.d = context;
    }

    static synchronized b a(Context context) {
        synchronized (b.class) {
            if (c == null) {
                b bVar = new b(context);
                c = bVar;
                return bVar;
            }
            b bVar2 = c;
            return bVar2;
        }
    }

    private static void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder("Unable to parse the accessory services configuration file :");
            sb.append(str);
            throw new RuntimeException(sb.toString());
        }
    }

    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r4v2, types: [boolean] */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r4v24 */
    /* JADX WARNING: type inference failed for: r4v25 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        throw new java.lang.RuntimeException("Wrong Schema of xml file. Please check");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:113:0x0254 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0043 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v2, types: [boolean]
  assigns: []
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], boolean, ?[int, short, byte, char]]
  mth insns count: 271
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
    /* JADX WARNING: Removed duplicated region for block: B:111:0x024f A[DONT_GENERATE, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0264 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x010c A[SYNTHETIC, Splitter:B:52:0x010c] */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean a() {
        /*
            r18 = this;
            r1 = r18
            monitor-enter(r18)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0271 }
            r0.<init>()     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = a     // Catch:{ all -> 0x0271 }
            java.lang.String r3 = "Parse Accssory Service profile xml file"
            android.util.Log.i(r2, r3)     // Catch:{ all -> 0x0271 }
            android.content.Context r2 = r1.d     // Catch:{ all -> 0x0271 }
            com.samsung.android.sdk.accessory.l r2 = com.samsung.android.sdk.accessory.l.a(r2)     // Catch:{ all -> 0x0271 }
            byte[] r2 = r2.a()     // Catch:{ all -> 0x0271 }
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x0271 }
            int r4 = r2.length     // Catch:{ all -> 0x0271 }
            r5 = 0
            r3.<init>(r2, r5, r4)     // Catch:{ all -> 0x0271 }
            org.xmlpull.v1.XmlPullParserFactory r2 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ XmlPullParserException -> 0x0269 }
            r4 = 1
            r2.setNamespaceAware(r4)     // Catch:{ XmlPullParserException -> 0x0269 }
            org.xmlpull.v1.XmlPullParser r2 = r2.newPullParser()     // Catch:{ XmlPullParserException -> 0x0269 }
            if (r2 == 0) goto L_0x0036
            java.io.StringReader r6 = new java.io.StringReader     // Catch:{ XmlPullParserException -> 0x0269 }
            r6.<init>(r3)     // Catch:{ XmlPullParserException -> 0x0269 }
            r2.setInput(r6)     // Catch:{ XmlPullParserException -> 0x0269 }
        L_0x0036:
            r3 = 0
            if (r2 == 0) goto L_0x004b
            int r6 = r2.getEventType()     // Catch:{ XmlPullParserException -> 0x0043 }
            r9 = r3
            r10 = r9
            r11 = r10
            r13 = r11
            r15 = r13
            goto L_0x0051
        L_0x0043:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = "Wrong Schema of xml file. Please check"
            r0.<init>(r2)     // Catch:{ all -> 0x0271 }
            throw r0     // Catch:{ all -> 0x0271 }
        L_0x004b:
            r9 = r3
            r10 = r9
            r11 = r10
            r13 = r11
            r15 = r13
            r6 = 0
        L_0x0051:
            r14 = 0
            r16 = 0
        L_0x0054:
            if (r6 != r4) goto L_0x006b
            java.lang.String r0 = a     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = "End document"
            android.util.Log.i(r0, r2)     // Catch:{ all -> 0x0271 }
            java.util.HashMap<java.lang.String, com.samsung.android.sdk.accessory.k> r0 = r1.f     // Catch:{ all -> 0x0271 }
            if (r0 == 0) goto L_0x0063
            monitor-exit(r18)
            return r4
        L_0x0063:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = "Unable to parse the accessory services configuration file"
            r0.<init>(r2)     // Catch:{ all -> 0x0271 }
            throw r0     // Catch:{ all -> 0x0271 }
        L_0x006b:
            if (r6 != 0) goto L_0x0077
            java.lang.String r7 = a     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "Start document"
            android.util.Log.v(r7, r8)     // Catch:{ all -> 0x0271 }
        L_0x0074:
            r3 = r15
            goto L_0x024c
        L_0x0077:
            r7 = 2
            if (r6 != r7) goto L_0x0200
            if (r2 == 0) goto L_0x0200
            java.lang.String r8 = r2.getName()     // Catch:{ all -> 0x0271 }
            java.lang.String r12 = "application"
            boolean r12 = r8.equals(r12)     // Catch:{ all -> 0x0271 }
            if (r12 == 0) goto L_0x0098
            java.lang.String r7 = "name"
            java.lang.String r7 = r2.getAttributeValue(r3, r7)     // Catch:{ all -> 0x0271 }
            r1.e = r7     // Catch:{ all -> 0x0271 }
            java.lang.String r7 = "application"
            java.lang.String r8 = r1.e     // Catch:{ all -> 0x0271 }
            a(r7, r8)     // Catch:{ all -> 0x0271 }
            goto L_0x0074
        L_0x0098:
            java.lang.String r12 = "serviceProfile"
            boolean r12 = r8.equals(r12)     // Catch:{ all -> 0x0271 }
            if (r12 == 0) goto L_0x011b
            java.lang.String r8 = "serviceImpl"
            java.lang.String r8 = r2.getAttributeValue(r3, r8)     // Catch:{ all -> 0x0271 }
            java.lang.String r9 = "serviceImpl"
            a(r9, r8)     // Catch:{ all -> 0x0271 }
            java.lang.String r9 = "name"
            java.lang.String r9 = r2.getAttributeValue(r3, r9)     // Catch:{ all -> 0x0271 }
            java.lang.String r10 = "name"
            a(r10, r9)     // Catch:{ all -> 0x0271 }
            java.lang.String r10 = "role"
            java.lang.String r10 = r2.getAttributeValue(r3, r10)     // Catch:{ all -> 0x0271 }
            java.lang.String r11 = "role"
            a(r11, r10)     // Catch:{ all -> 0x0271 }
            java.lang.String r11 = "id"
            java.lang.String r11 = r2.getAttributeValue(r3, r11)     // Catch:{ all -> 0x0271 }
            java.lang.String r12 = "id"
            a(r12, r11)     // Catch:{ all -> 0x0271 }
            java.lang.String r12 = "version"
            java.lang.String r12 = r2.getAttributeValue(r3, r12)     // Catch:{ all -> 0x0271 }
            java.lang.String r13 = "version"
            a(r13, r12)     // Catch:{ all -> 0x0271 }
            java.lang.String r13 = "serviceLimit"
            java.lang.String r13 = r2.getAttributeValue(r3, r13)     // Catch:{ all -> 0x0271 }
            if (r13 == 0) goto L_0x0103
            java.lang.String[] r15 = b     // Catch:{ all -> 0x0271 }
            r15 = r15[r5]     // Catch:{ all -> 0x0271 }
            boolean r15 = r13.equalsIgnoreCase(r15)     // Catch:{ all -> 0x0271 }
            if (r15 == 0) goto L_0x00ea
            goto L_0x0103
        L_0x00ea:
            java.lang.String[] r15 = b     // Catch:{ all -> 0x0271 }
            r15 = r15[r4]     // Catch:{ all -> 0x0271 }
            boolean r15 = r13.equalsIgnoreCase(r15)     // Catch:{ all -> 0x0271 }
            if (r15 == 0) goto L_0x00f6
            r7 = 1
            goto L_0x0104
        L_0x00f6:
            java.lang.String[] r15 = b     // Catch:{ all -> 0x0271 }
            r15 = r15[r7]     // Catch:{ all -> 0x0271 }
            boolean r13 = r13.equalsIgnoreCase(r15)     // Catch:{ all -> 0x0271 }
            if (r13 == 0) goto L_0x0101
            goto L_0x0104
        L_0x0101:
            r7 = r14
            goto L_0x0104
        L_0x0103:
            r7 = 0
        L_0x0104:
            java.lang.String r13 = "serviceTimeout"
            java.lang.String r13 = r2.getAttributeValue(r3, r13)     // Catch:{ all -> 0x0271 }
            if (r13 == 0) goto L_0x0110
            int r13 = java.lang.Integer.parseInt(r13)     // Catch:{ NumberFormatException -> 0x0110 }
        L_0x0110:
            r14 = r7
            r15 = r8
            r13 = r12
            r17 = r10
            r10 = r9
            r9 = r11
            r11 = r17
            goto L_0x024d
        L_0x011b:
            java.lang.String r7 = "transport"
            boolean r7 = r8.equals(r7)     // Catch:{ all -> 0x0271 }
            if (r7 == 0) goto L_0x015d
            java.lang.String r7 = "type"
            java.lang.String r7 = r2.getAttributeValue(r3, r7)     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "Transport"
            a(r8, r7)     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "TRANSPORT_BT"
            boolean r8 = r7.equalsIgnoreCase(r8)     // Catch:{ all -> 0x0271 }
            if (r8 == 0) goto L_0x013c
            r7 = r16 | 1
        L_0x0138:
            r16 = r7
            goto L_0x024d
        L_0x013c:
            java.lang.String r8 = "TRANSPORT_WIFI"
            boolean r8 = r7.equalsIgnoreCase(r8)     // Catch:{ all -> 0x0271 }
            if (r8 == 0) goto L_0x0147
            r7 = r16 | 2
            goto L_0x0138
        L_0x0147:
            java.lang.String r8 = "TRANSPORT_BLE"
            boolean r8 = r7.equalsIgnoreCase(r8)     // Catch:{ all -> 0x0271 }
            if (r8 == 0) goto L_0x0152
            r7 = r16 | 4
            goto L_0x0138
        L_0x0152:
            java.lang.String r8 = "TRANSPORT_USB"
            boolean r7 = r7.equalsIgnoreCase(r8)     // Catch:{ all -> 0x0271 }
            if (r7 == 0) goto L_0x0074
            r7 = r16 | 8
            goto L_0x0138
        L_0x015d:
            java.lang.String r7 = "serviceChannel"
            boolean r7 = r8.equals(r7)     // Catch:{ all -> 0x0271 }
            if (r7 == 0) goto L_0x01e5
            java.lang.String r7 = "id"
            java.lang.String r7 = r2.getAttributeValue(r3, r7)     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "serviceChannel/id"
            a(r8, r7)     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "dataRate"
            java.lang.String r8 = r2.getAttributeValue(r3, r8)     // Catch:{ all -> 0x0271 }
            if (r8 != 0) goto L_0x0185
            java.lang.String r8 = a     // Catch:{ all -> 0x0271 }
            java.lang.String r12 = "Parsing new attribute failed.Trying to access old attribute"
            android.util.Log.i(r8, r12)     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "qosDataRate"
            java.lang.String r8 = r2.getAttributeValue(r3, r8)     // Catch:{ all -> 0x0271 }
        L_0x0185:
            java.lang.String r12 = "serviceChannel/qoSDataRate"
            a(r12, r8)     // Catch:{ all -> 0x0271 }
            java.lang.String r12 = "priority"
            java.lang.String r12 = r2.getAttributeValue(r3, r12)     // Catch:{ all -> 0x0271 }
            if (r12 != 0) goto L_0x0198
            java.lang.String r12 = "qosPriority"
            java.lang.String r12 = r2.getAttributeValue(r3, r12)     // Catch:{ all -> 0x0271 }
        L_0x0198:
            java.lang.String r4 = "serviceChannel/qoSPriority"
            a(r4, r12)     // Catch:{ all -> 0x0271 }
            java.lang.String r4 = "reliability"
            java.lang.String r4 = r2.getAttributeValue(r3, r4)     // Catch:{ all -> 0x0271 }
            if (r4 != 0) goto L_0x01ab
            java.lang.String r4 = "qosType"
            java.lang.String r4 = r2.getAttributeValue(r3, r4)     // Catch:{ all -> 0x0271 }
        L_0x01ab:
            java.lang.String r5 = "serviceChannel/qoSType"
            a(r5, r4)     // Catch:{ all -> 0x0271 }
            java.lang.String r5 = "Low"
            boolean r5 = r8.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
            if (r5 != 0) goto L_0x01bd
            java.lang.String r5 = "High"
            r8.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
        L_0x01bd:
            java.lang.String r5 = "Low"
            boolean r5 = r12.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
            if (r5 != 0) goto L_0x01d2
            java.lang.String r5 = "Medium"
            boolean r5 = r12.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
            if (r5 != 0) goto L_0x01d2
            java.lang.String r5 = "High"
            r12.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
        L_0x01d2:
            java.lang.String r5 = "enable"
            r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
            com.samsung.android.sdk.accessory.j r4 = new com.samsung.android.sdk.accessory.j     // Catch:{ all -> 0x0271 }
            int r5 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x0271 }
            r4.<init>(r5)     // Catch:{ all -> 0x0271 }
            r0.add(r4)     // Catch:{ all -> 0x0271 }
            goto L_0x0074
        L_0x01e5:
            java.lang.String r4 = "feature"
            boolean r4 = r8.equals(r4)     // Catch:{ all -> 0x0271 }
            if (r4 == 0) goto L_0x0074
            java.lang.String r4 = "type"
            java.lang.String r4 = r2.getAttributeValue(r3, r4)     // Catch:{ all -> 0x0271 }
            java.lang.String r5 = "Feature"
            a(r5, r4)     // Catch:{ all -> 0x0271 }
            java.lang.String r5 = "message"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0271 }
            goto L_0x0074
        L_0x0200:
            r4 = 3
            if (r6 != r4) goto L_0x0243
            if (r2 == 0) goto L_0x0243
            java.lang.String r4 = r2.getName()     // Catch:{ all -> 0x0271 }
            java.lang.String r5 = "serviceProfile"
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0271 }
            if (r4 == 0) goto L_0x0074
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0271 }
            r4.<init>()     // Catch:{ all -> 0x0271 }
            r4.addAll(r0)     // Catch:{ all -> 0x0271 }
            com.samsung.android.sdk.accessory.k r5 = new com.samsung.android.sdk.accessory.k     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = r1.e     // Catch:{ all -> 0x0271 }
            r7 = r5
            r12 = r15
            r3 = r15
            r15 = r16
            r16 = r4
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x0271 }
            java.util.HashMap<java.lang.String, com.samsung.android.sdk.accessory.k> r4 = r1.f     // Catch:{ all -> 0x0271 }
            if (r4 != 0) goto L_0x0232
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0271 }
            r4.<init>()     // Catch:{ all -> 0x0271 }
            r1.f = r4     // Catch:{ all -> 0x0271 }
        L_0x0232:
            java.util.HashMap<java.lang.String, com.samsung.android.sdk.accessory.k> r4 = r1.f     // Catch:{ all -> 0x0271 }
            r4.put(r3, r5)     // Catch:{ all -> 0x0271 }
            r0.clear()     // Catch:{ all -> 0x0271 }
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            goto L_0x024d
        L_0x0243:
            r3 = r15
            r4 = 4
            if (r6 != r4) goto L_0x024c
            if (r2 == 0) goto L_0x024c
            r2.getText()     // Catch:{ all -> 0x0271 }
        L_0x024c:
            r15 = r3
        L_0x024d:
            if (r2 == 0) goto L_0x0264
            int r6 = r2.next()     // Catch:{ XmlPullParserException -> 0x025c, Exception -> 0x0254 }
            goto L_0x0264
        L_0x0254:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = "Unable to parse the accessory services configuration file"
            r0.<init>(r2)     // Catch:{ all -> 0x0271 }
            throw r0     // Catch:{ all -> 0x0271 }
        L_0x025c:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = "Unable to parse the accessory services configuration file"
            r0.<init>(r2)     // Catch:{ all -> 0x0271 }
            throw r0     // Catch:{ all -> 0x0271 }
        L_0x0264:
            r3 = 0
            r4 = 1
            r5 = 0
            goto L_0x0054
        L_0x0269:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0271 }
            java.lang.String r2 = "XmlPullParserFactory Exception for Accssory Service profile XML file"
            r0.<init>(r2)     // Catch:{ all -> 0x0271 }
            throw r0     // Catch:{ all -> 0x0271 }
        L_0x0271:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.b.a():boolean");
    }

    /* access modifiers changed from: 0000 */
    public final synchronized k a(String str) {
        if (this.f == null) {
            a();
        }
        if (this.f.get(str) != null) {
            return (k) this.f.get(str);
        }
        String str2 = a;
        StringBuilder sb = new StringBuilder("fetchServicesDescription: Class not found in registered list");
        sb.append(str);
        Log.e(str2, sb.toString());
        return null;
    }
}
