package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.search.repository.OnlineFoodSearchRepository;
import com.myfitnesspal.feature.search.service.SearchService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideOnlineFoodSearchRepositoryFactory implements Factory<OnlineFoodSearchRepository> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<SearchService> searchServiceProvider;

    public ApplicationModule_ProvideOnlineFoodSearchRepositoryFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<SearchService> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.searchServiceProvider = provider2;
    }

    public OnlineFoodSearchRepository get() {
        return provideInstance(this.module, this.contextProvider, this.searchServiceProvider);
    }

    public static OnlineFoodSearchRepository provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<SearchService> provider2) {
        return proxyProvideOnlineFoodSearchRepository(applicationModule, (Context) provider.get(), (SearchService) provider2.get());
    }

    public static ApplicationModule_ProvideOnlineFoodSearchRepositoryFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<SearchService> provider2) {
        return new ApplicationModule_ProvideOnlineFoodSearchRepositoryFactory(applicationModule, provider, provider2);
    }

    public static OnlineFoodSearchRepository proxyProvideOnlineFoodSearchRepository(ApplicationModule applicationModule, Context context, SearchService searchService) {
        return (OnlineFoodSearchRepository) Preconditions.checkNotNull(applicationModule.provideOnlineFoodSearchRepository(context, searchService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
