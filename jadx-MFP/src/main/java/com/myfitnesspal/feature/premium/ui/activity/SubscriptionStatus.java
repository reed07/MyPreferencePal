package com.myfitnesspal.feature.premium.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.premium.ui.fragment.SubscriptionStatusFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import javax.inject.Inject;

public class SubscriptionStatus extends MfpActivity {
    @Inject
    Lazy<PaymentAnalyticsHelper> paymentAnalyticsHelper;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, SubscriptionStatus.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.subscription_status_activity);
        if (bundle == null) {
            ((PaymentAnalyticsHelper) this.paymentAnalyticsHelper.get()).reportPremiumSettingsScreenViewed();
            getSupportFragmentManager().beginTransaction().add((int) R.id.subscription_status_fragment, (Fragment) SubscriptionStatusFragment.newInstance()).commit();
        }
    }
}
