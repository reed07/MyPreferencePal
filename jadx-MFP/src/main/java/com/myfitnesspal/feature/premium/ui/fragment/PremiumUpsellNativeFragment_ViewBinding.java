package com.myfitnesspal.feature.premium.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class PremiumUpsellNativeFragment_ViewBinding implements Unbinder {
    private PremiumUpsellNativeFragment target;

    @UiThread
    public PremiumUpsellNativeFragment_ViewBinding(PremiumUpsellNativeFragment premiumUpsellNativeFragment, View view) {
        this.target = premiumUpsellNativeFragment;
        premiumUpsellNativeFragment.groupContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.upsellGroupContainer, "field 'groupContainer'", ViewGroup.class);
        premiumUpsellNativeFragment.buttonContainer = (ViewGroup) Utils.findOptionalViewAsType(view, R.id.upsellBuyButtonContainer, "field 'buttonContainer'", ViewGroup.class);
        premiumUpsellNativeFragment.headerView = view.findViewById(R.id.premiumUpsellHeader);
        premiumUpsellNativeFragment.headerText = (TextView) Utils.findOptionalViewAsType(view, R.id.upsellHeaderCaption, "field 'headerText'", TextView.class);
        premiumUpsellNativeFragment.headerSubtext = (TextView) Utils.findOptionalViewAsType(view, R.id.upsellHeaderSubtext, "field 'headerSubtext'", TextView.class);
        premiumUpsellNativeFragment.headerImage = (MfpImageView) Utils.findOptionalViewAsType(view, R.id.upsellHeaderLogo, "field 'headerImage'", MfpImageView.class);
    }

    @CallSuper
    public void unbind() {
        PremiumUpsellNativeFragment premiumUpsellNativeFragment = this.target;
        if (premiumUpsellNativeFragment != null) {
            this.target = null;
            premiumUpsellNativeFragment.groupContainer = null;
            premiumUpsellNativeFragment.buttonContainer = null;
            premiumUpsellNativeFragment.headerView = null;
            premiumUpsellNativeFragment.headerText = null;
            premiumUpsellNativeFragment.headerSubtext = null;
            premiumUpsellNativeFragment.headerImage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
