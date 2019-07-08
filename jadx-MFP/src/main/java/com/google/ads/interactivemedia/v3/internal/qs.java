package com.google.ads.interactivemedia.v3.internal;

import android.util.Base64;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.ads.interactivemedia.v3.internal.fa.a;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: IMASDK */
public final class qs implements tv<qr> {
    private static final Pattern A = a("AUTOSELECT");
    private static final Pattern B = a(MessengerShareContentUtility.PREVIEW_DEFAULT);
    private static final Pattern C = a("FORCED");
    private static final Pattern D = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern E = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern F = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern a = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern b = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern c = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern d = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern e = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern f = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern g = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern h = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern i = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern j = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern k = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern l = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern m = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern n = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern o = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern p = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern q = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern r = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern s = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern t = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern u = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern v = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern w = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern x = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern y = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern z = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private final qn G;

    public qs() {
        this(qn.a);
    }

    public qs(qn qnVar) {
        this.G = qnVar;
    }

    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1 */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r6v3, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v11 */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: type inference failed for: r6v13 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x025d, code lost:
        if (r1.equals("SUBTITLES") == false) goto L_0x0261;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x025f, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0261, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0262, code lost:
        switch(r1) {
            case 0: goto L_0x02d6;
            case 1: goto L_0x02b7;
            case 2: goto L_0x0269;
            default: goto L_0x0265;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0269, code lost:
        r1 = a(r5, z, (java.util.Map<java.lang.String, java.lang.String>) r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0275, code lost:
        if (r1.startsWith("CC") == false) goto L_0x0286;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0277, code lost:
        r2 = com.google.android.exoplayer2.util.MimeTypes.APPLICATION_CEA608;
        r1 = java.lang.Integer.parseInt(r1.substring(2));
        r24 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0286, code lost:
        r2 = com.google.android.exoplayer2.util.MimeTypes.APPLICATION_CEA708;
        r1 = java.lang.Integer.parseInt(r1.substring(7));
        r24 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0294, code lost:
        if (r4 != null) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0296, code lost:
        r4 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x029b, code lost:
        r4.add(com.google.ads.interactivemedia.v3.internal.bs.a(r21, r6, (java.lang.String) null, r24, (java.lang.String) null, -1, r30, 0, r32, r1));
        r2 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02b7, code lost:
        r14.add(new com.google.ads.interactivemedia.v3.internal.qo(r7, com.google.ads.interactivemedia.v3.internal.bs.a(r21, r6, com.google.android.exoplayer2.util.MimeTypes.APPLICATION_M3U8, com.google.android.exoplayer2.util.MimeTypes.TEXT_VTT, (java.lang.String) null, -1, r30, r32), r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02d6, code lost:
        r25 = (java.lang.String) r3.get(r9);
        r2 = a(r5, d, null, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02e7, code lost:
        if (r2 == null) goto L_0x02f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02e9, code lost:
        r27 = java.lang.Integer.parseInt(com.google.ads.interactivemedia.v3.internal.vf.b(r2, "/")[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02f9, code lost:
        r27 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02fb, code lost:
        if (r25 == null) goto L_0x0304;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02fd, code lost:
        r24 = com.google.ads.interactivemedia.v3.internal.un.f(r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0304, code lost:
        r24 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0306, code lost:
        r2 = com.google.ads.interactivemedia.v3.internal.bs.a(r21, r6, com.google.android.exoplayer2.util.MimeTypes.APPLICATION_M3U8, r24, r25, -1, r27, -1, null, r30, 0, r32);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0316, code lost:
        if (r7 != null) goto L_0x031a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0318, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x031a, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x031f, code lost:
        if (r5 >= r12.size()) goto L_0x0334;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x032d, code lost:
        if (r7.equals(((com.google.ads.interactivemedia.v3.internal.qo) r12.get(r5)).a) == false) goto L_0x0331;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x032f, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0331, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0334, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0335, code lost:
        if (r5 == false) goto L_0x0338;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0338, code lost:
        r13.add(new com.google.ads.interactivemedia.v3.internal.qo(r7, r2, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0340, code lost:
        r2 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0342, code lost:
        r0 = r0 + 1;
        r9 = r72;
        r1 = r33;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x034b, code lost:
        r33 = r1;
        r34 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x034f, code lost:
        if (r16 == false) goto L_0x0358;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0351, code lost:
        r16 = java.util.Collections.emptyList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0358, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x035a, code lost:
        r9 = new com.google.ads.interactivemedia.v3.internal.qn(r10, r11, r12, r13, r14, r34, r16, r17, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0365, code lost:
        com.google.ads.interactivemedia.v3.internal.vf.a((java.io.Closeable) r33);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0368, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0070, code lost:
        r0.add(r2);
        r2 = new com.google.ads.interactivemedia.v3.internal.qt(r0, r1);
        r10 = r72.toString();
        r0 = new java.util.HashSet();
        r3 = new java.util.HashMap();
        r15 = new java.util.HashMap();
        r12 = new java.util.ArrayList();
        r13 = new java.util.ArrayList();
        r14 = new java.util.ArrayList();
        r9 = new java.util.ArrayList();
        r11 = new java.util.ArrayList();
        r16 = false;
        r17 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a8, code lost:
        r19 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ae, code lost:
        if (r2.a() == false) goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b0, code lost:
        r4 = r2.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ba, code lost:
        if (r4.startsWith("#EXT") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x07da, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00bc, code lost:
        r11.add(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x07db, code lost:
        r4 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c5, code lost:
        if (r4.startsWith("#EXT-X-DEFINE") == false) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c7, code lost:
        r15.put(a(r4, x, (java.util.Map<java.lang.String, java.lang.String>) r15), a(r4, D, (java.util.Map<java.lang.String, java.lang.String>) r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00de, code lost:
        if (r4.equals("#EXT-X-INDEPENDENT-SEGMENTS") == false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e0, code lost:
        r17 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00eb, code lost:
        if (r4.startsWith("#EXT-X-MEDIA") == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ed, code lost:
        r9.add(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f8, code lost:
        if (r4.startsWith("#EXT-X-STREAM-INF") == false) goto L_0x01a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fa, code lost:
        r16 = r16 | r4.contains("CLOSED-CAPTIONS=NONE");
        r5 = a(r4, c);
        r7 = a(r4, a, r8, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010e, code lost:
        if (r7 == null) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0110, code lost:
        r26 = java.lang.Integer.parseInt(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0117, code lost:
        r26 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0119, code lost:
        r5 = a(r4, e, r8, r15);
        r7 = a(r4, f, r8, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0125, code lost:
        if (r7 == null) goto L_0x0149;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0127, code lost:
        r7 = r7.split(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_X);
        r8 = java.lang.Integer.parseInt(r7[r6]);
        r7 = java.lang.Integer.parseInt(r7[1]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x013b, code lost:
        if (r8 <= 0) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x013d, code lost:
        if (r7 > 0) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0140, code lost:
        r19 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0143, code lost:
        r7 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0144, code lost:
        r28 = r7;
        r27 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0149, code lost:
        r27 = -1;
        r28 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x014d, code lost:
        r8 = a(r4, g, null, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0154, code lost:
        if (r8 == null) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0156, code lost:
        r29 = java.lang.Float.parseFloat(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x015d, code lost:
        r29 = -1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x015f, code lost:
        r4 = a(r4, b, null, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0165, code lost:
        if (r4 == null) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0167, code lost:
        if (r5 == null) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0169, code lost:
        r3.put(r4, com.google.ads.interactivemedia.v3.internal.vf.a(r5, 1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0171, code lost:
        r4 = a(r2.b(), (java.util.Map<java.lang.String, java.lang.String>) r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x017d, code lost:
        if (r0.add(r4) == false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x017f, code lost:
        r12.add(new com.google.ads.interactivemedia.v3.internal.qo(r4, com.google.ads.interactivemedia.v3.internal.bs.a(java.lang.Integer.toString(r12.size()), (java.lang.String) null, com.google.android.exoplayer2.util.MimeTypes.APPLICATION_M3U8, (java.lang.String) null, r5, r26, r27, r28, r29, null, 0, 0), ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a3, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01a8, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01ad, code lost:
        r0 = 0;
        r2 = null;
        r4 = null;
        r6 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b4, code lost:
        if (r0 >= r9.size()) goto L_0x034b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b6, code lost:
        r5 = (java.lang.String) r9.get(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c2, code lost:
        if (a(r5, B, (boolean) r6) == false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01c4, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01c6, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01cd, code lost:
        if (a(r5, C, (boolean) r6) == false) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01cf, code lost:
        r7 = r7 | 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d7, code lost:
        if (a(r5, A, (boolean) r6) == false) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01d9, code lost:
        r30 = r7 | 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01de, code lost:
        r30 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01e0, code lost:
        r7 = a(r5, t, null, r15);
        r6 = a(r5, x, (java.util.Map<java.lang.String, java.lang.String>) r15);
        r72 = r9;
        r32 = a(r5, w, null, r15);
        r9 = a(r5, y, null, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x020f, code lost:
        r33 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r1 = new java.lang.StringBuilder((java.lang.String.valueOf(r9).length() + 1) + java.lang.String.valueOf(r6).length());
        r1.append(r9);
        r1.append(":");
        r1.append(r6);
        r21 = r1.toString();
        r1 = a(r5, v, (java.util.Map<java.lang.String, java.lang.String>) r15);
        r8 = r1.hashCode();
        r34 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0236, code lost:
        if (r8 == -959297733) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x023b, code lost:
        if (r8 == -333210994) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0240, code lost:
        if (r8 == 62628790) goto L_0x0243;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0249, code lost:
        if (r1.equals("AUDIO") == false) goto L_0x0261;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x024b, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0253, code lost:
        if (r1.equals("CLOSED-CAPTIONS") == false) goto L_0x0261;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0255, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v2
  assigns: []
  uses: []
  mth insns count: 794
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
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0057 A[Catch:{ all -> 0x07fd }, LOOP:1: B:19:0x0057->B:325:0x0057, LOOP_START, PHI: r1 r6 
  PHI: (r1v2 java.io.BufferedReader) = (r1v0 java.io.BufferedReader), (r1v4 java.io.BufferedReader) binds: [B:18:0x0055, B:325:0x0057] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r6v1 ?) = (r6v0 ?), (r6v11 ?) binds: [B:18:0x0055, B:325:0x0057] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x07f0  */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.qr a(android.net.Uri r72, java.io.InputStream r73) throws java.io.IOException {
        /*
            r71 = this;
            java.io.BufferedReader r1 = new java.io.BufferedReader
            java.io.InputStreamReader r0 = new java.io.InputStreamReader
            r2 = r73
            r0.<init>(r2)
            r1.<init>(r0)
            java.util.ArrayDeque r0 = new java.util.ArrayDeque
            r0.<init>()
            int r2 = r1.read()     // Catch:{ all -> 0x07fd }
            r3 = 239(0xef, float:3.35E-43)
            r4 = 7
            r5 = 1
            r6 = 0
            if (r2 != r3) goto L_0x0034
            int r2 = r1.read()     // Catch:{ all -> 0x07fd }
            r3 = 187(0xbb, float:2.62E-43)
            if (r2 != r3) goto L_0x0032
            int r2 = r1.read()     // Catch:{ all -> 0x07fd }
            r3 = 191(0xbf, float:2.68E-43)
            if (r2 == r3) goto L_0x002d
            goto L_0x0032
        L_0x002d:
            int r2 = r1.read()     // Catch:{ all -> 0x07fd }
            goto L_0x0034
        L_0x0032:
            r2 = 0
            goto L_0x0055
        L_0x0034:
            int r2 = a(r1, r5, r2)     // Catch:{ all -> 0x07fd }
            r3 = r2
            r2 = 0
        L_0x003a:
            if (r2 >= r4) goto L_0x004d
            java.lang.String r7 = "#EXTM3U"
            char r7 = r7.charAt(r2)     // Catch:{ all -> 0x07fd }
            if (r3 == r7) goto L_0x0046
            r2 = 0
            goto L_0x0055
        L_0x0046:
            int r3 = r1.read()     // Catch:{ all -> 0x07fd }
            int r2 = r2 + 1
            goto L_0x003a
        L_0x004d:
            int r2 = a(r1, r6, r3)     // Catch:{ all -> 0x07fd }
            boolean r2 = com.google.ads.interactivemedia.v3.internal.vf.a(r2)     // Catch:{ all -> 0x07fd }
        L_0x0055:
            if (r2 == 0) goto L_0x07f0
        L_0x0057:
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x07fd }
            if (r2 == 0) goto L_0x07e4
            java.lang.String r2 = r2.trim()     // Catch:{ all -> 0x07fd }
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x07fd }
            if (r3 != 0) goto L_0x07de
            java.lang.String r3 = "#EXT-X-STREAM-INF"
            boolean r3 = r2.startsWith(r3)     // Catch:{ all -> 0x07fd }
            r8 = 0
            if (r3 == 0) goto L_0x0369
            r0.add(r2)     // Catch:{ all -> 0x07fd }
            com.google.ads.interactivemedia.v3.internal.qt r2 = new com.google.ads.interactivemedia.v3.internal.qt     // Catch:{ all -> 0x07fd }
            r2.<init>(r0, r1)     // Catch:{ all -> 0x07fd }
            java.lang.String r10 = r72.toString()     // Catch:{ all -> 0x07fd }
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x07fd }
            r0.<init>()     // Catch:{ all -> 0x07fd }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x07fd }
            r3.<init>()     // Catch:{ all -> 0x07fd }
            java.util.HashMap r15 = new java.util.HashMap     // Catch:{ all -> 0x07fd }
            r15.<init>()     // Catch:{ all -> 0x07fd }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x07fd }
            r12.<init>()     // Catch:{ all -> 0x07fd }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x07fd }
            r13.<init>()     // Catch:{ all -> 0x07fd }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x07fd }
            r14.<init>()     // Catch:{ all -> 0x07fd }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x07fd }
            r9.<init>()     // Catch:{ all -> 0x07fd }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x07fd }
            r11.<init>()     // Catch:{ all -> 0x07fd }
            r16 = 0
            r17 = 0
        L_0x00a8:
            boolean r18 = r2.a()     // Catch:{ all -> 0x07fd }
            r19 = -1
            if (r18 == 0) goto L_0x01ad
            java.lang.String r4 = r2.b()     // Catch:{ all -> 0x07fd }
            java.lang.String r7 = "#EXT"
            boolean r7 = r4.startsWith(r7)     // Catch:{ all -> 0x07fd }
            if (r7 == 0) goto L_0x00bf
            r11.add(r4)     // Catch:{ all -> 0x07fd }
        L_0x00bf:
            java.lang.String r7 = "#EXT-X-DEFINE"
            boolean r7 = r4.startsWith(r7)     // Catch:{ all -> 0x07fd }
            if (r7 == 0) goto L_0x00d8
            java.util.regex.Pattern r7 = x     // Catch:{ all -> 0x07fd }
            java.lang.String r7 = a(r4, r7, r15)     // Catch:{ all -> 0x07fd }
            java.util.regex.Pattern r5 = D     // Catch:{ all -> 0x07fd }
            java.lang.String r4 = a(r4, r5, r15)     // Catch:{ all -> 0x07fd }
            r15.put(r7, r4)     // Catch:{ all -> 0x07fd }
            goto L_0x01a8
        L_0x00d8:
            java.lang.String r5 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r5 = r4.equals(r5)     // Catch:{ all -> 0x07fd }
            if (r5 == 0) goto L_0x00e5
            r4 = 7
            r5 = 1
            r17 = 1
            goto L_0x00a8
        L_0x00e5:
            java.lang.String r5 = "#EXT-X-MEDIA"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x07fd }
            if (r5 == 0) goto L_0x00f2
            r9.add(r4)     // Catch:{ all -> 0x07fd }
            goto L_0x01a8
        L_0x00f2:
            java.lang.String r5 = "#EXT-X-STREAM-INF"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x07fd }
            if (r5 == 0) goto L_0x01a8
            java.lang.String r5 = "CLOSED-CAPTIONS=NONE"
            boolean r5 = r4.contains(r5)     // Catch:{ all -> 0x07fd }
            r16 = r16 | r5
            java.util.regex.Pattern r5 = c     // Catch:{ all -> 0x07fd }
            int r5 = a(r4, r5)     // Catch:{ all -> 0x07fd }
            java.util.regex.Pattern r7 = a     // Catch:{ all -> 0x07fd }
            java.lang.String r7 = a(r4, r7, r8, r15)     // Catch:{ all -> 0x07fd }
            if (r7 == 0) goto L_0x0117
            int r5 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x07fd }
            r26 = r5
            goto L_0x0119
        L_0x0117:
            r26 = r5
        L_0x0119:
            java.util.regex.Pattern r5 = e     // Catch:{ all -> 0x07fd }
            java.lang.String r5 = a(r4, r5, r8, r15)     // Catch:{ all -> 0x07fd }
            java.util.regex.Pattern r7 = f     // Catch:{ all -> 0x07fd }
            java.lang.String r7 = a(r4, r7, r8, r15)     // Catch:{ all -> 0x07fd }
            if (r7 == 0) goto L_0x0149
            java.lang.String r8 = "x"
            java.lang.String[] r7 = r7.split(r8)     // Catch:{ all -> 0x07fd }
            r8 = r7[r6]     // Catch:{ all -> 0x07fd }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ all -> 0x07fd }
            r20 = 1
            r7 = r7[r20]     // Catch:{ all -> 0x07fd }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x07fd }
            if (r8 <= 0) goto L_0x0143
            if (r7 > 0) goto L_0x0140
            goto L_0x0143
        L_0x0140:
            r19 = r8
            goto L_0x0144
        L_0x0143:
            r7 = -1
        L_0x0144:
            r28 = r7
            r27 = r19
            goto L_0x014d
        L_0x0149:
            r27 = -1
            r28 = -1
        L_0x014d:
            java.util.regex.Pattern r8 = g     // Catch:{ all -> 0x07fd }
            r7 = 0
            java.lang.String r8 = a(r4, r8, r7, r15)     // Catch:{ all -> 0x07fd }
            if (r8 == 0) goto L_0x015d
            float r8 = java.lang.Float.parseFloat(r8)     // Catch:{ all -> 0x07fd }
            r29 = r8
            goto L_0x015f
        L_0x015d:
            r29 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x015f:
            java.util.regex.Pattern r8 = b     // Catch:{ all -> 0x07fd }
            java.lang.String r4 = a(r4, r8, r7, r15)     // Catch:{ all -> 0x07fd }
            if (r4 == 0) goto L_0x0171
            if (r5 == 0) goto L_0x0171
            r7 = 1
            java.lang.String r8 = com.google.ads.interactivemedia.v3.internal.vf.a(r5, r7)     // Catch:{ all -> 0x07fd }
            r3.put(r4, r8)     // Catch:{ all -> 0x07fd }
        L_0x0171:
            java.lang.String r4 = r2.b()     // Catch:{ all -> 0x07fd }
            java.lang.String r4 = a(r4, r15)     // Catch:{ all -> 0x07fd }
            boolean r7 = r0.add(r4)     // Catch:{ all -> 0x07fd }
            if (r7 == 0) goto L_0x01a3
            int r7 = r12.size()     // Catch:{ all -> 0x07fd }
            java.lang.String r21 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x07fd }
            r22 = 0
            java.lang.String r23 = "application/x-mpegURL"
            r24 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r25 = r5
            com.google.ads.interactivemedia.v3.internal.bs r5 = com.google.ads.interactivemedia.v3.internal.bs.a(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)     // Catch:{ all -> 0x07fd }
            com.google.ads.interactivemedia.v3.internal.qo r7 = new com.google.ads.interactivemedia.v3.internal.qo     // Catch:{ all -> 0x07fd }
            java.lang.String r8 = ""
            r7.<init>(r4, r5, r8)     // Catch:{ all -> 0x07fd }
            r12.add(r7)     // Catch:{ all -> 0x07fd }
        L_0x01a3:
            r4 = 7
            r5 = 1
            r8 = 0
            goto L_0x00a8
        L_0x01a8:
            r4 = 7
            r5 = 1
            r8 = 0
            goto L_0x00a8
        L_0x01ad:
            r0 = 0
            r2 = 0
            r4 = 0
        L_0x01b0:
            int r5 = r9.size()     // Catch:{ all -> 0x07fd }
            if (r0 >= r5) goto L_0x034b
            java.lang.Object r5 = r9.get(r0)     // Catch:{ all -> 0x07fd }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x07fd }
            java.util.regex.Pattern r7 = B     // Catch:{ all -> 0x07fd }
            boolean r7 = a(r5, r7, r6)     // Catch:{ all -> 0x07fd }
            if (r7 == 0) goto L_0x01c6
            r7 = 1
            goto L_0x01c7
        L_0x01c6:
            r7 = 0
        L_0x01c7:
            java.util.regex.Pattern r8 = C     // Catch:{ all -> 0x07fd }
            boolean r8 = a(r5, r8, r6)     // Catch:{ all -> 0x07fd }
            if (r8 == 0) goto L_0x01d1
            r7 = r7 | 2
        L_0x01d1:
            java.util.regex.Pattern r8 = A     // Catch:{ all -> 0x07fd }
            boolean r8 = a(r5, r8, r6)     // Catch:{ all -> 0x07fd }
            if (r8 == 0) goto L_0x01de
            r7 = r7 | 4
            r30 = r7
            goto L_0x01e0
        L_0x01de:
            r30 = r7
        L_0x01e0:
            java.util.regex.Pattern r7 = t     // Catch:{ all -> 0x07fd }
            r8 = 0
            java.lang.String r7 = a(r5, r7, r8, r15)     // Catch:{ all -> 0x07fd }
            java.util.regex.Pattern r6 = x     // Catch:{ all -> 0x07fd }
            java.lang.String r6 = a(r5, r6, r15)     // Catch:{ all -> 0x07fd }
            r72 = r9
            java.util.regex.Pattern r9 = w     // Catch:{ all -> 0x07fd }
            java.lang.String r32 = a(r5, r9, r8, r15)     // Catch:{ all -> 0x07fd }
            java.util.regex.Pattern r9 = y     // Catch:{ all -> 0x07fd }
            java.lang.String r9 = a(r5, r9, r8, r15)     // Catch:{ all -> 0x07fd }
            java.lang.String r8 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x07fd }
            int r8 = r8.length()     // Catch:{ all -> 0x07fd }
            r20 = 1
            int r8 = r8 + 1
            java.lang.String r21 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x07fd }
            int r21 = r21.length()     // Catch:{ all -> 0x07fd }
            int r8 = r8 + r21
            r33 = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x07da }
            r1.<init>(r8)     // Catch:{ all -> 0x07da }
            r1.append(r9)     // Catch:{ all -> 0x07da }
            java.lang.String r8 = ":"
            r1.append(r8)     // Catch:{ all -> 0x07da }
            r1.append(r6)     // Catch:{ all -> 0x07da }
            java.lang.String r21 = r1.toString()     // Catch:{ all -> 0x07da }
            java.util.regex.Pattern r1 = v     // Catch:{ all -> 0x07da }
            java.lang.String r1 = a(r5, r1, r15)     // Catch:{ all -> 0x07da }
            int r8 = r1.hashCode()     // Catch:{ all -> 0x07da }
            r34 = r2
            r2 = -959297733(0xffffffffc6d2473b, float:-26915.615)
            if (r8 == r2) goto L_0x0257
            r2 = -333210994(0xffffffffec239a8e, float:-7.911391E26)
            if (r8 == r2) goto L_0x024d
            r2 = 62628790(0x3bba3b6, float:1.1028458E-36)
            if (r8 == r2) goto L_0x0243
            goto L_0x0261
        L_0x0243:
            java.lang.String r2 = "AUDIO"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x07da }
            if (r1 == 0) goto L_0x0261
            r1 = 0
            goto L_0x0262
        L_0x024d:
            java.lang.String r2 = "CLOSED-CAPTIONS"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x07da }
            if (r1 == 0) goto L_0x0261
            r1 = 2
            goto L_0x0262
        L_0x0257:
            java.lang.String r2 = "SUBTITLES"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x07da }
            if (r1 == 0) goto L_0x0261
            r1 = 1
            goto L_0x0262
        L_0x0261:
            r1 = -1
        L_0x0262:
            switch(r1) {
                case 0: goto L_0x02d6;
                case 1: goto L_0x02b7;
                case 2: goto L_0x0269;
                default: goto L_0x0265;
            }     // Catch:{ all -> 0x07da }
        L_0x0265:
            r1 = 7
            r8 = 2
            goto L_0x0340
        L_0x0269:
            java.util.regex.Pattern r1 = z     // Catch:{ all -> 0x07da }
            java.lang.String r1 = a(r5, r1, r15)     // Catch:{ all -> 0x07da }
            java.lang.String r2 = "CC"
            boolean r2 = r1.startsWith(r2)     // Catch:{ all -> 0x07da }
            if (r2 == 0) goto L_0x0286
            java.lang.String r2 = "application/cea-608"
            r8 = 2
            java.lang.String r1 = r1.substring(r8)     // Catch:{ all -> 0x07da }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x07da }
            r24 = r2
            r5 = 7
            goto L_0x0294
        L_0x0286:
            r8 = 2
            java.lang.String r2 = "application/cea-708"
            r5 = 7
            java.lang.String r1 = r1.substring(r5)     // Catch:{ all -> 0x07da }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x07da }
            r24 = r2
        L_0x0294:
            if (r4 != 0) goto L_0x029b
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x07da }
            r4.<init>()     // Catch:{ all -> 0x07da }
        L_0x029b:
            r23 = 0
            r25 = 0
            r26 = -1
            r28 = 0
            r22 = r6
            r27 = r30
            r29 = r32
            r30 = r1
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)     // Catch:{ all -> 0x07da }
            r4.add(r1)     // Catch:{ all -> 0x07da }
            r2 = r34
            r1 = 7
            goto L_0x0342
        L_0x02b7:
            r5 = 7
            r8 = 2
            java.lang.String r23 = "application/x-mpegURL"
            java.lang.String r24 = "text/vtt"
            r25 = 0
            r26 = -1
            r22 = r6
            r27 = r30
            r28 = r32
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ all -> 0x07da }
            com.google.ads.interactivemedia.v3.internal.qo r2 = new com.google.ads.interactivemedia.v3.internal.qo     // Catch:{ all -> 0x07da }
            r2.<init>(r7, r1, r6)     // Catch:{ all -> 0x07da }
            r14.add(r2)     // Catch:{ all -> 0x07da }
            r1 = 7
            goto L_0x0340
        L_0x02d6:
            r1 = 7
            r8 = 2
            java.lang.Object r2 = r3.get(r9)     // Catch:{ all -> 0x07da }
            r25 = r2
            java.lang.String r25 = (java.lang.String) r25     // Catch:{ all -> 0x07da }
            java.util.regex.Pattern r2 = d     // Catch:{ all -> 0x07da }
            r9 = 0
            java.lang.String r2 = a(r5, r2, r9, r15)     // Catch:{ all -> 0x07da }
            if (r2 == 0) goto L_0x02f9
            java.lang.String r5 = "/"
            java.lang.String[] r2 = com.google.ads.interactivemedia.v3.internal.vf.b(r2, r5)     // Catch:{ all -> 0x07da }
            r5 = 0
            r2 = r2[r5]     // Catch:{ all -> 0x07da }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ all -> 0x07da }
            r27 = r2
            goto L_0x02fb
        L_0x02f9:
            r27 = -1
        L_0x02fb:
            if (r25 == 0) goto L_0x0304
            java.lang.String r2 = com.google.ads.interactivemedia.v3.internal.un.f(r25)     // Catch:{ all -> 0x07da }
            r24 = r2
            goto L_0x0306
        L_0x0304:
            r24 = 0
        L_0x0306:
            java.lang.String r23 = "application/x-mpegURL"
            r26 = -1
            r28 = -1
            r29 = 0
            r31 = 0
            r22 = r6
            com.google.ads.interactivemedia.v3.internal.bs r2 = com.google.ads.interactivemedia.v3.internal.bs.a(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)     // Catch:{ all -> 0x07da }
            if (r7 != 0) goto L_0x031a
            r5 = 1
            goto L_0x0335
        L_0x031a:
            r5 = 0
        L_0x031b:
            int r9 = r12.size()     // Catch:{ all -> 0x07da }
            if (r5 >= r9) goto L_0x0334
            java.lang.Object r9 = r12.get(r5)     // Catch:{ all -> 0x07da }
            com.google.ads.interactivemedia.v3.internal.qo r9 = (com.google.ads.interactivemedia.v3.internal.qo) r9     // Catch:{ all -> 0x07da }
            java.lang.String r9 = r9.a     // Catch:{ all -> 0x07da }
            boolean r9 = r7.equals(r9)     // Catch:{ all -> 0x07da }
            if (r9 == 0) goto L_0x0331
            r5 = 1
            goto L_0x0335
        L_0x0331:
            int r5 = r5 + 1
            goto L_0x031b
        L_0x0334:
            r5 = 0
        L_0x0335:
            if (r5 == 0) goto L_0x0338
            goto L_0x0342
        L_0x0338:
            com.google.ads.interactivemedia.v3.internal.qo r5 = new com.google.ads.interactivemedia.v3.internal.qo     // Catch:{ all -> 0x07da }
            r5.<init>(r7, r2, r6)     // Catch:{ all -> 0x07da }
            r13.add(r5)     // Catch:{ all -> 0x07da }
        L_0x0340:
            r2 = r34
        L_0x0342:
            int r0 = r0 + 1
            r9 = r72
            r1 = r33
            r6 = 0
            goto L_0x01b0
        L_0x034b:
            r33 = r1
            r34 = r2
            if (r16 == 0) goto L_0x0358
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x07da }
            r16 = r0
            goto L_0x035a
        L_0x0358:
            r16 = r4
        L_0x035a:
            com.google.ads.interactivemedia.v3.internal.qn r0 = new com.google.ads.interactivemedia.v3.internal.qn     // Catch:{ all -> 0x07da }
            r9 = r0
            r1 = r15
            r15 = r34
            r18 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x07da }
            com.google.ads.interactivemedia.v3.internal.vf.a(r33)
            return r0
        L_0x0369:
            r33 = r1
            r1 = 7
            r8 = 2
            java.lang.String r3 = "#EXT-X-TARGETDURATION"
            boolean r3 = r2.startsWith(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXT-X-MEDIA-SEQUENCE"
            boolean r3 = r2.startsWith(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXTINF"
            boolean r3 = r2.startsWith(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXT-X-KEY"
            boolean r3 = r2.startsWith(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXT-X-BYTERANGE"
            boolean r3 = r2.startsWith(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXT-X-DISCONTINUITY"
            boolean r3 = r2.equals(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXT-X-DISCONTINUITY-SEQUENCE"
            boolean r3 = r2.equals(r3)     // Catch:{ all -> 0x07da }
            if (r3 != 0) goto L_0x03b8
            java.lang.String r3 = "#EXT-X-ENDLIST"
            boolean r3 = r2.equals(r3)     // Catch:{ all -> 0x07da }
            if (r3 == 0) goto L_0x03ae
            goto L_0x03b8
        L_0x03ae:
            r0.add(r2)     // Catch:{ all -> 0x07da }
            r1 = r33
            r4 = 7
            r5 = 1
            r6 = 0
            goto L_0x0057
        L_0x03b8:
            r0.add(r2)     // Catch:{ all -> 0x07da }
            r2 = r71
            com.google.ads.interactivemedia.v3.internal.qn r1 = r2.G     // Catch:{ all -> 0x07da }
            com.google.ads.interactivemedia.v3.internal.qt r3 = new com.google.ads.interactivemedia.v3.internal.qt     // Catch:{ all -> 0x07da }
            r4 = r33
            r3.<init>(r0, r4)     // Catch:{ all -> 0x07fb }
            java.lang.String r35 = r72.toString()     // Catch:{ all -> 0x07fb }
            boolean r5 = r1.p     // Catch:{ all -> 0x07fb }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x07fb }
            r0.<init>()     // Catch:{ all -> 0x07fb }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x07fb }
            r6.<init>()     // Catch:{ all -> 0x07fb }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x07fb }
            r7.<init>()     // Catch:{ all -> 0x07fb }
            java.lang.String r9 = ""
            java.util.TreeMap r10 = new java.util.TreeMap     // Catch:{ all -> 0x07fb }
            r10.<init>()     // Catch:{ all -> 0x07fb }
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r48 = r5
            r55 = r9
            r37 = r11
            r46 = r37
            r5 = 0
            r8 = 0
            r9 = 0
            r11 = 0
            r17 = -1
            r19 = 0
            r21 = 0
            r30 = 0
            r32 = 0
            r34 = 0
            r39 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r45 = 1
            r49 = 0
            r51 = 0
            r52 = 0
            r68 = 0
            r69 = 0
        L_0x0414:
            boolean r22 = r3.a()     // Catch:{ all -> 0x07fb }
            if (r22 == 0) goto L_0x07c0
            java.lang.String r13 = r3.b()     // Catch:{ all -> 0x07fb }
            java.lang.String r14 = "#EXT"
            boolean r14 = r13.startsWith(r14)     // Catch:{ all -> 0x07fb }
            if (r14 == 0) goto L_0x0429
            r7.add(r13)     // Catch:{ all -> 0x07fb }
        L_0x0429:
            java.lang.String r14 = "#EXT-X-PLAYLIST-TYPE"
            boolean r14 = r13.startsWith(r14)     // Catch:{ all -> 0x07fb }
            if (r14 == 0) goto L_0x044d
            java.util.regex.Pattern r14 = j     // Catch:{ all -> 0x07fb }
            java.lang.String r13 = a(r13, r14, r0)     // Catch:{ all -> 0x07fb }
            java.lang.String r14 = "VOD"
            boolean r14 = r14.equals(r13)     // Catch:{ all -> 0x07fb }
            if (r14 == 0) goto L_0x0442
            r34 = 1
            goto L_0x0414
        L_0x0442:
            java.lang.String r14 = "EVENT"
            boolean r13 = r14.equals(r13)     // Catch:{ all -> 0x07fb }
            if (r13 == 0) goto L_0x0414
            r34 = 2
            goto L_0x0414
        L_0x044d:
            java.lang.String r14 = "#EXT-X-START"
            boolean r14 = r13.startsWith(r14)     // Catch:{ all -> 0x07fb }
            r22 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            if (r14 == 0) goto L_0x0466
            java.util.regex.Pattern r14 = n     // Catch:{ all -> 0x07fb }
            double r13 = b(r13, r14)     // Catch:{ all -> 0x07fb }
            double r13 = r13 * r22
            long r13 = (long) r13     // Catch:{ all -> 0x07fb }
            r37 = r13
            goto L_0x0414
        L_0x0466:
            java.lang.String r14 = "#EXT-X-MAP"
            boolean r14 = r13.startsWith(r14)     // Catch:{ all -> 0x07fb }
            if (r14 == 0) goto L_0x04ca
            java.util.regex.Pattern r14 = t     // Catch:{ all -> 0x07fb }
            java.lang.String r22 = a(r13, r14, r0)     // Catch:{ all -> 0x07fb }
            java.util.regex.Pattern r14 = p     // Catch:{ all -> 0x07fb }
            r15 = 0
            java.lang.String r13 = a(r13, r14, r15, r0)     // Catch:{ all -> 0x07fb }
            if (r13 == 0) goto L_0x04a0
            java.lang.String r14 = "@"
            java.lang.String[] r13 = r13.split(r14)     // Catch:{ all -> 0x07fb }
            r14 = 0
            r15 = r13[r14]     // Catch:{ all -> 0x07fb }
            long r14 = java.lang.Long.parseLong(r15)     // Catch:{ all -> 0x07fb }
            int r2 = r13.length     // Catch:{ all -> 0x07fb }
            r16 = r3
            r3 = 1
            if (r2 <= r3) goto L_0x049b
            r2 = r13[r3]     // Catch:{ all -> 0x07fb }
            long r11 = java.lang.Long.parseLong(r2)     // Catch:{ all -> 0x07fb }
            r23 = r11
            r25 = r14
            goto L_0x04a6
        L_0x049b:
            r23 = r11
            r25 = r14
            goto L_0x04a6
        L_0x04a0:
            r16 = r3
            r23 = r11
            r25 = r17
        L_0x04a6:
            if (r5 == 0) goto L_0x04b3
            if (r9 == 0) goto L_0x04ab
            goto L_0x04b3
        L_0x04ab:
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ all -> 0x07fb }
            java.lang.String r1 = "The encryption IV attribute must be present when an initialization segment is encrypted with METHOD=AES-128."
            r0.<init>(r1)     // Catch:{ all -> 0x07fb }
            throw r0     // Catch:{ all -> 0x07fb }
        L_0x04b3:
            com.google.ads.interactivemedia.v3.internal.qq r2 = new com.google.ads.interactivemedia.v3.internal.qq     // Catch:{ all -> 0x07fb }
            r21 = r2
            r27 = r5
            r28 = r9
            r21.<init>(r22, r23, r25, r27, r28)     // Catch:{ all -> 0x07fb }
            r21 = r2
            r3 = r16
            r2 = r71
            r11 = 0
            r17 = -1
            goto L_0x0414
        L_0x04ca:
            r16 = r3
            java.lang.String r2 = "#EXT-X-TARGETDURATION"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x04e6
            java.util.regex.Pattern r2 = h     // Catch:{ all -> 0x07fb }
            int r2 = a(r13, r2)     // Catch:{ all -> 0x07fb }
            long r2 = (long) r2     // Catch:{ all -> 0x07fb }
            r13 = 1000000(0xf4240, double:4.940656E-318)
            long r46 = r2 * r13
            r3 = r16
            r2 = r71
            goto L_0x0414
        L_0x04e6:
            java.lang.String r2 = "#EXT-X-MEDIA-SEQUENCE"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0504
            java.util.regex.Pattern r2 = k     // Catch:{ all -> 0x07fb }
            java.util.Map r3 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = a(r13, r2, r3)     // Catch:{ all -> 0x07fb }
            long r52 = java.lang.Long.parseLong(r2)     // Catch:{ all -> 0x07fb }
            r3 = r16
            r43 = r52
            r2 = r71
            goto L_0x0414
        L_0x0504:
            java.lang.String r2 = "#EXT-X-VERSION"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0518
            java.util.regex.Pattern r2 = i     // Catch:{ all -> 0x07fb }
            int r45 = a(r13, r2)     // Catch:{ all -> 0x07fb }
            r3 = r16
            r2 = r71
            goto L_0x0414
        L_0x0518:
            java.lang.String r2 = "#EXT-X-DEFINE"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x055b
            java.util.regex.Pattern r2 = E     // Catch:{ all -> 0x07fb }
            r3 = 0
            java.lang.String r2 = a(r13, r2, r3, r0)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0541
            java.util.Map<java.lang.String, java.lang.String> r3 = r1.g     // Catch:{ all -> 0x07fb }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x07fb }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x07fb }
            if (r3 == 0) goto L_0x0536
            r0.put(r2, r3)     // Catch:{ all -> 0x07fb }
        L_0x0536:
            r24 = r1
            r72 = r9
            r25 = r10
            r3 = 0
            r22 = -1
            goto L_0x07b4
        L_0x0541:
            java.util.regex.Pattern r2 = x     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = a(r13, r2, r0)     // Catch:{ all -> 0x07fb }
            java.util.regex.Pattern r3 = D     // Catch:{ all -> 0x07fb }
            java.lang.String r3 = a(r13, r3, r0)     // Catch:{ all -> 0x07fb }
            r0.put(r2, r3)     // Catch:{ all -> 0x07fb }
            r24 = r1
            r72 = r9
            r25 = r10
            r3 = 0
            r22 = -1
            goto L_0x07b4
        L_0x055b:
            java.lang.String r2 = "#EXTINF"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x057c
            java.util.regex.Pattern r2 = l     // Catch:{ all -> 0x07fb }
            double r2 = b(r13, r2)     // Catch:{ all -> 0x07fb }
            double r2 = r2 * r22
            long r2 = (long) r2     // Catch:{ all -> 0x07fb }
            java.util.regex.Pattern r14 = m     // Catch:{ all -> 0x07fb }
            java.lang.String r15 = ""
            java.lang.String r55 = a(r13, r14, r15, r0)     // Catch:{ all -> 0x07fb }
            r69 = r2
            r3 = r16
            r2 = r71
            goto L_0x0414
        L_0x057c:
            java.lang.String r2 = "#EXT-X-KEY"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0638
            java.util.regex.Pattern r2 = q     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = a(r13, r2, r0)     // Catch:{ all -> 0x07fb }
            java.util.regex.Pattern r3 = r     // Catch:{ all -> 0x07fb }
            java.lang.String r5 = "identity"
            java.lang.String r3 = a(r13, r3, r5, r0)     // Catch:{ all -> 0x07fb }
            java.lang.String r5 = "NONE"
            boolean r5 = r5.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r5 == 0) goto L_0x05a7
            r10.clear()     // Catch:{ all -> 0x07fb }
            r3 = r16
            r2 = r71
            r5 = 0
            r9 = 0
            r32 = 0
            goto L_0x0414
        L_0x05a7:
            java.util.regex.Pattern r5 = u     // Catch:{ all -> 0x07fb }
            r9 = 0
            java.lang.String r5 = a(r13, r5, r9, r0)     // Catch:{ all -> 0x07fb }
            java.lang.String r9 = "identity"
            boolean r9 = r9.equals(r3)     // Catch:{ all -> 0x07fb }
            if (r9 == 0) goto L_0x05cc
            java.lang.String r3 = "AES-128"
            boolean r2 = r3.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0630
            java.util.regex.Pattern r2 = t     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = a(r13, r2, r0)     // Catch:{ all -> 0x07fb }
            r9 = r5
            r3 = r16
            r5 = r2
            r2 = r71
            goto L_0x0414
        L_0x05cc:
            if (r8 != 0) goto L_0x05e6
            java.lang.String r8 = "SAMPLE-AES-CENC"
            boolean r8 = r8.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r8 != 0) goto L_0x05e3
            java.lang.String r8 = "SAMPLE-AES-CTR"
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x05df
            goto L_0x05e3
        L_0x05df:
            java.lang.String r2 = "cbcs"
            r8 = r2
            goto L_0x05e6
        L_0x05e3:
            java.lang.String r2 = "cenc"
            r8 = r2
        L_0x05e6:
            java.lang.String r2 = "com.microsoft.playready"
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0625
            java.util.regex.Pattern r2 = s     // Catch:{ all -> 0x07fb }
            java.lang.String r9 = "1"
            java.lang.String r2 = a(r13, r2, r9, r0)     // Catch:{ all -> 0x07fb }
            java.lang.String r9 = "1"
            boolean r2 = r9.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 != 0) goto L_0x0600
            r9 = 0
            goto L_0x0629
        L_0x0600:
            java.util.regex.Pattern r2 = t     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = a(r13, r2, r0)     // Catch:{ all -> 0x07fb }
            r9 = 44
            int r9 = r2.indexOf(r9)     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = r2.substring(r9)     // Catch:{ all -> 0x07fb }
            r9 = 0
            byte[] r2 = android.util.Base64.decode(r2, r9)     // Catch:{ all -> 0x07fb }
            java.util.UUID r9 = com.google.ads.interactivemedia.v3.internal.at.e     // Catch:{ all -> 0x07fb }
            byte[] r2 = com.google.ads.interactivemedia.v3.internal.ho.a(r9, r2)     // Catch:{ all -> 0x07fb }
            com.google.ads.interactivemedia.v3.internal.fa$a r9 = new com.google.ads.interactivemedia.v3.internal.fa$a     // Catch:{ all -> 0x07fb }
            java.util.UUID r13 = com.google.ads.interactivemedia.v3.internal.at.e     // Catch:{ all -> 0x07fb }
            java.lang.String r14 = "video/mp4"
            r9.<init>(r13, r14, r2)     // Catch:{ all -> 0x07fb }
            goto L_0x0629
        L_0x0625:
            com.google.ads.interactivemedia.v3.internal.fa$a r9 = a(r13, r3, r0)     // Catch:{ all -> 0x07fb }
        L_0x0629:
            if (r9 == 0) goto L_0x0630
            r10.put(r3, r9)     // Catch:{ all -> 0x07fb }
            r32 = 0
        L_0x0630:
            r9 = r5
            r3 = r16
            r2 = r71
            r5 = 0
            goto L_0x0414
        L_0x0638:
            java.lang.String r2 = "#EXT-X-BYTERANGE"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0664
            java.util.regex.Pattern r2 = o     // Catch:{ all -> 0x07fb }
            java.lang.String r2 = a(r13, r2, r0)     // Catch:{ all -> 0x07fb }
            java.lang.String r3 = "@"
            java.lang.String[] r2 = r2.split(r3)     // Catch:{ all -> 0x07fb }
            r3 = 0
            r13 = r2[r3]     // Catch:{ all -> 0x07fb }
            long r17 = java.lang.Long.parseLong(r13)     // Catch:{ all -> 0x07fb }
            int r3 = r2.length     // Catch:{ all -> 0x07fb }
            r13 = 1
            if (r3 <= r13) goto L_0x065e
            r2 = r2[r13]     // Catch:{ all -> 0x07fb }
            long r2 = java.lang.Long.parseLong(r2)     // Catch:{ all -> 0x07fb }
            r11 = r2
        L_0x065e:
            r3 = r16
            r2 = r71
            goto L_0x0414
        L_0x0664:
            java.lang.String r2 = "#EXT-X-DISCONTINUITY-SEQUENCE"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            r3 = 58
            if (r2 == 0) goto L_0x0684
            int r2 = r13.indexOf(r3)     // Catch:{ all -> 0x07fb }
            r3 = 1
            int r2 = r2 + r3
            java.lang.String r2 = r13.substring(r2)     // Catch:{ all -> 0x07fb }
            int r42 = java.lang.Integer.parseInt(r2)     // Catch:{ all -> 0x07fb }
            r3 = r16
            r2 = r71
            r41 = 1
            goto L_0x0414
        L_0x0684:
            java.lang.String r2 = "#EXT-X-DISCONTINUITY"
            boolean r2 = r13.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x0694
            int r19 = r19 + 1
            r3 = r16
            r2 = r71
            goto L_0x0414
        L_0x0694:
            java.lang.String r2 = "#EXT-X-PROGRAM-DATE-TIME"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x06c7
            r14 = 0
            int r2 = (r39 > r14 ? 1 : (r39 == r14 ? 0 : -1))
            if (r2 != 0) goto L_0x06bc
            int r2 = r13.indexOf(r3)     // Catch:{ all -> 0x07fb }
            r3 = 1
            int r2 = r2 + r3
            java.lang.String r2 = r13.substring(r2)     // Catch:{ all -> 0x07fb }
            long r13 = com.google.ads.interactivemedia.v3.internal.vf.g(r2)     // Catch:{ all -> 0x07fb }
            long r13 = com.google.ads.interactivemedia.v3.internal.at.b(r13)     // Catch:{ all -> 0x07fb }
            long r39 = r13 - r30
            r3 = r16
            r2 = r71
            goto L_0x0414
        L_0x06bc:
            r24 = r1
            r72 = r9
            r25 = r10
            r3 = 0
            r22 = -1
            goto L_0x07b4
        L_0x06c7:
            r3 = 1
            java.lang.String r2 = "#EXT-X-GAP"
            boolean r2 = r13.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x06d8
            r3 = r16
            r2 = r71
            r68 = 1
            goto L_0x0414
        L_0x06d8:
            java.lang.String r2 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r2 = r13.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x06e8
            r3 = r16
            r2 = r71
            r48 = 1
            goto L_0x0414
        L_0x06e8:
            java.lang.String r2 = "#EXT-X-ENDLIST"
            boolean r2 = r13.equals(r2)     // Catch:{ all -> 0x07fb }
            if (r2 == 0) goto L_0x06f8
            r3 = r16
            r2 = r71
            r49 = 1
            goto L_0x0414
        L_0x06f8:
            java.lang.String r2 = "#"
            boolean r2 = r13.startsWith(r2)     // Catch:{ all -> 0x07fb }
            if (r2 != 0) goto L_0x07ab
            if (r5 != 0) goto L_0x0705
            r63 = 0
            goto L_0x0710
        L_0x0705:
            if (r9 == 0) goto L_0x070a
            r63 = r9
            goto L_0x0710
        L_0x070a:
            java.lang.String r2 = java.lang.Long.toHexString(r52)     // Catch:{ all -> 0x07fb }
            r63 = r2
        L_0x0710:
            r14 = 1
            long r14 = r52 + r14
            r22 = -1
            int r2 = (r17 > r22 ? 1 : (r17 == r22 ? 0 : -1))
            if (r2 != 0) goto L_0x071c
            r11 = 0
        L_0x071c:
            if (r32 != 0) goto L_0x076a
            boolean r20 = r10.isEmpty()     // Catch:{ all -> 0x07fb }
            if (r20 != 0) goto L_0x076a
            java.util.Collection r3 = r10.values()     // Catch:{ all -> 0x07fb }
            r24 = r1
            r72 = r9
            r1 = 0
            com.google.ads.interactivemedia.v3.internal.fa$a[] r9 = new com.google.ads.interactivemedia.v3.internal.fa.a[r1]     // Catch:{ all -> 0x07fb }
            java.lang.Object[] r3 = r3.toArray(r9)     // Catch:{ all -> 0x07fb }
            com.google.ads.interactivemedia.v3.internal.fa$a[] r3 = (com.google.ads.interactivemedia.v3.internal.fa.a[]) r3     // Catch:{ all -> 0x07fb }
            com.google.ads.interactivemedia.v3.internal.fa r9 = new com.google.ads.interactivemedia.v3.internal.fa     // Catch:{ all -> 0x07fb }
            r9.<init>(r8, r3)     // Catch:{ all -> 0x07fb }
            if (r51 != 0) goto L_0x0762
            int r1 = r3.length     // Catch:{ all -> 0x07fb }
            com.google.ads.interactivemedia.v3.internal.fa$a[] r1 = new com.google.ads.interactivemedia.v3.internal.fa.a[r1]     // Catch:{ all -> 0x07fb }
            r73 = r9
            r25 = r10
            r9 = 0
        L_0x0744:
            int r10 = r3.length     // Catch:{ all -> 0x07fb }
            if (r9 >= r10) goto L_0x0757
            r10 = r3[r9]     // Catch:{ all -> 0x07fb }
            r26 = r3
            r3 = 0
            com.google.ads.interactivemedia.v3.internal.fa$a r10 = r10.a(r3)     // Catch:{ all -> 0x07fb }
            r1[r9] = r10     // Catch:{ all -> 0x07fb }
            int r9 = r9 + 1
            r3 = r26
            goto L_0x0744
        L_0x0757:
            r3 = 0
            com.google.ads.interactivemedia.v3.internal.fa r9 = new com.google.ads.interactivemedia.v3.internal.fa     // Catch:{ all -> 0x07fb }
            r9.<init>(r8, r1)     // Catch:{ all -> 0x07fb }
            r32 = r73
            r51 = r9
            goto L_0x0771
        L_0x0762:
            r73 = r9
            r25 = r10
            r3 = 0
            r32 = r73
            goto L_0x0771
        L_0x076a:
            r24 = r1
            r72 = r9
            r25 = r10
            r3 = 0
        L_0x0771:
            com.google.ads.interactivemedia.v3.internal.qq r1 = new com.google.ads.interactivemedia.v3.internal.qq     // Catch:{ all -> 0x07fb }
            java.lang.String r53 = a(r13, r0)     // Catch:{ all -> 0x07fb }
            r52 = r1
            r54 = r21
            r56 = r69
            r58 = r19
            r59 = r30
            r61 = r32
            r62 = r5
            r64 = r11
            r66 = r17
            r52.<init>(r53, r54, r55, r56, r58, r59, r61, r62, r63, r64, r66, r68)     // Catch:{ all -> 0x07fb }
            r6.add(r1)     // Catch:{ all -> 0x07fb }
            long r30 = r30 + r69
            java.lang.String r55 = ""
            if (r2 == 0) goto L_0x0797
            long r11 = r11 + r17
        L_0x0797:
            r9 = r72
            r52 = r14
            r3 = r16
            r17 = r22
            r1 = r24
            r10 = r25
            r2 = r71
            r68 = 0
            r69 = 0
            goto L_0x0414
        L_0x07ab:
            r24 = r1
            r72 = r9
            r25 = r10
            r3 = 0
            r22 = -1
        L_0x07b4:
            r9 = r72
            r3 = r16
            r1 = r24
            r10 = r25
            r2 = r71
            goto L_0x0414
        L_0x07c0:
            com.google.ads.interactivemedia.v3.internal.qp r0 = new com.google.ads.interactivemedia.v3.internal.qp     // Catch:{ all -> 0x07fb }
            r1 = 0
            int r3 = (r39 > r1 ? 1 : (r39 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x07cb
            r50 = 1
            goto L_0x07cd
        L_0x07cb:
            r50 = 0
        L_0x07cd:
            r33 = r0
            r36 = r7
            r52 = r6
            r33.<init>(r34, r35, r36, r37, r39, r41, r42, r43, r45, r46, r48, r49, r50, r51, r52)     // Catch:{ all -> 0x07fb }
            com.google.ads.interactivemedia.v3.internal.vf.a(r4)
            return r0
        L_0x07da:
            r0 = move-exception
            r4 = r33
            goto L_0x07ff
        L_0x07de:
            r4 = r1
            r1 = 7
            r1 = r4
            r4 = 7
            goto L_0x0057
        L_0x07e4:
            r4 = r1
            com.google.ads.interactivemedia.v3.internal.vf.a(r4)
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r1 = "Failed to parse the playlist, could not identify any tags."
            r0.<init>(r1)
            throw r0
        L_0x07f0:
            r4 = r1
            com.google.ads.interactivemedia.v3.internal.nb r0 = new com.google.ads.interactivemedia.v3.internal.nb     // Catch:{ all -> 0x07fb }
            java.lang.String r1 = "Input does not start with the #EXTM3U header."
            r2 = r72
            r0.<init>(r1, r2)     // Catch:{ all -> 0x07fb }
            throw r0     // Catch:{ all -> 0x07fb }
        L_0x07fb:
            r0 = move-exception
            goto L_0x07ff
        L_0x07fd:
            r0 = move-exception
            r4 = r1
        L_0x07ff:
            com.google.ads.interactivemedia.v3.internal.vf.a(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.qs.a(android.net.Uri, java.io.InputStream):com.google.ads.interactivemedia.v3.internal.qr");
    }

    private static int a(BufferedReader bufferedReader, boolean z2, int i2) throws IOException {
        while (i2 != -1 && Character.isWhitespace(i2) && (z2 || !vf.a(i2))) {
            i2 = bufferedReader.read();
        }
        return i2;
    }

    private static a a(String str, String str2, Map<String, String> map) throws ca {
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(str2)) {
            String a2 = a(str, t, map);
            return new a(at.d, MimeTypes.VIDEO_MP4, Base64.decode(a2.substring(a2.indexOf(44)), 0));
        } else if (!"com.widevine".equals(str2)) {
            return null;
        } else {
            try {
                return new a(at.d, "hls", str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                throw new ca((Throwable) e2);
            }
        }
    }

    private static int a(String str, Pattern pattern) throws ca {
        return Integer.parseInt(a(str, pattern, Collections.emptyMap()));
    }

    private static double b(String str, Pattern pattern) throws ca {
        return Double.parseDouble(a(str, pattern, Collections.emptyMap()));
    }

    private static String a(String str, Pattern pattern, Map<String, String> map) throws ca {
        String a2 = a(str, pattern, null, map);
        if (a2 != null) {
            return a2;
        }
        String pattern2 = pattern.pattern();
        StringBuilder sb = new StringBuilder(String.valueOf(pattern2).length() + 19 + String.valueOf(str).length());
        sb.append("Couldn't match ");
        sb.append(pattern2);
        sb.append(" in ");
        sb.append(str);
        throw new ca(sb.toString());
    }

    private static String a(String str, Pattern pattern, String str2, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str2 = matcher.group(1);
        }
        return (map.isEmpty() || str2 == null) ? str2 : a(str2, map);
    }

    private static String a(String str, Map<String, String> map) {
        Matcher matcher = F.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement((String) map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean a(String str, Pattern pattern, boolean z2) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1).equals("YES");
        }
        return false;
    }

    private static Pattern a(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 9);
        sb.append(str);
        sb.append("=(NO");
        sb.append("|YES");
        sb.append(")");
        return Pattern.compile(sb.toString());
    }
}
