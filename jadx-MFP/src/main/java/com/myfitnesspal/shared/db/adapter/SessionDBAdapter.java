package com.myfitnesspal.shared.db.adapter;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import javax.inject.Inject;

public abstract class SessionDBAdapter {
    @Inject
    Lazy<FoodService> foodService = null;
    @Inject
    Lazy<Session> session = null;

    protected SessionDBAdapter() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    /* access modifiers changed from: protected */
    public Session getSession() {
        return (Session) this.session.get();
    }
}
