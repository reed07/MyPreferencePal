package com.myfitnesspal.feature.appgallery.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class XPromoInterstitialFragment_ViewBinding implements Unbinder {
    private XPromoInterstitialFragment target;

    @UiThread
    public XPromoInterstitialFragment_ViewBinding(XPromoInterstitialFragment xPromoInterstitialFragment, View view) {
        this.target = xPromoInterstitialFragment;
        xPromoInterstitialFragment.notNow = (TextView) Utils.findRequiredViewAsType(view, R.id.not_now, "field 'notNow'", TextView.class);
        xPromoInterstitialFragment.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
        xPromoInterstitialFragment.body = (TextView) Utils.findRequiredViewAsType(view, R.id.body, "field 'body'", TextView.class);
        xPromoInterstitialFragment.partnerIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.partner_icon, "field 'partnerIcon'", ImageView.class);
        xPromoInterstitialFragment.cta = (Button) Utils.findRequiredViewAsType(view, R.id.cta, "field 'cta'", Button.class);
        xPromoInterstitialFragment.dontShowAgain = (CheckBox) Utils.findRequiredViewAsType(view, R.id.dont_show_again, "field 'dontShowAgain'", CheckBox.class);
    }

    @CallSuper
    public void unbind() {
        XPromoInterstitialFragment xPromoInterstitialFragment = this.target;
        if (xPromoInterstitialFragment != null) {
            this.target = null;
            xPromoInterstitialFragment.notNow = null;
            xPromoInterstitialFragment.title = null;
            xPromoInterstitialFragment.body = null;
            xPromoInterstitialFragment.partnerIcon = null;
            xPromoInterstitialFragment.cta = null;
            xPromoInterstitialFragment.dontShowAgain = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
