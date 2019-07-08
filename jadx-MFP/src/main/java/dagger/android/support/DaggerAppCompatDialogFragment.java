package dagger.android.support;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

public abstract class DaggerAppCompatDialogFragment extends AppCompatDialogFragment implements HasSupportFragmentInjector {
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
