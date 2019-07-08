package com.airbnb.lottie.parser;

import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.model.layer.Layer.LayerType;
import com.airbnb.lottie.model.layer.Layer.MatteType;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Collections;
import java.util.List;

public class LayerParser {
    private LayerParser() {
    }

    public static Layer parse(LottieComposition lottieComposition) {
        LottieComposition lottieComposition2 = lottieComposition;
        Rect bounds = lottieComposition.getBounds();
        LayerType layerType = LayerType.PreComp;
        List emptyList = Collections.emptyList();
        AnimatableTransform animatableTransform = r5;
        AnimatableTransform animatableTransform2 = new AnimatableTransform();
        Layer layer = new Layer(Collections.emptyList(), lottieComposition2, "__container", -1, layerType, -1, null, emptyList, animatableTransform, 0, 0, 0, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, bounds.width(), bounds.height(), null, null, Collections.emptyList(), MatteType.None, null);
        return layer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0262  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.airbnb.lottie.model.layer.Layer parse(android.util.JsonReader r36, com.airbnb.lottie.LottieComposition r37) throws java.io.IOException {
        /*
            r7 = r37
            java.lang.String r0 = "UNSET"
            com.airbnb.lottie.model.layer.Layer$MatteType r1 = com.airbnb.lottie.model.layer.Layer.MatteType.None
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r36.beginObject()
            r2 = 0
            r11 = 0
            r3 = 0
            r4 = 0
            r12 = -1
            r27 = r1
            r14 = r2
            r16 = r14
            r28 = r16
            r29 = r28
            r30 = r29
            r31 = r30
            r17 = r4
            r24 = r12
            r1 = 0
            r15 = 1065353216(0x3f800000, float:1.0)
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r26 = 0
            r12 = r0
            r13 = r31
            r0 = 0
        L_0x003e:
            boolean r2 = r36.hasNext()
            if (r2 == 0) goto L_0x031d
            java.lang.String r2 = r36.nextName()
            int r4 = r2.hashCode()
            r5 = 1
            switch(r4) {
                case -995424086: goto L_0x013f;
                case -903568142: goto L_0x0134;
                case 104: goto L_0x0129;
                case 116: goto L_0x011e;
                case 119: goto L_0x0113;
                case 3177: goto L_0x0108;
                case 3233: goto L_0x00fd;
                case 3367: goto L_0x00f2;
                case 3432: goto L_0x00e7;
                case 3519: goto L_0x00dc;
                case 3553: goto L_0x00d0;
                case 3664: goto L_0x00c5;
                case 3669: goto L_0x00ba;
                case 3679: goto L_0x00ae;
                case 3681: goto L_0x00a2;
                case 3684: goto L_0x0097;
                case 3705: goto L_0x008b;
                case 3712: goto L_0x007f;
                case 3717: goto L_0x0074;
                case 104415: goto L_0x0069;
                case 108390670: goto L_0x005e;
                case 1441620890: goto L_0x0052;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x0149
        L_0x0052:
            java.lang.String r4 = "masksProperties"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 10
            goto L_0x014a
        L_0x005e:
            java.lang.String r4 = "refId"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 2
            goto L_0x014a
        L_0x0069:
            java.lang.String r4 = "ind"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 1
            goto L_0x014a
        L_0x0074:
            java.lang.String r4 = "ty"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 3
            goto L_0x014a
        L_0x007f:
            java.lang.String r4 = "tt"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 9
            goto L_0x014a
        L_0x008b:
            java.lang.String r4 = "tm"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 20
            goto L_0x014a
        L_0x0097:
            java.lang.String r4 = "sw"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 5
            goto L_0x014a
        L_0x00a2:
            java.lang.String r4 = "st"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 15
            goto L_0x014a
        L_0x00ae:
            java.lang.String r4 = "sr"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 14
            goto L_0x014a
        L_0x00ba:
            java.lang.String r4 = "sh"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 6
            goto L_0x014a
        L_0x00c5:
            java.lang.String r4 = "sc"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 7
            goto L_0x014a
        L_0x00d0:
            java.lang.String r4 = "op"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 19
            goto L_0x014a
        L_0x00dc:
            java.lang.String r4 = "nm"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 0
            goto L_0x014a
        L_0x00e7:
            java.lang.String r4 = "ks"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 8
            goto L_0x014a
        L_0x00f2:
            java.lang.String r4 = "ip"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 18
            goto L_0x014a
        L_0x00fd:
            java.lang.String r4 = "ef"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 13
            goto L_0x014a
        L_0x0108:
            java.lang.String r4 = "cl"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 21
            goto L_0x014a
        L_0x0113:
            java.lang.String r4 = "w"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 16
            goto L_0x014a
        L_0x011e:
            java.lang.String r4 = "t"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 12
            goto L_0x014a
        L_0x0129:
            java.lang.String r4 = "h"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 17
            goto L_0x014a
        L_0x0134:
            java.lang.String r4 = "shapes"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 11
            goto L_0x014a
        L_0x013f:
            java.lang.String r4 = "parent"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0149
            r2 = 4
            goto L_0x014a
        L_0x0149:
            r2 = -1
        L_0x014a:
            switch(r2) {
                case 0: goto L_0x0314;
                case 1: goto L_0x030a;
                case 2: goto L_0x0303;
                case 3: goto L_0x02eb;
                case 4: goto L_0x02e1;
                case 5: goto L_0x02d0;
                case 6: goto L_0x02bf;
                case 7: goto L_0x02b4;
                case 8: goto L_0x02ac;
                case 9: goto L_0x029e;
                case 10: goto L_0x0286;
                case 11: goto L_0x026c;
                case 12: goto L_0x020d;
                case 13: goto L_0x01af;
                case 14: goto L_0x01a6;
                case 15: goto L_0x019b;
                case 16: goto L_0x0189;
                case 17: goto L_0x0177;
                case 18: goto L_0x016e;
                case 19: goto L_0x0165;
                case 20: goto L_0x015d;
                case 21: goto L_0x0154;
                default: goto L_0x014d;
            }
        L_0x014d:
            r2 = r36
            r36.skipValue()
            goto L_0x031a
        L_0x0154:
            java.lang.String r2 = r36.nextString()
            r13 = r2
            r2 = r36
            goto L_0x031a
        L_0x015d:
            r2 = r36
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r31 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r2, r7, r3)
            goto L_0x031a
        L_0x0165:
            r2 = r36
            double r4 = r36.nextDouble()
            float r1 = (float) r4
            goto L_0x031a
        L_0x016e:
            r2 = r36
            double r4 = r36.nextDouble()
            float r0 = (float) r4
            goto L_0x031a
        L_0x0177:
            r2 = r36
            int r4 = r36.nextInt()
            float r4 = (float) r4
            float r5 = com.airbnb.lottie.utils.Utils.dpScale()
            float r4 = r4 * r5
            int r4 = (int) r4
            r23 = r4
            goto L_0x031a
        L_0x0189:
            r2 = r36
            int r4 = r36.nextInt()
            float r4 = (float) r4
            float r5 = com.airbnb.lottie.utils.Utils.dpScale()
            float r4 = r4 * r5
            int r4 = (int) r4
            r22 = r4
            goto L_0x031a
        L_0x019b:
            r2 = r36
            double r4 = r36.nextDouble()
            float r4 = (float) r4
            r26 = r4
            goto L_0x031a
        L_0x01a6:
            r2 = r36
            double r4 = r36.nextDouble()
            float r15 = (float) r4
            goto L_0x031a
        L_0x01af:
            r2 = r36
            r36.beginArray()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x01b9:
            boolean r5 = r36.hasNext()
            if (r5 == 0) goto L_0x01f4
            r36.beginObject()
        L_0x01c2:
            boolean r5 = r36.hasNext()
            if (r5 == 0) goto L_0x01ef
            java.lang.String r5 = r36.nextName()
            int r3 = r5.hashCode()
            r6 = 3519(0xdbf, float:4.931E-42)
            if (r3 == r6) goto L_0x01d5
            goto L_0x01df
        L_0x01d5:
            java.lang.String r3 = "nm"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x01df
            r3 = 0
            goto L_0x01e0
        L_0x01df:
            r3 = -1
        L_0x01e0:
            if (r3 == 0) goto L_0x01e6
            r36.skipValue()
            goto L_0x01ed
        L_0x01e6:
            java.lang.String r3 = r36.nextString()
            r4.add(r3)
        L_0x01ed:
            r3 = 0
            goto L_0x01c2
        L_0x01ef:
            r36.endObject()
            r3 = 0
            goto L_0x01b9
        L_0x01f4:
            r36.endArray()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r7.addWarning(r3)
            goto L_0x031a
        L_0x020d:
            r2 = r36
            r36.beginObject()
        L_0x0212:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x0267
            java.lang.String r3 = r36.nextName()
            int r4 = r3.hashCode()
            r6 = 97
            if (r4 == r6) goto L_0x0233
            r6 = 100
            if (r4 == r6) goto L_0x0229
            goto L_0x023d
        L_0x0229:
            java.lang.String r4 = "d"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x023d
            r3 = 0
            goto L_0x023e
        L_0x0233:
            java.lang.String r4 = "a"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x023d
            r3 = 1
            goto L_0x023e
        L_0x023d:
            r3 = -1
        L_0x023e:
            switch(r3) {
                case 0: goto L_0x0262;
                case 1: goto L_0x0245;
                default: goto L_0x0241;
            }
        L_0x0241:
            r36.skipValue()
            goto L_0x0212
        L_0x0245:
            r36.beginArray()
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x0254
            com.airbnb.lottie.model.animatable.AnimatableTextProperties r3 = com.airbnb.lottie.parser.AnimatableTextPropertiesParser.parse(r36, r37)
            r30 = r3
        L_0x0254:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x025e
            r36.skipValue()
            goto L_0x0254
        L_0x025e:
            r36.endArray()
            goto L_0x0212
        L_0x0262:
            com.airbnb.lottie.model.animatable.AnimatableTextFrame r29 = com.airbnb.lottie.parser.AnimatableValueParser.parseDocumentData(r36, r37)
            goto L_0x0212
        L_0x0267:
            r36.endObject()
            goto L_0x031a
        L_0x026c:
            r2 = r36
            r36.beginArray()
        L_0x0271:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x0281
            com.airbnb.lottie.model.content.ContentModel r3 = com.airbnb.lottie.parser.ContentModelParser.parse(r36, r37)
            if (r3 == 0) goto L_0x0271
            r8.add(r3)
            goto L_0x0271
        L_0x0281:
            r36.endArray()
            goto L_0x031a
        L_0x0286:
            r2 = r36
            r36.beginArray()
        L_0x028b:
            boolean r3 = r36.hasNext()
            if (r3 == 0) goto L_0x0299
            com.airbnb.lottie.model.content.Mask r3 = com.airbnb.lottie.parser.MaskParser.parse(r36, r37)
            r10.add(r3)
            goto L_0x028b
        L_0x0299:
            r36.endArray()
            goto L_0x031a
        L_0x029e:
            r2 = r36
            com.airbnb.lottie.model.layer.Layer$MatteType[] r3 = com.airbnb.lottie.model.layer.Layer.MatteType.values()
            int r4 = r36.nextInt()
            r27 = r3[r4]
            goto L_0x031a
        L_0x02ac:
            r2 = r36
            com.airbnb.lottie.model.animatable.AnimatableTransform r28 = com.airbnb.lottie.parser.AnimatableTransformParser.parse(r36, r37)
            goto L_0x031a
        L_0x02b4:
            r2 = r36
            java.lang.String r3 = r36.nextString()
            int r21 = android.graphics.Color.parseColor(r3)
            goto L_0x031a
        L_0x02bf:
            r2 = r36
            int r3 = r36.nextInt()
            float r3 = (float) r3
            float r4 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r4
            int r3 = (int) r3
            r20 = r3
            goto L_0x031a
        L_0x02d0:
            r2 = r36
            int r3 = r36.nextInt()
            float r3 = (float) r3
            float r4 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r4
            int r3 = (int) r3
            r19 = r3
            goto L_0x031a
        L_0x02e1:
            r2 = r36
            int r3 = r36.nextInt()
            long r3 = (long) r3
            r24 = r3
            goto L_0x031a
        L_0x02eb:
            r2 = r36
            int r3 = r36.nextInt()
            com.airbnb.lottie.model.layer.Layer$LayerType r4 = com.airbnb.lottie.model.layer.Layer.LayerType.Unknown
            int r4 = r4.ordinal()
            if (r3 >= r4) goto L_0x0300
            com.airbnb.lottie.model.layer.Layer$LayerType[] r4 = com.airbnb.lottie.model.layer.Layer.LayerType.values()
            r14 = r4[r3]
            goto L_0x031a
        L_0x0300:
            com.airbnb.lottie.model.layer.Layer$LayerType r14 = com.airbnb.lottie.model.layer.Layer.LayerType.Unknown
            goto L_0x031a
        L_0x0303:
            r2 = r36
            java.lang.String r16 = r36.nextString()
            goto L_0x031a
        L_0x030a:
            r2 = r36
            int r3 = r36.nextInt()
            long r3 = (long) r3
            r17 = r3
            goto L_0x031a
        L_0x0314:
            r2 = r36
            java.lang.String r12 = r36.nextString()
        L_0x031a:
            r3 = 0
            goto L_0x003e
        L_0x031d:
            r2 = r36
            r36.endObject()
            float r32 = r0 / r15
            float r33 = r1 / r15
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            int r0 = (r32 > r11 ? 1 : (r32 == r11 ? 0 : -1))
            if (r0 <= 0) goto L_0x0351
            com.airbnb.lottie.value.Keyframe r5 = new com.airbnb.lottie.value.Keyframe
            java.lang.Float r2 = java.lang.Float.valueOf(r11)
            java.lang.Float r3 = java.lang.Float.valueOf(r11)
            r4 = 0
            r34 = 0
            java.lang.Float r35 = java.lang.Float.valueOf(r32)
            r0 = r5
            r1 = r37
            r9 = r5
            r5 = r34
            r11 = r6
            r6 = r35
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r11.add(r9)
            r0 = 0
            goto L_0x0353
        L_0x0351:
            r11 = r6
            r0 = 0
        L_0x0353:
            int r1 = (r33 > r0 ? 1 : (r33 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0358
            goto L_0x035e
        L_0x0358:
            float r0 = r37.getEndFrame()
            r33 = r0
        L_0x035e:
            com.airbnb.lottie.value.Keyframe r9 = new com.airbnb.lottie.value.Keyframe
            r0 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r2 = java.lang.Float.valueOf(r0)
            java.lang.Float r3 = java.lang.Float.valueOf(r0)
            r4 = 0
            java.lang.Float r6 = java.lang.Float.valueOf(r33)
            r0 = r9
            r1 = r37
            r5 = r32
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r11.add(r9)
            com.airbnb.lottie.value.Keyframe r9 = new com.airbnb.lottie.value.Keyframe
            r0 = 0
            java.lang.Float r2 = java.lang.Float.valueOf(r0)
            java.lang.Float r3 = java.lang.Float.valueOf(r0)
            r0 = 2139095039(0x7f7fffff, float:3.4028235E38)
            java.lang.Float r6 = java.lang.Float.valueOf(r0)
            r0 = r9
            r5 = r33
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r11.add(r9)
            java.lang.String r0 = ".ai"
            boolean r0 = r12.endsWith(r0)
            if (r0 != 0) goto L_0x03a5
            java.lang.String r0 = "ai"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x03aa
        L_0x03a5:
            java.lang.String r0 = "Convert your Illustrator layers to shape layers."
            r7.addWarning(r0)
        L_0x03aa:
            com.airbnb.lottie.model.layer.Layer r32 = new com.airbnb.lottie.model.layer.Layer
            r0 = r32
            r1 = r8
            r2 = r37
            r3 = r12
            r4 = r17
            r6 = r14
            r7 = r24
            r9 = r16
            r24 = r11
            r11 = r28
            r12 = r19
            r13 = r20
            r14 = r21
            r16 = r26
            r17 = r22
            r18 = r23
            r19 = r29
            r20 = r30
            r21 = r24
            r22 = r27
            r23 = r31
            r0.<init>(r1, r2, r3, r4, r6, r7, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            return r32
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.LayerParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.layer.Layer");
    }
}
