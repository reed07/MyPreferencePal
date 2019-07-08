package com.myfitnesspal.shared.service.ads;

import android.content.Context;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/shared/service/ads/AdIdConsentCompliant;", "", "localSettingsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "adAnalyticsHelper", "Lcom/myfitnesspal/shared/service/ads/AdsAnalyticsHelper;", "androidAdvertisementIdentifier", "Lcom/myfitnesspal/shared/service/ads/AndroidAdvertisementIdentifier;", "(Ldagger/Lazy;Ldagger/Lazy;Lcom/myfitnesspal/shared/service/ads/AndroidAdvertisementIdentifier;)V", "storeGaidAndSendAnalyticsEvent", "", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdIdConsentCompliant.kt */
public final class AdIdConsentCompliant {
    /* access modifiers changed from: private */
    public final Lazy<AdsAnalyticsHelper> adAnalyticsHelper;
    /* access modifiers changed from: private */
    public final AndroidAdvertisementIdentifier androidAdvertisementIdentifier;
    /* access modifiers changed from: private */
    public final Lazy<LocalSettingsService> localSettingsService;

    public AdIdConsentCompliant(@NotNull Lazy<LocalSettingsService> lazy, @NotNull Lazy<AdsAnalyticsHelper> lazy2, @NotNull AndroidAdvertisementIdentifier androidAdvertisementIdentifier2) {
        Intrinsics.checkParameterIsNotNull(lazy, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy2, "adAnalyticsHelper");
        Intrinsics.checkParameterIsNotNull(androidAdvertisementIdentifier2, "androidAdvertisementIdentifier");
        this.localSettingsService = lazy;
        this.adAnalyticsHelper = lazy2;
        this.androidAdvertisementIdentifier = androidAdvertisementIdentifier2;
    }

    public final void storeGaidAndSendAnalyticsEvent(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1(this, context, null), 3, null);
    }
}
