package com.inmobi.ads;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: NativeDataSource */
final class ap extends PagerAdapter implements ax {
    private static final String a = "ap";
    private static Handler e = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean b;
    @NonNull
    private final ao c;
    /* access modifiers changed from: private */
    public au d;
    /* access modifiers changed from: private */
    @NonNull
    public SparseArray<Runnable> f = new SparseArray<>();

    ap(@NonNull ao aoVar, @NonNull au auVar) {
        this.c = aoVar;
        this.d = auVar;
    }

    public final int getItemPosition(Object obj) {
        Object tag = obj == null ? null : ((View) obj).getTag();
        if (tag == null || !(tag instanceof Integer)) {
            return -2;
        }
        return ((Integer) tag).intValue();
    }

    public final int getCount() {
        return this.c.b();
    }

    public final boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view.equals(obj);
    }

    @TargetApi(21)
    public final Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        am a2 = this.c.a(i);
        if (a2 == null) {
            return null;
        }
        ViewGroup a3 = this.d.a(viewGroup, a2);
        int abs = Math.abs(this.d.b - i);
        final int i2 = i;
        final ViewGroup viewGroup2 = a3;
        final ViewGroup viewGroup3 = viewGroup;
        final am amVar = a2;
        AnonymousClass2 r1 = new Runnable() {
            public final void run() {
                if (!ap.this.b) {
                    ap.this.f.remove(i2);
                    ap.this.d.b(viewGroup2, amVar);
                }
            }
        };
        this.f.put(i, r1);
        e.postDelayed(r1, (long) (abs * 50));
        a3.setLayoutParams(NativeViewFactory.a((ak) a2, viewGroup));
        a3.setTag(Integer.valueOf(i));
        viewGroup.addView(a3);
        return a3;
    }

    public final void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull final Object obj) {
        viewGroup.removeView((View) obj);
        Runnable runnable = (Runnable) this.f.get(i);
        if (runnable != null) {
            e.removeCallbacks(runnable);
            NativeViewFactory.class.getSimpleName();
        }
        e.post(new Runnable() {
            public final void run() {
                View view = (View) obj;
                ap.this.d.d.a(view);
            }
        });
    }

    public final void destroy() {
        this.b = true;
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            e.removeCallbacks((Runnable) this.f.get(this.f.keyAt(i)));
        }
        this.f.clear();
    }
}
