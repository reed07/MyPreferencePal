package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.interceptor.BaseHeaderRequestInterceptor;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.DeviceInfo;
import dagger.Module;
import dagger.Provides;
import java.util.UUID;
import javax.inject.Named;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007¨\u0006\u0015"}, d2 = {"Lcom/myfitnesspal/shared/injection/module/ApiModule;", "", "()V", "provideBaseRequestInterceptor", "Lcom/myfitnesspal/shared/api/v2/interceptor/BaseHeaderRequestInterceptor;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "deviceInfo", "Lcom/uacf/core/util/DeviceInfo;", "deviceUuid", "Ljava/util/UUID;", "userAgentProvider", "Lcom/myfitnesspal/shared/service/device/UserAgentProvider;", "authTokenProvider", "Lcom/myfitnesspal/shared/api/auth/AuthTokenProvider;", "apiUrlProvider", "Lcom/myfitnesspal/shared/api/ApiUrlProvider;", "provideHttpClient", "Lokhttp3/OkHttpClient;", "requestRequestInterceptor", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Module
/* compiled from: ApiModule.kt */
public final class ApiModule {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String HTTP_CLIENT = "http_client";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/injection/module/ApiModule$Companion;", "", "()V", "HTTP_CLIENT", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ApiModule.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Singleton
    @NotNull
    @Provides
    public final BaseHeaderRequestInterceptor provideBaseRequestInterceptor(@NotNull CountryService countryService, @NotNull DeviceInfo deviceInfo, @NotNull @Named("deviceUUID") UUID uuid, @NotNull UserAgentProvider userAgentProvider, @NotNull AuthTokenProvider authTokenProvider, @NotNull ApiUrlProvider apiUrlProvider) {
        Intrinsics.checkParameterIsNotNull(countryService, "countryService");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(uuid, "deviceUuid");
        Intrinsics.checkParameterIsNotNull(userAgentProvider, "userAgentProvider");
        Intrinsics.checkParameterIsNotNull(authTokenProvider, "authTokenProvider");
        Intrinsics.checkParameterIsNotNull(apiUrlProvider, "apiUrlProvider");
        BaseHeaderRequestInterceptor baseHeaderRequestInterceptor = new BaseHeaderRequestInterceptor(countryService, deviceInfo, uuid, userAgentProvider, authTokenProvider, apiUrlProvider);
        return baseHeaderRequestInterceptor;
    }

    @Named("http_client")
    @Singleton
    @NotNull
    @Provides
    public final OkHttpClient provideHttpClient(@NotNull BaseHeaderRequestInterceptor baseHeaderRequestInterceptor) {
        Intrinsics.checkParameterIsNotNull(baseHeaderRequestInterceptor, "requestRequestInterceptor");
        OkHttpClient build = new Builder().addInterceptor(baseHeaderRequestInterceptor).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OkHttpClient.Builder()\n …\n                .build()");
        return build;
    }
}
