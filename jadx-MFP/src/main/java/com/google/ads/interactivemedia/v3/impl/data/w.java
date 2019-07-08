package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;

/* compiled from: IMASDK */
public abstract class w {
    public abstract v build();

    public abstract w height(int i);

    public abstract w left(int i);

    public abstract w top(int i);

    public abstract w width(int i);

    public w locationOnScreenOfView(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return left(iArr[0]).top(iArr[1]).height(view.getHeight()).width(view.getWidth());
    }
}
