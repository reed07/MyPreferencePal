package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ViewModelProviders {

    @Deprecated
    public static class DefaultFactory extends AndroidViewModelFactory {
    }

    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.");
    }

    private static Activity checkActivity(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
    }

    @MainThread
    @NonNull
    public static ViewModelProvider of(@NonNull FragmentActivity fragmentActivity) {
        return of(fragmentActivity, (Factory) null);
    }

    @MainThread
    @NonNull
    public static ViewModelProvider of(@NonNull Fragment fragment, @Nullable Factory factory) {
        Application checkApplication = checkApplication(checkActivity(fragment));
        if (factory == null) {
            factory = AndroidViewModelFactory.getInstance(checkApplication);
        }
        return new ViewModelProvider(ViewModelStores.of(fragment), factory);
    }

    @MainThread
    @NonNull
    public static ViewModelProvider of(@NonNull FragmentActivity fragmentActivity, @Nullable Factory factory) {
        Application checkApplication = checkApplication(fragmentActivity);
        if (factory == null) {
            factory = AndroidViewModelFactory.getInstance(checkApplication);
        }
        return new ViewModelProvider(ViewModelStores.of(fragmentActivity), factory);
    }
}
