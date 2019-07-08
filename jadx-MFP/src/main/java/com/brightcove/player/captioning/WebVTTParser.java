package com.brightcove.player.captioning;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebVTTParser {
    private static final String CENTER = "center";
    private static final Pattern CUE_HEADER = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern CUE_SETTING = Pattern.compile("\\s+(\\S+):(\\S+)");
    private static final String END = "end";
    private static final String LEFT = "left";
    private static final Pattern LINE_CUE_SETTING_VALUE = Pattern.compile("([\\S&&[^,]]+),?(\\S+)?");
    private static final Pattern POSITION_CUE_SETTING_VALUE = Pattern.compile("([\\S&&[^,]]+),?(\\S+)?");
    private static final String RIGHT = "right";
    private static final String START = "start";
    private static final String TAG = "WebVTTParser";
    private static final Pattern WEBVTT_TIMESTAMP = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{3}");

    /* JADX WARNING: Code restructure failed: missing block: B:108:0x027e, code lost:
        if (r5.equals("line-left") != false) goto L_0x0282;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010a, code lost:
        if (r12.equals("start") != false) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01d7, code lost:
        if (r5.equals("start") == false) goto L_0x01ee;
     */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x00d8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x00d8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0202  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.brightcove.player.model.WebVTTDocument parse(java.io.InputStream r14, java.lang.String r15) throws java.io.IOException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.io.BufferedReader r2 = new java.io.BufferedReader
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r3.<init>(r14, r15)
            r2.<init>(r3)
            java.lang.String r15 = r2.readLine()
            if (r15 == 0) goto L_0x02cc
            java.lang.String r3 = "﻿"
            boolean r3 = r15.startsWith(r3)
            r4 = 1
            if (r3 == 0) goto L_0x002e
            java.lang.String r3 = TAG
            java.lang.String r5 = "BOMs in UTF-8 files are not necessary or recommended.  Please remove."
            android.util.Log.w(r3, r5)
            java.lang.String r15 = r15.substring(r4)
        L_0x002e:
            java.lang.String r3 = "WEBVTT"
            boolean r3 = r15.startsWith(r3)
            if (r3 == 0) goto L_0x02b5
        L_0x0036:
            java.lang.String r15 = r2.readLine()
            if (r15 == 0) goto L_0x02ad
            boolean r15 = r15.isEmpty()
            if (r15 == 0) goto L_0x0036
        L_0x0042:
            java.lang.String r15 = r2.readLine()
            if (r15 == 0) goto L_0x02a1
            java.util.regex.Pattern r3 = CUE_HEADER
            java.util.regex.Matcher r15 = r3.matcher(r15)
            java.lang.String r3 = ""
            boolean r5 = r15.find()
            if (r5 != 0) goto L_0x0057
            goto L_0x0042
        L_0x0057:
            java.lang.String r5 = r15.group(r4)
            long r5 = parseTimestamp(r5)
            r7 = 2
            java.lang.String r8 = r15.group(r7)
            long r8 = parseTimestamp(r8)
            r10 = 3
            java.lang.String r15 = r15.group(r10)
            java.util.Map r15 = parseSettings(r15)
        L_0x0071:
            java.lang.String r11 = r2.readLine()
            if (r11 == 0) goto L_0x00c8
            boolean r12 = r11.isEmpty()
            if (r12 != 0) goto L_0x00c8
            int r12 = r3.length()
            if (r12 <= 0) goto L_0x00b4
            java.lang.String r12 = "<br>"
            boolean r12 = r3.endsWith(r12)
            if (r12 != 0) goto L_0x00b4
            java.lang.String r12 = "<br >"
            boolean r12 = r3.endsWith(r12)
            if (r12 != 0) goto L_0x00b4
            java.lang.String r12 = "<br/>"
            boolean r12 = r3.endsWith(r12)
            if (r12 != 0) goto L_0x00b4
            java.lang.String r12 = "<br />"
            boolean r12 = r3.endsWith(r12)
            if (r12 != 0) goto L_0x00b4
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r3)
            java.lang.String r3 = "<br/>"
            r12.append(r3)
            java.lang.String r3 = r12.toString()
        L_0x00b4:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r3)
            java.lang.String r3 = r11.trim()
            r12.append(r3)
            java.lang.String r3 = r12.toString()
            goto L_0x0071
        L_0x00c8:
            com.brightcove.player.captioning.BrightcoveClosedCaption r11 = new com.brightcove.player.captioning.BrightcoveClosedCaption
            int r6 = (int) r5
            int r5 = (int) r8
            r11.<init>(r6, r5, r3)
            r3 = 0
            java.util.Set r15 = r15.entrySet()
            java.util.Iterator r15 = r15.iterator()
        L_0x00d8:
            boolean r5 = r15.hasNext()
            if (r5 == 0) goto L_0x029c
            java.lang.Object r5 = r15.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r6 = r5.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r8 = "align"
            boolean r6 = r6.equals(r8)
            r8 = 0
            r9 = -1
            if (r6 == 0) goto L_0x0173
            com.brightcove.player.model.StyledElement$TextAlign r6 = com.brightcove.player.model.StyledElement.TextAlign.UNDEFINED
            java.lang.Object r12 = r5.getValue()
            java.lang.String r12 = (java.lang.String) r12
            int r13 = r12.hashCode()
            switch(r13) {
                case -1364013995: goto L_0x0135;
                case -1074341483: goto L_0x012b;
                case 100571: goto L_0x0121;
                case 3317767: goto L_0x0117;
                case 108511772: goto L_0x010d;
                case 109757538: goto L_0x0104;
                default: goto L_0x0103;
            }
        L_0x0103:
            goto L_0x013f
        L_0x0104:
            java.lang.String r13 = "start"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x013f
            goto L_0x0140
        L_0x010d:
            java.lang.String r8 = "right"
            boolean r8 = r12.equals(r8)
            if (r8 == 0) goto L_0x013f
            r8 = 4
            goto L_0x0140
        L_0x0117:
            java.lang.String r8 = "left"
            boolean r8 = r12.equals(r8)
            if (r8 == 0) goto L_0x013f
            r8 = 1
            goto L_0x0140
        L_0x0121:
            java.lang.String r8 = "end"
            boolean r8 = r12.equals(r8)
            if (r8 == 0) goto L_0x013f
            r8 = 5
            goto L_0x0140
        L_0x012b:
            java.lang.String r8 = "middle"
            boolean r8 = r12.equals(r8)
            if (r8 == 0) goto L_0x013f
            r8 = 2
            goto L_0x0140
        L_0x0135:
            java.lang.String r8 = "center"
            boolean r8 = r12.equals(r8)
            if (r8 == 0) goto L_0x013f
            r8 = 3
            goto L_0x0140
        L_0x013f:
            r8 = -1
        L_0x0140:
            switch(r8) {
                case 0: goto L_0x016c;
                case 1: goto L_0x0169;
                case 2: goto L_0x0166;
                case 3: goto L_0x0166;
                case 4: goto L_0x0163;
                case 5: goto L_0x0160;
                default: goto L_0x0143;
            }
        L_0x0143:
            java.lang.String r8 = TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = "unexpected align value: "
            r9.append(r12)
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            android.util.Log.w(r8, r5)
            goto L_0x016e
        L_0x0160:
            com.brightcove.player.model.StyledElement$TextAlign r6 = com.brightcove.player.model.StyledElement.TextAlign.END
            goto L_0x016e
        L_0x0163:
            com.brightcove.player.model.StyledElement$TextAlign r6 = com.brightcove.player.model.StyledElement.TextAlign.RIGHT
            goto L_0x016e
        L_0x0166:
            com.brightcove.player.model.StyledElement$TextAlign r6 = com.brightcove.player.model.StyledElement.TextAlign.CENTER
            goto L_0x016e
        L_0x0169:
            com.brightcove.player.model.StyledElement$TextAlign r6 = com.brightcove.player.model.StyledElement.TextAlign.LEFT
            goto L_0x016e
        L_0x016c:
            com.brightcove.player.model.StyledElement$TextAlign r6 = com.brightcove.player.model.StyledElement.TextAlign.START
        L_0x016e:
            r11.setTextAlign(r6)
            goto L_0x00d8
        L_0x0173:
            java.lang.Object r6 = r5.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r12 = "line"
            boolean r6 = r6.equals(r12)
            r12 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            if (r6 == 0) goto L_0x0209
            if (r3 != 0) goto L_0x01a6
            com.brightcove.player.model.Region r3 = new com.brightcove.player.model.Region
            r3.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            int r13 = r1.size()
            r6.append(r13)
            java.lang.String r13 = ""
            r6.append(r13)
            java.lang.String r6 = r6.toString()
            r1.put(r6, r3)
            r11.setRegion(r6)
        L_0x01a6:
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            com.brightcove.player.captioning.LineCueSettingValue r5 = parseLineCueSettingValue(r5)
            com.brightcove.player.model.LengthVtt r6 = new com.brightcove.player.model.LengthVtt
            java.lang.String r13 = r5.offset
            r6.<init>(r13)
            r3.setOriginY(r6)
            java.lang.String r6 = r5.alignment
            if (r6 == 0) goto L_0x00d8
            java.lang.String r5 = r5.alignment
            int r6 = r5.hashCode()
            if (r6 == r12) goto L_0x01e4
            r12 = 100571(0x188db, float:1.4093E-40)
            if (r6 == r12) goto L_0x01da
            r12 = 109757538(0x68ac462, float:5.219839E-35)
            if (r6 == r12) goto L_0x01d1
            goto L_0x01ee
        L_0x01d1:
            java.lang.String r6 = "start"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01ee
            goto L_0x01ef
        L_0x01da:
            java.lang.String r6 = "end"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01ee
            r8 = 2
            goto L_0x01ef
        L_0x01e4:
            java.lang.String r6 = "center"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01ee
            r8 = 1
            goto L_0x01ef
        L_0x01ee:
            r8 = -1
        L_0x01ef:
            switch(r8) {
                case 0: goto L_0x0202;
                case 1: goto L_0x01fb;
                case 2: goto L_0x01f4;
                default: goto L_0x01f2;
            }
        L_0x01f2:
            goto L_0x00d8
        L_0x01f4:
            com.brightcove.player.model.Region$DisplayAlign r5 = com.brightcove.player.model.Region.DisplayAlign.AFTER
            r3.setDisplayAlign(r5)
            goto L_0x00d8
        L_0x01fb:
            com.brightcove.player.model.Region$DisplayAlign r5 = com.brightcove.player.model.Region.DisplayAlign.CENTER
            r3.setDisplayAlign(r5)
            goto L_0x00d8
        L_0x0202:
            com.brightcove.player.model.Region$DisplayAlign r5 = com.brightcove.player.model.Region.DisplayAlign.BEFORE
            r3.setDisplayAlign(r5)
            goto L_0x00d8
        L_0x0209:
            java.lang.Object r6 = r5.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r13 = "position"
            boolean r6 = r6.equals(r13)
            if (r6 == 0) goto L_0x00d8
            if (r3 != 0) goto L_0x0239
            com.brightcove.player.model.Region r3 = new com.brightcove.player.model.Region
            r3.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            int r13 = r1.size()
            r6.append(r13)
            java.lang.String r13 = ""
            r6.append(r13)
            java.lang.String r6 = r6.toString()
            r1.put(r6, r3)
            r11.setRegion(r6)
        L_0x0239:
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            com.brightcove.player.captioning.PositionCueSettingValue r5 = parsePositionCueSettingValue(r5)
            com.brightcove.player.model.Length r6 = new com.brightcove.player.model.Length
            java.lang.String r13 = r5.offset
            r6.<init>(r13)
            r3.setOriginX(r6)
            java.lang.String r6 = r5.alignment
            if (r6 == 0) goto L_0x00d8
            java.lang.String r5 = r5.alignment
            int r6 = r5.hashCode()
            r13 = -1842484672(0xffffffff922dea40, float:-5.487791E-28)
            if (r6 == r13) goto L_0x0278
            if (r6 == r12) goto L_0x026e
            r8 = -1276788989(0xffffffffb3e5bf03, float:-1.0698388E-7)
            if (r6 == r8) goto L_0x0264
            goto L_0x0281
        L_0x0264:
            java.lang.String r6 = "line-right"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0281
            r8 = 2
            goto L_0x0282
        L_0x026e:
            java.lang.String r6 = "center"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0281
            r8 = 1
            goto L_0x0282
        L_0x0278:
            java.lang.String r6 = "line-left"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0281
            goto L_0x0282
        L_0x0281:
            r8 = -1
        L_0x0282:
            switch(r8) {
                case 0: goto L_0x0295;
                case 1: goto L_0x028e;
                case 2: goto L_0x0287;
                default: goto L_0x0285;
            }
        L_0x0285:
            goto L_0x00d8
        L_0x0287:
            com.brightcove.player.model.Region$DisplayAlign r5 = com.brightcove.player.model.Region.DisplayAlign.AFTER
            r3.setDisplayAlign(r5)
            goto L_0x00d8
        L_0x028e:
            com.brightcove.player.model.Region$DisplayAlign r5 = com.brightcove.player.model.Region.DisplayAlign.CENTER
            r3.setDisplayAlign(r5)
            goto L_0x00d8
        L_0x0295:
            com.brightcove.player.model.Region$DisplayAlign r5 = com.brightcove.player.model.Region.DisplayAlign.BEFORE
            r3.setDisplayAlign(r5)
            goto L_0x00d8
        L_0x029c:
            r0.add(r11)
            goto L_0x0042
        L_0x02a1:
            r2.close()
            r14.close()
            com.brightcove.player.model.WebVTTDocument r14 = new com.brightcove.player.model.WebVTTDocument
            r14.<init>(r1, r0)
            return r14
        L_0x02ad:
            java.io.IOException r14 = new java.io.IOException
            java.lang.String r15 = "Expected an empty line after webvtt header"
            r14.<init>(r15)
            throw r14
        L_0x02b5:
            java.io.IOException r14 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Expected WEBVTT. Got "
            r0.append(r1)
            r0.append(r15)
            java.lang.String r15 = r0.toString()
            r14.<init>(r15)
            throw r14
        L_0x02cc:
            java.io.IOException r14 = new java.io.IOException
            java.lang.String r15 = "Expected WEBVTT. Got null"
            r14.<init>(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.captioning.WebVTTParser.parse(java.io.InputStream, java.lang.String):com.brightcove.player.model.WebVTTDocument");
    }

    private static Map<String, String> parseSettings(String str) {
        HashMap hashMap = new HashMap();
        Matcher matcher = CUE_SETTING.matcher(str);
        while (matcher.find()) {
            hashMap.put(matcher.group(1), matcher.group(2));
        }
        return hashMap;
    }

    private static long parseTimestamp(String str) throws NumberFormatException {
        if (WEBVTT_TIMESTAMP.matcher(str).find()) {
            String[] split = str.split("\\.", 2);
            long j = 0;
            for (String parseLong : split[0].split(":")) {
                j = (j * 60) + Long.parseLong(parseLong);
            }
            return (j * 1000) + Long.parseLong(split[1]);
        }
        throw new NumberFormatException("has invalid format");
    }

    private static LineCueSettingValue parseLineCueSettingValue(String str) {
        Matcher matcher = LINE_CUE_SETTING_VALUE.matcher(str);
        if (matcher.find()) {
            return new LineCueSettingValue(matcher.group(1), matcher.group(2));
        }
        return null;
    }

    private static PositionCueSettingValue parsePositionCueSettingValue(String str) {
        Matcher matcher = POSITION_CUE_SETTING_VALUE.matcher(str);
        if (matcher.find()) {
            return new PositionCueSettingValue(matcher.group(1), matcher.group(2));
        }
        return null;
    }
}
