package com.myfitnesspal.feature.consents.service;

import io.uacf.core.consents.UacfUserConsentStatus;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsServiceImpl.kt */
final class ConsentsServiceImpl$updateConsentStatus$1<V> implements Callable<Object> {
    final /* synthetic */ UacfUserConsentStatus $uacfUserconsentStatus;
    final /* synthetic */ ConsentsServiceImpl this$0;

    ConsentsServiceImpl$updateConsentStatus$1(ConsentsServiceImpl consentsServiceImpl, UacfUserConsentStatus uacfUserConsentStatus) {
        this.this$0 = consentsServiceImpl;
        this.$uacfUserconsentStatus = uacfUserConsentStatus;
    }

    public final void call() {
        this.this$0.updateConsents(this.$uacfUserconsentStatus);
    }
}
