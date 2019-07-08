package com.myfitnesspal.feature.consents.model;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext.Key;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CoroutineExceptionHandler.kt */
public final class AdConsentsViewModel$updateAdConsents$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ AdConsentsViewModel this$0;

    public AdConsentsViewModel$updateAdConsents$$inlined$CoroutineExceptionHandler$1(Key key, AdConsentsViewModel adConsentsViewModel) {
        this.this$0 = adConsentsViewModel;
        super(key);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r2 != null) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleException(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r2, @org.jetbrains.annotations.NotNull java.lang.Throwable r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r2 = "exception"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2)
            com.myfitnesspal.feature.consents.model.AdConsentsViewModel r2 = r1.this$0
            com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper r2 = r2.adConsentsAnalyticsHelper
            r2.reportInterruptionError()
            com.myfitnesspal.shared.util.CrashlyticsUtil.logIfEnabled(r3)
            boolean r2 = r3 instanceof io.uacf.core.api.UacfApiException
            if (r2 != 0) goto L_0x001b
            r3 = 0
        L_0x001b:
            io.uacf.core.api.UacfApiException r3 = (io.uacf.core.api.UacfApiException) r3
            if (r3 == 0) goto L_0x0026
            java.lang.String r2 = r3.toString()
            if (r2 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            java.lang.String r2 = "Failed PATCH Ad Consents"
        L_0x0028:
            com.myfitnesspal.shared.util.CrashlyticsUtil.logIfEnabled(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.consents.model.AdConsentsViewModel$updateAdConsents$$inlined$CoroutineExceptionHandler$1.handleException(kotlin.coroutines.CoroutineContext, java.lang.Throwable):void");
    }
}
