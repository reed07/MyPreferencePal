package dagger.android;

import android.app.Fragment;
import android.content.Context;
import javax.inject.Inject;

@Deprecated
public abstract class DaggerFragment extends Fragment implements HasFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    public void onAttach(Context context) {
        AndroidInjection.inject((Fragment) this);
        super.onAttach(context);
    }

    public AndroidInjector<Fragment> fragmentInjector() {
        return this.childFragmentInjector;
    }
}
