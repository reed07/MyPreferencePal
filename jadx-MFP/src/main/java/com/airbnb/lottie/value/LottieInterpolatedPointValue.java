package com.airbnb.lottie.value;

import android.graphics.PointF;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieInterpolatedPointValue extends LottieInterpolatedValue<PointF> {
    private final PointF point;

    /* access modifiers changed from: 0000 */
    public PointF interpolateValue(PointF pointF, PointF pointF2, float f) {
        this.point.set(MiscUtils.lerp(pointF.x, pointF2.x, f), MiscUtils.lerp(pointF.y, pointF2.y, f));
        return this.point;
    }
}
