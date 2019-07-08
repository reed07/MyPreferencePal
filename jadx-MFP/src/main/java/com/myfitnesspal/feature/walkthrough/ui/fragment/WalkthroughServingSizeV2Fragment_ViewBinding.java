package com.myfitnesspal.feature.walkthrough.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class WalkthroughServingSizeV2Fragment_ViewBinding implements Unbinder {
    private WalkthroughServingSizeV2Fragment target;

    @UiThread
    public WalkthroughServingSizeV2Fragment_ViewBinding(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment, View view) {
        this.target = walkthroughServingSizeV2Fragment;
        walkthroughServingSizeV2Fragment.txtFoodName = (TextView) Utils.findRequiredViewAsType(view, R.id.txtFoodName, "field 'txtFoodName'", TextView.class);
        walkthroughServingSizeV2Fragment.txtNoOfServings = (TextView) Utils.findRequiredViewAsType(view, R.id.txtNoOfServings, "field 'txtNoOfServings'", TextView.class);
        walkthroughServingSizeV2Fragment.noOfServingsTableRow = Utils.findRequiredView(view, R.id.noOfServingsTableRow, "field 'noOfServingsTableRow'");
        walkthroughServingSizeV2Fragment.txtServingSize = (TextView) Utils.findRequiredViewAsType(view, R.id.txtServingSize, "field 'txtServingSize'", TextView.class);
        walkthroughServingSizeV2Fragment.servingSizeTableRow = Utils.findRequiredView(view, R.id.servingSizeTableRow, "field 'servingSizeTableRow'");
        walkthroughServingSizeV2Fragment.walkThroughContainer = Utils.findRequiredView(view, R.id.walkthrough_container, "field 'walkThroughContainer'");
        walkthroughServingSizeV2Fragment.verifiedBadge = Utils.findRequiredView(view, R.id.verified_badge, "field 'verifiedBadge'");
    }

    @CallSuper
    public void unbind() {
        WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment = this.target;
        if (walkthroughServingSizeV2Fragment != null) {
            this.target = null;
            walkthroughServingSizeV2Fragment.txtFoodName = null;
            walkthroughServingSizeV2Fragment.txtNoOfServings = null;
            walkthroughServingSizeV2Fragment.noOfServingsTableRow = null;
            walkthroughServingSizeV2Fragment.txtServingSize = null;
            walkthroughServingSizeV2Fragment.servingSizeTableRow = null;
            walkthroughServingSizeV2Fragment.walkThroughContainer = null;
            walkthroughServingSizeV2Fragment.verifiedBadge = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
