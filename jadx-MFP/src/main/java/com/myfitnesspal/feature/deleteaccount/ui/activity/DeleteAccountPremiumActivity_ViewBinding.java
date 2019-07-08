package com.myfitnesspal.feature.deleteaccount.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class DeleteAccountPremiumActivity_ViewBinding implements Unbinder {
    private DeleteAccountPremiumActivity target;

    @UiThread
    public DeleteAccountPremiumActivity_ViewBinding(DeleteAccountPremiumActivity deleteAccountPremiumActivity) {
        this(deleteAccountPremiumActivity, deleteAccountPremiumActivity.getWindow().getDecorView());
    }

    @UiThread
    public DeleteAccountPremiumActivity_ViewBinding(DeleteAccountPremiumActivity deleteAccountPremiumActivity, View view) {
        this.target = deleteAccountPremiumActivity;
        deleteAccountPremiumActivity.premiumCancelInfoView = Utils.findRequiredView(view, R.id.premium_cancel_info, "field 'premiumCancelInfoView'");
        deleteAccountPremiumActivity.premiumCancelSwitch = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_premium_cancel, "field 'premiumCancelSwitch'", Switch.class);
        deleteAccountPremiumActivity.continueButton = Utils.findRequiredView(view, R.id.btn_continue, "field 'continueButton'");
        deleteAccountPremiumActivity.premiumCanceledText = Utils.findRequiredView(view, R.id.premium_canceled_text, "field 'premiumCanceledText'");
    }

    @CallSuper
    public void unbind() {
        DeleteAccountPremiumActivity deleteAccountPremiumActivity = this.target;
        if (deleteAccountPremiumActivity != null) {
            this.target = null;
            deleteAccountPremiumActivity.premiumCancelInfoView = null;
            deleteAccountPremiumActivity.premiumCancelSwitch = null;
            deleteAccountPremiumActivity.continueButton = null;
            deleteAccountPremiumActivity.premiumCanceledText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
