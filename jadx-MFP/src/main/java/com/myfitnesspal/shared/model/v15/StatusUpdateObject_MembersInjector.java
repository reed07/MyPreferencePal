package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class StatusUpdateObject_MembersInjector implements MembersInjector<StatusUpdateObject> {
    private final Provider<Session> sessionProvider;

    public StatusUpdateObject_MembersInjector(Provider<Session> provider) {
        this.sessionProvider = provider;
    }

    public static MembersInjector<StatusUpdateObject> create(Provider<Session> provider) {
        return new StatusUpdateObject_MembersInjector(provider);
    }

    public void injectMembers(StatusUpdateObject statusUpdateObject) {
        injectSession(statusUpdateObject, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectSession(StatusUpdateObject statusUpdateObject, Lazy<Session> lazy) {
        statusUpdateObject.session = lazy;
    }
}
