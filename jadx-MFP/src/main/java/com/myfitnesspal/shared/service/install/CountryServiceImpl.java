package com.myfitnesspal.shared.service.install;

import android.content.Context;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v1.Country.LIST_MAPPER;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.util.ResourceUtils;
import com.uacf.core.util.FileUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 H2\u00020\u0001:\u0001HB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u000e\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0014\u00107\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u001208H\u0002J\u0012\u00109\u001a\u0004\u0018\u00010\f2\u0006\u0010:\u001a\u00020\u0012H\u0016J\u0014\u0010;\u001a\u0004\u0018\u00010\f2\b\u0010:\u001a\u0004\u0018\u00010\u0012H\u0002J\u0012\u0010<\u001a\u0004\u0018\u00010\f2\u0006\u0010=\u001a\u00020\u0012H\u0016J\u0010\u0010>\u001a\u00020$2\u0006\u0010?\u001a\u00020\u0012H\u0016J\u0012\u0010@\u001a\u00020$2\b\u0010A\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010B\u001a\u00020\u00122\u0006\u0010C\u001a\u00020\fH\u0016J\u0012\u0010D\u001a\u0004\u0018\u00010\u00122\u0006\u0010C\u001a\u00020\fH\u0016J\u0012\u0010E\u001a\u00020\u00122\b\u0010=\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010F\u001a\u00020(2\u0006\u0010G\u001a\u00020\u0012H\u0016R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0002\n\u0000R'\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00118BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001cR\u0014\u0010!\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001cR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$8VX\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(8VX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010)R\u0014\u0010*\u001a\u00020(8VX\u0004¢\u0006\u0006\u001a\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020(8VX\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)R'\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00118BX\u0002¢\u0006\f\n\u0004\b.\u0010\u0016\u001a\u0004\b-\u0010\u0014R\u0016\u0010/\u001a\u0004\u0018\u00010\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u001cR\u001c\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001202X\u0004¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020$02X\u0004¢\u0006\u0002\n\u0000R\u001a\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020$02X\u0004¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001202X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/myfitnesspal/shared/service/install/CountryServiceImpl;", "Lcom/myfitnesspal/shared/service/install/CountryService;", "context", "Landroid/content/Context;", "resourceUtils", "Lcom/myfitnesspal/shared/util/ResourceUtils;", "globalSettings", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/globalsettings/GlobalSettingsService;", "(Landroid/content/Context;Lcom/myfitnesspal/shared/util/ResourceUtils;Ldagger/Lazy;)V", "allSupportedCountries", "", "Lcom/myfitnesspal/shared/model/v1/Country;", "getAllSupportedCountries", "()Ljava/util/List;", "countries", "countryCategoryForTOS", "", "", "getCountryCategoryForTOS", "()Ljava/util/Map;", "countryCategoryForTOS$delegate", "Lkotlin/Lazy;", "currentCountry", "getCurrentCountry", "()Lcom/myfitnesspal/shared/model/v1/Country;", "currentCountryLongName", "getCurrentCountryLongName", "()Ljava/lang/String;", "currentLanguage", "getCurrentLanguage", "currentLocaleIdentifier", "getCurrentLocaleIdentifier", "currentLocaleIdentifierForV2", "getCurrentLocaleIdentifierForV2", "indexOfCurrentCountry", "", "getIndexOfCurrentCountry", "()I", "isCurrentCountryCJK", "", "()Z", "isEnglishCurrentDialect", "isEnglishUSCurrentLocale", "localizedLanguageNames", "getLocalizedLanguageNames", "localizedLanguageNames$delegate", "localizedNameForCurrentLanguage", "getLocalizedNameForCurrentLanguage", "lookupAbbreviation", "Ljava/util/HashMap;", "lookupId", "lookupIdFromName", "lookupName", "generateCountries", "generateCountryAndLanguage", "Lkotlin/Pair;", "getCountryFromCountryCode", "code", "getCountryFromCountryCodeWithoutInit", "getCountryFromLongName", "name", "getIndexOfLongName", "longCountryName", "getIndexOfShortName", "shortCountryName", "getLocalizedLongCountryName", "country", "getLongCountryName", "getShortNameFromLongName", "needsToAcceptTOS", "countryCode", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: CountryServiceImpl.kt */
public final class CountryServiceImpl implements CountryService {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CountryServiceImpl.class), "localizedLanguageNames", "getLocalizedLanguageNames()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CountryServiceImpl.class), "countryCategoryForTOS", "getCountryCategoryForTOS()Ljava/util/Map;"))};
    private static final String ASSET_COUNTRIES_JSON = "localization/countries.json";
    private static final String ASSET_LANGUAGES_JSON = "localization/languages.json";
    private static final String ASSET_TOS_COUNTRIES = "tos/countries.json";
    public static final Companion Companion = new Companion(null);
    private static final String LANGUAGE_ENGLISH_SHORT = "en";
    private static final String LOCALE_ENGLISH_US = "en_US";
    private static final String PRIVACY_CATEGORY_US = "US";
    /* access modifiers changed from: private */
    public final Context context;
    private final List<Country> countries = generateCountries();
    private final Lazy countryCategoryForTOS$delegate = LazyKt.lazy(new CountryServiceImpl$countryCategoryForTOS$2(this));
    @NotNull
    private final Country currentCountry;
    @NotNull
    private final String currentLanguage;
    private final dagger.Lazy<GlobalSettingsService> globalSettings;
    private final Lazy localizedLanguageNames$delegate = LazyKt.lazy(new CountryServiceImpl$localizedLanguageNames$2(this));
    private final HashMap<String, String> lookupAbbreviation = new HashMap<>();
    private final HashMap<String, Integer> lookupId = new HashMap<>();
    private final HashMap<String, Integer> lookupIdFromName = new HashMap<>();
    private final HashMap<String, String> lookupName = new HashMap<>();
    private final ResourceUtils resourceUtils;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/shared/service/install/CountryServiceImpl$Companion;", "", "()V", "ASSET_COUNTRIES_JSON", "", "ASSET_LANGUAGES_JSON", "ASSET_TOS_COUNTRIES", "LANGUAGE_ENGLISH_SHORT", "LOCALE_ENGLISH_US", "PRIVACY_CATEGORY_US", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CountryServiceImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final Map<String, String> getCountryCategoryForTOS() {
        Lazy lazy = this.countryCategoryForTOS$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (Map) lazy.getValue();
    }

    private final Map<String, String> getLocalizedLanguageNames() {
        Lazy lazy = this.localizedLanguageNames$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Map) lazy.getValue();
    }

    @Inject
    public CountryServiceImpl(@NotNull Context context2, @NotNull ResourceUtils resourceUtils2, @NotNull dagger.Lazy<GlobalSettingsService> lazy) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(resourceUtils2, "resourceUtils");
        Intrinsics.checkParameterIsNotNull(lazy, "globalSettings");
        this.context = context2;
        this.resourceUtils = resourceUtils2;
        this.globalSettings = lazy;
        Pair generateCountryAndLanguage = generateCountryAndLanguage();
        this.currentCountry = (Country) generateCountryAndLanguage.getFirst();
        this.currentLanguage = (String) generateCountryAndLanguage.getSecond();
    }

    @NotNull
    public Country getCurrentCountry() {
        return this.currentCountry;
    }

    @NotNull
    public String getCurrentLanguage() {
        return this.currentLanguage;
    }

    public boolean isEnglishCurrentDialect() {
        return StringsKt.startsWith$default(getCurrentLocaleIdentifier(), LANGUAGE_ENGLISH_SHORT, false, 2, null);
    }

    public boolean isEnglishUSCurrentLocale() {
        return StringsKt.startsWith$default(getCurrentLocaleIdentifierForV2(), LOCALE_ENGLISH_US, false, 2, null);
    }

    @NotNull
    public String getCurrentLocaleIdentifier() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCurrentLanguage());
        sb.append('-');
        sb.append(getCurrentCountry().getShortName());
        return sb.toString();
    }

    @NotNull
    public String getCurrentLocaleIdentifierForV2() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCurrentLanguage());
        sb.append('_');
        sb.append(getCurrentCountry().getShortName());
        return sb.toString();
    }

    @NotNull
    public List<Country> getAllSupportedCountries() {
        return this.countries;
    }

    public int getIndexOfCurrentCountry() {
        return getIndexOfShortName(getCurrentCountry().getShortName());
    }

    @Nullable
    public String getCurrentCountryLongName() {
        return getLongCountryName(getCurrentCountry());
    }

    @Nullable
    public String getLocalizedNameForCurrentLanguage() {
        Map localizedLanguageNames = getLocalizedLanguageNames();
        if (localizedLanguageNames.containsKey(getCurrentLanguage())) {
            return (String) localizedLanguageNames.get(getCurrentLanguage());
        }
        return (String) localizedLanguageNames.get(LANGUAGE_ENGLISH_SHORT);
    }

    public boolean isCurrentCountryCJK() {
        String shortName = getCurrentCountry().getShortName();
        return Intrinsics.areEqual((Object) shortName, (Object) Country.TRADITIONAL_CHINESE_SHORT) || Intrinsics.areEqual((Object) shortName, (Object) Country.SIMPLIFIED_CHINESE_SHORT) || Intrinsics.areEqual((Object) shortName, (Object) Country.KOREA_SOUTH_SHORT) || Intrinsics.areEqual((Object) shortName, (Object) Country.JAPANESE_SHORT);
    }

    @Nullable
    public Country getCountryFromCountryCode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "code");
        return getCountryFromCountryCodeWithoutInit(str);
    }

    @Nullable
    public Country getCountryFromLongName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        int indexOfLongName = getIndexOfLongName(str);
        if (indexOfLongName == -1) {
            return null;
        }
        return (Country) this.countries.get(indexOfLongName);
    }

    public int getIndexOfShortName(@Nullable String str) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            return 0;
        }
        int i = 0;
        for (Country shortName : this.countries) {
            String shortName2 = shortName.getShortName();
            if (shortName2 != null && StringsKt.equals(shortName2, str, true)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getIndexOfLongName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "longCountryName");
        return getIndexOfShortName(getShortNameFromLongName(str));
    }

    @Nullable
    public String getLongCountryName(@NotNull Country country) {
        Intrinsics.checkParameterIsNotNull(country, "country");
        Map map = this.lookupName;
        String shortName = country.getShortName();
        CharSequence charSequence = shortName;
        if (charSequence == null || charSequence.length() == 0) {
            shortName = "US";
        }
        return (String) map.get(shortName);
    }

    @NotNull
    public String getLocalizedLongCountryName(@NotNull Country country) {
        Intrinsics.checkParameterIsNotNull(country, "country");
        String shortName = country.getShortName();
        HashMap<String, Integer> hashMap = this.lookupId;
        CharSequence charSequence = shortName;
        if (charSequence == null || charSequence.length() == 0) {
            shortName = "US";
        }
        Integer num = (Integer) hashMap.get(shortName);
        if (num != null) {
            Context context2 = this.context;
            Intrinsics.checkExpressionValueIsNotNull(num, "it");
            String string = context2.getString(num.intValue());
            if (string != null) {
                return string;
            }
        }
        return "";
    }

    @NotNull
    public String getShortNameFromLongName(@Nullable String str) {
        HashMap<String, String> hashMap = this.lookupAbbreviation;
        CharSequence charSequence = str;
        boolean z = false;
        if (charSequence == null || charSequence.length() == 0) {
            str = Country.UNITED_STATES_LONG;
        }
        String str2 = (String) hashMap.get(str);
        CharSequence charSequence2 = str2;
        if (charSequence2 == null || charSequence2.length() == 0) {
            z = true;
        }
        if (z) {
            return "US";
        }
        if (str2 != null) {
            return str2;
        }
        Intrinsics.throwNpe();
        return str2;
    }

    public boolean needsToAcceptTOS(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "countryCode");
        String str2 = (String) getCountryCategoryForTOS().get(str);
        return str2 != null && !StringsKt.equals(str2, "US", true);
    }

    private final List<Country> generateCountries() {
        String str;
        List<Country> list = (List) new ApiJsonMapper().tryMapFrom(FileUtils.readAllLinesFromAsset(this.context, ASSET_COUNTRIES_JSON), LIST_MAPPER.class);
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        for (Country country : list) {
            String component1 = country.component1();
            String component2 = country.component2();
            if (component1 == null) {
                str = null;
            } else if (component1 != null) {
                str = component1.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toUpperCase()");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            ResourceUtils resourceUtils2 = this.resourceUtils;
            Context context2 = this.context;
            StringBuilder sb = new StringBuilder();
            sb.append("country_");
            sb.append(str);
            int resourceIdentifier = resourceUtils2.getResourceIdentifier(context2, sb.toString());
            if (str != null) {
                this.lookupId.put(str, Integer.valueOf(resourceIdentifier));
                if (component2 != null) {
                    this.lookupName.put(str, component2);
                }
            }
            if (component2 != null) {
                this.lookupIdFromName.put(component2, Integer.valueOf(resourceIdentifier));
                this.lookupAbbreviation.put(component2, component1);
            }
        }
        return CollectionsKt.sortedWith(list, new CountryServiceImpl$generateCountries$$inlined$sortedBy$1(this));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        if (r4 != false) goto L_0x00c0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.Pair<com.myfitnesspal.shared.model.v1.Country, java.lang.String> generateCountryAndLanguage() {
        /*
            r7 = this;
            com.myfitnesspal.build.BuildConfiguration r0 = new com.myfitnesspal.build.BuildConfiguration
            r0.<init>()
            r1 = 0
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "en"
            boolean r3 = r0.isDebug()
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x0018
            boolean r0 = r0.isQABuild()
            if (r0 == 0) goto L_0x00a2
        L_0x0018:
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r0 = r7.globalSettings
            java.lang.Object r0 = r0.get()
            java.lang.String r3 = "globalSettings.get()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r0 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r0
            java.lang.String r0 = r0.getRequestLocaleOverride()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0036
            int r3 = r0.length()
            if (r3 != 0) goto L_0x0034
            goto L_0x0036
        L_0x0034:
            r3 = 0
            goto L_0x0037
        L_0x0036:
            r3 = 1
        L_0x0037:
            if (r3 != 0) goto L_0x00a2
            java.lang.String r3 = "_"
            kotlin.text.Regex r6 = new kotlin.text.Regex
            r6.<init>(r3)
            java.util.List r0 = r6.split(r0, r5)
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L_0x0077
            int r3 = r0.size()
            java.util.ListIterator r3 = r0.listIterator(r3)
        L_0x0052:
            boolean r6 = r3.hasPrevious()
            if (r6 == 0) goto L_0x0077
            java.lang.Object r6 = r3.previous()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0068
            r6 = 1
            goto L_0x0069
        L_0x0068:
            r6 = 0
        L_0x0069:
            if (r6 != 0) goto L_0x0052
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            int r3 = r3.nextIndex()
            int r3 = r3 + r4
            java.util.List r0 = kotlin.collections.CollectionsKt.take(r0, r3)
            goto L_0x007b
        L_0x0077:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x007b:
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x009a
            java.lang.String[] r3 = new java.lang.String[r5]
            java.lang.Object[] r0 = r0.toArray(r3)
            if (r0 == 0) goto L_0x0092
            java.lang.String[] r0 = (java.lang.String[]) r0
            int r3 = r0.length
            r6 = 2
            if (r3 != r6) goto L_0x00a2
            r1 = r0[r4]
            r2 = r0[r5]
            goto L_0x00a2
        L_0x0092:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Array<T>"
            r0.<init>(r1)
            throw r0
        L_0x009a:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type java.util.Collection<T>"
            r0.<init>(r1)
            throw r0
        L_0x00a2:
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x00b0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x00ae
            goto L_0x00b0
        L_0x00ae:
            r0 = 0
            goto L_0x00b1
        L_0x00b0:
            r0 = 1
        L_0x00b1:
            if (r0 != 0) goto L_0x00c0
            r0 = r2
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x00bd
            goto L_0x00be
        L_0x00bd:
            r4 = 0
        L_0x00be:
            if (r4 == 0) goto L_0x00d6
        L_0x00c0:
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r1 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r1 = r0.getCountry()
            java.lang.String r2 = r0.getLanguage()
            java.lang.String r0 = "it.language"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
        L_0x00d6:
            com.myfitnesspal.shared.model.v1.Country r0 = r7.getCountryFromCountryCodeWithoutInit(r1)
            if (r0 == 0) goto L_0x00df
            if (r0 == 0) goto L_0x00df
            goto L_0x00f5
        L_0x00df:
            r0 = r7
            com.myfitnesspal.shared.service.install.CountryServiceImpl r0 = (com.myfitnesspal.shared.service.install.CountryServiceImpl) r0
            java.lang.String r2 = "en"
            java.lang.String r1 = "US"
            com.myfitnesspal.shared.model.v1.Country r0 = r0.getCountryFromCountryCodeWithoutInit(r1)
            if (r0 == 0) goto L_0x00ef
            if (r0 == 0) goto L_0x00ef
            goto L_0x00f5
        L_0x00ef:
            com.myfitnesspal.shared.model.v1.Country$Companion r0 = com.myfitnesspal.shared.model.v1.Country.Companion
            com.myfitnesspal.shared.model.v1.Country r0 = r0.newInstanceUSA()
        L_0x00f5:
            kotlin.Pair r1 = new kotlin.Pair
            r1.<init>(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.install.CountryServiceImpl.generateCountryAndLanguage():kotlin.Pair");
    }

    private final Country getCountryFromCountryCodeWithoutInit(String str) {
        Object obj;
        Iterator it = this.countries.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            String shortName = ((Country) obj).getShortName();
            boolean z = true;
            if (shortName == null || !StringsKt.equals(shortName, str, true)) {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        return (Country) obj;
    }
}
