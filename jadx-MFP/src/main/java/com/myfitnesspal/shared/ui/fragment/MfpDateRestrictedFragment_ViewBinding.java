package com.myfitnesspal.shared.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MfpDateRestrictedFragment_ViewBinding implements Unbinder {
    private MfpDateRestrictedFragment target;

    @UiThread
    public MfpDateRestrictedFragment_ViewBinding(MfpDateRestrictedFragment mfpDateRestrictedFragment, View view) {
        this.target = mfpDateRestrictedFragment;
        mfpDateRestrictedFragment.previous = view.findViewById(R.id.btnPrevious);
        mfpDateRestrictedFragment.next = view.findViewById(R.id.btnNext);
        mfpDateRestrictedFragment.date = (TextView) Utils.findOptionalViewAsType(view, R.id.btnDate, "field 'date'", TextView.class);
        mfpDateRestrictedFragment.contentPager = (ViewPager) Utils.findOptionalViewAsType(view, R.id.content_pager, "field 'contentPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        MfpDateRestrictedFragment mfpDateRestrictedFragment = this.target;
        if (mfpDateRestrictedFragment != null) {
            this.target = null;
            mfpDateRestrictedFragment.previous = null;
            mfpDateRestrictedFragment.next = null;
            mfpDateRestrictedFragment.date = null;
            mfpDateRestrictedFragment.contentPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
