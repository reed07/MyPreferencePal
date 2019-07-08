package com.myfitnesspal.feature.premium.ui.navigation;

import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0010\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellCoordinator;", "", "navigator", "Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellNavigator;", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "(Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellNavigator;Lcom/myfitnesspal/shared/service/config/ConfigService;Lcom/myfitnesspal/feature/premium/service/PremiumService;)V", "showFeaturesUpsell", "", "promotedFeature", "", "displayMode", "Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "showNormalUpsell", "showPremiumUpsell", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumUpsellCoordinator.kt */
public final class PremiumUpsellCoordinator {
    private final ConfigService configService;
    private final PremiumUpsellNavigator navigator;
    private final PremiumService premiumService;

    public PremiumUpsellCoordinator(@NotNull PremiumUpsellNavigator premiumUpsellNavigator, @NotNull ConfigService configService2, @NotNull PremiumService premiumService2) {
        Intrinsics.checkParameterIsNotNull(premiumUpsellNavigator, "navigator");
        Intrinsics.checkParameterIsNotNull(configService2, "configService");
        Intrinsics.checkParameterIsNotNull(premiumService2, "premiumService");
        this.navigator = premiumUpsellNavigator;
        this.configService = configService2;
        this.premiumService = premiumService2;
    }

    public final void showPremiumUpsell(@Nullable String str, @NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, "displayMode");
        switch (upsellDisplayMode) {
            case FeatureScreen:
                this.navigator.showNativeUpsell(str, upsellDisplayMode);
                return;
            case Normal:
            case SignUp:
                showNormalUpsell(str, upsellDisplayMode);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r0.equals("price") != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_BENEFIT_PRICE) != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_BENEFIT) != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        r2.navigator.showBenefitUpsell(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_FEATURES) != false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005c, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_BENEFIT_FEATURES) != false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005e, code lost:
        showFeaturesUpsell(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0068, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_FEATURES_PRICE) != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        r2.navigator.showPriceUpsell(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_PRICE_BENEFIT) != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_FEATURES_BENEFIT) != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations.VARIANT_PRICE_FEATURES) != false) goto L_0x005e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void showNormalUpsell(java.lang.String r3, com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode r4) {
        /*
            r2 = this;
            com.myfitnesspal.shared.service.config.ConfigService r0 = r2.configService
            java.lang.String r0 = com.myfitnesspal.shared.util.ConfigUtils.getPremiumUpsellScreenType(r0)
            if (r0 != 0) goto L_0x000a
            goto L_0x0070
        L_0x000a:
            int r1 = r0.hashCode()
            switch(r1) {
                case -1916135737: goto L_0x0062;
                case -1135306011: goto L_0x0056;
                case -290659267: goto L_0x004d;
                case -222710633: goto L_0x003f;
                case 32226209: goto L_0x0036;
                case 106934601: goto L_0x002d;
                case 414893683: goto L_0x0024;
                case 1226879637: goto L_0x001b;
                case 1324069793: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0070
        L_0x0012:
            java.lang.String r1 = "price_benefit"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
            goto L_0x0047
        L_0x001b:
            java.lang.String r1 = "features_benefit"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
            goto L_0x0047
        L_0x0024:
            java.lang.String r1 = "price_features"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
            goto L_0x005e
        L_0x002d:
            java.lang.String r1 = "price"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
            goto L_0x006a
        L_0x0036:
            java.lang.String r1 = "benefit_price"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
            goto L_0x006a
        L_0x003f:
            java.lang.String r1 = "benefit"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
        L_0x0047:
            com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigator r0 = r2.navigator
            r0.showBenefitUpsell(r3, r4)
            goto L_0x0073
        L_0x004d:
            java.lang.String r1 = "features"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
            goto L_0x005e
        L_0x0056:
            java.lang.String r1 = "benefit_features"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
        L_0x005e:
            r2.showFeaturesUpsell(r3, r4)
            goto L_0x0073
        L_0x0062:
            java.lang.String r1 = "features_price"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0070
        L_0x006a:
            com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigator r0 = r2.navigator
            r0.showPriceUpsell(r3, r4)
            goto L_0x0073
        L_0x0070:
            r2.showFeaturesUpsell(r3, r4)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellCoordinator.showNormalUpsell(java.lang.String, com.myfitnesspal.shared.constants.Constants$UpsellDisplayMode):void");
    }

    private final void showFeaturesUpsell(String str, UpsellDisplayMode upsellDisplayMode) {
        if (ConfigUtils.isPremiumUpsellWebViewEnabledAndPremiumNotSubscribed(this.configService, this.premiumService)) {
            this.navigator.showWebUpsell(str, upsellDisplayMode);
        } else {
            this.navigator.showNativeUpsell(str, upsellDisplayMode);
        }
    }
}
