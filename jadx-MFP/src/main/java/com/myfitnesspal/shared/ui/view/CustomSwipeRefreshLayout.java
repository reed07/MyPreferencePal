package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    private boolean mDeclined;
    private float mPrevX;
    private int mTouchSlop;

    public CustomSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mPrevX = MotionEvent.obtain(motionEvent).getX();
            this.mDeclined = false;
        } else if (action == 2) {
            float abs = Math.abs(motionEvent.getX() - this.mPrevX);
            if (this.mDeclined || abs > ((float) this.mTouchSlop)) {
                this.mDeclined = true;
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
