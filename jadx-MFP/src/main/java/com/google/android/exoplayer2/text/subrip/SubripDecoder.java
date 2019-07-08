package com.google.android.exoplayer2.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubripDecoder extends SimpleSubtitleDecoder {
    private static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    private static final String ALIGN_BOTTOM_MID = "{\\an2}";
    private static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    private static final String ALIGN_MID_LEFT = "{\\an4}";
    private static final String ALIGN_MID_MID = "{\\an5}";
    private static final String ALIGN_MID_RIGHT = "{\\an6}";
    private static final String ALIGN_TOP_LEFT = "{\\an7}";
    private static final String ALIGN_TOP_MID = "{\\an8}";
    private static final String ALIGN_TOP_RIGHT = "{\\an9}";
    static final float END_FRACTION = 0.92f;
    static final float MID_FRACTION = 0.5f;
    static final float START_FRACTION = 0.08f;
    private static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    private static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+),(\\d+)";
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    private static final String TAG = "SubripDecoder";
    private final ArrayList<String> tags = new ArrayList<>();
    private final StringBuilder textBuilder = new StringBuilder();

    static float getFractionalPositionForAnchorType(int i) {
        switch (i) {
            case 0:
                return 0.08f;
            case 1:
                return 0.5f;
            default:
                return END_FRACTION;
        }
    }

    public SubripDecoder() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public SubripSubtitle decode(byte[] bArr, int i, boolean z) {
        String str;
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                break;
            } else if (readLine.length() != 0) {
                try {
                    Integer.parseInt(readLine);
                    String readLine2 = parsableByteArray.readLine();
                    if (readLine2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(readLine2);
                    if (matcher.matches()) {
                        boolean z2 = true;
                        longArray.add(parseTimecode(matcher, 1));
                        int i2 = 0;
                        if (!TextUtils.isEmpty(matcher.group(6))) {
                            longArray.add(parseTimecode(matcher, 6));
                        } else {
                            z2 = false;
                        }
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        while (true) {
                            String readLine3 = parsableByteArray.readLine();
                            if (TextUtils.isEmpty(readLine3)) {
                                break;
                            }
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(readLine3, this.tags));
                        }
                        Spanned fromHtml = Html.fromHtml(this.textBuilder.toString());
                        while (true) {
                            if (i2 >= this.tags.size()) {
                                str = null;
                                break;
                            }
                            str = (String) this.tags.get(i2);
                            if (str.matches(SUBRIP_ALIGNMENT_TAG)) {
                                break;
                            }
                            i2++;
                        }
                        arrayList.add(buildCue(fromHtml, str));
                        if (z2) {
                            arrayList.add(null);
                        }
                    } else {
                        String str2 = TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Skipping invalid timing: ");
                        sb.append(readLine2);
                        Log.w(str2, sb.toString());
                    }
                } catch (NumberFormatException unused) {
                    String str3 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Skipping invalid index: ");
                    sb2.append(readLine);
                    Log.w(str3, sb2.toString());
                }
            }
        }
        Cue[] cueArr = new Cue[arrayList.size()];
        arrayList.toArray(cueArr);
        return new SubripSubtitle(cueArr, longArray.toArray());
    }

    private String processLine(String str, ArrayList<String> arrayList) {
        String trim = str.trim();
        StringBuilder sb = new StringBuilder(trim);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(trim);
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group();
            arrayList.add(group);
            int start = matcher.start() - i;
            int length = group.length();
            sb.replace(start, start + length, "");
            i += length;
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        if (r0.equals(ALIGN_TOP_RIGHT) != false) goto L_0x00f8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.text.Cue buildCue(android.text.Spanned r15, @android.support.annotation.Nullable java.lang.String r16) {
        /*
            r14 = this;
            r0 = r16
            if (r0 != 0) goto L_0x000b
            com.google.android.exoplayer2.text.Cue r0 = new com.google.android.exoplayer2.text.Cue
            r2 = r15
            r0.<init>(r15)
            return r0
        L_0x000b:
            r2 = r15
            int r1 = r16.hashCode()
            r3 = 5
            r4 = 8
            r5 = 4
            r6 = 7
            r7 = 3
            r8 = 6
            r9 = -1
            r10 = 2
            r11 = 0
            r12 = 1
            switch(r1) {
                case -685620710: goto L_0x0078;
                case -685620679: goto L_0x006d;
                case -685620648: goto L_0x0062;
                case -685620617: goto L_0x0057;
                case -685620586: goto L_0x004c;
                case -685620555: goto L_0x0041;
                case -685620524: goto L_0x0036;
                case -685620493: goto L_0x002a;
                case -685620462: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0083
        L_0x001f:
            java.lang.String r1 = "{\\an9}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 5
            goto L_0x0084
        L_0x002a:
            java.lang.String r1 = "{\\an8}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 8
            goto L_0x0084
        L_0x0036:
            java.lang.String r1 = "{\\an7}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 2
            goto L_0x0084
        L_0x0041:
            java.lang.String r1 = "{\\an6}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 4
            goto L_0x0084
        L_0x004c:
            java.lang.String r1 = "{\\an5}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 7
            goto L_0x0084
        L_0x0057:
            java.lang.String r1 = "{\\an4}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 1
            goto L_0x0084
        L_0x0062:
            java.lang.String r1 = "{\\an3}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 3
            goto L_0x0084
        L_0x006d:
            java.lang.String r1 = "{\\an2}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 6
            goto L_0x0084
        L_0x0078:
            java.lang.String r1 = "{\\an1}"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0083
            r1 = 0
            goto L_0x0084
        L_0x0083:
            r1 = -1
        L_0x0084:
            switch(r1) {
                case 0: goto L_0x008b;
                case 1: goto L_0x008b;
                case 2: goto L_0x008b;
                case 3: goto L_0x0089;
                case 4: goto L_0x0089;
                case 5: goto L_0x0089;
                default: goto L_0x0087;
            }
        L_0x0087:
            r13 = 1
            goto L_0x008c
        L_0x0089:
            r13 = 2
            goto L_0x008c
        L_0x008b:
            r13 = 0
        L_0x008c:
            int r1 = r16.hashCode()
            switch(r1) {
                case -685620710: goto L_0x00ec;
                case -685620679: goto L_0x00e1;
                case -685620648: goto L_0x00d6;
                case -685620617: goto L_0x00cb;
                case -685620586: goto L_0x00c0;
                case -685620555: goto L_0x00b4;
                case -685620524: goto L_0x00a9;
                case -685620493: goto L_0x009e;
                case -685620462: goto L_0x0094;
                default: goto L_0x0093;
            }
        L_0x0093:
            goto L_0x00f7
        L_0x0094:
            java.lang.String r1 = "{\\an9}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            goto L_0x00f8
        L_0x009e:
            java.lang.String r1 = "{\\an8}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 4
            goto L_0x00f8
        L_0x00a9:
            java.lang.String r1 = "{\\an7}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 3
            goto L_0x00f8
        L_0x00b4:
            java.lang.String r1 = "{\\an6}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 8
            goto L_0x00f8
        L_0x00c0:
            java.lang.String r1 = "{\\an5}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 7
            goto L_0x00f8
        L_0x00cb:
            java.lang.String r1 = "{\\an4}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 6
            goto L_0x00f8
        L_0x00d6:
            java.lang.String r1 = "{\\an3}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 2
            goto L_0x00f8
        L_0x00e1:
            java.lang.String r1 = "{\\an2}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 1
            goto L_0x00f8
        L_0x00ec:
            java.lang.String r1 = "{\\an1}"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f7
            r3 = 0
            goto L_0x00f8
        L_0x00f7:
            r3 = -1
        L_0x00f8:
            switch(r3) {
                case 0: goto L_0x00ff;
                case 1: goto L_0x00ff;
                case 2: goto L_0x00ff;
                case 3: goto L_0x00fd;
                case 4: goto L_0x00fd;
                case 5: goto L_0x00fd;
                default: goto L_0x00fb;
            }
        L_0x00fb:
            r6 = 1
            goto L_0x0100
        L_0x00fd:
            r6 = 0
            goto L_0x0100
        L_0x00ff:
            r6 = 2
        L_0x0100:
            com.google.android.exoplayer2.text.Cue r0 = new com.google.android.exoplayer2.text.Cue
            r3 = 0
            float r4 = getFractionalPositionForAnchorType(r6)
            r5 = 0
            float r7 = getFractionalPositionForAnchorType(r13)
            r9 = 1
            r1 = r0
            r2 = r15
            r8 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.subrip.SubripDecoder.buildCue(android.text.Spanned, java.lang.String):com.google.android.exoplayer2.text.Cue");
    }

    private static long parseTimecode(Matcher matcher, int i) {
        return ((Long.parseLong(matcher.group(i + 1)) * 60 * 60 * 1000) + (Long.parseLong(matcher.group(i + 2)) * 60 * 1000) + (Long.parseLong(matcher.group(i + 3)) * 1000) + Long.parseLong(matcher.group(i + 4))) * 1000;
    }
}
