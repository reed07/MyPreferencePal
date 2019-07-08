package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: IMASDK */
public final class ot extends DefaultHandler implements tv<tc> {
    private static final Pattern a = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern b = Pattern.compile("CC([1-4])=.*");
    private static final Pattern c = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private final XmlPullParserFactory d;

    public ot() {
        try {
            this.d = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARNING: type inference failed for: r33v0 */
    /* JADX WARNING: type inference failed for: r33v1 */
    /* JADX WARNING: type inference failed for: r33v2 */
    /* JADX WARNING: type inference failed for: r2v32, types: [com.google.ads.interactivemedia.v3.internal.pe] */
    /* JADX WARNING: type inference failed for: r33v4 */
    /* JADX WARNING: type inference failed for: r2v33, types: [com.google.ads.interactivemedia.v3.internal.pd] */
    /* JADX WARNING: type inference failed for: r33v5 */
    /* JADX WARNING: type inference failed for: r2v34, types: [com.google.ads.interactivemedia.v3.internal.pg] */
    /* JADX WARNING: type inference failed for: r33v6 */
    /* JADX WARNING: type inference failed for: r73v0 */
    /* JADX WARNING: type inference failed for: r11v26, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r58v0, types: [com.google.ads.interactivemedia.v3.internal.oz] */
    /* JADX WARNING: type inference failed for: r58v1, types: [com.google.ads.interactivemedia.v3.internal.pa] */
    /* JADX WARNING: type inference failed for: r1v77, types: [com.google.ads.interactivemedia.v3.internal.pd] */
    /* JADX WARNING: type inference failed for: r73v4 */
    /* JADX WARNING: type inference failed for: r1v80, types: [com.google.ads.interactivemedia.v3.internal.pg] */
    /* JADX WARNING: type inference failed for: r73v5 */
    /* JADX WARNING: type inference failed for: r33v8 */
    /* JADX WARNING: type inference failed for: r33v9 */
    /* JADX WARNING: type inference failed for: r33v10 */
    /* JADX WARNING: type inference failed for: r33v11 */
    /* JADX WARNING: type inference failed for: r58v10, types: [com.google.ads.interactivemedia.v3.internal.oz] */
    /* JADX WARNING: type inference failed for: r58v11, types: [com.google.ads.interactivemedia.v3.internal.pa] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x06ce, code lost:
        r96 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x06d0, code lost:
        switch(r96) {
            case 0: goto L_0x06de;
            case 1: goto L_0x06db;
            case 2: goto L_0x06d9;
            case 3: goto L_0x06d6;
            case 4: goto L_0x06d4;
            default: goto L_0x06d3;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x06d4, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x06d6, code lost:
        r7 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x06d9, code lost:
        r7 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x06db, code lost:
        r7 = 512;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x06de, code lost:
        r7 = 1024;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r33v2
  assigns: []
  uses: []
  mth insns count: 1437
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
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0856 A[Catch:{ XmlPullParserException -> 0x0d6f }] */
    /* JADX WARNING: Removed duplicated region for block: B:347:0x0859 A[Catch:{ XmlPullParserException -> 0x0d6f }] */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0873 A[Catch:{ XmlPullParserException -> 0x0d6f }] */
    /* JADX WARNING: Removed duplicated region for block: B:362:0x08a8 A[LOOP:4: B:147:0x0479->B:362:0x08a8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:420:0x0a39 A[Catch:{ XmlPullParserException -> 0x0d6f }, LOOP:3: B:72:0x0298->B:420:0x0a39, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:488:0x0cdc A[Catch:{ XmlPullParserException -> 0x0d6f }, LOOP:2: B:60:0x01cc->B:488:0x0cdc, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:507:0x0d37 A[Catch:{ XmlPullParserException -> 0x0d6f }] */
    /* JADX WARNING: Removed duplicated region for block: B:509:0x0d4a A[Catch:{ XmlPullParserException -> 0x0d6f }] */
    /* JADX WARNING: Removed duplicated region for block: B:511:0x0d52 A[Catch:{ XmlPullParserException -> 0x0d6f }, LOOP:0: B:26:0x0093->B:511:0x0d52, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:518:0x0d14 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:521:0x0c69 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:522:0x094b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:523:0x058d A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 11 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.tc a(android.net.Uri r98, java.io.InputStream r99) throws java.io.IOException {
        /*
            r97 = this;
            r1 = r97
            org.xmlpull.v1.XmlPullParserFactory r2 = r1.d     // Catch:{ XmlPullParserException -> 0x0d6f }
            org.xmlpull.v1.XmlPullParser r2 = r2.newPullParser()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r3 = 0
            r4 = r99
            r2.setInput(r4, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r4 = r2.next()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r5 = 2
            if (r4 != r5) goto L_0x0d67
            java.lang.String r4 = "MPD"
            java.lang.String r6 = r2.getName()     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r4 = r4.equals(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 == 0) goto L_0x0d67
            java.lang.String r4 = r98.toString()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r6 = "availabilityStartTime"
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r10 = b(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r6 = "mediaPresentationDuration"
            long r12 = a(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r6 = "minBufferTime"
            long r14 = a(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r6 = "type"
            java.lang.String r6 = r2.getAttributeValue(r3, r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r6 == 0) goto L_0x0050
            java.lang.String r9 = "dynamic"
            boolean r6 = r9.equals(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r6 == 0) goto L_0x0050
            r16 = 1
            goto L_0x0052
        L_0x0050:
            r16 = 0
        L_0x0052:
            if (r16 == 0) goto L_0x005b
            java.lang.String r6 = "minimumUpdatePeriod"
            long r17 = a(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x005d
        L_0x005b:
            r17 = r7
        L_0x005d:
            if (r16 == 0) goto L_0x0066
            java.lang.String r6 = "timeShiftBufferDepth"
            long r19 = a(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0068
        L_0x0066:
            r19 = r7
        L_0x0068:
            if (r16 == 0) goto L_0x0071
            java.lang.String r6 = "suggestedPresentationDelay"
            long r21 = a(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0073
        L_0x0071:
            r21 = r7
        L_0x0073:
            java.lang.String r6 = "publishTime"
            long r23 = b(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0d6f }
            r6.<init>()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r28 = r6
            if (r16 == 0) goto L_0x0085
            r25 = r7
            goto L_0x0087
        L_0x0085:
            r25 = 0
        L_0x0087:
            r27 = r3
            r31 = r27
            r32 = r31
            r9 = r4
            r5 = r25
            r4 = 0
            r26 = 0
        L_0x0093:
            r2.next()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = "BaseURL"
            boolean r7 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x00d0
            if (r4 != 0) goto L_0x00b9
            java.lang.String r4 = b(r2, r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r9 = r4
            r42 = r5
            r55 = r10
            r69 = r12
            r53 = r14
            r4 = 1
            r25 = 0
            r46 = 0
            r6 = r1
            r5 = r2
            r1 = r3
            r2 = r28
            goto L_0x0d0c
        L_0x00b9:
            r41 = r4
            r42 = r5
            r52 = r9
            r55 = r10
            r69 = r12
            r53 = r14
            r25 = 0
            r46 = 0
            r6 = r1
            r5 = r2
            r1 = r3
            r2 = r28
            goto L_0x0d08
        L_0x00d0:
            java.lang.String r7 = "ProgramInformation"
            boolean r7 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0145
            java.lang.String r7 = "moreInformationURL"
            java.lang.String r37 = b(r2, r7, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = "lang"
            java.lang.String r38 = b(r2, r7, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = r3
            r8 = r7
            r27 = r8
        L_0x00e8:
            r2.next()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r3 = "Title"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x00f9
            java.lang.String r3 = r2.nextText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = r3
            goto L_0x0119
        L_0x00f9:
            java.lang.String r3 = "Source"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x0107
            java.lang.String r3 = r2.nextText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r8 = r3
            goto L_0x0119
        L_0x0107:
            java.lang.String r3 = "Copyright"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x0116
            java.lang.String r3 = r2.nextText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r27 = r3
            goto L_0x0119
        L_0x0116:
            f(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0119:
            java.lang.String r3 = "ProgramInformation"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.a(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x0143
            com.google.ads.interactivemedia.v3.internal.ow r3 = new com.google.ads.interactivemedia.v3.internal.ow     // Catch:{ XmlPullParserException -> 0x0d6f }
            r33 = r3
            r34 = r7
            r35 = r8
            r36 = r27
            r33.<init>(r34, r35, r36, r37, r38)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r27 = r3
            r42 = r5
            r55 = r10
            r69 = r12
            r53 = r14
            r25 = 0
            r46 = 0
            r6 = r1
            r5 = r2
            r2 = r28
            r1 = 0
            goto L_0x0d0c
        L_0x0143:
            r3 = 0
            goto L_0x00e8
        L_0x0145:
            java.lang.String r3 = "UTCTiming"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x0175
            java.lang.String r3 = "schemeIdUri"
            r7 = 0
            java.lang.String r3 = r2.getAttributeValue(r7, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r8 = "value"
            java.lang.String r8 = r2.getAttributeValue(r7, r8)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.pj r7 = new com.google.ads.interactivemedia.v3.internal.pj     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7.<init>(r3, r8)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r42 = r5
            r31 = r7
            r55 = r10
            r69 = r12
            r53 = r14
            r25 = 0
            r46 = 0
            r6 = r1
            r5 = r2
            r2 = r28
            r1 = 0
            goto L_0x0d0c
        L_0x0175:
            java.lang.String r3 = "Location"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x019a
            java.lang.String r3 = r2.nextText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r32 = r3
            r42 = r5
            r55 = r10
            r69 = r12
            r53 = r14
            r25 = 0
            r46 = 0
            r6 = r1
            r5 = r2
            r2 = r28
            r1 = 0
            goto L_0x0d0c
        L_0x019a:
            java.lang.String r3 = "Period"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x0cf0
            if (r26 != 0) goto L_0x0cf0
            java.lang.String r3 = "id"
            r7 = 0
            java.lang.String r34 = r2.getAttributeValue(r7, r3)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r3 = "start"
            long r35 = a(r2, r3, r5)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r3 = "duration"
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r39 = a(r2, r3, r7)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r3.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            r41 = r4
            r4 = r9
            r8 = 0
            r33 = 0
        L_0x01cc:
            r2.next()     // Catch:{ XmlPullParserException -> 0x0cec }
            r42 = r5
            java.lang.String r5 = "BaseURL"
            boolean r5 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r5)     // Catch:{ XmlPullParserException -> 0x0cec }
            if (r5 == 0) goto L_0x020a
            if (r8 != 0) goto L_0x01f3
            java.lang.String r4 = b(r2, r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r6 = r1
            r5 = r2
            r72 = r3
            r52 = r9
            r55 = r10
            r69 = r12
            r53 = r14
            r1 = 0
            r8 = 1
            r25 = 0
            r46 = 0
            goto L_0x0c61
        L_0x01f3:
            r6 = r1
            r5 = r2
            r72 = r3
            r50 = r4
            r51 = r8
            r52 = r9
            r55 = r10
            r69 = r12
            r53 = r14
            r1 = 0
            r25 = 0
            r46 = 0
            goto L_0x0c5d
        L_0x020a:
            java.lang.String r5 = "AdaptationSet"
            boolean r5 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r5)     // Catch:{ XmlPullParserException -> 0x0cec }
            if (r5 == 0) goto L_0x0a58
            java.lang.String r5 = "id"
            r6 = -1
            int r45 = a(r2, r5, r6)     // Catch:{ XmlPullParserException -> 0x0cec }
            int r5 = a(r2)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r6 = "mimeType"
            r50 = r4
            r4 = 0
            java.lang.String r6 = r2.getAttributeValue(r4, r6)     // Catch:{ XmlPullParserException -> 0x0cec }
            r44 = r5
            java.lang.String r5 = "codecs"
            java.lang.String r5 = r2.getAttributeValue(r4, r5)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r4 = "width"
            r51 = r8
            r8 = -1
            int r4 = a(r2, r4, r8)     // Catch:{ XmlPullParserException -> 0x0cec }
            r52 = r9
            java.lang.String r9 = "height"
            int r9 = a(r2, r9, r8)     // Catch:{ XmlPullParserException -> 0x0cec }
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r8 = a(r2, r8)     // Catch:{ XmlPullParserException -> 0x0cec }
            r53 = r14
            java.lang.String r14 = "audioSamplingRate"
            r15 = -1
            int r14 = a(r2, r14, r15)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r15 = "lang"
            r55 = r10
            r10 = 0
            java.lang.String r11 = r2.getAttributeValue(r10, r15)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r15 = "label"
            java.lang.String r15 = r2.getAttributeValue(r10, r15)     // Catch:{ XmlPullParserException -> 0x0cec }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r10.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            r46 = r11
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r11.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            r69 = r12
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r12.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r13.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            r71 = r7
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            r72 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0cec }
            r3.<init>()     // Catch:{ XmlPullParserException -> 0x0cec }
            r47 = r3
            r48 = r15
            r73 = r33
            r15 = r44
            r3 = r46
            r44 = 0
            r49 = -1
            r74 = 0
            r46 = r11
            r11 = r50
        L_0x0298:
            r2.next()     // Catch:{ XmlPullParserException -> 0x0cec }
            java.lang.String r1 = "BaseURL"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x02e5
            if (r44 != 0) goto L_0x02c7
            java.lang.String r1 = b(r2, r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r11 = r1
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r6 = r97
            r44 = 1
            r5 = r2
            goto L_0x0943
        L_0x02c7:
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r75 = r11
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r6 = r97
            r5 = r2
            r2 = r15
            goto L_0x0940
        L_0x02e5:
            java.lang.String r1 = "ContentProtection"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x0325
            android.util.Pair r1 = b(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r75 = r11
            java.lang.Object r11 = r1.first     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r11 == 0) goto L_0x02fd
            java.lang.Object r11 = r1.first     // Catch:{ XmlPullParserException -> 0x0d6f }
            r74 = r11
            java.lang.String r74 = (java.lang.String) r74     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x02fd:
            java.lang.Object r11 = r1.second     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r11 == 0) goto L_0x0308
            java.lang.Object r1 = r1.second     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.fa$a r1 = (com.google.ads.interactivemedia.v3.internal.fa.a) r1     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0308:
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r11 = r75
            r6 = r97
            r5 = r2
            goto L_0x0943
        L_0x0325:
            r75 = r11
            java.lang.String r1 = "ContentComponent"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x036b
            java.lang.String r1 = "lang"
            r11 = 0
            java.lang.String r1 = r2.getAttributeValue(r11, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 != 0) goto L_0x0339
            goto L_0x0344
        L_0x0339:
            if (r1 != 0) goto L_0x033c
            goto L_0x0343
        L_0x033c:
            boolean r1 = r3.equals(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.qi.c(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0343:
            r1 = r3
        L_0x0344:
            int r3 = a(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r3 = a(r15, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r15 = r3
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r11 = r75
            r6 = r97
            r3 = r1
            r5 = r2
            goto L_0x0943
        L_0x036b:
            java.lang.String r1 = "Role"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x0398
            java.lang.String r1 = "Role"
            com.google.ads.interactivemedia.v3.internal.ou r1 = a(r2, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r13.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r6 = r97
            r5 = r2
            r2 = r15
            goto L_0x0940
        L_0x0398:
            java.lang.String r1 = "AudioChannelConfiguration"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x03c3
            int r1 = e(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r49 = r1
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r11 = r75
            r6 = r97
            r5 = r2
            goto L_0x0943
        L_0x03c3:
            java.lang.String r1 = "Accessibility"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x03f0
            java.lang.String r1 = "Accessibility"
            com.google.ads.interactivemedia.v3.internal.ou r1 = a(r2, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r12.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r6 = r97
            r5 = r2
            r2 = r15
            goto L_0x0940
        L_0x03f0:
            java.lang.String r1 = "SupplementalProperty"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x041d
            java.lang.String r1 = "SupplementalProperty"
            com.google.ads.interactivemedia.v3.internal.ou r1 = a(r2, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r7 = r46
            r4 = r47
            r6 = r97
            r5 = r2
            r2 = r15
            goto L_0x0940
        L_0x041d:
            java.lang.String r1 = "Representation"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x08bc
            java.lang.String r1 = "id"
            r11 = 0
            java.lang.String r57 = r2.getAttributeValue(r11, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r1 = "bandwidth"
            r11 = -1
            int r62 = a(r2, r1, r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r1 = "mimeType"
            java.lang.String r1 = b(r2, r1, r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r11 = "codecs"
            java.lang.String r11 = b(r2, r11, r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r76 = r5
            java.lang.String r5 = "width"
            int r63 = a(r2, r5, r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r5 = "height"
            int r64 = a(r2, r5, r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            float r65 = a(r2, r8)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r5 = "audioSamplingRate"
            int r5 = a(r2, r5, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r86 = r4
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.<init>()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r87 = r6
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0d6f }
            r6.<init>()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r88 = r8
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0d6f }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r89 = r9
            r60 = r49
            r59 = r73
            r9 = r75
            r58 = 0
            r61 = 0
        L_0x0479:
            r2.next()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r90 = r14
            java.lang.String r14 = "BaseURL"
            boolean r14 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 == 0) goto L_0x04a5
            if (r58 != 0) goto L_0x049d
            java.lang.String r9 = b(r2, r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r91 = r7
            r7 = r9
            r92 = r10
            r77 = r59
            r14 = r60
            r81 = r61
            r9 = r97
            r58 = 1
            goto L_0x0585
        L_0x049d:
            r91 = r7
            r66 = r9
            r9 = r97
            goto L_0x057b
        L_0x04a5:
            java.lang.String r14 = "AudioChannelConfiguration"
            boolean r14 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 == 0) goto L_0x04be
            int r14 = e(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r91 = r7
            r7 = r9
            r92 = r10
            r77 = r59
            r81 = r61
            r9 = r97
            goto L_0x0585
        L_0x04be:
            java.lang.String r14 = "SegmentBase"
            boolean r14 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 == 0) goto L_0x04e5
            r14 = r59
            com.google.ads.interactivemedia.v3.internal.pg r14 = (com.google.ads.interactivemedia.v3.internal.pg) r14     // Catch:{ XmlPullParserException -> 0x04e0 }
            r66 = r9
            r9 = r97
            com.google.ads.interactivemedia.v3.internal.pg r14 = r9.a(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r91 = r7
            r92 = r10
            r77 = r14
            r14 = r60
            r81 = r61
            r7 = r66
            goto L_0x0585
        L_0x04e0:
            r0 = move-exception
            r9 = r97
            goto L_0x0d70
        L_0x04e5:
            r66 = r9
            r9 = r97
            java.lang.String r14 = "SegmentList"
            boolean r14 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 == 0) goto L_0x0507
            r14 = r59
            com.google.ads.interactivemedia.v3.internal.pd r14 = (com.google.ads.interactivemedia.v3.internal.pd) r14     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.pd r14 = r9.a(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r91 = r7
            r92 = r10
            r77 = r14
            r14 = r60
            r81 = r61
            r7 = r66
            goto L_0x0585
        L_0x0507:
            java.lang.String r14 = "SegmentTemplate"
            boolean r14 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 == 0) goto L_0x0524
            r14 = r59
            com.google.ads.interactivemedia.v3.internal.pe r14 = (com.google.ads.interactivemedia.v3.internal.pe) r14     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.pe r14 = r9.a(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r91 = r7
            r92 = r10
            r77 = r14
            r14 = r60
            r81 = r61
            r7 = r66
            goto L_0x0585
        L_0x0524:
            java.lang.String r14 = "ContentProtection"
            boolean r14 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 == 0) goto L_0x0552
            android.util.Pair r14 = b(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r91 = r7
            java.lang.Object r7 = r14.first     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x053c
            java.lang.Object r7 = r14.first     // Catch:{ XmlPullParserException -> 0x0d6f }
            r61 = r7
            java.lang.String r61 = (java.lang.String) r61     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x053c:
            java.lang.Object r7 = r14.second     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0547
            java.lang.Object r7 = r14.second     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.fa$a r7 = (com.google.ads.interactivemedia.v3.internal.fa.a) r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.add(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0547:
            r92 = r10
            r77 = r59
            r14 = r60
            r81 = r61
            r7 = r66
            goto L_0x0585
        L_0x0552:
            r91 = r7
            java.lang.String r7 = "InbandEventStream"
            boolean r7 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0566
            java.lang.String r7 = "InbandEventStream"
            com.google.ads.interactivemedia.v3.internal.ou r7 = a(r2, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r6.add(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x057b
        L_0x0566:
            java.lang.String r7 = "SupplementalProperty"
            boolean r7 = com.google.ads.interactivemedia.v3.internal.qi.b(r2, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0578
            java.lang.String r7 = "SupplementalProperty"
            com.google.ads.interactivemedia.v3.internal.ou r7 = a(r2, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r8.add(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x057b
        L_0x0578:
            f(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x057b:
            r92 = r10
            r77 = r59
            r14 = r60
            r81 = r61
            r7 = r66
        L_0x0585:
            java.lang.String r10 = "Representation"
            boolean r10 = com.google.ads.interactivemedia.v3.internal.qi.a(r2, r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x08a8
            boolean r10 = com.google.ads.interactivemedia.v3.internal.un.a(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x0598
            java.lang.String r10 = com.google.ads.interactivemedia.v3.internal.un.e(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x05f7
        L_0x0598:
            boolean r10 = com.google.ads.interactivemedia.v3.internal.un.b(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05a3
            java.lang.String r10 = com.google.ads.interactivemedia.v3.internal.un.d(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x05f7
        L_0x05a3:
            boolean r10 = b(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05ab
            r10 = r1
            goto L_0x05f7
        L_0x05ab:
            java.lang.String r10 = "application/mp4"
            boolean r10 = r10.equals(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05cc
            if (r11 == 0) goto L_0x05f6
            java.lang.String r10 = "stpp"
            boolean r10 = r11.startsWith(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05c0
            java.lang.String r10 = "application/ttml+xml"
            goto L_0x05f7
        L_0x05c0:
            java.lang.String r10 = "wvtt"
            boolean r10 = r11.startsWith(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05f6
            java.lang.String r10 = "application/x-mp4-vtt"
            goto L_0x05f7
        L_0x05cc:
            java.lang.String r10 = "application/x-rawcc"
            boolean r10 = r10.equals(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05f6
            if (r11 == 0) goto L_0x05f4
            java.lang.String r10 = "cea708"
            boolean r10 = r11.contains(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05e1
            java.lang.String r10 = "application/cea-708"
            goto L_0x05f7
        L_0x05e1:
            java.lang.String r10 = "eia608"
            boolean r10 = r11.contains(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 != 0) goto L_0x05f1
            java.lang.String r10 = "cea608"
            boolean r10 = r11.contains(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x05f4
        L_0x05f1:
            java.lang.String r10 = "application/cea-608"
            goto L_0x05f7
        L_0x05f4:
            r10 = 0
            goto L_0x05f7
        L_0x05f6:
            r10 = 0
        L_0x05f7:
            r93 = r2
            r9 = 0
        L_0x05fa:
            int r2 = r13.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r9 >= r2) goto L_0x0628
            java.lang.Object r2 = r13.get(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ou r2 = (com.google.ads.interactivemedia.v3.internal.ou) r2     // Catch:{ XmlPullParserException -> 0x0d6f }
            r94 = r15
            java.lang.String r15 = "urn:mpeg:dash:role:2011"
            r83 = r6
            java.lang.String r6 = r2.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r6 = r15.equalsIgnoreCase(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r6 == 0) goto L_0x0621
            java.lang.String r6 = "main"
            java.lang.String r2 = r2.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r2 = r6.equals(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 == 0) goto L_0x0621
            r2 = 1
            goto L_0x062d
        L_0x0621:
            int r9 = r9 + 1
            r6 = r83
            r15 = r94
            goto L_0x05fa
        L_0x0628:
            r83 = r6
            r94 = r15
            r2 = 0
        L_0x062d:
            r6 = 0
            r9 = 0
        L_0x062f:
            int r15 = r13.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r6 >= r15) goto L_0x0659
            java.lang.Object r15 = r13.get(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ou r15 = (com.google.ads.interactivemedia.v3.internal.ou) r15     // Catch:{ XmlPullParserException -> 0x0d6f }
            r95 = r13
            java.lang.String r13 = "urn:mpeg:dash:role:2011"
            r82 = r4
            java.lang.String r4 = r15.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r4 = r13.equalsIgnoreCase(r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 == 0) goto L_0x0652
            java.lang.String r4 = r15.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r4 = a(r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4 = r4 | r9
            r9 = r4
        L_0x0652:
            int r6 = r6 + 1
            r4 = r82
            r13 = r95
            goto L_0x062f
        L_0x0659:
            r82 = r4
            r95 = r13
            r4 = 0
            r6 = 0
        L_0x065f:
            int r13 = r12.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 >= r13) goto L_0x06e9
            java.lang.Object r13 = r12.get(r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ou r13 = (com.google.ads.interactivemedia.v3.internal.ou) r13     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r15 = "urn:mpeg:dash:role:2011"
            r79 = r7
            java.lang.String r7 = r13.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r7 = r15.equalsIgnoreCase(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0680
            java.lang.String r7 = r13.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r7 = a(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x06e2
        L_0x0680:
            java.lang.String r7 = "urn:tva:metadata:cs:AudioPurposeCS:2007"
            java.lang.String r15 = r13.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r7 = r7.equalsIgnoreCase(r15)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06e3
            java.lang.String r7 = r13.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06e1
            int r13 = r7.hashCode()     // Catch:{ XmlPullParserException -> 0x0d6f }
            switch(r13) {
                case 49: goto L_0x06c3;
                case 50: goto L_0x06b8;
                case 51: goto L_0x06ad;
                case 52: goto L_0x06a2;
                case 53: goto L_0x0696;
                case 54: goto L_0x0697;
                default: goto L_0x0696;
            }     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0696:
            goto L_0x06ce
        L_0x0697:
            java.lang.String r13 = "6"
            boolean r7 = r7.equals(r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06ce
            r96 = 4
            goto L_0x06d0
        L_0x06a2:
            java.lang.String r13 = "4"
            boolean r7 = r7.equals(r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06ce
            r96 = 3
            goto L_0x06d0
        L_0x06ad:
            java.lang.String r13 = "3"
            boolean r7 = r7.equals(r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06ce
            r96 = 2
            goto L_0x06d0
        L_0x06b8:
            java.lang.String r13 = "2"
            boolean r7 = r7.equals(r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06ce
            r96 = 1
            goto L_0x06d0
        L_0x06c3:
            java.lang.String r13 = "1"
            boolean r7 = r7.equals(r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x06ce
            r96 = 0
            goto L_0x06d0
        L_0x06ce:
            r96 = -1
        L_0x06d0:
            switch(r96) {
                case 0: goto L_0x06de;
                case 1: goto L_0x06db;
                case 2: goto L_0x06d9;
                case 3: goto L_0x06d6;
                case 4: goto L_0x06d4;
                default: goto L_0x06d3;
            }     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x06d3:
            goto L_0x06e1
        L_0x06d4:
            r7 = 1
            goto L_0x06e2
        L_0x06d6:
            r7 = 8
            goto L_0x06e2
        L_0x06d9:
            r7 = 4
            goto L_0x06e2
        L_0x06db:
            r7 = 512(0x200, float:7.175E-43)
            goto L_0x06e2
        L_0x06de:
            r7 = 1024(0x400, float:1.435E-42)
            goto L_0x06e2
        L_0x06e1:
            r7 = 0
        L_0x06e2:
            r6 = r6 | r7
        L_0x06e3:
            int r4 = r4 + 1
            r7 = r79
            goto L_0x065f
        L_0x06e9:
            r79 = r7
            r4 = r9 | r6
            if (r10 == 0) goto L_0x0840
            java.lang.String r6 = "audio/eac3"
            boolean r6 = r6.equals(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r6 == 0) goto L_0x0721
            r6 = 0
        L_0x06f8:
            int r7 = r8.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r6 >= r7) goto L_0x071e
            java.lang.Object r7 = r8.get(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ou r7 = (com.google.ads.interactivemedia.v3.internal.ou) r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r9 = r7.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r10 = "tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014"
            boolean r9 = r10.equals(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r9 == 0) goto L_0x071b
            java.lang.String r9 = "ec+3"
            java.lang.String r7 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r7 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x071b
            java.lang.String r6 = "audio/eac3-joc"
            goto L_0x0722
        L_0x071b:
            int r6 = r6 + 1
            goto L_0x06f8
        L_0x071e:
            java.lang.String r6 = "audio/eac3"
            goto L_0x0722
        L_0x0721:
            r6 = r10
        L_0x0722:
            boolean r7 = com.google.ads.interactivemedia.v3.internal.un.b(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x073e
            r66 = 0
            r58 = r48
            r59 = r1
            r60 = r6
            r61 = r11
            r67 = r2
            r68 = r4
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r78 = r1
            goto L_0x0854
        L_0x073e:
            boolean r7 = com.google.ads.interactivemedia.v3.internal.un.a(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0760
            r65 = 0
            r58 = r48
            r59 = r1
            r60 = r6
            r61 = r11
            r63 = r14
            r64 = r5
            r66 = r2
            r67 = r4
            r68 = r3
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r78 = r1
            goto L_0x0854
        L_0x0760:
            boolean r5 = b(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r5 == 0) goto L_0x083d
            java.lang.String r5 = "application/cea-608"
            boolean r5 = r5.equals(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r5 == 0) goto L_0x07c6
            r5 = 0
        L_0x076f:
            int r7 = r12.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r5 >= r7) goto L_0x07c2
            java.lang.Object r7 = r12.get(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ou r7 = (com.google.ads.interactivemedia.v3.internal.ou) r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r8 = "urn:scte:dash:cc:cea-608:2015"
            java.lang.String r9 = r7.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r8 = r8.equals(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r8 == 0) goto L_0x07bf
            java.lang.String r8 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r8 == 0) goto L_0x07bf
            java.util.regex.Pattern r8 = b     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r9 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.util.regex.Matcher r8 = r8.matcher(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r9 = r8.matches()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r9 == 0) goto L_0x07a2
            r9 = 1
            java.lang.String r5 = r8.group(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x07c3
        L_0x07a2:
            java.lang.String r9 = "MpdParser"
            java.lang.String r10 = "Unable to parse CEA-608 channel number from: "
            java.lang.String r7 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r13 = r7.length()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r13 == 0) goto L_0x07b7
            java.lang.String r7 = r10.concat(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x07bc
        L_0x07b7:
            java.lang.String r7 = new java.lang.String     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7.<init>(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x07bc:
            android.util.Log.w(r9, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x07bf:
            int r5 = r5 + 1
            goto L_0x076f
        L_0x07c2:
            r5 = -1
        L_0x07c3:
            r66 = r5
            goto L_0x0828
        L_0x07c6:
            java.lang.String r5 = "application/cea-708"
            boolean r5 = r5.equals(r6)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r5 == 0) goto L_0x0826
            r5 = 0
        L_0x07cf:
            int r7 = r12.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r5 >= r7) goto L_0x0822
            java.lang.Object r7 = r12.get(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ou r7 = (com.google.ads.interactivemedia.v3.internal.ou) r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r9 = "urn:scte:dash:cc:cea-708:2015"
            java.lang.String r10 = r7.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r9 = r9.equals(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r9 == 0) goto L_0x081f
            java.lang.String r9 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r9 == 0) goto L_0x081f
            java.util.regex.Pattern r9 = c     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r10 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.util.regex.Matcher r9 = r9.matcher(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r10 = r9.matches()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x0802
            r8 = 1
            java.lang.String r5 = r9.group(r8)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0823
        L_0x0802:
            java.lang.String r8 = "MpdParser"
            java.lang.String r10 = "Unable to parse CEA-708 service block number from: "
            java.lang.String r7 = r7.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r13 = r7.length()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r13 == 0) goto L_0x0817
            java.lang.String r7 = r10.concat(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x081c
        L_0x0817:
            java.lang.String r7 = new java.lang.String     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7.<init>(r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x081c:
            android.util.Log.w(r8, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x081f:
            int r5 = r5 + 1
            goto L_0x07cf
        L_0x0822:
            r5 = -1
        L_0x0823:
            r66 = r5
            goto L_0x0828
        L_0x0826:
            r66 = -1
        L_0x0828:
            r58 = r48
            r59 = r1
            r60 = r6
            r61 = r11
            r63 = r2
            r64 = r4
            r65 = r3
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r57, r58, r59, r60, r61, r62, r63, r64, r65, r66)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r78 = r1
            goto L_0x0854
        L_0x083d:
            r60 = r6
            goto L_0x0842
        L_0x0840:
            r60 = r10
        L_0x0842:
            r58 = r48
            r59 = r1
            r61 = r11
            r63 = r2
            r64 = r4
            r65 = r3
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r57, r58, r59, r60, r61, r62, r63, r64, r65)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r78 = r1
        L_0x0854:
            if (r77 == 0) goto L_0x0859
            r80 = r77
            goto L_0x0860
        L_0x0859:
            com.google.ads.interactivemedia.v3.internal.pg r1 = new com.google.ads.interactivemedia.v3.internal.pg     // Catch:{ XmlPullParserException -> 0x0d6f }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r80 = r1
        L_0x0860:
            com.google.ads.interactivemedia.v3.internal.uw r1 = new com.google.ads.interactivemedia.v3.internal.uw     // Catch:{ XmlPullParserException -> 0x0d6f }
            r84 = -1
            r77 = r1
            r77.<init>(r78, r79, r80, r81, r82, r83, r84)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.bs r2 = r1.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r2 = r2.h     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 != 0) goto L_0x0891
            boolean r4 = com.google.ads.interactivemedia.v3.internal.un.b(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 == 0) goto L_0x087d
            r2 = r94
            r4 = 2
            goto L_0x0894
        L_0x087d:
            boolean r4 = com.google.ads.interactivemedia.v3.internal.un.a(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 == 0) goto L_0x0887
            r2 = r94
            r4 = 1
            goto L_0x0894
        L_0x0887:
            boolean r2 = b(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 == 0) goto L_0x0891
            r2 = r94
            r4 = 3
            goto L_0x0894
        L_0x0891:
            r2 = r94
            r4 = -1
        L_0x0894:
            int r2 = a(r2, r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4 = r47
            r4.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r15 = r2
            r7 = r46
            r11 = r75
            r5 = r93
            r6 = r97
            goto L_0x0943
        L_0x08a8:
            r93 = r2
            r79 = r7
            r60 = r14
            r59 = r77
            r9 = r79
            r61 = r81
            r14 = r90
            r7 = r91
            r10 = r92
            goto L_0x0479
        L_0x08bc:
            r93 = r2
            r86 = r4
            r76 = r5
            r87 = r6
            r91 = r7
            r88 = r8
            r89 = r9
            r92 = r10
            r95 = r13
            r90 = r14
            r2 = r15
            r4 = r47
            java.lang.String r1 = "SegmentBase"
            r5 = r93
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r1)     // Catch:{ XmlPullParserException -> 0x0a53 }
            if (r1 == 0) goto L_0x08ef
            r1 = r73
            com.google.ads.interactivemedia.v3.internal.pg r1 = (com.google.ads.interactivemedia.v3.internal.pg) r1     // Catch:{ XmlPullParserException -> 0x0a53 }
            r6 = r97
            com.google.ads.interactivemedia.v3.internal.pg r1 = r6.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r73 = r1
            r15 = r2
            r7 = r46
            r11 = r75
            goto L_0x0943
        L_0x08ef:
            r6 = r97
            java.lang.String r1 = "SegmentList"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0909
            r1 = r73
            com.google.ads.interactivemedia.v3.internal.pd r1 = (com.google.ads.interactivemedia.v3.internal.pd) r1     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.pd r1 = r6.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r73 = r1
            r15 = r2
            r7 = r46
            r11 = r75
            goto L_0x0943
        L_0x0909:
            java.lang.String r1 = "SegmentTemplate"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0921
            r1 = r73
            com.google.ads.interactivemedia.v3.internal.pe r1 = (com.google.ads.interactivemedia.v3.internal.pe) r1     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.pe r1 = r6.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r73 = r1
            r15 = r2
            r7 = r46
            r11 = r75
            goto L_0x0943
        L_0x0921:
            java.lang.String r1 = "InbandEventStream"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0935
            java.lang.String r1 = "InbandEventStream"
            com.google.ads.interactivemedia.v3.internal.ou r1 = a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = r46
            r7.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0940
        L_0x0935:
            r7 = r46
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0940
            f(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0940:
            r15 = r2
            r11 = r75
        L_0x0943:
            java.lang.String r1 = "AdaptationSet"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0a39
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r2 = r4.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r1.<init>(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r2 = 0
        L_0x0955:
            int r3 = r4.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 >= r3) goto L_0x0a1a
            java.lang.Object r3 = r4.get(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.uw r3 = (com.google.ads.interactivemedia.v3.internal.uw) r3     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.bs r8 = r3.a     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r10 = r3.d     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r10 == 0) goto L_0x096a
            java.lang.String r10 = r3.d     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x096c
        L_0x096a:
            r10 = r74
        L_0x096c:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.fa$a> r11 = r3.e     // Catch:{ XmlPullParserException -> 0x0d6f }
            r13 = r92
            r11.addAll(r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r14 = r11.isEmpty()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r14 != 0) goto L_0x09c9
            int r14 = r11.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r9 = 1
            int r14 = r14 - r9
        L_0x097f:
            if (r14 < 0) goto L_0x09b9
            java.lang.Object r25 = r11.get(r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r9 = r25
            com.google.ads.interactivemedia.v3.internal.fa$a r9 = (com.google.ads.interactivemedia.v3.internal.fa.a) r9     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r25 = r9.a()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r25 != 0) goto L_0x09ad
            r47 = r4
            r92 = r13
            r4 = 0
        L_0x0994:
            int r13 = r11.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r4 >= r13) goto L_0x09b1
            java.lang.Object r13 = r11.get(r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.fa$a r13 = (com.google.ads.interactivemedia.v3.internal.fa.a) r13     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r13 = r13.a(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r13 == 0) goto L_0x09aa
            r11.remove(r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x09b1
        L_0x09aa:
            int r4 = r4 + 1
            goto L_0x0994
        L_0x09ad:
            r47 = r4
            r92 = r13
        L_0x09b1:
            int r14 = r14 + -1
            r4 = r47
            r13 = r92
            r9 = 1
            goto L_0x097f
        L_0x09b9:
            r47 = r4
            r92 = r13
            com.google.ads.interactivemedia.v3.internal.fa r4 = new com.google.ads.interactivemedia.v3.internal.fa     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.<init>(r10, r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.bs r8 = r8.a(r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r61 = r8
            goto L_0x09cf
        L_0x09c9:
            r47 = r4
            r92 = r13
            r61 = r8
        L_0x09cf:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.ou> r4 = r3.f     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.addAll(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            long r8 = r3.g     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r10 = r3.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.pb r3 = r3.c     // Catch:{ XmlPullParserException -> 0x0d6f }
            boolean r11 = r3 instanceof com.google.ads.interactivemedia.v3.internal.pg     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r11 == 0) goto L_0x09f4
            com.google.ads.interactivemedia.v3.internal.pa r11 = new com.google.ads.interactivemedia.v3.internal.pa     // Catch:{ XmlPullParserException -> 0x0d6f }
            r63 = r3
            com.google.ads.interactivemedia.v3.internal.pg r63 = (com.google.ads.interactivemedia.v3.internal.pg) r63     // Catch:{ XmlPullParserException -> 0x0d6f }
            r65 = 0
            r66 = -1
            r58 = r11
            r59 = r8
            r62 = r10
            r64 = r4
            r58.<init>(r59, r61, r62, r63, r64, r65, r66)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0a09
        L_0x09f4:
            boolean r11 = r3 instanceof com.google.ads.interactivemedia.v3.internal.pc     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r11 == 0) goto L_0x0a12
            com.google.ads.interactivemedia.v3.internal.oz r11 = new com.google.ads.interactivemedia.v3.internal.oz     // Catch:{ XmlPullParserException -> 0x0d6f }
            r63 = r3
            com.google.ads.interactivemedia.v3.internal.pc r63 = (com.google.ads.interactivemedia.v3.internal.pc) r63     // Catch:{ XmlPullParserException -> 0x0d6f }
            r58 = r11
            r59 = r8
            r62 = r10
            r64 = r4
            r58.<init>(r59, r61, r62, r63, r64)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0a09:
            r1.add(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r2 = r2 + 1
            r4 = r47
            goto L_0x0955
        L_0x0a12:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r2 = "segmentBase must be of type SingleSegmentBase or MultiSegmentBase"
            r1.<init>(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            throw r1     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0a1a:
            com.google.ads.interactivemedia.v3.internal.rr r2 = new com.google.ads.interactivemedia.v3.internal.rr     // Catch:{ XmlPullParserException -> 0x0d6f }
            r44 = r2
            r46 = r15
            r47 = r1
            r48 = r12
            r49 = r91
            r44.<init>(r45, r46, r47, r48, r49)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r1 = r72
            r1.add(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r72 = r1
            r7 = r71
            r1 = 0
            r25 = 0
            r46 = 0
            goto L_0x0c5d
        L_0x0a39:
            r47 = r4
            r2 = r5
            r1 = r6
            r46 = r7
            r5 = r76
            r4 = r86
            r6 = r87
            r8 = r88
            r9 = r89
            r14 = r90
            r7 = r91
            r10 = r92
            r13 = r95
            goto L_0x0298
        L_0x0a53:
            r0 = move-exception
            r6 = r97
            goto L_0x0d70
        L_0x0a58:
            r6 = r1
            r5 = r2
            r1 = r3
            r50 = r4
            r71 = r7
            r51 = r8
            r52 = r9
            r55 = r10
            r69 = r12
            r53 = r14
            java.lang.String r2 = "EventStream"
            boolean r2 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 == 0) goto L_0x0c17
            java.lang.String r2 = "schemeIdUri"
            java.lang.String r3 = ""
            java.lang.String r2 = b(r5, r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r3 = "value"
            java.lang.String r4 = ""
            java.lang.String r3 = b(r5, r3, r4)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r4 = "timescale"
            r7 = 1
            long r44 = c(r5, r4, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.<init>()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.io.ByteArrayOutputStream r15 = new java.io.ByteArrayOutputStream     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = 512(0x200, float:7.175E-43)
            r15.<init>(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0a96:
            r5.next()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = "Event"
            boolean r7 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r7 == 0) goto L_0x0bc1
            java.lang.String r7 = "id"
            r13 = 0
            long r29 = c(r5, r7, r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = "duration"
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r10 = c(r5, r7, r8)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r7 = "presentationTime"
            long r7 = c(r5, r7, r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r37 = 1000(0x3e8, double:4.94E-321)
            r9 = r10
            r11 = r37
            r46 = r13
            r13 = r44
            long r37 = com.google.ads.interactivemedia.v3.internal.vf.c(r9, r11, r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r11 = 1000000(0xf4240, double:4.940656E-318)
            r9 = r7
            r13 = r44
            long r7 = com.google.ads.interactivemedia.v3.internal.vf.c(r9, r11, r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r9 = "messageData"
            r10 = 0
            java.lang.String r9 = b(r5, r9, r10)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r15.reset()     // Catch:{ XmlPullParserException -> 0x0d6f }
            org.xmlpull.v1.XmlSerializer r10 = android.util.Xml.newSerializer()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r11 = "UTF-8"
            r10.setOutput(r15, r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r5.nextToken()     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0ae7:
            java.lang.String r11 = "Event"
            boolean r11 = com.google.ads.interactivemedia.v3.internal.qi.a(r5, r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r11 != 0) goto L_0x0b8d
            int r11 = r5.getEventType()     // Catch:{ XmlPullParserException -> 0x0d6f }
            switch(r11) {
                case 0: goto L_0x0b7e;
                case 1: goto L_0x0b78;
                case 2: goto L_0x0b51;
                case 3: goto L_0x0b43;
                case 4: goto L_0x0b39;
                case 5: goto L_0x0b2f;
                case 6: goto L_0x0b25;
                case 7: goto L_0x0b1b;
                case 8: goto L_0x0b10;
                case 9: goto L_0x0b05;
                case 10: goto L_0x0afa;
                default: goto L_0x0af6;
            }     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0af6:
            r25 = 0
            goto L_0x0b88
        L_0x0afa:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.docdecl(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b05:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.comment(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b10:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.processingInstruction(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b1b:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.ignorableWhitespace(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b25:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.entityRef(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b2f:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.cdsect(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b39:
            java.lang.String r11 = r5.getText()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.text(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b43:
            java.lang.String r11 = r5.getNamespace()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r12 = r5.getName()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.endTag(r11, r12)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b51:
            java.lang.String r11 = r5.getNamespace()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r12 = r5.getName()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.startTag(r11, r12)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r11 = 0
        L_0x0b5d:
            int r12 = r5.getAttributeCount()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r11 >= r12) goto L_0x0b75
            java.lang.String r12 = r5.getAttributeNamespace(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r13 = r5.getAttributeName(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r14 = r5.getAttributeValue(r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r10.attribute(r12, r13, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r11 = r11 + 1
            goto L_0x0b5d
        L_0x0b75:
            r25 = 0
            goto L_0x0b88
        L_0x0b78:
            r10.endDocument()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r25 = 0
            goto L_0x0b88
        L_0x0b7e:
            r25 = 0
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r25)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r12 = 0
            r10.startDocument(r12, r11)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0b88:
            r5.nextToken()     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0ae7
        L_0x0b8d:
            r25 = 0
            r10.flush()     // Catch:{ XmlPullParserException -> 0x0d6f }
            byte[] r10 = r15.toByteArray()     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Long r14 = java.lang.Long.valueOf(r7)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r9 != 0) goto L_0x0b9f
            r48 = r10
            goto L_0x0ba5
        L_0x0b9f:
            byte[] r7 = com.google.ads.interactivemedia.v3.internal.vf.c(r9)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r48 = r7
        L_0x0ba5:
            com.google.ads.interactivemedia.v3.internal.ju r12 = new com.google.ads.interactivemedia.v3.internal.ju     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = r12
            r8 = r2
            r9 = r3
            r10 = r37
            r98 = r15
            r15 = r12
            r12 = r29
            r72 = r1
            r1 = r14
            r14 = r48
            r7.<init>(r8, r9, r10, r12, r14)     // Catch:{ XmlPullParserException -> 0x0d6f }
            android.util.Pair r1 = android.util.Pair.create(r1, r15)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            goto L_0x0bcc
        L_0x0bc1:
            r72 = r1
            r98 = r15
            r25 = 0
            r46 = 0
            f(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0bcc:
            java.lang.String r1 = "EventStream"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0c11
            int r1 = r4.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            long[] r12 = new long[r1]     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r1 = r4.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ju[] r13 = new com.google.ads.interactivemedia.v3.internal.ju[r1]     // Catch:{ XmlPullParserException -> 0x0d6f }
            r1 = 0
        L_0x0be1:
            int r7 = r4.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 >= r7) goto L_0x0c00
            java.lang.Object r7 = r4.get(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            android.util.Pair r7 = (android.util.Pair) r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Object r8 = r7.first     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ XmlPullParserException -> 0x0d6f }
            long r8 = r8.longValue()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r12[r1] = r8     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Object r7 = r7.second     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ju r7 = (com.google.ads.interactivemedia.v3.internal.ju) r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            r13[r1] = r7     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r1 = r1 + 1
            goto L_0x0be1
        L_0x0c00:
            com.google.ads.interactivemedia.v3.internal.yu r1 = new com.google.ads.interactivemedia.v3.internal.yu     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = r1
            r8 = r2
            r9 = r3
            r10 = r44
            r7.<init>(r8, r9, r10, r12, r13)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r7 = r71
            r7.add(r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r1 = 0
            goto L_0x0c5d
        L_0x0c11:
            r15 = r98
            r1 = r72
            goto L_0x0a96
        L_0x0c17:
            r72 = r1
            r7 = r71
            r25 = 0
            r46 = 0
            java.lang.String r1 = "SegmentBase"
            boolean r1 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 == 0) goto L_0x0c33
            r1 = 0
            com.google.ads.interactivemedia.v3.internal.pg r2 = r6.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r33 = r2
            r4 = r50
            r8 = r51
            goto L_0x0c61
        L_0x0c33:
            r1 = 0
            java.lang.String r2 = "SegmentList"
            boolean r2 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 == 0) goto L_0x0c47
            com.google.ads.interactivemedia.v3.internal.pd r2 = r6.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r33 = r2
            r4 = r50
            r8 = r51
            goto L_0x0c61
        L_0x0c47:
            java.lang.String r2 = "SegmentTemplate"
            boolean r2 = com.google.ads.interactivemedia.v3.internal.qi.b(r5, r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 == 0) goto L_0x0c5a
            com.google.ads.interactivemedia.v3.internal.pe r2 = r6.a(r5, r1)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r33 = r2
            r4 = r50
            r8 = r51
            goto L_0x0c61
        L_0x0c5a:
            f(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0c5d:
            r4 = r50
            r8 = r51
        L_0x0c61:
            java.lang.String r2 = "Period"
            boolean r2 = com.google.ads.interactivemedia.v3.internal.qi.a(r5, r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r2 == 0) goto L_0x0cdc
            com.google.ads.interactivemedia.v3.internal.ov r2 = new com.google.ads.interactivemedia.v3.internal.ov     // Catch:{ XmlPullParserException -> 0x0d6f }
            r33 = r2
            r37 = r72
            r38 = r7
            r33.<init>(r34, r35, r37, r38)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Long r3 = java.lang.Long.valueOf(r39)     // Catch:{ XmlPullParserException -> 0x0d6f }
            android.util.Pair r2 = android.util.Pair.create(r2, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Object r3 = r2.first     // Catch:{ XmlPullParserException -> 0x0d6f }
            com.google.ads.interactivemedia.v3.internal.ov r3 = (com.google.ads.interactivemedia.v3.internal.ov) r3     // Catch:{ XmlPullParserException -> 0x0d6f }
            long r7 = r3.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x0cb4
            if (r16 == 0) goto L_0x0c97
            r2 = r28
            r4 = r41
            r9 = r52
            r26 = 1
            goto L_0x0d0c
        L_0x0c97:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ XmlPullParserException -> 0x0d6f }
            int r2 = r28.size()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r3 = 47
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.<init>(r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r3 = "Unable to determine start of period "
            r4.append(r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r4.append(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r2 = r4.toString()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r1.<init>(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            throw r1     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0cb4:
            java.lang.Object r2 = r2.second     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ XmlPullParserException -> 0x0d6f }
            long r7 = r2.longValue()     // Catch:{ XmlPullParserException -> 0x0d6f }
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 != 0) goto L_0x0ccd
            r2 = r28
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0cd2
        L_0x0ccd:
            long r9 = r3.b     // Catch:{ XmlPullParserException -> 0x0d6f }
            long r7 = r7 + r9
            r2 = r28
        L_0x0cd2:
            r2.add(r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            r42 = r7
            r4 = r41
            r9 = r52
            goto L_0x0d0c
        L_0x0cdc:
            r2 = r5
            r1 = r6
            r5 = r42
            r9 = r52
            r14 = r53
            r10 = r55
            r12 = r69
            r3 = r72
            goto L_0x01cc
        L_0x0cec:
            r0 = move-exception
            r6 = r1
            goto L_0x0d70
        L_0x0cf0:
            r41 = r4
            r42 = r5
            r52 = r9
            r55 = r10
            r69 = r12
            r53 = r14
            r25 = 0
            r46 = 0
            r6 = r1
            r5 = r2
            r2 = r28
            r1 = 0
            f(r5)     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0d08:
            r4 = r41
            r9 = r52
        L_0x0d0c:
            java.lang.String r3 = "MPD"
            boolean r3 = com.google.ads.interactivemedia.v3.internal.qi.a(r5, r3)     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r3 == 0) goto L_0x0d52
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r69 > r7 ? 1 : (r69 == r7 ? 0 : -1))
            if (r1 != 0) goto L_0x0d2f
            int r1 = (r42 > r7 ? 1 : (r42 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x0d24
            r12 = r42
            goto L_0x0d31
        L_0x0d24:
            if (r16 == 0) goto L_0x0d27
            goto L_0x0d2f
        L_0x0d27:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r2 = "Unable to determine duration of static manifest."
            r1.<init>(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            throw r1     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0d2f:
            r12 = r69
        L_0x0d31:
            boolean r1 = r2.isEmpty()     // Catch:{ XmlPullParserException -> 0x0d6f }
            if (r1 != 0) goto L_0x0d4a
            com.google.ads.interactivemedia.v3.internal.tc r1 = new com.google.ads.interactivemedia.v3.internal.tc     // Catch:{ XmlPullParserException -> 0x0d6f }
            r9 = r1
            r10 = r55
            r14 = r53
            r25 = r27
            r26 = r31
            r27 = r32
            r28 = r2
            r9.<init>(r10, r12, r14, r16, r17, r19, r21, r23, r25, r26, r27, r28)     // Catch:{ XmlPullParserException -> 0x0d6f }
            return r1
        L_0x0d4a:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r2 = "No periods found."
            r1.<init>(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            throw r1     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0d52:
            r3 = r1
            r28 = r2
            r2 = r5
            r5 = r42
            r14 = r53
            r10 = r55
            r12 = r69
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1 = r97
            goto L_0x0093
        L_0x0d67:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ XmlPullParserException -> 0x0d6f }
            java.lang.String r2 = "inputStream does not contain a valid media presentation description"
            r1.<init>(r2)     // Catch:{ XmlPullParserException -> 0x0d6f }
            throw r1     // Catch:{ XmlPullParserException -> 0x0d6f }
        L_0x0d6f:
            r0 = move-exception
        L_0x0d70:
            r1 = r0
            com.google.ads.interactivemedia.v3.internal.ca r2 = new com.google.ads.interactivemedia.v3.internal.ca
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ot.a(android.net.Uri, java.io.InputStream):com.google.ads.interactivemedia.v3.internal.tc");
    }

    private static int a(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (!TextUtils.isEmpty(attributeValue)) {
            if (MimeTypes.BASE_TYPE_AUDIO.equals(attributeValue)) {
                return 1;
            }
            if ("video".equals(attributeValue)) {
                return 2;
            }
            if ("text".equals(attributeValue)) {
                return 3;
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<java.lang.String, com.google.ads.interactivemedia.v3.internal.fa.a> b(org.xmlpull.v1.XmlPullParser r15) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            java.lang.String r0 = "schemeIdUri"
            r1 = 0
            java.lang.String r0 = r15.getAttributeValue(r1, r0)
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x009e
            java.lang.String r0 = com.google.ads.interactivemedia.v3.internal.vf.d(r0)
            r4 = -1
            int r5 = r0.hashCode()
            r6 = 489446379(0x1d2c5beb, float:2.281153E-21)
            if (r5 == r6) goto L_0x003a
            r6 = 755418770(0x2d06c692, float:7.66111E-12)
            if (r5 == r6) goto L_0x002f
            r6 = 1812765994(0x6c0c9d2a, float:6.799672E26)
            if (r5 == r6) goto L_0x0024
            goto L_0x0045
        L_0x0024:
            java.lang.String r5 = "urn:mpeg:dash:mp4protection:2011"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x0045
            r0 = 0
            goto L_0x0046
        L_0x002f:
            java.lang.String r5 = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x0045
            r0 = 2
            goto L_0x0046
        L_0x003a:
            java.lang.String r5 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x0045
            r0 = 1
            goto L_0x0046
        L_0x0045:
            r0 = -1
        L_0x0046:
            switch(r0) {
                case 0: goto L_0x0058;
                case 1: goto L_0x0051;
                case 2: goto L_0x004a;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x009e
        L_0x004a:
            java.util.UUID r0 = com.google.ads.interactivemedia.v3.internal.at.d
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = 0
            goto L_0x00a3
        L_0x0051:
            java.util.UUID r0 = com.google.ads.interactivemedia.v3.internal.at.e
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = 0
            goto L_0x00a3
        L_0x0058:
            java.lang.String r0 = "value"
            java.lang.String r0 = r15.getAttributeValue(r1, r0)
            java.lang.String r4 = "default_KID"
            java.lang.String r4 = com.google.ads.interactivemedia.v3.internal.qi.d(r15, r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x0099
            java.lang.String r5 = "00000000-0000-0000-0000-000000000000"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0099
            java.lang.String r5 = "\\s+"
            java.lang.String[] r4 = r4.split(r5)
            int r5 = r4.length
            java.util.UUID[] r5 = new java.util.UUID[r5]
            r6 = 0
        L_0x007d:
            int r7 = r4.length
            if (r6 >= r7) goto L_0x008b
            r7 = r4[r6]
            java.util.UUID r7 = java.util.UUID.fromString(r7)
            r5[r6] = r7
            int r6 = r6 + 1
            goto L_0x007d
        L_0x008b:
            java.util.UUID r4 = com.google.ads.interactivemedia.v3.internal.at.b
            byte[] r4 = com.google.ads.interactivemedia.v3.internal.ho.a(r4, r5, r1)
            java.util.UUID r5 = com.google.ads.interactivemedia.v3.internal.at.b
            r6 = r1
            r7 = 0
            r14 = r5
            r5 = r0
            r0 = r14
            goto L_0x00a3
        L_0x0099:
            r5 = r0
            r0 = r1
            r4 = r0
            r6 = r4
            goto L_0x00a2
        L_0x009e:
            r0 = r1
            r4 = r0
            r5 = r4
            r6 = r5
        L_0x00a2:
            r7 = 0
        L_0x00a3:
            r15.next()
            java.lang.String r8 = "ms:laurl"
            boolean r8 = com.google.ads.interactivemedia.v3.internal.qi.b(r15, r8)
            if (r8 == 0) goto L_0x00ba
            java.lang.String r6 = "licenseUrl"
            java.lang.String r6 = r15.getAttributeValue(r1, r6)
            r9 = r0
            r12 = r4
            r10 = r6
            r13 = r7
            goto L_0x013e
        L_0x00ba:
            java.lang.String r8 = "widevine:license"
            boolean r8 = com.google.ads.interactivemedia.v3.internal.qi.b(r15, r8)
            if (r8 == 0) goto L_0x00dc
            java.lang.String r7 = "robustness_level"
            java.lang.String r7 = r15.getAttributeValue(r1, r7)
            if (r7 == 0) goto L_0x00d5
            java.lang.String r8 = "HW"
            boolean r7 = r7.startsWith(r8)
            if (r7 == 0) goto L_0x00d5
            r7 = 1
            goto L_0x00d6
        L_0x00d5:
            r7 = 0
        L_0x00d6:
            r9 = r0
            r12 = r4
            r10 = r6
            r13 = r7
            goto L_0x013e
        L_0x00dc:
            r8 = 4
            if (r4 != 0) goto L_0x010c
            java.lang.String r9 = "pssh"
            boolean r9 = com.google.ads.interactivemedia.v3.internal.qi.c(r15, r9)
            if (r9 == 0) goto L_0x010c
            int r9 = r15.next()
            if (r9 != r8) goto L_0x010c
            java.lang.String r0 = r15.getText()
            byte[] r0 = android.util.Base64.decode(r0, r3)
            java.util.UUID r4 = com.google.ads.interactivemedia.v3.internal.ho.a(r0)
            if (r4 != 0) goto L_0x0107
            java.lang.String r0 = "MpdParser"
            java.lang.String r8 = "Skipping malformed cenc:pssh data"
            android.util.Log.w(r0, r8)
            r12 = r1
            r9 = r4
            r10 = r6
            r13 = r7
            goto L_0x013e
        L_0x0107:
            r12 = r0
            r9 = r4
            r10 = r6
            r13 = r7
            goto L_0x013e
        L_0x010c:
            if (r4 != 0) goto L_0x0137
            java.util.UUID r9 = com.google.ads.interactivemedia.v3.internal.at.e
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0137
            java.lang.String r9 = "mspr:pro"
            boolean r9 = com.google.ads.interactivemedia.v3.internal.qi.b(r15, r9)
            if (r9 == 0) goto L_0x0137
            int r9 = r15.next()
            if (r9 != r8) goto L_0x0137
            java.util.UUID r4 = com.google.ads.interactivemedia.v3.internal.at.e
            java.lang.String r8 = r15.getText()
            byte[] r8 = android.util.Base64.decode(r8, r3)
            byte[] r4 = com.google.ads.interactivemedia.v3.internal.ho.a(r4, r8)
            r9 = r0
            r12 = r4
            r10 = r6
            r13 = r7
            goto L_0x013e
        L_0x0137:
            f(r15)
            r9 = r0
            r12 = r4
            r10 = r6
            r13 = r7
        L_0x013e:
            java.lang.String r0 = "ContentProtection"
            boolean r0 = com.google.ads.interactivemedia.v3.internal.qi.a(r15, r0)
            if (r0 == 0) goto L_0x0158
            if (r9 == 0) goto L_0x0152
            com.google.ads.interactivemedia.v3.internal.fa$a r15 = new com.google.ads.interactivemedia.v3.internal.fa$a
            java.lang.String r11 = "video/mp4"
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L_0x0153
        L_0x0152:
            r15 = r1
        L_0x0153:
            android.util.Pair r15 = android.util.Pair.create(r5, r15)
            return r15
        L_0x0158:
            r0 = r9
            r6 = r10
            r4 = r12
            r7 = r13
            goto L_0x00a3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ot.b(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    private final pg a(XmlPullParser xmlPullParser, pg pgVar) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        pg pgVar2 = pgVar;
        long c2 = c(xmlPullParser2, "timescale", pgVar2 != null ? pgVar2.b : 1);
        long j3 = 0;
        long c3 = c(xmlPullParser2, "presentationTimeOffset", pgVar2 != null ? pgVar2.c : 0);
        long j4 = pgVar2 != null ? pgVar2.d : 0;
        if (pgVar2 != null) {
            j3 = pgVar2.e;
        }
        ox oxVar = null;
        String attributeValue = xmlPullParser2.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j2 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - j2) + 1;
        } else {
            j = j3;
            j2 = j4;
        }
        if (pgVar2 != null) {
            oxVar = pgVar2.a;
        }
        do {
            xmlPullParser.next();
            if (qi.b(xmlPullParser2, "Initialization")) {
                oxVar = d(xmlPullParser);
            } else {
                f(xmlPullParser);
            }
        } while (!qi.a(xmlPullParser2, "SegmentBase"));
        pg pgVar3 = new pg(oxVar, c2, c3, j2, j);
        return pgVar3;
    }

    private final pd a(XmlPullParser xmlPullParser, pd pdVar) throws XmlPullParserException, IOException {
        List list;
        List list2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        pd pdVar2 = pdVar;
        long j = 1;
        long c2 = c(xmlPullParser2, "timescale", pdVar2 != null ? pdVar2.b : 1);
        long c3 = c(xmlPullParser2, "presentationTimeOffset", pdVar2 != null ? pdVar2.c : 0);
        long c4 = c(xmlPullParser2, "duration", pdVar2 != null ? pdVar2.e : -9223372036854775807L);
        String str = "startNumber";
        if (pdVar2 != null) {
            j = pdVar2.d;
        }
        long c5 = c(xmlPullParser2, str, j);
        List list3 = null;
        ox oxVar = null;
        List list4 = null;
        do {
            xmlPullParser.next();
            if (qi.b(xmlPullParser2, "Initialization")) {
                oxVar = d(xmlPullParser);
            } else if (qi.b(xmlPullParser2, "SegmentTimeline")) {
                list4 = c(xmlPullParser);
            } else if (qi.b(xmlPullParser2, "SegmentURL")) {
                if (list3 == null) {
                    list3 = new ArrayList();
                }
                list3.add(a(xmlPullParser2, "media", "mediaRange"));
            } else {
                f(xmlPullParser);
            }
        } while (!qi.a(xmlPullParser2, "SegmentList"));
        if (pdVar2 != null) {
            if (oxVar == null) {
                oxVar = pdVar2.a;
            }
            if (list4 == null) {
                list4 = pdVar2.f;
            }
            if (list3 == null) {
                list3 = pdVar2.g;
            }
            list = list3;
            list2 = list4;
        } else {
            list = list3;
            list2 = list4;
        }
        pd pdVar3 = new pd(oxVar, c2, c3, c5, c4, list2, list);
        return pdVar3;
    }

    private final pe a(XmlPullParser xmlPullParser, pe peVar) throws XmlPullParserException, IOException {
        List list;
        ox oxVar;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        pe peVar2 = peVar;
        long j = 1;
        long c2 = c(xmlPullParser2, "timescale", peVar2 != null ? peVar2.b : 1);
        long c3 = c(xmlPullParser2, "presentationTimeOffset", peVar2 != null ? peVar2.c : 0);
        long c4 = c(xmlPullParser2, "duration", peVar2 != null ? peVar2.e : -9223372036854775807L);
        String str = "startNumber";
        if (peVar2 != null) {
            j = peVar2.d;
        }
        long c5 = c(xmlPullParser2, str, j);
        ox oxVar2 = null;
        pi a2 = a(xmlPullParser2, "media", peVar2 != null ? peVar2.h : null);
        pi a3 = a(xmlPullParser2, "initialization", peVar2 != null ? peVar2.g : null);
        List list2 = null;
        do {
            xmlPullParser.next();
            if (qi.b(xmlPullParser2, "Initialization")) {
                oxVar2 = d(xmlPullParser);
            } else if (qi.b(xmlPullParser2, "SegmentTimeline")) {
                list2 = c(xmlPullParser);
            } else {
                f(xmlPullParser);
            }
        } while (!qi.a(xmlPullParser2, "SegmentTemplate"));
        if (peVar2 != null) {
            if (oxVar2 == null) {
                oxVar2 = peVar2.a;
            }
            if (list2 == null) {
                list2 = peVar2.f;
            }
            list = list2;
            oxVar = oxVar2;
        } else {
            list = list2;
            oxVar = oxVar2;
        }
        pe peVar3 = new pe(oxVar, c2, c3, c5, c4, list, a3, a2);
        return peVar3;
    }

    private final List<pf> c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (qi.b(xmlPullParser, "S")) {
                j = c(xmlPullParser, "t", j);
                long c2 = c(xmlPullParser, "d", -9223372036854775807L);
                int a2 = a(xmlPullParser, "r", 0) + 1;
                for (int i = 0; i < a2; i++) {
                    arrayList.add(new pf(j, c2));
                    j += c2;
                }
            } else {
                f(xmlPullParser);
            }
        } while (!qi.a(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    private static pi a(XmlPullParser xmlPullParser, String str, pi piVar) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? pi.a(attributeValue) : piVar;
    }

    private final ox d(XmlPullParser xmlPullParser) {
        return a(xmlPullParser, "sourceURL", "range");
    }

    private final ox a(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            long parseLong = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - parseLong) + 1;
                j2 = parseLong;
            } else {
                j = -1;
                j2 = parseLong;
            }
        } else {
            j = -1;
            j2 = 0;
        }
        ox oxVar = new ox(attributeValue, j2, j);
        return oxVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int e(org.xmlpull.v1.XmlPullParser r6) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            java.lang.String r0 = "schemeIdUri"
            r1 = 0
            java.lang.String r0 = b(r6, r0, r1)
            java.lang.String r2 = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011"
            boolean r2 = r2.equals(r0)
            r3 = 2
            r4 = 1
            r5 = -1
            if (r2 == 0) goto L_0x001c
            java.lang.String r0 = "value"
            int r3 = a(r6, r0, r5)
            goto L_0x007f
        L_0x001c:
            java.lang.String r2 = "tag:dolby.com,2014:dash:audio_channel_configuration:2011"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007e
            java.lang.String r0 = "value"
            java.lang.String r0 = r6.getAttributeValue(r1, r0)
            java.lang.String r0 = com.google.ads.interactivemedia.v3.internal.vf.d(r0)
            if (r0 == 0) goto L_0x007e
            int r1 = r0.hashCode()
            r2 = 1596796(0x185d7c, float:2.237588E-39)
            if (r1 == r2) goto L_0x0068
            r2 = 2937391(0x2cd22f, float:4.116161E-39)
            if (r1 == r2) goto L_0x005e
            r2 = 3094035(0x2f3613, float:4.335666E-39)
            if (r1 == r2) goto L_0x0054
            r2 = 3133436(0x2fcffc, float:4.390879E-39)
            if (r1 == r2) goto L_0x004a
            goto L_0x0072
        L_0x004a:
            java.lang.String r1 = "fa01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 3
            goto L_0x0073
        L_0x0054:
            java.lang.String r1 = "f801"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 2
            goto L_0x0073
        L_0x005e:
            java.lang.String r1 = "a000"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 1
            goto L_0x0073
        L_0x0068:
            java.lang.String r1 = "4000"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 0
            goto L_0x0073
        L_0x0072:
            r0 = -1
        L_0x0073:
            switch(r0) {
                case 0: goto L_0x007c;
                case 1: goto L_0x007f;
                case 2: goto L_0x007a;
                case 3: goto L_0x0077;
                default: goto L_0x0076;
            }
        L_0x0076:
            goto L_0x007e
        L_0x0077:
            r3 = 8
            goto L_0x007f
        L_0x007a:
            r3 = 6
            goto L_0x007f
        L_0x007c:
            r3 = 1
            goto L_0x007f
        L_0x007e:
            r3 = -1
        L_0x007f:
            r6.next()
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = com.google.ads.interactivemedia.v3.internal.qi.a(r6, r0)
            if (r0 == 0) goto L_0x007f
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ot.e(org.xmlpull.v1.XmlPullParser):int");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.String r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = -1
            int r2 = r7.hashCode()
            r3 = 8
            r4 = 4
            r5 = 2
            r6 = 1
            switch(r2) {
                case -2060497896: goto L_0x007b;
                case -1724546052: goto L_0x0070;
                case -1580883024: goto L_0x0065;
                case -1408024454: goto L_0x005b;
                case 99825: goto L_0x0051;
                case 3343801: goto L_0x0047;
                case 3530173: goto L_0x003c;
                case 552573414: goto L_0x0032;
                case 899152809: goto L_0x0028;
                case 1629013393: goto L_0x001e;
                case 1855372047: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0085
        L_0x0013:
            java.lang.String r2 = "supplementary"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 2
            goto L_0x0086
        L_0x001e:
            java.lang.String r2 = "emergency"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 5
            goto L_0x0086
        L_0x0028:
            java.lang.String r2 = "commentary"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 3
            goto L_0x0086
        L_0x0032:
            java.lang.String r2 = "caption"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 6
            goto L_0x0086
        L_0x003c:
            java.lang.String r2 = "sign"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 8
            goto L_0x0086
        L_0x0047:
            java.lang.String r2 = "main"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 0
            goto L_0x0086
        L_0x0051:
            java.lang.String r2 = "dub"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 4
            goto L_0x0086
        L_0x005b:
            java.lang.String r2 = "alternate"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 1
            goto L_0x0086
        L_0x0065:
            java.lang.String r2 = "enhanced-audio-intelligibility"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 10
            goto L_0x0086
        L_0x0070:
            java.lang.String r2 = "description"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 9
            goto L_0x0086
        L_0x007b:
            java.lang.String r2 = "subtitle"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0085
            r7 = 7
            goto L_0x0086
        L_0x0085:
            r7 = -1
        L_0x0086:
            switch(r7) {
                case 0: goto L_0x00a2;
                case 1: goto L_0x00a1;
                case 2: goto L_0x00a0;
                case 3: goto L_0x009f;
                case 4: goto L_0x009c;
                case 5: goto L_0x0099;
                case 6: goto L_0x0096;
                case 7: goto L_0x0093;
                case 8: goto L_0x0090;
                case 9: goto L_0x008d;
                case 10: goto L_0x008a;
                default: goto L_0x0089;
            }
        L_0x0089:
            return r0
        L_0x008a:
            r7 = 512(0x200, float:7.175E-43)
            return r7
        L_0x008d:
            r7 = 1024(0x400, float:1.435E-42)
            return r7
        L_0x0090:
            r7 = 256(0x100, float:3.59E-43)
            return r7
        L_0x0093:
            r7 = 128(0x80, float:1.794E-43)
            return r7
        L_0x0096:
            r7 = 64
            return r7
        L_0x0099:
            r7 = 32
            return r7
        L_0x009c:
            r7 = 16
            return r7
        L_0x009f:
            return r3
        L_0x00a0:
            return r4
        L_0x00a1:
            return r5
        L_0x00a2:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ot.a(java.lang.String):int");
    }

    private static void f(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (qi.b(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (qi.b(xmlPullParser)) {
                    i++;
                } else if (qi.a(xmlPullParser)) {
                    i--;
                }
            }
        }
    }

    private static boolean b(String str) {
        return un.c(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str);
    }

    private static int a(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        qi.c(i == i2);
        return i;
    }

    private static ou a(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String b2 = b(xmlPullParser, "schemeIdUri", "");
        String b3 = b(xmlPullParser, "value", (String) null);
        String b4 = b(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!qi.a(xmlPullParser, str));
        return new ou(b2, b3, b4);
    }

    private static float a(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = a.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    private static long a(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return vf.f(attributeValue);
    }

    private static long b(XmlPullParser xmlPullParser, String str, long j) throws ca {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return -9223372036854775807L;
        }
        return vf.g(attributeValue);
    }

    private static String b(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return qi.b(str, xmlPullParser.getText());
    }

    private static int a(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return i;
        }
        return Integer.parseInt(attributeValue);
    }

    private static long c(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return Long.parseLong(attributeValue);
    }

    private static String b(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }
}
