package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.db.adapter.UserV1DBAdapter;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SyncSummaryPacket_MembersInjector implements MembersInjector<SyncSummaryPacket> {
    private final Provider<Session> sessionProvider;
    private final Provider<UserV1DBAdapter> userV1DBAdapterProvider;

    public SyncSummaryPacket_MembersInjector(Provider<UserV1DBAdapter> provider, Provider<Session> provider2) {
        this.userV1DBAdapterProvider = provider;
        this.sessionProvider = provider2;
    }

    public static MembersInjector<SyncSummaryPacket> create(Provider<UserV1DBAdapter> provider, Provider<Session> provider2) {
        return new SyncSummaryPacket_MembersInjector(provider, provider2);
    }

    public void injectMembers(SyncSummaryPacket syncSummaryPacket) {
        injectUserV1DBAdapter(syncSummaryPacket, DoubleCheck.lazy(this.userV1DBAdapterProvider));
        injectSession(syncSummaryPacket, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectUserV1DBAdapter(SyncSummaryPacket syncSummaryPacket, Lazy<UserV1DBAdapter> lazy) {
        syncSummaryPacket.userV1DBAdapter = lazy;
    }

    public static void injectSession(SyncSummaryPacket syncSummaryPacket, Lazy<Session> lazy) {
        syncSummaryPacket.session = lazy;
    }
}
