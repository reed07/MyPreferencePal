package com.airbnb.lottie.value;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;

public class LottieValueCallback<T> {
    @Nullable
    private BaseKeyframeAnimation<?, ?> animation;
    private final LottieFrameInfo<T> frameInfo = new LottieFrameInfo<>();
    @Nullable
    protected T value = null;

    public LottieValueCallback() {
    }

    public LottieValueCallback(@Nullable T t) {
        this.value = t;
    }

    @Nullable
    public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
        return this.value;
    }

    @Nullable
    @RestrictTo
    public final T getValueInternal(float f, float f2, T t, T t2, float f3, float f4, float f5) {
        return getValue(this.frameInfo.set(f, f2, t, t2, f3, f4, f5));
    }

    @RestrictTo
    public final void setAnimation(@Nullable BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animation = baseKeyframeAnimation;
    }
}
