package com.myfitnesspal.feature.profile.ui.viewmodel;

import com.myfitnesspal.feature.profile.service.ProfileAggregatorService;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel.OnlineDataTask;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyInfoViewModel_OnlineDataTask_MembersInjector implements MembersInjector<OnlineDataTask> {
    private final Provider<ProfileAggregatorService> aggregatorProvider;

    public MyInfoViewModel_OnlineDataTask_MembersInjector(Provider<ProfileAggregatorService> provider) {
        this.aggregatorProvider = provider;
    }

    public static MembersInjector<OnlineDataTask> create(Provider<ProfileAggregatorService> provider) {
        return new MyInfoViewModel_OnlineDataTask_MembersInjector(provider);
    }

    public void injectMembers(OnlineDataTask onlineDataTask) {
        injectAggregator(onlineDataTask, DoubleCheck.lazy(this.aggregatorProvider));
    }

    public static void injectAggregator(OnlineDataTask onlineDataTask, Lazy<ProfileAggregatorService> lazy) {
        onlineDataTask.aggregator = lazy;
    }
}
