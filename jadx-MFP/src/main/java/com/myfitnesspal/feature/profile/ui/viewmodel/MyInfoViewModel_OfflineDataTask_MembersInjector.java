package com.myfitnesspal.feature.profile.ui.viewmodel;

import com.myfitnesspal.feature.profile.service.ProfileAggregatorService;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel.OfflineDataTask;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyInfoViewModel_OfflineDataTask_MembersInjector implements MembersInjector<OfflineDataTask> {
    private final Provider<ProfileAggregatorService> aggregatorProvider;

    public MyInfoViewModel_OfflineDataTask_MembersInjector(Provider<ProfileAggregatorService> provider) {
        this.aggregatorProvider = provider;
    }

    public static MembersInjector<OfflineDataTask> create(Provider<ProfileAggregatorService> provider) {
        return new MyInfoViewModel_OfflineDataTask_MembersInjector(provider);
    }

    public void injectMembers(OfflineDataTask offlineDataTask) {
        injectAggregator(offlineDataTask, DoubleCheck.lazy(this.aggregatorProvider));
    }

    public static void injectAggregator(OfflineDataTask offlineDataTask, Lazy<ProfileAggregatorService> lazy) {
        offlineDataTask.aggregator = lazy;
    }
}
