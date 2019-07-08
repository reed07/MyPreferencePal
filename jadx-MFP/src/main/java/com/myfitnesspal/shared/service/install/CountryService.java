package com.myfitnesspal.shared.service.install;

import com.myfitnesspal.shared.model.v1.Country;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020\u000bH&J\u0012\u0010!\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020\u000bH&J\u0010\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u000bH&J\u0012\u0010%\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u0004H&J\u0012\u0010)\u001a\u0004\u0018\u00010\u000b2\u0006\u0010(\u001a\u00020\u0004H&J\u0012\u0010*\u001a\u00020\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u000bH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0012\u0010\u0010\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0012\u0010\u0012\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001aR\u0012\u0010\u001c\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\r¨\u0006-"}, d2 = {"Lcom/myfitnesspal/shared/service/install/CountryService;", "", "allSupportedCountries", "", "Lcom/myfitnesspal/shared/model/v1/Country;", "getAllSupportedCountries", "()Ljava/util/List;", "currentCountry", "getCurrentCountry", "()Lcom/myfitnesspal/shared/model/v1/Country;", "currentCountryLongName", "", "getCurrentCountryLongName", "()Ljava/lang/String;", "currentLanguage", "getCurrentLanguage", "currentLocaleIdentifier", "getCurrentLocaleIdentifier", "currentLocaleIdentifierForV2", "getCurrentLocaleIdentifierForV2", "indexOfCurrentCountry", "", "getIndexOfCurrentCountry", "()I", "isCurrentCountryCJK", "", "()Z", "isEnglishCurrentDialect", "isEnglishUSCurrentLocale", "localizedNameForCurrentLanguage", "getLocalizedNameForCurrentLanguage", "getCountryFromCountryCode", "code", "getCountryFromLongName", "name", "getIndexOfLongName", "longCountryName", "getIndexOfShortName", "shortCountryName", "getLocalizedLongCountryName", "country", "getLongCountryName", "getShortNameFromLongName", "needsToAcceptTOS", "countryCode", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: CountryService.kt */
public interface CountryService {
    @NotNull
    List<Country> getAllSupportedCountries();

    @Nullable
    Country getCountryFromCountryCode(@NotNull String str);

    @Nullable
    Country getCountryFromLongName(@NotNull String str);

    @NotNull
    Country getCurrentCountry();

    @Nullable
    String getCurrentCountryLongName();

    @NotNull
    String getCurrentLanguage();

    @NotNull
    String getCurrentLocaleIdentifier();

    @NotNull
    String getCurrentLocaleIdentifierForV2();

    int getIndexOfCurrentCountry();

    int getIndexOfLongName(@NotNull String str);

    int getIndexOfShortName(@Nullable String str);

    @NotNull
    String getLocalizedLongCountryName(@NotNull Country country);

    @Nullable
    String getLocalizedNameForCurrentLanguage();

    @Nullable
    String getLongCountryName(@NotNull Country country);

    @NotNull
    String getShortNameFromLongName(@Nullable String str);

    boolean isCurrentCountryCJK();

    boolean isEnglishCurrentDialect();

    boolean isEnglishUSCurrentLocale();

    boolean needsToAcceptTOS(@NotNull String str);
}
