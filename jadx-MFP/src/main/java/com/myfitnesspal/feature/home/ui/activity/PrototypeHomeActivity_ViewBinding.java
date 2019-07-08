package com.myfitnesspal.feature.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class PrototypeHomeActivity_ViewBinding implements Unbinder {
    private PrototypeHomeActivity target;

    @UiThread
    public PrototypeHomeActivity_ViewBinding(PrototypeHomeActivity prototypeHomeActivity) {
        this(prototypeHomeActivity, prototypeHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public PrototypeHomeActivity_ViewBinding(PrototypeHomeActivity prototypeHomeActivity, View view) {
        this.target = prototypeHomeActivity;
        prototypeHomeActivity.buttonHome = Utils.findRequiredView(view, R.id.buttonHome, "field 'buttonHome'");
        prototypeHomeActivity.buttonDiary = Utils.findRequiredView(view, R.id.buttonDiary, "field 'buttonDiary'");
        prototypeHomeActivity.buttonExplore = Utils.findRequiredView(view, R.id.buttonExplore, "field 'buttonExplore'");
        prototypeHomeActivity.buttonMe = Utils.findRequiredView(view, R.id.buttonMe, "field 'buttonMe'");
    }

    @CallSuper
    public void unbind() {
        PrototypeHomeActivity prototypeHomeActivity = this.target;
        if (prototypeHomeActivity != null) {
            this.target = null;
            prototypeHomeActivity.buttonHome = null;
            prototypeHomeActivity.buttonDiary = null;
            prototypeHomeActivity.buttonExplore = null;
            prototypeHomeActivity.buttonMe = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
