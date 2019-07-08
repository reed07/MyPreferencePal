package com.myfitnesspal.shared.api.v2;

import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.SharedConstants.Api;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.event.AuthFailedEvent;
import com.myfitnesspal.shared.service.install.CountryService;
import com.squareup.otto.Bus;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import okhttp3.Request.Builder;

public class MfpV2ApiImpl extends MfpV2ApiImplBase<MfpV2Api> implements MfpV2Api {
    private final Lazy<CountryService> countryService;
    private final Lazy<DeviceInfo> deviceInfo;

    /* access modifiers changed from: protected */
    public boolean getShouldRetry() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isSuccessCode(int i) {
        if (!(i == 204 || i == 409)) {
            switch (i) {
                case 200:
                case RequestCodes.EDIT_RECIPE_INGREDIENT /*201*/:
                case RequestCodes.RECIPE_DETAILS /*202*/:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public MfpV2ApiImpl(ApiConstructorArgs apiConstructorArgs) {
        super(apiConstructorArgs);
        this.countryService = apiConstructorArgs.getCountryService();
        this.deviceInfo = apiConstructorArgs.getDeviceInfo();
    }

    public MfpV2Api withMapper(Mapper2<?, String> mapper2) {
        return (MfpV2Api) super.withMapper(mapper2);
    }

    /* access modifiers changed from: protected */
    public void reauthenticate() throws ApiException {
        try {
            getAuthTokenProvider().refreshAccessToken();
        } catch (ApiException unused) {
            ((Bus) this.messageBus.get()).post(new AuthFailedEvent());
        }
    }

    /* access modifiers changed from: protected */
    public void addHeadersTo(Builder builder) {
        super.addHeadersTo(builder);
        builder.header("Accept-Language", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifier()).header(Http.API_VERSION, Api.CURRENT_API_VERSION).header(Http.SCREEN_DENSITY, String.valueOf(((DeviceInfo) this.deviceInfo.get()).getDensity())).header(Http.SCREEN_HEIGHT, String.valueOf(((DeviceInfo) this.deviceInfo.get()).getScreenHeight())).header(Http.SCREEN_WIDTH, String.valueOf(((DeviceInfo) this.deviceInfo.get()).getScreenWidth()));
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getApiV2BaseUrl();
    }

    /* access modifiers changed from: protected */
    public String getFullUri(String str, Object[] objArr) {
        String fullUri = super.getFullUri(str, objArr);
        if (fullUri.contains(Uri.OAUTH2_TOKEN)) {
            return fullUri;
        }
        return fullUri.replace(Uri.USERID_TOKEN, Strings.toString(getAuthTokenProvider().getDomainUserId()));
    }
}
