package com.myfitnesspal.shared.notification.model;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.myfitnesspal.android.R;

public enum MfpNotificationChannel {
    MESSAGES("messages", R.string.notification_channel_messages, R.string.notification_channel_description_messages),
    STATUSES("statuses", R.string.notification_channel_statuses, R.string.notification_channel_description_statuses),
    INVITATIONS("invitations", R.string.notification_channel_invitations, R.string.notification_channel_description_invitations),
    STATUS_COMMENTS("status_comments", R.string.notification_channel_status_comments, R.string.notification_channel_description_status_comments),
    REMINDERS("reminders", R.string.notification_channel_reminders, R.string.notification_channel_description_reminders),
    GENERIC(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, R.string.notification_channel_generics, R.string.notification_channel_description_generics),
    URL_HANDLERS("url_handlers", R.string.notification_channel_url_handlers, R.string.notification_channel_description_url_handlers),
    SAMSUNG_GEAR("samsung_gear", R.string.notification_channel_samsung_gear, R.string.notification_channel_description_samsung_gear);
    
    private final String channelId;
    private final int channelNameResId;
    private final int descriptionResId;

    private MfpNotificationChannel(String str, int i, int i2) {
        this.channelId = str;
        this.channelNameResId = i;
        this.descriptionResId = i2;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public int getChannelNameResId() {
        return this.channelNameResId;
    }

    public int getDescriptionResId() {
        return this.descriptionResId;
    }
}
