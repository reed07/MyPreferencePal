package android.arch.lifecycle;

import android.support.annotation.NonNull;

public interface DefaultLifecycleObserver extends FullLifecycleObserver {

    /* renamed from: android.arch.lifecycle.DefaultLifecycleObserver$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onCreate(@NonNull DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onDestroy(@NonNull DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onPause(@NonNull DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onResume(@NonNull DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onStart(@NonNull DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onStop(@NonNull DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }
    }

    void onCreate(@NonNull LifecycleOwner lifecycleOwner);

    void onDestroy(@NonNull LifecycleOwner lifecycleOwner);

    void onPause(@NonNull LifecycleOwner lifecycleOwner);

    void onResume(@NonNull LifecycleOwner lifecycleOwner);

    void onStart(@NonNull LifecycleOwner lifecycleOwner);

    void onStop(@NonNull LifecycleOwner lifecycleOwner);
}
