package com.myfitnesspal.feature.recipes.ui.view;

import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class IngredientsContainer_MembersInjector implements MembersInjector<IngredientsContainer> {
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public IngredientsContainer_MembersInjector(Provider<UserEnergyService> provider) {
        this.userEnergyServiceProvider = provider;
    }

    public static MembersInjector<IngredientsContainer> create(Provider<UserEnergyService> provider) {
        return new IngredientsContainer_MembersInjector(provider);
    }

    public void injectMembers(IngredientsContainer ingredientsContainer) {
        injectUserEnergyService(ingredientsContainer, DoubleCheck.lazy(this.userEnergyServiceProvider));
    }

    public static void injectUserEnergyService(IngredientsContainer ingredientsContainer, Lazy<UserEnergyService> lazy) {
        ingredientsContainer.userEnergyService = lazy;
    }
}
