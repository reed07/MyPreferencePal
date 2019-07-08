package com.myfitnesspal.feature.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class HomeActivity_ViewBinding implements Unbinder {
    private HomeActivity target;

    @UiThread
    public HomeActivity_ViewBinding(HomeActivity homeActivity) {
        this(homeActivity, homeActivity.getWindow().getDecorView());
    }

    @UiThread
    public HomeActivity_ViewBinding(HomeActivity homeActivity, View view) {
        this.target = homeActivity;
        homeActivity.bottomBarOnboarding = Utils.findRequiredView(view, R.id.bottom_bar_onboard, "field 'bottomBarOnboarding'");
    }

    @CallSuper
    public void unbind() {
        HomeActivity homeActivity = this.target;
        if (homeActivity != null) {
            this.target = null;
            homeActivity.bottomBarOnboarding = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
