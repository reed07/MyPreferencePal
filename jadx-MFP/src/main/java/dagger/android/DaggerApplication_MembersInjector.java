package dagger.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DaggerApplication_MembersInjector implements MembersInjector<DaggerApplication> {
    private final Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider;
    private final Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider;
    private final Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider;

    public DaggerApplication_MembersInjector(Provider<DispatchingAndroidInjector<Activity>> provider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> provider2, Provider<DispatchingAndroidInjector<Fragment>> provider3, Provider<DispatchingAndroidInjector<Service>> provider4, Provider<DispatchingAndroidInjector<ContentProvider>> provider5) {
        this.activityInjectorProvider = provider;
        this.broadcastReceiverInjectorProvider = provider2;
        this.fragmentInjectorProvider = provider3;
        this.serviceInjectorProvider = provider4;
        this.contentProviderInjectorProvider = provider5;
    }

    public static MembersInjector<DaggerApplication> create(Provider<DispatchingAndroidInjector<Activity>> provider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> provider2, Provider<DispatchingAndroidInjector<Fragment>> provider3, Provider<DispatchingAndroidInjector<Service>> provider4, Provider<DispatchingAndroidInjector<ContentProvider>> provider5) {
        DaggerApplication_MembersInjector daggerApplication_MembersInjector = new DaggerApplication_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return daggerApplication_MembersInjector;
    }

    public void injectMembers(DaggerApplication daggerApplication) {
        injectActivityInjector(daggerApplication, (DispatchingAndroidInjector) this.activityInjectorProvider.get());
        injectBroadcastReceiverInjector(daggerApplication, (DispatchingAndroidInjector) this.broadcastReceiverInjectorProvider.get());
        injectFragmentInjector(daggerApplication, (DispatchingAndroidInjector) this.fragmentInjectorProvider.get());
        injectServiceInjector(daggerApplication, (DispatchingAndroidInjector) this.serviceInjectorProvider.get());
        injectContentProviderInjector(daggerApplication, (DispatchingAndroidInjector) this.contentProviderInjectorProvider.get());
        injectSetInjected(daggerApplication);
    }

    public static void injectActivityInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<Activity> dispatchingAndroidInjector) {
        daggerApplication.activityInjector = dispatchingAndroidInjector;
    }

    public static void injectBroadcastReceiverInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<BroadcastReceiver> dispatchingAndroidInjector) {
        daggerApplication.broadcastReceiverInjector = dispatchingAndroidInjector;
    }

    public static void injectFragmentInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerApplication.fragmentInjector = dispatchingAndroidInjector;
    }

    public static void injectServiceInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<Service> dispatchingAndroidInjector) {
        daggerApplication.serviceInjector = dispatchingAndroidInjector;
    }

    public static void injectContentProviderInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<ContentProvider> dispatchingAndroidInjector) {
        daggerApplication.contentProviderInjector = dispatchingAndroidInjector;
    }

    public static void injectSetInjected(DaggerApplication daggerApplication) {
        daggerApplication.setInjected();
    }
}
