package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AddExerciseDeepLinkMixin_MembersInjector implements MembersInjector<AddExerciseDeepLinkMixin> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseEntryMapper> mapperProvider;

    public AddExerciseDeepLinkMixin_MembersInjector(Provider<AnalyticsService> provider, Provider<AppIndexerBot> provider2, Provider<ExerciseEntryMapper> provider3, Provider<DiaryService> provider4, Provider<DbConnectionManager> provider5) {
        this.analyticsProvider = provider;
        this.appIndexerBotProvider = provider2;
        this.mapperProvider = provider3;
        this.diaryServiceProvider = provider4;
        this.dbConnectionManagerProvider = provider5;
    }

    public static MembersInjector<AddExerciseDeepLinkMixin> create(Provider<AnalyticsService> provider, Provider<AppIndexerBot> provider2, Provider<ExerciseEntryMapper> provider3, Provider<DiaryService> provider4, Provider<DbConnectionManager> provider5) {
        AddExerciseDeepLinkMixin_MembersInjector addExerciseDeepLinkMixin_MembersInjector = new AddExerciseDeepLinkMixin_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return addExerciseDeepLinkMixin_MembersInjector;
    }

    public void injectMembers(AddExerciseDeepLinkMixin addExerciseDeepLinkMixin) {
        DeepLinkMixinBase_MembersInjector.injectAnalytics(addExerciseDeepLinkMixin, DoubleCheck.lazy(this.analyticsProvider));
        AppIndexerMixinBase_MembersInjector.injectAnalytics(addExerciseDeepLinkMixin, DoubleCheck.lazy(this.analyticsProvider));
        AppIndexerMixinBase_MembersInjector.injectAppIndexerBot(addExerciseDeepLinkMixin, DoubleCheck.lazy(this.appIndexerBotProvider));
        injectMapper(addExerciseDeepLinkMixin, (ExerciseEntryMapper) this.mapperProvider.get());
        injectDiaryService(addExerciseDeepLinkMixin, DoubleCheck.lazy(this.diaryServiceProvider));
        injectDbConnectionManager(addExerciseDeepLinkMixin, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectMapper(AddExerciseDeepLinkMixin addExerciseDeepLinkMixin, ExerciseEntryMapper exerciseEntryMapper) {
        addExerciseDeepLinkMixin.mapper = exerciseEntryMapper;
    }

    public static void injectDiaryService(AddExerciseDeepLinkMixin addExerciseDeepLinkMixin, Lazy<DiaryService> lazy) {
        addExerciseDeepLinkMixin.diaryService = lazy;
    }

    public static void injectDbConnectionManager(AddExerciseDeepLinkMixin addExerciseDeepLinkMixin, Lazy<DbConnectionManager> lazy) {
        addExerciseDeepLinkMixin.dbConnectionManager = lazy;
    }
}
