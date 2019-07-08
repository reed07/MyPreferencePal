package com.myfitnesspal.feature.explore.ui.viewmodel;

import com.myfitnesspal.feature.explore.service.ExploreService;
import com.myfitnesspal.feature.explore.ui.viewmodel.ExploreViewModel.ExploreDataTask;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ExploreViewModel_ExploreDataTask_MembersInjector implements MembersInjector<ExploreDataTask> {
    private final Provider<ExploreService> exploreServiceProvider;

    public ExploreViewModel_ExploreDataTask_MembersInjector(Provider<ExploreService> provider) {
        this.exploreServiceProvider = provider;
    }

    public static MembersInjector<ExploreDataTask> create(Provider<ExploreService> provider) {
        return new ExploreViewModel_ExploreDataTask_MembersInjector(provider);
    }

    public void injectMembers(ExploreDataTask exploreDataTask) {
        injectExploreService(exploreDataTask, (ExploreService) this.exploreServiceProvider.get());
    }

    public static void injectExploreService(ExploreDataTask exploreDataTask, ExploreService exploreService) {
        exploreDataTask.exploreService = exploreService;
    }
}
