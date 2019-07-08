package com.myfitnesspal.shared.injection.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel;
import com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel;
import com.myfitnesspal.feature.search.ui.viewmodel.FoodSearchViewModel;
import com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel;
import com.myfitnesspal.feature.search.ui.viewmodel.OnlineFoodSearchViewModel;
import com.myfitnesspal.feature.settings.model.ChangePasswordViewModel;
import com.myfitnesspal.feature.settings.model.TroubleshootingViewModel;
import com.myfitnesspal.shared.injection.scope.ViewModelKey;
import com.myfitnesspal.shared.ui.factory.VMFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH'J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH'J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H'J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0014H'J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H'J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aH'J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH'¨\u0006\u001f"}, d2 = {"Lcom/myfitnesspal/shared/injection/module/ViewModelModule;", "", "()V", "bindChangePasswordViewModel", "Landroid/arch/lifecycle/ViewModel;", "changePasswordViewModel", "Lcom/myfitnesspal/feature/settings/model/ChangePasswordViewModel;", "bindConsentsViewModel", "consentsViewModel", "Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel;", "bindFoodFeedbackViewModel", "foodFeedbackViewModel", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel;", "bindFoodSearchViewModel", "searchViewModel", "Lcom/myfitnesspal/feature/search/ui/viewmodel/FoodSearchViewModel;", "bindLocalFoodSearchViewModel", "localFoodSearchViewModel", "Lcom/myfitnesspal/feature/search/ui/viewmodel/LocalFoodSearchViewModel;", "bindOnlineFoodSearchViewModel", "Lcom/myfitnesspal/feature/search/ui/viewmodel/OnlineFoodSearchViewModel;", "bindPersonalizedConsentViewModel", "adConsentsViewModel", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel;", "bindTroubleshootingViewModel", "troubleshootingViewModel", "Lcom/myfitnesspal/feature/settings/model/TroubleshootingViewModel;", "bindViewModelFactory", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "vmFactory", "Lcom/myfitnesspal/shared/ui/factory/VMFactory;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Module
/* compiled from: ViewModelModule.kt */
public abstract class ViewModelModule {
    @ViewModelKey(ChangePasswordViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindChangePasswordViewModel(@NotNull ChangePasswordViewModel changePasswordViewModel);

    @ViewModelKey(ConsentsViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindConsentsViewModel(@NotNull ConsentsViewModel consentsViewModel);

    @ViewModelKey(FoodFeedbackViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindFoodFeedbackViewModel(@NotNull FoodFeedbackViewModel foodFeedbackViewModel);

    @ViewModelKey(FoodSearchViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindFoodSearchViewModel(@NotNull FoodSearchViewModel foodSearchViewModel);

    @ViewModelKey(LocalFoodSearchViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindLocalFoodSearchViewModel(@NotNull LocalFoodSearchViewModel localFoodSearchViewModel);

    @ViewModelKey(OnlineFoodSearchViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindOnlineFoodSearchViewModel(@NotNull OnlineFoodSearchViewModel onlineFoodSearchViewModel);

    @ViewModelKey(AdConsentsViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindPersonalizedConsentViewModel(@NotNull AdConsentsViewModel adConsentsViewModel);

    @ViewModelKey(TroubleshootingViewModel.class)
    @Binds
    @IntoMap
    @NotNull
    public abstract ViewModel bindTroubleshootingViewModel(@NotNull TroubleshootingViewModel troubleshootingViewModel);

    @NotNull
    @Binds
    public abstract Factory bindViewModelFactory(@NotNull VMFactory vMFactory);
}
