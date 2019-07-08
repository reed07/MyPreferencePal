package com.myfitnesspal.android.login;

import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.logging.LogConfig;
import com.uacf.core.logging.Printer;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class Welcome_MembersInjector implements MembersInjector<Welcome> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<LogConfig> logConfigProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Printer> printerProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StartupManager> startupManagerProvider;

    public Welcome_MembersInjector(Provider<StartupManager> provider, Provider<NavigationHelper> provider2, Provider<ConfigService> provider3, Provider<LogConfig> provider4, Provider<Printer> provider5, Provider<Session> provider6) {
        this.startupManagerProvider = provider;
        this.navigationHelperProvider = provider2;
        this.configServiceProvider = provider3;
        this.logConfigProvider = provider4;
        this.printerProvider = provider5;
        this.sessionProvider = provider6;
    }

    public static MembersInjector<Welcome> create(Provider<StartupManager> provider, Provider<NavigationHelper> provider2, Provider<ConfigService> provider3, Provider<LogConfig> provider4, Provider<Printer> provider5, Provider<Session> provider6) {
        Welcome_MembersInjector welcome_MembersInjector = new Welcome_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return welcome_MembersInjector;
    }

    public void injectMembers(Welcome welcome) {
        injectStartupManager(welcome, DoubleCheck.lazy(this.startupManagerProvider));
        injectNavigationHelper(welcome, DoubleCheck.lazy(this.navigationHelperProvider));
        injectConfigService(welcome, DoubleCheck.lazy(this.configServiceProvider));
        injectLogConfig(welcome, DoubleCheck.lazy(this.logConfigProvider));
        injectPrinter(welcome, DoubleCheck.lazy(this.printerProvider));
        injectSession(welcome, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectStartupManager(Welcome welcome, Lazy<StartupManager> lazy) {
        welcome.startupManager = lazy;
    }

    public static void injectNavigationHelper(Welcome welcome, Lazy<NavigationHelper> lazy) {
        welcome.navigationHelper = lazy;
    }

    public static void injectConfigService(Welcome welcome, Lazy<ConfigService> lazy) {
        welcome.configService = lazy;
    }

    public static void injectLogConfig(Welcome welcome, Lazy<LogConfig> lazy) {
        welcome.logConfig = lazy;
    }

    public static void injectPrinter(Welcome welcome, Lazy<Printer> lazy) {
        welcome.printer = lazy;
    }

    public static void injectSession(Welcome welcome, Lazy<Session> lazy) {
        welcome.session = lazy;
    }
}
