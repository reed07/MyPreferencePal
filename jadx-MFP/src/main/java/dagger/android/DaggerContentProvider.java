package dagger.android;

import android.content.ContentProvider;
import android.support.annotation.CallSuper;

public abstract class DaggerContentProvider extends ContentProvider {
    @CallSuper
    public boolean onCreate() {
        AndroidInjection.inject((ContentProvider) this);
        return true;
    }
}
