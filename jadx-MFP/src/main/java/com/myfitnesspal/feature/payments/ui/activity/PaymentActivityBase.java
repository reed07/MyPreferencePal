package com.myfitnesspal.feature.payments.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.feature.onboarding.ui.activity.OnboardingActivity;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.DisableReceiptPostToServer;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Payments;
import com.myfitnesspal.shared.constants.Constants.Payments.GenericError;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.premium.PaymentsLogger;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import dagger.Lazy;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public abstract class PaymentActivityBase extends MfpActivity {
    private static final String EXTRA_ANALYTICS_POSTED = "PaymentActivityBase.analytics_posted";
    private static final String EXTRA_BACKEND_SYNC_COMPLETED = "PaymentActivityBase.backend_sync_completed";
    private static final String EXTRA_ERROR_CODE = "PaymentActivityBase.error_code";
    public static final String EXTRA_FREE_TRIAL_DURATION_DAYS = "free_trial_duration";
    public static final String EXTRA_FREE_TRIAL_ENABLED = "free_trial_enabled";
    private static final String EXTRA_PAYMENT_RESULT = "PaymentActivityBase.payment_result";
    private static final String GENERAL_ERROR_DIALOG_TAG = "PaymentActivityBase.GeneralPaymentErrorDialog";
    private static final String POTENTIALLY_CHARGED_ERROR_DIALOG_TAG = "PaymentActivityBase.PossiblyChargedPaymentErrorDialog";
    private static final String TAG = "PaymentActivityBase";
    private boolean analyticsPosted;
    private boolean backendSyncCompleted;
    private boolean finished;
    private GenericError genericError = GenericError.NO_ERROR;
    @Inject
    Lazy<GeoLocationService> geoLocationService;
    private DialogPositiveListener<String> onDialogOkListener = new DialogPositiveListener<String>() {
        public void onClick(String str) {
            PaymentActivityBase.this.finishWithError();
        }
    };
    private DialogNegativeListener onDialogPotentiallyChargedLearnMoreListener = new DialogNegativeListener() {
        public void onClick() {
            NavigationHelper navigationHelper = PaymentActivityBase.this.getNavigationHelper();
            PaymentActivityBase paymentActivityBase = PaymentActivityBase.this;
            navigationHelper.withIntent(Faq.newStartIntent(paymentActivityBase, 108, paymentActivityBase.getString(R.string.faq))).startActivity();
            PaymentActivityBase.this.writeResultAndFinish();
        }
    };
    @Inject
    Lazy<PaymentAnalyticsHelper> paymentAnalytics;
    private MfpPaymentResult paymentResult;
    @Inject
    Lazy<PaymentService> paymentService;
    private String promotedFeature;
    private boolean showErrorOnResume;
    /* access modifiers changed from: private */
    public final Function2<MfpPaymentResult, List<ApiException>> subscribeFailure = new Function2<MfpPaymentResult, List<ApiException>>() {
        public void execute(MfpPaymentResult mfpPaymentResult, List<ApiException> list) throws RuntimeException {
            PaymentsLogger.e("[%s] SubscriptionService FAILED for productId=%s", PaymentActivityBase.TAG, mfpPaymentResult.getProduct().getProductId());
            PaymentActivityBase.this.finishWithSuccess(mfpPaymentResult, true);
        }
    };
    private final Function1<MfpPaymentResult> subscribeSuccess = new Function1<MfpPaymentResult>() {
        public void execute(MfpPaymentResult mfpPaymentResult) throws RuntimeException {
            PaymentsLogger.d("[%s] SubscriptionService successful for productId=%s", PaymentActivityBase.TAG, mfpPaymentResult.getProduct().getProductId());
            PaymentActivityBase.this.finishWithSuccess(mfpPaymentResult, false);
        }
    };
    @Inject
    Lazy<SubscriptionService> subscriptionService;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setResult(0);
        this.promotedFeature = ExtrasUtils.getString(getIntent(), Extras.EXTRA_PROMOTED_FEATURE);
        if (bundle != null) {
            this.backendSyncCompleted = bundle.getBoolean(EXTRA_BACKEND_SYNC_COMPLETED, false);
            this.analyticsPosted = bundle.getBoolean(EXTRA_ANALYTICS_POSTED, false);
            this.paymentResult = (MfpPaymentResult) BundleUtils.getParcelable(bundle, EXTRA_PAYMENT_RESULT, MfpPaymentResult.class.getClassLoader());
            this.genericError = GenericError.fromErrorCode(bundle.getInt(EXTRA_ERROR_CODE, this.genericError.getErrorCode()));
            reinstallFragmentDialogListeners();
        }
        MfpPaymentResult mfpPaymentResult = this.paymentResult;
        if (mfpPaymentResult != null && !this.backendSyncCompleted) {
            setPaymentSuccess(mfpPaymentResult);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.showErrorOnResume) {
            showErrorDialog(this.genericError);
            this.showErrorOnResume = false;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_BACKEND_SYNC_COMPLETED, this.backendSyncCompleted);
        bundle.putBoolean(EXTRA_ANALYTICS_POSTED, this.analyticsPosted);
        bundle.putInt(EXTRA_ERROR_CODE, this.genericError.getErrorCode());
        MfpPaymentResult mfpPaymentResult = this.paymentResult;
        if (mfpPaymentResult != null) {
            bundle.putParcelable(EXTRA_PAYMENT_RESULT, mfpPaymentResult);
        }
    }

    public final MfpProduct getProduct() {
        return (MfpProduct) getIntent().getParcelableExtra(Payments.Extras.PRODUCT);
    }

    public void finish() {
        this.finished = true;
        super.finish();
    }

    /* access modifiers changed from: protected */
    public final GeoLocationService getGeoLocationService() {
        return (GeoLocationService) this.geoLocationService.get();
    }

    /* access modifiers changed from: protected */
    public void setPaymentSuccess(final MfpPaymentResult mfpPaymentResult) {
        this.paymentResult = mfpPaymentResult;
        checkReportPaymentSuccess();
        if (this.finished) {
            PaymentsLogger.d("[%s] setPaymentSuccess: called, but already finished", TAG);
        } else if (getConfigService().isVariantEnabled(DisableReceiptPostToServer.NAME)) {
            ((SubscriptionService) this.subscriptionService.get()).addIncompleteReceipt(mfpPaymentResult, new Function0() {
                public void execute() {
                    Toast.makeText(PaymentActivityBase.this, "SIMULATED BACKEND FAILURE FOR TESTING!", 1).show();
                    FunctionUtils.invokeIfValid(PaymentActivityBase.this.subscribeFailure, mfpPaymentResult, null);
                }
            });
        } else {
            PaymentsLogger.d("[%s] setPaymentSuccess: recording receipt with PurchaseService...", TAG);
            ((SubscriptionService) this.subscriptionService.get()).processReceipt(mfpPaymentResult, this.subscribeSuccess, this.subscribeFailure);
        }
    }

    /* access modifiers changed from: protected */
    public void setPaymentFailed(GenericError genericError2) {
        if (!this.finished) {
            PaymentsLogger.e("[%s] setPaymentFailed: using code=%d", TAG, Integer.valueOf(genericError2.getErrorCode()));
            this.genericError = genericError2;
            if (genericError2 == GenericError.NO_ERROR || genericError2 == GenericError.USER_CANCELED) {
                finishWithError();
            } else {
                showErrorDialog(genericError2);
            }
        } else {
            PaymentsLogger.e("[%s] setPaymentFailed, but already finished. code=%d", TAG, Integer.valueOf(genericError2.getErrorCode()));
        }
    }

    private void showErrorDialog(GenericError genericError2) {
        AlertDialogFragment alertDialogFragment;
        String str;
        if (hasResumed()) {
            this.genericError = genericError2;
            if (genericError2 == GenericError.POTENTIALLY_CHARGED) {
                ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportPotentiallyChargedFailure();
                AlertDialogFragment alertDialogFragment2 = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.payment_error_dialog_title)).setMessage((int) R.string.payment_error_dialog_message_potentially_charged)).setPositiveText(R.string.ok, this.onDialogOkListener)).setNegativeText(R.string.learn_more, this.onDialogPotentiallyChargedLearnMoreListener);
                str = POTENTIALLY_CHARGED_ERROR_DIALOG_TAG;
                alertDialogFragment = alertDialogFragment2;
            } else {
                str = GENERAL_ERROR_DIALOG_TAG;
                alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.payment_error_dialog_title)).setMessage(getErrorString(genericError2))).setPositiveText(R.string.ok, this.onDialogOkListener);
            }
            alertDialogFragment.setCancelable(false);
            alertDialogFragment.show(getSupportFragmentManager(), str);
            return;
        }
        this.showErrorOnResume = true;
    }

    private void reinstallFragmentDialogListeners() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) getSupportFragmentManager().findFragmentByTag(GENERAL_ERROR_DIALOG_TAG);
        if (alertDialogFragment != null) {
            alertDialogFragment.setPositiveListener(this.onDialogOkListener);
        }
        AlertDialogFragment alertDialogFragment2 = (AlertDialogFragment) getSupportFragmentManager().findFragmentByTag(POTENTIALLY_CHARGED_ERROR_DIALOG_TAG);
        if (alertDialogFragment2 != null) {
            alertDialogFragment2.setPositiveListener(this.onDialogOkListener);
            alertDialogFragment2.setNegativeListener(this.onDialogPotentiallyChargedLearnMoreListener);
        }
    }

    /* access modifiers changed from: private */
    public void finishWithError() {
        PaymentsLogger.e("[%s] finishWithError: code=", TAG, Integer.valueOf(this.genericError.getErrorCode()));
        this.backendSyncCompleted = true;
        this.paymentResult = null;
        writeResultAndFinish();
    }

    /* access modifiers changed from: private */
    public void finishWithSuccess(MfpPaymentResult mfpPaymentResult, boolean z) {
        Intent intent;
        PaymentsLogger.d("[%s] finishWithSuccess", TAG);
        this.backendSyncCompleted = true;
        this.paymentResult = mfpPaymentResult;
        this.genericError = GenericError.NO_ERROR;
        writeResultAndFinish();
        if (ConfigUtils.isPremiumOnboardingEnabled(getConfigService())) {
            intent = OnboardingActivity.newStartIntent(this);
        } else {
            intent = PaymentConfirmation.newStartIntent(this, mfpPaymentResult, z, ExtrasUtils.getBoolean(getIntent(), EXTRA_FREE_TRIAL_ENABLED), ExtrasUtils.getInt(getIntent(), "free_trial_duration"));
        }
        getNavigationHelper().withIntent(intent).startActivity();
    }

    /* access modifiers changed from: private */
    public void writeResultAndFinish() {
        int i = this.genericError == GenericError.NO_ERROR ? -1 : 0;
        Intent intent = new Intent();
        intent.putExtra(Payments.Extras.PAYMENT_ERROR, this.genericError.getErrorCode());
        intent.putExtra(Payments.Extras.PAYMENT_RESULT, this.paymentResult);
        setResult(i, intent);
        checkReportPaymentError();
        finish();
    }

    private void checkReportPaymentSuccess() {
        if (!this.analyticsPosted) {
            ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportPaymentSuccess(getProduct(), this.promotedFeature);
            this.analyticsPosted = true;
        }
    }

    private void checkReportPaymentError() {
        if (!this.analyticsPosted) {
            if (!(this.genericError == GenericError.USER_CANCELED || this.genericError == GenericError.NO_ERROR)) {
                ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportPaymentFailure(String.format(Locale.ENGLISH, "%s [code=%d]", new Object[]{getErrorString(this.genericError), Integer.valueOf(this.genericError.getErrorCode())}));
            }
            this.analyticsPosted = true;
        }
    }

    private String getErrorString(GenericError genericError2) {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.locale;
        try {
            configuration.locale = Locale.ENGLISH;
            resources.updateConfiguration(configuration, null);
            String string = resources.getString(this.genericError.getErrorMessageResourceId(), new Object[]{Integer.valueOf(genericError2.getErrorCode())});
            configuration.locale = locale;
            resources.updateConfiguration(configuration, null);
            return string;
        } catch (Exception unused) {
            String str = "";
            configuration.locale = locale;
            resources.updateConfiguration(configuration, null);
            return str;
        }
    }
}
