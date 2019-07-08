package com.airbnb.lottie.parser;

class ShapeFillParser {
    private ShapeFillParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.ShapeFill parse(android.util.JsonReader r11, com.airbnb.lottie.LottieComposition r12) throws java.io.IOException {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            r4 = r2
            r7 = r4
            r8 = r7
            r2 = 1
            r5 = 0
        L_0x0008:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0084
            java.lang.String r3 = r11.nextName()
            r6 = -1
            int r9 = r3.hashCode()
            r10 = -396065730(0xffffffffe864843e, float:-4.316556E24)
            if (r9 == r10) goto L_0x0055
            r10 = 99
            if (r9 == r10) goto L_0x004b
            r10 = 111(0x6f, float:1.56E-43)
            if (r9 == r10) goto L_0x0041
            r10 = 114(0x72, float:1.6E-43)
            if (r9 == r10) goto L_0x0037
            r10 = 3519(0xdbf, float:4.931E-42)
            if (r9 == r10) goto L_0x002d
            goto L_0x005f
        L_0x002d:
            java.lang.String r9 = "nm"
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x005f
            r3 = 0
            goto L_0x0060
        L_0x0037:
            java.lang.String r9 = "r"
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x005f
            r3 = 4
            goto L_0x0060
        L_0x0041:
            java.lang.String r9 = "o"
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x005f
            r3 = 2
            goto L_0x0060
        L_0x004b:
            java.lang.String r9 = "c"
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x005f
            r3 = 1
            goto L_0x0060
        L_0x0055:
            java.lang.String r9 = "fillEnabled"
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x005f
            r3 = 3
            goto L_0x0060
        L_0x005f:
            r3 = -1
        L_0x0060:
            switch(r3) {
                case 0: goto L_0x007e;
                case 1: goto L_0x0078;
                case 2: goto L_0x0072;
                case 3: goto L_0x006c;
                case 4: goto L_0x0067;
                default: goto L_0x0063;
            }
        L_0x0063:
            r11.skipValue()
            goto L_0x0008
        L_0x0067:
            int r2 = r11.nextInt()
            goto L_0x0008
        L_0x006c:
            boolean r3 = r11.nextBoolean()
            r5 = r3
            goto L_0x0008
        L_0x0072:
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r3 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r11, r12)
            r8 = r3
            goto L_0x0008
        L_0x0078:
            com.airbnb.lottie.model.animatable.AnimatableColorValue r3 = com.airbnb.lottie.parser.AnimatableValueParser.parseColor(r11, r12)
            r7 = r3
            goto L_0x0008
        L_0x007e:
            java.lang.String r3 = r11.nextString()
            r4 = r3
            goto L_0x0008
        L_0x0084:
            if (r2 != r1) goto L_0x0089
            android.graphics.Path$FillType r11 = android.graphics.Path.FillType.WINDING
            goto L_0x008b
        L_0x0089:
            android.graphics.Path$FillType r11 = android.graphics.Path.FillType.EVEN_ODD
        L_0x008b:
            r6 = r11
            com.airbnb.lottie.model.content.ShapeFill r11 = new com.airbnb.lottie.model.content.ShapeFill
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ShapeFillParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ShapeFill");
    }
}
