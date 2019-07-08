package com.myfitnesspal.feature.profile.ui.viewmodel;

import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyItemsViewModel.MyItemsTask;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyItemsViewModel_MyItemsTask_MembersInjector implements MembersInjector<MyItemsTask> {
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<Session> sessionProvider;

    public MyItemsViewModel_MyItemsTask_MembersInjector(Provider<ExerciseService> provider, Provider<DbConnectionManager> provider2, Provider<Session> provider3) {
        this.exerciseServiceProvider = provider;
        this.dbConnectionManagerProvider = provider2;
        this.sessionProvider = provider3;
    }

    public static MembersInjector<MyItemsTask> create(Provider<ExerciseService> provider, Provider<DbConnectionManager> provider2, Provider<Session> provider3) {
        return new MyItemsViewModel_MyItemsTask_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(MyItemsTask myItemsTask) {
        injectExerciseService(myItemsTask, DoubleCheck.lazy(this.exerciseServiceProvider));
        injectDbConnectionManager(myItemsTask, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectSession(myItemsTask, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectExerciseService(MyItemsTask myItemsTask, Lazy<ExerciseService> lazy) {
        myItemsTask.exerciseService = lazy;
    }

    public static void injectDbConnectionManager(MyItemsTask myItemsTask, Lazy<DbConnectionManager> lazy) {
        myItemsTask.dbConnectionManager = lazy;
    }

    public static void injectSession(MyItemsTask myItemsTask, Lazy<Session> lazy) {
        myItemsTask.session = lazy;
    }
}
