package com.airbnb.lottie.model;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

@RestrictTo
public interface KeyPathElement {
    <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback);

    void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2);
}
