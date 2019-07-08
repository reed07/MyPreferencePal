package com.myfitnesspal.feature.premium.service;

import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.payments.util.GooglePlayUtil;
import com.myfitnesspal.feature.payments.util.GooglePlayUtil.SkuQueryResult;
import com.myfitnesspal.feature.premium.event.PremiumEvents.ProductAvailabilityUpdated;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides.OverrideState;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.squareup.otto.Bus;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PremiumServiceImpl implements PremiumService {
    private static final boolean ALL_FEATURE_ROLLOUTS_ENABLED = BuildConfiguration.getBuildConfiguration().isDebug();
    private BuildConfiguration buildConfig = new BuildConfiguration();
    private final Lazy<Bus> bus;
    private final Lazy<ConfigService> config;
    private final Lazy<PremiumFeatureOverrides> featureOverrides;
    private final Lazy<GlobalSettingsService> globalSettings;
    private final Lazy<ProductService> productService;
    /* access modifiers changed from: private */
    public AtomicBoolean skuLookupRunning = new AtomicBoolean(false);
    private AtomicReference<PlaySkuState> skuState = new AtomicReference<>(PlaySkuState.Unknown);
    private final Lazy<SubscriptionService> subscriptionService;

    /* renamed from: com.myfitnesspal.feature.premium.service.PremiumServiceImpl$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult = new int[SkuQueryResult.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.myfitnesspal.feature.payments.util.GooglePlayUtil$SkuQueryResult[] r0 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.SkuQueryResult.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult = r0
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.myfitnesspal.feature.payments.util.GooglePlayUtil$SkuQueryResult r1 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.SkuQueryResult.ServiceDisconnected     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult     // Catch:{ NoSuchFieldError -> 0x001f }
                com.myfitnesspal.feature.payments.util.GooglePlayUtil$SkuQueryResult r1 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.SkuQueryResult.ServiceNotAvailable     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult     // Catch:{ NoSuchFieldError -> 0x002a }
                com.myfitnesspal.feature.payments.util.GooglePlayUtil$SkuQueryResult r1 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.SkuQueryResult.Available     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.myfitnesspal.feature.payments.util.GooglePlayUtil$SkuQueryResult r1 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.SkuQueryResult.Unavailabile     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.premium.service.PremiumServiceImpl.AnonymousClass2.<clinit>():void");
        }
    }

    private enum PlaySkuState {
        Unknown,
        Available,
        Unavailable;

        static PlaySkuState from(String str) {
            PlaySkuState[] values;
            for (PlaySkuState playSkuState : values()) {
                if (playSkuState.toString().equals(str)) {
                    return playSkuState;
                }
            }
            return Unknown;
        }
    }

    public PremiumServiceImpl(Lazy<ConfigService> lazy, Lazy<SubscriptionService> lazy2, Lazy<ProductService> lazy3, Lazy<PremiumFeatureOverrides> lazy4, Lazy<GlobalSettingsService> lazy5, Lazy<Bus> lazy6) {
        this.config = lazy;
        this.subscriptionService = lazy2;
        this.productService = lazy3;
        this.featureOverrides = lazy4;
        this.globalSettings = lazy5;
        this.bus = lazy6;
    }

    public boolean isPremiumAvailable() {
        return isPremiumSubscribed() || isPremiumAvailableGeographically();
    }

    public boolean isPremiumSubscribed() {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        if ((buildConfiguration.isDebug() || buildConfiguration.isQABuild()) && ((PremiumFeatureOverrides) this.featureOverrides.get()).areOverridesEnabled()) {
            for (PremiumFeature overrideFor : PremiumFeature.values()) {
                OverrideState overrideFor2 = ((PremiumFeatureOverrides) this.featureOverrides.get()).getOverrideFor(overrideFor);
                if (overrideFor2 != null && overrideFor2.isSubscribed()) {
                    return true;
                }
            }
        }
        return ((SubscriptionService) this.subscriptionService.get()).isUserSubscribed();
    }

    public void invalidateAvailability() {
        setSkuState(PlaySkuState.Unknown);
        this.skuLookupRunning.set(false);
    }

    public boolean isPremiumAvailableGeographically() {
        boolean z = true;
        if (this.buildConfig.isDebug()) {
            return true;
        }
        if (this.skuState.get() == PlaySkuState.Unknown && !this.skuLookupRunning.get()) {
            this.skuLookupRunning.set(true);
            ((ProductService) this.productService.get()).getProducts(new Function1<List<MfpProduct>>() {
                public void execute(List<MfpProduct> list) {
                    if (list == null) {
                        PremiumServiceImpl.this.setSkuState(PlaySkuState.Unknown);
                        PremiumServiceImpl.this.skuLookupRunning.set(false);
                        return;
                    }
                    GooglePlayUtil.skusAreAvailable(list, new Function1<SkuQueryResult>() {
                        public void execute(SkuQueryResult skuQueryResult) {
                            switch (AnonymousClass2.$SwitchMap$com$myfitnesspal$feature$payments$util$GooglePlayUtil$SkuQueryResult[skuQueryResult.ordinal()]) {
                                case 1:
                                    PremiumServiceImpl.this.setSkuState(PlaySkuState.Unknown);
                                    break;
                                case 2:
                                    PremiumServiceImpl.this.setSkuState(PlaySkuState.Unavailable);
                                    break;
                                case 3:
                                    PremiumServiceImpl.this.setSkuState(PlaySkuState.Available);
                                    break;
                                case 4:
                                    PremiumServiceImpl.this.setSkuState(PlaySkuState.Unavailable);
                                    break;
                            }
                            PremiumServiceImpl.this.skuLookupRunning.set(false);
                        }
                    });
                }
            });
        }
        PlaySkuState playSkuState = (PlaySkuState) this.skuState.get();
        if (playSkuState == PlaySkuState.Unknown) {
            playSkuState = PlaySkuState.from(((GlobalSettingsService) this.globalSettings.get()).getLastPremiumAvailability(PlaySkuState.Unknown.toString()));
        }
        if (playSkuState == PlaySkuState.Unavailable) {
            z = false;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.feature.premium.service.PremiumService.Availability getFeatureAvailability(com.myfitnesspal.feature.premium.service.PremiumFeature r7) {
        /*
            r6 = this;
            boolean r0 = r6.isPremiumAvailable()
            if (r0 != 0) goto L_0x0009
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r7 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Hidden
            return r7
        L_0x0009:
            dagger.Lazy<com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides> r0 = r6.featureOverrides
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides r0 = (com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides) r0
            boolean r0 = r0.areOverridesEnabled()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0036
            dagger.Lazy<com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides> r0 = r6.featureOverrides
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides r0 = (com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides) r0
            com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides$OverrideState r0 = r0.getOverrideFor(r7)
            if (r0 == 0) goto L_0x0036
            boolean r3 = r0.isAvailable()
            boolean r4 = r0.isEnabled()
            boolean r0 = r0.isSubscribed()
            r5 = r0
            r0 = 1
            goto L_0x003a
        L_0x0036:
            r0 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x003a:
            if (r0 != 0) goto L_0x0068
            boolean r4 = r6.isFeatureEnabledByRollout(r7)
            dagger.Lazy<com.myfitnesspal.feature.payments.service.SubscriptionService> r0 = r6.subscriptionService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.payments.service.SubscriptionService r0 = (com.myfitnesspal.feature.payments.service.SubscriptionService) r0
            java.lang.String r3 = r7.getFeatureId()
            boolean r5 = r0.isUserSubscribedToFeature(r3)
            if (r5 != 0) goto L_0x0067
            dagger.Lazy<com.myfitnesspal.feature.premium.service.ProductService> r0 = r6.productService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.premium.service.ProductService r0 = (com.myfitnesspal.feature.premium.service.ProductService) r0
            java.lang.String r7 = r7.getFeatureId()
            boolean r7 = r0.isFeatureInCatalog(r7)
            if (r7 == 0) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r3 = 0
            goto L_0x0068
        L_0x0067:
            r3 = 1
        L_0x0068:
            if (r3 == 0) goto L_0x0071
            if (r4 == 0) goto L_0x0071
            if (r5 == 0) goto L_0x0071
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r7 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Available
            return r7
        L_0x0071:
            if (r3 == 0) goto L_0x0077
            if (r4 != 0) goto L_0x0077
            if (r5 != 0) goto L_0x0083
        L_0x0077:
            if (r3 != 0) goto L_0x007d
            if (r4 == 0) goto L_0x007d
            if (r5 != 0) goto L_0x0083
        L_0x007d:
            if (r3 != 0) goto L_0x0086
            if (r4 != 0) goto L_0x0086
            if (r5 == 0) goto L_0x0086
        L_0x0083:
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r7 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Revoked
            return r7
        L_0x0086:
            if (r3 == 0) goto L_0x008f
            if (r4 == 0) goto L_0x008f
            if (r5 != 0) goto L_0x008f
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r7 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Locked
            return r7
        L_0x008f:
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r7 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Hidden
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.premium.service.PremiumServiceImpl.getFeatureAvailability(com.myfitnesspal.feature.premium.service.PremiumFeature):com.myfitnesspal.feature.premium.service.PremiumService$Availability");
    }

    public boolean isFeatureAvailable(PremiumFeature premiumFeature) {
        Availability featureAvailability = getFeatureAvailability(premiumFeature);
        return featureAvailability == Availability.Available || featureAvailability == Availability.Locked;
    }

    public boolean isFeatureSubscribed(PremiumFeature premiumFeature) {
        return getFeatureAvailability(premiumFeature) == Availability.Available;
    }

    private boolean isFeatureEnabledByRollout(PremiumFeature premiumFeature) {
        if (ALL_FEATURE_ROLLOUTS_ENABLED) {
            return true;
        }
        return Strings.equals(((ConfigService) this.config.get()).getVariant(premiumFeature.getRolloutId()), "on");
    }

    /* access modifiers changed from: private */
    public void setSkuState(PlaySkuState playSkuState) {
        if (playSkuState != this.skuState.get()) {
            this.skuState.set(playSkuState);
            ((Bus) this.bus.get()).post(new ProductAvailabilityUpdated());
            if (playSkuState != PlaySkuState.Unknown) {
                ((GlobalSettingsService) this.globalSettings.get()).setLastPremiumAvailability(playSkuState.toString());
            }
        }
    }
}
