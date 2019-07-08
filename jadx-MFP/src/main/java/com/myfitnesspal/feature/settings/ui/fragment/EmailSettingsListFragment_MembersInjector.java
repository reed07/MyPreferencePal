package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.validation.Validator;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EmailSettingsListFragment_MembersInjector implements MembersInjector<EmailSettingsListFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<Validator> emailValidatorProvider;
    private final Provider<UacfEmailVerificationManager> emailVerificationManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<UserPropertiesService> userPropertiesServiceProvider;

    public EmailSettingsListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserPropertiesService> provider3, Provider<ActionTrackingService> provider4, Provider<ConfigService> provider5, Provider<LoginModel> provider6, Provider<DbConnectionManager> provider7, Provider<Validator> provider8, Provider<UacfEmailVerificationManager> provider9) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userPropertiesServiceProvider = provider3;
        this.actionTrackingServiceProvider = provider4;
        this.configServiceProvider = provider5;
        this.loginModelProvider = provider6;
        this.dbConnectionManagerProvider = provider7;
        this.emailValidatorProvider = provider8;
        this.emailVerificationManagerProvider = provider9;
    }

    public static MembersInjector<EmailSettingsListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserPropertiesService> provider3, Provider<ActionTrackingService> provider4, Provider<ConfigService> provider5, Provider<LoginModel> provider6, Provider<DbConnectionManager> provider7, Provider<Validator> provider8, Provider<UacfEmailVerificationManager> provider9) {
        EmailSettingsListFragment_MembersInjector emailSettingsListFragment_MembersInjector = new EmailSettingsListFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return emailSettingsListFragment_MembersInjector;
    }

    public void injectMembers(EmailSettingsListFragment emailSettingsListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(emailSettingsListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(emailSettingsListFragment, (Glide) this.glideProvider.get());
        injectUserPropertiesService(emailSettingsListFragment, DoubleCheck.lazy(this.userPropertiesServiceProvider));
        injectActionTrackingService(emailSettingsListFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectConfigService(emailSettingsListFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectLoginModel(emailSettingsListFragment, DoubleCheck.lazy(this.loginModelProvider));
        injectDbConnectionManager(emailSettingsListFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectEmailValidator(emailSettingsListFragment, (Validator) this.emailValidatorProvider.get());
        injectEmailVerificationManager(emailSettingsListFragment, DoubleCheck.lazy(this.emailVerificationManagerProvider));
    }

    public static void injectUserPropertiesService(EmailSettingsListFragment emailSettingsListFragment, Lazy<UserPropertiesService> lazy) {
        emailSettingsListFragment.userPropertiesService = lazy;
    }

    public static void injectActionTrackingService(EmailSettingsListFragment emailSettingsListFragment, Lazy<ActionTrackingService> lazy) {
        emailSettingsListFragment.actionTrackingService = lazy;
    }

    public static void injectConfigService(EmailSettingsListFragment emailSettingsListFragment, Lazy<ConfigService> lazy) {
        emailSettingsListFragment.configService = lazy;
    }

    public static void injectLoginModel(EmailSettingsListFragment emailSettingsListFragment, Lazy<LoginModel> lazy) {
        emailSettingsListFragment.loginModel = lazy;
    }

    public static void injectDbConnectionManager(EmailSettingsListFragment emailSettingsListFragment, Lazy<DbConnectionManager> lazy) {
        emailSettingsListFragment.dbConnectionManager = lazy;
    }

    public static void injectEmailValidator(EmailSettingsListFragment emailSettingsListFragment, Validator validator) {
        emailSettingsListFragment.emailValidator = validator;
    }

    public static void injectEmailVerificationManager(EmailSettingsListFragment emailSettingsListFragment, Lazy<UacfEmailVerificationManager> lazy) {
        emailSettingsListFragment.emailVerificationManager = lazy;
    }
}
