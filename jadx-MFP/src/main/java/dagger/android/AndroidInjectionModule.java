package dagger.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import dagger.Module;
import dagger.android.AndroidInjector.Factory;
import dagger.multibindings.Multibinds;
import java.util.Map;

@Module
public abstract class AndroidInjectionModule {
    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<Class<? extends Activity>, Factory<? extends Activity>> activityInjectorFactories();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<String, Factory<? extends Activity>> activityInjectorFactoriesWithStringKeys();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<Class<? extends BroadcastReceiver>, Factory<? extends BroadcastReceiver>> broadcastReceiverInjectorFactories();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<String, Factory<? extends BroadcastReceiver>> broadcastReceiverInjectorFactoriesWithStringKeys();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<Class<? extends ContentProvider>, Factory<? extends ContentProvider>> contentProviderInjectorFactories();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<String, Factory<? extends ContentProvider>> contentProviderInjectorFactoriesWithStringKeys();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<Class<? extends Fragment>, Factory<? extends Fragment>> fragmentInjectorFactories();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<String, Factory<? extends Fragment>> fragmentInjectorFactoriesWithStringKeys();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<Class<? extends Service>, Factory<? extends Service>> serviceInjectorFactories();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<String, Factory<? extends Service>> serviceInjectorFactoriesWithStringKeys();

    private AndroidInjectionModule() {
    }
}
