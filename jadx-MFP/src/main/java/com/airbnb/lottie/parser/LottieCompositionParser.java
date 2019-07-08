package com.airbnb.lottie.parser;

import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SparseArrayCompat;
import android.util.JsonReader;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.model.layer.Layer.LayerType;
import com.brightcove.player.event.AbstractEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LottieCompositionParser {
    private LottieCompositionParser() {
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.airbnb.lottie.LottieComposition parse(android.util.JsonReader r26) throws java.io.IOException {
        /*
            r0 = r26
            float r1 = com.airbnb.lottie.utils.Utils.dpScale()
            android.support.v4.util.LongSparseArray r8 = new android.support.v4.util.LongSparseArray
            r8.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.util.HashMap r12 = new java.util.HashMap
            r12.<init>()
            android.support.v4.util.SparseArrayCompat r11 = new android.support.v4.util.SparseArrayCompat
            r11.<init>()
            com.airbnb.lottie.LottieComposition r13 = new com.airbnb.lottie.LottieComposition
            r13.<init>()
            r26.beginObject()
            r2 = 0
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r14 = 0
        L_0x0032:
            boolean r15 = r26.hasNext()
            if (r15 == 0) goto L_0x014a
            java.lang.String r15 = r26.nextName()
            r16 = -1
            int r17 = r15.hashCode()
            r18 = 2
            r19 = 1
            switch(r17) {
                case -1408207997: goto L_0x00a9;
                case -1109732030: goto L_0x009f;
                case 104: goto L_0x0095;
                case 118: goto L_0x008a;
                case 119: goto L_0x007f;
                case 3276: goto L_0x0075;
                case 3367: goto L_0x006b;
                case 3553: goto L_0x0061;
                case 94623709: goto L_0x0056;
                case 97615364: goto L_0x004b;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x00b3
        L_0x004b:
            java.lang.String r3 = "fonts"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 8
            goto L_0x00b4
        L_0x0056:
            java.lang.String r3 = "chars"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 9
            goto L_0x00b4
        L_0x0061:
            java.lang.String r3 = "op"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 3
            goto L_0x00b4
        L_0x006b:
            java.lang.String r3 = "ip"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 2
            goto L_0x00b4
        L_0x0075:
            java.lang.String r3 = "fr"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 4
            goto L_0x00b4
        L_0x007f:
            java.lang.String r3 = "w"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 0
            goto L_0x00b4
        L_0x008a:
            java.lang.String r3 = "v"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 5
            goto L_0x00b4
        L_0x0095:
            java.lang.String r3 = "h"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 1
            goto L_0x00b4
        L_0x009f:
            java.lang.String r3 = "layers"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 6
            goto L_0x00b4
        L_0x00a9:
            java.lang.String r3 = "assets"
            boolean r3 = r15.equals(r3)
            if (r3 == 0) goto L_0x00b3
            r3 = 7
            goto L_0x00b4
        L_0x00b3:
            r3 = -1
        L_0x00b4:
            switch(r3) {
                case 0: goto L_0x013e;
                case 1: goto L_0x0135;
                case 2: goto L_0x012c;
                case 3: goto L_0x011e;
                case 4: goto L_0x0115;
                case 5: goto L_0x00df;
                case 6: goto L_0x00d7;
                case 7: goto L_0x00cf;
                case 8: goto L_0x00c7;
                case 9: goto L_0x00bf;
                default: goto L_0x00b7;
            }
        L_0x00b7:
            r16 = r11
            r15 = r12
            r26.skipValue()
            goto L_0x0145
        L_0x00bf:
            parseChars(r0, r13, r11)
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x00c7:
            parseFonts(r0, r12)
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x00cf:
            parseAssets(r0, r13, r9, r10)
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x00d7:
            parseLayers(r0, r13, r7, r8)
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x00df:
            java.lang.String r3 = r26.nextString()
            java.lang.String r15 = "\\."
            java.lang.String[] r3 = r3.split(r15)
            r15 = 0
            r16 = r3[r15]
            int r20 = java.lang.Integer.parseInt(r16)
            r15 = r3[r19]
            int r21 = java.lang.Integer.parseInt(r15)
            r3 = r3[r18]
            int r22 = java.lang.Integer.parseInt(r3)
            r23 = 4
            r24 = 4
            r25 = 0
            boolean r3 = com.airbnb.lottie.utils.Utils.isAtLeastVersion(r20, r21, r22, r23, r24, r25)
            if (r3 != 0) goto L_0x0111
            java.lang.String r3 = "Lottie only supports bodymovin >= 4.4.0"
            r13.addWarning(r3)
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x0111:
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x0115:
            double r14 = r26.nextDouble()
            float r14 = (float) r14
            r16 = r11
            r15 = r12
            goto L_0x0145
        L_0x011e:
            r16 = r11
            r15 = r12
            double r11 = r26.nextDouble()
            float r3 = (float) r11
            r6 = 1008981770(0x3c23d70a, float:0.01)
            float r6 = r3 - r6
            goto L_0x0145
        L_0x012c:
            r16 = r11
            r15 = r12
            double r11 = r26.nextDouble()
            float r5 = (float) r11
            goto L_0x0145
        L_0x0135:
            r16 = r11
            r15 = r12
            int r3 = r26.nextInt()
            r4 = r3
            goto L_0x0145
        L_0x013e:
            r16 = r11
            r15 = r12
            int r2 = r26.nextInt()
        L_0x0145:
            r12 = r15
            r11 = r16
            goto L_0x0032
        L_0x014a:
            r16 = r11
            r15 = r12
            r26.endObject()
            float r0 = (float) r2
            float r0 = r0 * r1
            int r0 = (int) r0
            float r2 = (float) r4
            float r2 = r2 * r1
            int r1 = (int) r2
            android.graphics.Rect r3 = new android.graphics.Rect
            r2 = 0
            r3.<init>(r2, r2, r0, r1)
            r2 = r13
            r4 = r5
            r5 = r6
            r6 = r14
            r2.init(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.LottieCompositionParser.parse(android.util.JsonReader):com.airbnb.lottie.LottieComposition");
    }

    private static void parseLayers(JsonReader jsonReader, LottieComposition lottieComposition, List<Layer> list, LongSparseArray<Layer> longSparseArray) throws IOException {
        jsonReader.beginArray();
        int i = 0;
        while (jsonReader.hasNext()) {
            Layer parse = LayerParser.parse(jsonReader, lottieComposition);
            if (parse.getLayerType() == LayerType.Image) {
                i++;
            }
            list.add(parse);
            longSparseArray.put(parse.getId(), parse);
            if (i > 4) {
                StringBuilder sb = new StringBuilder();
                sb.append("You have ");
                sb.append(i);
                sb.append(" images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
                L.warn(sb.toString());
            }
        }
        jsonReader.endArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseAssets(android.util.JsonReader r12, com.airbnb.lottie.LottieComposition r13, java.util.Map<java.lang.String, java.util.List<com.airbnb.lottie.model.layer.Layer>> r14, java.util.Map<java.lang.String, com.airbnb.lottie.LottieImageAsset> r15) throws java.io.IOException {
        /*
            r12.beginArray()
        L_0x0003:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x00e2
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.support.v4.util.LongSparseArray r1 = new android.support.v4.util.LongSparseArray
            r1.<init>()
            r12.beginObject()
            r2 = 0
            r3 = 0
            r7 = r3
            r8 = r7
            r9 = r8
            r5 = 0
            r6 = 0
        L_0x001d:
            boolean r3 = r12.hasNext()
            if (r3 == 0) goto L_0x00c9
            java.lang.String r3 = r12.nextName()
            r4 = -1
            int r10 = r3.hashCode()
            r11 = -1109732030(0xffffffffbddad542, float:-0.10685207)
            if (r10 == r11) goto L_0x007a
            r11 = 104(0x68, float:1.46E-43)
            if (r10 == r11) goto L_0x0070
            r11 = 112(0x70, float:1.57E-43)
            if (r10 == r11) goto L_0x0066
            r11 = 117(0x75, float:1.64E-43)
            if (r10 == r11) goto L_0x005b
            r11 = 119(0x77, float:1.67E-43)
            if (r10 == r11) goto L_0x0050
            r11 = 3355(0xd1b, float:4.701E-42)
            if (r10 == r11) goto L_0x0046
            goto L_0x0084
        L_0x0046:
            java.lang.String r10 = "id"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x0084
            r3 = 0
            goto L_0x0085
        L_0x0050:
            java.lang.String r10 = "w"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x0084
            r3 = 2
            goto L_0x0085
        L_0x005b:
            java.lang.String r10 = "u"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x0084
            r3 = 5
            goto L_0x0085
        L_0x0066:
            java.lang.String r10 = "p"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x0084
            r3 = 4
            goto L_0x0085
        L_0x0070:
            java.lang.String r10 = "h"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x0084
            r3 = 3
            goto L_0x0085
        L_0x007a:
            java.lang.String r10 = "layers"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x0084
            r3 = 1
            goto L_0x0085
        L_0x0084:
            r3 = -1
        L_0x0085:
            switch(r3) {
                case 0: goto L_0x00c2;
                case 1: goto L_0x00a5;
                case 2: goto L_0x009e;
                case 3: goto L_0x0098;
                case 4: goto L_0x0092;
                case 5: goto L_0x008c;
                default: goto L_0x0088;
            }
        L_0x0088:
            r12.skipValue()
            goto L_0x001d
        L_0x008c:
            java.lang.String r3 = r12.nextString()
            r9 = r3
            goto L_0x001d
        L_0x0092:
            java.lang.String r3 = r12.nextString()
            r8 = r3
            goto L_0x001d
        L_0x0098:
            int r3 = r12.nextInt()
            r6 = r3
            goto L_0x001d
        L_0x009e:
            int r3 = r12.nextInt()
            r5 = r3
            goto L_0x001d
        L_0x00a5:
            r12.beginArray()
        L_0x00a8:
            boolean r3 = r12.hasNext()
            if (r3 == 0) goto L_0x00bd
            com.airbnb.lottie.model.layer.Layer r3 = com.airbnb.lottie.parser.LayerParser.parse(r12, r13)
            long r10 = r3.getId()
            r1.put(r10, r3)
            r0.add(r3)
            goto L_0x00a8
        L_0x00bd:
            r12.endArray()
            goto L_0x001d
        L_0x00c2:
            java.lang.String r3 = r12.nextString()
            r7 = r3
            goto L_0x001d
        L_0x00c9:
            r12.endObject()
            if (r8 == 0) goto L_0x00dd
            com.airbnb.lottie.LottieImageAsset r0 = new com.airbnb.lottie.LottieImageAsset
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.String r1 = r0.getId()
            r15.put(r1, r0)
            goto L_0x0003
        L_0x00dd:
            r14.put(r7, r0)
            goto L_0x0003
        L_0x00e2:
            r12.endArray()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.LottieCompositionParser.parseAssets(android.util.JsonReader, com.airbnb.lottie.LottieComposition, java.util.Map, java.util.Map):void");
    }

    private static void parseFonts(JsonReader jsonReader, Map<String, Font> map) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            if (nextName.hashCode() == 3322014 && nextName.equals(AbstractEvent.LIST)) {
                c = 0;
            }
            if (c != 0) {
                jsonReader.skipValue();
            } else {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    Font parse = FontParser.parse(jsonReader);
                    map.put(parse.getName(), parse);
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
    }

    private static void parseChars(JsonReader jsonReader, LottieComposition lottieComposition, SparseArrayCompat<FontCharacter> sparseArrayCompat) throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            FontCharacter parse = FontCharacterParser.parse(jsonReader, lottieComposition);
            sparseArrayCompat.put(parse.hashCode(), parse);
        }
        jsonReader.endArray();
    }
}
