package com.myfitnesspal.shared.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MfpPagedEditableActivity_ViewBinding implements Unbinder {
    private MfpPagedEditableActivity target;

    @UiThread
    public MfpPagedEditableActivity_ViewBinding(MfpPagedEditableActivity mfpPagedEditableActivity) {
        this(mfpPagedEditableActivity, mfpPagedEditableActivity.getWindow().getDecorView());
    }

    @UiThread
    public MfpPagedEditableActivity_ViewBinding(MfpPagedEditableActivity mfpPagedEditableActivity, View view) {
        this.target = mfpPagedEditableActivity;
        mfpPagedEditableActivity.indicator = (TabLayout) Utils.findRequiredViewAsType(view, R.id.indicator, "field 'indicator'", TabLayout.class);
        mfpPagedEditableActivity.pager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.pager, "field 'pager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        MfpPagedEditableActivity mfpPagedEditableActivity = this.target;
        if (mfpPagedEditableActivity != null) {
            this.target = null;
            mfpPagedEditableActivity.indicator = null;
            mfpPagedEditableActivity.pager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
