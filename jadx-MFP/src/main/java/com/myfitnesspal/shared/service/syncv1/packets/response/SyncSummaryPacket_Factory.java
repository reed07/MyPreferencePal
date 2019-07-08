package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.db.adapter.UserV1DBAdapter;
import com.myfitnesspal.shared.service.session.Session;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SyncSummaryPacket_Factory implements Factory<SyncSummaryPacket> {
    private final Provider<Session> sessionProvider;
    private final Provider<UserV1DBAdapter> userV1DBAdapterProvider;

    public SyncSummaryPacket_Factory(Provider<UserV1DBAdapter> provider, Provider<Session> provider2) {
        this.userV1DBAdapterProvider = provider;
        this.sessionProvider = provider2;
    }

    public SyncSummaryPacket get() {
        return provideInstance(this.userV1DBAdapterProvider, this.sessionProvider);
    }

    public static SyncSummaryPacket provideInstance(Provider<UserV1DBAdapter> provider, Provider<Session> provider2) {
        SyncSummaryPacket syncSummaryPacket = new SyncSummaryPacket();
        SyncSummaryPacket_MembersInjector.injectUserV1DBAdapter(syncSummaryPacket, DoubleCheck.lazy(provider));
        SyncSummaryPacket_MembersInjector.injectSession(syncSummaryPacket, DoubleCheck.lazy(provider2));
        return syncSummaryPacket;
    }

    public static SyncSummaryPacket_Factory create(Provider<UserV1DBAdapter> provider, Provider<Session> provider2) {
        return new SyncSummaryPacket_Factory(provider, provider2);
    }

    public static SyncSummaryPacket newSyncSummaryPacket() {
        return new SyncSummaryPacket();
    }
}
