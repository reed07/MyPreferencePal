package com.myfitnesspal.feature.debug.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import java.io.File;
import javax.inject.Provider;

public final class DebugLogsFragment_MembersInjector implements MembersInjector<DebugLogsFragment> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<File> logsDirProvider;

    public DebugLogsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<File> provider3, Provider<AppSettings> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.logsDirProvider = provider3;
        this.appSettingsProvider = provider4;
    }

    public static MembersInjector<DebugLogsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<File> provider3, Provider<AppSettings> provider4) {
        return new DebugLogsFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(DebugLogsFragment debugLogsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(debugLogsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(debugLogsFragment, (Glide) this.glideProvider.get());
        injectLogsDir(debugLogsFragment, (File) this.logsDirProvider.get());
        injectAppSettings(debugLogsFragment, DoubleCheck.lazy(this.appSettingsProvider));
    }

    public static void injectLogsDir(DebugLogsFragment debugLogsFragment, File file) {
        debugLogsFragment.logsDir = file;
    }

    public static void injectAppSettings(DebugLogsFragment debugLogsFragment, Lazy<AppSettings> lazy) {
        debugLogsFragment.appSettings = lazy;
    }
}
