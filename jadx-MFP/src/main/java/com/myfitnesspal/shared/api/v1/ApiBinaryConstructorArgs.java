package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.model.mapper.ApiBinaryMapperBase;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.squareup.otto.Bus;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import java.util.UUID;
import javax.inject.Provider;

public class ApiBinaryConstructorArgs extends ApiConstructorArgs {
    private final Provider<BinaryEncoder> encoderProvider;
    private ApiBinaryMapperBase mapper;

    public ApiBinaryConstructorArgs(Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider, UUID uuid, long j, Lazy<RequestInterceptor> lazy2, Lazy<Bus> lazy3, Provider<BinaryEncoder> provider, ApiBinaryMapperBase apiBinaryMapperBase, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        super(lazy, userAgentProvider, uuid, j, lazy2, lazy3, lazy4, lazy5, lazy6);
        this.mapper = apiBinaryMapperBase;
        this.encoderProvider = provider;
    }

    public Provider<BinaryEncoder> getEncoderProvider() {
        return this.encoderProvider;
    }

    public ApiBinaryMapperBase getMapper() {
        return this.mapper;
    }

    public void setMapper(ApiBinaryMapperBase apiBinaryMapperBase) {
        this.mapper = apiBinaryMapperBase;
    }
}
