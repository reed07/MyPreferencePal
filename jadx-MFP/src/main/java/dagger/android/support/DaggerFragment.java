package dagger.android.support;

import android.content.Context;
import android.support.v4.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

public abstract class DaggerFragment extends Fragment implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.childFragmentInjector;
    }
}
