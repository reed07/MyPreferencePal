package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.reminders.RemindersService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AddReminderResponsePacket_MembersInjector implements MembersInjector<AddReminderResponsePacket> {
    private final Provider<RemindersService> remindersServiceProvider;

    public AddReminderResponsePacket_MembersInjector(Provider<RemindersService> provider) {
        this.remindersServiceProvider = provider;
    }

    public static MembersInjector<AddReminderResponsePacket> create(Provider<RemindersService> provider) {
        return new AddReminderResponsePacket_MembersInjector(provider);
    }

    public void injectMembers(AddReminderResponsePacket addReminderResponsePacket) {
        injectRemindersService(addReminderResponsePacket, DoubleCheck.lazy(this.remindersServiceProvider));
    }

    public static void injectRemindersService(AddReminderResponsePacket addReminderResponsePacket, Lazy<RemindersService> lazy) {
        addReminderResponsePacket.remindersService = lazy;
    }
}
