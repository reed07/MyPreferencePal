package dagger.android.support;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class DaggerApplication_MembersInjector implements MembersInjector<DaggerApplication> {
    private final Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider;
    private final Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider;
    private final Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public DaggerApplication_MembersInjector(Provider<DispatchingAndroidInjector<Activity>> provider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> provider2, Provider<DispatchingAndroidInjector<Fragment>> provider3, Provider<DispatchingAndroidInjector<Service>> provider4, Provider<DispatchingAndroidInjector<ContentProvider>> provider5, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider6) {
        this.activityInjectorProvider = provider;
        this.broadcastReceiverInjectorProvider = provider2;
        this.fragmentInjectorProvider = provider3;
        this.serviceInjectorProvider = provider4;
        this.contentProviderInjectorProvider = provider5;
        this.supportFragmentInjectorProvider = provider6;
    }

    public static MembersInjector<DaggerApplication> create(Provider<DispatchingAndroidInjector<Activity>> provider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> provider2, Provider<DispatchingAndroidInjector<Fragment>> provider3, Provider<DispatchingAndroidInjector<Service>> provider4, Provider<DispatchingAndroidInjector<ContentProvider>> provider5, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider6) {
        DaggerApplication_MembersInjector daggerApplication_MembersInjector = new DaggerApplication_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return daggerApplication_MembersInjector;
    }

    public void injectMembers(DaggerApplication daggerApplication) {
        dagger.android.DaggerApplication_MembersInjector.injectActivityInjector(daggerApplication, (DispatchingAndroidInjector) this.activityInjectorProvider.get());
        dagger.android.DaggerApplication_MembersInjector.injectBroadcastReceiverInjector(daggerApplication, (DispatchingAndroidInjector) this.broadcastReceiverInjectorProvider.get());
        dagger.android.DaggerApplication_MembersInjector.injectFragmentInjector(daggerApplication, (DispatchingAndroidInjector) this.fragmentInjectorProvider.get());
        dagger.android.DaggerApplication_MembersInjector.injectServiceInjector(daggerApplication, (DispatchingAndroidInjector) this.serviceInjectorProvider.get());
        dagger.android.DaggerApplication_MembersInjector.injectContentProviderInjector(daggerApplication, (DispatchingAndroidInjector) this.contentProviderInjectorProvider.get());
        dagger.android.DaggerApplication_MembersInjector.injectSetInjected(daggerApplication);
        injectSupportFragmentInjector(daggerApplication, (DispatchingAndroidInjector) this.supportFragmentInjectorProvider.get());
    }

    public static void injectSupportFragmentInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector) {
        daggerApplication.supportFragmentInjector = dispatchingAndroidInjector;
    }
}
