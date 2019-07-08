package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzark
public final class zzbfs extends zzbfk {
    private static final Set<String> zzewz = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzexa = new DecimalFormat("#,###");
    private File zzexb;
    private boolean zzexc;

    public zzbfs(zzbdz zzbdz) {
        super(zzbdz);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzaxz.zzeo("Context.getCacheDir() returned null");
            return;
        }
        this.zzexb = new File(cacheDir, "admobVideoStreams");
        if (!this.zzexb.isDirectory() && !this.zzexb.mkdirs()) {
            String str = "Could not create preload cache directory at ";
            String valueOf = String.valueOf(this.zzexb.getAbsolutePath());
            zzaxz.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzexb = null;
        } else if (!this.zzexb.setReadable(true, false) || !this.zzexb.setExecutable(true, false)) {
            String str2 = "Could not set cache file permissions at ";
            String valueOf2 = String.valueOf(this.zzexb.getAbsolutePath());
            zzaxz.zzeo(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
            this.zzexb = null;
        }
    }

    /* JADX WARNING: type inference failed for: r14v0, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: type inference failed for: r1v24 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: type inference failed for: r10v5 */
    /* JADX WARNING: type inference failed for: r1v28 */
    /* JADX WARNING: type inference failed for: r10v6 */
    /* JADX WARNING: type inference failed for: r24v0 */
    /* JADX WARNING: type inference failed for: r1v30 */
    /* JADX WARNING: type inference failed for: r1v34, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v42 */
    /* JADX WARNING: type inference failed for: r24v1 */
    /* JADX WARNING: type inference failed for: r1v43 */
    /* JADX WARNING: type inference failed for: r24v2 */
    /* JADX WARNING: type inference failed for: r24v3 */
    /* JADX WARNING: type inference failed for: r24v4 */
    /* JADX WARNING: type inference failed for: r1v44 */
    /* JADX WARNING: type inference failed for: r1v45 */
    /* JADX WARNING: type inference failed for: r1v49 */
    /* JADX WARNING: type inference failed for: r1v52 */
    /* JADX WARNING: type inference failed for: r24v5, types: [int] */
    /* JADX WARNING: type inference failed for: r24v6 */
    /* JADX WARNING: type inference failed for: r1v57 */
    /* JADX WARNING: type inference failed for: r24v7 */
    /* JADX WARNING: type inference failed for: r24v8 */
    /* JADX WARNING: type inference failed for: r24v9 */
    /* JADX WARNING: type inference failed for: r10v10 */
    /* JADX WARNING: type inference failed for: r24v10 */
    /* JADX WARNING: type inference failed for: r1v64 */
    /* JADX WARNING: type inference failed for: r24v11 */
    /* JADX WARNING: type inference failed for: r1v77 */
    /* JADX WARNING: type inference failed for: r2v62, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r14v2 */
    /* JADX WARNING: type inference failed for: r1v84, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r14v3 */
    /* JADX WARNING: type inference failed for: r1v94 */
    /* JADX WARNING: type inference failed for: r1v95 */
    /* JADX WARNING: type inference failed for: r1v96 */
    /* JADX WARNING: type inference failed for: r10v12 */
    /* JADX WARNING: type inference failed for: r24v12 */
    /* JADX WARNING: type inference failed for: r24v13 */
    /* JADX WARNING: type inference failed for: r24v14 */
    /* JADX WARNING: type inference failed for: r24v15 */
    /* JADX WARNING: type inference failed for: r24v16 */
    /* JADX WARNING: type inference failed for: r24v17 */
    /* JADX WARNING: type inference failed for: r24v18 */
    /* JADX WARNING: type inference failed for: r24v19 */
    /* JADX WARNING: type inference failed for: r24v20 */
    /* JADX WARNING: type inference failed for: r24v21 */
    /* JADX WARNING: type inference failed for: r1v97 */
    /* JADX WARNING: type inference failed for: r1v98 */
    /* JADX WARNING: type inference failed for: r24v22 */
    /* JADX WARNING: type inference failed for: r24v23 */
    /* JADX WARNING: type inference failed for: r24v24 */
    /* JADX WARNING: type inference failed for: r1v99 */
    /* JADX WARNING: type inference failed for: r24v25 */
    /* JADX WARNING: type inference failed for: r24v26 */
    /* JADX WARNING: type inference failed for: r24v27 */
    /* JADX WARNING: type inference failed for: r24v28 */
    /* JADX WARNING: type inference failed for: r24v29 */
    /* JADX WARNING: type inference failed for: r24v30 */
    /* JADX WARNING: type inference failed for: r24v31 */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01f2, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x0249;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r1 = r5.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01fd, code lost:
        if (r1 < 400) goto L_0x0249;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01ff, code lost:
        r15 = "badUrl";
        r0 = "HTTP request failed. Code: ";
        r2 = java.lang.String.valueOf(java.lang.Integer.toString(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x020f, code lost:
        if (r2.length() == 0) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0211, code lost:
        r2 = r0.concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0217, code lost:
        r2 = new java.lang.String(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 32);
        r4.append("HTTP status code ");
        r4.append(r1);
        r4.append(" at ");
        r4.append(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0244, code lost:
        throw new java.io.IOException(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0245, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        r7 = r5.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x024d, code lost:
        if (r7 >= 0) goto L_0x0278;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x024f, code lost:
        r0 = "Stream cache aborted, missing content-length header at ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:?, code lost:
        r1 = java.lang.String.valueOf(r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0259, code lost:
        if (r1.length() == 0) goto L_0x0260;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x025b, code lost:
        r0 = r0.concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0260, code lost:
        r0 = new java.lang.String(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0266, code lost:
        com.google.android.gms.internal.ads.zzaxz.zzeo(r0);
        zza(r9, r12.getAbsolutePath(), "contentLengthMissing", null);
        zzewz.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0277, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r1 = zzexa.format((long) r7);
        r3 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcou)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x028f, code lost:
        if (r7 <= r3) goto L_0x02e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        r2 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 33) + java.lang.String.valueOf(r31).length());
        r2.append("Content length ");
        r2.append(r1);
        r2.append(" exceeds limit at ");
        r2.append(r9);
        com.google.android.gms.internal.ads.zzaxz.zzeo(r2.toString());
        r0 = "File too big for full file cache. Size: ";
        r1 = java.lang.String.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02ca, code lost:
        if (r1.length() == 0) goto L_0x02d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02cc, code lost:
        r0 = r0.concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02d1, code lost:
        r0 = new java.lang.String(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02d7, code lost:
        zza(r9, r12.getAbsolutePath(), "sizeExceeded", r0);
        zzewz.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02e5, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        r4 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 20) + java.lang.String.valueOf(r31).length());
        r4.append("Caching ");
        r4.append(r1);
        r4.append(" bytes from ");
        r4.append(r9);
        com.google.android.gms.internal.ads.zzaxz.zzdn(r4.toString());
        r5 = java.nio.channels.Channels.newChannel(r5.getInputStream());
        r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        r2 = r4.getChannel();
        r1 = java.nio.ByteBuffer.allocate(com.google.android.exoplayer2.source.ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES);
        r16 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r17 = r16.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x033f, code lost:
        r10 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:?, code lost:
        r6 = new com.google.android.gms.internal.ads.zzbai(((java.lang.Long) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcox)).longValue());
        r13 = ((java.lang.Long) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcow)).longValue();
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0359, code lost:
        r20 = r5.read(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x035d, code lost:
        if (r20 < 0) goto L_0x0479;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x035f, code lost:
        r11 = r11 + r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0361, code lost:
        if (r11 <= r3) goto L_0x0396;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0363, code lost:
        r15 = "sizeExceeded";
        r0 = "File too big for full file cache. Size: ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:?, code lost:
        r1 = java.lang.String.valueOf(java.lang.Integer.toString(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0373, code lost:
        if (r1.length() == 0) goto L_0x037b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0375, code lost:
        r1 = r0.concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x037b, code lost:
        r1 = new java.lang.String(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0387, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0388, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x038b, code lost:
        r2 = r1;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x038e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0391, code lost:
        r1 = r10;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0393, code lost:
        r10 = r4;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:?, code lost:
        r1.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x039d, code lost:
        if (r2.write(r1) > 0) goto L_0x046b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x039f, code lost:
        r1.clear();
        r24 = ((r16.currentTimeMillis() - r17) > (1000 * r13) ? 1 : ((r16.currentTimeMillis() - r17) == (1000 * r13) ? 0 : -1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03ae, code lost:
        if (r24 > 0) goto L_0x0429;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03b0, code lost:
        r20 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03b4, code lost:
        if (r8.zzexc != false) goto L_0x0419;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03ba, code lost:
        if (r6.tryAcquire() == false) goto L_0x03f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03c2, code lost:
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03c4, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03c6, code lost:
        r24 = r10;
        r10 = com.google.android.gms.internal.ads.zzbat.zztu;
        r1 = r1;
        r25 = r2;
        r26 = r3;
        r27 = r4;
        r21 = r5;
        r19 = r6;
        r29 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03e4, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:?, code lost:
        r1 = new com.google.android.gms.internal.ads.zzbfl(r30, r31, r12.getAbsolutePath(), r11, r7, false);
        r10.post(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03eb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03ed, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x03ee, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03f3, code lost:
        r25 = r2;
        r26 = r3;
        r27 = r4;
        r21 = r5;
        r19 = r6;
        r29 = r7;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0405, code lost:
        r6 = r19;
        r1 = r20;
        r5 = r21;
        r15 = r22;
        r10 = r24;
        r2 = r25;
        r3 = r26;
        r4 = r27;
        r7 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0419, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
        r15 = "externalAbort";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0428, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0429, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
        r15 = "downloadTimeout";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0431, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:?, code lost:
        r0 = java.lang.Long.toString(r13);
        r2 = new java.lang.StringBuilder(java.lang.String.valueOf(r0).length() + 29);
        r2.append("Timeout exceeded. Limit: ");
        r2.append(r0);
        r2.append(" sec");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x045c, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x045d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0460, code lost:
        r2 = r2.toString();
        r1 = r24;
        r10 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0467, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x046f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0472, code lost:
        r27 = r4;
        r22 = r15;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0479, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:?, code lost:
        r27.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0488, code lost:
        if (com.google.android.gms.internal.ads.zzaxz.isLoggable(3) == false) goto L_0x04cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x048a, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:?, code lost:
        r1 = zzexa.format((long) r11);
        r3 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 22) + java.lang.String.valueOf(r31).length());
        r3.append("Preloaded ");
        r3.append(r1);
        r3.append(" bytes from ");
        r3.append(r9);
        com.google.android.gms.internal.ads.zzaxz.zzdn(r3.toString());
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x04c0, code lost:
        r1 = false;
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x04c3, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x04c5, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x04cb, code lost:
        r1 = false;
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:?, code lost:
        r12.setReadable(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x04d4, code lost:
        if (r0.isFile() == false) goto L_0x04de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x04d6, code lost:
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:?, code lost:
        r0.setLastModified(java.lang.System.currentTimeMillis());
        r24 = r24;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x04de, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:?, code lost:
        r0.createNewFile();
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        zza(r9, r12.getAbsolutePath(), r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x04ea, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:?, code lost:
        zzewz.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x04f0, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x04f1, code lost:
        r0 = e;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x04f5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x04f8, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x04fa, code lost:
        r15 = r22;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x04fd, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0500, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0504, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0507, code lost:
        r27 = r4;
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x050a, code lost:
        r22 = r15;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0510, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x051a, code lost:
        throw new java.io.IOException("Invalid protocol.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x051b, code lost:
        r1 = r14;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0525, code lost:
        throw new java.io.IOException("Too many redirects (20)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0526, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0529, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x052c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x052f, code lost:
        r1 = r14;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0532, code lost:
        r2 = null;
        r10 = null;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0538, code lost:
        com.google.android.gms.ads.internal.zzbv.zzlj().zza(r0, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0548, code lost:
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 26);
        r3.append("Preload aborted for URL \"");
        r3.append(r9);
        r3.append("\"");
        com.google.android.gms.internal.ads.zzaxz.zzen(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x056c, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 25);
        r4.append("Preload failed for URL \"");
        r4.append(r9);
        r4.append("\"");
        com.google.android.gms.internal.ads.zzaxz.zzc(r4.toString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x05ab, code lost:
        r0 = r0.concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x05b0, code lost:
        r0 = new java.lang.String(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0405, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0131, code lost:
        r15 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzls();
        r1 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcoy)).intValue();
        r3 = new java.net.URL(r9);
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014d, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0150, code lost:
        if (r2 > 20) goto L_0x051b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0152, code lost:
        r5 = r3.openConnection();
        r5.setConnectTimeout(r1);
        r5.setReadTimeout(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015e, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x0510;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0160, code lost:
        r5 = (java.net.HttpURLConnection) r5;
        r6 = new com.google.android.gms.internal.ads.zzbax();
        r6.zza(r5, (byte[]) null);
        r5.setInstanceFollowRedirects(false);
        r7 = r5.getResponseCode();
        r6.zza(r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0177, code lost:
        if ((r7 / 100) != 3) goto L_0x01f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r4 = r5.getHeaderField(com.google.common.net.HttpHeaders.LOCATION);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x017f, code lost:
        if (r4 == null) goto L_0x01e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0181, code lost:
        r6 = new java.net.URL(r3, r4);
        r3 = r6.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018a, code lost:
        if (r3 == null) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0192, code lost:
        if (r3.equals(com.mopub.common.Constants.HTTP) != false) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x019a, code lost:
        if (r3.equals(com.mopub.common.Constants.HTTPS) != false) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x019c, code lost:
        r1 = "Unsupported scheme: ";
        r2 = java.lang.String.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01a8, code lost:
        if (r2.length() == 0) goto L_0x01af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01aa, code lost:
        r1 = r1.concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01af, code lost:
        r1 = new java.lang.String(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b8, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b9, code lost:
        r3 = "Redirecting to ";
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01c3, code lost:
        if (r4.length() == 0) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c5, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01ca, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01d0, code lost:
        com.google.android.gms.internal.ads.zzaxz.zzdn(r3);
        r5.disconnect();
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01e0, code lost:
        throw new java.io.IOException("Protocol is null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01e8, code lost:
        throw new java.io.IOException("Missing Location header in redirect");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01e9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01ec, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01ed, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:219:0x04e1 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v9
  assigns: []
  uses: []
  mth insns count: 579
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
    /* JADX WARNING: Removed duplicated region for block: B:207:0x04c3 A[ExcHandler: RuntimeException (e java.lang.RuntimeException), PHI: r22 r24 r27 
  PHI: (r22v8 java.lang.String) = (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v16 java.lang.String), (r22v16 java.lang.String) binds: [B:217:0x04de, B:218:?, B:215:0x04d6, B:216:?, B:204:0x048a, B:205:?, B:173:0x03c4, B:176:0x03e4] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r24v4 ?) = (r24v12 ?), (r24v13 ?), (r24v15 ?), (r24v17 ?), (r24v19 ?), (r24v21 ?), (r24v26 ?), (r24v30 ?) binds: [B:217:0x04de, B:218:?, B:215:0x04d6, B:216:?, B:204:0x048a, B:205:?, B:173:0x03c4, B:176:0x03e4] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r27v10 java.io.FileOutputStream) = (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v18 java.io.FileOutputStream), (r27v15 java.io.FileOutputStream) binds: [B:217:0x04de, B:218:?, B:215:0x04d6, B:216:?, B:204:0x048a, B:205:?, B:176:0x03e4, B:173:0x03c4] A[DONT_GENERATE, DONT_INLINE], Splitter:B:176:0x03e4] */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0538  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0548  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x056c  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x05ab  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x05b0  */
    /* JADX WARNING: Unknown variable types count: 24 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzex(java.lang.String r31) {
        /*
            r30 = this;
            r8 = r30
            r9 = r31
            java.io.File r0 = r8.zzexb
            r10 = 0
            r11 = 0
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "noCacheDir"
            r8.zza(r9, r10, r0, r10)
            return r11
        L_0x0010:
            java.io.File r0 = r8.zzexb
            if (r0 != 0) goto L_0x0016
            r3 = 0
            goto L_0x0032
        L_0x0016:
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x001d:
            if (r2 >= r1) goto L_0x0032
            r4 = r0[r2]
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = ".done"
            boolean r4 = r4.endsWith(r5)
            if (r4 != 0) goto L_0x002f
            int r3 = r3 + 1
        L_0x002f:
            int r2 = r2 + 1
            goto L_0x001d
        L_0x0032:
            com.google.android.gms.internal.ads.zzaac<java.lang.Integer> r0 = com.google.android.gms.internal.ads.zzaan.zzcot
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r3 <= r0) goto L_0x0098
            java.io.File r0 = r8.zzexb
            if (r0 != 0) goto L_0x004a
            r0 = 0
            goto L_0x008b
        L_0x004a:
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.io.File[] r0 = r0.listFiles()
            int r3 = r0.length
            r4 = r1
            r2 = r10
            r1 = 0
        L_0x0057:
            if (r1 >= r3) goto L_0x0074
            r6 = r0[r1]
            java.lang.String r7 = r6.getName()
            java.lang.String r12 = ".done"
            boolean r7 = r7.endsWith(r12)
            if (r7 != 0) goto L_0x0071
            long r12 = r6.lastModified()
            int r7 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r7 >= 0) goto L_0x0071
            r2 = r6
            r4 = r12
        L_0x0071:
            int r1 = r1 + 1
            goto L_0x0057
        L_0x0074:
            if (r2 == 0) goto L_0x008a
            boolean r0 = r2.delete()
            java.io.File r1 = r8.zzc(r2)
            boolean r2 = r1.isFile()
            if (r2 == 0) goto L_0x008b
            boolean r1 = r1.delete()
            r0 = r0 & r1
            goto L_0x008b
        L_0x008a:
            r0 = 0
        L_0x008b:
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "Unable to expire stream cache"
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)
            java.lang.String r0 = "expireFailed"
            r8.zza(r9, r10, r0, r10)
            return r11
        L_0x0098:
            java.lang.String r0 = r30.zzey(r31)
            java.io.File r12 = new java.io.File
            java.io.File r1 = r8.zzexb
            r12.<init>(r1, r0)
            java.io.File r0 = r8.zzc(r12)
            boolean r1 = r12.isFile()
            r13 = 1
            if (r1 == 0) goto L_0x00db
            boolean r1 = r0.isFile()
            if (r1 == 0) goto L_0x00db
            long r0 = r12.length()
            int r1 = (int) r0
            java.lang.String r0 = "Stream cache hit at "
            java.lang.String r2 = java.lang.String.valueOf(r31)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00ca
            java.lang.String r0 = r0.concat(r2)
            goto L_0x00d0
        L_0x00ca:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r0)
            r0 = r2
        L_0x00d0:
            com.google.android.gms.internal.ads.zzaxz.zzdn(r0)
            java.lang.String r0 = r12.getAbsolutePath()
            r8.zza(r9, r0, r1)
            return r13
        L_0x00db:
            java.io.File r1 = r8.zzexb
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r31)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00f5
            java.lang.String r1 = r1.concat(r2)
            r14 = r1
            goto L_0x00fb
        L_0x00f5:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r14 = r2
        L_0x00fb:
            java.util.Set<java.lang.String> r1 = zzewz
            monitor-enter(r1)
            java.util.Set<java.lang.String> r2 = zzewz     // Catch:{ all -> 0x05c7 }
            boolean r2 = r2.contains(r14)     // Catch:{ all -> 0x05c7 }
            if (r2 == 0) goto L_0x012b
            java.lang.String r0 = "Stream cache already in progress at "
            java.lang.String r2 = java.lang.String.valueOf(r31)     // Catch:{ all -> 0x05c7 }
            int r3 = r2.length()     // Catch:{ all -> 0x05c7 }
            if (r3 == 0) goto L_0x0117
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x05c7 }
            goto L_0x011d
        L_0x0117:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x05c7 }
            r2.<init>(r0)     // Catch:{ all -> 0x05c7 }
            r0 = r2
        L_0x011d:
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)     // Catch:{ all -> 0x05c7 }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ all -> 0x05c7 }
            java.lang.String r2 = "inProgress"
            r8.zza(r9, r0, r2, r10)     // Catch:{ all -> 0x05c7 }
            monitor-exit(r1)     // Catch:{ all -> 0x05c7 }
            return r11
        L_0x012b:
            java.util.Set<java.lang.String> r2 = zzewz     // Catch:{ all -> 0x05c7 }
            r2.add(r14)     // Catch:{ all -> 0x05c7 }
            monitor-exit(r1)     // Catch:{ all -> 0x05c7 }
            java.lang.String r15 = "error"
            com.google.android.gms.ads.internal.zzbv.zzls()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            com.google.android.gms.internal.ads.zzaac<java.lang.Integer> r1 = com.google.android.gms.internal.ads.zzaan.zzcoy     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r1 = r1.intValue()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r2.<init>(r9)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r3 = r2
            r2 = 0
        L_0x014d:
            int r2 = r2 + r13
            r4 = 20
            if (r2 > r4) goto L_0x051b
            java.net.URLConnection r5 = r3.openConnection()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r5.setConnectTimeout(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r5.setReadTimeout(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            boolean r6 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            if (r6 == 0) goto L_0x0510
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            com.google.android.gms.internal.ads.zzbax r6 = new com.google.android.gms.internal.ads.zzbax     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r6.<init>()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r6.zza(r5, r10)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r5.setInstanceFollowRedirects(r11)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r7 = r5.getResponseCode()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r6.zza(r5, r7)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r7 = r7 / 100
            r6 = 3
            if (r7 != r6) goto L_0x01f0
            java.lang.String r4 = "Location"
            java.lang.String r4 = r5.getHeaderField(r4)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r4 == 0) goto L_0x01e1
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r6.<init>(r3, r4)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r3 = r6.getProtocol()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r3 == 0) goto L_0x01d9
            java.lang.String r7 = "http"
            boolean r7 = r3.equals(r7)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r7 != 0) goto L_0x01b9
            java.lang.String r7 = "https"
            boolean r7 = r3.equals(r7)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r7 != 0) goto L_0x01b9
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r1 = "Unsupported scheme: "
            java.lang.String r2 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r3 = r2.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r3 == 0) goto L_0x01af
            java.lang.String r1 = r1.concat(r2)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            goto L_0x01b5
        L_0x01af:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r1 = r2
        L_0x01b5:
            r0.<init>(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            throw r0     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
        L_0x01b9:
            java.lang.String r3 = "Redirecting to "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r7 = r4.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r7 == 0) goto L_0x01ca
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            goto L_0x01d0
        L_0x01ca:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r3 = r4
        L_0x01d0:
            com.google.android.gms.internal.ads.zzaxz.zzdn(r3)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r5.disconnect()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r3 = r6
            goto L_0x014d
        L_0x01d9:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r1 = "Protocol is null"
            r0.<init>(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            throw r0     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
        L_0x01e1:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r1 = "Missing Location header in redirect"
            r0.<init>(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            throw r0     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
        L_0x01e9:
            r0 = move-exception
            goto L_0x01ec
        L_0x01eb:
            r0 = move-exception
        L_0x01ec:
            r2 = r10
        L_0x01ed:
            r1 = r14
            goto L_0x0534
        L_0x01f0:
            boolean r1 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            if (r1 == 0) goto L_0x0249
            r1 = r5
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r1 = r1.getResponseCode()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2 = 400(0x190, float:5.6E-43)
            if (r1 < r2) goto L_0x0249
            java.lang.String r15 = "badUrl"
            java.lang.String r0 = "HTTP request failed. Code: "
            java.lang.String r2 = java.lang.Integer.toString(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r3 = r2.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r3 == 0) goto L_0x0217
            java.lang.String r0 = r0.concat(r2)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2 = r0
            goto L_0x021c
        L_0x0217:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
        L_0x021c:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            java.lang.String r3 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            int r3 = r3 + 32
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            java.lang.String r3 = "HTTP status code "
            r4.append(r3)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            r4.append(r1)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            java.lang.String r1 = " at "
            r4.append(r1)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            r4.append(r9)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
            throw r0     // Catch:{ IOException -> 0x0247, RuntimeException -> 0x0245 }
        L_0x0245:
            r0 = move-exception
            goto L_0x01ed
        L_0x0247:
            r0 = move-exception
            goto L_0x01ed
        L_0x0249:
            int r7 = r5.getContentLength()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            if (r7 >= 0) goto L_0x0278
            java.lang.String r0 = "Stream cache aborted, missing content-length header at "
            java.lang.String r1 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r2 = r1.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r2 == 0) goto L_0x0260
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            goto L_0x0266
        L_0x0260:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r0 = r1
        L_0x0266:
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r1 = "contentLengthMissing"
            r8.zza(r9, r0, r1, r10)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.util.Set<java.lang.String> r0 = zzewz     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r0.remove(r14)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            return r11
        L_0x0278:
            java.text.DecimalFormat r1 = zzexa     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            long r2 = (long) r7     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            com.google.android.gms.internal.ads.zzaac<java.lang.Integer> r2 = com.google.android.gms.internal.ads.zzaan.zzcou     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            com.google.android.gms.internal.ads.zzaak r3 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r3 = r2.intValue()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            if (r7 <= r3) goto L_0x02e6
            java.lang.String r0 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r0 = r0.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r0 = r0 + 33
            java.lang.String r2 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r0 = r0 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r0 = "Content length "
            r2.append(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2.append(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r0 = " exceeds limit at "
            r2.append(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r2.append(r9)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r0 = r2.toString()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r0 = "File too big for full file cache. Size: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            int r2 = r1.length()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            if (r2 == 0) goto L_0x02d1
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            goto L_0x02d7
        L_0x02d1:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r0 = r1
        L_0x02d7:
            java.lang.String r1 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.lang.String r2 = "sizeExceeded"
            r8.zza(r9, r1, r2, r0)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            java.util.Set<java.lang.String> r0 = zzewz     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            r0.remove(r14)     // Catch:{ IOException -> 0x01eb, RuntimeException -> 0x01e9 }
            return r11
        L_0x02e6:
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r2 = r2.length()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r2 = r2 + r4
            java.lang.String r4 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r4 = r4.length()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            int r2 = r2 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r4.<init>(r2)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.String r2 = "Caching "
            r4.append(r2)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r4.append(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.String r1 = " bytes from "
            r4.append(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r4.append(r9)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            com.google.android.gms.internal.ads.zzaxz.zzdn(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.io.InputStream r1 = r5.getInputStream()     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.nio.channels.ReadableByteChannel r5 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            r4.<init>(r12)     // Catch:{ IOException -> 0x052e, RuntimeException -> 0x052c }
            java.nio.channels.FileChannel r2 = r4.getChannel()     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            r1 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            com.google.android.gms.common.util.Clock r16 = com.google.android.gms.ads.internal.zzbv.zzlm()     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            long r17 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r6 = com.google.android.gms.internal.ads.zzaan.zzcox     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            com.google.android.gms.internal.ads.zzaak r10 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            java.lang.Object r6 = r10.zzd(r6)     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ IOException -> 0x0506, RuntimeException -> 0x0504 }
            r10 = r14
            long r13 = r6.longValue()     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            com.google.android.gms.internal.ads.zzbai r6 = new com.google.android.gms.internal.ads.zzbai     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            r6.<init>(r13)     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r13 = com.google.android.gms.internal.ads.zzaan.zzcow     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            com.google.android.gms.internal.ads.zzaak r14 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            java.lang.Object r13 = r14.zzd(r13)     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            long r13 = r13.longValue()     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
        L_0x0359:
            int r20 = r5.read(r1)     // Catch:{ IOException -> 0x04ff, RuntimeException -> 0x04fd }
            if (r20 < 0) goto L_0x0479
            int r11 = r11 + r20
            if (r11 <= r3) goto L_0x0396
            java.lang.String r15 = "sizeExceeded"
            java.lang.String r0 = "File too big for full file cache. Size: "
            java.lang.String r1 = java.lang.Integer.toString(r11)     // Catch:{ IOException -> 0x0390, RuntimeException -> 0x038e }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x0390, RuntimeException -> 0x038e }
            int r2 = r1.length()     // Catch:{ IOException -> 0x0390, RuntimeException -> 0x038e }
            if (r2 == 0) goto L_0x037b
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x0390, RuntimeException -> 0x038e }
            r1 = r0
            goto L_0x0380
        L_0x037b:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x0390, RuntimeException -> 0x038e }
            r1.<init>(r0)     // Catch:{ IOException -> 0x0390, RuntimeException -> 0x038e }
        L_0x0380:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x038a, RuntimeException -> 0x0388 }
            java.lang.String r2 = "stream cache file size limit exceeded"
            r0.<init>(r2)     // Catch:{ IOException -> 0x038a, RuntimeException -> 0x0388 }
            throw r0     // Catch:{ IOException -> 0x038a, RuntimeException -> 0x0388 }
        L_0x0388:
            r0 = move-exception
            goto L_0x038b
        L_0x038a:
            r0 = move-exception
        L_0x038b:
            r2 = r1
            r1 = r10
            goto L_0x0393
        L_0x038e:
            r0 = move-exception
            goto L_0x0391
        L_0x0390:
            r0 = move-exception
        L_0x0391:
            r1 = r10
            r2 = 0
        L_0x0393:
            r10 = r4
            goto L_0x0534
        L_0x0396:
            r1.flip()     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
        L_0x0399:
            int r20 = r2.write(r1)     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            if (r20 > 0) goto L_0x046b
            r1.clear()     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            long r20 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            long r20 = r20 - r17
            r22 = 1000(0x3e8, double:4.94E-321)
            long r22 = r22 * r13
            int r24 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r24 > 0) goto L_0x0429
            r20 = r1
            boolean r1 = r8.zzexc     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            if (r1 != 0) goto L_0x0419
            boolean r1 = r6.tryAcquire()     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            if (r1 == 0) goto L_0x03f3
            java.lang.String r21 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzbat.zztu     // Catch:{ IOException -> 0x0471, RuntimeException -> 0x046f }
            r22 = r15
            com.google.android.gms.internal.ads.zzbfl r15 = new com.google.android.gms.internal.ads.zzbfl     // Catch:{ IOException -> 0x03ed, RuntimeException -> 0x03eb }
            r23 = 0
            r24 = r10
            r10 = r1
            r1 = r15
            r25 = r2
            r2 = r30
            r26 = r3
            r3 = r31
            r27 = r4
            r4 = r21
            r21 = r5
            r5 = r11
            r19 = r6
            r28 = 3
            r6 = r7
            r29 = r7
            r7 = r23
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            r10.post(r15)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            goto L_0x0405
        L_0x03eb:
            r0 = move-exception
            goto L_0x03ee
        L_0x03ed:
            r0 = move-exception
        L_0x03ee:
            r27 = r4
            r1 = r10
            goto L_0x04fa
        L_0x03f3:
            r25 = r2
            r26 = r3
            r27 = r4
            r21 = r5
            r19 = r6
            r29 = r7
            r24 = r10
            r22 = r15
            r28 = 3
        L_0x0405:
            r6 = r19
            r1 = r20
            r5 = r21
            r15 = r22
            r10 = r24
            r2 = r25
            r3 = r26
            r4 = r27
            r7 = r29
            goto L_0x0359
        L_0x0419:
            r27 = r4
            r24 = r10
            r22 = r15
            java.lang.String r15 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            java.lang.String r1 = "abort requested"
            r0.<init>(r1)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            throw r0     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
        L_0x0429:
            r27 = r4
            r24 = r10
            r22 = r15
            java.lang.String r15 = "downloadTimeout"
            java.lang.String r0 = java.lang.Long.toString(r13)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            int r1 = r1.length()     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            int r1 = r1 + 29
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            java.lang.String r1 = "Timeout exceeded. Limit: "
            r2.append(r1)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            r2.append(r0)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            java.lang.String r0 = " sec"
            r2.append(r0)     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            java.lang.String r10 = r2.toString()     // Catch:{ IOException -> 0x0469, RuntimeException -> 0x0467 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x045f, RuntimeException -> 0x045d }
            java.lang.String r1 = "stream cache time limit exceeded"
            r0.<init>(r1)     // Catch:{ IOException -> 0x045f, RuntimeException -> 0x045d }
            throw r0     // Catch:{ IOException -> 0x045f, RuntimeException -> 0x045d }
        L_0x045d:
            r0 = move-exception
            goto L_0x0460
        L_0x045f:
            r0 = move-exception
        L_0x0460:
            r2 = r10
            r1 = r24
            r10 = r27
            goto L_0x0534
        L_0x0467:
            r0 = move-exception
            goto L_0x04c8
        L_0x0469:
            r0 = move-exception
            goto L_0x04c8
        L_0x046b:
            r28 = 3
            goto L_0x0399
        L_0x046f:
            r0 = move-exception
            goto L_0x0472
        L_0x0471:
            r0 = move-exception
        L_0x0472:
            r27 = r4
            r22 = r15
            r1 = r10
            goto L_0x050c
        L_0x0479:
            r27 = r4
            r24 = r10
            r22 = r15
            r28 = 3
            r27.close()     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            boolean r1 = com.google.android.gms.internal.ads.zzaxz.isLoggable(r28)     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            if (r1 == 0) goto L_0x04cb
            java.text.DecimalFormat r1 = zzexa     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            long r2 = (long) r11     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            int r2 = r2 + 22
            java.lang.String r3 = java.lang.String.valueOf(r31)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            java.lang.String r2 = "Preloaded "
            r3.append(r2)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            r3.append(r1)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            java.lang.String r1 = " bytes from "
            r3.append(r1)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            r3.append(r9)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            com.google.android.gms.internal.ads.zzaxz.zzdn(r1)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            r1 = 0
            r2 = 1
            goto L_0x04cd
        L_0x04c3:
            r0 = move-exception
            goto L_0x04c6
        L_0x04c5:
            r0 = move-exception
        L_0x04c6:
            r15 = r22
        L_0x04c8:
            r1 = r24
            goto L_0x050c
        L_0x04cb:
            r1 = 0
            r2 = 1
        L_0x04cd:
            r12.setReadable(r2, r1)     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            boolean r1 = r0.isFile()     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            if (r1 == 0) goto L_0x04de
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            r0.setLastModified(r1)     // Catch:{ IOException -> 0x04c5, RuntimeException -> 0x04c3 }
            goto L_0x04e1
        L_0x04de:
            r0.createNewFile()     // Catch:{ IOException -> 0x04e1, RuntimeException -> 0x04c3 }
        L_0x04e1:
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            r8.zza(r9, r0, r11)     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            java.util.Set<java.lang.String> r0 = zzewz     // Catch:{ IOException -> 0x04f7, RuntimeException -> 0x04f5 }
            r1 = r24
            r0.remove(r1)     // Catch:{ IOException -> 0x04f3, RuntimeException -> 0x04f1 }
            r0 = 1
            return r0
        L_0x04f1:
            r0 = move-exception
            goto L_0x04fa
        L_0x04f3:
            r0 = move-exception
            goto L_0x04fa
        L_0x04f5:
            r0 = move-exception
            goto L_0x04f8
        L_0x04f7:
            r0 = move-exception
        L_0x04f8:
            r1 = r24
        L_0x04fa:
            r15 = r22
            goto L_0x050c
        L_0x04fd:
            r0 = move-exception
            goto L_0x0500
        L_0x04ff:
            r0 = move-exception
        L_0x0500:
            r27 = r4
            r1 = r10
            goto L_0x050a
        L_0x0504:
            r0 = move-exception
            goto L_0x0507
        L_0x0506:
            r0 = move-exception
        L_0x0507:
            r27 = r4
            r1 = r14
        L_0x050a:
            r22 = r15
        L_0x050c:
            r10 = r27
            r2 = 0
            goto L_0x0534
        L_0x0510:
            r1 = r14
            r22 = r15
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0528, RuntimeException -> 0x0526 }
            java.lang.String r2 = "Invalid protocol."
            r0.<init>(r2)     // Catch:{ IOException -> 0x0528, RuntimeException -> 0x0526 }
            throw r0     // Catch:{ IOException -> 0x0528, RuntimeException -> 0x0526 }
        L_0x051b:
            r1 = r14
            r22 = r15
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0528, RuntimeException -> 0x0526 }
            java.lang.String r2 = "Too many redirects (20)"
            r0.<init>(r2)     // Catch:{ IOException -> 0x0528, RuntimeException -> 0x0526 }
            throw r0     // Catch:{ IOException -> 0x0528, RuntimeException -> 0x0526 }
        L_0x0526:
            r0 = move-exception
            goto L_0x0529
        L_0x0528:
            r0 = move-exception
        L_0x0529:
            r15 = r22
            goto L_0x0532
        L_0x052c:
            r0 = move-exception
            goto L_0x052f
        L_0x052e:
            r0 = move-exception
        L_0x052f:
            r1 = r14
            r22 = r15
        L_0x0532:
            r2 = 0
            r10 = 0
        L_0x0534:
            boolean r3 = r0 instanceof java.lang.RuntimeException
            if (r3 == 0) goto L_0x0541
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            java.lang.String r4 = "VideoStreamFullFileCache.preload"
            r3.zza(r0, r4)
        L_0x0541:
            r10.close()     // Catch:{ IOException | NullPointerException -> 0x0544 }
        L_0x0544:
            boolean r3 = r8.zzexc
            if (r3 == 0) goto L_0x056c
            java.lang.String r0 = java.lang.String.valueOf(r31)
            int r0 = r0.length()
            int r0 = r0 + 26
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.String r0 = "Preload aborted for URL \""
            r3.append(r0)
            r3.append(r9)
            java.lang.String r0 = "\""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.google.android.gms.internal.ads.zzaxz.zzen(r0)
            goto L_0x058f
        L_0x056c:
            java.lang.String r3 = java.lang.String.valueOf(r31)
            int r3 = r3.length()
            int r3 = r3 + 25
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Preload failed for URL \""
            r4.append(r3)
            r4.append(r9)
            java.lang.String r3 = "\""
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.google.android.gms.internal.ads.zzaxz.zzc(r3, r0)
        L_0x058f:
            boolean r0 = r12.exists()
            if (r0 == 0) goto L_0x05b9
            boolean r0 = r12.delete()
            if (r0 != 0) goto L_0x05b9
            java.lang.String r0 = "Could not delete partial cache file at "
            java.lang.String r3 = r12.getAbsolutePath()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x05b0
            java.lang.String r0 = r0.concat(r3)
            goto L_0x05b6
        L_0x05b0:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r0)
            r0 = r3
        L_0x05b6:
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)
        L_0x05b9:
            java.lang.String r0 = r12.getAbsolutePath()
            r8.zza(r9, r0, r15, r2)
            java.util.Set<java.lang.String> r0 = zzewz
            r0.remove(r1)
            r1 = 0
            return r1
        L_0x05c7:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x05c7 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfs.zzex(java.lang.String):boolean");
    }

    public final void abort() {
        this.zzexc = true;
    }

    private final File zzc(File file) {
        return new File(this.zzexb, String.valueOf(file.getName()).concat(".done"));
    }
}
