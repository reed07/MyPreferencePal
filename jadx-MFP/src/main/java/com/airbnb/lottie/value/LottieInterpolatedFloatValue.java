package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

public class LottieInterpolatedFloatValue extends LottieInterpolatedValue<Float> {
    /* access modifiers changed from: 0000 */
    public Float interpolateValue(Float f, Float f2, float f3) {
        return Float.valueOf(MiscUtils.lerp(f.floatValue(), f2.floatValue(), f3));
    }
}
