package com.myfitnesspal.shared.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import com.myfitnesspal.shared.notification.JobServiceFactory.Job;
import javax.inject.Inject;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public static final String EXTRA_NOTIFICATION_ID = "notification_id";
    public static final String EXTRA_NOTIFICATION_TYPE = "notification_type";
    public static final String EXTRA_RESOURCE_ID = "notification_resource_id";
    public static final String EXTRA_URL = "notification_url";
    @Inject
    JobServiceFactory jobServiceFactory;

    public void onReceive(Context context, Intent intent) {
        ((MyFitnessPalApp) context.getApplicationContext()).component().inject(this);
        this.jobServiceFactory.setExtras(convertIntentToJobParameters(intent)).scheduleJob(Job.NOTIFICATION_INVOKER);
    }

    private PersistableBundle convertIntentToJobParameters(Intent intent) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(EXTRA_NOTIFICATION_TYPE, intent.getStringExtra(EXTRA_NOTIFICATION_TYPE));
        persistableBundle.putInt("notification_id", intent.getIntExtra("notification_id", 0));
        persistableBundle.putLong(EXTRA_RESOURCE_ID, intent.getLongExtra(EXTRA_RESOURCE_ID, 0));
        persistableBundle.putString(EXTRA_URL, intent.getStringExtra(EXTRA_URL));
        return persistableBundle;
    }
}
