package com.myfitnesspal.feature.diary.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class DiarySettingsActivity_ViewBinding implements Unbinder {
    private DiarySettingsActivity target;

    @UiThread
    public DiarySettingsActivity_ViewBinding(DiarySettingsActivity diarySettingsActivity) {
        this(diarySettingsActivity, diarySettingsActivity.getWindow().getDecorView());
    }

    @UiThread
    public DiarySettingsActivity_ViewBinding(DiarySettingsActivity diarySettingsActivity, View view) {
        this.target = diarySettingsActivity;
        diarySettingsActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
        diarySettingsActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.fragments_pager, "field 'viewPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        DiarySettingsActivity diarySettingsActivity = this.target;
        if (diarySettingsActivity != null) {
            this.target = null;
            diarySettingsActivity.tabLayout = null;
            diarySettingsActivity.viewPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
