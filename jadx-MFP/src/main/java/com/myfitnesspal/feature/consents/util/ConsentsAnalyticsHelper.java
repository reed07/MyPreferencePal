package com.myfitnesspal.feature.consents.util;

import com.myfitnesspal.feature.consents.model.ConsentsViewModel.Mode;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0003H&J\u0018\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0013\u001a\u00020\u0007H&J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\u0015\u001a\u00020\u0007H&J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u000eH&J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\u0007H&J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\u001b\u001a\u00020\u0007H&J\b\u0010\u001c\u001a\u00020\u0007H&J\u0010\u0010\u001d\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\u001e\u001a\u00020\u0007H&J\b\u0010\u001f\u001a\u00020\u0007H&J\b\u0010 \u001a\u00020\u0007H&J\u0010\u0010!\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$H&¨\u0006%"}, d2 = {"Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelper;", "", "getScreenNameBasedOnMode", "", "mode", "Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;", "reportConsentInterruptionAccepted", "", "reportConsentInterruptionNotChecked", "reportConsentInterruptionSee", "consentsCount", "", "reportConsentWithdrawDeleteAccept", "accepted", "", "reportConsentWithdrawIntiated", "consentType", "reportConsentsSeeToAnalytics", "numConsents", "reportCountryChangeComplete", "reportCountryChangeConsentsSee", "reportCountryChangeInitiated", "reportCreateUsernameSee", "hasDisclaimerCheckbox", "reportLearnMoreSee", "reportOnBoardingConsentNotChecked", "reportOnBoardingConsentSee", "reportOnBoardingConsentSkip", "reportOnBoardingConsentsError", "reportOnBoardingConsentsResponse", "reportOnBoardingTOSNotChecked", "reportPrivacyCenterManageConsentsSee", "reportPrivacyCenterSee", "reportUAAffiliatesSee", "setConsentsUserProperties", "consentStatus", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsAnalyticsHelper.kt */
public interface ConsentsAnalyticsHelper {
    @NotNull
    String getScreenNameBasedOnMode(@NotNull Mode mode);

    void reportConsentInterruptionAccepted();

    void reportConsentInterruptionNotChecked();

    void reportConsentInterruptionSee(int i);

    void reportConsentWithdrawDeleteAccept(boolean z);

    void reportConsentWithdrawIntiated(@NotNull String str);

    void reportConsentsSeeToAnalytics(int i, @NotNull Mode mode);

    void reportCountryChangeComplete();

    void reportCountryChangeConsentsSee(int i);

    void reportCountryChangeInitiated();

    void reportCreateUsernameSee(boolean z);

    void reportLearnMoreSee(@NotNull Mode mode, @NotNull String str);

    void reportOnBoardingConsentNotChecked();

    void reportOnBoardingConsentSee(int i);

    void reportOnBoardingConsentSkip();

    void reportOnBoardingConsentsError();

    void reportOnBoardingConsentsResponse(int i);

    void reportOnBoardingTOSNotChecked();

    void reportPrivacyCenterManageConsentsSee();

    void reportPrivacyCenterSee();

    void reportUAAffiliatesSee(@NotNull Mode mode);

    void setConsentsUserProperties(@NotNull UacfConsentStatus uacfConsentStatus);
}
