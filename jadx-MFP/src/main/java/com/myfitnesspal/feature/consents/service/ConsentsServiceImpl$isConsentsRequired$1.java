package com.myfitnesspal.feature.consents.service;

import io.reactivex.functions.Function;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsServiceImpl.kt */
final class ConsentsServiceImpl$isConsentsRequired$1<T, R> implements Function<T, R> {
    final /* synthetic */ String $iso;
    final /* synthetic */ ConsentsServiceImpl this$0;

    ConsentsServiceImpl$isConsentsRequired$1(ConsentsServiceImpl consentsServiceImpl, String str) {
        this.this$0 = consentsServiceImpl;
        this.$iso = str;
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Boolean.valueOf(apply((UacfConsentStatus) obj));
    }

    public final boolean apply(@NotNull UacfConsentStatus uacfConsentStatus) {
        Intrinsics.checkParameterIsNotNull(uacfConsentStatus, "it");
        return this.this$0.isConsentsRequired(uacfConsentStatus, this.$iso);
    }
}
