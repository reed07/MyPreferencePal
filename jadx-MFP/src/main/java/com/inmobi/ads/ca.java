package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;

/* compiled from: ViewableAd */
public abstract class ca {
    @NonNull
    AdContainer a;
    @Nullable
    a b;
    @Nullable
    protected WeakReference<View> c;

    /* compiled from: ViewableAd */
    protected static abstract class a {
        private boolean a;

        public abstract View a(View view, ViewGroup viewGroup, boolean z, RenderView renderView);

        protected a() {
        }

        public boolean b() {
            return this.a;
        }

        public void a() {
            if (!this.a) {
                this.a = true;
            }
        }
    }

    @Nullable
    public View a() {
        return null;
    }

    @Nullable
    public abstract View a(View view, ViewGroup viewGroup, boolean z);

    public abstract void a(int i);

    public abstract void a(Context context, int i);

    public abstract void a(@Nullable View... viewArr);

    public abstract void d();

    public ca() {
    }

    public ca(@NonNull AdContainer adContainer) {
        this.a = adContainer;
    }

    @Nullable
    public a f() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final void a(View view) {
        this.c = new WeakReference<>(view);
    }

    @NonNull
    public c c() {
        return new c();
    }

    @Nullable
    public View b() {
        WeakReference<View> weakReference = this.c;
        if (weakReference == null) {
            return null;
        }
        return (View) weakReference.get();
    }

    public void e() {
        WeakReference<View> weakReference = this.c;
        if (weakReference != null) {
            weakReference.clear();
        }
    }
}
