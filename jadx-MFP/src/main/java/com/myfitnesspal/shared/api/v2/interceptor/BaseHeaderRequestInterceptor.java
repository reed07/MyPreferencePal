package com.myfitnesspal.shared.api.v2.interceptor;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.DeviceInfo;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0014H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/myfitnesspal/shared/api/v2/interceptor/BaseHeaderRequestInterceptor;", "Lokhttp3/Interceptor;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "deviceInfo", "Lcom/uacf/core/util/DeviceInfo;", "deviceUuid", "Ljava/util/UUID;", "userAgentProvider", "Lcom/myfitnesspal/shared/service/device/UserAgentProvider;", "authTokenProvider", "Lcom/myfitnesspal/shared/api/auth/AuthTokenProvider;", "apiUrlProvider", "Lcom/myfitnesspal/shared/api/ApiUrlProvider;", "(Lcom/myfitnesspal/shared/service/install/CountryService;Lcom/uacf/core/util/DeviceInfo;Ljava/util/UUID;Lcom/myfitnesspal/shared/service/device/UserAgentProvider;Lcom/myfitnesspal/shared/api/auth/AuthTokenProvider;Lcom/myfitnesspal/shared/api/ApiUrlProvider;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "appendAuthHeaders", "Lokhttp3/Request$Builder;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: BaseHeaderRequestInterceptor.kt */
public final class BaseHeaderRequestInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    private static final String HEADER_API_VERSION = "api-version";
    private static final String HEADER_DEVICE_ID = "device_id";
    private static final String HEADER_SCREEN_DENSITY = "Screen-Density";
    private static final String HEADER_SCREEN_HEIGHT = "Screen-Height";
    private static final String HEADER_SCREEN_WIDTH = "Screen-Width";
    private static final String VALUE_CURRENT_API_VERSION = "2.0.5";
    private final ApiUrlProvider apiUrlProvider;
    private final AuthTokenProvider authTokenProvider;
    private final CountryService countryService;
    private final DeviceInfo deviceInfo;
    private final UUID deviceUuid;
    private final UserAgentProvider userAgentProvider;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/shared/api/v2/interceptor/BaseHeaderRequestInterceptor$Companion;", "", "()V", "HEADER_ACCEPT_LANGUAGE", "", "HEADER_API_VERSION", "HEADER_DEVICE_ID", "HEADER_SCREEN_DENSITY", "HEADER_SCREEN_HEIGHT", "HEADER_SCREEN_WIDTH", "VALUE_CURRENT_API_VERSION", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: BaseHeaderRequestInterceptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BaseHeaderRequestInterceptor(@NotNull CountryService countryService2, @NotNull DeviceInfo deviceInfo2, @NotNull UUID uuid, @NotNull UserAgentProvider userAgentProvider2, @NotNull AuthTokenProvider authTokenProvider2, @NotNull ApiUrlProvider apiUrlProvider2) {
        Intrinsics.checkParameterIsNotNull(countryService2, "countryService");
        Intrinsics.checkParameterIsNotNull(deviceInfo2, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(uuid, "deviceUuid");
        Intrinsics.checkParameterIsNotNull(userAgentProvider2, "userAgentProvider");
        Intrinsics.checkParameterIsNotNull(authTokenProvider2, "authTokenProvider");
        Intrinsics.checkParameterIsNotNull(apiUrlProvider2, "apiUrlProvider");
        this.countryService = countryService2;
        this.deviceInfo = deviceInfo2;
        this.deviceUuid = uuid;
        this.userAgentProvider = userAgentProvider2;
        this.authTokenProvider = authTokenProvider2;
        this.apiUrlProvider = apiUrlProvider2;
    }

    @NotNull
    public Response intercept(@NotNull Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Builder newBuilder = chain.request().newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(newBuilder, "chain.request()\n                .newBuilder()");
        Response proceed = chain.proceed(appendAuthHeaders(newBuilder).header("User-Agent", (String) this.userAgentProvider.get()).header("Accept", HttpConstants.CONTENT_TYPE_JSON).header("Accept-Language", this.countryService.getCurrentLocaleIdentifier()).header("api-version", VALUE_CURRENT_API_VERSION).header("device_id", this.deviceUuid.toString()).header("Screen-Density", String.valueOf(this.deviceInfo.getDensity())).header("Screen-Width", String.valueOf(this.deviceInfo.getScreenWidth())).header("Screen-Height", String.valueOf(this.deviceInfo.getScreenHeight())).build());
        Intrinsics.checkExpressionValueIsNotNull(proceed, "chain.proceed(chain.requ…                .build())");
        return proceed;
    }

    private final Builder appendAuthHeaders(@NotNull Builder builder) {
        Map credentials = this.apiUrlProvider.getCredentials(this.authTokenProvider);
        Intrinsics.checkExpressionValueIsNotNull(credentials, "apiUrlProvider.getCredentials(authTokenProvider)");
        for (Entry entry : credentials.entrySet()) {
            builder.header((String) entry.getKey(), (String) entry.getValue());
        }
        return builder;
    }
}
