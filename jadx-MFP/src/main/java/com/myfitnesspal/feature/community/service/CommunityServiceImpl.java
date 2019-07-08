package com.myfitnesspal.feature.community.service;

import com.google.common.net.HttpHeaders;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.ABTest.CommunityInternational;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.v2.MfpUrl;
import com.myfitnesspal.shared.model.v2.MfpUrl.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import dagger.Lazy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

public class CommunityServiceImpl implements CommunityService {
    private static final String CHINESE_SIMPLIFIED = "zh-CN";
    private static final String CHINESE_TRADITIONAL = "zh-TW";
    private static final String ENGLISH = "en";
    private static final String LOCALE = "locale";
    private static final HashMap<String, String> MFP_TO_VANILLA = new HashMap<>();
    private static final Set<String> SUPPORTED_LANGUAGES = new HashSet(Arrays.asList(new String[]{ENGLISH, "fr", "es", "de", "nl", "da", "it", "sv", "pt", "ru", "no", "jp", "ko", "zh", "zhtw"}));
    private static final String TARGET = "target";
    private final Provider<MfpV2Api> api;
    private final Lazy<ConfigService> configService;
    private final Lazy<CountryService> countryService;

    static {
        MFP_TO_VANILLA.put("ja", "jp");
        MFP_TO_VANILLA.put("nb", "no");
    }

    public CommunityServiceImpl(Provider<MfpV2Api> provider, Lazy<ConfigService> lazy, Lazy<CountryService> lazy2) {
        this.api = provider;
        this.configService = lazy;
        this.countryService = lazy2;
    }

    public void singleSignOn(String str, final Function2<ApiResponse<MfpUrl>, List<String>> function2, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).getAsync(Uri.FORUM_SSO, (Function2<T, Map<String, List<String>>>) new Function2<ApiResponse<MfpUrl>, Map<String, List<String>>>() {
            public void execute(ApiResponse<MfpUrl> apiResponse, Map<String, List<String>> map) {
                FunctionUtils.invokeIfValid(function2, apiResponse, (List) map.get(HttpHeaders.SET_COOKIE));
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
            }
        }, TARGET, str, "locale", languageForRequest());
    }

    public boolean isCommunityEnabled() {
        String language = language();
        boolean z = true;
        if (ENGLISH.equals(language)) {
            return true;
        }
        if (MFP_TO_VANILLA.containsKey(language)) {
            language = (String) MFP_TO_VANILLA.get(language);
        }
        if (!((ConfigService) this.configService.get()).isVariantOnAndLanguageSupported(CommunityInternational.NAME, language) || !SUPPORTED_LANGUAGES.contains(language)) {
            z = false;
        }
        return z;
    }

    private String language() {
        return ((CountryService) this.countryService.get()).getCurrentLanguage().toLowerCase();
    }

    private String languageForRequest() {
        String currentLocaleIdentifier = ((CountryService) this.countryService.get()).getCurrentLocaleIdentifier();
        return (CHINESE_SIMPLIFIED.equals(currentLocaleIdentifier) || CHINESE_TRADITIONAL.equals(currentLocaleIdentifier)) ? currentLocaleIdentifier : language();
    }
}
