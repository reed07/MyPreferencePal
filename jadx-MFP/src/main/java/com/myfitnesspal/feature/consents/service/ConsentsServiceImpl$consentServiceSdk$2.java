package com.myfitnesspal.feature.consents.service;

import io.uacf.consentservices.sdk.UacfConsentServiceSdk;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsServiceImpl.kt */
final class ConsentsServiceImpl$consentServiceSdk$2 extends Lambda implements Function0<UacfConsentServiceSdk> {
    final /* synthetic */ ConsentsServiceImpl this$0;

    ConsentsServiceImpl$consentServiceSdk$2(ConsentsServiceImpl consentsServiceImpl) {
        this.this$0 = consentsServiceImpl;
        super(0);
    }

    public final UacfConsentServiceSdk invoke() {
        return this.this$0.getConsentFactory().newSdkInstance(this.this$0.getContext());
    }
}
