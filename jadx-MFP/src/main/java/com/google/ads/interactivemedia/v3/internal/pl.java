package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class pl implements pt {
    private final int b;

    public pl() {
        this(0);
    }

    private pl(int i) {
        this.b = 0;
    }

    /* JADX WARNING: type inference failed for: r1v12, types: [com.google.ads.interactivemedia.v3.internal.qh] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.google.ads.interactivemedia.v3.internal.hw] */
    /* JADX WARNING: type inference failed for: r1v17, types: [com.google.ads.interactivemedia.v3.internal.iw] */
    /* JADX WARNING: type inference failed for: r1v18, types: [com.google.ads.interactivemedia.v3.internal.gp] */
    /* JADX WARNING: type inference failed for: r1v19, types: [com.google.ads.interactivemedia.v3.internal.hy] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.pu a(com.google.ads.interactivemedia.v3.internal.fq r16, android.net.Uri r17, com.google.ads.interactivemedia.v3.internal.bs r18, java.util.List<com.google.ads.interactivemedia.v3.internal.bs> r19, com.google.ads.interactivemedia.v3.internal.fa r20, com.google.ads.interactivemedia.v3.internal.ve r21, com.google.ads.interactivemedia.v3.internal.fr r22) throws java.lang.InterruptedException, java.io.IOException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r18
            r3 = r19
            r10 = r21
            r11 = r22
            if (r1 == 0) goto L_0x0079
            boolean r4 = b(r16)
            if (r4 == 0) goto L_0x0018
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r16)
            return r1
        L_0x0018:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.qh
            if (r4 == 0) goto L_0x0028
            com.google.ads.interactivemedia.v3.internal.qh r4 = new com.google.ads.interactivemedia.v3.internal.qh
            java.lang.String r5 = r2.x
            r4.<init>(r5, r10)
            com.google.ads.interactivemedia.v3.internal.pu r4 = a(r4)
            goto L_0x0053
        L_0x0028:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.hy
            if (r4 == 0) goto L_0x0036
            com.google.ads.interactivemedia.v3.internal.hy r4 = new com.google.ads.interactivemedia.v3.internal.hy
            r4.<init>()
            com.google.ads.interactivemedia.v3.internal.pu r4 = a(r4)
            goto L_0x0053
        L_0x0036:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.hw
            if (r4 == 0) goto L_0x0044
            com.google.ads.interactivemedia.v3.internal.hw r4 = new com.google.ads.interactivemedia.v3.internal.hw
            r4.<init>()
            com.google.ads.interactivemedia.v3.internal.pu r4 = a(r4)
            goto L_0x0053
        L_0x0044:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.gp
            if (r4 == 0) goto L_0x0052
            com.google.ads.interactivemedia.v3.internal.gp r4 = new com.google.ads.interactivemedia.v3.internal.gp
            r4.<init>()
            com.google.ads.interactivemedia.v3.internal.pu r4 = a(r4)
            goto L_0x0053
        L_0x0052:
            r4 = 0
        L_0x0053:
            if (r4 != 0) goto L_0x0079
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Unexpected previousExtractor type: "
            java.lang.Class r1 = r16.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r4 = r1.length()
            if (r4 == 0) goto L_0x0070
            java.lang.String r1 = r3.concat(r1)
            goto L_0x0075
        L_0x0070:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
        L_0x0075:
            r2.<init>(r1)
            throw r2
        L_0x0079:
            java.lang.String r1 = r17.getLastPathSegment()
            if (r1 != 0) goto L_0x0081
            java.lang.String r1 = ""
        L_0x0081:
            java.lang.String r4 = "text/vtt"
            java.lang.String r5 = r2.h
            boolean r4 = r4.equals(r5)
            r12 = 0
            r14 = 0
            if (r4 != 0) goto L_0x0124
            java.lang.String r4 = ".webvtt"
            boolean r4 = r1.endsWith(r4)
            if (r4 != 0) goto L_0x0124
            java.lang.String r4 = ".vtt"
            boolean r4 = r1.endsWith(r4)
            if (r4 == 0) goto L_0x00a0
            goto L_0x0124
        L_0x00a0:
            java.lang.String r4 = ".aac"
            boolean r4 = r1.endsWith(r4)
            if (r4 == 0) goto L_0x00af
            com.google.ads.interactivemedia.v3.internal.hy r1 = new com.google.ads.interactivemedia.v3.internal.hy
            r1.<init>()
            goto L_0x012b
        L_0x00af:
            java.lang.String r4 = ".ac3"
            boolean r4 = r1.endsWith(r4)
            if (r4 != 0) goto L_0x011e
            java.lang.String r4 = ".ec3"
            boolean r4 = r1.endsWith(r4)
            if (r4 == 0) goto L_0x00c0
            goto L_0x011e
        L_0x00c0:
            java.lang.String r4 = ".mp3"
            boolean r4 = r1.endsWith(r4)
            if (r4 == 0) goto L_0x00ce
            com.google.ads.interactivemedia.v3.internal.gp r1 = new com.google.ads.interactivemedia.v3.internal.gp
            r1.<init>(r14, r12)
            goto L_0x012b
        L_0x00ce:
            java.lang.String r4 = ".mp4"
            boolean r4 = r1.endsWith(r4)
            if (r4 != 0) goto L_0x0108
            java.lang.String r4 = ".m4"
            int r5 = r1.length()
            int r5 = r5 + -4
            boolean r4 = r1.startsWith(r4, r5)
            if (r4 != 0) goto L_0x0108
            java.lang.String r4 = ".mp4"
            int r5 = r1.length()
            int r5 = r5 + -5
            boolean r4 = r1.startsWith(r4, r5)
            if (r4 != 0) goto L_0x0108
            java.lang.String r4 = ".cmf"
            int r5 = r1.length()
            int r5 = r5 + -5
            boolean r1 = r1.startsWith(r4, r5)
            if (r1 == 0) goto L_0x0101
            goto L_0x0108
        L_0x0101:
            int r1 = r0.b
            com.google.ads.interactivemedia.v3.internal.iw r1 = a(r1, r2, r3, r10)
            goto L_0x012b
        L_0x0108:
            com.google.ads.interactivemedia.v3.internal.hg r1 = new com.google.ads.interactivemedia.v3.internal.hg
            r5 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0110
            r9 = r3
            goto L_0x0115
        L_0x0110:
            java.util.List r4 = java.util.Collections.emptyList()
            r9 = r4
        L_0x0115:
            r4 = r1
            r6 = r21
            r8 = r20
            r4.<init>(r5, r6, r7, r8, r9)
            goto L_0x012b
        L_0x011e:
            com.google.ads.interactivemedia.v3.internal.hw r1 = new com.google.ads.interactivemedia.v3.internal.hw
            r1.<init>()
            goto L_0x012b
        L_0x0124:
            com.google.ads.interactivemedia.v3.internal.qh r1 = new com.google.ads.interactivemedia.v3.internal.qh
            java.lang.String r4 = r2.x
            r1.<init>(r4, r10)
        L_0x012b:
            r22.a()
            boolean r4 = a(r1, r11)
            if (r4 == 0) goto L_0x0139
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r1)
            return r1
        L_0x0139:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.qh
            if (r4 != 0) goto L_0x014f
            com.google.ads.interactivemedia.v3.internal.qh r4 = new com.google.ads.interactivemedia.v3.internal.qh
            java.lang.String r5 = r2.x
            r4.<init>(r5, r10)
            boolean r5 = a(r4, r11)
            if (r5 == 0) goto L_0x014f
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r4)
            return r1
        L_0x014f:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.hy
            if (r4 != 0) goto L_0x0163
            com.google.ads.interactivemedia.v3.internal.hy r4 = new com.google.ads.interactivemedia.v3.internal.hy
            r4.<init>()
            boolean r5 = a(r4, r11)
            if (r5 == 0) goto L_0x0163
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r4)
            return r1
        L_0x0163:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.hw
            if (r4 != 0) goto L_0x0177
            com.google.ads.interactivemedia.v3.internal.hw r4 = new com.google.ads.interactivemedia.v3.internal.hw
            r4.<init>()
            boolean r5 = a(r4, r11)
            if (r5 == 0) goto L_0x0177
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r4)
            return r1
        L_0x0177:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.gp
            if (r4 != 0) goto L_0x018b
            com.google.ads.interactivemedia.v3.internal.gp r4 = new com.google.ads.interactivemedia.v3.internal.gp
            r4.<init>(r14, r12)
            boolean r5 = a(r4, r11)
            if (r5 == 0) goto L_0x018b
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r4)
            return r1
        L_0x018b:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.hg
            if (r4 != 0) goto L_0x01af
            com.google.ads.interactivemedia.v3.internal.hg r12 = new com.google.ads.interactivemedia.v3.internal.hg
            r5 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0197
            r9 = r3
            goto L_0x019c
        L_0x0197:
            java.util.List r4 = java.util.Collections.emptyList()
            r9 = r4
        L_0x019c:
            r4 = r12
            r6 = r21
            r8 = r20
            r4.<init>(r5, r6, r7, r8, r9)
            boolean r4 = a(r12, r11)
            if (r4 == 0) goto L_0x01af
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r12)
            return r1
        L_0x01af:
            boolean r4 = r1 instanceof com.google.ads.interactivemedia.v3.internal.iw
            if (r4 != 0) goto L_0x01c4
            int r4 = r0.b
            com.google.ads.interactivemedia.v3.internal.iw r2 = a(r4, r2, r3, r10)
            boolean r3 = a(r2, r11)
            if (r3 == 0) goto L_0x01c4
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r2)
            return r1
        L_0x01c4:
            com.google.ads.interactivemedia.v3.internal.pu r1 = a(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.pl.a(com.google.ads.interactivemedia.v3.internal.fq, android.net.Uri, com.google.ads.interactivemedia.v3.internal.bs, java.util.List, com.google.ads.interactivemedia.v3.internal.fa, com.google.ads.interactivemedia.v3.internal.ve, com.google.ads.interactivemedia.v3.internal.fr):com.google.ads.interactivemedia.v3.internal.pu");
    }

    private static iw a(int i, bs bsVar, List<bs> list, ve veVar) {
        int i2 = i | 16;
        if (list != null) {
            i2 |= 32;
        } else {
            list = Collections.singletonList(bs.a(null, MimeTypes.APPLICATION_CEA608, 0, null));
        }
        String str = bsVar.e;
        if (!TextUtils.isEmpty(str)) {
            if (!MimeTypes.AUDIO_AAC.equals(un.e(str))) {
                i2 |= 2;
            }
            if (!MimeTypes.VIDEO_H264.equals(un.d(str))) {
                i2 |= 4;
            }
        }
        return new iw(2, veVar, new jc(i2, list));
    }

    private static pu a(fq fqVar) {
        return new pu(fqVar, (fqVar instanceof hy) || (fqVar instanceof hw) || (fqVar instanceof gp), b(fqVar));
    }

    /* JADX INFO: finally extract failed */
    private static boolean a(fq fqVar, fr frVar) throws InterruptedException, IOException {
        try {
            boolean a = fqVar.a(frVar);
            frVar.a();
            return a;
        } catch (EOFException unused) {
            frVar.a();
            return false;
        } catch (Throwable th) {
            frVar.a();
            throw th;
        }
    }

    private static boolean b(fq fqVar) {
        return (fqVar instanceof iw) || (fqVar instanceof hg);
    }
}
