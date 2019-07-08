package com.myfitnesspal.shared.notification;

import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.model.InAppNotifications;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import javax.inject.Inject;

public class InAppNotificationManagerImpl implements InAppNotificationManager {
    private AppSettings appSettings;
    private ApiJsonMapper mapper = new ApiJsonMapper();
    private InAppNotifications notifications;

    @Inject
    public InAppNotificationManagerImpl(AppSettings appSettings2) {
        this.appSettings = appSettings2;
    }

    public Single<InAppNotifications> getCurrentInAppNotifications() {
        return Single.create(new SingleOnSubscribe() {
            public final void subscribe(SingleEmitter singleEmitter) {
                InAppNotificationManagerImpl.lambda$getCurrentInAppNotifications$0(InAppNotificationManagerImpl.this, singleEmitter);
            }
        });
    }

    public static /* synthetic */ void lambda$getCurrentInAppNotifications$0(InAppNotificationManagerImpl inAppNotificationManagerImpl, SingleEmitter singleEmitter) throws Exception {
        if (inAppNotificationManagerImpl.notifications == null) {
            inAppNotificationManagerImpl.notifications = new InAppNotifications();
            if (Strings.notEmpty(inAppNotificationManagerImpl.appSettings.getInAppNotificationsJson())) {
                inAppNotificationManagerImpl.notifications = (InAppNotifications) inAppNotificationManagerImpl.mapper.mapFrom(inAppNotificationManagerImpl.appSettings.getInAppNotificationsJson(), InAppNotifications.class);
            }
        }
        singleEmitter.onSuccess(inAppNotificationManagerImpl.notifications);
    }

    public void setCurrentInAppNotifications(InAppNotifications inAppNotifications) {
        if (inAppNotifications != null) {
            try {
                this.appSettings.setInAppNotificationsJson(this.mapper.reverseMap((Object) inAppNotifications));
                this.notifications = inAppNotifications;
            } catch (Exception e) {
                Ln.e(e, "Could not save the notifications json string", new Object[0]);
            }
        } else {
            Ln.d("Notifications object is null. Cannot write to AppSettings.", new Object[0]);
        }
    }

    public void clearInAppNotifications() {
        this.appSettings.clearInAppNotifications();
        this.notifications = null;
    }
}
