package com.myfitnesspal.feature.appgallery.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class AppDetailFragment_ViewBinding implements Unbinder {
    private AppDetailFragment target;

    @UiThread
    public AppDetailFragment_ViewBinding(AppDetailFragment appDetailFragment, View view) {
        this.target = appDetailFragment;
        appDetailFragment.txtAppName = (TextView) Utils.findRequiredViewAsType(view, R.id.appName, "field 'txtAppName'", TextView.class);
        appDetailFragment.txtCompanyName = (TextView) Utils.findRequiredViewAsType(view, R.id.appCompany, "field 'txtCompanyName'", TextView.class);
        appDetailFragment.txtAppDescription = (TextView) Utils.findRequiredViewAsType(view, R.id.appDetailedDescription, "field 'txtAppDescription'", TextView.class);
        appDetailFragment.actionButton = (Button) Utils.findRequiredViewAsType(view, R.id.action_button, "field 'actionButton'", Button.class);
        appDetailFragment.learnMoreButton = Utils.findRequiredView(view, R.id.learn_more, "field 'learnMoreButton'");
        appDetailFragment.appIcon = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.appImageView, "field 'appIcon'", MfpImageView.class);
        appDetailFragment.screenShotsPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.screenShotsPager, "field 'screenShotsPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        AppDetailFragment appDetailFragment = this.target;
        if (appDetailFragment != null) {
            this.target = null;
            appDetailFragment.txtAppName = null;
            appDetailFragment.txtCompanyName = null;
            appDetailFragment.txtAppDescription = null;
            appDetailFragment.actionButton = null;
            appDetailFragment.learnMoreButton = null;
            appDetailFragment.appIcon = null;
            appDetailFragment.screenShotsPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
