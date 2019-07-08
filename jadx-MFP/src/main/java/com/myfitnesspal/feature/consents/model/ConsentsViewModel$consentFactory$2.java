package com.myfitnesspal.feature.consents.model;

import io.uacf.consentservices.sdk.UacfConsentServiceSdkFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
final class ConsentsViewModel$consentFactory$2 extends Lambda implements Function0<UacfConsentServiceSdkFactory> {
    public static final ConsentsViewModel$consentFactory$2 INSTANCE = new ConsentsViewModel$consentFactory$2();

    ConsentsViewModel$consentFactory$2() {
        super(0);
    }

    @NotNull
    public final UacfConsentServiceSdkFactory invoke() {
        return new UacfConsentServiceSdkFactory();
    }
}
