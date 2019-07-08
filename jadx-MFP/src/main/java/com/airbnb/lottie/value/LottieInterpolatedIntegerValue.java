package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

public class LottieInterpolatedIntegerValue extends LottieInterpolatedValue<Integer> {
    /* access modifiers changed from: 0000 */
    public Integer interpolateValue(Integer num, Integer num2, float f) {
        return Integer.valueOf(MiscUtils.lerp(num.intValue(), num2.intValue(), f));
    }
}
