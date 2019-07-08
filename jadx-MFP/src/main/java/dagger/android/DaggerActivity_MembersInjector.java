package dagger.android;

import android.app.Fragment;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DaggerActivity_MembersInjector implements MembersInjector<DaggerActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;

    public DaggerActivity_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.fragmentInjectorProvider = provider;
    }

    public static MembersInjector<DaggerActivity> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new DaggerActivity_MembersInjector(provider);
    }

    public void injectMembers(DaggerActivity daggerActivity) {
        injectFragmentInjector(daggerActivity, (DispatchingAndroidInjector) this.fragmentInjectorProvider.get());
    }

    public static void injectFragmentInjector(DaggerActivity daggerActivity, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerActivity.fragmentInjector = dispatchingAndroidInjector;
    }
}
