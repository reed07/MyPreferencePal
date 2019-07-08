package com.myfitnesspal.feature.registration.task;

import com.myfitnesspal.feature.registration.service.PrefetchService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;

public class PrefetchTask extends EventedTaskBase<Result, Exception> {
    private final Lazy<PrefetchService> prefetchService;

    public static class CompletedEvent extends TaskEventBase<Result, ApiException> {
    }

    public enum Result {
        Success,
        RequiredOperationFailed
    }

    public PrefetchTask(Lazy<PrefetchService> lazy) {
        super(CompletedEvent.class);
        this.prefetchService = lazy;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:1|2|3|4|5|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x002b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.feature.registration.task.PrefetchTask.Result exec(android.content.Context r5) throws java.lang.Exception {
        /*
            r4 = this;
            java.util.concurrent.CountDownLatch r5 = new java.util.concurrent.CountDownLatch
            r0 = 1
            r5.<init>(r0)
            com.uacf.core.util.Holder r0 = new com.uacf.core.util.Holder
            r0.<init>()
            r1 = 0
            dagger.Lazy<com.myfitnesspal.feature.registration.service.PrefetchService> r2 = r4.prefetchService     // Catch:{ all -> 0x0053 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0053 }
            com.myfitnesspal.feature.registration.service.PrefetchService r2 = (com.myfitnesspal.feature.registration.service.PrefetchService) r2     // Catch:{ all -> 0x0053 }
            com.myfitnesspal.feature.registration.task.PrefetchTask$1 r3 = new com.myfitnesspal.feature.registration.task.PrefetchTask$1     // Catch:{ all -> 0x0053 }
            r3.<init>(r0, r5)     // Catch:{ all -> 0x0053 }
            r2.setOnCompletedListener(r3)     // Catch:{ all -> 0x0053 }
            dagger.Lazy<com.myfitnesspal.feature.registration.service.PrefetchService> r2 = r4.prefetchService     // Catch:{ all -> 0x0053 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0053 }
            com.myfitnesspal.feature.registration.service.PrefetchService r2 = (com.myfitnesspal.feature.registration.service.PrefetchService) r2     // Catch:{ all -> 0x0053 }
            r2.prefetch()     // Catch:{ all -> 0x0053 }
            r5.await()     // Catch:{ InterruptedException -> 0x002b }
            goto L_0x0030
        L_0x002b:
            com.myfitnesspal.feature.registration.task.PrefetchTask$Result r5 = com.myfitnesspal.feature.registration.task.PrefetchTask.Result.Success     // Catch:{ all -> 0x0053 }
            r0.setValue(r5)     // Catch:{ all -> 0x0053 }
        L_0x0030:
            dagger.Lazy<com.myfitnesspal.feature.registration.service.PrefetchService> r5 = r4.prefetchService
            java.lang.Object r5 = r5.get()
            com.myfitnesspal.feature.registration.service.PrefetchService r5 = (com.myfitnesspal.feature.registration.service.PrefetchService) r5
            r5.setOnCompletedListener(r1)
            dagger.Lazy<com.myfitnesspal.feature.registration.service.PrefetchService> r5 = r4.prefetchService
            java.lang.Object r5 = r5.get()
            com.myfitnesspal.feature.registration.service.PrefetchService r5 = (com.myfitnesspal.feature.registration.service.PrefetchService) r5
            java.lang.Object r1 = r0.getValue()
            com.myfitnesspal.feature.registration.task.PrefetchTask$Result r1 = (com.myfitnesspal.feature.registration.task.PrefetchTask.Result) r1
            r5.setTaskResult(r1)
            java.lang.Object r5 = r0.getValue()
            com.myfitnesspal.feature.registration.task.PrefetchTask$Result r5 = (com.myfitnesspal.feature.registration.task.PrefetchTask.Result) r5
            return r5
        L_0x0053:
            r5 = move-exception
            dagger.Lazy<com.myfitnesspal.feature.registration.service.PrefetchService> r2 = r4.prefetchService
            java.lang.Object r2 = r2.get()
            com.myfitnesspal.feature.registration.service.PrefetchService r2 = (com.myfitnesspal.feature.registration.service.PrefetchService) r2
            r2.setOnCompletedListener(r1)
            dagger.Lazy<com.myfitnesspal.feature.registration.service.PrefetchService> r1 = r4.prefetchService
            java.lang.Object r1 = r1.get()
            com.myfitnesspal.feature.registration.service.PrefetchService r1 = (com.myfitnesspal.feature.registration.service.PrefetchService) r1
            java.lang.Object r0 = r0.getValue()
            com.myfitnesspal.feature.registration.task.PrefetchTask$Result r0 = (com.myfitnesspal.feature.registration.task.PrefetchTask.Result) r0
            r1.setTaskResult(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.registration.task.PrefetchTask.exec(android.content.Context):com.myfitnesspal.feature.registration.task.PrefetchTask$Result");
    }
}
