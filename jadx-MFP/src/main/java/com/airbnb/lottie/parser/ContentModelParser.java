package com.airbnb.lottie.parser;

class ContentModelParser {
    private ContentModelParser() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b1, code lost:
        if (r2.equals("gs") != false) goto L_0x00dd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0041 A[SYNTHETIC] */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.ContentModel parse(android.util.JsonReader r9, com.airbnb.lottie.LottieComposition r10) throws java.io.IOException {
        /*
            r9.beginObject()
            r0 = 2
            r1 = 2
        L_0x0005:
            boolean r2 = r9.hasNext()
            r3 = 0
            r4 = 1
            r5 = -1
            r6 = 0
            if (r2 == 0) goto L_0x0046
            java.lang.String r2 = r9.nextName()
            int r7 = r2.hashCode()
            r8 = 100
            if (r7 == r8) goto L_0x002a
            r8 = 3717(0xe85, float:5.209E-42)
            if (r7 == r8) goto L_0x0020
            goto L_0x0034
        L_0x0020:
            java.lang.String r7 = "ty"
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x0034
            r2 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r7 = "d"
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x0034
            r2 = 1
            goto L_0x0035
        L_0x0034:
            r2 = -1
        L_0x0035:
            switch(r2) {
                case 0: goto L_0x0041;
                case 1: goto L_0x003c;
                default: goto L_0x0038;
            }
        L_0x0038:
            r9.skipValue()
            goto L_0x0005
        L_0x003c:
            int r1 = r9.nextInt()
            goto L_0x0005
        L_0x0041:
            java.lang.String r2 = r9.nextString()
            goto L_0x0047
        L_0x0046:
            r2 = r6
        L_0x0047:
            if (r2 != 0) goto L_0x004a
            return r6
        L_0x004a:
            int r7 = r2.hashCode()
            switch(r7) {
                case 3239: goto L_0x00d2;
                case 3270: goto L_0x00c8;
                case 3295: goto L_0x00be;
                case 3307: goto L_0x00b4;
                case 3308: goto L_0x00ab;
                case 3488: goto L_0x00a0;
                case 3633: goto L_0x0095;
                case 3646: goto L_0x008a;
                case 3669: goto L_0x0080;
                case 3679: goto L_0x0075;
                case 3681: goto L_0x006a;
                case 3705: goto L_0x005e;
                case 3710: goto L_0x0053;
                default: goto L_0x0051;
            }
        L_0x0051:
            goto L_0x00dc
        L_0x0053:
            java.lang.String r0 = "tr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 5
            goto L_0x00dd
        L_0x005e:
            java.lang.String r0 = "tm"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 9
            goto L_0x00dd
        L_0x006a:
            java.lang.String r0 = "st"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 1
            goto L_0x00dd
        L_0x0075:
            java.lang.String r0 = "sr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 10
            goto L_0x00dd
        L_0x0080:
            java.lang.String r0 = "sh"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 6
            goto L_0x00dd
        L_0x008a:
            java.lang.String r0 = "rp"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 12
            goto L_0x00dd
        L_0x0095:
            java.lang.String r0 = "rc"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 8
            goto L_0x00dd
        L_0x00a0:
            java.lang.String r0 = "mm"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 11
            goto L_0x00dd
        L_0x00ab:
            java.lang.String r3 = "gs"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00dc
            goto L_0x00dd
        L_0x00b4:
            java.lang.String r0 = "gr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 0
            goto L_0x00dd
        L_0x00be:
            java.lang.String r0 = "gf"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 4
            goto L_0x00dd
        L_0x00c8:
            java.lang.String r0 = "fl"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 3
            goto L_0x00dd
        L_0x00d2:
            java.lang.String r0 = "el"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00dc
            r0 = 7
            goto L_0x00dd
        L_0x00dc:
            r0 = -1
        L_0x00dd:
            switch(r0) {
                case 0: goto L_0x0138;
                case 1: goto L_0x0133;
                case 2: goto L_0x012e;
                case 3: goto L_0x0129;
                case 4: goto L_0x0124;
                case 5: goto L_0x011f;
                case 6: goto L_0x011a;
                case 7: goto L_0x0115;
                case 8: goto L_0x0110;
                case 9: goto L_0x010b;
                case 10: goto L_0x0106;
                case 11: goto L_0x00fc;
                case 12: goto L_0x00f7;
                default: goto L_0x00e0;
            }
        L_0x00e0:
            java.lang.String r10 = "LOTTIE"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown shape type "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r10, r0)
            goto L_0x013c
        L_0x00f7:
            com.airbnb.lottie.model.content.Repeater r6 = com.airbnb.lottie.parser.RepeaterParser.parse(r9, r10)
            goto L_0x013c
        L_0x00fc:
            com.airbnb.lottie.model.content.MergePaths r6 = com.airbnb.lottie.parser.MergePathsParser.parse(r9)
            java.lang.String r0 = "Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove()."
            r10.addWarning(r0)
            goto L_0x013c
        L_0x0106:
            com.airbnb.lottie.model.content.PolystarShape r6 = com.airbnb.lottie.parser.PolystarShapeParser.parse(r9, r10)
            goto L_0x013c
        L_0x010b:
            com.airbnb.lottie.model.content.ShapeTrimPath r6 = com.airbnb.lottie.parser.ShapeTrimPathParser.parse(r9, r10)
            goto L_0x013c
        L_0x0110:
            com.airbnb.lottie.model.content.RectangleShape r6 = com.airbnb.lottie.parser.RectangleShapeParser.parse(r9, r10)
            goto L_0x013c
        L_0x0115:
            com.airbnb.lottie.model.content.CircleShape r6 = com.airbnb.lottie.parser.CircleShapeParser.parse(r9, r10, r1)
            goto L_0x013c
        L_0x011a:
            com.airbnb.lottie.model.content.ShapePath r6 = com.airbnb.lottie.parser.ShapePathParser.parse(r9, r10)
            goto L_0x013c
        L_0x011f:
            com.airbnb.lottie.model.animatable.AnimatableTransform r6 = com.airbnb.lottie.parser.AnimatableTransformParser.parse(r9, r10)
            goto L_0x013c
        L_0x0124:
            com.airbnb.lottie.model.content.GradientFill r6 = com.airbnb.lottie.parser.GradientFillParser.parse(r9, r10)
            goto L_0x013c
        L_0x0129:
            com.airbnb.lottie.model.content.ShapeFill r6 = com.airbnb.lottie.parser.ShapeFillParser.parse(r9, r10)
            goto L_0x013c
        L_0x012e:
            com.airbnb.lottie.model.content.GradientStroke r6 = com.airbnb.lottie.parser.GradientStrokeParser.parse(r9, r10)
            goto L_0x013c
        L_0x0133:
            com.airbnb.lottie.model.content.ShapeStroke r6 = com.airbnb.lottie.parser.ShapeStrokeParser.parse(r9, r10)
            goto L_0x013c
        L_0x0138:
            com.airbnb.lottie.model.content.ShapeGroup r6 = com.airbnb.lottie.parser.ShapeGroupParser.parse(r9, r10)
        L_0x013c:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0146
            r9.skipValue()
            goto L_0x013c
        L_0x0146:
            r9.endObject()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ContentModelParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ContentModel");
    }
}
