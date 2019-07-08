package com.airbnb.lottie.animation.keyframe;

import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseKeyframeAnimation<K, A> {
    @Nullable
    private Keyframe<K> cachedKeyframe;
    private boolean isDiscrete = false;
    private final List<? extends Keyframe<K>> keyframes;
    final List<AnimationListener> listeners = new ArrayList();
    private float progress = BitmapDescriptorFactory.HUE_RED;
    @Nullable
    protected LottieValueCallback<A> valueCallback;

    public interface AnimationListener {
        void onValueChanged();
    }

    /* access modifiers changed from: 0000 */
    public abstract A getValue(Keyframe<K> keyframe, float f);

    BaseKeyframeAnimation(List<? extends Keyframe<K>> list) {
        this.keyframes = list;
    }

    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    public void addUpdateListener(AnimationListener animationListener) {
        this.listeners.add(animationListener);
    }

    public void setProgress(@FloatRange float f) {
        if (f < getStartDelayProgress()) {
            f = getStartDelayProgress();
        } else if (f > getEndProgress()) {
            f = getEndProgress();
        }
        if (f != this.progress) {
            this.progress = f;
            notifyListeners();
        }
    }

    public void notifyListeners() {
        for (int i = 0; i < this.listeners.size(); i++) {
            ((AnimationListener) this.listeners.get(i)).onValueChanged();
        }
    }

    private Keyframe<K> getCurrentKeyframe() {
        Keyframe<K> keyframe = this.cachedKeyframe;
        if (keyframe != null && keyframe.containsProgress(this.progress)) {
            return this.cachedKeyframe;
        }
        List<? extends Keyframe<K>> list = this.keyframes;
        Keyframe<K> keyframe2 = (Keyframe) list.get(list.size() - 1);
        if (this.progress < keyframe2.getStartProgress()) {
            for (int size = this.keyframes.size() - 1; size >= 0; size--) {
                keyframe2 = (Keyframe) this.keyframes.get(size);
                if (keyframe2.containsProgress(this.progress)) {
                    break;
                }
            }
        }
        this.cachedKeyframe = keyframe2;
        return keyframe2;
    }

    /* access modifiers changed from: 0000 */
    public float getLinearCurrentKeyframeProgress() {
        if (this.isDiscrete) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        Keyframe currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe.isStatic()) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return (this.progress - currentKeyframe.getStartProgress()) / (currentKeyframe.getEndProgress() - currentKeyframe.getStartProgress());
    }

    private float getInterpolatedCurrentKeyframeProgress() {
        Keyframe currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe.isStatic()) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return currentKeyframe.interpolator.getInterpolation(getLinearCurrentKeyframeProgress());
    }

    @FloatRange
    private float getStartDelayProgress() {
        return this.keyframes.isEmpty() ? BitmapDescriptorFactory.HUE_RED : ((Keyframe) this.keyframes.get(0)).getStartProgress();
    }

    /* access modifiers changed from: 0000 */
    @FloatRange
    public float getEndProgress() {
        if (this.keyframes.isEmpty()) {
            return 1.0f;
        }
        List<? extends Keyframe<K>> list = this.keyframes;
        return ((Keyframe) list.get(list.size() - 1)).getEndProgress();
    }

    public A getValue() {
        return getValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }

    public float getProgress() {
        return this.progress;
    }

    public void setValueCallback(@Nullable LottieValueCallback<A> lottieValueCallback) {
        LottieValueCallback<A> lottieValueCallback2 = this.valueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.valueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }
}
