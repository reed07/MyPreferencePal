package com.myfitnesspal.shared.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpEditableFragmentBase_MembersInjector<T> implements MembersInjector<MfpEditableFragmentBase<T>> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public MfpEditableFragmentBase_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static <T> MembersInjector<MfpEditableFragmentBase<T>> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new MfpEditableFragmentBase_MembersInjector(provider, provider2);
    }

    public void injectMembers(MfpEditableFragmentBase<T> mfpEditableFragmentBase) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(mfpEditableFragmentBase, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(mfpEditableFragmentBase, (Glide) this.glideProvider.get());
    }
}
