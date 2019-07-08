package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.media.AudioManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAudioManagerFactory implements Factory<AudioManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAudioManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public AudioManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static AudioManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideAudioManager(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideAudioManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideAudioManagerFactory(applicationModule, provider);
    }

    public static AudioManager proxyProvideAudioManager(ApplicationModule applicationModule, Context context) {
        return (AudioManager) Preconditions.checkNotNull(applicationModule.provideAudioManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
