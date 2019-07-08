package com.myfitnesspal.shared.db.adapter;

import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SessionDBAdapter_MembersInjector implements MembersInjector<SessionDBAdapter> {
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Session> sessionProvider;

    public SessionDBAdapter_MembersInjector(Provider<Session> provider, Provider<FoodService> provider2) {
        this.sessionProvider = provider;
        this.foodServiceProvider = provider2;
    }

    public static MembersInjector<SessionDBAdapter> create(Provider<Session> provider, Provider<FoodService> provider2) {
        return new SessionDBAdapter_MembersInjector(provider, provider2);
    }

    public void injectMembers(SessionDBAdapter sessionDBAdapter) {
        injectSession(sessionDBAdapter, DoubleCheck.lazy(this.sessionProvider));
        injectFoodService(sessionDBAdapter, DoubleCheck.lazy(this.foodServiceProvider));
    }

    public static void injectSession(SessionDBAdapter sessionDBAdapter, Lazy<Session> lazy) {
        sessionDBAdapter.session = lazy;
    }

    public static void injectFoodService(SessionDBAdapter sessionDBAdapter, Lazy<FoodService> lazy) {
        sessionDBAdapter.foodService = lazy;
    }
}
