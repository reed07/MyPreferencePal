package com.brightcove.player.captioning;

public abstract class BrightcoveCaptionStyle {

    public interface Builder {
        Builder backgroundColor(int i);

        Builder backgroundOpacity(int i);

        BrightcoveCaptionStyle build();

        Builder edgeColor(int i);

        Builder edgeType(int i);

        Builder fontSize(String str);

        Builder foregroundColor(int i);

        Builder foregroundOpacity(int i);

        Builder preset(int i);

        Builder typeface(String str);

        Builder windowColor(int i);

        Builder windowOpacity(int i);
    }

    public abstract int backgroundColor();

    public abstract int backgroundOpacity();

    public abstract int edgeColor();

    public abstract int edgeType();

    public abstract String fontSize();

    public abstract int foregroundColor();

    public abstract int foregroundOpacity();

    public abstract int preset();

    public abstract String typeface();

    public void validate() {
    }

    public abstract int windowColor();

    public abstract int windowOpacity();

    public static BrightcoveCaptionStyle createCaptionStyle(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return builder().preset(-1).fontSize(str).typeface(str2).foregroundColor(i).foregroundOpacity(i2).edgeType(i3).edgeColor(i4).backgroundColor(i5).backgroundOpacity(i6).windowColor(i7).windowOpacity(i8).build();
    }

    public static BrightcoveCaptionStyle createCaptionStyleFromPreset(String str, int i) {
        int i2;
        int i3 = -256;
        switch (i) {
            case 1:
                i3 = -16777216;
                i2 = -1;
                break;
            case 2:
                i2 = -16777216;
                break;
            case 3:
                i2 = -16776961;
                break;
            default:
                i3 = -1;
                i2 = -16777216;
                break;
        }
        return builder().preset(i).fontSize(str).typeface("sans-serif").foregroundColor(i3).foregroundOpacity(-1).edgeType(0).edgeColor(-16777216).backgroundColor(i2).backgroundOpacity(-1).windowColor(0).windowOpacity(0).build();
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.brightcove.player.captioning.BrightcoveCaptionStyle updateStyleByPreferenceKey(com.brightcove.player.captioning.BrightcoveCaptionStyle r13, java.lang.String r14, java.lang.Object r15) {
        /*
            java.lang.String r0 = r13.typeface()
            java.lang.String r1 = r13.fontSize()
            int r2 = r13.foregroundColor()
            int r3 = r13.foregroundOpacity()
            int r4 = r13.edgeType()
            int r5 = r13.edgeColor()
            int r6 = r13.backgroundColor()
            int r7 = r13.backgroundOpacity()
            int r8 = r13.windowColor()
            int r9 = r13.windowOpacity()
            int r10 = r14.hashCode()
            r11 = 0
            switch(r10) {
                case -1236731529: goto L_0x008d;
                case -1009808097: goto L_0x0082;
                case -762839331: goto L_0x0078;
                case -339562066: goto L_0x006e;
                case -280051019: goto L_0x0064;
                case 75674378: goto L_0x005a;
                case 332759354: goto L_0x0050;
                case 1205119556: goto L_0x0046;
                case 1424864313: goto L_0x003c;
                case 1786850802: goto L_0x0032;
                default: goto L_0x0030;
            }
        L_0x0030:
            goto L_0x0098
        L_0x0032:
            java.lang.String r10 = "captioning_foreground_opacity"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 3
            goto L_0x0099
        L_0x003c:
            java.lang.String r10 = "captioning_edge_type"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 4
            goto L_0x0099
        L_0x0046:
            java.lang.String r10 = "captioning_edge_color"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 5
            goto L_0x0099
        L_0x0050:
            java.lang.String r10 = "captioning_typeface"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 1
            goto L_0x0099
        L_0x005a:
            java.lang.String r10 = "captioning_foreground_color"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 2
            goto L_0x0099
        L_0x0064:
            java.lang.String r10 = "captioning_background_color"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 6
            goto L_0x0099
        L_0x006e:
            java.lang.String r10 = "captioning_font_size"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 0
            goto L_0x0099
        L_0x0078:
            java.lang.String r10 = "captioning_background_opacity"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 7
            goto L_0x0099
        L_0x0082:
            java.lang.String r10 = "captioning_window_opacity"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 9
            goto L_0x0099
        L_0x008d:
            java.lang.String r10 = "captioning_window_color"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0098
            r10 = 8
            goto L_0x0099
        L_0x0098:
            r10 = -1
        L_0x0099:
            switch(r10) {
                case 0: goto L_0x00ef;
                case 1: goto L_0x00eb;
                case 2: goto L_0x00e4;
                case 3: goto L_0x00dd;
                case 4: goto L_0x00d6;
                case 5: goto L_0x00cf;
                case 6: goto L_0x00c8;
                case 7: goto L_0x00c1;
                case 8: goto L_0x00ba;
                case 9: goto L_0x00b3;
                default: goto L_0x009c;
            }
        L_0x009c:
            java.lang.String r15 = "BrightcoveCaptionStyle"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = "Unexpected preference: "
            r10.append(r12)
            r10.append(r14)
            java.lang.String r14 = r10.toString()
            android.util.Log.v(r15, r14)
            goto L_0x00f2
        L_0x00b3:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r9 = r15.intValue()
            goto L_0x00f2
        L_0x00ba:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r8 = r15.intValue()
            goto L_0x00f2
        L_0x00c1:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r7 = r15.intValue()
            goto L_0x00f2
        L_0x00c8:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r6 = r15.intValue()
            goto L_0x00f2
        L_0x00cf:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r5 = r15.intValue()
            goto L_0x00f2
        L_0x00d6:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r4 = r15.intValue()
            goto L_0x00f2
        L_0x00dd:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r3 = r15.intValue()
            goto L_0x00f2
        L_0x00e4:
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r2 = r15.intValue()
            goto L_0x00f2
        L_0x00eb:
            r0 = r15
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x00f2
        L_0x00ef:
            r1 = r15
            java.lang.String r1 = (java.lang.String) r1
        L_0x00f2:
            if (r6 != 0) goto L_0x00f5
            r7 = 0
        L_0x00f5:
            if (r8 != 0) goto L_0x00f8
            r9 = 0
        L_0x00f8:
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r14 = builder()
            int r13 = r13.preset()
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r14.preset(r13)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.fontSize(r1)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.typeface(r0)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.foregroundColor(r2)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.foregroundOpacity(r3)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.edgeType(r4)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.edgeColor(r5)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.backgroundColor(r6)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.backgroundOpacity(r7)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.windowColor(r8)
            com.brightcove.player.captioning.BrightcoveCaptionStyle$Builder r13 = r13.windowOpacity(r9)
            com.brightcove.player.captioning.BrightcoveCaptionStyle r13 = r13.build()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.captioning.BrightcoveCaptionStyle.updateStyleByPreferenceKey(com.brightcove.player.captioning.BrightcoveCaptionStyle, java.lang.String, java.lang.Object):com.brightcove.player.captioning.BrightcoveCaptionStyle");
    }

    public static Builder builder() {
        return new Builder();
    }
}
