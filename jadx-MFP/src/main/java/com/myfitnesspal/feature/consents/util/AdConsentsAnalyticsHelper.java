package com.myfitnesspal.feature.consents.util;

import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.Mode;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\"\u0010\u0011\u001a\u00020\u00032\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&J\b\u0010\u0016\u001a\u00020\u0003H&J\b\u0010\u0017\u001a\u00020\u0003H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u0019"}, d2 = {"Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelper;", "", "reportAdConsentsDisplayed", "", "mode", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "consentList", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "reportConsentOffSaved", "consentId", "", "reportConsentSettingsError", "reportConsentTapOffAttempted", "reportConsentTappedOn", "reportInterruptionAccepted", "reportInterruptionError", "reportInterruptionSaveAlertDisplayed", "reportInterruptionSaveTapped", "reportInterruptionSkipAlertDisplayed", "reportInterruptionSkipTapped", "reportInterruptionSkipped", "reportLearnMoreTapped", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdConsentsAnalyticsHelper.kt */
public interface AdConsentsAnalyticsHelper {
    void reportAdConsentsDisplayed(@NotNull Mode mode, @NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list);

    void reportConsentOffSaved(@NotNull String str);

    void reportConsentSettingsError();

    void reportConsentTapOffAttempted(@NotNull String str);

    void reportConsentTappedOn(@NotNull String str);

    void reportInterruptionAccepted(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list);

    void reportInterruptionError();

    void reportInterruptionSaveAlertDisplayed();

    void reportInterruptionSaveTapped();

    void reportInterruptionSkipAlertDisplayed();

    void reportInterruptionSkipTapped();

    void reportInterruptionSkipped();

    void reportLearnMoreTapped(@NotNull String str);
}
