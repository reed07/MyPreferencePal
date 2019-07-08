package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.android.AndroidInjector;

public interface HasSupportFragmentInjector {
    AndroidInjector<Fragment> supportFragmentInjector();
}
