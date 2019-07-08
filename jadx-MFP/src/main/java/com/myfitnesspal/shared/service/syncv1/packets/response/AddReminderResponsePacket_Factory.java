package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.reminders.RemindersService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AddReminderResponsePacket_Factory implements Factory<AddReminderResponsePacket> {
    private final Provider<RemindersService> remindersServiceProvider;

    public AddReminderResponsePacket_Factory(Provider<RemindersService> provider) {
        this.remindersServiceProvider = provider;
    }

    public AddReminderResponsePacket get() {
        return provideInstance(this.remindersServiceProvider);
    }

    public static AddReminderResponsePacket provideInstance(Provider<RemindersService> provider) {
        AddReminderResponsePacket addReminderResponsePacket = new AddReminderResponsePacket();
        AddReminderResponsePacket_MembersInjector.injectRemindersService(addReminderResponsePacket, DoubleCheck.lazy(provider));
        return addReminderResponsePacket;
    }

    public static AddReminderResponsePacket_Factory create(Provider<RemindersService> provider) {
        return new AddReminderResponsePacket_Factory(provider);
    }

    public static AddReminderResponsePacket newAddReminderResponsePacket() {
        return new AddReminderResponsePacket();
    }
}
