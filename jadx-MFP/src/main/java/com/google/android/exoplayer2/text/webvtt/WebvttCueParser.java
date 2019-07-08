package com.google.android.exoplayer2.text.webvtt;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.exoplayer2.text.webvtt.WebvttCue.Builder;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttCueParser {
    private static final char CHAR_AMPERSAND = '&';
    private static final char CHAR_GREATER_THAN = '>';
    private static final char CHAR_LESS_THAN = '<';
    private static final char CHAR_SEMI_COLON = ';';
    private static final char CHAR_SLASH = '/';
    private static final char CHAR_SPACE = ' ';
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    private static final String ENTITY_AMPERSAND = "amp";
    private static final String ENTITY_GREATER_THAN = "gt";
    private static final String ENTITY_LESS_THAN = "lt";
    private static final String ENTITY_NON_BREAK_SPACE = "nbsp";
    private static final int STYLE_BOLD = 1;
    private static final int STYLE_ITALIC = 2;
    private static final String TAG = "WebvttCueParser";
    private static final String TAG_BOLD = "b";
    private static final String TAG_CLASS = "c";
    private static final String TAG_ITALIC = "i";
    private static final String TAG_LANG = "lang";
    private static final String TAG_UNDERLINE = "u";
    private static final String TAG_VOICE = "v";
    private final StringBuilder textBuilder = new StringBuilder();

    private static final class StartTag {
        private static final String[] NO_CLASSES = new String[0];
        public final String[] classes;
        public final String name;
        public final int position;
        public final String voice;

        private StartTag(String str, int i, String str2, String[] strArr) {
            this.position = i;
            this.name = str;
            this.voice = str2;
            this.classes = strArr;
        }

        public static StartTag buildStartTag(String str, int i) {
            String str2;
            String[] strArr;
            String trim = str.trim();
            if (trim.isEmpty()) {
                return null;
            }
            int indexOf = trim.indexOf(" ");
            if (indexOf == -1) {
                str2 = "";
            } else {
                String trim2 = trim.substring(indexOf).trim();
                trim = trim.substring(0, indexOf);
                str2 = trim2;
            }
            String[] split = Util.split(trim, "\\.");
            String str3 = split[0];
            if (split.length > 1) {
                strArr = (String[]) Arrays.copyOfRange(split, 1, split.length);
            } else {
                strArr = NO_CLASSES;
            }
            return new StartTag(str3, i, str2, strArr);
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", new String[0]);
        }
    }

    private static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int i, WebvttCssStyle webvttCssStyle) {
            this.score = i;
            this.style = webvttCssStyle;
        }

        public int compareTo(@NonNull StyleMatch styleMatch) {
            return this.score - styleMatch.score;
        }
    }

    public boolean parseCue(ParsableByteArray parsableByteArray, Builder builder, List<WebvttCssStyle> list) {
        String readLine = parsableByteArray.readLine();
        if (readLine == null) {
            return false;
        }
        Matcher matcher = CUE_HEADER_PATTERN.matcher(readLine);
        if (matcher.matches()) {
            return parseCue(null, matcher, parsableByteArray, builder, this.textBuilder, list);
        }
        String readLine2 = parsableByteArray.readLine();
        if (readLine2 == null) {
            return false;
        }
        Matcher matcher2 = CUE_HEADER_PATTERN.matcher(readLine2);
        if (!matcher2.matches()) {
            return false;
        }
        return parseCue(readLine.trim(), matcher2, parsableByteArray, builder, this.textBuilder, list);
    }

    static void parseCueSettingsList(String str, Builder builder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            try {
                if (AbstractEvent.LINE.equals(group)) {
                    parseLineAttribute(group2, builder);
                } else if ("align".equals(group)) {
                    builder.setTextAlignment(parseTextAlignment(group2));
                } else if ("position".equals(group)) {
                    parsePositionAttribute(group2, builder);
                } else if (AbstractEvent.SIZE.equals(group)) {
                    builder.setWidth(WebvttParserUtil.parsePercentage(group2));
                } else {
                    String str2 = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown cue setting ");
                    sb.append(group);
                    sb.append(":");
                    sb.append(group2);
                    Log.w(str2, sb.toString());
                }
            } catch (NumberFormatException unused) {
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Skipping bad cue setting: ");
                sb2.append(matcher.group());
                Log.w(str3, sb2.toString());
            }
        }
    }

    static void parseCueText(String str, String str2, Builder builder, List<WebvttCssStyle> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char charAt = str2.charAt(i);
            if (charAt == '&') {
                i++;
                int indexOf = str2.indexOf(59, i);
                int indexOf2 = str2.indexOf(32, i);
                if (indexOf == -1) {
                    indexOf = indexOf2;
                } else if (indexOf2 != -1) {
                    indexOf = Math.min(indexOf, indexOf2);
                }
                if (indexOf != -1) {
                    applyEntity(str2.substring(i, indexOf), spannableStringBuilder);
                    if (indexOf == indexOf2) {
                        spannableStringBuilder.append(" ");
                    }
                    i = indexOf + 1;
                } else {
                    spannableStringBuilder.append(charAt);
                }
            } else if (charAt != '<') {
                spannableStringBuilder.append(charAt);
                i++;
            } else {
                int i2 = i + 1;
                if (i2 >= str2.length()) {
                    i = i2;
                } else {
                    int i3 = 1;
                    boolean z = str2.charAt(i2) == '/';
                    int findEndOfTag = findEndOfTag(str2, i2);
                    int i4 = findEndOfTag - 2;
                    boolean z2 = str2.charAt(i4) == '/';
                    if (z) {
                        i3 = 2;
                    }
                    int i5 = i + i3;
                    if (!z2) {
                        i4 = findEndOfTag - 1;
                    }
                    String substring = str2.substring(i5, i4);
                    String tagName = getTagName(substring);
                    if (tagName == null || !isSupportedTag(tagName)) {
                        i = findEndOfTag;
                    } else {
                        if (z) {
                            while (!arrayDeque.isEmpty()) {
                                StartTag startTag = (StartTag) arrayDeque.pop();
                                applySpansForTag(str, startTag, spannableStringBuilder, list, arrayList);
                                if (startTag.name.equals(tagName)) {
                                    break;
                                }
                            }
                        } else if (!z2) {
                            arrayDeque.push(StartTag.buildStartTag(substring, spannableStringBuilder.length()));
                        }
                        i = findEndOfTag;
                    }
                }
            }
        }
        while (!arrayDeque.isEmpty()) {
            applySpansForTag(str, (StartTag) arrayDeque.pop(), spannableStringBuilder, list, arrayList);
        }
        applySpansForTag(str, StartTag.buildWholeCueVirtualTag(), spannableStringBuilder, list, arrayList);
        builder.setText(spannableStringBuilder);
    }

    private static boolean parseCue(String str, Matcher matcher, ParsableByteArray parsableByteArray, Builder builder, StringBuilder sb, List<WebvttCssStyle> list) {
        try {
            builder.setStartTime(WebvttParserUtil.parseTimestampUs(matcher.group(1))).setEndTime(WebvttParserUtil.parseTimestampUs(matcher.group(2)));
            parseCueSettingsList(matcher.group(3), builder);
            sb.setLength(0);
            while (true) {
                String readLine = parsableByteArray.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(readLine.trim());
                } else {
                    parseCueText(str, sb.toString(), builder, list);
                    return true;
                }
            }
        } catch (NumberFormatException unused) {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Skipping cue with bad header: ");
            sb2.append(matcher.group());
            Log.w(str2, sb2.toString());
            return false;
        }
    }

    private static void parseLineAttribute(String str, Builder builder) throws NumberFormatException {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            builder.setLineAnchor(parsePositionAnchor(str.substring(indexOf + 1)));
            str = str.substring(0, indexOf);
        } else {
            builder.setLineAnchor(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            builder.setLine(WebvttParserUtil.parsePercentage(str)).setLineType(0);
            return;
        }
        int parseInt = Integer.parseInt(str);
        if (parseInt < 0) {
            parseInt--;
        }
        builder.setLine((float) parseInt).setLineType(1);
    }

    private static void parsePositionAttribute(String str, Builder builder) throws NumberFormatException {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            builder.setPositionAnchor(parsePositionAnchor(str.substring(indexOf + 1)));
            str = str.substring(0, indexOf);
        } else {
            builder.setPositionAnchor(Integer.MIN_VALUE);
        }
        builder.setPosition(WebvttParserUtil.parsePercentage(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int parsePositionAnchor(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == r1) goto L_0x003a
            r1 = -1074341483(0xffffffffbff6d995, float:-1.9285151)
            if (r0 == r1) goto L_0x0030
            r1 = 100571(0x188db, float:1.4093E-40)
            if (r0 == r1) goto L_0x0026
            r1 = 109757538(0x68ac462, float:5.219839E-35)
            if (r0 == r1) goto L_0x001c
            goto L_0x0044
        L_0x001c:
            java.lang.String r0 = "start"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 0
            goto L_0x0045
        L_0x0026:
            java.lang.String r0 = "end"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 3
            goto L_0x0045
        L_0x0030:
            java.lang.String r0 = "middle"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 2
            goto L_0x0045
        L_0x003a:
            java.lang.String r0 = "center"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 1
            goto L_0x0045
        L_0x0044:
            r0 = -1
        L_0x0045:
            switch(r0) {
                case 0: goto L_0x0063;
                case 1: goto L_0x0062;
                case 2: goto L_0x0062;
                case 3: goto L_0x0061;
                default: goto L_0x0048;
            }
        L_0x0048:
            java.lang.String r0 = "WebvttCueParser"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid anchor value: "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.google.android.exoplayer2.util.Log.w(r0, r5)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            return r5
        L_0x0061:
            return r2
        L_0x0062:
            return r3
        L_0x0063:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.parsePositionAnchor(java.lang.String):int");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.text.Layout.Alignment parseTextAlignment(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -1364013995: goto L_0x003a;
                case -1074341483: goto L_0x0030;
                case 100571: goto L_0x0026;
                case 3317767: goto L_0x001c;
                case 108511772: goto L_0x0012;
                case 109757538: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0044
        L_0x0008:
            java.lang.String r0 = "start"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 0
            goto L_0x0045
        L_0x0012:
            java.lang.String r0 = "right"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 5
            goto L_0x0045
        L_0x001c:
            java.lang.String r0 = "left"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 1
            goto L_0x0045
        L_0x0026:
            java.lang.String r0 = "end"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 4
            goto L_0x0045
        L_0x0030:
            java.lang.String r0 = "middle"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 3
            goto L_0x0045
        L_0x003a:
            java.lang.String r0 = "center"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0044
            r0 = 2
            goto L_0x0045
        L_0x0044:
            r0 = -1
        L_0x0045:
            switch(r0) {
                case 0: goto L_0x0066;
                case 1: goto L_0x0066;
                case 2: goto L_0x0063;
                case 3: goto L_0x0063;
                case 4: goto L_0x0060;
                case 5: goto L_0x0060;
                default: goto L_0x0048;
            }
        L_0x0048:
            java.lang.String r0 = "WebvttCueParser"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid alignment value: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.google.android.exoplayer2.util.Log.w(r0, r3)
            r3 = 0
            return r3
        L_0x0060:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            return r3
        L_0x0063:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
            return r3
        L_0x0066:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.parseTextAlignment(java.lang.String):android.text.Layout$Alignment");
    }

    private static int findEndOfTag(String str, int i) {
        int indexOf = str.indexOf(62, i);
        return indexOf == -1 ? str.length() : indexOf + 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void applyEntity(java.lang.String r2, android.text.SpannableStringBuilder r3) {
        /*
            int r0 = r2.hashCode()
            r1 = 3309(0xced, float:4.637E-42)
            if (r0 == r1) goto L_0x0035
            r1 = 3464(0xd88, float:4.854E-42)
            if (r0 == r1) goto L_0x002b
            r1 = 96708(0x179c4, float:1.35517E-40)
            if (r0 == r1) goto L_0x0021
            r1 = 3374865(0x337f11, float:4.729193E-39)
            if (r0 == r1) goto L_0x0017
            goto L_0x003f
        L_0x0017:
            java.lang.String r0 = "nbsp"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 2
            goto L_0x0040
        L_0x0021:
            java.lang.String r0 = "amp"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 3
            goto L_0x0040
        L_0x002b:
            java.lang.String r0 = "lt"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 0
            goto L_0x0040
        L_0x0035:
            java.lang.String r0 = "gt"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 1
            goto L_0x0040
        L_0x003f:
            r0 = -1
        L_0x0040:
            switch(r0) {
                case 0: goto L_0x0071;
                case 1: goto L_0x006b;
                case 2: goto L_0x0065;
                case 3: goto L_0x005f;
                default: goto L_0x0043;
            }
        L_0x0043:
            java.lang.String r3 = "WebvttCueParser"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "ignoring unsupported entity: '&"
            r0.append(r1)
            r0.append(r2)
            java.lang.String r2 = ";'"
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            com.google.android.exoplayer2.util.Log.w(r3, r2)
            goto L_0x0076
        L_0x005f:
            r2 = 38
            r3.append(r2)
            goto L_0x0076
        L_0x0065:
            r2 = 32
            r3.append(r2)
            goto L_0x0076
        L_0x006b:
            r2 = 62
            r3.append(r2)
            goto L_0x0076
        L_0x0071:
            r2 = 60
            r3.append(r2)
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.applyEntity(java.lang.String, android.text.SpannableStringBuilder):void");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isSupportedTag(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case 98: goto L_0x003e;
                case 99: goto L_0x0034;
                case 105: goto L_0x002a;
                case 117: goto L_0x001f;
                case 118: goto L_0x0014;
                case 3314158: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0048
        L_0x000a:
            java.lang.String r0 = "lang"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0048
            r3 = 3
            goto L_0x0049
        L_0x0014:
            java.lang.String r0 = "v"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0048
            r3 = 5
            goto L_0x0049
        L_0x001f:
            java.lang.String r0 = "u"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0048
            r3 = 4
            goto L_0x0049
        L_0x002a:
            java.lang.String r0 = "i"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0048
            r3 = 2
            goto L_0x0049
        L_0x0034:
            java.lang.String r0 = "c"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0048
            r3 = 1
            goto L_0x0049
        L_0x003e:
            java.lang.String r0 = "b"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0048
            r3 = 0
            goto L_0x0049
        L_0x0048:
            r3 = -1
        L_0x0049:
            switch(r3) {
                case 0: goto L_0x004d;
                case 1: goto L_0x004d;
                case 2: goto L_0x004d;
                case 3: goto L_0x004d;
                case 4: goto L_0x004d;
                case 5: goto L_0x004d;
                default: goto L_0x004c;
            }
        L_0x004c:
            return r2
        L_0x004d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.isSupportedTag(java.lang.String):boolean");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void applySpansForTag(java.lang.String r7, com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag r8, android.text.SpannableStringBuilder r9, java.util.List<com.google.android.exoplayer2.text.webvtt.WebvttCssStyle> r10, java.util.List<com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StyleMatch> r11) {
        /*
            int r0 = r8.position
            int r1 = r9.length()
            java.lang.String r2 = r8.name
            int r3 = r2.hashCode()
            r4 = 0
            r5 = 2
            r6 = 1
            switch(r3) {
                case 0: goto L_0x0051;
                case 98: goto L_0x0047;
                case 99: goto L_0x003d;
                case 105: goto L_0x0033;
                case 117: goto L_0x0028;
                case 118: goto L_0x001d;
                case 3314158: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x005b
        L_0x0013:
            java.lang.String r3 = "lang"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 4
            goto L_0x005c
        L_0x001d:
            java.lang.String r3 = "v"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 5
            goto L_0x005c
        L_0x0028:
            java.lang.String r3 = "u"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 2
            goto L_0x005c
        L_0x0033:
            java.lang.String r3 = "i"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 1
            goto L_0x005c
        L_0x003d:
            java.lang.String r3 = "c"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 3
            goto L_0x005c
        L_0x0047:
            java.lang.String r3 = "b"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 0
            goto L_0x005c
        L_0x0051:
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            r2 = 6
            goto L_0x005c
        L_0x005b:
            r2 = -1
        L_0x005c:
            r3 = 33
            switch(r2) {
                case 0: goto L_0x0074;
                case 1: goto L_0x006b;
                case 2: goto L_0x0062;
                case 3: goto L_0x007c;
                case 4: goto L_0x007c;
                case 5: goto L_0x007c;
                case 6: goto L_0x007c;
                default: goto L_0x0061;
            }
        L_0x0061:
            return
        L_0x0062:
            android.text.style.UnderlineSpan r2 = new android.text.style.UnderlineSpan
            r2.<init>()
            r9.setSpan(r2, r0, r1, r3)
            goto L_0x007c
        L_0x006b:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r5)
            r9.setSpan(r2, r0, r1, r3)
            goto L_0x007c
        L_0x0074:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r6)
            r9.setSpan(r2, r0, r1, r3)
        L_0x007c:
            r11.clear()
            getApplicableStyles(r10, r7, r8, r11)
            int r7 = r11.size()
        L_0x0086:
            if (r4 >= r7) goto L_0x0096
            java.lang.Object r8 = r11.get(r4)
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StyleMatch r8 = (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StyleMatch) r8
            com.google.android.exoplayer2.text.webvtt.WebvttCssStyle r8 = r8.style
            applyStyleToText(r9, r8, r0, r1)
            int r4 = r4 + 1
            goto L_0x0086
        L_0x0096:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.applySpansForTag(java.lang.String, com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StartTag, android.text.SpannableStringBuilder, java.util.List, java.util.List):void");
    }

    private static void applyStyleToText(SpannableStringBuilder spannableStringBuilder, WebvttCssStyle webvttCssStyle, int i, int i2) {
        if (webvttCssStyle != null) {
            if (webvttCssStyle.getStyle() != -1) {
                spannableStringBuilder.setSpan(new StyleSpan(webvttCssStyle.getStyle()), i, i2, 33);
            }
            if (webvttCssStyle.isLinethrough()) {
                spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
            }
            if (webvttCssStyle.isUnderline()) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
            }
            if (webvttCssStyle.hasFontColor()) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(webvttCssStyle.getFontColor()), i, i2, 33);
            }
            if (webvttCssStyle.hasBackgroundColor()) {
                spannableStringBuilder.setSpan(new BackgroundColorSpan(webvttCssStyle.getBackgroundColor()), i, i2, 33);
            }
            if (webvttCssStyle.getFontFamily() != null) {
                spannableStringBuilder.setSpan(new TypefaceSpan(webvttCssStyle.getFontFamily()), i, i2, 33);
            }
            if (webvttCssStyle.getTextAlign() != null) {
                spannableStringBuilder.setSpan(new Standard(webvttCssStyle.getTextAlign()), i, i2, 33);
            }
            switch (webvttCssStyle.getFontSizeUnit()) {
                case 1:
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) webvttCssStyle.getFontSize(), true), i, i2, 33);
                    break;
                case 2:
                    spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize()), i, i2, 33);
                    break;
                case 3:
                    spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize() / 100.0f), i, i2, 33);
                    break;
            }
        }
    }

    private static String getTagName(String str) {
        String trim = str.trim();
        if (trim.isEmpty()) {
            return null;
        }
        return Util.splitAtFirst(trim, "[ \\.]")[0];
    }

    private static void getApplicableStyles(List<WebvttCssStyle> list, String str, StartTag startTag, List<StyleMatch> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WebvttCssStyle webvttCssStyle = (WebvttCssStyle) list.get(i);
            int specificityScore = webvttCssStyle.getSpecificityScore(str, startTag.name, startTag.classes, startTag.voice);
            if (specificityScore > 0) {
                list2.add(new StyleMatch(specificityScore, webvttCssStyle));
            }
        }
        Collections.sort(list2);
    }
}
