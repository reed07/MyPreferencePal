package com.myfitnesspal.feature.consents.model;

import io.uacf.consentservices.sdk.UacfConsentIsoCodeProvider;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "kotlin.jvm.PlatformType", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
final class ConsentsViewModel$getConsentStatus$1<V> implements Callable<T> {
    final /* synthetic */ ConsentsViewModel this$0;

    ConsentsViewModel$getConsentStatus$1(ConsentsViewModel consentsViewModel) {
        this.this$0 = consentsViewModel;
    }

    public final UacfConsentStatus call() {
        return this.this$0.getConsentServiceSdk().getConsentStatus(new UacfConsentIsoCodeProvider(this) {
            final /* synthetic */ ConsentsViewModel$getConsentStatus$1 this$0;

            {
                this.this$0 = r1;
            }

            @NotNull
            public final String getIsoCode() {
                return this.this$0.this$0.iso;
            }
        });
    }
}
