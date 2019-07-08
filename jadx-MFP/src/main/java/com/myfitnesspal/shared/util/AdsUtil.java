package com.myfitnesspal.shared.util;

import android.content.Context;
import android.os.Bundle;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration.Builder;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.GooglePlayServicesBanner.GooglePlayServicesMediationSettings;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/shared/util/AdsUtil;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdsUtil.kt */
public final class AdsUtil {
    public static final Companion Companion = new Companion(null);
    private static final String ENABLE = "1";
    private static final String NON_PERSONALIZED_ADS_EXTRA = "npa";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J \u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/myfitnesspal/shared/util/AdsUtil$Companion;", "", "()V", "ENABLE", "", "NON_PERSONALIZED_ADS_EXTRA", "getNonPersonalizedAdsBundle", "Landroid/os/Bundle;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "initMoPub", "", "context", "Landroid/content/Context;", "sdkInitializationListener", "Lcom/mopub/common/SdkInitializationListener;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdsUtil.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @Nullable
        public final Bundle getNonPersonalizedAdsBundle(@NotNull LocalSettingsService localSettingsService) {
            Intrinsics.checkParameterIsNotNull(localSettingsService, "localSettingsService");
            if (!localSettingsService.getIsUserSubjectToPersonalizedAds() || localSettingsService.getIsPersonalizedAdConsentAccepted()) {
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putString(AdsUtil.NON_PERSONALIZED_ADS_EXTRA, "1");
            return bundle;
        }

        @JvmStatic
        public final void initMoPub(@NotNull LocalSettingsService localSettingsService, @NotNull Context context, @NotNull SdkInitializationListener sdkInitializationListener) {
            Intrinsics.checkParameterIsNotNull(localSettingsService, "localSettingsService");
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(sdkInitializationListener, "sdkInitializationListener");
            Bundle nonPersonalizedAdsBundle = getNonPersonalizedAdsBundle(localSettingsService);
            Builder builder = new Builder(context.getString(R.string.mopub_ad_unit_id));
            if (nonPersonalizedAdsBundle != null) {
                builder.withMediationSettings(new GooglePlayServicesMediationSettings(nonPersonalizedAdsBundle));
            }
            MoPub.initializeSdk(context, builder.build(), sdkInitializationListener);
        }
    }

    @JvmStatic
    @Nullable
    public static final Bundle getNonPersonalizedAdsBundle(@NotNull LocalSettingsService localSettingsService) {
        return Companion.getNonPersonalizedAdsBundle(localSettingsService);
    }

    @JvmStatic
    public static final void initMoPub(@NotNull LocalSettingsService localSettingsService, @NotNull Context context, @NotNull SdkInitializationListener sdkInitializationListener) {
        Companion.initMoPub(localSettingsService, context, sdkInitializationListener);
    }
}
