package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.reminders.RemindersService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeleteItemResponsePacket_Factory implements Factory<DeleteItemResponsePacket> {
    private final Provider<RemindersService> remindersServiceProvider;

    public DeleteItemResponsePacket_Factory(Provider<RemindersService> provider) {
        this.remindersServiceProvider = provider;
    }

    public DeleteItemResponsePacket get() {
        return provideInstance(this.remindersServiceProvider);
    }

    public static DeleteItemResponsePacket provideInstance(Provider<RemindersService> provider) {
        DeleteItemResponsePacket deleteItemResponsePacket = new DeleteItemResponsePacket();
        DeleteItemResponsePacket_MembersInjector.injectRemindersService(deleteItemResponsePacket, (RemindersService) provider.get());
        return deleteItemResponsePacket;
    }

    public static DeleteItemResponsePacket_Factory create(Provider<RemindersService> provider) {
        return new DeleteItemResponsePacket_Factory(provider);
    }

    public static DeleteItemResponsePacket newDeleteItemResponsePacket() {
        return new DeleteItemResponsePacket();
    }
}
