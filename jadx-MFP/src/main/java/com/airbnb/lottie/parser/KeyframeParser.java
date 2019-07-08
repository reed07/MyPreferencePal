package com.airbnb.lottie.parser;

import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.util.JsonReader;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.lang.ref.WeakReference;

class KeyframeParser {
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;

    KeyframeParser() {
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    @Nullable
    private static WeakReference<Interpolator> getInterpolator(int i) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = (WeakReference) pathInterpolatorCache().get(i);
        }
        return weakReference;
    }

    private static void putInterpolator(int i, WeakReference<Interpolator> weakReference) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(i, weakReference);
        }
    }

    static <T> Keyframe<T> parse(JsonReader jsonReader, LottieComposition lottieComposition, float f, ValueParser<T> valueParser, boolean z) throws IOException {
        if (z) {
            return parseKeyframe(lottieComposition, jsonReader, f, valueParser);
        }
        return parseStaticValue(jsonReader, f, valueParser);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> com.airbnb.lottie.value.Keyframe<T> parseKeyframe(com.airbnb.lottie.LottieComposition r16, android.util.JsonReader r17, float r18, com.airbnb.lottie.parser.ValueParser<T> r19) throws java.io.IOException {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r17.beginObject()
            r4 = 0
            r5 = 0
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r14 = r9
            r15 = r14
            r5 = 0
            r12 = 0
        L_0x0013:
            boolean r10 = r17.hasNext()
            if (r10 == 0) goto L_0x00ba
            java.lang.String r10 = r17.nextName()
            r11 = -1
            int r13 = r10.hashCode()
            r3 = 1
            switch(r13) {
                case 101: goto L_0x006d;
                case 104: goto L_0x0063;
                case 105: goto L_0x0059;
                case 111: goto L_0x004f;
                case 115: goto L_0x0045;
                case 116: goto L_0x003b;
                case 3701: goto L_0x0031;
                case 3707: goto L_0x0027;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x0077
        L_0x0027:
            java.lang.String r13 = "to"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 6
            goto L_0x0078
        L_0x0031:
            java.lang.String r13 = "ti"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 7
            goto L_0x0078
        L_0x003b:
            java.lang.String r13 = "t"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 0
            goto L_0x0078
        L_0x0045:
            java.lang.String r13 = "s"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 1
            goto L_0x0078
        L_0x004f:
            java.lang.String r13 = "o"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 3
            goto L_0x0078
        L_0x0059:
            java.lang.String r13 = "i"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 4
            goto L_0x0078
        L_0x0063:
            java.lang.String r13 = "h"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 5
            goto L_0x0078
        L_0x006d:
            java.lang.String r13 = "e"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x0077
            r10 = 2
            goto L_0x0078
        L_0x0077:
            r10 = -1
        L_0x0078:
            switch(r10) {
                case 0: goto L_0x00b2;
                case 1: goto L_0x00ab;
                case 2: goto L_0x00a4;
                case 3: goto L_0x009d;
                case 4: goto L_0x0096;
                case 5: goto L_0x008b;
                case 6: goto L_0x0085;
                case 7: goto L_0x007f;
                default: goto L_0x007b;
            }
        L_0x007b:
            r17.skipValue()
            goto L_0x0013
        L_0x007f:
            android.graphics.PointF r3 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r17, r18)
            r15 = r3
            goto L_0x0013
        L_0x0085:
            android.graphics.PointF r3 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r17, r18)
            r14 = r3
            goto L_0x0013
        L_0x008b:
            int r5 = r17.nextInt()
            if (r5 != r3) goto L_0x0092
            goto L_0x0093
        L_0x0092:
            r3 = 0
        L_0x0093:
            r5 = r3
            goto L_0x0013
        L_0x0096:
            android.graphics.PointF r3 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r17, r18)
            r7 = r3
            goto L_0x0013
        L_0x009d:
            android.graphics.PointF r3 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r17, r18)
            r6 = r3
            goto L_0x0013
        L_0x00a4:
            java.lang.Object r3 = r2.parse(r0, r1)
            r8 = r3
            goto L_0x0013
        L_0x00ab:
            java.lang.Object r3 = r2.parse(r0, r1)
            r9 = r3
            goto L_0x0013
        L_0x00b2:
            double r10 = r17.nextDouble()
            float r3 = (float) r10
            r12 = r3
            goto L_0x0013
        L_0x00ba:
            r17.endObject()
            if (r5 == 0) goto L_0x00c4
            android.view.animation.Interpolator r0 = LINEAR_INTERPOLATOR
            r11 = r0
            r10 = r9
            goto L_0x0129
        L_0x00c4:
            if (r6 == 0) goto L_0x0125
            if (r7 == 0) goto L_0x0125
            float r0 = r6.x
            float r2 = -r1
            float r0 = com.airbnb.lottie.utils.MiscUtils.clamp(r0, r2, r1)
            r6.x = r0
            float r0 = r6.y
            r3 = 1120403456(0x42c80000, float:100.0)
            r5 = -1027080192(0xffffffffc2c80000, float:-100.0)
            float r0 = com.airbnb.lottie.utils.MiscUtils.clamp(r0, r5, r3)
            r6.y = r0
            float r0 = r7.x
            float r0 = com.airbnb.lottie.utils.MiscUtils.clamp(r0, r2, r1)
            r7.x = r0
            float r0 = r7.y
            float r0 = com.airbnb.lottie.utils.MiscUtils.clamp(r0, r5, r3)
            r7.y = r0
            float r0 = r6.x
            float r2 = r6.y
            float r3 = r7.x
            float r5 = r7.y
            int r0 = com.airbnb.lottie.utils.Utils.hashFor(r0, r2, r3, r5)
            java.lang.ref.WeakReference r2 = getInterpolator(r0)
            if (r2 == 0) goto L_0x0106
            java.lang.Object r3 = r2.get()
            r4 = r3
            android.view.animation.Interpolator r4 = (android.view.animation.Interpolator) r4
        L_0x0106:
            if (r2 == 0) goto L_0x010a
            if (r4 != 0) goto L_0x0122
        L_0x010a:
            float r2 = r6.x
            float r2 = r2 / r1
            float r3 = r6.y
            float r3 = r3 / r1
            float r4 = r7.x
            float r4 = r4 / r1
            float r5 = r7.y
            float r5 = r5 / r1
            android.view.animation.Interpolator r4 = android.support.v4.view.animation.PathInterpolatorCompat.create(r2, r3, r4, r5)
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0122 }
            r1.<init>(r4)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0122 }
            putInterpolator(r0, r1)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0122 }
        L_0x0122:
            r11 = r4
            r10 = r8
            goto L_0x0129
        L_0x0125:
            android.view.animation.Interpolator r0 = LINEAR_INTERPOLATOR
            r11 = r0
            r10 = r8
        L_0x0129:
            com.airbnb.lottie.value.Keyframe r0 = new com.airbnb.lottie.value.Keyframe
            r13 = 0
            r7 = r0
            r8 = r16
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r0.pathCp1 = r14
            r0.pathCp2 = r15
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.KeyframeParser.parseKeyframe(com.airbnb.lottie.LottieComposition, android.util.JsonReader, float, com.airbnb.lottie.parser.ValueParser):com.airbnb.lottie.value.Keyframe");
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        return new Keyframe<>(valueParser.parse(jsonReader, f));
    }
}
