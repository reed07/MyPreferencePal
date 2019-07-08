package com.myfitnesspal.feature.progress.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ImportPhotoFragment_MembersInjector implements MembersInjector<ImportPhotoFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ProgressAnalytics> progressAnalyticsProvider;

    public ImportPhotoFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ProgressAnalytics> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.progressAnalyticsProvider = provider3;
    }

    public static MembersInjector<ImportPhotoFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ProgressAnalytics> provider3) {
        return new ImportPhotoFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(ImportPhotoFragment importPhotoFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(importPhotoFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(importPhotoFragment, (Glide) this.glideProvider.get());
        injectProgressAnalytics(importPhotoFragment, DoubleCheck.lazy(this.progressAnalyticsProvider));
    }

    public static void injectProgressAnalytics(ImportPhotoFragment importPhotoFragment, Lazy<ProgressAnalytics> lazy) {
        importPhotoFragment.progressAnalytics = lazy;
    }
}
