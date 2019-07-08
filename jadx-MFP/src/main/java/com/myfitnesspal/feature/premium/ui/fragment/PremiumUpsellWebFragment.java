package com.myfitnesspal.feature.premium.ui.fragment;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.util.PremiumUpsellUrl;
import com.myfitnesspal.feature.premium.util.PremiumUpsellUtils;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.view.MfpWebView;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Strings;
import java.io.File;
import java.util.Collection;
import javax.inject.Inject;

public class PremiumUpsellWebFragment extends PremiumUpsellFragment {
    private static final String CACHE_FILE_PREFIX = "file://";
    private static final boolean ENABLE_MOCK_PRICES = false;
    private static final String PURCHASE_URL_PREFIX = "mfp://mfp/premium/purchase";
    @Inject
    MfpApiSettings apiSettings;
    /* access modifiers changed from: private */
    public boolean loadError = false;
    /* access modifiers changed from: private */
    public MfpProduct monthlyProduct;
    private long startLoadTime;
    @BindView(2131364204)
    MfpWebView webView;
    /* access modifiers changed from: private */
    public MfpProduct yearlyProduct;

    public static PremiumUpsellWebFragment newInstance(String str, UpsellDisplayMode upsellDisplayMode) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.EXTRA_PROMOTED_FEATURE, str);
        bundle.putSerializable("PremiumUpsellFragment.DISPLAY_MODE", upsellDisplayMode);
        PremiumUpsellWebFragment premiumUpsellWebFragment = new PremiumUpsellWebFragment();
        premiumUpsellWebFragment.setArguments(bundle);
        return premiumUpsellWebFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.startLoadTime = System.currentTimeMillis();
        return layoutInflater.inflate(R.layout.premium_upsell_webview, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        loadProducts(((ConfigService) this.configService.get()).getABTestProperties(PremiumUpsellUtils.getCurrentUpsellSkuRolloutName((ProductService) this.productService.get(), (ConfigService) this.configService.get(), (LocalSettingsService) this.localSettings.get())));
        this.webView.getSettings().setJavaScriptEnabled(true);
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        if ((buildConfiguration.isDebug() || buildConfiguration.isQABuild()) && VERSION.SDK_INT >= 19) {
            MfpWebView mfpWebView = this.webView;
            MfpWebView.setWebContentsDebuggingEnabled(true);
        }
        this.webView.setWebViewClient(new MfpWebViewClient(getActivity()) {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                ((PaymentAnalyticsHelper) PremiumUpsellWebFragment.this.paymentAnalytics.get()).reportUpsellWebViewLoadStart((MfpProduct) Enumerable.firstOrDefault(PremiumUpsellWebFragment.this.products), PremiumUpsellWebFragment.this.promotedFeature);
            }

            public void onPageFinished(WebView webView, String str) {
                if (!PremiumUpsellWebFragment.this.loadError) {
                    PremiumUpsellWebFragment.this.reportLoadedAnalytics(Strings.notEmpty(str) && str.startsWith(PremiumUpsellWebFragment.CACHE_FILE_PREFIX));
                }
                PremiumUpsellWebFragment.this.loadError = false;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                PremiumUpsellWebFragment.this.reportFailedAnalytics();
                PremiumUpsellWebFragment.this.loadError = true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:11:0x003a  */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x00a2 A[RETURN] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean shouldOverrideUrlLoading(android.webkit.WebView r4, java.lang.String r5) {
                /*
                    r3 = this;
                    java.lang.String r4 = "mfp://mfp/premium/purchase"
                    boolean r4 = com.uacf.core.util.Strings.startsWith(r5, r4)
                    if (r4 == 0) goto L_0x0036
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r4 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    com.myfitnesspal.feature.payments.model.MfpProduct r4 = r4.monthlyProduct
                    java.lang.String r4 = r4.getProductId()
                    boolean r4 = r5.contains(r4)
                    if (r4 == 0) goto L_0x001f
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r4 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    com.myfitnesspal.feature.payments.model.MfpProduct r4 = r4.monthlyProduct
                    goto L_0x0037
                L_0x001f:
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r4 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    com.myfitnesspal.feature.payments.model.MfpProduct r4 = r4.yearlyProduct
                    java.lang.String r4 = r4.getProductId()
                    boolean r4 = r5.contains(r4)
                    if (r4 == 0) goto L_0x0036
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r4 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    com.myfitnesspal.feature.payments.model.MfpProduct r4 = r4.yearlyProduct
                    goto L_0x0037
                L_0x0036:
                    r4 = 0
                L_0x0037:
                    r5 = 0
                    if (r4 == 0) goto L_0x00a2
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r0 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    r0.reportBuyButtonClickedAnalytics(r4)
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r0 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    boolean r0 = r0.isTrialDurationDaysAvailable(r4)
                    if (r0 == 0) goto L_0x0053
                    com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails r5 = r4.getSubscriptionDetails()
                    com.myfitnesspal.feature.payments.model.MfpTrialDetails r5 = r5.getTrialDetails()
                    int r5 = r5.getDurationInterval()
                L_0x0053:
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r0 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.getNavigationHelper()
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r1 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.fromFragment(r1)
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r1 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    dagger.Lazy r1 = r1.paymentService
                    java.lang.Object r1 = r1.get()
                    com.myfitnesspal.feature.payments.service.PaymentService r1 = (com.myfitnesspal.feature.payments.service.PaymentService) r1
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r2 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    android.support.v4.app.FragmentActivity r2 = r2.getActivity()
                    android.content.Intent r4 = r1.getStartIntent(r2, r4)
                    com.myfitnesspal.shared.ui.navigation.NavigationHelper r4 = r0.withIntent(r4)
                    java.lang.String r0 = "promoted_feature"
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r1 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    java.lang.String r1 = r1.promotedFeature
                    com.myfitnesspal.shared.ui.navigation.NavigationHelper r4 = r4.withExtra(r0, r1)
                    java.lang.String r0 = "free_trial_enabled"
                    com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment r1 = com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.this
                    dagger.Lazy r1 = r1.productService
                    java.lang.Object r1 = r1.get()
                    com.myfitnesspal.feature.premium.service.ProductService r1 = (com.myfitnesspal.feature.premium.service.ProductService) r1
                    boolean r1 = r1.areFreeTrialsEnabled()
                    com.myfitnesspal.shared.ui.navigation.NavigationHelper r4 = r4.withExtra(r0, r1)
                    java.lang.String r0 = "free_trial_duration"
                    com.myfitnesspal.shared.ui.navigation.NavigationHelper r4 = r4.withExtra(r0, r5)
                    r5 = 140(0x8c, float:1.96E-43)
                    r4.startActivity(r5)
                    r4 = 1
                    return r4
                L_0x00a2:
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment.AnonymousClass1.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.webView.setWebViewClient(null);
    }

    public void onProductsLoaded() {
        loadPrices();
        checkLoadWebView();
    }

    /* access modifiers changed from: protected */
    public void onPricesLoaded() {
        if (CollectionUtils.size((Collection<?>) this.products) > 1) {
            if (yearly((MfpProduct) this.products.get(0))) {
                this.yearlyProduct = (MfpProduct) this.products.get(0);
                this.monthlyProduct = (MfpProduct) this.products.get(1);
            } else {
                this.yearlyProduct = (MfpProduct) this.products.get(1);
                this.monthlyProduct = (MfpProduct) this.products.get(0);
            }
            this.startLoadTime = System.currentTimeMillis();
            checkLoadWebView();
        }
        checkLoadWebView();
    }

    private void checkLoadWebView() {
        if (this.monthlyProduct != null && this.yearlyProduct != null && this.displayPrices != null) {
            if (new File(((ProductService) this.productService.get()).getUpsellWebViewCacheFilename()).exists()) {
                String paramsForCachedUrl = PremiumUpsellUrl.getParamsForCachedUrl(getConfigService(), this.products, this.displayPrices, getSession().getUser().getUserId(), ((ProductService) this.productService.get()).areFreeTrialsEnabled());
                MfpWebView mfpWebView = this.webView;
                StringBuilder sb = new StringBuilder();
                sb.append(CACHE_FILE_PREFIX);
                sb.append(((ProductService) this.productService.get()).getUpsellWebViewCacheFilename());
                sb.append(paramsForCachedUrl);
                mfpWebView.loadUrl(sb.toString());
                return;
            }
            ((ProductService) this.productService.get()).preCacheUpsellWebViewHtml(this.products);
            this.webView.loadUrl(PremiumUpsellUrl.getLiveUrl(this.configService, this.apiSettings, getSession().getUser().getUserId(), this.products, this.displayPrices, ((ProductService) this.productService.get()).areFreeTrialsEnabled()));
        }
    }

    /* access modifiers changed from: private */
    public void reportFailedAnalytics() {
        ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellWebViewLoadFail((MfpProduct) Enumerable.firstOrDefault(this.products), this.promotedFeature);
    }

    /* access modifiers changed from: private */
    public void reportLoadedAnalytics(boolean z) {
        ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellWebViewLoadFinish((MfpProduct) Enumerable.firstOrDefault(this.products), this.promotedFeature);
        ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellWebViewLoadDuration((MfpProduct) Enumerable.firstOrDefault(this.products), this.promotedFeature, z, System.currentTimeMillis() - this.startLoadTime);
        reportShowUpsellAnalytics(this.products);
    }

    private static boolean yearly(MfpProduct mfpProduct) {
        return Strings.equals((Object) mfpProduct.getSubscriptionDetails().getFrequencyUnit(), (Object) FrequencyUnit.YEAR);
    }

    /* access modifiers changed from: private */
    public boolean isTrialDurationDaysAvailable(MfpProduct mfpProduct) {
        return (mfpProduct.getSubscriptionDetails() == null || mfpProduct.getSubscriptionDetails().getTrialDetails() == null || mfpProduct.getSubscriptionDetails().getTrialDetails().getDurationInterval() == 0) ? false : true;
    }
}
