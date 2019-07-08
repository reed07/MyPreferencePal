package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import com.uacf.core.util.ViewUtils;

public class StateAwareScrollView extends NestedScrollView {
    private static final String BUNDLE_KEY_PREFIX_X = "StateAwayScrollViewX.";
    private static final String BUNDLE_KEY_PREFIX_Y = "StateAwayScrollViewY.";

    public StateAwareScrollView(Context context) {
        super(context);
    }

    public StateAwareScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StateAwareScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void loadScrollState(Bundle bundle) {
        if (bundle != null) {
            int id = getId();
            if (id != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(BUNDLE_KEY_PREFIX_X);
                sb.append(id);
                final int i = bundle.getInt(sb.toString(), getScrollX());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(BUNDLE_KEY_PREFIX_Y);
                sb2.append(id);
                final int i2 = bundle.getInt(sb2.toString(), getScrollY());
                post(new Runnable() {
                    public void run() {
                        StateAwareScrollView.this.setScrollX(i);
                        StateAwareScrollView.this.setScrollY(i2);
                    }
                });
            }
        }
    }

    public static void loadScrollState(View view, Bundle bundle) {
        if (view != null && bundle != null) {
            for (StateAwareScrollView loadScrollState : ViewUtils.findByType(view, StateAwareScrollView.class)) {
                loadScrollState.loadScrollState(bundle);
            }
        }
    }

    public static void saveScrollState(View view, Bundle bundle) {
        if (view != null && bundle != null) {
            for (StateAwareScrollView stateAwareScrollView : ViewUtils.findByType(view, StateAwareScrollView.class)) {
                int id = stateAwareScrollView.getId();
                if (id != -1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(BUNDLE_KEY_PREFIX_X);
                    sb.append(id);
                    bundle.putInt(sb.toString(), stateAwareScrollView.getScrollX());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(BUNDLE_KEY_PREFIX_Y);
                    sb2.append(id);
                    bundle.putInt(sb2.toString(), stateAwareScrollView.getScrollY());
                }
            }
        }
    }
}
