package com.myfitnesspal.feature.progress.ui.viewmodel;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ProgressViewModel_MembersInjector implements MembersInjector<ProgressViewModel> {
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<UserHeightService> heightServiceProvider;
    private final Provider<ProgressService> progressServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UserWeightService> weightServiceProvider;

    public ProgressViewModel_MembersInjector(Provider<StepService> provider, Provider<ProgressService> provider2, Provider<DiaryService> provider3, Provider<UserWeightService> provider4, Provider<UserHeightService> provider5, Provider<Session> provider6) {
        this.stepServiceProvider = provider;
        this.progressServiceProvider = provider2;
        this.diaryServiceProvider = provider3;
        this.weightServiceProvider = provider4;
        this.heightServiceProvider = provider5;
        this.sessionProvider = provider6;
    }

    public static MembersInjector<ProgressViewModel> create(Provider<StepService> provider, Provider<ProgressService> provider2, Provider<DiaryService> provider3, Provider<UserWeightService> provider4, Provider<UserHeightService> provider5, Provider<Session> provider6) {
        ProgressViewModel_MembersInjector progressViewModel_MembersInjector = new ProgressViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return progressViewModel_MembersInjector;
    }

    public void injectMembers(ProgressViewModel progressViewModel) {
        injectStepService(progressViewModel, DoubleCheck.lazy(this.stepServiceProvider));
        injectProgressService(progressViewModel, DoubleCheck.lazy(this.progressServiceProvider));
        injectDiaryService(progressViewModel, DoubleCheck.lazy(this.diaryServiceProvider));
        injectWeightService(progressViewModel, DoubleCheck.lazy(this.weightServiceProvider));
        injectHeightService(progressViewModel, DoubleCheck.lazy(this.heightServiceProvider));
        injectSession(progressViewModel, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectStepService(ProgressViewModel progressViewModel, Lazy<StepService> lazy) {
        progressViewModel.stepService = lazy;
    }

    public static void injectProgressService(ProgressViewModel progressViewModel, Lazy<ProgressService> lazy) {
        progressViewModel.progressService = lazy;
    }

    public static void injectDiaryService(ProgressViewModel progressViewModel, Lazy<DiaryService> lazy) {
        progressViewModel.diaryService = lazy;
    }

    public static void injectWeightService(ProgressViewModel progressViewModel, Lazy<UserWeightService> lazy) {
        progressViewModel.weightService = lazy;
    }

    public static void injectHeightService(ProgressViewModel progressViewModel, Lazy<UserHeightService> lazy) {
        progressViewModel.heightService = lazy;
    }

    public static void injectSession(ProgressViewModel progressViewModel, Lazy<Session> lazy) {
        progressViewModel.session = lazy;
    }
}
