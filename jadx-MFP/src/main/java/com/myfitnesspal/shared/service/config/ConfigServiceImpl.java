package com.myfitnesspal.shared.service.config;

import com.myfitnesspal.feature.settings.model.ABTestSettings;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2ConfigApi;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.constants.Constants.FAQ;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.model.v15.ABTestObject;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;

public class ConfigServiceImpl extends SimpleAsyncServiceBase implements ConfigService {
    private static final String COMMA_DELIM = ",";
    private static final String SUPPORTED_COUNTRIES_KEY = "supported_countries";
    private static final String SUPPORTED_LANGUAGES_KEY = "supported_languages";
    private static final String USER_KEY = "__user_key__";
    private static Map<String, String> variantsReportedForExperimentStart = new HashMap();
    private final ABTestSettings abTestSettings;
    private final Lazy<AnalyticsService> analyticsService;
    private Provider<MfpV2ConfigApi> api;
    private Cache<Configuration> cache;
    private final Lazy<CountryService> countryService;
    private final UUID deviceId;
    private Configuration inMemoryCachedConfiguration;
    private final Object inMemoryCachedConfigurationWriterLock = new Object();
    /* access modifiers changed from: private */
    public final AtomicBoolean refreshing = new AtomicBoolean(false);
    private Lazy<Session> session;
    private final long versionCode;
    private final String versionName;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "ConfigServiceImpl";
    }

    public ConfigServiceImpl(Provider<MfpV2ConfigApi> provider, Cache<Configuration> cache2, Lazy<Session> lazy, ABTestSettings aBTestSettings, UUID uuid, String str, long j, Lazy<AnalyticsService> lazy2, Lazy<CountryService> lazy3) {
        this.api = provider;
        this.cache = cache2;
        this.session = lazy;
        this.abTestSettings = aBTestSettings;
        this.deviceId = uuid;
        this.versionName = Strings.toString(str);
        this.versionCode = j;
        this.analyticsService = lazy2;
        this.countryService = lazy3;
    }

    private void updateLastRefreshTime() {
        this.cache.putMetadata(getExpirationKey(), System.currentTimeMillis());
    }

    private long getLastRefreshTime() {
        return this.cache.getMetadata(getExpirationKey(), 0);
    }

    /* access modifiers changed from: private */
    public boolean expired() {
        return System.currentTimeMillis() - getLastRefreshTime() > 300000;
    }

    public void refreshIfExpiredAsync() {
        async(new Runnable() {
            public void run() {
                if (!ConfigServiceImpl.this.refreshing.get() && ConfigServiceImpl.this.expired()) {
                    Ln.d("expired config detected, refreshing...", new Object[0]);
                    ConfigServiceImpl.this.refreshing.set(true);
                    try {
                        ConfigServiceImpl.this.refresh();
                    } catch (ApiException unused) {
                    } catch (Throwable th) {
                        ConfigServiceImpl.this.refreshing.set(false);
                        throw th;
                    }
                    ConfigServiceImpl.this.refreshing.set(false);
                }
            }
        });
    }

    public boolean refresh() throws ApiException {
        updateLastRefreshTime();
        try {
            Configuration configuration = (Configuration) ((MfpV2ConfigApi) ((MfpV2ConfigApi) this.api.get()).withOutputType(Configuration.class)).get(Uri.CONFIG, getParams());
            if (configuration == null) {
                return false;
            }
            setInMemoryCachedConfiguration(configuration);
            this.cache.put(getCacheKey(), configuration);
            return true;
        } catch (ApiException e) {
            Ln.e(e);
            throw e;
        }
    }

    public void prefetchAsync(final Function0 function0) {
        async(new Runnable() {
            public void run() {
                try {
                    if (ConfigServiceImpl.this.getCachedConfiguration() == null) {
                        ConfigServiceImpl.this.refresh();
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    ConfigServiceImpl.this.postToMainThread(function0);
                    throw th;
                }
                ConfigServiceImpl.this.postToMainThread(function0);
            }
        });
    }

    public boolean hasValidConfiguration() {
        return (getInMemoryCachedConfiguration() == null && getCachedConfiguration() == null) ? false : true;
    }

    public Map<String, String> getABTestProperties(String str) {
        ABTestObject aBTestSync = getABTestSync(str);
        return aBTestSync != null ? new HashMap(aBTestSync.getProperties()) : new HashMap();
    }

    private String getVariantFromABTest(ABTestObject aBTestObject) {
        if (aBTestObject != null) {
            return aBTestObject.getVariant();
        }
        return null;
    }

    public String getVariant(String str) {
        return getVariant(str, null);
    }

    public String getVariant(String str, String str2) {
        ABTestObject testOverrideFor = getTestOverrideFor(str);
        if (testOverrideFor == null) {
            Configuration cachedConfiguration = getCachedConfiguration();
            if (cachedConfiguration != null) {
                testOverrideFor = extractABTestObjectFromConfiguration(cachedConfiguration, str);
            }
        }
        if (testOverrideFor == null) {
            return str2;
        }
        trackTestVariant(str, testOverrideFor);
        return testOverrideFor.getVariant();
    }

    public String getVariantIfCountrySupported(String str) {
        String variant = getVariant(str);
        if (isCountrySupported(str)) {
            return variant;
        }
        return null;
    }

    public String getVariantIfLanguageSupported(String str) {
        String variant = getVariant(str);
        if (isLaunguageSupported(str)) {
            return variant;
        }
        return null;
    }

    public boolean isVariantEnabled(String str) {
        return isVariantEnabled(str, "on");
    }

    public boolean isVariantEnabled(String str, String str2) {
        return isVariantEnabled(str, str2, false);
    }

    public boolean isVariantEnabled(String str, String str2, boolean z) {
        return doesVariantContainExpectedValue(getVariant(str), z ? str2 : "false", str2);
    }

    public boolean isVariantOnAndCountrySupported(String str) {
        return isVariantEnabled(str) && isCountrySupported(str);
    }

    public boolean isVariantOnAndLanguageSupported(String str, String str2) {
        boolean z = false;
        if (!isVariantEnabled(str)) {
            return false;
        }
        Set cSVPropertiesForRollout = getCSVPropertiesForRollout(str, SUPPORTED_LANGUAGES_KEY);
        if (CollectionUtils.isEmpty((Collection<?>) cSVPropertiesForRollout) || cSVPropertiesForRollout.contains(str2)) {
            z = true;
        }
        return z;
    }

    public boolean isVariantOnAndLanguageSupported(String str) {
        return isVariantOnAndLanguageSupported(str, ((CountryService) this.countryService.get()).getCurrentLanguage());
    }

    public boolean isVariantOnAndCountryAndLanguageSupported(String str) {
        return isVariantOnAndCountrySupported(str) && isVariantOnAndLanguageSupported(str);
    }

    public String getTermsAndPrivacyUrl() {
        Configuration cachedConfiguration = getCachedConfiguration();
        return cachedConfiguration != null && cachedConfiguration.getAppConfig() != null && Strings.notEmpty(cachedConfiguration.getAppConfig().getTermsAndPrivacyUrl()) ? cachedConfiguration.getAppConfig().getTermsAndPrivacyUrl() : URLs.DEFAULT_TOS_RELATIVE_URL;
    }

    public String getFaqUrl(int i) {
        AppConfig appConfig;
        String str;
        Configuration cachedConfiguration = getCachedConfiguration();
        if (cachedConfiguration != null) {
            appConfig = cachedConfiguration.getAppConfig();
        } else {
            appConfig = null;
        }
        boolean z = appConfig != null;
        switch (i) {
            case 100:
                return (!z || !Strings.notEmpty(appConfig.getFaqUrl())) ? "" : appConfig.getFaqUrl();
            case 101:
                return (!z || !Strings.notEmpty(appConfig.getOfflineSearchFaqUrl())) ? FAQ.OFFLINE_SEARCH_ARTICLE : appConfig.getOfflineSearchFaqUrl();
            case 102:
                return (!z || !Strings.notEmpty(appConfig.getCalorieAdjustmentFaqUrl())) ? FAQ.CALORIE_ADJUSTMENT_ARTICLE : appConfig.getCalorieAdjustmentFaqUrl();
            case 103:
                return (!z || !Strings.notEmpty(appConfig.getGoalRecalcualtionFaqUrl())) ? FAQ.GOAL_RECALCULATION_ARTICLE : appConfig.getGoalRecalcualtionFaqUrl();
            case 104:
                return (!z || !Strings.notEmpty(appConfig.getAdFreeFaqUrl())) ? FAQ.AD_FREE_FAQ : appConfig.getAdFreeFaqUrl();
            case 105:
                return (!z || !Strings.notEmpty(appConfig.getQuickAddFaqUrl())) ? FAQ.QUICK_ADD_FAQ : appConfig.getQuickAddFaqUrl();
            case 106:
                return (!z || !Strings.notEmpty(appConfig.getFoodAnalysisFaqUrl())) ? FAQ.FOOD_ANALYSIS_FAQ : appConfig.getFoodAnalysisFaqUrl();
            case 107:
                return (!z || !Strings.notEmpty(appConfig.getPrioritySupportFaqUrl())) ? FAQ.PRIORITY_SUPPORT_FAQ : appConfig.getPrioritySupportFaqUrl();
            case 108:
                return (!z || !Strings.notEmpty(appConfig.getUserPotentiallyChargedUrl())) ? FAQ.USER_POTENTIALLY_CHANGED_ARTICLE : appConfig.getUserPotentiallyChargedUrl();
            case 109:
                return (!z || !Strings.notEmpty(appConfig.getChangeMealNamesUrl())) ? FAQ.CHANGE_MEAL_NAMES_ARTICLE : appConfig.getChangeMealNamesUrl();
            case 110:
                return (!z || !Strings.notEmpty(appConfig.getStrengthCaloriesUrl())) ? FAQ.STRENGTH_CALORIES_ARTICLE : appConfig.getStrengthCaloriesUrl();
            default:
                if (!z || !Strings.notEmpty(appConfig.getFaqUrl())) {
                    str = "";
                } else {
                    str = appConfig.getFaqUrl();
                }
                return str;
        }
    }

    private String getCacheKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.Cache.CONFIG_CACHE_KEY);
        sb.append(Strings.toString(Long.valueOf(((Session) this.session.get()).getUser().getMasterDatabaseId())));
        return sb.toString();
    }

    private String getExpirationKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCacheKey());
        sb.append(".expiration");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public Configuration getCachedConfiguration() {
        Configuration inMemoryCachedConfiguration2 = getInMemoryCachedConfiguration();
        if (inMemoryCachedConfiguration2 != null || !this.cache.contains(getCacheKey())) {
            return inMemoryCachedConfiguration2;
        }
        try {
            Configuration configuration = (Configuration) this.cache.get(getCacheKey());
            try {
                setInMemoryCachedConfiguration(configuration);
                return configuration;
            } catch (Exception e) {
                Configuration configuration2 = configuration;
                e = e;
                inMemoryCachedConfiguration2 = configuration2;
                Ln.e(e, "error mapping config value to JSON!", new Object[0]);
                return inMemoryCachedConfiguration2;
            }
        } catch (Exception e2) {
            e = e2;
            Ln.e(e, "error mapping config value to JSON!", new Object[0]);
            return inMemoryCachedConfiguration2;
        }
    }

    private Object[] getParams() {
        return new Object[]{"device_id", Strings.toString(this.deviceId), "user_id", Strings.toString(Long.valueOf(((Session) this.session.get()).getUser().getMasterDatabaseId())), Http.PLATFORM, "android", "version", this.versionName, Http.BUILD_VERSION, Strings.toString(Long.valueOf(this.versionCode))};
    }

    private ABTestObject getABTestSync(String str) {
        ABTestObject testOverrideFor = getTestOverrideFor(str);
        if (testOverrideFor == null) {
            Configuration cachedConfiguration = getCachedConfiguration();
            if (cachedConfiguration != null) {
                testOverrideFor = extractABTestObjectFromConfiguration(cachedConfiguration, str);
            }
        }
        if (testOverrideFor != null) {
            trackTestVariant(str, testOverrideFor);
        }
        return testOverrideFor;
    }

    /* access modifiers changed from: protected */
    public ABTestObject getTestOverrideFor(String str) {
        if (!this.abTestSettings.shouldOverride(str)) {
            return null;
        }
        ABTestObject aBTestObject = new ABTestObject();
        aBTestObject.setName(str);
        aBTestObject.setVariant(this.abTestSettings.getVariantOverrideFor(str, "false"));
        aBTestObject.setProperties(this.abTestSettings.getPropertyOverridesFor(str, aBTestObject.getVariant()));
        return aBTestObject;
    }

    private ABTestObject extractABTestObjectFromConfiguration(Configuration configuration, String str) {
        return (ABTestObject) Enumerable.firstOrDefault(configuration != null ? configuration.getAbtests() : null, new ReturningFunction1(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final Object execute(Object obj) {
                return Boolean.valueOf(Strings.equals(((ABTestObject) obj).getName(), this.f$0));
            }
        });
    }

    private boolean doesVariantContainExpectedValue(String str, String str2, String str3) {
        String[] split;
        if (!Strings.notEmpty(str)) {
            str = str2;
        }
        for (String str4 : str.split("\\|")) {
            if (!Strings.equals(str4, "false") && Strings.equalsIgnoreCase(str4.trim(), str3)) {
                return true;
            }
        }
        return false;
    }

    public String getEnglishTosUrl() {
        return getTosUrl("en");
    }

    public Map<String, String> getABTests() {
        Configuration cachedConfiguration = getCachedConfiguration();
        List abtests = cachedConfiguration != null ? cachedConfiguration.getAbtests() : null;
        final HashMap hashMap = new HashMap();
        Enumerable.forEach((Collection<T>) abtests, (Function1<T>) new Function1<ABTestObject>() {
            public void execute(ABTestObject aBTestObject) {
                if (aBTestObject != null && Strings.notEmpty(aBTestObject.getName())) {
                    hashMap.put(aBTestObject.getName(), Strings.notEmpty(aBTestObject.getVariant()) ? aBTestObject.getVariant() : "false");
                }
            }
        });
        return hashMap;
    }

    public String getABTestPropertyValueIfVariantEnabled(String str, String str2) {
        if (!isVariantEnabled(str)) {
            return null;
        }
        return (String) getABTestProperties(str).get(str2);
    }

    private String getTosUrl(String str) {
        return String.format(URLs.TOS_URL_FORMAT, new Object[]{str.toLowerCase()});
    }

    private boolean isCountrySupported(String str) {
        Set cSVPropertiesForRollout = getCSVPropertiesForRollout(str, "supported_countries");
        if (cSVPropertiesForRollout == null || cSVPropertiesForRollout.isEmpty()) {
            return true;
        }
        return cSVPropertiesForRollout.contains(((CountryService) this.countryService.get()).getShortNameFromLongName(((Session) this.session.get()).getUser().getProfile().getCountryName()));
    }

    private boolean isLaunguageSupported(String str) {
        Set cSVPropertiesForRollout = getCSVPropertiesForRollout(str, SUPPORTED_LANGUAGES_KEY);
        return CollectionUtils.isEmpty((Collection<?>) cSVPropertiesForRollout) || cSVPropertiesForRollout.contains(((CountryService) this.countryService.get()).getCurrentLanguage());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void trackTestVariant(java.lang.String r4, com.myfitnesspal.shared.model.v15.ABTestObject r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.Map<java.lang.String, java.lang.String> r0 = variantsReportedForExperimentStart     // Catch:{ all -> 0x0070 }
            java.lang.String r1 = "__user_key__"
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0070 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0070 }
            java.lang.String r1 = r3.getCacheKey()     // Catch:{ all -> 0x0070 }
            boolean r2 = com.uacf.core.util.Strings.isEmpty(r0)     // Catch:{ all -> 0x0070 }
            if (r2 != 0) goto L_0x001b
            boolean r0 = com.uacf.core.util.Strings.equals(r0, r1)     // Catch:{ all -> 0x0070 }
            if (r0 != 0) goto L_0x002f
        L_0x001b:
            java.lang.String r0 = "clear experiment_start cache because user has changed"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0070 }
            com.uacf.core.util.Ln.d(r0, r2)     // Catch:{ all -> 0x0070 }
            java.util.Map<java.lang.String, java.lang.String> r0 = variantsReportedForExperimentStart     // Catch:{ all -> 0x0070 }
            r0.clear()     // Catch:{ all -> 0x0070 }
            java.util.Map<java.lang.String, java.lang.String> r0 = variantsReportedForExperimentStart     // Catch:{ all -> 0x0070 }
            java.lang.String r2 = "__user_key__"
            r0.put(r2, r1)     // Catch:{ all -> 0x0070 }
        L_0x002f:
            boolean r0 = com.uacf.core.util.Strings.isEmpty(r4)     // Catch:{ all -> 0x0070 }
            if (r0 != 0) goto L_0x006e
            if (r5 != 0) goto L_0x0038
            goto L_0x006e
        L_0x0038:
            boolean r0 = r5.isDisableTracking()     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x0040
            monitor-exit(r3)
            return
        L_0x0040:
            java.lang.String r5 = r3.getVariantFromABTest(r5)     // Catch:{ all -> 0x0070 }
            java.util.Map<java.lang.String, java.lang.String> r0 = variantsReportedForExperimentStart     // Catch:{ all -> 0x0070 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0070 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0070 }
            boolean r0 = com.uacf.core.util.Strings.equals(r0, r5)     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x0054
            monitor-exit(r3)
            return
        L_0x0054:
            boolean r0 = com.uacf.core.util.Strings.isEmpty(r5)     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x005c
            java.lang.String r5 = "false"
        L_0x005c:
            java.util.Map<java.lang.String, java.lang.String> r0 = variantsReportedForExperimentStart     // Catch:{ all -> 0x0070 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0070 }
            dagger.Lazy<com.myfitnesspal.shared.service.analytics.AnalyticsService> r0 = r3.analyticsService     // Catch:{ all -> 0x0070 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0070 }
            com.myfitnesspal.shared.service.analytics.AnalyticsService r0 = (com.myfitnesspal.shared.service.analytics.AnalyticsService) r0     // Catch:{ all -> 0x0070 }
            r0.reportExperimentStart(r4, r5)     // Catch:{ all -> 0x0070 }
            monitor-exit(r3)
            return
        L_0x006e:
            monitor-exit(r3)
            return
        L_0x0070:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.config.ConfigServiceImpl.trackTestVariant(java.lang.String, com.myfitnesspal.shared.model.v15.ABTestObject):void");
    }

    private Set<String> getCSVPropertiesForRollout(String str, String str2) {
        Map aBTestProperties = getABTestProperties(str);
        HashSet hashSet = new HashSet();
        if (CollectionUtils.notEmpty(aBTestProperties)) {
            String str3 = (String) aBTestProperties.get(str2);
            if (Strings.notEmpty(str3)) {
                for (String trim : str3.split(COMMA_DELIM)) {
                    hashSet.add(trim.trim());
                }
            }
        }
        return hashSet;
    }

    private void setInMemoryCachedConfiguration(Configuration configuration) {
        synchronized (this.inMemoryCachedConfigurationWriterLock) {
            this.inMemoryCachedConfiguration = configuration;
        }
    }

    private Configuration getInMemoryCachedConfiguration() {
        Configuration configuration;
        synchronized (this.inMemoryCachedConfigurationWriterLock) {
            configuration = this.inMemoryCachedConfiguration;
        }
        return configuration;
    }
}
