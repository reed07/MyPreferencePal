package com.myfitnesspal.feature.search.ui.adapter;

import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SearchCategoryItemAdapter_MembersInjector implements MembersInjector<SearchCategoryItemAdapter> {
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public SearchCategoryItemAdapter_MembersInjector(Provider<UserEnergyService> provider, Provider<LocalizedStringsUtil> provider2) {
        this.userEnergyServiceProvider = provider;
        this.localizedStringsUtilProvider = provider2;
    }

    public static MembersInjector<SearchCategoryItemAdapter> create(Provider<UserEnergyService> provider, Provider<LocalizedStringsUtil> provider2) {
        return new SearchCategoryItemAdapter_MembersInjector(provider, provider2);
    }

    public void injectMembers(SearchCategoryItemAdapter searchCategoryItemAdapter) {
        injectUserEnergyService(searchCategoryItemAdapter, (UserEnergyService) this.userEnergyServiceProvider.get());
        injectLocalizedStringsUtil(searchCategoryItemAdapter, (LocalizedStringsUtil) this.localizedStringsUtilProvider.get());
    }

    public static void injectUserEnergyService(SearchCategoryItemAdapter searchCategoryItemAdapter, UserEnergyService userEnergyService) {
        searchCategoryItemAdapter.userEnergyService = userEnergyService;
    }

    public static void injectLocalizedStringsUtil(SearchCategoryItemAdapter searchCategoryItemAdapter, LocalizedStringsUtil localizedStringsUtil) {
        searchCategoryItemAdapter.localizedStringsUtil = localizedStringsUtil;
    }
}
