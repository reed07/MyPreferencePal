package com.myfitnesspal.feature.onboarding.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import com.myfitnesspal.feature.onboarding.navigation.PremiumOnboardingFlowCoordinator;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "coordinator", "Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;", "analyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelper;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/onboarding/navigation/PremiumOnboardingFlowCoordinator;Ldagger/Lazy;)V", "create", "T", "Landroid/arch/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroid/arch/lifecycle/ViewModel;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingViewModelFactory.kt */
public final class PremiumOnboardingViewModelFactory implements Factory {
    private final Lazy<PremiumOnboardingAnalyticsHelper> analyticsHelper;
    private final Application application;
    private final PremiumOnboardingFlowCoordinator coordinator;

    public PremiumOnboardingViewModelFactory(@NotNull Application application2, @NotNull PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator, @NotNull Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        Intrinsics.checkParameterIsNotNull(premiumOnboardingFlowCoordinator, "coordinator");
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsHelper");
        this.application = application2;
        this.coordinator = premiumOnboardingFlowCoordinator;
        this.analyticsHelper = lazy;
    }

    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> cls) {
        T t;
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        if (Intrinsics.areEqual((Object) cls, (Object) PremiumOnboardingWelcomeViewModel.class)) {
            t = (AndroidViewModel) new PremiumOnboardingWelcomeViewModel(this.application, this.coordinator, this.analyticsHelper);
        } else if (Intrinsics.areEqual((Object) cls, (Object) PremiumOnboardingFlowViewModel.class)) {
            t = (AndroidViewModel) new PremiumOnboardingFlowViewModel(this.application, this.coordinator, this.analyticsHelper);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("No ViewModel registered for ");
            sb.append(cls);
            throw new IllegalArgumentException(sb.toString());
        }
        return (ViewModel) t;
    }
}
