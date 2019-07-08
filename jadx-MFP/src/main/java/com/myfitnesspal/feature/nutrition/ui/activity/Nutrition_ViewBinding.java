package com.myfitnesspal.feature.nutrition.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class Nutrition_ViewBinding implements Unbinder {
    private Nutrition target;

    @UiThread
    public Nutrition_ViewBinding(Nutrition nutrition) {
        this(nutrition, nutrition.getWindow().getDecorView());
    }

    @UiThread
    public Nutrition_ViewBinding(Nutrition nutrition, View view) {
        this.target = nutrition;
        nutrition.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    }

    @CallSuper
    public void unbind() {
        Nutrition nutrition = this.target;
        if (nutrition != null) {
            this.target = null;
            nutrition.tabLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
