package com.myfitnesspal.feature.payments.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class PaymentConfirmation_ViewBinding implements Unbinder {
    private PaymentConfirmation target;

    @UiThread
    public PaymentConfirmation_ViewBinding(PaymentConfirmation paymentConfirmation) {
        this(paymentConfirmation, paymentConfirmation.getWindow().getDecorView());
    }

    @UiThread
    public PaymentConfirmation_ViewBinding(PaymentConfirmation paymentConfirmation, View view) {
        this.target = paymentConfirmation;
        paymentConfirmation.planDuration = (TextView) Utils.findRequiredViewAsType(view, R.id.plan_value, "field 'planDuration'", TextView.class);
        paymentConfirmation.renewalDate = (TextView) Utils.findRequiredViewAsType(view, R.id.renew_value, "field 'renewalDate'", TextView.class);
        paymentConfirmation.emailAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.email_value, "field 'emailAddress'", TextView.class);
        paymentConfirmation.doneButton = Utils.findRequiredView(view, R.id.done_button, "field 'doneButton'");
        paymentConfirmation.headerText = (TextView) Utils.findRequiredViewAsType(view, R.id.upsellHeaderCaption, "field 'headerText'", TextView.class);
        paymentConfirmation.infoDetailsContainer = Utils.findRequiredView(view, R.id.info_details_container, "field 'infoDetailsContainer'");
        paymentConfirmation.unableToPostText = Utils.findRequiredView(view, R.id.unable_to_post_text, "field 'unableToPostText'");
        paymentConfirmation.confirmationInfoTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.confirmation_info_title, "field 'confirmationInfoTitle'", TextView.class);
        paymentConfirmation.confirmationInfoBody = (TextView) Utils.findRequiredViewAsType(view, R.id.confirmation_info_body, "field 'confirmationInfoBody'", TextView.class);
        paymentConfirmation.explorePremiumButton = Utils.findRequiredView(view, R.id.explore_premium_button, "field 'explorePremiumButton'");
        paymentConfirmation.notNowView = Utils.findRequiredView(view, R.id.not_now_button, "field 'notNowView'");
        paymentConfirmation.standardPaymentConfirmationContainer = Utils.findRequiredView(view, R.id.standard_payment_confirmation_container, "field 'standardPaymentConfirmationContainer'");
        paymentConfirmation.freeTrialPaymentConfirmationContainer = Utils.findRequiredView(view, R.id.free_trial_payment_confirmation_container, "field 'freeTrialPaymentConfirmationContainer'");
    }

    @CallSuper
    public void unbind() {
        PaymentConfirmation paymentConfirmation = this.target;
        if (paymentConfirmation != null) {
            this.target = null;
            paymentConfirmation.planDuration = null;
            paymentConfirmation.renewalDate = null;
            paymentConfirmation.emailAddress = null;
            paymentConfirmation.doneButton = null;
            paymentConfirmation.headerText = null;
            paymentConfirmation.infoDetailsContainer = null;
            paymentConfirmation.unableToPostText = null;
            paymentConfirmation.confirmationInfoTitle = null;
            paymentConfirmation.confirmationInfoBody = null;
            paymentConfirmation.explorePremiumButton = null;
            paymentConfirmation.notNowView = null;
            paymentConfirmation.standardPaymentConfirmationContainer = null;
            paymentConfirmation.freeTrialPaymentConfirmationContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
