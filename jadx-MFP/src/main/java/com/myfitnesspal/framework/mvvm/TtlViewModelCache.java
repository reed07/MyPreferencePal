package com.myfitnesspal.framework.mvvm;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;

public final class TtlViewModelCache implements ViewModelCache {
    private static final int TTL_MILLIS = 3000;
    /* access modifiers changed from: private */
    public final Map<String, BaseViewModel> cache = new HashMap();
    /* access modifiers changed from: private */
    public final Map<String, Runnable> callbacks = new HashMap();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void put(BaseViewModel baseViewModel) {
        String id = baseViewModel.getId();
        this.cache.put(id, baseViewModel);
        schedule(id);
    }

    public <T extends BaseViewModel> T get(String str) {
        interrupt(str);
        return (BaseViewModel) this.cache.remove(str);
    }

    private void interrupt(String str) {
        Runnable runnable = (Runnable) this.callbacks.remove(str);
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    private void schedule(final String str) {
        interrupt(str);
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                TtlViewModelCache.this.callbacks.remove(str);
                TtlViewModelCache.this.cache.remove(str);
            }
        };
        this.callbacks.put(str, r0);
        this.handler.postDelayed(r0, 3000);
    }
}
