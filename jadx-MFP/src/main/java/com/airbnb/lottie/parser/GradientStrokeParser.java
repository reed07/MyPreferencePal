package com.airbnb.lottie.parser;

class GradientStrokeParser {
    private GradientStrokeParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.GradientStroke parse(android.util.JsonReader r19, com.airbnb.lottie.LottieComposition r20) throws java.io.IOException {
        /*
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r1 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 0
        L_0x0011:
            boolean r13 = r19.hasNext()
            if (r13 == 0) goto L_0x0205
            java.lang.String r13 = r19.nextName()
            int r14 = r13.hashCode()
            r16 = -1
            switch(r14) {
                case 100: goto L_0x008d;
                case 101: goto L_0x0083;
                case 103: goto L_0x0079;
                case 111: goto L_0x006f;
                case 115: goto L_0x0065;
                case 116: goto L_0x005b;
                case 119: goto L_0x0051;
                case 3447: goto L_0x0047;
                case 3454: goto L_0x003c;
                case 3487: goto L_0x0031;
                case 3519: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            goto L_0x0098
        L_0x0026:
            java.lang.String r14 = "nm"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 0
            goto L_0x0099
        L_0x0031:
            java.lang.String r14 = "ml"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 9
            goto L_0x0099
        L_0x003c:
            java.lang.String r14 = "lj"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 8
            goto L_0x0099
        L_0x0047:
            java.lang.String r14 = "lc"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 7
            goto L_0x0099
        L_0x0051:
            java.lang.String r14 = "w"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 6
            goto L_0x0099
        L_0x005b:
            java.lang.String r14 = "t"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 3
            goto L_0x0099
        L_0x0065:
            java.lang.String r14 = "s"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 4
            goto L_0x0099
        L_0x006f:
            java.lang.String r14 = "o"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 2
            goto L_0x0099
        L_0x0079:
            java.lang.String r14 = "g"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 1
            goto L_0x0099
        L_0x0083:
            java.lang.String r14 = "e"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 5
            goto L_0x0099
        L_0x008d:
            java.lang.String r14 = "d"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0098
            r13 = 10
            goto L_0x0099
        L_0x0098:
            r13 = -1
        L_0x0099:
            switch(r13) {
                case 0: goto L_0x01fb;
                case 1: goto L_0x01a1;
                case 2: goto L_0x0197;
                case 3: goto L_0x0184;
                case 4: goto L_0x017a;
                case 5: goto L_0x0170;
                case 6: goto L_0x0166;
                case 7: goto L_0x0154;
                case 8: goto L_0x0142;
                case 9: goto L_0x0137;
                case 10: goto L_0x00a5;
                default: goto L_0x009c;
            }
        L_0x009c:
            r0 = r19
            r13 = r20
            r19.skipValue()
            goto L_0x0011
        L_0x00a5:
            r19.beginArray()
        L_0x00a8:
            boolean r13 = r19.hasNext()
            if (r13 == 0) goto L_0x011b
            r19.beginObject()
            r13 = 0
            r14 = 0
        L_0x00b3:
            boolean r17 = r19.hasNext()
            if (r17 == 0) goto L_0x00f6
            java.lang.String r15 = r19.nextName()
            int r0 = r15.hashCode()
            r18 = r12
            r12 = 110(0x6e, float:1.54E-43)
            if (r0 == r12) goto L_0x00d6
            r12 = 118(0x76, float:1.65E-43)
            if (r0 == r12) goto L_0x00cc
            goto L_0x00e0
        L_0x00cc:
            java.lang.String r0 = "v"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 1
            goto L_0x00e1
        L_0x00d6:
            java.lang.String r0 = "n"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00e0
            r0 = 0
            goto L_0x00e1
        L_0x00e0:
            r0 = -1
        L_0x00e1:
            switch(r0) {
                case 0: goto L_0x00ee;
                case 1: goto L_0x00e8;
                default: goto L_0x00e4;
            }
        L_0x00e4:
            r19.skipValue()
            goto L_0x00f3
        L_0x00e8:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r0 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r19, r20)
            r14 = r0
            goto L_0x00f3
        L_0x00ee:
            java.lang.String r0 = r19.nextString()
            r13 = r0
        L_0x00f3:
            r12 = r18
            goto L_0x00b3
        L_0x00f6:
            r18 = r12
            r19.endObject()
            java.lang.String r0 = "o"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x0105
            r12 = r14
            goto L_0x00a8
        L_0x0105:
            java.lang.String r0 = "d"
            boolean r0 = r13.equals(r0)
            if (r0 != 0) goto L_0x0115
            java.lang.String r0 = "g"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x0118
        L_0x0115:
            r11.add(r14)
        L_0x0118:
            r12 = r18
            goto L_0x00a8
        L_0x011b:
            r18 = r12
            r19.endArray()
            int r0 = r11.size()
            r13 = 1
            if (r0 != r13) goto L_0x012f
            r0 = 0
            java.lang.Object r0 = r11.get(r0)
            r11.add(r0)
        L_0x012f:
            r0 = r19
            r13 = r20
            r12 = r18
            goto L_0x0011
        L_0x0137:
            double r13 = r19.nextDouble()
            float r10 = (float) r13
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0142:
            r13 = 1
            com.airbnb.lottie.model.content.ShapeStroke$LineJoinType[] r0 = com.airbnb.lottie.model.content.ShapeStroke.LineJoinType.values()
            int r9 = r19.nextInt()
            int r9 = r9 - r13
            r9 = r0[r9]
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0154:
            r13 = 1
            com.airbnb.lottie.model.content.ShapeStroke$LineCapType[] r0 = com.airbnb.lottie.model.content.ShapeStroke.LineCapType.values()
            int r8 = r19.nextInt()
            int r8 = r8 - r13
            r8 = r0[r8]
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0166:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r7 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r19, r20)
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0170:
            com.airbnb.lottie.model.animatable.AnimatablePointValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parsePoint(r19, r20)
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x017a:
            com.airbnb.lottie.model.animatable.AnimatablePointValue r5 = com.airbnb.lottie.parser.AnimatableValueParser.parsePoint(r19, r20)
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0184:
            r13 = 1
            int r0 = r19.nextInt()
            if (r0 != r13) goto L_0x018e
            com.airbnb.lottie.model.content.GradientType r0 = com.airbnb.lottie.model.content.GradientType.Linear
            goto L_0x0190
        L_0x018e:
            com.airbnb.lottie.model.content.GradientType r0 = com.airbnb.lottie.model.content.GradientType.Radial
        L_0x0190:
            r2 = r0
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x0197:
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r4 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r19, r20)
            r0 = r19
            r13 = r20
            goto L_0x0011
        L_0x01a1:
            r0 = 0
            r13 = 1
            r19.beginObject()
            r14 = -1
        L_0x01a7:
            boolean r15 = r19.hasNext()
            if (r15 == 0) goto L_0x01f2
            java.lang.String r15 = r19.nextName()
            int r0 = r15.hashCode()
            r13 = 107(0x6b, float:1.5E-43)
            if (r0 == r13) goto L_0x01c8
            r13 = 112(0x70, float:1.57E-43)
            if (r0 == r13) goto L_0x01be
            goto L_0x01d2
        L_0x01be:
            java.lang.String r0 = "p"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x01d2
            r0 = 0
            goto L_0x01d3
        L_0x01c8:
            java.lang.String r0 = "k"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x01d2
            r0 = 1
            goto L_0x01d3
        L_0x01d2:
            r0 = -1
        L_0x01d3:
            switch(r0) {
                case 0: goto L_0x01e7;
                case 1: goto L_0x01de;
                default: goto L_0x01d6;
            }
        L_0x01d6:
            r0 = r19
            r13 = r20
            r19.skipValue()
            goto L_0x01ef
        L_0x01de:
            r0 = r19
            r13 = r20
            com.airbnb.lottie.model.animatable.AnimatableGradientColorValue r3 = com.airbnb.lottie.parser.AnimatableValueParser.parseGradientColor(r0, r13, r14)
            goto L_0x01ef
        L_0x01e7:
            r0 = r19
            r13 = r20
            int r14 = r19.nextInt()
        L_0x01ef:
            r0 = 0
            r13 = 1
            goto L_0x01a7
        L_0x01f2:
            r0 = r19
            r13 = r20
            r19.endObject()
            goto L_0x0011
        L_0x01fb:
            r0 = r19
            r13 = r20
            java.lang.String r1 = r19.nextString()
            goto L_0x0011
        L_0x0205:
            com.airbnb.lottie.model.content.GradientStroke r13 = new com.airbnb.lottie.model.content.GradientStroke
            r0 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.GradientStrokeParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.GradientStroke");
    }
}
