package com.airbnb.lottie;

import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.ColorInt;

public class SimpleColorFilter extends PorterDuffColorFilter {
    public SimpleColorFilter(@ColorInt int i) {
        super(i, Mode.SRC_ATOP);
    }
}
