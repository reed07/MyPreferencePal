package dagger.android;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import javax.inject.Inject;

public abstract class DaggerDialogFragment extends DialogFragment implements HasFragmentInjector {
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
