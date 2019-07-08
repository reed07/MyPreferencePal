package com.myfitnesspal.feature.consents.service;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.uacf.consentservices.sdk.UacfConsentResponseStatus;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0006H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u000e\u001a\u00020\fH&J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H&J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004H&J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004H&J\u001b\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "", "getConsentStatus", "Lio/reactivex/Single;", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "iso", "", "getConsentStatusFromCountryName", "countryName", "getUserConsentStatus", "Lio/uacf/consentservices/sdk/UacfConsentResponseStatus;", "isConsentsRequired", "", "uacfConsentStatus", "isUserAcceptedConsentsMatrix", "storeConsentData", "", "matrixVersion", "acceptedCount", "", "storeIsSubjectToAndAcceptedPersonalizedAd", "storeShouldInterruptUserForAdConsents", "updateAdConsentsForInterruption", "uacfUserconsentStatus", "Lio/uacf/core/consents/UacfUserConsentStatus;", "(Lio/uacf/core/consents/UacfUserConsentStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateConsentStatus", "Lio/reactivex/Completable;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsService.kt */
public interface ConsentsService {
    @NotNull
    Single<UacfConsentStatus> getConsentStatus(@NotNull String str);

    @NotNull
    Single<UacfConsentStatus> getConsentStatusFromCountryName(@Nullable String str);

    @NotNull
    UacfConsentResponseStatus getUserConsentStatus(@NotNull String str);

    @NotNull
    Single<Boolean> isConsentsRequired(@NotNull String str);

    boolean isConsentsRequired(@NotNull UacfConsentStatus uacfConsentStatus, @NotNull String str);

    boolean isUserAcceptedConsentsMatrix();

    void storeConsentData(@NotNull UacfConsentStatus uacfConsentStatus, @NotNull String str);

    void storeConsentData(@NotNull String str, int i);

    void storeConsentData(@NotNull String str, int i, @NotNull String str2);

    void storeIsSubjectToAndAcceptedPersonalizedAd(@NotNull UacfConsentStatus uacfConsentStatus);

    void storeShouldInterruptUserForAdConsents(@NotNull UacfConsentStatus uacfConsentStatus);

    @Nullable
    Object updateAdConsentsForInterruption(@Nullable UacfUserConsentStatus uacfUserConsentStatus, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    Completable updateConsentStatus(@Nullable UacfUserConsentStatus uacfUserConsentStatus);
}
