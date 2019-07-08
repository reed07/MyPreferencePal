package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.db.table.ProfileImagesTable;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideUserImageServiceFactory implements Factory<UserImageService> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<ProfileImagesTable> profileImagesTableProvider;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvideUserImageServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ProfileImagesTable> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.profileImagesTableProvider = provider3;
    }

    public UserImageService get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider, this.profileImagesTableProvider);
    }

    public static UserImageService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ProfileImagesTable> provider3) {
        return proxyProvideUserImageService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), (ProfileImagesTable) provider3.get());
    }

    public static ApplicationModule_ProvideUserImageServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ProfileImagesTable> provider3) {
        return new ApplicationModule_ProvideUserImageServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static UserImageService proxyProvideUserImageService(ApplicationModule applicationModule, Context context, Lazy<Session> lazy, ProfileImagesTable profileImagesTable) {
        return (UserImageService) Preconditions.checkNotNull(applicationModule.provideUserImageService(context, lazy, profileImagesTable), "Cannot return null from a non-@Nullable @Provides method");
    }
}
