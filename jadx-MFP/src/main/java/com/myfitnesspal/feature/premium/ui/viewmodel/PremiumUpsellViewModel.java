package com.myfitnesspal.feature.premium.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellCoordinator;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/myfitnesspal/feature/premium/ui/viewmodel/PremiumUpsellViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "coordinator", "Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellCoordinator;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellCoordinator;)V", "displayMode", "Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "getDisplayMode", "()Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;", "setDisplayMode", "(Lcom/myfitnesspal/shared/constants/Constants$UpsellDisplayMode;)V", "promotedFeature", "", "getPromotedFeature", "()Ljava/lang/String;", "setPromotedFeature", "(Ljava/lang/String;)V", "showPremiumUpsell", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumUpsellViewModel.kt */
public final class PremiumUpsellViewModel extends AndroidViewModel {
    private final PremiumUpsellCoordinator coordinator;
    @NotNull
    private UpsellDisplayMode displayMode = UpsellDisplayMode.Normal;
    @Nullable
    private String promotedFeature;

    public PremiumUpsellViewModel(@NotNull Application application, @NotNull PremiumUpsellCoordinator premiumUpsellCoordinator) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(premiumUpsellCoordinator, "coordinator");
        super(application);
        this.coordinator = premiumUpsellCoordinator;
    }

    @NotNull
    public final UpsellDisplayMode getDisplayMode() {
        return this.displayMode;
    }

    public final void setDisplayMode(@NotNull UpsellDisplayMode upsellDisplayMode) {
        Intrinsics.checkParameterIsNotNull(upsellDisplayMode, "<set-?>");
        this.displayMode = upsellDisplayMode;
    }

    @Nullable
    public final String getPromotedFeature() {
        return this.promotedFeature;
    }

    public final void setPromotedFeature(@Nullable String str) {
        this.promotedFeature = str;
    }

    public final void showPremiumUpsell() {
        this.coordinator.showPremiumUpsell(this.promotedFeature, this.displayMode);
    }
}
