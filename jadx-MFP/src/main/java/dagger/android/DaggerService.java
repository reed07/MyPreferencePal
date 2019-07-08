package dagger.android;

import android.app.Service;

public abstract class DaggerService extends Service {
    public void onCreate() {
        AndroidInjection.inject((Service) this);
        super.onCreate();
    }
}
