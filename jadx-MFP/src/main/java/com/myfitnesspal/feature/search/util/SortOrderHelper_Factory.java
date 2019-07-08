package com.myfitnesspal.feature.search.util;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SortOrderHelper_Factory implements Factory<SortOrderHelper> {
    private final Provider<SharedPreferences> exerciseSortingPreferencesProvider;
    private final Provider<SharedPreferences> foodSortingPreferencesProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;

    public SortOrderHelper_Factory(Provider<SharedPreferences> provider, Provider<SharedPreferences> provider2, Provider<LocalSettingsService> provider3) {
        this.exerciseSortingPreferencesProvider = provider;
        this.foodSortingPreferencesProvider = provider2;
        this.localSettingsServiceProvider = provider3;
    }

    public SortOrderHelper get() {
        return provideInstance(this.exerciseSortingPreferencesProvider, this.foodSortingPreferencesProvider, this.localSettingsServiceProvider);
    }

    public static SortOrderHelper provideInstance(Provider<SharedPreferences> provider, Provider<SharedPreferences> provider2, Provider<LocalSettingsService> provider3) {
        return new SortOrderHelper((SharedPreferences) provider.get(), (SharedPreferences) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static SortOrderHelper_Factory create(Provider<SharedPreferences> provider, Provider<SharedPreferences> provider2, Provider<LocalSettingsService> provider3) {
        return new SortOrderHelper_Factory(provider, provider2, provider3);
    }

    public static SortOrderHelper newSortOrderHelper(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, Lazy<LocalSettingsService> lazy) {
        return new SortOrderHelper(sharedPreferences, sharedPreferences2, lazy);
    }
}
