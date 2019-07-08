package com.myfitnesspal.feature.payments.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription.SubscriptionStatus;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelperImpl;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.payments.util.PaymentUtils;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.util.ProductUtils;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNeutralListener;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class PaymentConfirmation extends MfpActivity {
    private static final String DATE_PARSE_PATTERN = Format.newIso8601DateFormat().toPattern();
    public static final int DEFAULT_TRIAL_DURATION_DAYS = 30;
    public static final String EXTRA_FREE_TRIAL_DURATION_DAYS = "free_trial_duration";
    private static final String EXTRA_FREE_TRIAL_ENABLED = "free_trial_enabled";
    private static final String MANAGE_DIALOG_TAG = "PaymentConfirmation.ManageDialog";
    private static final String TAG = "PaymentConfirmation";
    @BindView(2131362196)
    TextView confirmationInfoBody;
    @BindView(2131362197)
    TextView confirmationInfoTitle;
    @BindView(2131362345)
    View doneButton;
    @BindView(2131362449)
    TextView emailAddress;
    @BindView(2131362536)
    View explorePremiumButton;
    @BindView(2131362660)
    View freeTrialPaymentConfirmationContainer;
    @BindView(2131364103)
    TextView headerText;
    @BindView(2131362807)
    View infoDetailsContainer;
    @BindView(2131363163)
    View notNowView;
    private OnClickListener onDoneClickListener = new OnClickListener() {
        public void onClick(View view) {
            PaymentConfirmation.this.finish();
        }
    };
    private OnClickListener onExplorePremiumClickListener = new OnClickListener() {
        public void onClick(View view) {
            ((PaymentAnalyticsHelper) PaymentConfirmation.this.paymentAnalyticsHelper.get()).reportFreeTrialActivatedExploreClicked();
            PaymentConfirmation.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) PaymentConfirmation.this.getActivity(), PaymentAnalyticsHelperImpl.ANALYTICS_MENU_DRAWER, UpsellDisplayMode.FeatureScreen)).finishActivityAfterNavigation().startActivity();
        }
    };
    private DialogNeutralListener onManageClickListener = new DialogNeutralListener() {
        public void onClick() {
            PaymentConfirmation.this.managePayment();
        }
    };
    @Inject
    Lazy<PaymentAnalyticsHelper> paymentAnalyticsHelper;
    private MfpPaymentResult paymentResult = null;
    @BindView(2131363276)
    TextView planDuration;
    @BindView(2131363450)
    TextView renewalDate;
    @BindView(2131363717)
    View standardPaymentConfirmationContainer;
    @Inject
    Lazy<SubscriptionService> subscriptionService;
    @BindView(2131364074)
    View unableToPostText;
    private boolean unableToPostToServer;

    public static Intent newStartIntent(Context context, MfpPaymentResult mfpPaymentResult, boolean z) {
        return newStartIntent(context, mfpPaymentResult, false, z, 30);
    }

    public static Intent newStartIntent(Context context, MfpPaymentResult mfpPaymentResult, boolean z, boolean z2, int i) {
        return new Intent(context, PaymentConfirmation.class).putExtra(Extras.PAYMENT_RESULT, mfpPaymentResult).putExtra(Extras.UNABLE_TO_POST_TO_SERVER, z).putExtra("free_trial_enabled", z2).putExtra("free_trial_duration", i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.payment_confirmation);
        component().inject(this);
        setupListeners();
        setupText();
        this.paymentResult = (MfpPaymentResult) ExtrasUtils.getParcelable(getIntent(), Extras.PAYMENT_RESULT, MfpPaymentResult.class.getClassLoader());
        this.unableToPostToServer = ExtrasUtils.getBoolean(getIntent(), Extras.UNABLE_TO_POST_TO_SERVER);
        if (bundle != null) {
            return;
        }
        if (!areFreeTrialsEnabled()) {
            showManageDialog();
        } else {
            ((PaymentAnalyticsHelper) this.paymentAnalyticsHelper.get()).reportFreeTrialActivatedScreenViewed();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        refresh();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(str, MANAGE_DIALOG_TAG)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setNeutralListener(this.onManageClickListener);
        return true;
    }

    private boolean areFreeTrialsEnabled() {
        return ExtrasUtils.getBoolean(getIntent(), "free_trial_enabled");
    }

    private void setupListeners() {
        this.doneButton.setOnClickListener(this.onDoneClickListener);
        this.notNowView.setOnClickListener(this.onDoneClickListener);
        this.explorePremiumButton.setOnClickListener(this.onExplorePremiumClickListener);
    }

    private void setupText() {
        this.headerText.setText(R.string.payment_confirm_header);
        setFreeTrialConfirmationText();
    }

    private void setFreeTrialConfirmationText() {
        String str;
        String string = getString(R.string.free_trial_confirmation_info_body);
        if (ExtrasUtils.getInt(getIntent(), "free_trial_duration") != 0) {
            str = getString(R.string.free_trial_confirmation_info_title, new Object[]{Integer.valueOf(ExtrasUtils.getInt(getIntent(), "free_trial_duration"))});
        } else {
            str = getString(R.string.free_trial_confirmation_info_title, new Object[]{Integer.valueOf(30)});
        }
        String string2 = getString(R.string.google_play_settings);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        int indexOf = string.indexOf(string2);
        if (indexOf != -1) {
            spannableStringBuilder.setSpan(new BlueClickableSpanNoUnderline(this) {
                public void onClick(View view) {
                    ((PaymentAnalyticsHelper) PaymentConfirmation.this.paymentAnalyticsHelper.get()).reportManageSubscriptionClickedFromFreeTrialActivatedScreen();
                    PaymentConfirmation.this.managePayment();
                }
            }, indexOf, string2.length() + indexOf, 33);
        }
        this.confirmationInfoBody.setMovementMethod(LinkMovementMethod.getInstance());
        this.confirmationInfoBody.setText(spannableStringBuilder);
        this.confirmationInfoTitle.setText(str);
    }

    private void refresh() {
        setBusy(true);
        ((SubscriptionService) this.subscriptionService.get()).findByProductId(this.paymentResult.getProduct().getProductId(), new Function1<List<MfpPaidSubscription>>() {
            public void execute(List<MfpPaidSubscription> list) throws RuntimeException {
                PaymentConfirmation paymentConfirmation = PaymentConfirmation.this;
                paymentConfirmation.rebindUi(paymentConfirmation.getMostRecentlySubscribed(list));
                PaymentConfirmation.this.setBusy(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void rebindUi(MfpPaidSubscription mfpPaidSubscription) {
        boolean areFreeTrialsEnabled = areFreeTrialsEnabled();
        ViewUtils.setVisible(areFreeTrialsEnabled, this.freeTrialPaymentConfirmationContainer);
        ViewUtils.setVisible(!areFreeTrialsEnabled, this.standardPaymentConfirmationContainer);
        ViewUtils.setVisible(!areFreeTrialsEnabled, this.doneButton);
        toggleUnableToPostTextVisibility(this.unableToPostToServer);
        if (mfpPaidSubscription != null) {
            this.planDuration.setText(String.format("%s, %s", new Object[]{ProductUtils.formatSubscriptionDuration((Context) this, mfpPaidSubscription), mfpPaidSubscription.getPaymentDetails().getPricePoint().getDisplayPrice()}));
            Date parse = DateTimeUtils.parse(DATE_PARSE_PATTERN, mfpPaidSubscription.getSubscriptionEndDate());
            this.renewalDate.setText(DateFormat.getDateInstance(1).format(parse));
            this.emailAddress.setText(getSession().getUser().getEmail());
        }
    }

    private void toggleUnableToPostTextVisibility(boolean z) {
        ViewUtils.setVisible(z, this.unableToPostText);
        ViewUtils.setVisible(!z, this.infoDetailsContainer);
    }

    private void showManageDialog() {
        ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.payment_confirm_dialog_title)).setMessage((int) R.string.payment_confirm_dialog_body)).setPositiveText(R.string.ok, null)).setNeutralText(R.string.payment_confirm_dialog_button_manage, this.onManageClickListener)).show(getSupportFragmentManager(), MANAGE_DIALOG_TAG);
    }

    /* access modifiers changed from: private */
    public void managePayment() {
        PaymentUtils.managePayment(this, this.paymentResult.getProvider(), this.paymentResult.getProduct().getProductId());
    }

    /* access modifiers changed from: private */
    public MfpPaidSubscription getMostRecentlySubscribed(List<MfpPaidSubscription> list) {
        List list2 = (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) $$Lambda$PaymentConfirmation$ATN64Ttcs73eIepv7qGtuRRA0.INSTANCE);
        if (!CollectionUtils.notEmpty((Collection<?>) list2)) {
            return null;
        }
        Collections.sort(list2, $$Lambda$PaymentConfirmation$e7oUx6zAbEdg8QWo11oY1vnaZw.INSTANCE);
        return (MfpPaidSubscription) list2.get(0);
    }

    static /* synthetic */ Boolean lambda$getMostRecentlySubscribed$0(MfpPaidSubscription mfpPaidSubscription) throws RuntimeException {
        return Boolean.valueOf(mfpPaidSubscription.getSubscriptionStatus() == SubscriptionStatus.ACTIVE);
    }
}
