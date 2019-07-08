package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.uacf.core.util.VersionUtils;

public class ResizableFrameLayout extends FrameLayout {
    private int[] mInsets = new int[4];

    public ResizableFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ResizableFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ResizableFrameLayout(Context context) {
        super(context);
    }

    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (!VersionUtils.isOsVersionGreaterThanOrEqualTo(20)) {
            return windowInsets;
        }
        this.mInsets[0] = windowInsets.getSystemWindowInsetLeft();
        this.mInsets[1] = windowInsets.getSystemWindowInsetTop();
        this.mInsets[2] = windowInsets.getSystemWindowInsetRight();
        return super.onApplyWindowInsets(windowInsets.replaceSystemWindowInsets(0, 0, 0, windowInsets.getSystemWindowInsetBottom()));
    }
}
