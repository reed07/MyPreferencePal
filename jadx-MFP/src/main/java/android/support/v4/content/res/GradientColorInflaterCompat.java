package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R;
import android.util.AttributeSet;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
final class GradientColorInflaterCompat {

    static final class ColorStops {
        final int[] mColors;
        final float[] mOffsets;

        ColorStops(@NonNull List<Integer> list, @NonNull List<Float> list2) {
            int size = list.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i = 0; i < size; i++) {
                this.mColors[i] = ((Integer) list.get(i)).intValue();
                this.mOffsets[i] = ((Float) list2.get(i)).floatValue();
            }
        }

        ColorStops(@ColorInt int i, @ColorInt int i2) {
            this.mColors = new int[]{i, i2};
            this.mOffsets = new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f};
        }

        ColorStops(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
            this.mColors = new int[]{i, i2, i3};
            this.mOffsets = new float[]{BitmapDescriptorFactory.HUE_RED, 0.5f, 1.0f};
        }
    }

    private GradientColorInflaterCompat() {
    }

    static Shader createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Theme theme) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String name = xmlPullParser.getName();
        if (name.equals("gradient")) {
            Theme theme2 = theme;
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme2, attributeSet, R.styleable.GradientColor);
            float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "startX", R.styleable.GradientColor_android_startX, BitmapDescriptorFactory.HUE_RED);
            float namedFloat2 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "startY", R.styleable.GradientColor_android_startY, BitmapDescriptorFactory.HUE_RED);
            float namedFloat3 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "endX", R.styleable.GradientColor_android_endX, BitmapDescriptorFactory.HUE_RED);
            float namedFloat4 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "endY", R.styleable.GradientColor_android_endY, BitmapDescriptorFactory.HUE_RED);
            float namedFloat5 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "centerX", R.styleable.GradientColor_android_centerX, BitmapDescriptorFactory.HUE_RED);
            float namedFloat6 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "centerY", R.styleable.GradientColor_android_centerY, BitmapDescriptorFactory.HUE_RED);
            int namedInt = TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser2, "type", R.styleable.GradientColor_android_type, 0);
            int namedColor = TypedArrayUtils.getNamedColor(obtainAttributes, xmlPullParser2, "startColor", R.styleable.GradientColor_android_startColor, 0);
            boolean hasAttribute = TypedArrayUtils.hasAttribute(xmlPullParser2, "centerColor");
            int namedColor2 = TypedArrayUtils.getNamedColor(obtainAttributes, xmlPullParser2, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
            int namedColor3 = TypedArrayUtils.getNamedColor(obtainAttributes, xmlPullParser2, "endColor", R.styleable.GradientColor_android_endColor, 0);
            int namedInt2 = TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser2, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
            float f = namedFloat4;
            float namedFloat7 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser2, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, BitmapDescriptorFactory.HUE_RED);
            obtainAttributes.recycle();
            ColorStops checkColors = checkColors(inflateChildElements(resources, xmlPullParser, attributeSet, theme), namedColor, namedColor3, hasAttribute, namedColor2);
            switch (namedInt) {
                case 1:
                    if (namedFloat7 > BitmapDescriptorFactory.HUE_RED) {
                        int[] iArr = checkColors.mColors;
                        RadialGradient radialGradient = new RadialGradient(namedFloat5, namedFloat6, namedFloat7, iArr, checkColors.mOffsets, parseTileMode(namedInt2));
                        return radialGradient;
                    }
                    throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
                case 2:
                    return new SweepGradient(namedFloat5, namedFloat6, checkColors.mColors, checkColors.mOffsets);
                default:
                    LinearGradient linearGradient = new LinearGradient(namedFloat, namedFloat2, namedFloat3, f, checkColors.mColors, checkColors.mOffsets, parseTileMode(namedInt2));
                    return linearGradient;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(xmlPullParser.getPositionDescription());
            sb.append(": invalid gradient color tag ");
            sb.append(name);
            throw new XmlPullParserException(sb.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0089, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r10.toString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v4.content.res.GradientColorInflaterCompat.ColorStops inflateChildElements(@android.support.annotation.NonNull android.content.res.Resources r8, @android.support.annotation.NonNull org.xmlpull.v1.XmlPullParser r9, @android.support.annotation.NonNull android.util.AttributeSet r10, @android.support.annotation.Nullable android.content.res.Resources.Theme r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r9.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L_0x0012:
            int r3 = r9.next()
            if (r3 == r1) goto L_0x008a
            int r5 = r9.getDepth()
            if (r5 >= r0) goto L_0x0021
            r6 = 3
            if (r3 == r6) goto L_0x008a
        L_0x0021:
            r6 = 2
            if (r3 == r6) goto L_0x0025
            goto L_0x0012
        L_0x0025:
            if (r5 > r0) goto L_0x0012
            java.lang.String r3 = r9.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L_0x0034
            goto L_0x0012
        L_0x0034:
            int[] r3 = android.support.compat.R.styleable.GradientColorItem
            android.content.res.TypedArray r3 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r8, r11, r10, r3)
            int r5 = android.support.compat.R.styleable.GradientColorItem_android_color
            boolean r5 = r3.hasValue(r5)
            int r6 = android.support.compat.R.styleable.GradientColorItem_android_offset
            boolean r6 = r3.hasValue(r6)
            if (r5 == 0) goto L_0x006a
            if (r6 == 0) goto L_0x006a
            int r5 = android.support.compat.R.styleable.GradientColorItem_android_color
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            int r6 = android.support.compat.R.styleable.GradientColorItem_android_offset
            r7 = 0
            float r6 = r3.getFloat(r6, r7)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L_0x0012
        L_0x006a:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.getPositionDescription()
            r10.append(r9)
            java.lang.String r9 = ": <item> tag requires a 'color' attribute and a 'offset' "
            r10.append(r9)
            java.lang.String r9 = "attribute!"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L_0x008a:
            int r8 = r4.size()
            if (r8 <= 0) goto L_0x0096
            android.support.v4.content.res.GradientColorInflaterCompat$ColorStops r8 = new android.support.v4.content.res.GradientColorInflaterCompat$ColorStops
            r8.<init>(r4, r2)
            return r8
        L_0x0096:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.GradientColorInflaterCompat.inflateChildElements(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.support.v4.content.res.GradientColorInflaterCompat$ColorStops");
    }

    private static ColorStops checkColors(@Nullable ColorStops colorStops, @ColorInt int i, @ColorInt int i2, boolean z, @ColorInt int i3) {
        if (colorStops != null) {
            return colorStops;
        }
        if (z) {
            return new ColorStops(i, i3, i2);
        }
        return new ColorStops(i, i2);
    }

    private static TileMode parseTileMode(int i) {
        switch (i) {
            case 1:
                return TileMode.REPEAT;
            case 2:
                return TileMode.MIRROR;
            default:
                return TileMode.CLAMP;
        }
    }
}
