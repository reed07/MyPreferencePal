package com.myfitnesspal.shared.ui.component;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import com.uacf.core.util.Ln;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleLiveEvent";
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    public void observe(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        if (hasActiveObservers()) {
            Ln.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(lifecycleOwner, new Observer(observer) {
            private final /* synthetic */ Observer f$1;

            {
                this.f$1 = r2;
            }

            public final void onChanged(Object obj) {
                SingleLiveEvent.lambda$observe$0(SingleLiveEvent.this, this.f$1, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$observe$0(SingleLiveEvent singleLiveEvent, Observer observer, Object obj) {
        if (singleLiveEvent.mPending.compareAndSet(true, false)) {
            observer.onChanged(obj);
        }
    }

    @MainThread
    public void setValue(@Nullable T t) {
        this.mPending.set(true);
        super.setValue(t);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
