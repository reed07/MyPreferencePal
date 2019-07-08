package com.myfitnesspal.shared.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CustomToggle_ViewBinding implements Unbinder {
    private CustomToggle target;

    @UiThread
    public CustomToggle_ViewBinding(CustomToggle customToggle) {
        this(customToggle, customToggle);
    }

    @UiThread
    public CustomToggle_ViewBinding(CustomToggle customToggle, View view) {
        this.target = customToggle;
        customToggle.leftToggle = (Button) Utils.findRequiredViewAsType(view, R.id.left_toggle_btn, "field 'leftToggle'", Button.class);
        customToggle.rightToggle = (Button) Utils.findRequiredViewAsType(view, R.id.right_toggle_btn, "field 'rightToggle'", Button.class);
    }

    @CallSuper
    public void unbind() {
        CustomToggle customToggle = this.target;
        if (customToggle != null) {
            this.target = null;
            customToggle.leftToggle = null;
            customToggle.rightToggle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
