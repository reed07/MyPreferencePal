package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class UpdateGoalsFragment_MembersInjector implements MembersInjector<UpdateGoalsFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<RemindersService> remindersServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SignUpService> signUpServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public UpdateGoalsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<RemindersService> provider4, Provider<UserEnergyService> provider5, Provider<NutrientGoalsUtil> provider6, Provider<LocalizedStringsUtil> provider7, Provider<AnalyticsService> provider8, Provider<MeasurementsService> provider9, Provider<CountryService> provider10, Provider<DiaryService> provider11, Provider<NutrientGoalService> provider12, Provider<Session> provider13, Provider<SignUpService> provider14) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userWeightServiceProvider = provider3;
        this.remindersServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.nutrientGoalsUtilProvider = provider6;
        this.localizedStringsUtilProvider = provider7;
        this.analyticsServiceProvider = provider8;
        this.measurementsServiceProvider = provider9;
        this.countryServiceProvider = provider10;
        this.diaryServiceProvider = provider11;
        this.nutrientGoalServiceProvider = provider12;
        this.sessionProvider = provider13;
        this.signUpServiceProvider = provider14;
    }

    public static MembersInjector<UpdateGoalsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<RemindersService> provider4, Provider<UserEnergyService> provider5, Provider<NutrientGoalsUtil> provider6, Provider<LocalizedStringsUtil> provider7, Provider<AnalyticsService> provider8, Provider<MeasurementsService> provider9, Provider<CountryService> provider10, Provider<DiaryService> provider11, Provider<NutrientGoalService> provider12, Provider<Session> provider13, Provider<SignUpService> provider14) {
        UpdateGoalsFragment_MembersInjector updateGoalsFragment_MembersInjector = new UpdateGoalsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
        return updateGoalsFragment_MembersInjector;
    }

    public void injectMembers(UpdateGoalsFragment updateGoalsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(updateGoalsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(updateGoalsFragment, (Glide) this.glideProvider.get());
        injectUserWeightService(updateGoalsFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectRemindersService(updateGoalsFragment, DoubleCheck.lazy(this.remindersServiceProvider));
        injectUserEnergyService(updateGoalsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectNutrientGoalsUtil(updateGoalsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectLocalizedStringsUtil(updateGoalsFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectAnalyticsService(updateGoalsFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectMeasurementsService(updateGoalsFragment, DoubleCheck.lazy(this.measurementsServiceProvider));
        injectCountryService(updateGoalsFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectDiaryService(updateGoalsFragment, DoubleCheck.lazy(this.diaryServiceProvider));
        injectNutrientGoalService(updateGoalsFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectSession(updateGoalsFragment, DoubleCheck.lazy(this.sessionProvider));
        injectSignUpService(updateGoalsFragment, DoubleCheck.lazy(this.signUpServiceProvider));
    }

    public static void injectUserWeightService(UpdateGoalsFragment updateGoalsFragment, Lazy<UserWeightService> lazy) {
        updateGoalsFragment.userWeightService = lazy;
    }

    public static void injectRemindersService(UpdateGoalsFragment updateGoalsFragment, Lazy<RemindersService> lazy) {
        updateGoalsFragment.remindersService = lazy;
    }

    public static void injectUserEnergyService(UpdateGoalsFragment updateGoalsFragment, Lazy<UserEnergyService> lazy) {
        updateGoalsFragment.userEnergyService = lazy;
    }

    public static void injectNutrientGoalsUtil(UpdateGoalsFragment updateGoalsFragment, Lazy<NutrientGoalsUtil> lazy) {
        updateGoalsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(UpdateGoalsFragment updateGoalsFragment, Lazy<LocalizedStringsUtil> lazy) {
        updateGoalsFragment.localizedStringsUtil = lazy;
    }

    public static void injectAnalyticsService(UpdateGoalsFragment updateGoalsFragment, Lazy<AnalyticsService> lazy) {
        updateGoalsFragment.analyticsService = lazy;
    }

    public static void injectMeasurementsService(UpdateGoalsFragment updateGoalsFragment, Lazy<MeasurementsService> lazy) {
        updateGoalsFragment.measurementsService = lazy;
    }

    public static void injectCountryService(UpdateGoalsFragment updateGoalsFragment, Lazy<CountryService> lazy) {
        updateGoalsFragment.countryService = lazy;
    }

    public static void injectDiaryService(UpdateGoalsFragment updateGoalsFragment, Lazy<DiaryService> lazy) {
        updateGoalsFragment.diaryService = lazy;
    }

    public static void injectNutrientGoalService(UpdateGoalsFragment updateGoalsFragment, Lazy<NutrientGoalService> lazy) {
        updateGoalsFragment.nutrientGoalService = lazy;
    }

    public static void injectSession(UpdateGoalsFragment updateGoalsFragment, Lazy<Session> lazy) {
        updateGoalsFragment.session = lazy;
    }

    public static void injectSignUpService(UpdateGoalsFragment updateGoalsFragment, Lazy<SignUpService> lazy) {
        updateGoalsFragment.signUpService = lazy;
    }
}
