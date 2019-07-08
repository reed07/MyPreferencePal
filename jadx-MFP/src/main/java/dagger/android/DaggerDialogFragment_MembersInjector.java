package dagger.android;

import android.app.Fragment;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DaggerDialogFragment_MembersInjector implements MembersInjector<DaggerDialogFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public DaggerDialogFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.childFragmentInjectorProvider = provider;
    }

    public static MembersInjector<DaggerDialogFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new DaggerDialogFragment_MembersInjector(provider);
    }

    public void injectMembers(DaggerDialogFragment daggerDialogFragment) {
        injectChildFragmentInjector(daggerDialogFragment, (DispatchingAndroidInjector) this.childFragmentInjectorProvider.get());
    }

    public static void injectChildFragmentInjector(DaggerDialogFragment daggerDialogFragment, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerDialogFragment.childFragmentInjector = dispatchingAndroidInjector;
    }
}
