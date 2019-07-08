package com.myfitnesspal.feature.notificationinbox.ui.viewmodel;

import com.myfitnesspal.feature.notificationinbox.service.GetAllNotificationsTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.core.util.CollectionUtils;
import com.uacf.taskrunner.Runner;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.model.UacfNotification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotificationInboxViewModel extends RunnerViewModel<String> {
    private UacfNotificationSdk notificationSdk;
    private List<UacfNotification> uacfNotificationList = new ArrayList();

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int NOTIFICATIONS_LIST = ViewModelPropertyId.next();
    }

    public NotificationInboxViewModel(Runner runner, UacfNotificationSdk uacfNotificationSdk) {
        super(runner);
        this.notificationSdk = uacfNotificationSdk;
    }

    public void load(String... strArr) {
        if (getState() != State.Loading) {
            new GetAllNotificationsTask(this.notificationSdk).run(getRunner());
        }
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), GetAllNotificationsTask.class)) {
            List<UacfNotification> list = (List) taskDetails.getResult();
            if (CollectionUtils.notEmpty((Collection<?>) list)) {
                this.uacfNotificationList = list;
            }
            setState(State.Loaded);
            notifyPropertyChanged(Property.NOTIFICATIONS_LIST);
        }
    }

    public List<UacfNotification> getUacfNotificationList() {
        return new ArrayList(this.uacfNotificationList);
    }
}
