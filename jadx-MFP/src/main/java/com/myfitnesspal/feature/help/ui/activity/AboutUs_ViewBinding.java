package com.myfitnesspal.feature.help.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AboutUs_ViewBinding implements Unbinder {
    private AboutUs target;

    @UiThread
    public AboutUs_ViewBinding(AboutUs aboutUs) {
        this(aboutUs, aboutUs.getWindow().getDecorView());
    }

    @UiThread
    public AboutUs_ViewBinding(AboutUs aboutUs, View view) {
        this.target = aboutUs;
        aboutUs.copyright = (TextView) Utils.findRequiredViewAsType(view, R.id.copyright, "field 'copyright'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        AboutUs aboutUs = this.target;
        if (aboutUs != null) {
            this.target = null;
            aboutUs.copyright = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
