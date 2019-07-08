package com.myfitnesspal.feature.premium.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPlatformDetails.PlatformName;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.payments.util.PaymentUtils;
import com.myfitnesspal.feature.premium.util.ProductUtils;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class SubscriptionStatusFragment extends MfpFragment {
    private static final String API_ERROR_DIALOG_TAG = "SubscriptionStatusFragment.ApiErrorDialog";
    private static final String NO_SUBSCRIPTIONS_DIALOG_TAG = "SubscriptionStatusFragment.NoSubscriptionsDialog";
    private static final String TAG = "SubscriptionStatusFragment";
    /* access modifiers changed from: private */
    public MfpPaidSubscription activeSubscription;
    @BindView(2131362542)
    View externalManagePanel;
    @BindView(2131362543)
    TextView externalManageText;
    @BindView(2131363098)
    View nativeManagePanel;
    private DialogNegativeListener onApiErrorNegativeClicked = new DialogNegativeListener() {
        public void onClick() {
            SubscriptionStatusFragment.this.getActivity().finish();
        }
    };
    private DialogPositiveListener onApiErrorPositiveClicked = new DialogPositiveListener() {
        public void onClick(Object obj) {
            SubscriptionStatusFragment.this.requery();
        }
    };
    private OnClickListener onManageClicked = new OnClickListener() {
        public void onClick(View view) {
            ((PaymentAnalyticsHelper) SubscriptionStatusFragment.this.paymentAnalyticsHelper.get()).reportManageSubscriptionClickedFromSubscriptionSettings();
            PaymentUtils.managePayment(SubscriptionStatusFragment.this.getActivity(), (String) SubscriptionStatusFragment.this.activeSubscription.getPaymentDetails().getPlatformDetails().getPaymentProviders().get(0), SubscriptionStatusFragment.this.activeSubscription.getPaymentDetails().getProductId());
        }
    };
    private DialogPositiveListener onNoSubsPositiveClicked = new DialogPositiveListener() {
        public void onClick(Object obj) {
            SubscriptionStatusFragment.this.getActivity().finish();
        }
    };
    @Inject
    Lazy<PaymentAnalyticsHelper> paymentAnalyticsHelper;
    @BindView(2131363251)
    TextView paymentPlatform;
    @BindView(2131362251)
    TextView planValue;
    @Inject
    SubscriptionService subscriptionService;

    public static SubscriptionStatusFragment newInstance() {
        return new SubscriptionStatusFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.subscription_status_fragment, null);
        ButterKnife.bind((Object) this, inflate);
        component().inject(this);
        ViewUtils.setVisible(false, this.nativeManagePanel);
        ViewUtils.setVisible(false, this.externalManagePanel);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        requery();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (super.onRebindDialogFragment(dialogFragment, str)) {
            return true;
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        if (NO_SUBSCRIPTIONS_DIALOG_TAG.equals(str)) {
            alertDialogFragment.setPositiveListener(this.onNoSubsPositiveClicked);
            return true;
        } else if (!API_ERROR_DIALOG_TAG.equals(str)) {
            return false;
        } else {
            alertDialogFragment.setPositiveListener(this.onApiErrorPositiveClicked);
            alertDialogFragment.setNegativeListener(this.onApiErrorNegativeClicked);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void requery() {
        setBusy(true);
        this.subscriptionService.syncWithBackend(new Function1<List<MfpPaidSubscription>>() {
            public void execute(List<MfpPaidSubscription> list) throws RuntimeException {
                if (SubscriptionStatusFragment.this.isEnabled()) {
                    SubscriptionStatusFragment.this.setBusy(false);
                    SubscriptionStatusFragment.this.activeSubscription = ProductUtils.getMostRecentActiveSubscription(list);
                    SubscriptionStatusFragment.this.rebindUi();
                    if (SubscriptionStatusFragment.this.activeSubscription == null) {
                        SubscriptionStatusFragment.this.showNoSubscriptionsDialog();
                    }
                }
            }
        }, new Function1<List<ApiException>>() {
            public void execute(List<ApiException> list) throws RuntimeException {
                if (SubscriptionStatusFragment.this.isEnabled()) {
                    SubscriptionStatusFragment.this.setBusy(false);
                    SubscriptionStatusFragment.this.showErrorDialog(CollectionUtils.notEmpty((Collection<?>) list) ? (ApiException) list.get(0) : null);
                    SubscriptionStatusFragment.this.activeSubscription = null;
                    SubscriptionStatusFragment.this.rebindUi();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void rebindUi() {
        MfpPaidSubscription mfpPaidSubscription = this.activeSubscription;
        if (mfpPaidSubscription != null) {
            this.nativeManagePanel.setOnClickListener(this.onManageClicked);
            this.planValue.setText(ProductUtils.formatSubscriptionDuration((Context) getActivity(), mfpPaidSubscription));
            PlatformName platformName = mfpPaidSubscription.getPaymentDetails().getPlatformDetails().getPlatformName();
            boolean z = platformName == PlatformName.ANDROID;
            ViewUtils.setVisible(z, this.nativeManagePanel);
            ViewUtils.setVisible(!z, this.externalManagePanel);
            if (z) {
                return;
            }
            if (platformName == PlatformName.IOS) {
                this.paymentPlatform.setText(R.string.billing_information_payment_platform_ios);
                this.externalManageText.setText(R.string.billing_information_manage_subscription_ios);
                return;
            }
            this.paymentPlatform.setText(R.string.billing_information_payment_platform_web);
            this.externalManageText.setText(R.string.billing_information_manage_subscription_web);
        }
    }

    /* access modifiers changed from: private */
    public void showNoSubscriptionsDialog() {
        if (getActivity().getSupportFragmentManager().findFragmentByTag(NO_SUBSCRIPTIONS_DIALOG_TAG) == null) {
            AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.billing_information_no_subs_body)).setTitle(R.string.billing_information_no_subs_title)).setPositiveText(R.string.ok, this.onNoSubsPositiveClicked);
            alertDialogFragment.setCancelable(false);
            showDialogFragment(alertDialogFragment, NO_SUBSCRIPTIONS_DIALOG_TAG);
        }
    }

    /* access modifiers changed from: private */
    public void showErrorDialog(ApiException apiException) {
        if (getActivity().getSupportFragmentManager().findFragmentByTag(API_ERROR_DIALOG_TAG) == null) {
            String string = getString(R.string.billing_information_no_subs_body);
            if (!(apiException == null || apiException.getApiError() == null || !Strings.notEmpty(apiException.getApiError().getErrorDescription()))) {
                string = apiException.getApiError().getErrorDescription();
            }
            AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage(string)).setTitle(R.string.billing_information_no_subs_title)).setPositiveText(R.string.retry, this.onApiErrorPositiveClicked)).setNegativeText(R.string.close, this.onApiErrorNegativeClicked);
            alertDialogFragment.setCancelable(false);
            showDialogFragment(alertDialogFragment, API_ERROR_DIALOG_TAG);
        }
    }
}
