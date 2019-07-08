package com.myfitnesspal.shared.model.v2;

import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.notification.model.MfpNotificationChannel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/shared/model/v2/MfpNotificationType;", "", "notificationId", "", "defaultTitleResId", "(Ljava/lang/String;III)V", "getDefaultTitleResId", "()I", "getNotificationId", "toNotificationChannel", "Lcom/myfitnesspal/shared/notification/model/MfpNotificationChannel;", "MESSAGE", "STATUS", "INVITATION", "STATUS_COMMENT", "REMINDER", "GENERIC", "URL_HANDLER", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: MfpNotificationType.kt */
public enum MfpNotificationType {
    MESSAGE(1, R.string.notification_message_title),
    STATUS(2, R.string.notification_status_update_title),
    INVITATION(3, R.string.notification_invitation_title),
    STATUS_COMMENT(4, R.string.notification_status_comment_title),
    REMINDER(5, R.string.notification_reminder_title),
    GENERIC(6, R.string.notification_generic_title),
    URL_HANDLER(7, R.string.notification_generic_title);
    
    private final int defaultTitleResId;
    private final int notificationId;

    protected MfpNotificationType(int i, int i2) {
        this.notificationId = i;
        this.defaultTitleResId = i2;
    }

    public final int getDefaultTitleResId() {
        return this.defaultTitleResId;
    }

    public final int getNotificationId() {
        return this.notificationId;
    }

    @Nullable
    public final MfpNotificationChannel toNotificationChannel() {
        switch (this) {
            case MESSAGE:
                return MfpNotificationChannel.MESSAGES;
            case STATUS:
                return MfpNotificationChannel.STATUSES;
            case INVITATION:
                return MfpNotificationChannel.INVITATIONS;
            case STATUS_COMMENT:
                return MfpNotificationChannel.STATUS_COMMENTS;
            case REMINDER:
                return MfpNotificationChannel.REMINDERS;
            case GENERIC:
                return MfpNotificationChannel.GENERIC;
            case URL_HANDLER:
                return MfpNotificationChannel.URL_HANDLERS;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
