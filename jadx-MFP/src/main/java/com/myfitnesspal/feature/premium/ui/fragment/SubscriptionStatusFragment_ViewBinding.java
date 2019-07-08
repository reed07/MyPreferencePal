package com.myfitnesspal.feature.premium.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SubscriptionStatusFragment_ViewBinding implements Unbinder {
    private SubscriptionStatusFragment target;

    @UiThread
    public SubscriptionStatusFragment_ViewBinding(SubscriptionStatusFragment subscriptionStatusFragment, View view) {
        this.target = subscriptionStatusFragment;
        subscriptionStatusFragment.nativeManagePanel = Utils.findRequiredView(view, R.id.native_manage_panel, "field 'nativeManagePanel'");
        subscriptionStatusFragment.externalManagePanel = Utils.findRequiredView(view, R.id.external_manage_panel, "field 'externalManagePanel'");
        subscriptionStatusFragment.planValue = (TextView) Utils.findRequiredViewAsType(view, R.id.current_plan_value, "field 'planValue'", TextView.class);
        subscriptionStatusFragment.paymentPlatform = (TextView) Utils.findRequiredViewAsType(view, R.id.payment_platform_value, "field 'paymentPlatform'", TextView.class);
        subscriptionStatusFragment.externalManageText = (TextView) Utils.findRequiredViewAsType(view, R.id.external_manage_text, "field 'externalManageText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SubscriptionStatusFragment subscriptionStatusFragment = this.target;
        if (subscriptionStatusFragment != null) {
            this.target = null;
            subscriptionStatusFragment.nativeManagePanel = null;
            subscriptionStatusFragment.externalManagePanel = null;
            subscriptionStatusFragment.planValue = null;
            subscriptionStatusFragment.paymentPlatform = null;
            subscriptionStatusFragment.externalManageText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
