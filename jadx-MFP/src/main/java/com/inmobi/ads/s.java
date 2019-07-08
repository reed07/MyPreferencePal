package com.inmobi.ads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;

/* compiled from: DecorViewVisibilityTracker */
final class s extends ce {
    private static final String d = "s";
    @NonNull
    private OnPreDrawListener e;
    @NonNull
    private final WeakReference<View> f;

    /* access modifiers changed from: protected */
    public final int a() {
        return 100;
    }

    /* access modifiers changed from: protected */
    public final void b() {
    }

    s(@NonNull a aVar, @NonNull Activity activity) {
        super(aVar);
        View decorView = activity.getWindow().getDecorView();
        this.f = new WeakReference<>(decorView);
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.e = new OnPreDrawListener() {
                public final boolean onPreDraw() {
                    s.this.g();
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(this.e);
        }
    }

    private void h() {
        View view = (View) this.f.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void e() {
        h();
        super.e();
    }

    public final void c() {
        if (!this.a) {
            h();
            super.c();
        }
    }

    public final void d() {
        if (this.a) {
            View view = (View) this.f.get();
            if (view != null) {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnPreDrawListener(this.e);
                }
            }
            super.d();
        }
    }
}
