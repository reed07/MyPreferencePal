package com.myfitnesspal.shared.bus;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class PostFromAnyThreadBus extends Bus {
    private Handler handler;

    public PostFromAnyThreadBus() {
        super(ThreadEnforcer.MAIN);
    }

    public void post(Object obj) {
        makeCallOnMainThread(new Runnable(obj) {
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                $$Lambda$PostFromAnyThreadBus$pSu5dpMwEEKyHVHL_75C9ezb5pw.super.post(this.f$1);
            }
        });
    }

    public void register(Object obj) {
        makeCallOnMainThread(new Runnable(obj) {
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                PostFromAnyThreadBus.lambda$register$1(PostFromAnyThreadBus.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$register$1(PostFromAnyThreadBus postFromAnyThreadBus, Object obj) {
        try {
            super.register(obj);
        } catch (IllegalArgumentException unused) {
        }
    }

    public void unregister(Object obj) {
        makeCallOnMainThread(new Runnable(obj) {
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                PostFromAnyThreadBus.lambda$unregister$2(PostFromAnyThreadBus.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$unregister$2(PostFromAnyThreadBus postFromAnyThreadBus, Object obj) {
        try {
            super.unregister(obj);
        } catch (IllegalArgumentException unused) {
        }
    }

    private void makeCallOnMainThread(Runnable runnable) {
        Looper mainLooper = Looper.getMainLooper();
        if (Looper.myLooper() != mainLooper) {
            if (this.handler == null) {
                this.handler = new Handler(mainLooper);
            }
            this.handler.post(new Runnable(runnable) {
                private final /* synthetic */ Runnable f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.run();
                }
            });
            return;
        }
        runnable.run();
    }
}
