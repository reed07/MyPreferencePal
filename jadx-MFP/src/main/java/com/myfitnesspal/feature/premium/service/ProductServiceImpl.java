package com.myfitnesspal.feature.premium.service;

import android.content.SharedPreferences;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpProduct.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.premium.util.PremiumUpsellUrl;
import com.myfitnesspal.feature.premium.util.PremiumUpsellUtils;
import com.myfitnesspal.feature.premium.util.ProductServiceCache;
import com.myfitnesspal.feature.premium.util.RemoteLoadMonitor;
import com.myfitnesspal.feature.premium.util.RemoteLoadMonitor.Loader;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellSkus;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumFreeTrialContinuingUsers;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.TestMode;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class ProductServiceImpl extends SimpleAsyncServiceBase implements ProductService {
    private static final String FILENAME_UPSELL_WEBVIEW = "premium_upsell_cache.html";
    private static final String LEGACY_FILENAME_UPSELL_WEBVIEW = "premium_upsell_cache";
    private final Provider<MfpV2Api> api;
    private final MfpApiSettings apiSettings;
    private final Lazy<ConfigService> configService;
    private final Lazy<GeoLocationService> geoLocationService;
    private final Lazy<LocalSettingsService> localSettingsService;
    /* access modifiers changed from: private */
    public final Lazy<PremiumService> premiumService;
    /* access modifiers changed from: private */
    public final ProductServiceCache productServiceCache;
    /* access modifiers changed from: private */
    public final RemoteLoadMonitor remote = new RemoteLoadMonitor(new Loader() {
        public void load() {
            ProductServiceImpl.this.getProducts(null);
        }
    });
    private final Lazy<Session> session;
    private final Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "ProductServiceImpl";
    }

    @Inject
    public ProductServiceImpl(SharedPreferences sharedPreferences, Lazy<PremiumService> lazy, Lazy<GeoLocationService> lazy2, Provider<MfpV2Api> provider, Lazy<Session> lazy3, Lazy<ConfigService> lazy4, MfpApiSettings mfpApiSettings, Lazy<LocalSettingsService> lazy5, Lazy<UserApplicationSettingsService> lazy6) {
        this.api = provider;
        this.premiumService = lazy;
        this.productServiceCache = new ProductServiceCache(sharedPreferences);
        this.geoLocationService = lazy2;
        this.session = lazy3;
        this.configService = lazy4;
        this.apiSettings = mfpApiSettings;
        this.localSettingsService = lazy5;
        this.userApplicationSettingsService = lazy6;
    }

    /* access modifiers changed from: private */
    public String getLocale() {
        return ((GeoLocationService) this.geoLocationService.get()).getLocaleCode();
    }

    public List<MfpProduct> refreshProductsIfCacheExpired() throws ApiException {
        if (!this.productServiceCache.expired()) {
            return this.productServiceCache.getProducts(getLocale());
        }
        return refreshProducts();
    }

    public void getProducts(final Function1<List<MfpProduct>> function1) {
        if (authInfoAvailable() || TestMode.INSTANCE.enabled()) {
            async(new Runnable() {
                public void run() {
                    boolean z;
                    String access$000 = ProductServiceImpl.this.getLocale();
                    List products = ProductServiceImpl.this.productServiceCache.getProducts(access$000);
                    if (!TestMode.INSTANCE.enabled() && CollectionUtils.isEmpty((Collection<?>) products)) {
                        products = ProductServiceImpl.this.productServiceCache.readCacheFromDisk(access$000);
                    }
                    if (products != null) {
                        ProductServiceImpl.this.invokeOnMainThread(function1, products);
                        z = true;
                    } else {
                        z = false;
                    }
                    if (ProductServiceImpl.this.productServiceCache.expired() || products == null || !ProductServiceImpl.this.remote.isLoadingOrLoaded()) {
                        try {
                            List access$400 = ProductServiceImpl.this.refreshProducts();
                            ((PremiumService) ProductServiceImpl.this.premiumService.get()).invalidateAvailability();
                            if (!z) {
                                ProductServiceImpl.this.invokeOnMainThread(function1, access$400);
                            }
                        } catch (ApiException unused) {
                            if (!z) {
                                ProductServiceImpl.this.invokeOnMainThread(function1, null);
                            }
                        }
                    }
                }
            });
        } else {
            invokeOnMainThread(function1, null);
        }
    }

    public boolean isFeatureInCatalog(String str) {
        boolean z = false;
        if (!authInfoAvailable()) {
            return false;
        }
        this.remote.loadOnce();
        Map features = this.productServiceCache.getFeatures(getLocale());
        if (features != null && features.containsKey(str)) {
            z = true;
        }
        return z;
    }

    public String getUpsellWebViewCacheFilename() {
        StringBuilder sb = new StringBuilder();
        sb.append(MyFitnessPalApp.getInstance().getCacheDir().getPath());
        sb.append("/");
        sb.append(FILENAME_UPSELL_WEBVIEW);
        return sb.toString();
    }

    public void preCacheUpsellWebViewHtml(List<MfpProduct> list) {
        final String upsellWebViewCacheFilename = getUpsellWebViewCacheFilename();
        Map aBTestProperties = ((ConfigService) this.configService.get()).getABTestProperties(PremiumUpsellUtils.getCurrentUpsellSkuRolloutName(this, (ConfigService) this.configService.get(), (LocalSettingsService) this.localSettingsService.get()));
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PremiumUpsellUtils.getPremiumUpsellProducts(list, aBTestProperties, this, (ConfigService) this.configService.get()));
        new OkHttpClient().newCall(new Builder().url(PremiumUpsellUrl.getPreCacheUrl(this.configService, this.apiSettings, ((Session) this.session.get()).getUser().getUserId(), arrayList, areFreeTrialsEnabled())).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                Ln.e(iOException);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    File file = new File(upsellWebViewCacheFilename);
                    if (file.exists()) {
                        file.delete();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(response.body().bytes());
                    fileOutputStream.close();
                }
                if (response.body() != null) {
                    response.body().close();
                }
            }
        });
    }

    public void setFreeTrialFlagAndCacheWebView(boolean z, List<MfpProduct> list) {
        if (getFreeTrialsFlag() != z) {
            setFreeTrialsFlag(z);
            if (z) {
                ((LocalSettingsService) this.localSettingsService.get()).setPremiumAdDisplayed(false);
            }
        }
        deleteCachedUpsellWebView();
        deleteDeprecatedUpsellCacheWebView();
        preCacheUpsellWebViewHtml(list);
    }

    public boolean areFreeTrialsEnabled() {
        return getFreeTrialsFlag();
    }

    private void setFreeTrialsFlag(boolean z) {
        ((LocalSettingsService) this.localSettingsService.get()).setFreeTrialsEnabled(z);
    }

    private boolean getFreeTrialsFlag() {
        return ((LocalSettingsService) this.localSettingsService.get()).areFreeTrialsEnabled();
    }

    /* access modifiers changed from: private */
    public List<MfpProduct> refreshProducts() throws ApiException {
        this.remote.onStarted();
        try {
            List<MfpProduct> items = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.NEW_PRODUCT_CATALOG)).getItems();
            this.productServiceCache.update(items, getLocale());
            checkForFreeTrialProducts(items);
            this.remote.onFinished(true);
            return items;
        } catch (Throwable th) {
            this.remote.onFinished(false);
            throw th;
        }
    }

    private void checkForFreeTrialProducts(List<MfpProduct> list) {
        boolean z;
        boolean z2;
        boolean z3 = false;
        if (ConfigUtils.isPremiumTrialForNewUsersEnabled((ConfigService) this.configService.get()) || ConfigUtils.isPremiumTrialForReactivatingUsersEnabled((ConfigService) this.configService.get())) {
            z = PremiumUpsellUtils.hasProductsFromRollout(list, UpsellSkus.NAME_FREE_TRIAL, (ConfigService) this.configService.get());
            if (z) {
                ((LocalSettingsService) this.localSettingsService.get()).setTrialRolloutName(UpsellSkus.NAME_FREE_TRIAL);
            }
        } else {
            z = false;
        }
        if (ConfigUtils.isPremiumTrialForContinuingUsersEnabled((ConfigService) this.configService.get())) {
            z2 = PremiumUpsellUtils.hasProductsFromRollout(list, PremiumFreeTrialContinuingUsers.NAME, (ConfigService) this.configService.get());
            if (z2) {
                ((LocalSettingsService) this.localSettingsService.get()).setTrialRolloutName(PremiumFreeTrialContinuingUsers.NAME);
            }
        } else {
            z2 = false;
        }
        if (z || z2) {
            z3 = true;
        }
        setFreeTrialFlagAndCacheWebView(z3, list);
    }

    private void deleteCachedUpsellWebView() {
        File file = new File(getUpsellWebViewCacheFilename());
        if (file.exists()) {
            file.delete();
        }
    }

    private void deleteDeprecatedUpsellCacheWebView() {
        File file = new File(MyFitnessPalApp.getInstance().getCacheDir().getPath(), LEGACY_FILENAME_UPSELL_WEBVIEW);
        if (file.exists()) {
            file.delete();
        }
    }

    private boolean authInfoAvailable() {
        return TestMode.INSTANCE.enabled() || ((Session) this.session.get()).getUser().isLoggedIn();
    }
}
