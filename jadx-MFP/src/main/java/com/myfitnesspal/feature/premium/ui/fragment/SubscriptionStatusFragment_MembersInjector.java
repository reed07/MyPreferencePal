package com.myfitnesspal.feature.premium.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SubscriptionStatusFragment_MembersInjector implements MembersInjector<SubscriptionStatusFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PaymentAnalyticsHelper> paymentAnalyticsHelperProvider;
    private final Provider<SubscriptionService> subscriptionServiceProvider;

    public SubscriptionStatusFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PaymentAnalyticsHelper> provider3, Provider<SubscriptionService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.paymentAnalyticsHelperProvider = provider3;
        this.subscriptionServiceProvider = provider4;
    }

    public static MembersInjector<SubscriptionStatusFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PaymentAnalyticsHelper> provider3, Provider<SubscriptionService> provider4) {
        return new SubscriptionStatusFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(SubscriptionStatusFragment subscriptionStatusFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(subscriptionStatusFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(subscriptionStatusFragment, (Glide) this.glideProvider.get());
        injectPaymentAnalyticsHelper(subscriptionStatusFragment, DoubleCheck.lazy(this.paymentAnalyticsHelperProvider));
        injectSubscriptionService(subscriptionStatusFragment, (SubscriptionService) this.subscriptionServiceProvider.get());
    }

    public static void injectPaymentAnalyticsHelper(SubscriptionStatusFragment subscriptionStatusFragment, Lazy<PaymentAnalyticsHelper> lazy) {
        subscriptionStatusFragment.paymentAnalyticsHelper = lazy;
    }

    public static void injectSubscriptionService(SubscriptionStatusFragment subscriptionStatusFragment, SubscriptionService subscriptionService) {
        subscriptionStatusFragment.subscriptionService = subscriptionService;
    }
}
