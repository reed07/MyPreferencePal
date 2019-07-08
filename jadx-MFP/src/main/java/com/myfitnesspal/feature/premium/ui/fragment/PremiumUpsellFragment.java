package com.myfitnesspal.feature.premium.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.myfitnesspal.feature.payments.util.GooglePlayUtil;
import com.myfitnesspal.feature.premium.model.MfpUpsellConfig;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.feature.premium.util.PremiumUpsellUtils;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumFeatureListClickEffect;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public abstract class PremiumUpsellFragment extends MfpFragment {
    private static final int ACTION_BAR_CLOSE = 100;
    protected static final String EXTRA_ANALYTICS_REPORTED = "PremiumUpsellFragment.ANALYTICS_REPORTED";
    @Inject
    Lazy<AnalyticsService> analytics;
    protected boolean analyticsReported;
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    @Inject
    Lazy<ConfigService> configService;
    protected ServiceConnection connection;
    protected boolean destroyed = false;
    protected UpsellDisplayMode displayMode;
    protected Map<String, String> displayPrices;
    @Inject
    Lazy<GeoLocationService> geoLocationService;
    private boolean hasShownTouchAnimationOnce = false;
    @Inject
    Lazy<LocalSettingsService> localSettings;
    protected final DialogNegativeListener onConnectionFailedSkipClickListener = new DialogNegativeListener() {
        public void onClick() {
            PremiumUpsellFragment.this.finishAndSlideOut();
        }
    };
    @Inject
    Lazy<PaymentAnalyticsHelper> paymentAnalytics;
    @Inject
    Lazy<PaymentService> paymentService;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<ProductService> productService;
    protected List<MfpProduct> products;
    protected String promotedFeature;
    protected MfpUpsellConfig upsellConfig;
    @Inject
    Lazy<UpsellService> upsellService;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    /* access modifiers changed from: protected */
    public abstract void onPricesLoaded();

    /* access modifiers changed from: protected */
    public abstract void onProductsLoaded();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.promotedFeature = BundleUtils.getString(getArguments(), Extras.EXTRA_PROMOTED_FEATURE);
    }

    public void onPause() {
        super.onPause();
        ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellClosed((MfpProduct) Enumerable.firstOrDefault(this.products), this.promotedFeature);
    }

    public void onDestroy() {
        super.onDestroy();
        disconnectPlayService();
        this.destroyed = true;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
        if (this.displayMode == UpsellDisplayMode.SignUp) {
            MenuItem add = menu.add(0, 100, 0, R.string.skip);
            MenuItemCompat.setActionView(add, (int) R.layout.prominent_action_item);
            MenuItemCompat.setShowAsAction(add, 2);
            TextView textView = (TextView) MenuItemCompat.getActionView(menu.findItem(100));
            textView.setText(R.string.skip);
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PremiumUpsellFragment.this.finishAndSlideOut();
                }
            });
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_ANALYTICS_REPORTED, this.analyticsReported);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 140 && i2 == -1) {
            FragmentActivity activity = getActivity();
            activity.setResult(-1);
            activity.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void configureActionBarForDisplayMode() {
        setTitle(R.string.go_premium, new Object[0]);
        setHasOptionsMenu(true);
        if (this.displayMode == UpsellDisplayMode.FeatureScreen) {
            setTitle(R.string.premium_features, new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void bindHeaderTextView(TextView textView, String str, int i, int i2) {
        textView.setText(str);
        textView.setTextColor(i);
        setTextSize(textView, i2);
    }

    /* access modifiers changed from: protected */
    public void configureGroupViewForDisplayType(ViewGroup viewGroup) {
        if (this.displayMode == UpsellDisplayMode.FeatureScreen) {
            viewGroup.setPadding(0, 0, 0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void loadPrices() {
        disconnectPlayService();
        if (getActivity() != null) {
            Intent intent = new Intent(GooglePlayConstants.GOOGLE_BILLING_BIND_INTENT);
            intent.setPackage("com.android.vending");
            this.connection = new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    if (!PremiumUpsellFragment.this.destroyed) {
                        PremiumUpsellFragment premiumUpsellFragment = PremiumUpsellFragment.this;
                        premiumUpsellFragment.displayPrices = GooglePlayUtil.getDisplayPriceForSkus(premiumUpsellFragment.getActivity().getApplicationContext(), Stub.asInterface(iBinder), PremiumUpsellFragment.extractSkuList(PremiumUpsellFragment.this.products));
                        PremiumUpsellFragment.this.onPricesLoaded();
                    }
                    PremiumUpsellFragment.this.disconnectPlayService();
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    PremiumUpsellFragment.this.disconnectPlayService();
                }
            };
            if (!getActivity().getApplicationContext().bindService(intent, this.connection, 1)) {
                this.displayPrices = new HashMap();
                onPricesLoaded();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void loadProducts(final Map<String, String> map) {
        ((ProductService) this.productService.get()).getProducts(new Function1<List<MfpProduct>>() {
            public void execute(List<MfpProduct> list) {
                ArrayList arrayList = new ArrayList();
                if (CollectionUtils.notEmpty((Collection<?>) list)) {
                    if (PremiumUpsellFragment.this.displayMode == UpsellDisplayMode.FeatureScreen) {
                        arrayList.addAll(list);
                    } else {
                        arrayList.addAll(PremiumUpsellUtils.getPremiumUpsellProducts(list, map, (ProductService) PremiumUpsellFragment.this.productService.get(), PremiumUpsellFragment.this.getConfigService()));
                    }
                }
                PremiumUpsellFragment.this.products = arrayList;
                if (arrayList.size() == 0) {
                    ((PaymentAnalyticsHelper) PremiumUpsellFragment.this.paymentAnalytics.get()).reportNoProductsFromMfp(PremiumUpsellFragment.this.promotedFeature);
                }
                PremiumUpsellFragment.this.onProductsLoaded();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void disconnectPlayService() {
        if (this.connection != null && getActivity() != null) {
            getActivity().getApplicationContext().unbindService(this.connection);
            this.connection = null;
        }
    }

    /* access modifiers changed from: protected */
    public void showTouchAnimation(final ViewGroup viewGroup) {
        if (getConfigService().isVariantEnabled(PremiumFeatureListClickEffect.NAME) && this.displayMode == UpsellDisplayMode.FeatureScreen && !this.hasShownTouchAnimationOnce && !((LocalSettingsService) this.localSettings.get()).hasClickedOnPremiumFeature() && viewGroup.getChildCount() != 0) {
            viewGroup.postDelayed(new Runnable() {
                public void run() {
                    if (PremiumUpsellFragment.this.hasResumed()) {
                        final Drawable background = viewGroup.getChildAt(0).getBackground();
                        if (background != null) {
                            background.setState(new int[]{16842919, 16842910});
                            viewGroup.postDelayed(new Runnable() {
                                public void run() {
                                    if (PremiumUpsellFragment.this.hasResumed()) {
                                        background.setState(new int[0]);
                                    }
                                }
                            }, 500);
                        }
                    }
                }
            }, 500);
            this.hasShownTouchAnimationOnce = true;
        }
    }

    /* access modifiers changed from: protected */
    public void setTextSize(TextView textView, int i) {
        textView.setTextSize(2, (float) i);
    }

    /* access modifiers changed from: protected */
    public void reportShowUpsellAnalytics(List<MfpProduct> list) {
        if (!this.analyticsReported) {
            ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellViewed((MfpProduct) Enumerable.firstOrDefault(list), this.promotedFeature);
            this.analyticsReported = true;
        }
    }

    /* access modifiers changed from: protected */
    public void reportBuyButtonClickedAnalytics(MfpProduct mfpProduct) {
        ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellBuyButtonPress(mfpProduct, this.promotedFeature);
    }

    /* access modifiers changed from: protected */
    public void reportProductServiceErrorAnalytics() {
        ((PaymentAnalyticsHelper) this.paymentAnalytics.get()).reportUpsellLoadFailure();
    }

    /* access modifiers changed from: protected */
    public void finishAndSlideOut() {
        getActivity().finish();
        getActivity().overridePendingTransition(0, R.anim.slide_out_bottom_100_short);
    }

    /* access modifiers changed from: private */
    public static ArrayList<String> extractSkuList(List<MfpProduct> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (MfpProduct productId : list) {
            arrayList.add(productId.getProductId());
        }
        return arrayList;
    }
}
