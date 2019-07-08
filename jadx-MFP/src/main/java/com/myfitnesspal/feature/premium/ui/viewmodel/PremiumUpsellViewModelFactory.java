package com.myfitnesspal.feature.premium.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellCoordinator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/premium/ui/viewmodel/PremiumUpsellViewModelFactory;", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "coordinator", "Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellCoordinator;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/premium/ui/navigation/PremiumUpsellCoordinator;)V", "create", "T", "Landroid/arch/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroid/arch/lifecycle/ViewModel;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumUpsellViewModelFactory.kt */
public final class PremiumUpsellViewModelFactory implements Factory {
    private final Application application;
    private final PremiumUpsellCoordinator coordinator;

    public PremiumUpsellViewModelFactory(@NotNull Application application2, @NotNull PremiumUpsellCoordinator premiumUpsellCoordinator) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        Intrinsics.checkParameterIsNotNull(premiumUpsellCoordinator, "coordinator");
        this.application = application2;
        this.coordinator = premiumUpsellCoordinator;
    }

    public <T extends ViewModel> T create(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        if (Intrinsics.areEqual((Object) cls, (Object) PremiumUpsellViewModel.class)) {
            return (ViewModel) new PremiumUpsellViewModel(this.application, this.coordinator);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No ViewModel registered for ");
        sb.append(cls);
        throw new IllegalArgumentException(sb.toString());
    }
}
