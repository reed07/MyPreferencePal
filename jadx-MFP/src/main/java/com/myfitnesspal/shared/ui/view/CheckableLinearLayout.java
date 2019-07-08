package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private static final int[] CheckedStateSet = {16842912};
    private boolean isChecked;

    public CheckableLinearLayout(Context context) {
        super(context);
    }

    public CheckableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CheckedStateSet);
        }
        return onCreateDrawableState;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
        refreshDrawableState();
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
