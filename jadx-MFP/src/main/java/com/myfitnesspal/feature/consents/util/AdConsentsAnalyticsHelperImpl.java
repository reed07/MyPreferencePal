package com.myfitnesspal.feature.consents.util;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.Mode;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e0\rH\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\tH\u0016J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\"\u0010\u0017\u001a\u00020\t2\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e0\rH\u0016J\b\u0010\u0018\u001a\u00020\tH\u0016J\b\u0010\u0019\u001a\u00020\tH\u0016J\b\u0010\u001a\u001a\u00020\tH\u0016J\b\u0010\u001b\u001a\u00020\tH\u0016J\b\u0010\u001c\u001a\u00020\tH\u0016J\b\u0010\u001d\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006 "}, d2 = {"Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelper;", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "reportAdConsentsDisplayed", "", "mode", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "consentList", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "reportConsentOffSaved", "consentId", "", "reportConsentSettingsError", "reportConsentTapOffAttempted", "reportConsentTappedOn", "reportInterruptionAccepted", "reportInterruptionError", "reportInterruptionSaveAlertDisplayed", "reportInterruptionSaveTapped", "reportInterruptionSkipAlertDisplayed", "reportInterruptionSkipTapped", "reportInterruptionSkipped", "reportLearnMoreTapped", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdConsentsAnalyticsHelperImpl.kt */
public final class AdConsentsAnalyticsHelperImpl implements AdConsentsAnalyticsHelper {
    private static final String AD_CONSENT_INTERRUPTION_ACCEPTED = "ad_consent_interruption_accepted";
    private static final String AD_CONSENT_INTERRUPTION_DISPLAYED = "ad_consent_interruption_displayed";
    private static final String AD_CONSENT_INTERRUPTION_ERROR = "ad_consent_interruption_error";
    private static final String AD_CONSENT_INTERRUPTION_LEARN_MORE_TAPPED = "ad_consent_interruption_learn_more_tapped";
    private static final String AD_CONSENT_INTERRUPTION_SAVE_ALERT_DISPLAYED = "ad_consent_interruption_save_alert_displayed";
    private static final String AD_CONSENT_INTERRUPTION_SAVE_TAPPED = "ad_consent_interruption_save_tapped";
    private static final String AD_CONSENT_INTERRUPTION_SKIPPED = "ad_consent_interruption_skipped";
    private static final String AD_CONSENT_INTERRUPTION_SKIP_ALERT_DISPLAYED = "ad_consent_interruption_skip_alert_displayed";
    private static final String AD_CONSENT_INTERRUPTION_SKIP_TAPPED = "ad_consent_interruption_skip_tapped";
    private static final String AD_CONSENT_SETTINGS_CONSENT_SAVED_OFF = "ad_consent_settings_consent_saved_off";
    private static final String AD_CONSENT_SETTINGS_CONSENT_TAPPED_ON = "ad_consent_settings_tapped_on";
    private static final String AD_CONSENT_SETTINGS_CONSENT_TAP_OFF_ATTEMPTED = "ad_consent_tap_off_attempted";
    private static final String AD_CONSENT_SETTINGS_DISPLAYED = "ad_consent_settings_viewed";
    private static final String AD_CONSENT_SETTINGS_ERROR = "ad_consent_settings_error";
    private static final String COUNT = "count";
    public static final Companion Companion = new Companion(null);
    private static final String TYPE = "type";
    private static final String TYPES = "types";
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelperImpl$Companion;", "", "()V", "AD_CONSENT_INTERRUPTION_ACCEPTED", "", "AD_CONSENT_INTERRUPTION_DISPLAYED", "AD_CONSENT_INTERRUPTION_ERROR", "AD_CONSENT_INTERRUPTION_LEARN_MORE_TAPPED", "AD_CONSENT_INTERRUPTION_SAVE_ALERT_DISPLAYED", "AD_CONSENT_INTERRUPTION_SAVE_TAPPED", "AD_CONSENT_INTERRUPTION_SKIPPED", "AD_CONSENT_INTERRUPTION_SKIP_ALERT_DISPLAYED", "AD_CONSENT_INTERRUPTION_SKIP_TAPPED", "AD_CONSENT_SETTINGS_CONSENT_SAVED_OFF", "AD_CONSENT_SETTINGS_CONSENT_TAPPED_ON", "AD_CONSENT_SETTINGS_CONSENT_TAP_OFF_ATTEMPTED", "AD_CONSENT_SETTINGS_DISPLAYED", "AD_CONSENT_SETTINGS_ERROR", "COUNT", "TYPE", "TYPES", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AdConsentsAnalyticsHelperImpl(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public void reportAdConsentsDisplayed(@NotNull Mode mode, @NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        String str;
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        Intrinsics.checkParameterIsNotNull(list, "consentList");
        List arrayList = new ArrayList();
        for (Pair first : list) {
            String id = ((UacfConsent) first.getFirst()).getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "it.first.id");
            arrayList.add(id);
        }
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        switch (mode) {
            case INTERRUPTION:
                str = AD_CONSENT_INTERRUPTION_DISPLAYED;
                break;
            case SETTINGS:
                str = AD_CONSENT_SETTINGS_DISPLAYED;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        Pair[] pairArr = new Pair[2];
        String str2 = TYPES;
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            pairArr[0] = TuplesKt.to(str2, Arrays.toString(array));
            pairArr[1] = TuplesKt.to("count", String.valueOf(list.size()));
            analyticsService2.reportEvent(str, MapsKt.mapOf(pairArr));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public void reportLearnMoreTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_LEARN_MORE_TAPPED, MapsKt.mapOf(TuplesKt.to("type", str)));
    }

    public void reportInterruptionSaveTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_SAVE_TAPPED);
    }

    public void reportInterruptionSaveAlertDisplayed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_SAVE_ALERT_DISPLAYED);
    }

    public void reportInterruptionAccepted(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        Intrinsics.checkParameterIsNotNull(list, "consentList");
        ArrayList arrayList = new ArrayList();
        Iterable iterable = list;
        Collection arrayList2 = new ArrayList();
        for (Object next : iterable) {
            if (((Boolean) ((Pair) next).getSecond()).booleanValue()) {
                arrayList2.add(next);
            }
        }
        for (Pair first : (List) arrayList2) {
            arrayList.add(((UacfConsent) first.getFirst()).getId());
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_ACCEPTED, MapsKt.mapOf(TuplesKt.to(TYPES, Arrays.toString(arrayList.toArray())), TuplesKt.to("count", String.valueOf(arrayList.size()))));
    }

    public void reportInterruptionSkipTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_SKIP_TAPPED);
    }

    public void reportInterruptionSkipAlertDisplayed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_SKIP_ALERT_DISPLAYED);
    }

    public void reportInterruptionSkipped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_SKIPPED);
    }

    public void reportInterruptionError() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_INTERRUPTION_ERROR);
    }

    public void reportConsentTappedOn(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_SETTINGS_CONSENT_TAPPED_ON, MapsKt.mapOf(TuplesKt.to("type", str)));
    }

    public void reportConsentTapOffAttempted(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_SETTINGS_CONSENT_TAP_OFF_ATTEMPTED, MapsKt.mapOf(TuplesKt.to("type", str)));
    }

    public void reportConsentOffSaved(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_SETTINGS_CONSENT_SAVED_OFF, MapsKt.mapOf(TuplesKt.to("type", str)));
    }

    public void reportConsentSettingsError() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(AD_CONSENT_SETTINGS_ERROR);
    }
}
