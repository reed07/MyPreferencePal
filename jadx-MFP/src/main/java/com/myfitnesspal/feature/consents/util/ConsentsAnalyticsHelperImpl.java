package com.myfitnesspal.feature.consents.util;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel.Mode;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 +2\u00020\u0001:\u0001+B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0014H\u0016J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\tH\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010!\u001a\u00020\rH\u0016J\b\u0010\"\u001a\u00020\rH\u0016J\u0010\u0010#\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010$\u001a\u00020\rH\u0016J\b\u0010%\u001a\u00020\rH\u0016J\b\u0010&\u001a\u00020\rH\u0016J\u0010\u0010'\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020*H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006,"}, d2 = {"Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelper;", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "getScreenNameBasedOnMode", "", "mode", "Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;", "reportConsentInterruptionAccepted", "", "reportConsentInterruptionNotChecked", "reportConsentInterruptionSee", "consentsCount", "", "reportConsentWithdrawDeleteAccept", "accepted", "", "reportConsentWithdrawIntiated", "consentType", "reportConsentsSeeToAnalytics", "numConsents", "reportCountryChangeComplete", "reportCountryChangeConsentsSee", "reportCountryChangeInitiated", "reportCreateUsernameSee", "hasDisclaimerCheckbox", "reportLearnMoreSee", "reportOnBoardingConsentNotChecked", "reportOnBoardingConsentSee", "reportOnBoardingConsentSkip", "reportOnBoardingConsentsError", "reportOnBoardingConsentsResponse", "reportOnBoardingTOSNotChecked", "reportPrivacyCenterManageConsentsSee", "reportPrivacyCenterSee", "reportUAAffiliatesSee", "setConsentsUserProperties", "consentStatus", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsAnalyticsHelperImpl.kt */
public final class ConsentsAnalyticsHelperImpl implements ConsentsAnalyticsHelper {
    private static final String CONSENT_INTERRUPTION_ACCPETED = "consent_interruption_accepted";
    @NotNull
    public static final String CONSENT_INTERRUPTION_SEE = "consent_interruption_see";
    private static final String CONSENT_INTERRUPTUON_NOT_CHECKED = "consent_interruption_not_checked";
    private static final String COUNTRY_CHANGE_COMPLETE = "country_change_complete";
    @NotNull
    public static final String COUNTRY_CHANGE_CONSENTS_SEE = "country_change_consents_see";
    private static final String COUNTRY_CHANGE_INITIATED = "country_change_initiated";
    @NotNull
    public static final String CREATE_USERNAME_SEE = "create_username_see";
    public static final Companion Companion = new Companion(null);
    private static final String LEARN_MORE_SEE = "consent_learn_more_see";
    private static final String NUMBER_OF_CONSENTS = "number_of_consents";
    private static final String ONBOARDING_CONSENT_ERROR = "onboarding_consents_error";
    private static final String ONBOARDING_CONSENT_RESPONSE = "onboarding_consent_reponse";
    @NotNull
    public static final String ONBOARDING_CONSENT_SEE = "onboarding_consent_see";
    private static final String ONBOARDING_REQUIRED_CONSENT_UNCHECKED = "onboarding_required_consents_not_checked";
    private static final String ONBOARDING_TOS_NOT_CHECKED = "create_username_see_terms_of_service_not_checked";
    private static final String ONBOARIDNG_CONSENT_SKIP = "onboarding_consent_skip";
    @NotNull
    public static final String PRIVACY_CENTER_CONSENTS_SEE = "privacy_center_consents_see";
    private static final String PRIVACY_CENTER_CONSENTS_WITHDRAW_INTIATED = "privacy_center_consents_withdraw_initiated";
    @NotNull
    public static final String PRIVACY_CENTER_SEE = "privacy_center_see";
    private static final String PRIVACY_CENTER_WITHDRAW_ACCEPTED = "privacy_center_consents_withdraw_modal_accepted";
    private static final String PRIVACY_CENTER_WITHDRAW_CANCELED = "privacy_center_consents_withdraw_modal_canceled";
    private static final String TOS_PRESENT = "terms_of_service_checkbox_present";
    private static final String UA_AFFILIATES_SEE = "ua_affiliates_see";
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelperImpl$Companion;", "", "()V", "CONSENT_INTERRUPTION_ACCPETED", "", "CONSENT_INTERRUPTION_SEE", "CONSENT_INTERRUPTUON_NOT_CHECKED", "COUNTRY_CHANGE_COMPLETE", "COUNTRY_CHANGE_CONSENTS_SEE", "COUNTRY_CHANGE_INITIATED", "CREATE_USERNAME_SEE", "LEARN_MORE_SEE", "NUMBER_OF_CONSENTS", "ONBOARDING_CONSENT_ERROR", "ONBOARDING_CONSENT_RESPONSE", "ONBOARDING_CONSENT_SEE", "ONBOARDING_REQUIRED_CONSENT_UNCHECKED", "ONBOARDING_TOS_NOT_CHECKED", "ONBOARIDNG_CONSENT_SKIP", "PRIVACY_CENTER_CONSENTS_SEE", "PRIVACY_CENTER_CONSENTS_WITHDRAW_INTIATED", "PRIVACY_CENTER_SEE", "PRIVACY_CENTER_WITHDRAW_ACCEPTED", "PRIVACY_CENTER_WITHDRAW_CANCELED", "TOS_PRESENT", "UA_AFFILIATES_SEE", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConsentsAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ConsentsAnalyticsHelperImpl(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public void reportCreateUsernameSee(boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(CREATE_USERNAME_SEE, MapUtil.createMap(TOS_PRESENT, String.valueOf(z)));
    }

    public void reportOnBoardingTOSNotChecked() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ONBOARDING_TOS_NOT_CHECKED);
    }

    public void reportOnBoardingConsentsResponse(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ONBOARDING_CONSENT_RESPONSE, MapUtil.createMap(NUMBER_OF_CONSENTS, String.valueOf(i)));
    }

    public void reportOnBoardingConsentsError() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ONBOARDING_CONSENT_ERROR);
    }

    public void reportOnBoardingConsentNotChecked() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ONBOARDING_REQUIRED_CONSENT_UNCHECKED);
    }

    public void setConsentsUserProperties(@NotNull UacfConsentStatus uacfConsentStatus) {
        Intrinsics.checkParameterIsNotNull(uacfConsentStatus, "consentStatus");
        StringBuilder sb = new StringBuilder();
        sb.append("An operation is not implemented: ");
        sb.append("not implemented");
        throw new NotImplementedError(sb.toString());
    }

    public void reportOnBoardingConsentSee(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ONBOARDING_CONSENT_SEE, MapUtil.createMap(NUMBER_OF_CONSENTS, String.valueOf(i)));
    }

    public void reportOnBoardingConsentSkip() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ONBOARIDNG_CONSENT_SKIP);
    }

    public void reportCountryChangeInitiated() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(COUNTRY_CHANGE_INITIATED);
    }

    public void reportCountryChangeConsentsSee(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(COUNTRY_CHANGE_CONSENTS_SEE, MapUtil.createMap(NUMBER_OF_CONSENTS, String.valueOf(i)));
    }

    public void reportCountryChangeComplete() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(COUNTRY_CHANGE_COMPLETE);
    }

    public void reportPrivacyCenterManageConsentsSee() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(PRIVACY_CENTER_CONSENTS_SEE);
    }

    public void reportConsentWithdrawIntiated(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "consentType");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(PRIVACY_CENTER_CONSENTS_WITHDRAW_INTIATED, MapUtil.createMap(Attributes.CONSENT_TYPE, str));
    }

    public void reportConsentWithdrawDeleteAccept(boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(z ? PRIVACY_CENTER_WITHDRAW_ACCEPTED : PRIVACY_CENTER_WITHDRAW_CANCELED);
    }

    public void reportConsentInterruptionSee(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(CONSENT_INTERRUPTION_SEE, MapUtil.createMap(NUMBER_OF_CONSENTS, String.valueOf(i)));
    }

    public void reportConsentInterruptionAccepted() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(CONSENT_INTERRUPTION_ACCPETED);
    }

    public void reportConsentInterruptionNotChecked() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(CONSENT_INTERRUPTUON_NOT_CHECKED);
    }

    public void reportPrivacyCenterSee() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(PRIVACY_CENTER_SEE);
    }

    public void reportUAAffiliatesSee(@NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(UA_AFFILIATES_SEE, MapUtil.createMap("source", getScreenNameBasedOnMode(mode)));
    }

    public void reportLearnMoreSee(@NotNull Mode mode, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        Intrinsics.checkParameterIsNotNull(str, "consentType");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(LEARN_MORE_SEE, MapUtil.createMap("source", getScreenNameBasedOnMode(mode), Attributes.CONSENT_TYPE, str));
    }

    @NotNull
    public String getScreenNameBasedOnMode(@NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        switch (mode) {
            case NEW:
                return ONBOARDING_CONSENT_SEE;
            case EXISTING_ADD:
                return CONSENT_INTERRUPTION_SEE;
            case EXISTING_EDIT_CONSENT:
                return PRIVACY_CENTER_CONSENTS_SEE;
            case EXISTING_EDIT_COUNTRY:
                return COUNTRY_CHANGE_CONSENTS_SEE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public void reportConsentsSeeToAnalytics(int i, @NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        switch (mode) {
            case NEW:
                reportOnBoardingConsentSee(i);
                return;
            case EXISTING_ADD:
                reportConsentInterruptionSee(i);
                return;
            case EXISTING_EDIT_CONSENT:
                reportPrivacyCenterManageConsentsSee();
                return;
            case EXISTING_EDIT_COUNTRY:
                reportCountryChangeConsentsSee(i);
                return;
            default:
                return;
        }
    }
}
