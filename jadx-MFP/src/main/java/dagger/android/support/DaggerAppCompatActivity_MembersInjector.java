package dagger.android.support;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class DaggerAppCompatActivity_MembersInjector implements MembersInjector<DaggerAppCompatActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public DaggerAppCompatActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
    }

    public static MembersInjector<DaggerAppCompatActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        return new DaggerAppCompatActivity_MembersInjector(provider, provider2);
    }

    public void injectMembers(DaggerAppCompatActivity daggerAppCompatActivity) {
        injectSupportFragmentInjector(daggerAppCompatActivity, (DispatchingAndroidInjector) this.supportFragmentInjectorProvider.get());
        injectFrameworkFragmentInjector(daggerAppCompatActivity, (DispatchingAndroidInjector) this.frameworkFragmentInjectorProvider.get());
    }

    public static void injectSupportFragmentInjector(DaggerAppCompatActivity daggerAppCompatActivity, DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector) {
        daggerAppCompatActivity.supportFragmentInjector = dispatchingAndroidInjector;
    }

    public static void injectFrameworkFragmentInjector(DaggerAppCompatActivity daggerAppCompatActivity, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerAppCompatActivity.frameworkFragmentInjector = dispatchingAndroidInjector;
    }
}
