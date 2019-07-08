package com.myfitnesspal.shared.util;

import android.net.Uri;
import android.net.Uri.Builder;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/shared/util/GenericWebViewUtil;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: GenericWebViewUtil.kt */
public final class GenericWebViewUtil {
    @NotNull
    public static final String COUNTRIES_KEY = "supported_countries";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String MFP_LOC_PARAM = "mfp_loc";
    @NotNull
    public static final String URL_KEY = "webview_url";
    @NotNull
    public static final String WEBVIEW_KEY = "webview_key";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0007J&\u0010\u000e\u001a\u0004\u0018\u00010\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/shared/util/GenericWebViewUtil$Companion;", "", "()V", "COUNTRIES_KEY", "", "MFP_LOC_PARAM", "URL_KEY", "WEBVIEW_KEY", "getUrlForRollout", "configService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "getWebviewKeyToLaunch", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: GenericWebViewUtil.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final String getUrlForRollout(@NotNull Lazy<ConfigService> lazy, @NotNull CountryService countryService) {
            Intrinsics.checkParameterIsNotNull(lazy, "configService");
            Intrinsics.checkParameterIsNotNull(countryService, "countryService");
            Map propertiesForGenericWebView = ConfigUtils.getPropertiesForGenericWebView((ConfigService) lazy.get());
            Intrinsics.checkExpressionValueIsNotNull(propertiesForGenericWebView, "ConfigUtils.getPropertie…View(configService.get())");
            String str = (String) propertiesForGenericWebView.get(GenericWebViewUtil.URL_KEY);
            propertiesForGenericWebView.remove(GenericWebViewUtil.WEBVIEW_KEY);
            propertiesForGenericWebView.remove(GenericWebViewUtil.URL_KEY);
            propertiesForGenericWebView.remove(GenericWebViewUtil.COUNTRIES_KEY);
            Builder buildUpon = Uri.parse(str).buildUpon();
            Intrinsics.checkExpressionValueIsNotNull(buildUpon, "Uri.parse(baseurl).buildUpon()");
            for (Entry entry : propertiesForGenericWebView.entrySet()) {
                buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            buildUpon.appendQueryParameter(GenericWebViewUtil.MFP_LOC_PARAM, countryService.getCurrentLocaleIdentifierForV2());
            String builder = buildUpon.toString();
            Intrinsics.checkExpressionValueIsNotNull(builder, "uriBuilder.toString()");
            return builder;
        }

        @JvmStatic
        @Nullable
        public final String getWebviewKeyToLaunch(@NotNull Lazy<ConfigService> lazy, @NotNull Lazy<LocalSettingsService> lazy2) {
            Intrinsics.checkParameterIsNotNull(lazy, "configService");
            Intrinsics.checkParameterIsNotNull(lazy2, "localSettingsService");
            if (!ConfigUtils.isGenericWebViewEnabled((ConfigService) lazy.get())) {
                return "";
            }
            String str = (String) ConfigUtils.getPropertiesForGenericWebView((ConfigService) lazy.get()).get(GenericWebViewUtil.WEBVIEW_KEY);
            if (((LocalSettingsService) lazy2.get()).hasUserSeenGenericWebView(str)) {
                str = "";
            }
            return str;
        }
    }

    @JvmStatic
    @NotNull
    public static final String getUrlForRollout(@NotNull Lazy<ConfigService> lazy, @NotNull CountryService countryService) {
        return Companion.getUrlForRollout(lazy, countryService);
    }

    @JvmStatic
    @Nullable
    public static final String getWebviewKeyToLaunch(@NotNull Lazy<ConfigService> lazy, @NotNull Lazy<LocalSettingsService> lazy2) {
        return Companion.getWebviewKeyToLaunch(lazy, lazy2);
    }
}
