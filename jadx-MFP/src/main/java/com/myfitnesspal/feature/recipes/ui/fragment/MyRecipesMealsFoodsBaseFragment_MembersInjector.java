package com.myfitnesspal.feature.recipes.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyRecipesMealsFoodsBaseFragment_MembersInjector<T> implements MembersInjector<MyRecipesMealsFoodsBaseFragment<T>> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public MyRecipesMealsFoodsBaseFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static <T> MembersInjector<MyRecipesMealsFoodsBaseFragment<T>> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new MyRecipesMealsFoodsBaseFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(MyRecipesMealsFoodsBaseFragment<T> myRecipesMealsFoodsBaseFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myRecipesMealsFoodsBaseFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myRecipesMealsFoodsBaseFragment, (Glide) this.glideProvider.get());
    }
}
