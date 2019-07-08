package com.myfitnesspal.feature.consents.service;

import io.uacf.consentservices.sdk.UacfConsentIsoCodeProvider;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getIsoCode"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsServiceImpl.kt */
final class ConsentsServiceImpl$getUserConsentStatus$1 implements UacfConsentIsoCodeProvider {
    final /* synthetic */ String $iso;

    ConsentsServiceImpl$getUserConsentStatus$1(String str) {
        this.$iso = str;
    }

    @NotNull
    public final String getIsoCode() {
        return this.$iso;
    }
}
