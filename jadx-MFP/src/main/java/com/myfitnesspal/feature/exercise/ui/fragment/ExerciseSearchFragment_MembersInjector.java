package com.myfitnesspal.feature.exercise.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseSearchAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExerciseSearchFragment_MembersInjector implements MembersInjector<ExerciseSearchFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseAnalyticsHelper> exerciseAnalyticsHelperProvider;
    private final Provider<ExerciseEntryMapper> exerciseEntryMapperProvider;
    private final Provider<ExerciseMapper> exerciseMapperProvider;
    private final Provider<ExerciseSearchAnalyticsHelper> exerciseSearchAnalyticsHelperProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<ExerciseStringService> exerciseStringServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<SearchService> searchServiceProvider;
    private final Provider<SortOrderHelper> sortOrderHelperProvider;

    public ExerciseSearchFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ExerciseService> provider3, Provider<DiaryService> provider4, Provider<SearchService> provider5, Provider<SortOrderHelper> provider6, Provider<ExerciseStringService> provider7, Provider<ExerciseSearchAnalyticsHelper> provider8, Provider<ExerciseEntryMapper> provider9, Provider<ExerciseMapper> provider10, Provider<ExerciseAnalyticsHelper> provider11) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.exerciseServiceProvider = provider3;
        this.diaryServiceProvider = provider4;
        this.searchServiceProvider = provider5;
        this.sortOrderHelperProvider = provider6;
        this.exerciseStringServiceProvider = provider7;
        this.exerciseSearchAnalyticsHelperProvider = provider8;
        this.exerciseEntryMapperProvider = provider9;
        this.exerciseMapperProvider = provider10;
        this.exerciseAnalyticsHelperProvider = provider11;
    }

    public static MembersInjector<ExerciseSearchFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ExerciseService> provider3, Provider<DiaryService> provider4, Provider<SearchService> provider5, Provider<SortOrderHelper> provider6, Provider<ExerciseStringService> provider7, Provider<ExerciseSearchAnalyticsHelper> provider8, Provider<ExerciseEntryMapper> provider9, Provider<ExerciseMapper> provider10, Provider<ExerciseAnalyticsHelper> provider11) {
        ExerciseSearchFragment_MembersInjector exerciseSearchFragment_MembersInjector = new ExerciseSearchFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return exerciseSearchFragment_MembersInjector;
    }

    public void injectMembers(ExerciseSearchFragment exerciseSearchFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(exerciseSearchFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(exerciseSearchFragment, (Glide) this.glideProvider.get());
        injectExerciseService(exerciseSearchFragment, DoubleCheck.lazy(this.exerciseServiceProvider));
        injectDiaryService(exerciseSearchFragment, DoubleCheck.lazy(this.diaryServiceProvider));
        injectSearchService(exerciseSearchFragment, DoubleCheck.lazy(this.searchServiceProvider));
        injectSortOrderHelper(exerciseSearchFragment, DoubleCheck.lazy(this.sortOrderHelperProvider));
        injectExerciseStringService(exerciseSearchFragment, DoubleCheck.lazy(this.exerciseStringServiceProvider));
        injectExerciseSearchAnalyticsHelper(exerciseSearchFragment, DoubleCheck.lazy(this.exerciseSearchAnalyticsHelperProvider));
        injectExerciseEntryMapper(exerciseSearchFragment, DoubleCheck.lazy(this.exerciseEntryMapperProvider));
        injectExerciseMapper(exerciseSearchFragment, DoubleCheck.lazy(this.exerciseMapperProvider));
        injectExerciseAnalyticsHelper(exerciseSearchFragment, DoubleCheck.lazy(this.exerciseAnalyticsHelperProvider));
    }

    public static void injectExerciseService(ExerciseSearchFragment exerciseSearchFragment, Lazy<ExerciseService> lazy) {
        exerciseSearchFragment.exerciseService = lazy;
    }

    public static void injectDiaryService(ExerciseSearchFragment exerciseSearchFragment, Lazy<DiaryService> lazy) {
        exerciseSearchFragment.diaryService = lazy;
    }

    public static void injectSearchService(ExerciseSearchFragment exerciseSearchFragment, Lazy<SearchService> lazy) {
        exerciseSearchFragment.searchService = lazy;
    }

    public static void injectSortOrderHelper(ExerciseSearchFragment exerciseSearchFragment, Lazy<SortOrderHelper> lazy) {
        exerciseSearchFragment.sortOrderHelper = lazy;
    }

    public static void injectExerciseStringService(ExerciseSearchFragment exerciseSearchFragment, Lazy<ExerciseStringService> lazy) {
        exerciseSearchFragment.exerciseStringService = lazy;
    }

    public static void injectExerciseSearchAnalyticsHelper(ExerciseSearchFragment exerciseSearchFragment, Lazy<ExerciseSearchAnalyticsHelper> lazy) {
        exerciseSearchFragment.exerciseSearchAnalyticsHelper = lazy;
    }

    public static void injectExerciseEntryMapper(ExerciseSearchFragment exerciseSearchFragment, Lazy<ExerciseEntryMapper> lazy) {
        exerciseSearchFragment.exerciseEntryMapper = lazy;
    }

    public static void injectExerciseMapper(ExerciseSearchFragment exerciseSearchFragment, Lazy<ExerciseMapper> lazy) {
        exerciseSearchFragment.exerciseMapper = lazy;
    }

    public static void injectExerciseAnalyticsHelper(ExerciseSearchFragment exerciseSearchFragment, Lazy<ExerciseAnalyticsHelper> lazy) {
        exerciseSearchFragment.exerciseAnalyticsHelper = lazy;
    }
}
