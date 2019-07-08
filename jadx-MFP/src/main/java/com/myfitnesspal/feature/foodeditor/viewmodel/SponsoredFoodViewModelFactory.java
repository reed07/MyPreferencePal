package com.myfitnesspal.feature.foodeditor.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.NewInstanceFactory;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/foodeditor/viewmodel/SponsoredFoodViewModelFactory;", "Landroid/arch/lifecycle/ViewModelProvider$NewInstanceFactory;", "application", "Landroid/app/Application;", "sponsoredFoodSearchAd", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;)V", "create", "T", "Landroid/arch/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroid/arch/lifecycle/ViewModel;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SponsoredFoodViewModelFactory.kt */
public final class SponsoredFoodViewModelFactory extends NewInstanceFactory {
    private final Application application;
    private final SponsoredFoodSearchAd sponsoredFoodSearchAd;

    public SponsoredFoodViewModelFactory(@NotNull Application application2, @Nullable SponsoredFoodSearchAd sponsoredFoodSearchAd2) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        this.application = application2;
        this.sponsoredFoodSearchAd = sponsoredFoodSearchAd2;
    }

    public <T extends ViewModel> T create(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        if (!SponsoredFoodEditorViewModel.class.isAssignableFrom(cls)) {
            return super.create(cls);
        }
        return (ViewModel) cls.getConstructor(new Class[]{Application.class, SponsoredFoodSearchAd.class}).newInstance(new Object[]{this.application, this.sponsoredFoodSearchAd});
    }
}
