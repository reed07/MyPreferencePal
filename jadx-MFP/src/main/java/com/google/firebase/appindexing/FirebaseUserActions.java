package com.google.firebase.appindexing;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzo;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;

public abstract class FirebaseUserActions {
    public static final String APP_INDEXING_API_TAG = "FirebaseUserActions";
    @GuardedBy("FirebaseUserActions.class")
    private static WeakReference<FirebaseUserActions> zzeh;

    public abstract Task<Void> end(Action action);

    public abstract Task<Void> start(Action action);

    public static synchronized FirebaseUserActions getInstance() {
        FirebaseUserActions firebaseUserActions;
        synchronized (FirebaseUserActions.class) {
            firebaseUserActions = zzeh == null ? null : (FirebaseUserActions) zzeh.get();
            if (firebaseUserActions == null) {
                zzo zzo = new zzo(FirebaseApp.getInstance().getApplicationContext());
                zzeh = new WeakReference<>(zzo);
                firebaseUserActions = zzo;
            }
        }
        return firebaseUserActions;
    }
}
