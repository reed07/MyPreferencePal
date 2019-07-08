package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.xmlpull.v1.XmlPullParser;

public class ArcMotion extends PathMotion {
    private static final float DEFAULT_MAX_TANGENT = ((float) Math.tan(Math.toRadians(35.0d)));
    private float mMaximumAngle = 70.0f;
    private float mMaximumTangent = DEFAULT_MAX_TANGENT;
    private float mMinimumHorizontalAngle = BitmapDescriptorFactory.HUE_RED;
    private float mMinimumHorizontalTangent = BitmapDescriptorFactory.HUE_RED;
    private float mMinimumVerticalAngle = BitmapDescriptorFactory.HUE_RED;
    private float mMinimumVerticalTangent = BitmapDescriptorFactory.HUE_RED;

    public ArcMotion() {
    }

    public ArcMotion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.ARC_MOTION);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        setMinimumVerticalAngle(TypedArrayUtils.getNamedFloat(obtainStyledAttributes, xmlPullParser, "minimumVerticalAngle", 1, BitmapDescriptorFactory.HUE_RED));
        setMinimumHorizontalAngle(TypedArrayUtils.getNamedFloat(obtainStyledAttributes, xmlPullParser, "minimumHorizontalAngle", 0, BitmapDescriptorFactory.HUE_RED));
        setMaximumAngle(TypedArrayUtils.getNamedFloat(obtainStyledAttributes, xmlPullParser, "maximumAngle", 2, 70.0f));
        obtainStyledAttributes.recycle();
    }

    public void setMinimumHorizontalAngle(float f) {
        this.mMinimumHorizontalAngle = f;
        this.mMinimumHorizontalTangent = toTangent(f);
    }

    public void setMinimumVerticalAngle(float f) {
        this.mMinimumVerticalAngle = f;
        this.mMinimumVerticalTangent = toTangent(f);
    }

    public void setMaximumAngle(float f) {
        this.mMaximumAngle = f;
        this.mMaximumTangent = toTangent(f);
    }

    private static float toTangent(float f) {
        if (f >= BitmapDescriptorFactory.HUE_RED && f <= 90.0f) {
            return (float) Math.tan(Math.toRadians((double) (f / 2.0f)));
        }
        throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
    }

    public Path getPath(float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        Path path = new Path();
        path.moveTo(f, f2);
        float f10 = f3 - f;
        float f11 = f4 - f2;
        float f12 = (f10 * f10) + (f11 * f11);
        float f13 = (f + f3) / 2.0f;
        float f14 = (f2 + f4) / 2.0f;
        float f15 = 0.25f * f12;
        boolean z = f2 > f4;
        if (Math.abs(f10) < Math.abs(f11)) {
            float abs = Math.abs(f12 / (f11 * 2.0f));
            if (z) {
                f6 = abs + f4;
                f7 = f3;
            } else {
                f6 = abs + f2;
                f7 = f;
            }
            float f16 = this.mMinimumVerticalTangent;
            f5 = f15 * f16 * f16;
        } else {
            float f17 = f12 / (f10 * 2.0f);
            if (z) {
                f9 = f2;
                f8 = f17 + f;
            } else {
                f8 = f3 - f17;
                f9 = f4;
            }
            float f18 = this.mMinimumHorizontalTangent;
            f5 = f15 * f18 * f18;
        }
        float f19 = f13 - f7;
        float f20 = f14 - f6;
        float f21 = (f19 * f19) + (f20 * f20);
        float f22 = this.mMaximumTangent;
        float f23 = f15 * f22 * f22;
        if (f21 < f5) {
            f23 = f5;
        } else if (f21 <= f23) {
            f23 = BitmapDescriptorFactory.HUE_RED;
        }
        if (f23 != BitmapDescriptorFactory.HUE_RED) {
            float sqrt = (float) Math.sqrt((double) (f23 / f21));
            f7 = ((f7 - f13) * sqrt) + f13;
            f6 = f14 + (sqrt * (f6 - f14));
        }
        path.cubicTo((f + f7) / 2.0f, (f2 + f6) / 2.0f, (f7 + f3) / 2.0f, (f6 + f4) / 2.0f, f3, f4);
        return path;
    }
}
