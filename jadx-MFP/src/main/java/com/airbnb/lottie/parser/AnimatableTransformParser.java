package com.airbnb.lottie.parser;

public class AnimatableTransformParser {
    private AnimatableTransformParser() {
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.airbnb.lottie.model.animatable.AnimatableTransform parse(android.util.JsonReader r14, com.airbnb.lottie.LottieComposition r15) throws java.io.IOException {
        /*
            android.util.JsonToken r0 = r14.peek()
            android.util.JsonToken r1 = android.util.JsonToken.BEGIN_OBJECT
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x000c
            r0 = 1
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            if (r0 == 0) goto L_0x0012
            r14.beginObject()
        L_0x0012:
            r1 = 0
            r4 = r1
            r5 = r4
            r8 = r5
            r10 = r8
            r12 = r10
            r13 = r12
        L_0x0019:
            boolean r6 = r14.hasNext()
            if (r6 == 0) goto L_0x00d1
            java.lang.String r6 = r14.nextName()
            r7 = -1
            int r9 = r6.hashCode()
            switch(r9) {
                case 97: goto L_0x0072;
                case 111: goto L_0x0068;
                case 112: goto L_0x005e;
                case 114: goto L_0x0054;
                case 115: goto L_0x004a;
                case 3242: goto L_0x0040;
                case 3656: goto L_0x0036;
                case 3676: goto L_0x002c;
                default: goto L_0x002b;
            }
        L_0x002b:
            goto L_0x007c
        L_0x002c:
            java.lang.String r9 = "so"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 6
            goto L_0x007d
        L_0x0036:
            java.lang.String r9 = "rz"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 3
            goto L_0x007d
        L_0x0040:
            java.lang.String r9 = "eo"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 7
            goto L_0x007d
        L_0x004a:
            java.lang.String r9 = "s"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 2
            goto L_0x007d
        L_0x0054:
            java.lang.String r9 = "r"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 4
            goto L_0x007d
        L_0x005e:
            java.lang.String r9 = "p"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 1
            goto L_0x007d
        L_0x0068:
            java.lang.String r9 = "o"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 5
            goto L_0x007d
        L_0x0072:
            java.lang.String r9 = "a"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x007c
            r6 = 0
            goto L_0x007d
        L_0x007c:
            r6 = -1
        L_0x007d:
            switch(r6) {
                case 0: goto L_0x00ae;
                case 1: goto L_0x00a7;
                case 2: goto L_0x00a1;
                case 3: goto L_0x0095;
                case 4: goto L_0x009a;
                case 5: goto L_0x0090;
                case 6: goto L_0x008a;
                case 7: goto L_0x0084;
                default: goto L_0x0080;
            }
        L_0x0080:
            r14.skipValue()
            goto L_0x0019
        L_0x0084:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r14, r15, r3)
            r13 = r6
            goto L_0x0019
        L_0x008a:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r14, r15, r3)
            r12 = r6
            goto L_0x0019
        L_0x0090:
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r5 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r14, r15)
            goto L_0x0019
        L_0x0095:
            java.lang.String r6 = "Lottie doesn't support 3D layers."
            r15.addWarning(r6)
        L_0x009a:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r14, r15, r3)
            r10 = r6
            goto L_0x0019
        L_0x00a1:
            com.airbnb.lottie.model.animatable.AnimatableScaleValue r4 = com.airbnb.lottie.parser.AnimatableValueParser.parseScale(r14, r15)
            goto L_0x0019
        L_0x00a7:
            com.airbnb.lottie.model.animatable.AnimatableValue r6 = com.airbnb.lottie.parser.AnimatablePathValueParser.parseSplitPath(r14, r15)
            r8 = r6
            goto L_0x0019
        L_0x00ae:
            r14.beginObject()
        L_0x00b1:
            boolean r6 = r14.hasNext()
            if (r6 == 0) goto L_0x00cc
            java.lang.String r6 = r14.nextName()
            java.lang.String r7 = "k"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c8
            com.airbnb.lottie.model.animatable.AnimatablePathValue r1 = com.airbnb.lottie.parser.AnimatablePathValueParser.parse(r14, r15)
            goto L_0x00b1
        L_0x00c8:
            r14.skipValue()
            goto L_0x00b1
        L_0x00cc:
            r14.endObject()
            goto L_0x0019
        L_0x00d1:
            if (r0 == 0) goto L_0x00d6
            r14.endObject()
        L_0x00d6:
            if (r1 != 0) goto L_0x00e6
            java.lang.String r14 = "LOTTIE"
            java.lang.String r15 = "Layer has no transform property. You may be using an unsupported layer type such as a camera."
            android.util.Log.w(r14, r15)
            com.airbnb.lottie.model.animatable.AnimatablePathValue r1 = new com.airbnb.lottie.model.animatable.AnimatablePathValue
            r1.<init>()
            r7 = r1
            goto L_0x00e7
        L_0x00e6:
            r7 = r1
        L_0x00e7:
            if (r4 != 0) goto L_0x00f7
            com.airbnb.lottie.model.animatable.AnimatableScaleValue r4 = new com.airbnb.lottie.model.animatable.AnimatableScaleValue
            com.airbnb.lottie.value.ScaleXY r14 = new com.airbnb.lottie.value.ScaleXY
            r15 = 1065353216(0x3f800000, float:1.0)
            r14.<init>(r15, r15)
            r4.<init>(r14)
            r9 = r4
            goto L_0x00f8
        L_0x00f7:
            r9 = r4
        L_0x00f8:
            if (r5 != 0) goto L_0x0101
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r5 = new com.airbnb.lottie.model.animatable.AnimatableIntegerValue
            r5.<init>()
            r11 = r5
            goto L_0x0102
        L_0x0101:
            r11 = r5
        L_0x0102:
            com.airbnb.lottie.model.animatable.AnimatableTransform r14 = new com.airbnb.lottie.model.animatable.AnimatableTransform
            r6 = r14
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.AnimatableTransformParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.animatable.AnimatableTransform");
    }
}
