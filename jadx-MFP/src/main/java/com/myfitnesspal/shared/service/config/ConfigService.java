package com.myfitnesspal.shared.service.config;

import com.myfitnesspal.shared.api.ApiException;
import com.uacf.core.util.Function0;
import java.util.Map;

public interface ConfigService {
    Map<String, String> getABTestProperties(String str);

    String getABTestPropertyValueIfVariantEnabled(String str, String str2);

    Map<String, String> getABTests();

    String getEnglishTosUrl();

    String getFaqUrl(int i);

    String getTermsAndPrivacyUrl();

    String getVariant(String str);

    String getVariant(String str, String str2);

    String getVariantIfCountrySupported(String str);

    String getVariantIfLanguageSupported(String str);

    boolean hasValidConfiguration();

    boolean isVariantEnabled(String str);

    boolean isVariantEnabled(String str, String str2);

    boolean isVariantEnabled(String str, String str2, boolean z);

    boolean isVariantOnAndCountryAndLanguageSupported(String str);

    boolean isVariantOnAndCountrySupported(String str);

    boolean isVariantOnAndLanguageSupported(String str);

    boolean isVariantOnAndLanguageSupported(String str, String str2);

    void prefetchAsync(Function0 function0);

    boolean refresh() throws ApiException;

    void refreshIfExpiredAsync();
}
