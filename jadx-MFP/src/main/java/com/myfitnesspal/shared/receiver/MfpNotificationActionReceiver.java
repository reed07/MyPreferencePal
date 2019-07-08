package com.myfitnesspal.shared.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import com.myfitnesspal.shared.notification.JobServiceFactory.Job;
import com.uacf.core.util.ExtrasUtils;
import javax.inject.Inject;

public class MfpNotificationActionReceiver extends BroadcastReceiver {
    public static final String NOTIFICATION_POST_ACTION = "com.myfitnesspal.notification.action.POST";
    @Inject
    JobServiceFactory jobServiceFactory;

    public void onReceive(Context context, Intent intent) {
        ((MyFitnessPalApp) context.getApplicationContext()).component().inject(this);
        if (intent != null) {
            this.jobServiceFactory.setExtras(convertIntentToBundle(intent)).scheduleJob(Job.NOTIFICATION_ACTION);
        }
    }

    private PersistableBundle convertIntentToBundle(Intent intent) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putInt("notification_id", ExtrasUtils.getInt(intent, "notification_id", Integer.MIN_VALUE));
        persistableBundle.putString("action", intent.getAction());
        persistableBundle.putString("url", ExtrasUtils.getString(intent, "url"));
        persistableBundle.putString("body", ExtrasUtils.getString(intent, "body"));
        persistableBundle.putString("utm_campaign", ExtrasUtils.getString(intent, "utm_campaign", ""));
        return persistableBundle;
    }
}
