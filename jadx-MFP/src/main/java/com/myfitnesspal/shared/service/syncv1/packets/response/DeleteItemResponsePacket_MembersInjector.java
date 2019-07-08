package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.reminders.RemindersService;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DeleteItemResponsePacket_MembersInjector implements MembersInjector<DeleteItemResponsePacket> {
    private final Provider<RemindersService> remindersServiceProvider;

    public DeleteItemResponsePacket_MembersInjector(Provider<RemindersService> provider) {
        this.remindersServiceProvider = provider;
    }

    public static MembersInjector<DeleteItemResponsePacket> create(Provider<RemindersService> provider) {
        return new DeleteItemResponsePacket_MembersInjector(provider);
    }

    public void injectMembers(DeleteItemResponsePacket deleteItemResponsePacket) {
        injectRemindersService(deleteItemResponsePacket, (RemindersService) this.remindersServiceProvider.get());
    }

    public static void injectRemindersService(DeleteItemResponsePacket deleteItemResponsePacket, RemindersService remindersService) {
        deleteItemResponsePacket.remindersService = remindersService;
    }
}
