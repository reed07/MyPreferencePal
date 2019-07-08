package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector.Factory;
import dagger.multibindings.Multibinds;
import java.util.Map;

@Module(includes = {AndroidInjectionModule.class})
public abstract class AndroidSupportInjectionModule {
    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<Class<? extends Fragment>, Factory<? extends Fragment>> supportFragmentInjectorFactories();

    /* access modifiers changed from: 0000 */
    @Multibinds
    public abstract Map<String, Factory<? extends Fragment>> supportFragmentInjectorFactoriesWithStringKeys();

    private AndroidSupportInjectionModule() {
    }
}
