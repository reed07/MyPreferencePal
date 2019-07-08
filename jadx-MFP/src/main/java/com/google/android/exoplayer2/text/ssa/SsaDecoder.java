package com.google.android.exoplayer2.text.ssa;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue: ";
    private static final String FORMAT_LINE_PREFIX = "Format: ";
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    private static final String TAG = "SsaDecoder";
    private int formatEndIndex;
    private int formatKeyCount;
    private int formatStartIndex;
    private int formatTextIndex;
    private final boolean haveInitializationData;

    public SsaDecoder() {
        this(null);
    }

    public SsaDecoder(List<byte[]> list) {
        super(TAG);
        if (list == null || list.isEmpty()) {
            this.haveInitializationData = false;
            return;
        }
        this.haveInitializationData = true;
        String fromUtf8Bytes = Util.fromUtf8Bytes((byte[]) list.get(0));
        Assertions.checkArgument(fromUtf8Bytes.startsWith(FORMAT_LINE_PREFIX));
        parseFormatLine(fromUtf8Bytes);
        parseHeader(new ParsableByteArray((byte[]) list.get(1)));
    }

    /* access modifiers changed from: protected */
    public SsaSubtitle decode(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        if (!this.haveInitializationData) {
            parseHeader(parsableByteArray);
        }
        parseEventBody(parsableByteArray, arrayList, longArray);
        Cue[] cueArr = new Cue[arrayList.size()];
        arrayList.toArray(cueArr);
        return new SsaSubtitle(cueArr, longArray.toArray());
    }

    private void parseHeader(ParsableByteArray parsableByteArray) {
        String readLine;
        do {
            readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
        } while (!readLine.startsWith("[Events]"));
    }

    private void parseEventBody(ParsableByteArray parsableByteArray, List<Cue> list, LongArray longArray) {
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
            if (!this.haveInitializationData && readLine.startsWith(FORMAT_LINE_PREFIX)) {
                parseFormatLine(readLine);
            } else if (readLine.startsWith(DIALOGUE_LINE_PREFIX)) {
                parseDialogueLine(readLine, list, longArray);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0065 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseFormatLine(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 8
            java.lang.String r7 = r7.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r7 = android.text.TextUtils.split(r7, r0)
            int r0 = r7.length
            r6.formatKeyCount = r0
            r0 = -1
            r6.formatStartIndex = r0
            r6.formatEndIndex = r0
            r6.formatTextIndex = r0
            r1 = 0
            r2 = 0
        L_0x0018:
            int r3 = r6.formatKeyCount
            if (r2 >= r3) goto L_0x0068
            r3 = r7[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3)
            int r4 = r3.hashCode()
            r5 = 100571(0x188db, float:1.4093E-40)
            if (r4 == r5) goto L_0x004e
            r5 = 3556653(0x36452d, float:4.983932E-39)
            if (r4 == r5) goto L_0x0044
            r5 = 109757538(0x68ac462, float:5.219839E-35)
            if (r4 == r5) goto L_0x003a
            goto L_0x0058
        L_0x003a:
            java.lang.String r4 = "start"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0058
            r3 = 0
            goto L_0x0059
        L_0x0044:
            java.lang.String r4 = "text"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0058
            r3 = 2
            goto L_0x0059
        L_0x004e:
            java.lang.String r4 = "end"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0058
            r3 = 1
            goto L_0x0059
        L_0x0058:
            r3 = -1
        L_0x0059:
            switch(r3) {
                case 0: goto L_0x0063;
                case 1: goto L_0x0060;
                case 2: goto L_0x005d;
                default: goto L_0x005c;
            }
        L_0x005c:
            goto L_0x0065
        L_0x005d:
            r6.formatTextIndex = r2
            goto L_0x0065
        L_0x0060:
            r6.formatEndIndex = r2
            goto L_0x0065
        L_0x0063:
            r6.formatStartIndex = r2
        L_0x0065:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0068:
            int r7 = r6.formatStartIndex
            if (r7 == r0) goto L_0x0074
            int r7 = r6.formatEndIndex
            if (r7 == r0) goto L_0x0074
            int r7 = r6.formatTextIndex
            if (r7 != r0) goto L_0x0076
        L_0x0074:
            r6.formatKeyCount = r1
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDecoder.parseFormatLine(java.lang.String):void");
    }

    private void parseDialogueLine(String str, List<Cue> list, LongArray longArray) {
        long j;
        if (this.formatKeyCount == 0) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Skipping dialogue line before complete format: ");
            sb.append(str);
            Log.w(str2, sb.toString());
            return;
        }
        String[] split = str.substring(10).split(",", this.formatKeyCount);
        if (split.length != this.formatKeyCount) {
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Skipping dialogue line with fewer columns than format: ");
            sb2.append(str);
            Log.w(str3, sb2.toString());
            return;
        }
        long parseTimecodeUs = parseTimecodeUs(split[this.formatStartIndex]);
        if (parseTimecodeUs == -9223372036854775807L) {
            String str4 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Skipping invalid timing: ");
            sb3.append(str);
            Log.w(str4, sb3.toString());
            return;
        }
        String str5 = split[this.formatEndIndex];
        if (!str5.trim().isEmpty()) {
            j = parseTimecodeUs(str5);
            if (j == -9223372036854775807L) {
                String str6 = TAG;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Skipping invalid timing: ");
                sb4.append(str);
                Log.w(str6, sb4.toString());
                return;
            }
        } else {
            j = -9223372036854775807L;
        }
        list.add(new Cue(split[this.formatTextIndex].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        longArray.add(parseTimecodeUs);
        if (j != -9223372036854775807L) {
            list.add(null);
            longArray.add(j);
        }
    }

    public static long parseTimecodeUs(String str) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return -9223372036854775807L;
        }
        return (Long.parseLong(matcher.group(1)) * 60 * 60 * 1000000) + (Long.parseLong(matcher.group(2)) * 60 * 1000000) + (Long.parseLong(matcher.group(3)) * 1000000) + (Long.parseLong(matcher.group(4)) * 10000);
    }
}
