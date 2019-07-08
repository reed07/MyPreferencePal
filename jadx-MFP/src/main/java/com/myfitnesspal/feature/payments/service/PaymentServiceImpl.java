package com.myfitnesspal.feature.payments.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.payments.model.MfpAvailabilityDetails;
import com.myfitnesspal.feature.payments.model.MfpPlatformDetails;
import com.myfitnesspal.feature.payments.model.MfpPlatformDetails.PlatformName;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity;
import com.myfitnesspal.feature.payments.ui.activity.MockPaymentActivity;
import com.myfitnesspal.shared.constants.Constants.Payments.Extras;
import com.myfitnesspal.shared.constants.Constants.Payments.Providers;
import com.myfitnesspal.shared.service.premium.PaymentsLogger;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentServiceImpl extends SimpleAsyncServiceBase implements PaymentService {
    private static final String GOOGLE_BILLING_PACKAGE_NAME = "com.android.vending";
    private static final int MAX_THREADS = 1;
    private static final boolean MOCK_ALL_PAYMENTS_WHEN_MOCK_PAYMENTS_ENABLED = false;
    private static final boolean MOCK_PAYMENTS_ENABLED = BuildConfiguration.getBuildConfiguration().isDebug();
    private static final String TAG = "PaymentServiceImpl";
    private Context context;
    private boolean googleBillingAvailable = isGoogleBillingAvailable();
    private boolean mockPaymentsEnabled = isMockPaymentsEnabled();

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public PaymentServiceImpl(Context context2) {
        this.context = context2;
    }

    public boolean isPaymentProviderAvailable(String str) {
        if ("google".equals(str)) {
            return this.googleBillingAvailable;
        }
        if (Providers.MOCK.equals(str)) {
            return this.mockPaymentsEnabled;
        }
        return false;
    }

    public Intent getStartIntent(Context context2, MfpProduct mfpProduct) {
        Intent intent;
        List extractPaymentProviders = extractPaymentProviders(mfpProduct);
        String str = "";
        boolean z = this.mockPaymentsEnabled;
        if (this.googleBillingAvailable && extractPaymentProviders.contains("google")) {
            intent = new Intent(context2, GooglePlayPaymentActivity.class);
            str = "google";
        } else if (isMockPaymentsEnabled()) {
            intent = new Intent(context2, MockPaymentActivity.class);
            str = Providers.MOCK;
        } else {
            intent = null;
        }
        if (intent != null) {
            intent.putExtra(Extras.PRODUCT, mfpProduct);
            intent.putExtra(Extras.PROVIDER, str);
        }
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isMockPaymentsEnabled() {
        return MOCK_PAYMENTS_ENABLED;
    }

    /* access modifiers changed from: protected */
    public boolean isGoogleBillingAvailable() {
        try {
            List<ApplicationInfo> installedApplications = this.context.getPackageManager().getInstalledApplications(0);
            if (installedApplications != null) {
                for (ApplicationInfo applicationInfo : installedApplications) {
                    if (Strings.equals(applicationInfo.packageName, "com.android.vending")) {
                        PaymentsLogger.e("google play service is present on this device!", new Object[0]);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            PaymentsLogger.e("unable to get package listing -- too many apps installed? %s", e);
        }
        PaymentsLogger.e("google play service is not on this device!", new Object[0]);
        return false;
    }

    private static List<String> extractPaymentProviders(MfpProduct mfpProduct) {
        ArrayList arrayList = new ArrayList();
        for (MfpAvailabilityDetails availablePlatforms : mfpProduct.getAvailabilityDetails()) {
            Iterator it = availablePlatforms.getAvailablePlatforms().iterator();
            while (it.hasNext()) {
                MfpPlatformDetails mfpPlatformDetails = (MfpPlatformDetails) it.next();
                if (mfpPlatformDetails.getPlatformName() == PlatformName.ANDROID) {
                    arrayList.addAll(mfpPlatformDetails.getPaymentProviders());
                }
            }
        }
        return arrayList;
    }
}
