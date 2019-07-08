package com.myfitnesspal.shared.service.analytics;

import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v2.MfpAnalyticsEvent;
import com.myfitnesspal.shared.model.v2.MfpAnalyticsEventsContainer;
import com.uacf.core.util.CollectionUtils;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import javax.inject.Provider;

public class AnalyticsSyncOp extends UacfScheduleOpBase {
    private static final int MAX_BATCH_SIZE = 20;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int[] RETRY_TIMEOUTS_MS = {1000, 15000, 30000};
    private final Provider<MfpV2Api> api;
    private final Lazy<AuthTokenProvider> authTokens;
    private final String clientId;
    private final UUID deviceId;
    private MfpAnalyticsTask failedTask;
    private final Lazy<MfpAnalyticsTaskQueue> queue;
    private int retryCount = 0;

    public AnalyticsSyncOp(Lazy<MfpAnalyticsTaskQueue> lazy, Provider<MfpV2Api> provider, UUID uuid, String str, Lazy<AuthTokenProvider> lazy2) {
        this.queue = lazy;
        this.api = provider;
        this.deviceId = uuid;
        this.clientId = str;
        this.authTokens = lazy2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.sync.engine.UacfScheduleOp.Result sync(com.uacf.sync.engine.UacfScheduleContext r7, com.uacf.sync.engine.UacfScheduleOp.Progress r8) throws com.uacf.sync.engine.UacfScheduleException {
        /*
            r6 = this;
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            java.lang.String r8 = "AnalyticsSyncOp"
            r7.setName(r8)
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTask r7 = r6.failedTask
            if (r7 != 0) goto L_0x0026
            boolean r7 = com.myfitnesspal.shared.util.ConnectivityUtil.isOnline()
            if (r7 == 0) goto L_0x0021
            dagger.Lazy<com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue> r7 = r6.queue
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue r7 = (com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0026
        L_0x0021:
            com.uacf.sync.engine.UacfScheduleOp$Result r7 = com.uacf.sync.engine.UacfScheduleOp.Result.completed()
            return r7
        L_0x0026:
            r7 = 1
            r8 = 0
            r0 = 0
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTask r1 = r6.failedTask     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            if (r1 == 0) goto L_0x0039
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTask r1 = r6.failedTask     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            r6.failedTask = r8     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            goto L_0x0050
        L_0x0032:
            r8 = move-exception
            goto L_0x00ce
        L_0x0035:
            r7 = move-exception
            r8 = r1
            goto L_0x00b7
        L_0x0039:
            dagger.Lazy<com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue> r1 = r6.queue     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            java.lang.Object r1 = r1.get()     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue r1 = (com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue) r1     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            java.lang.Object r1 = r1.peek()     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            if (r1 != 0) goto L_0x004c
            com.uacf.sync.engine.UacfScheduleOp$Result r7 = com.uacf.sync.engine.UacfScheduleOp.Result.completed()     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
            return r7
        L_0x004c:
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTask r1 = r6.readBatch()     // Catch:{ ApiException -> 0x00b6, all -> 0x00b2 }
        L_0x0050:
            java.lang.String r2 = r1.getSerializedData()     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            javax.inject.Provider<com.myfitnesspal.shared.api.v2.MfpV2Api> r3 = r6.api     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            java.lang.Object r3 = r3.get()     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            com.myfitnesspal.shared.api.v2.MfpV2Api r3 = (com.myfitnesspal.shared.api.v2.MfpV2Api) r3     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            java.lang.Class<com.myfitnesspal.shared.model.v2.MfpAnalyticsResponse> r4 = com.myfitnesspal.shared.model.v2.MfpAnalyticsResponse.class
            com.myfitnesspal.shared.api.MfpApi r4 = r3.withOutputType(r4)     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            com.myfitnesspal.shared.api.v2.MfpV2Api r4 = (com.myfitnesspal.shared.api.v2.MfpV2Api) r4     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            java.lang.String r5 = "application/json"
            com.myfitnesspal.shared.api.MfpApi r2 = r4.withBody(r2, r5)     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            com.myfitnesspal.shared.api.v2.MfpV2Api r2 = (com.myfitnesspal.shared.api.v2.MfpV2Api) r2     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            java.lang.String r4 = "/v2/events"
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            r2.post(r4, r5)     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            int r2 = r3.getResponseCode()     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 < r4) goto L_0x0083
            int r2 = r3.getResponseCode()     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            r3 = 299(0x12b, float:4.19E-43)
            if (r2 <= r3) goto L_0x0084
        L_0x0083:
            r7 = 0
        L_0x0084:
            if (r7 != 0) goto L_0x0096
            java.lang.String r2 = "error in sending item, bailing now"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            com.uacf.core.util.Ln.d(r2, r3)     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            com.uacf.sync.engine.UacfScheduleOp$Result r8 = r6.getRetryResultAndIncrementCount(r8)     // Catch:{ ApiException -> 0x0035, all -> 0x0032 }
            if (r7 != 0) goto L_0x0095
            r6.failedTask = r1
        L_0x0095:
            return r8
        L_0x0096:
            if (r7 != 0) goto L_0x009a
            r6.failedTask = r1
        L_0x009a:
            dagger.Lazy<com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue> r7 = r6.queue
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue r7 = (com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue) r7
            boolean r7 = r7.notEmpty()
            if (r7 == 0) goto L_0x00ad
            com.uacf.sync.engine.UacfScheduleOp$Result r7 = com.uacf.sync.engine.UacfScheduleOp.Result.yield()
            goto L_0x00b1
        L_0x00ad:
            com.uacf.sync.engine.UacfScheduleOp$Result r7 = com.uacf.sync.engine.UacfScheduleOp.Result.completed()
        L_0x00b1:
            return r7
        L_0x00b2:
            r0 = move-exception
            r1 = r8
            r8 = r0
            goto L_0x00ce
        L_0x00b6:
            r7 = move-exception
        L_0x00b7:
            java.lang.String r1 = "exception in sending item, bailing now"
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ all -> 0x00ca }
            com.uacf.core.util.Ln.d(r1, r2)     // Catch:{ all -> 0x00ca }
            com.myfitnesspal.shared.service.syncv2.SyncExceptions$ApiSyncException r1 = new com.myfitnesspal.shared.service.syncv2.SyncExceptions$ApiSyncException     // Catch:{ all -> 0x00ca }
            r1.<init>(r7)     // Catch:{ all -> 0x00ca }
            com.uacf.sync.engine.UacfScheduleOp$Result r7 = r6.getRetryResultAndIncrementCount(r1)     // Catch:{ all -> 0x00ca }
            r6.failedTask = r8
            return r7
        L_0x00ca:
            r7 = move-exception
            r1 = r8
            r8 = r7
            r7 = 0
        L_0x00ce:
            if (r7 != 0) goto L_0x00d2
            r6.failedTask = r1
        L_0x00d2:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.analytics.AnalyticsSyncOp.sync(com.uacf.sync.engine.UacfScheduleContext, com.uacf.sync.engine.UacfScheduleOp$Progress):com.uacf.sync.engine.UacfScheduleOp$Result");
    }

    public void onRetriesExhausted() {
        super.onRetriesExhausted();
        this.failedTask = null;
    }

    private Result getRetryResultAndIncrementCount(UacfScheduleException uacfScheduleException) {
        int[] iArr = RETRY_TIMEOUTS_MS;
        int i = this.retryCount;
        int i2 = iArr[i];
        if (i < iArr.length - 1) {
            this.retryCount = i + 1;
        } else {
            this.retryCount = 0;
        }
        return Result.retry(uacfScheduleException).withDelayMillis(i2);
    }

    private static MfpAnalyticsEvent createBatchSizeEvent(MfpAnalyticsTask mfpAnalyticsTask) {
        MfpAnalyticsEvent mfpAnalyticsEvent = new MfpAnalyticsEvent();
        mfpAnalyticsEvent.setType(Events.ANALYTICS_BATCH_SIZE);
        mfpAnalyticsEvent.addAttribute("count", Integer.valueOf(mfpAnalyticsTask.getEventContainer().getEvents().size()));
        mfpAnalyticsEvent.setTimestamp(new Date());
        return mfpAnalyticsEvent;
    }

    private MfpAnalyticsTask readBatch() {
        MfpAnalyticsTask mfpAnalyticsTask = new MfpAnalyticsTask(this.deviceId, this.clientId, ((AuthTokenProvider) this.authTokens.get()).getDomainUserId());
        int i = 20;
        boolean z = false;
        while (!z) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            MfpAnalyticsTask mfpAnalyticsTask2 = (MfpAnalyticsTask) ((MfpAnalyticsTaskQueue) this.queue.get()).peek();
            if (mfpAnalyticsTask2 != null) {
                ((MfpAnalyticsTaskQueue) this.queue.get()).remove();
                MfpAnalyticsEventsContainer eventContainer = mfpAnalyticsTask2.getEventContainer();
                if (eventContainer != null && CollectionUtils.notEmpty((Collection<?>) eventContainer.getEvents())) {
                    for (MfpAnalyticsEvent add : eventContainer.getEvents()) {
                        mfpAnalyticsTask.add(add);
                    }
                }
            } else {
                z = true;
            }
            i = i2;
        }
        mfpAnalyticsTask.getEventContainer().add(createBatchSizeEvent(mfpAnalyticsTask));
        return mfpAnalyticsTask;
    }
}
