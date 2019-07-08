package com.myfitnesspal.shared.notification.service;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.receiver.MfpNotificationActionReceiver;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Provider;

public class MfpNotificationActionService extends JobService {
    public static final String EXTRA_BODY = "body";
    public static final String EXTRA_INTENT_ACTION = "action";
    public static final String EXTRA_NOTIFICATION_ID = "notification_id";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_UTM_CAMPAIGN = "utm_campaign";
    public static final int JOB_ID_NOTIFICATION_ACTION = 101;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Provider<MfpV2Api> apiProvider;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public MfpNotificationActionService() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        handleNotificationAction(jobParameters);
        return true;
    }

    /* access modifiers changed from: protected */
    public void handleNotificationAction(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(extras.getInt("notification_id", -1));
            ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
            String string = extras.getString("action");
            String string2 = extras.getString("action");
            String string3 = extras.getString("body");
            String string4 = extras.getString("utm_campaign", "");
            if (Strings.equals(string, MfpNotificationActionReceiver.NOTIFICATION_POST_ACTION) && Strings.notEmpty(string2)) {
                $$Lambda$MfpNotificationActionService$2hh9qFO8Uft6x9Hah4PSim4O87k r3 = new Runnable(string3, apiJsonMapper, string2, jobParameters, string, string4) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ ApiJsonMapper f$2;
                    private final /* synthetic */ String f$3;
                    private final /* synthetic */ JobParameters f$4;
                    private final /* synthetic */ String f$5;
                    private final /* synthetic */ String f$6;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                        this.f$5 = r6;
                        this.f$6 = r7;
                    }

                    public final void run() {
                        MfpNotificationActionService.lambda$handleNotificationAction$0(MfpNotificationActionService.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
                    }
                };
                new Thread(r3);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0054, code lost:
        r3.jobFinished(r7, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0057, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0035, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r4 = new java.lang.StringBuilder();
        r4.append("failed to post to url from notification action");
        r4.append(r6);
        com.uacf.core.util.Ln.e(r4.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0037 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$handleNotificationAction$0(com.myfitnesspal.shared.notification.service.MfpNotificationActionService r3, java.lang.String r4, com.myfitnesspal.shared.model.mapper.ApiJsonMapper r5, java.lang.String r6, android.app.job.JobParameters r7, java.lang.String r8, java.lang.String r9) {
        /*
            r0 = 0
            javax.inject.Provider<com.myfitnesspal.shared.api.v2.MfpV2Api> r1 = r3.apiProvider     // Catch:{ IOException -> 0x0037 }
            java.lang.Object r1 = r1.get()     // Catch:{ IOException -> 0x0037 }
            com.myfitnesspal.shared.api.v2.MfpV2Api r1 = (com.myfitnesspal.shared.api.v2.MfpV2Api) r1     // Catch:{ IOException -> 0x0037 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            com.myfitnesspal.shared.api.MfpApi r1 = r1.withOutputType(r2)     // Catch:{ IOException -> 0x0037 }
            com.myfitnesspal.shared.api.v2.MfpV2Api r1 = (com.myfitnesspal.shared.api.v2.MfpV2Api) r1     // Catch:{ IOException -> 0x0037 }
            boolean r2 = com.uacf.core.util.Strings.notEmpty(r4)     // Catch:{ IOException -> 0x0037 }
            if (r2 == 0) goto L_0x0024
            java.lang.Class<com.google.gson.JsonElement> r2 = com.google.gson.JsonElement.class
            java.lang.Object r4 = r5.mapFrom(r4, r2)     // Catch:{ IOException -> 0x0037 }
            com.google.gson.JsonElement r4 = (com.google.gson.JsonElement) r4     // Catch:{ IOException -> 0x0037 }
            com.google.gson.JsonObject r4 = r4.getAsJsonObject()     // Catch:{ IOException -> 0x0037 }
            goto L_0x0029
        L_0x0024:
            java.lang.Object r4 = new java.lang.Object     // Catch:{ IOException -> 0x0037 }
            r4.<init>()     // Catch:{ IOException -> 0x0037 }
        L_0x0029:
            com.myfitnesspal.shared.api.MfpApi r4 = r1.withJsonBody(r4)     // Catch:{ IOException -> 0x0037 }
            com.myfitnesspal.shared.api.v2.MfpV2Api r4 = (com.myfitnesspal.shared.api.v2.MfpV2Api) r4     // Catch:{ IOException -> 0x0037 }
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x0037 }
            r4.post(r6, r5)     // Catch:{ IOException -> 0x0037 }
            goto L_0x004d
        L_0x0035:
            r4 = move-exception
            goto L_0x0054
        L_0x0037:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r4.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "failed to post to url from notification action"
            r4.append(r5)     // Catch:{ all -> 0x0035 }
            r4.append(r6)     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0035 }
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ all -> 0x0035 }
            com.uacf.core.util.Ln.e(r4, r5)     // Catch:{ all -> 0x0035 }
        L_0x004d:
            r3.jobFinished(r7, r0)
            r3.trackEvent(r8, r9)
            return
        L_0x0054:
            r3.jobFinished(r7, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.notification.service.MfpNotificationActionService.lambda$handleNotificationAction$0(com.myfitnesspal.shared.notification.service.MfpNotificationActionService, java.lang.String, com.myfitnesspal.shared.model.mapper.ApiJsonMapper, java.lang.String, android.app.job.JobParameters, java.lang.String, java.lang.String):void");
    }

    private void trackEvent(String str, String str2) {
        Builder builder = new Builder();
        builder.put("utm_campaign", str2).put("action_type", Strings.toString(str));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_ACTION, builder.build());
    }
}
