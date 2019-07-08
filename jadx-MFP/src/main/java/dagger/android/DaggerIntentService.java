package dagger.android;

import android.app.IntentService;
import android.app.Service;

public abstract class DaggerIntentService extends IntentService {
    public DaggerIntentService(String str) {
        super(str);
    }

    public void onCreate() {
        AndroidInjection.inject((Service) this);
        super.onCreate();
    }
}
