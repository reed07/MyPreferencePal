package com.myfitnesspal.shared.notification.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.event.ItemTalliesUpdatedEvent;
import com.myfitnesspal.shared.model.InAppNotifications;
import com.myfitnesspal.shared.model.v15.PendingItemTalliesObject;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.squareup.otto.Bus;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class InAppNotificationService extends JobService {
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<InAppNotificationManager> inAppNotificationManager;
    @Inject
    Lazy<UserPropertiesService> userPropertiesService;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public InAppNotificationService() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        refresh(jobParameters);
        return true;
    }

    private void refresh(JobParameters jobParameters) {
        Ln.d("Getting pending Item tallies.", new Object[0]);
        ((UserPropertiesService) this.userPropertiesService.get()).getPendingItemTalliesSync().zipWith(((InAppNotificationManager) this.inAppNotificationManager.get()).getCurrentInAppNotifications(), new BiFunction() {
            public final Object apply(Object obj, Object obj2) {
                return InAppNotificationService.lambda$refresh$0(InAppNotificationService.this, (PendingItemTalliesObject) obj, (InAppNotifications) obj2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer(jobParameters) {
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                InAppNotificationService.lambda$refresh$1(InAppNotificationService.this, this.f$1, (InAppNotifications) obj);
            }
        }, new Consumer(jobParameters) {
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                InAppNotificationService.lambda$refresh$2(InAppNotificationService.this, this.f$1, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ InAppNotifications lambda$refresh$0(InAppNotificationService inAppNotificationService, PendingItemTalliesObject pendingItemTalliesObject, InAppNotifications inAppNotifications) throws Exception {
        Ln.d("Received pendingItemTallies. Friend Requests: %s, New Messages: %s", Integer.valueOf(pendingItemTalliesObject.getFriendRequestCount()), Integer.valueOf(pendingItemTalliesObject.getMessageCount()));
        inAppNotifications.setFriendRequestCount(pendingItemTalliesObject.getFriendRequestCount());
        inAppNotifications.setMessageCount(pendingItemTalliesObject.getMessageCount());
        inAppNotifications.setMessagesFromCoachCount(pendingItemTalliesObject.getMessagesFromCoachCount());
        inAppNotifications.setDiaryCommentsFromCoachCount(pendingItemTalliesObject.getDiaryCommentsFromCoachCount());
        inAppNotifications.setLastUpdateTime(System.currentTimeMillis());
        ((InAppNotificationManager) inAppNotificationService.inAppNotificationManager.get()).setCurrentInAppNotifications(inAppNotifications);
        return inAppNotifications;
    }

    public static /* synthetic */ void lambda$refresh$1(InAppNotificationService inAppNotificationService, JobParameters jobParameters, InAppNotifications inAppNotifications) throws Exception {
        Ln.d("In App Notifications have been updated.", new Object[0]);
        ((Bus) inAppNotificationService.bus.get()).post(new ItemTalliesUpdatedEvent(inAppNotifications));
        inAppNotificationService.jobFinished(jobParameters, false);
    }

    public static /* synthetic */ void lambda$refresh$2(InAppNotificationService inAppNotificationService, JobParameters jobParameters, Throwable th) throws Exception {
        Ln.e(th);
        inAppNotificationService.jobFinished(jobParameters, false);
    }
}
