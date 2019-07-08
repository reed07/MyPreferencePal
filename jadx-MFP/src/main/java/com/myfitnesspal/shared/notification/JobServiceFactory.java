package com.myfitnesspal.shared.notification;

import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import com.myfitnesspal.shared.notification.service.InAppNotificationService;
import com.myfitnesspal.shared.notification.service.MfpNotificationActionService;
import com.myfitnesspal.shared.notification.service.NotificationInvokerService;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/shared/notification/JobServiceFactory;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "extras", "Landroid/os/PersistableBundle;", "jobScheduler", "Landroid/app/job/JobScheduler;", "requiredNetworkType", "", "scheduleJob", "", "job", "Lcom/myfitnesspal/shared/notification/JobServiceFactory$Job;", "setExtras", "setRequiredNetworkType", "Job", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobServiceFactory.kt */
public final class JobServiceFactory {
    private final Context context;
    private PersistableBundle extras;
    private final JobScheduler jobScheduler;
    private int requiredNetworkType = 1;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/shared/notification/JobServiceFactory$Job;", "", "serviceClass", "Ljava/lang/Class;", "jobId", "", "(Ljava/lang/String;ILjava/lang/Class;I)V", "getJobId", "()I", "getServiceClass", "()Ljava/lang/Class;", "NOTIFICATION_INVOKER", "NOTIFICATION_ACTION", "NOTIFICATION_IN_APP", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: JobServiceFactory.kt */
    public enum Job {
        NOTIFICATION_INVOKER(NotificationInvokerService.class, 1),
        NOTIFICATION_ACTION(MfpNotificationActionService.class, 2),
        NOTIFICATION_IN_APP(InAppNotificationService.class, 3);
        
        private final int jobId;
        @NotNull
        private final Class<?> serviceClass;

        protected Job(Class<?> cls, int i) {
            Intrinsics.checkParameterIsNotNull(cls, "serviceClass");
            this.serviceClass = cls;
            this.jobId = i;
        }

        @NotNull
        public final Class<?> getServiceClass() {
            return this.serviceClass;
        }

        public final int getJobId() {
            return this.jobId;
        }
    }

    public JobServiceFactory(@NotNull Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        Object systemService = this.context.getSystemService("jobscheduler");
        if (systemService != null) {
            this.jobScheduler = (JobScheduler) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.job.JobScheduler");
    }

    public final void scheduleJob(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "job");
        Builder builder = new Builder(job.ordinal(), new ComponentName(this.context, job.getServiceClass()));
        PersistableBundle persistableBundle = this.extras;
        if (persistableBundle != null) {
            builder.setExtras(persistableBundle);
        }
        builder.setRequiredNetworkType(this.requiredNetworkType);
        this.jobScheduler.schedule(builder.build());
    }

    @NotNull
    public final JobServiceFactory setExtras(@NotNull PersistableBundle persistableBundle) {
        Intrinsics.checkParameterIsNotNull(persistableBundle, "extras");
        this.extras = persistableBundle;
        return this;
    }

    @NotNull
    public final JobServiceFactory setRequiredNetworkType(int i) {
        this.requiredNetworkType = i;
        return this;
    }
}
