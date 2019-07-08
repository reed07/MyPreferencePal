package com.myfitnesspal.shared.service;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import com.myfitnesspal.shared.notification.JobServiceFactory.Job;
import dagger.Lazy;
import javax.inject.Inject;

public class BackgroundJobHelperImpl implements BackgroundJobHelper {
    private final Context context;
    @Inject
    Lazy<JobServiceFactory> jobServiceFactory;

    public BackgroundJobHelperImpl(Context context2) {
        this.context = context2;
        ((MyFitnessPalApp) context2.getApplicationContext()).component().inject(this);
    }

    public boolean isJobRunning(int i) {
        for (JobInfo id : ((JobScheduler) this.context.getSystemService("jobscheduler")).getAllPendingJobs()) {
            if (id.getId() == Job.NOTIFICATION_IN_APP.getJobId()) {
                return true;
            }
        }
        return false;
    }

    public void updateInAppNotifications() {
        ((JobServiceFactory) this.jobServiceFactory.get()).scheduleJob(Job.NOTIFICATION_IN_APP);
    }
}
