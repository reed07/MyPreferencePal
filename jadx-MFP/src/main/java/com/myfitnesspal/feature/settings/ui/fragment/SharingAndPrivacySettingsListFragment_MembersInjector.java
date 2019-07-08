package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SharingAndPrivacySettingsListFragment_MembersInjector implements MembersInjector<SharingAndPrivacySettingsListFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiarySharingSettingsManager> diarySharingSettingsManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;

    public SharingAndPrivacySettingsListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<DiarySharingSettingsManager> provider3, Provider<GlobalSettingsService> provider4, Provider<DbConnectionManager> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.diarySharingSettingsManagerProvider = provider3;
        this.globalSettingsServiceProvider = provider4;
        this.dbConnectionManagerProvider = provider5;
    }

    public static MembersInjector<SharingAndPrivacySettingsListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<DiarySharingSettingsManager> provider3, Provider<GlobalSettingsService> provider4, Provider<DbConnectionManager> provider5) {
        SharingAndPrivacySettingsListFragment_MembersInjector sharingAndPrivacySettingsListFragment_MembersInjector = new SharingAndPrivacySettingsListFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return sharingAndPrivacySettingsListFragment_MembersInjector;
    }

    public void injectMembers(SharingAndPrivacySettingsListFragment sharingAndPrivacySettingsListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(sharingAndPrivacySettingsListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(sharingAndPrivacySettingsListFragment, (Glide) this.glideProvider.get());
        injectDiarySharingSettingsManager(sharingAndPrivacySettingsListFragment, (DiarySharingSettingsManager) this.diarySharingSettingsManagerProvider.get());
        injectGlobalSettingsService(sharingAndPrivacySettingsListFragment, DoubleCheck.lazy(this.globalSettingsServiceProvider));
        injectDbConnectionManager(sharingAndPrivacySettingsListFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectDiarySharingSettingsManager(SharingAndPrivacySettingsListFragment sharingAndPrivacySettingsListFragment, DiarySharingSettingsManager diarySharingSettingsManager) {
        sharingAndPrivacySettingsListFragment.diarySharingSettingsManager = diarySharingSettingsManager;
    }

    public static void injectGlobalSettingsService(SharingAndPrivacySettingsListFragment sharingAndPrivacySettingsListFragment, Lazy<GlobalSettingsService> lazy) {
        sharingAndPrivacySettingsListFragment.globalSettingsService = lazy;
    }

    public static void injectDbConnectionManager(SharingAndPrivacySettingsListFragment sharingAndPrivacySettingsListFragment, Lazy<DbConnectionManager> lazy) {
        sharingAndPrivacySettingsListFragment.dbConnectionManager = lazy;
    }
}
