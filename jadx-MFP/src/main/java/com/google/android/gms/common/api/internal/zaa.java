package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0024zaa> zack;

    @VisibleForTesting
    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa reason: collision with other inner class name */
    static class C0024zaa extends LifecycleCallback {
        private List<Runnable> zacl = new ArrayList();

        /* access modifiers changed from: private */
        public static C0024zaa zaa(Activity activity) {
            C0024zaa zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                zaa = (C0024zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0024zaa.class);
                if (zaa == null) {
                    zaa = new C0024zaa(fragment);
                }
            }
            return zaa;
        }

        private C0024zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacl.add(runnable);
        }

        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacl;
                this.zacl = new ArrayList();
            }
            for (Runnable run : list) {
                run.run();
            }
        }
    }

    public zaa(Activity activity) {
        this(C0024zaa.zaa(activity));
    }

    @VisibleForTesting
    private zaa(C0024zaa zaa) {
        this.zack = new WeakReference<>(zaa);
    }

    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0024zaa zaa = (C0024zaa) this.zack.get();
        if (zaa != null) {
            zaa.zaa(runnable);
            return this;
        }
        throw new IllegalStateException("The target activity has already been GC'd");
    }
}
