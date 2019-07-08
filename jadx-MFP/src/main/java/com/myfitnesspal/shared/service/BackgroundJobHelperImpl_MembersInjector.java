package com.myfitnesspal.shared.service;

import com.myfitnesspal.shared.notification.JobServiceFactory;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class BackgroundJobHelperImpl_MembersInjector implements MembersInjector<BackgroundJobHelperImpl> {
    private final Provider<JobServiceFactory> jobServiceFactoryProvider;

    public BackgroundJobHelperImpl_MembersInjector(Provider<JobServiceFactory> provider) {
        this.jobServiceFactoryProvider = provider;
    }

    public static MembersInjector<BackgroundJobHelperImpl> create(Provider<JobServiceFactory> provider) {
        return new BackgroundJobHelperImpl_MembersInjector(provider);
    }

    public void injectMembers(BackgroundJobHelperImpl backgroundJobHelperImpl) {
        injectJobServiceFactory(backgroundJobHelperImpl, DoubleCheck.lazy(this.jobServiceFactoryProvider));
    }

    public static void injectJobServiceFactory(BackgroundJobHelperImpl backgroundJobHelperImpl, Lazy<JobServiceFactory> lazy) {
        backgroundJobHelperImpl.jobServiceFactory = lazy;
    }
}
