package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.achievementinterstitialad.repository.UserSummaryRepository;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideUserSummaryRepositoryFactory implements Factory<UserSummaryRepository> {
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<UserSummaryService> userSummaryServiceProvider;

    public ApplicationModule_ProvideUserSummaryRepositoryFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<UserSummaryService> provider2) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.userSummaryServiceProvider = provider2;
    }

    public UserSummaryRepository get() {
        return provideInstance(this.module, this.sessionProvider, this.userSummaryServiceProvider);
    }

    public static UserSummaryRepository provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<UserSummaryService> provider2) {
        return proxyProvideUserSummaryRepository(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvideUserSummaryRepositoryFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<UserSummaryService> provider2) {
        return new ApplicationModule_ProvideUserSummaryRepositoryFactory(applicationModule, provider, provider2);
    }

    public static UserSummaryRepository proxyProvideUserSummaryRepository(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<UserSummaryService> lazy2) {
        return (UserSummaryRepository) Preconditions.checkNotNull(applicationModule.provideUserSummaryRepository(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
