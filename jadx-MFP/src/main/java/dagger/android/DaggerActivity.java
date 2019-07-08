package dagger.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import javax.inject.Inject;

public abstract class DaggerActivity extends Activity implements HasFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
    }

    public AndroidInjector<Fragment> fragmentInjector() {
        return this.fragmentInjector;
    }
}
