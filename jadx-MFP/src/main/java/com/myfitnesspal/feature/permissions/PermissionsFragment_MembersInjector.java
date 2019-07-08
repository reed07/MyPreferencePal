package com.myfitnesspal.feature.permissions;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class PermissionsFragment_MembersInjector implements MembersInjector<PermissionsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PermissionAnalyticsHelper> permissionAnalyticsHelperProvider;

    public PermissionsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PermissionAnalyticsHelper> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.permissionAnalyticsHelperProvider = provider3;
    }

    public static MembersInjector<PermissionsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PermissionAnalyticsHelper> provider3) {
        return new PermissionsFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(PermissionsFragment permissionsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(permissionsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(permissionsFragment, (Glide) this.glideProvider.get());
        injectPermissionAnalyticsHelper(permissionsFragment, DoubleCheck.lazy(this.permissionAnalyticsHelperProvider));
    }

    public static void injectPermissionAnalyticsHelper(PermissionsFragment permissionsFragment, Lazy<PermissionAnalyticsHelper> lazy) {
        permissionsFragment.permissionAnalyticsHelper = lazy;
    }
}
