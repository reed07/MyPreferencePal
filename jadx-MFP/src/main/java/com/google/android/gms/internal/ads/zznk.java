package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzhp.zza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class zznk extends DefaultHandler implements zzpm<zznj> {
    private static final Pattern zzbcz = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern zzbda = Pattern.compile("CC([1-4])=.*");
    private static final Pattern zzbdb = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private final String zzbdc;
    private final XmlPullParserFactory zzbdd;

    public zznk() {
        this(null);
    }

    private zznk(String str) {
        this.zzbdc = null;
        try {
            this.zzbdd = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r7v2 */
    /* JADX WARNING: type inference failed for: r38v0 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r38v1 */
    /* JADX WARNING: type inference failed for: r4v27, types: [com.google.android.gms.internal.ads.zznw] */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r4v28, types: [com.google.android.gms.internal.ads.zznv] */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r4v29, types: [com.google.android.gms.internal.ads.zzny] */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r38v2 */
    /* JADX WARNING: type inference failed for: r36v0 */
    /* JADX WARNING: type inference failed for: r12v9, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r50v0, types: [com.google.android.gms.internal.ads.zznr] */
    /* JADX WARNING: type inference failed for: r61v0, types: [com.google.android.gms.internal.ads.zzns] */
    /* JADX WARNING: type inference failed for: r3v53, types: [com.google.android.gms.internal.ads.zznv] */
    /* JADX WARNING: type inference failed for: r36v5 */
    /* JADX WARNING: type inference failed for: r3v56, types: [com.google.android.gms.internal.ads.zzny] */
    /* JADX WARNING: type inference failed for: r36v6 */
    /* JADX WARNING: type inference failed for: r38v3 */
    /* JADX WARNING: type inference failed for: r7v22 */
    /* JADX WARNING: type inference failed for: r7v23 */
    /* JADX WARNING: type inference failed for: r7v24 */
    /* JADX WARNING: type inference failed for: r38v4 */
    /* JADX WARNING: type inference failed for: r50v2, types: [com.google.android.gms.internal.ads.zznr] */
    /* JADX WARNING: type inference failed for: r61v6, types: [com.google.android.gms.internal.ads.zzns] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v2
  assigns: []
  uses: []
  mth insns count: 932
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
    /* JADX WARNING: Removed duplicated region for block: B:226:0x05fa A[Catch:{ XmlPullParserException -> 0x08ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x05fd A[Catch:{ XmlPullParserException -> 0x08ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x061b A[Catch:{ XmlPullParserException -> 0x08ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0641 A[Catch:{ XmlPullParserException -> 0x08ad }, LOOP:4: B:97:0x0384->B:241:0x0641, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x0759 A[Catch:{ XmlPullParserException -> 0x08ad }, LOOP:2: B:53:0x01d8->B:275:0x0759, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x0830 A[Catch:{ XmlPullParserException -> 0x08ad }, LOOP:1: B:45:0x011a->B:299:0x0830, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x087e A[Catch:{ XmlPullParserException -> 0x08ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x0890 A[Catch:{ XmlPullParserException -> 0x08ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0898 A[Catch:{ XmlPullParserException -> 0x08ad }, LOOP:0: B:30:0x0093->B:320:0x0898, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:326:0x085c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:329:0x07bd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06c1 A[EDGE_INSN: B:330:0x06c1->B:258:0x06c1 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x0429 A[EDGE_INSN: B:332:0x0429->B:124:0x0429 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 13 */
    /* renamed from: zzc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zznj zzb(android.net.Uri r77, java.io.InputStream r78) throws java.io.IOException {
        /*
            r76 = this;
            r1 = r76
            org.xmlpull.v1.XmlPullParserFactory r0 = r1.zzbdd     // Catch:{ XmlPullParserException -> 0x08ad }
            org.xmlpull.v1.XmlPullParser r0 = r0.newPullParser()     // Catch:{ XmlPullParserException -> 0x08ad }
            r2 = 0
            r3 = r78
            r0.setInput(r3, r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r3 = r0.next()     // Catch:{ XmlPullParserException -> 0x08ad }
            r4 = 2
            if (r3 != r4) goto L_0x08a5
            java.lang.String r3 = "MPD"
            java.lang.String r5 = r0.getName()     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r3 = r3.equals(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x08a5
            java.lang.String r3 = r77.toString()     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r5 = "availabilityStartTime"
            java.lang.String r5 = r0.getAttributeValue(r2, r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r5 != 0) goto L_0x0034
            r11 = r6
            goto L_0x0039
        L_0x0034:
            long r8 = com.google.android.gms.internal.ads.zzqe.zzal(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            r11 = r8
        L_0x0039:
            java.lang.String r5 = "mediaPresentationDuration"
            long r8 = zza(r0, r5, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r5 = "minBufferTime"
            long r15 = zza(r0, r5, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r5 = "type"
            java.lang.String r5 = r0.getAttributeValue(r2, r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x0058
            java.lang.String r14 = "dynamic"
            boolean r5 = r5.equals(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x0058
            r17 = 1
            goto L_0x005a
        L_0x0058:
            r17 = 0
        L_0x005a:
            if (r17 == 0) goto L_0x0063
            java.lang.String r5 = "minimumUpdatePeriod"
            long r18 = zza(r0, r5, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x0065
        L_0x0063:
            r18 = r6
        L_0x0065:
            if (r17 == 0) goto L_0x006e
            java.lang.String r5 = "timeShiftBufferDepth"
            long r20 = zza(r0, r5, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x0070
        L_0x006e:
            r20 = r6
        L_0x0070:
            if (r17 == 0) goto L_0x0079
            java.lang.String r5 = "suggestedPresentationDelay"
            long r22 = zza(r0, r5, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x007b
        L_0x0079:
            r22 = r6
        L_0x007b:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r17 == 0) goto L_0x0085
            r24 = r6
            goto L_0x0087
        L_0x0085:
            r24 = 0
        L_0x0087:
            r26 = r2
            r14 = r3
            r77 = r11
            r10 = r24
            r3 = 0
            r24 = 0
            r25 = r26
        L_0x0093:
            r0.next()     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r4 = "BaseURL"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x00ba
            if (r3 != 0) goto L_0x00ad
            java.lang.String r3 = zzb(r0, r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            r14 = r3
            r44 = r8
            r42 = r15
            r3 = 1
            r15 = r5
            goto L_0x0854
        L_0x00ad:
            r30 = r3
            r44 = r8
            r39 = r10
            r41 = r14
            r42 = r15
            r15 = r5
            goto L_0x084e
        L_0x00ba:
            java.lang.String r4 = "UTCTiming"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x00dc
            java.lang.String r4 = "schemeIdUri"
            java.lang.String r4 = r0.getAttributeValue(r2, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "value"
            java.lang.String r12 = r0.getAttributeValue(r2, r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzob r13 = new com.google.android.gms.internal.ads.zzob     // Catch:{ XmlPullParserException -> 0x08ad }
            r13.<init>(r4, r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            r44 = r8
            r25 = r13
            r42 = r15
            r15 = r5
            goto L_0x0854
        L_0x00dc:
            java.lang.String r4 = "Location"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x00f5
            java.lang.String r4 = r0.nextText()     // Catch:{ XmlPullParserException -> 0x08ad }
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            r26 = r4
            r44 = r8
            r42 = r15
            r15 = r5
            goto L_0x0854
        L_0x00f5:
            java.lang.String r4 = "Period"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x0843
            if (r24 != 0) goto L_0x0843
            java.lang.String r4 = "id"
            java.lang.String r4 = r0.getAttributeValue(r2, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "start"
            long r12 = zza(r0, r12, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r2 = "duration"
            long r27 = zza(r0, r2, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r2.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r6 = r14
            r7 = 0
            r29 = 0
        L_0x011a:
            r0.next()     // Catch:{ XmlPullParserException -> 0x08ad }
            r30 = r3
            java.lang.String r3 = "BaseURL"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x0158
            if (r29 != 0) goto L_0x0142
            java.lang.String r3 = zzb(r0, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            r6 = r3
            r47 = r4
            r46 = r5
            r44 = r8
            r39 = r10
            r48 = r12
            r41 = r14
            r42 = r15
            r29 = 1
            r3 = r2
            r2 = 0
            goto L_0x07b5
        L_0x0142:
            r3 = r2
            r47 = r4
            r46 = r5
            r31 = r6
            r38 = r7
            r44 = r8
            r39 = r10
            r48 = r12
            r41 = r14
            r42 = r15
            r2 = 0
            goto L_0x07b1
        L_0x0158:
            java.lang.String r3 = "AdaptationSet"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x076c
            java.lang.String r3 = "id"
            r31 = r6
            r6 = -1
            int r33 = zza(r0, r3, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r3 = zza(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r6 = "mimeType"
            r34 = r3
            r3 = 0
            java.lang.String r6 = r0.getAttributeValue(r3, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            r38 = r7
            java.lang.String r7 = "codecs"
            java.lang.String r7 = r0.getAttributeValue(r3, r7)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r3 = "width"
            r39 = r10
            r10 = -1
            int r3 = zza(r0, r3, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r11 = "height"
            int r11 = zza(r0, r11, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            r10 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r10 = zza(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            r41 = r14
            java.lang.String r14 = "audioSamplingRate"
            r42 = r15
            r15 = -1
            int r14 = zza(r0, r14, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r15 = "lang"
            r44 = r8
            r8 = 0
            java.lang.String r9 = r0.getAttributeValue(r8, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r15.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r16 = r9
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r9.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r46 = r5
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r47 = r4
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r4.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r60 = r2
            r48 = r12
            r13 = r16
            r12 = r31
            r2 = r34
            r36 = r38
            r16 = 0
            r34 = 0
            r35 = -1
        L_0x01d8:
            r0.next()     // Catch:{ XmlPullParserException -> 0x08ad }
            r37 = r15
            java.lang.String r15 = "BaseURL"
            boolean r15 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r15 == 0) goto L_0x0217
            if (r16 != 0) goto L_0x0201
            java.lang.String r12 = zzb(r0, r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r62 = r8
            r72 = r10
            r73 = r11
            r74 = r14
            r5 = r37
            r16 = 1
            goto L_0x06b9
        L_0x0201:
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r62 = r8
            r72 = r10
            r73 = r11
            r61 = r12
            r74 = r14
            r5 = r37
            goto L_0x06b7
        L_0x0217:
            java.lang.String r15 = "ContentProtection"
            boolean r15 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r15 == 0) goto L_0x023e
            com.google.android.gms.internal.ads.zzhp$zza r15 = zzb(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r15 == 0) goto L_0x0228
            r8.add(r15)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0228:
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r62 = r8
            r72 = r10
            r73 = r11
            r61 = r12
            r74 = r14
            r5 = r37
            goto L_0x06b7
        L_0x023e:
            java.lang.String r15 = "ContentComponent"
            boolean r15 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r15 == 0) goto L_0x027b
            java.lang.String r15 = "lang"
            r61 = r12
            r12 = 0
            java.lang.String r15 = r0.getAttributeValue(r12, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r13 != 0) goto L_0x0253
            r13 = r15
            goto L_0x025d
        L_0x0253:
            if (r15 != 0) goto L_0x0256
            goto L_0x025d
        L_0x0256:
            boolean r12 = r13.equals(r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzpo.checkState(r12)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x025d:
            int r12 = zza(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r2 = zzd(r2, r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r62 = r8
            r72 = r10
            r73 = r11
            r74 = r14
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x027b:
            r61 = r12
            java.lang.String r12 = "Role"
            boolean r12 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x02ca
            java.lang.String r12 = "schemeIdUri"
            r15 = 0
            java.lang.String r12 = zzb(r0, r12, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            r62 = r8
            java.lang.String r8 = "value"
            java.lang.String r8 = zzb(r0, r8, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0294:
            r0.next()     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r15 = "Role"
            boolean r15 = com.google.android.gms.internal.ads.zzqg.zzc(r0, r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r15 == 0) goto L_0x0294
            java.lang.String r15 = "urn:mpeg:dash:role:2011"
            boolean r12 = r15.equals(r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x02b1
            java.lang.String r12 = "main"
            boolean r8 = r12.equals(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r8 == 0) goto L_0x02b1
            r8 = 1
            goto L_0x02b2
        L_0x02b1:
            r8 = 0
        L_0x02b2:
            r8 = r34 | r8
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r34 = r8
            r72 = r10
            r73 = r11
            r74 = r14
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x02ca:
            r62 = r8
            java.lang.String r8 = "AudioChannelConfiguration"
            boolean r8 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r8 == 0) goto L_0x02ee
            int r8 = zze(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r35 = r8
            r72 = r10
            r73 = r11
            r74 = r14
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x02ee:
            java.lang.String r8 = "Accessibility"
            boolean r8 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r8 == 0) goto L_0x0311
            java.lang.String r8 = "Accessibility"
            com.google.android.gms.internal.ads.zznm r8 = zza(r0, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            r9.add(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r72 = r10
            r73 = r11
            r74 = r14
            r5 = r37
            goto L_0x06b7
        L_0x0311:
            java.lang.String r8 = "SupplementalProperty"
            boolean r8 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r8 == 0) goto L_0x0334
            java.lang.String r8 = "SupplementalProperty"
            com.google.android.gms.internal.ads.zznm r8 = zza(r0, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            r5.add(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r72 = r10
            r73 = r11
            r74 = r14
            r5 = r37
            goto L_0x06b7
        L_0x0334:
            java.lang.String r8 = "Representation"
            boolean r8 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r8 == 0) goto L_0x064b
            java.lang.String r8 = "id"
            r12 = 0
            java.lang.String r50 = r0.getAttributeValue(r12, r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r8 = "bandwidth"
            r12 = -1
            int r54 = zza(r0, r8, r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r8 = "mimeType"
            java.lang.String r8 = zzb(r0, r8, r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r15 = "codecs"
            java.lang.String r15 = zzb(r0, r15, r7)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "width"
            int r55 = zza(r0, r12, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "height"
            int r56 = zza(r0, r12, r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            float r57 = zza(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "audioSamplingRate"
            int r12 = zza(r0, r12, r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r3.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r70 = r6
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            r6.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r71 = r7
            r53 = r35
            r52 = r36
            r7 = r61
            r51 = 0
        L_0x0384:
            r0.next()     // Catch:{ XmlPullParserException -> 0x08ad }
            r72 = r10
            java.lang.String r10 = "BaseURL"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x03a1
            if (r51 != 0) goto L_0x041b
            java.lang.String r7 = zzb(r0, r7)     // Catch:{ XmlPullParserException -> 0x08ad }
            r73 = r11
            r63 = r52
            r10 = r53
            r51 = 1
            goto L_0x0421
        L_0x03a1:
            java.lang.String r10 = "AudioChannelConfiguration"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x03b3
            int r10 = zze(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
            r73 = r11
            r63 = r52
            goto L_0x0421
        L_0x03b3:
            java.lang.String r10 = "SegmentBase"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x03ca
            r10 = r52
            com.google.android.gms.internal.ads.zzny r10 = (com.google.android.gms.internal.ads.zzny) r10     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzny r10 = r1.zza(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            r63 = r10
            r73 = r11
            r10 = r53
            goto L_0x0421
        L_0x03ca:
            java.lang.String r10 = "SegmentList"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x03e1
            r10 = r52
            com.google.android.gms.internal.ads.zznv r10 = (com.google.android.gms.internal.ads.zznv) r10     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznv r10 = r1.zza(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            r63 = r10
            r73 = r11
            r10 = r53
            goto L_0x0421
        L_0x03e1:
            java.lang.String r10 = "SegmentTemplate"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x03f8
            r10 = r52
            com.google.android.gms.internal.ads.zznw r10 = (com.google.android.gms.internal.ads.zznw) r10     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznw r10 = r1.zza(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            r63 = r10
            r73 = r11
            r10 = r53
            goto L_0x0421
        L_0x03f8:
            java.lang.String r10 = "ContentProtection"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x040a
            com.google.android.gms.internal.ads.zzhp$zza r10 = zzb(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x041b
            r3.add(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x041b
        L_0x040a:
            java.lang.String r10 = "InbandEventStream"
            boolean r10 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x041b
            java.lang.String r10 = "InbandEventStream"
            com.google.android.gms.internal.ads.zznm r10 = zza(r0, r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            r6.add(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x041b:
            r73 = r11
            r63 = r52
            r10 = r53
        L_0x0421:
            java.lang.String r11 = "Representation"
            boolean r11 = com.google.android.gms.internal.ads.zzqg.zzc(r0, r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r11 == 0) goto L_0x0641
            boolean r11 = com.google.android.gms.internal.ads.zzpt.zzab(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r11 == 0) goto L_0x045b
            if (r15 == 0) goto L_0x0454
            java.lang.String r11 = ","
            java.lang.String[] r11 = r15.split(r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            r74 = r14
            int r14 = r11.length     // Catch:{ XmlPullParserException -> 0x08ad }
            r75 = r5
            r5 = 0
        L_0x043d:
            if (r5 >= r14) goto L_0x0458
            r51 = r11[r5]     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r51 = com.google.android.gms.internal.ads.zzpt.zzae(r51)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r51 == 0) goto L_0x0451
            boolean r52 = com.google.android.gms.internal.ads.zzpt.zzab(r51)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r52 == 0) goto L_0x0451
            r5 = r51
            goto L_0x04d8
        L_0x0451:
            int r5 = r5 + 1
            goto L_0x043d
        L_0x0454:
            r75 = r5
            r74 = r14
        L_0x0458:
            r5 = 0
            goto L_0x04d8
        L_0x045b:
            r75 = r5
            r74 = r14
            boolean r5 = com.google.android.gms.internal.ads.zzpt.zzac(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x0487
            if (r15 == 0) goto L_0x0485
            java.lang.String r5 = ","
            java.lang.String[] r5 = r15.split(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r11 = r5.length     // Catch:{ XmlPullParserException -> 0x08ad }
            r14 = 0
        L_0x046f:
            if (r14 >= r11) goto L_0x0485
            r51 = r5[r14]     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r51 = com.google.android.gms.internal.ads.zzpt.zzae(r51)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r51 == 0) goto L_0x0482
            boolean r52 = com.google.android.gms.internal.ads.zzpt.zzac(r51)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r52 == 0) goto L_0x0482
            r5 = r51
            goto L_0x04d8
        L_0x0482:
            int r14 = r14 + 1
            goto L_0x046f
        L_0x0485:
            r5 = 0
            goto L_0x04d8
        L_0x0487:
            boolean r5 = zzx(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x048f
            r5 = r8
            goto L_0x04d8
        L_0x048f:
            java.lang.String r5 = "application/mp4"
            boolean r5 = r5.equals(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x04ad
            java.lang.String r5 = "stpp"
            boolean r5 = r5.equals(r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x04a2
            java.lang.String r5 = "application/ttml+xml"
            goto L_0x04d8
        L_0x04a2:
            java.lang.String r5 = "wvtt"
            boolean r5 = r5.equals(r15)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x04d7
            java.lang.String r5 = "application/x-mp4-vtt"
            goto L_0x04d8
        L_0x04ad:
            java.lang.String r5 = "application/x-rawcc"
            boolean r5 = r5.equals(r8)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x04d7
            if (r15 == 0) goto L_0x04d5
            java.lang.String r5 = "cea708"
            boolean r5 = r15.contains(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x04c2
            java.lang.String r5 = "application/cea-708"
            goto L_0x04d8
        L_0x04c2:
            java.lang.String r5 = "eia608"
            boolean r5 = r15.contains(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 != 0) goto L_0x04d2
            java.lang.String r5 = "cea608"
            boolean r5 = r15.contains(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r5 == 0) goto L_0x04d5
        L_0x04d2:
            java.lang.String r5 = "application/cea-608"
            goto L_0x04d8
        L_0x04d5:
            r5 = 0
            goto L_0x04d8
        L_0x04d7:
            r5 = 0
        L_0x04d8:
            if (r5 == 0) goto L_0x05e8
            boolean r11 = com.google.android.gms.internal.ads.zzpt.zzac(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r11 == 0) goto L_0x04f2
            r58 = 0
            r51 = r8
            r52 = r5
            r53 = r15
            r59 = r34
            com.google.android.gms.internal.ads.zzfs r5 = com.google.android.gms.internal.ads.zzfs.zza(r50, r51, r52, r53, r54, r55, r56, r57, r58, r59)     // Catch:{ XmlPullParserException -> 0x08ad }
            r64 = r5
            goto L_0x05f8
        L_0x04f2:
            boolean r11 = com.google.android.gms.internal.ads.zzpt.zzab(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r11 == 0) goto L_0x0510
            r57 = 0
            r51 = r8
            r52 = r5
            r53 = r15
            r55 = r10
            r56 = r12
            r58 = r34
            r59 = r13
            com.google.android.gms.internal.ads.zzfs r5 = com.google.android.gms.internal.ads.zzfs.zza(r50, r51, r52, r53, r54, r55, r56, r57, r58, r59)     // Catch:{ XmlPullParserException -> 0x08ad }
            r64 = r5
            goto L_0x05f8
        L_0x0510:
            boolean r10 = zzx(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x05e8
            java.lang.String r10 = "application/cea-608"
            boolean r10 = r10.equals(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x0575
            r10 = 0
        L_0x051f:
            int r11 = r9.size()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 >= r11) goto L_0x0571
            java.lang.Object r11 = r9.get(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznm r11 = (com.google.android.gms.internal.ads.zznm) r11     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "urn:scte:dash:cc:cea-608:2015"
            java.lang.String r14 = r11.zzbdi     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r12 = r12.equals(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x056e
            java.lang.String r12 = r11.value     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x056e
            java.util.regex.Pattern r12 = zzbda     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r14 = r11.value     // Catch:{ XmlPullParserException -> 0x08ad }
            java.util.regex.Matcher r12 = r12.matcher(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r14 = r12.matches()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r14 == 0) goto L_0x0551
            r14 = 1
            java.lang.String r10 = r12.group(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x0572
        L_0x0551:
            java.lang.String r12 = "MpdParser"
            java.lang.String r14 = "Unable to parse CEA-608 channel number from: "
            java.lang.String r11 = r11.value     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r51 = r11.length()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r51 == 0) goto L_0x0566
            java.lang.String r11 = r14.concat(r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x056b
        L_0x0566:
            java.lang.String r11 = new java.lang.String     // Catch:{ XmlPullParserException -> 0x08ad }
            r11.<init>(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x056b:
            android.util.Log.w(r12, r11)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x056e:
            int r10 = r10 + 1
            goto L_0x051f
        L_0x0571:
            r10 = -1
        L_0x0572:
            r57 = r10
            goto L_0x05d7
        L_0x0575:
            java.lang.String r10 = "application/cea-708"
            boolean r10 = r10.equals(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 == 0) goto L_0x05d5
            r10 = 0
        L_0x057e:
            int r11 = r9.size()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r10 >= r11) goto L_0x05d1
            java.lang.Object r11 = r9.get(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznm r11 = (com.google.android.gms.internal.ads.zznm) r11     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r12 = "urn:scte:dash:cc:cea-708:2015"
            java.lang.String r14 = r11.zzbdi     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r12 = r12.equals(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x05ce
            java.lang.String r12 = r11.value     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x05ce
            java.util.regex.Pattern r12 = zzbdb     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r14 = r11.value     // Catch:{ XmlPullParserException -> 0x08ad }
            java.util.regex.Matcher r12 = r12.matcher(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r14 = r12.matches()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r14 == 0) goto L_0x05b0
            r14 = 1
            java.lang.String r10 = r12.group(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x05d2
        L_0x05b0:
            r14 = 1
            java.lang.String r12 = "MpdParser"
            java.lang.String r14 = "Unable to parse CEA-708 service block number from: "
            java.lang.String r11 = r11.value     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r51 = r11.length()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r51 == 0) goto L_0x05c6
            java.lang.String r11 = r14.concat(r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x05cb
        L_0x05c6:
            java.lang.String r11 = new java.lang.String     // Catch:{ XmlPullParserException -> 0x08ad }
            r11.<init>(r14)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x05cb:
            android.util.Log.w(r12, r11)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x05ce:
            int r10 = r10 + 1
            goto L_0x057e
        L_0x05d1:
            r10 = -1
        L_0x05d2:
            r57 = r10
            goto L_0x05d7
        L_0x05d5:
            r57 = -1
        L_0x05d7:
            r51 = r8
            r52 = r5
            r53 = r15
            r55 = r34
            r56 = r13
            com.google.android.gms.internal.ads.zzfs r5 = com.google.android.gms.internal.ads.zzfs.zza(r50, r51, r52, r53, r54, r55, r56, r57)     // Catch:{ XmlPullParserException -> 0x08ad }
            r64 = r5
            goto L_0x05f8
        L_0x05e8:
            r51 = r8
            r52 = r5
            r53 = r15
            r55 = r34
            r56 = r13
            com.google.android.gms.internal.ads.zzfs r5 = com.google.android.gms.internal.ads.zzfs.zza(r50, r51, r52, r53, r54, r55, r56)     // Catch:{ XmlPullParserException -> 0x08ad }
            r64 = r5
        L_0x05f8:
            if (r63 == 0) goto L_0x05fd
            r66 = r63
            goto L_0x0604
        L_0x05fd:
            com.google.android.gms.internal.ads.zzny r5 = new com.google.android.gms.internal.ads.zzny     // Catch:{ XmlPullParserException -> 0x08ad }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x08ad }
            r66 = r5
        L_0x0604:
            com.google.android.gms.internal.ads.zznl r5 = new com.google.android.gms.internal.ads.zznl     // Catch:{ XmlPullParserException -> 0x08ad }
            r63 = r5
            r65 = r7
            r67 = r3
            r68 = r6
            r63.<init>(r64, r65, r66, r67, r68)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzfs r3 = r5.zzaad     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r3 = r3.zzzj     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r6 = android.text.TextUtils.isEmpty(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r6 != 0) goto L_0x0633
            boolean r6 = com.google.android.gms.internal.ads.zzpt.zzac(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r6 == 0) goto L_0x0623
            r3 = 2
            goto L_0x0634
        L_0x0623:
            boolean r6 = com.google.android.gms.internal.ads.zzpt.zzab(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r6 == 0) goto L_0x062b
            r3 = 1
            goto L_0x0634
        L_0x062b:
            boolean r3 = zzx(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x0633
            r3 = 3
            goto L_0x0634
        L_0x0633:
            r3 = -1
        L_0x0634:
            int r2 = zzd(r2, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            r4.add(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x0641:
            r53 = r10
            r52 = r63
            r10 = r72
            r11 = r73
            goto L_0x0384
        L_0x064b:
            r69 = r3
            r75 = r5
            r70 = r6
            r71 = r7
            r72 = r10
            r73 = r11
            r74 = r14
            java.lang.String r3 = "SegmentBase"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x0670
            r3 = r36
            com.google.android.gms.internal.ads.zzny r3 = (com.google.android.gms.internal.ads.zzny) r3     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzny r3 = r1.zza(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            r36 = r3
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x0670:
            java.lang.String r3 = "SegmentList"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x0687
            r3 = r36
            com.google.android.gms.internal.ads.zznv r3 = (com.google.android.gms.internal.ads.zznv) r3     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznv r3 = r1.zza(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            r36 = r3
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x0687:
            java.lang.String r3 = "SegmentTemplate"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x069e
            r3 = r36
            com.google.android.gms.internal.ads.zznw r3 = (com.google.android.gms.internal.ads.zznw) r3     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznw r3 = r1.zza(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            r36 = r3
            r5 = r37
            r12 = r61
            goto L_0x06b9
        L_0x069e:
            java.lang.String r3 = "InbandEventStream"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x06b2
            java.lang.String r3 = "InbandEventStream"
            com.google.android.gms.internal.ads.zznm r3 = zza(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            r5 = r37
            r5.add(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x06b7
        L_0x06b2:
            r5 = r37
            com.google.android.gms.internal.ads.zzqg.zzf(r0)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x06b7:
            r12 = r61
        L_0x06b9:
            java.lang.String r3 = "AdaptationSet"
            boolean r3 = com.google.android.gms.internal.ads.zzqg.zzc(r0, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r3 == 0) goto L_0x0759
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x08ad }
            int r6 = r4.size()     // Catch:{ XmlPullParserException -> 0x08ad }
            r3.<init>(r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            r6 = 0
        L_0x06cb:
            int r7 = r4.size()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r6 >= r7) goto L_0x0742
            java.lang.Object r7 = r4.get(r6)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznl r7 = (com.google.android.gms.internal.ads.zznl) r7     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzfs r8 = r7.zzaad     // Catch:{ XmlPullParserException -> 0x08ad }
            java.util.ArrayList<com.google.android.gms.internal.ads.zzhp$zza> r10 = r7.zzbdg     // Catch:{ XmlPullParserException -> 0x08ad }
            r11 = r62
            r10.addAll(r11)     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r12 = r10.isEmpty()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 != 0) goto L_0x06f2
            com.google.android.gms.internal.ads.zzhp r12 = new com.google.android.gms.internal.ads.zzhp     // Catch:{ XmlPullParserException -> 0x08ad }
            r12.<init>(r10)     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zzfs r8 = r8.zza(r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            r54 = r8
            goto L_0x06f4
        L_0x06f2:
            r54 = r8
        L_0x06f4:
            java.util.ArrayList<com.google.android.gms.internal.ads.zznm> r8 = r7.zzbdh     // Catch:{ XmlPullParserException -> 0x08ad }
            r8.addAll(r5)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r10 = r7.zzbde     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznt r7 = r7.zzbdf     // Catch:{ XmlPullParserException -> 0x08ad }
            boolean r12 = r7 instanceof com.google.android.gms.internal.ads.zzny     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x071b
            com.google.android.gms.internal.ads.zzns r12 = new com.google.android.gms.internal.ads.zzns     // Catch:{ XmlPullParserException -> 0x08ad }
            r62 = 0
            r63 = -1
            r67 = r7
            com.google.android.gms.internal.ads.zzny r67 = (com.google.android.gms.internal.ads.zzny) r67     // Catch:{ XmlPullParserException -> 0x08ad }
            r69 = 0
            r70 = -1
            r61 = r12
            r65 = r54
            r66 = r10
            r68 = r8
            r61.<init>(r62, r63, r65, r66, r67, r68, r69, r70)     // Catch:{ XmlPullParserException -> 0x08ad }
            goto L_0x0732
        L_0x071b:
            boolean r12 = r7 instanceof com.google.android.gms.internal.ads.zznu     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r12 == 0) goto L_0x073a
            com.google.android.gms.internal.ads.zznr r12 = new com.google.android.gms.internal.ads.zznr     // Catch:{ XmlPullParserException -> 0x08ad }
            r51 = 0
            r52 = -1
            r56 = r7
            com.google.android.gms.internal.ads.zznu r56 = (com.google.android.gms.internal.ads.zznu) r56     // Catch:{ XmlPullParserException -> 0x08ad }
            r50 = r12
            r55 = r10
            r57 = r8
            r50.<init>(r51, r52, r54, r55, r56, r57)     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0732:
            r3.add(r12)     // Catch:{ XmlPullParserException -> 0x08ad }
            int r6 = r6 + 1
            r62 = r11
            goto L_0x06cb
        L_0x073a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r2 = "segmentBase must be of type SingleSegmentBase or MultiSegmentBase"
            r0.<init>(r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            throw r0     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0742:
            com.google.android.gms.internal.ads.zzni r4 = new com.google.android.gms.internal.ads.zzni     // Catch:{ XmlPullParserException -> 0x08ad }
            r32 = r4
            r34 = r2
            r35 = r3
            r36 = r9
            r37 = r75
            r32.<init>(r33, r34, r35, r36, r37)     // Catch:{ XmlPullParserException -> 0x08ad }
            r3 = r60
            r3.add(r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            r2 = 0
            goto L_0x07b1
        L_0x0759:
            r15 = r5
            r8 = r62
            r3 = r69
            r6 = r70
            r7 = r71
            r10 = r72
            r11 = r73
            r14 = r74
            r5 = r75
            goto L_0x01d8
        L_0x076c:
            r3 = r2
            r47 = r4
            r46 = r5
            r31 = r6
            r38 = r7
            r44 = r8
            r39 = r10
            r48 = r12
            r41 = r14
            r42 = r15
            java.lang.String r2 = "SegmentBase"
            boolean r2 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r2 == 0) goto L_0x0790
            r2 = 0
            com.google.android.gms.internal.ads.zzny r4 = r1.zza(r0, r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            r7 = r4
            r6 = r31
            goto L_0x07b5
        L_0x0790:
            r2 = 0
            java.lang.String r4 = "SegmentList"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x07a1
            com.google.android.gms.internal.ads.zznv r4 = r1.zza(r0, r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            r7 = r4
            r6 = r31
            goto L_0x07b5
        L_0x07a1:
            java.lang.String r4 = "SegmentTemplate"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzd(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x07b1
            com.google.android.gms.internal.ads.zznw r4 = r1.zza(r0, r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            r7 = r4
            r6 = r31
            goto L_0x07b5
        L_0x07b1:
            r6 = r31
            r7 = r38
        L_0x07b5:
            java.lang.String r4 = "Period"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzc(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x0830
            com.google.android.gms.internal.ads.zznn r4 = new com.google.android.gms.internal.ads.zznn     // Catch:{ XmlPullParserException -> 0x08ad }
            r5 = r47
            r8 = r48
            r4.<init>(r5, r8, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.Long r3 = java.lang.Long.valueOf(r27)     // Catch:{ XmlPullParserException -> 0x08ad }
            android.util.Pair r3 = android.util.Pair.create(r4, r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.Object r4 = r3.first     // Catch:{ XmlPullParserException -> 0x08ad }
            com.google.android.gms.internal.ads.zznn r4 = (com.google.android.gms.internal.ads.zznn) r4     // Catch:{ XmlPullParserException -> 0x08ad }
            long r5 = r4.zzbdj     // Catch:{ XmlPullParserException -> 0x08ad }
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0808
            if (r17 == 0) goto L_0x07eb
            r3 = r30
            r10 = r39
            r14 = r41
            r15 = r46
            r24 = 1
            goto L_0x0854
        L_0x07eb:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx     // Catch:{ XmlPullParserException -> 0x08ad }
            int r2 = r46.size()     // Catch:{ XmlPullParserException -> 0x08ad }
            r3 = 47
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x08ad }
            r4.<init>(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r3 = "Unable to determine start of period "
            r4.append(r3)     // Catch:{ XmlPullParserException -> 0x08ad }
            r4.append(r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r2 = r4.toString()     // Catch:{ XmlPullParserException -> 0x08ad }
            r0.<init>(r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            throw r0     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0808:
            java.lang.Object r3 = r3.second     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ XmlPullParserException -> 0x08ad }
            long r5 = r3.longValue()     // Catch:{ XmlPullParserException -> 0x08ad }
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x0821
            r15 = r46
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0827
        L_0x0821:
            long r7 = r4.zzbdj     // Catch:{ XmlPullParserException -> 0x08ad }
            long r6 = r7 + r5
            r15 = r46
        L_0x0827:
            r15.add(r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            r10 = r6
            r3 = r30
            r14 = r41
            goto L_0x0854
        L_0x0830:
            r2 = r3
            r3 = r30
            r10 = r39
            r14 = r41
            r15 = r42
            r8 = r44
            r5 = r46
            r4 = r47
            r12 = r48
            goto L_0x011a
        L_0x0843:
            r30 = r3
            r44 = r8
            r39 = r10
            r41 = r14
            r42 = r15
            r15 = r5
        L_0x084e:
            r3 = r30
            r10 = r39
            r14 = r41
        L_0x0854:
            java.lang.String r4 = "MPD"
            boolean r4 = com.google.android.gms.internal.ads.zzqg.zzc(r0, r4)     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r4 == 0) goto L_0x0898
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r44 > r4 ? 1 : (r44 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0876
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x086b
            r13 = r10
            goto L_0x0878
        L_0x086b:
            if (r17 == 0) goto L_0x086e
            goto L_0x0876
        L_0x086e:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r2 = "Unable to determine duration of static manifest."
            r0.<init>(r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            throw r0     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0876:
            r13 = r44
        L_0x0878:
            boolean r0 = r15.isEmpty()     // Catch:{ XmlPullParserException -> 0x08ad }
            if (r0 != 0) goto L_0x0890
            com.google.android.gms.internal.ads.zznj r0 = new com.google.android.gms.internal.ads.zznj     // Catch:{ XmlPullParserException -> 0x08ad }
            r10 = r0
            r11 = r77
            r6 = r15
            r15 = r42
            r24 = r25
            r25 = r26
            r26 = r6
            r10.<init>(r11, r13, r15, r17, r18, r20, r22, r24, r25, r26)     // Catch:{ XmlPullParserException -> 0x08ad }
            return r0
        L_0x0890:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r2 = "No periods found."
            r0.<init>(r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            throw r0     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x0898:
            r5 = r15
            r15 = r42
            r8 = r44
            r4 = 2
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0093
        L_0x08a5:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx     // Catch:{ XmlPullParserException -> 0x08ad }
            java.lang.String r2 = "inputStream does not contain a valid media presentation description"
            r0.<init>(r2)     // Catch:{ XmlPullParserException -> 0x08ad }
            throw r0     // Catch:{ XmlPullParserException -> 0x08ad }
        L_0x08ad:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzfx r2 = new com.google.android.gms.internal.ads.zzfx
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznk.zzb(android.net.Uri, java.io.InputStream):com.google.android.gms.internal.ads.zznj");
    }

    private static int zza(XmlPullParser xmlPullParser) {
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

    private static zza zzb(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean equals = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95".equals(xmlPullParser.getAttributeValue(null, "schemeIdUri"));
        byte[] bArr = null;
        UUID uuid = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (bArr == null && zzqg.zzd(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                bArr = Base64.decode(xmlPullParser.getText(), 0);
                uuid = zzjq.zze(bArr);
                if (uuid == null) {
                    Log.w("MpdParser", "Skipping malformed cenc:pssh data");
                    bArr = null;
                }
            } else if (bArr == null && equals && zzqg.zzd(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                bArr = zzjq.zza(zzfe.zzwp, Base64.decode(xmlPullParser.getText(), 0));
                uuid = zzfe.zzwp;
            } else if (zzqg.zzd(xmlPullParser, "widevine:license")) {
                String attributeValue = xmlPullParser.getAttributeValue(null, "robustness_level");
                z = attributeValue != null && attributeValue.startsWith("HW");
            }
        } while (!zzqg.zzc(xmlPullParser, "ContentProtection"));
        if (bArr != null) {
            return new zza(uuid, MimeTypes.VIDEO_MP4, bArr, z);
        }
        return null;
    }

    private final zzny zza(XmlPullParser xmlPullParser, zzny zzny) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        zzny zzny2 = zzny;
        long zzb = zzb(xmlPullParser2, "timescale", zzny2 != null ? zzny2.zzcr : 1);
        long j3 = 0;
        long zzb2 = zzb(xmlPullParser2, "presentationTimeOffset", zzny2 != null ? zzny2.zzbdw : 0);
        long j4 = zzny2 != null ? zzny2.zzbec : 0;
        if (zzny2 != null) {
            j3 = zzny2.zzbed;
        }
        zzno zzno = null;
        String attributeValue = xmlPullParser2.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j2 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - j2) + 1;
        } else {
            j = j3;
            j2 = j4;
        }
        if (zzny2 != null) {
            zzno = zzny2.zzbdv;
        }
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser2, "Initialization")) {
                zzno = zzd(xmlPullParser);
            }
        } while (!zzqg.zzc(xmlPullParser2, "SegmentBase"));
        zzny zzny3 = new zzny(zzno, zzb, zzb2, j2, j);
        return zzny3;
    }

    private final zznv zza(XmlPullParser xmlPullParser, zznv zznv) throws XmlPullParserException, IOException {
        List list;
        List list2;
        zzno zzno;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        zznv zznv2 = zznv;
        long zzb = zzb(xmlPullParser2, "timescale", zznv2 != null ? zznv2.zzcr : 1);
        long zzb2 = zzb(xmlPullParser2, "presentationTimeOffset", zznv2 != null ? zznv2.zzbdw : 0);
        long zzb3 = zzb(xmlPullParser2, "duration", zznv2 != null ? zznv2.zzcs : -9223372036854775807L);
        int zza = zza(xmlPullParser2, "startNumber", zznv2 != null ? zznv2.zzbdx : 1);
        List list3 = null;
        zzno zzno2 = null;
        List list4 = null;
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser2, "Initialization")) {
                zzno2 = zzd(xmlPullParser);
            } else if (zzqg.zzd(xmlPullParser2, "SegmentTimeline")) {
                list4 = zzc(xmlPullParser);
            } else if (zzqg.zzd(xmlPullParser2, "SegmentURL")) {
                if (list3 == null) {
                    list3 = new ArrayList();
                }
                list3.add(zza(xmlPullParser2, "media", "mediaRange"));
            }
        } while (!zzqg.zzc(xmlPullParser2, "SegmentList"));
        if (zznv2 != null) {
            if (zzno2 == null) {
                zzno2 = zznv2.zzbdv;
            }
            if (list4 == null) {
                list4 = zznv2.zzbdy;
            }
            if (list3 == null) {
                list3 = zznv2.zzbdz;
            }
            list = list3;
            zzno = zzno2;
            list2 = list4;
        } else {
            list = list3;
            zzno = zzno2;
            list2 = list4;
        }
        zznv zznv3 = new zznv(zzno, zzb, zzb2, zza, zzb3, list2, list);
        return zznv3;
    }

    private final zznw zza(XmlPullParser xmlPullParser, zznw zznw) throws XmlPullParserException, IOException {
        List list;
        zzno zzno;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        zznw zznw2 = zznw;
        long zzb = zzb(xmlPullParser2, "timescale", zznw2 != null ? zznw2.zzcr : 1);
        long zzb2 = zzb(xmlPullParser2, "presentationTimeOffset", zznw2 != null ? zznw2.zzbdw : 0);
        long zzb3 = zzb(xmlPullParser2, "duration", zznw2 != null ? zznw2.zzcs : -9223372036854775807L);
        int zza = zza(xmlPullParser2, "startNumber", zznw2 != null ? zznw2.zzbdx : 1);
        zzno zzno2 = null;
        zzoa zza2 = zza(xmlPullParser2, "media", zznw2 != null ? zznw2.zzbeb : null);
        zzoa zza3 = zza(xmlPullParser2, "initialization", zznw2 != null ? zznw2.zzbea : null);
        List list2 = null;
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser2, "Initialization")) {
                zzno2 = zzd(xmlPullParser);
            } else if (zzqg.zzd(xmlPullParser2, "SegmentTimeline")) {
                list2 = zzc(xmlPullParser);
            }
        } while (!zzqg.zzc(xmlPullParser2, "SegmentTemplate"));
        if (zznw2 != null) {
            if (zzno2 == null) {
                zzno2 = zznw2.zzbdv;
            }
            if (list2 == null) {
                list2 = zznw2.zzbdy;
            }
            list = list2;
            zzno = zzno2;
        } else {
            list = list2;
            zzno = zzno2;
        }
        zznw zznw3 = new zznw(zzno, zzb, zzb2, zza, zzb3, list, zza3, zza2);
        return zznw3;
    }

    private static List<zznx> zzc(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser, "S")) {
                j = zzb(xmlPullParser, "t", j);
                long zzb = zzb(xmlPullParser, "d", -9223372036854775807L);
                int zza = zza(xmlPullParser, "r", 0) + 1;
                for (int i = 0; i < zza; i++) {
                    arrayList.add(new zznx(j, zzb));
                    j += zzb;
                }
            }
        } while (!zzqg.zzc(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    private static zzoa zza(XmlPullParser xmlPullParser, String str, zzoa zzoa) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? zzoa.zzaa(attributeValue) : zzoa;
    }

    private final zzno zzd(XmlPullParser xmlPullParser) {
        return zza(xmlPullParser, "sourceURL", "range");
    }

    private static zzno zza(XmlPullParser xmlPullParser, String str, String str2) {
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
        zzno zzno = new zzno(attributeValue, j2, j);
        return zzno;
    }

    private static int zze(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(zzb(xmlPullParser, "schemeIdUri", (String) null))) {
            i = zza(xmlPullParser, "value", -1);
        }
        do {
            xmlPullParser.next();
        } while (!zzqg.zzc(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static boolean zzx(String str) {
        return zzpt.zzad(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str);
    }

    private static int zzd(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        zzpo.checkState(i == i2);
        return i;
    }

    private static zznm zza(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String zzb = zzb(xmlPullParser, "schemeIdUri", "");
        String zzb2 = zzb(xmlPullParser, "value", (String) null);
        String zzb3 = zzb(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!zzqg.zzc(xmlPullParser, str));
        return new zznm(zzb, zzb2, zzb3);
    }

    private static float zza(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = zzbcz.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    private static long zza(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return zzqe.zzak(attributeValue);
    }

    private static String zzb(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return zzqd.zzc(str, xmlPullParser.getText());
    }

    private static int zza(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return i;
        }
        return Integer.parseInt(attributeValue);
    }

    private static long zzb(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return Long.parseLong(attributeValue);
    }

    private static String zzb(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }
}
