package com.myfitnesspal.shared.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import javax.inject.Inject;

public final class InputMethodManagerHelper {
    private Context context;
    private InputMethodManager imm;

    @Inject
    public InputMethodManagerHelper(Context context2) {
        this.imm = (InputMethodManager) context2.getSystemService("input_method");
        this.context = context2;
    }

    public void showSoftInput() {
        this.imm.toggleSoftInput(2, 1);
    }

    public void showSoftInput(View view) {
        this.imm.showSoftInput(view, 2);
    }

    public void hideSoftInput(Activity activity) {
        hideSoftInput(ActivityUtils.findById(activity, 16908290));
    }

    public void hideSoftInput(Activity activity, int i) {
        hideSoftInput(ActivityUtils.findById(activity, i));
    }

    public void hideSoftInput(View view) {
        if (view != null) {
            this.imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void hideSoftInput() {
        Context context2 = this.context;
        if (context2 instanceof Activity) {
            hideSoftInput((Activity) context2);
        }
    }
}
