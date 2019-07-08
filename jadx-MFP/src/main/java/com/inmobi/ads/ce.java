package com.inmobi.ads;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* compiled from: VisibilityTracker */
abstract class ce {
    private static final String d = "ce";
    boolean a;
    /* access modifiers changed from: 0000 */
    @NonNull
    public final Map<View, d> b;
    /* access modifiers changed from: 0000 */
    @Nullable
    public c c;
    @NonNull
    private final ArrayList<View> e;
    private long f;
    /* access modifiers changed from: private */
    @NonNull
    public final a g;
    @NonNull
    private final b h;
    @NonNull
    private final Handler i;
    /* access modifiers changed from: private */
    public boolean j;

    /* compiled from: VisibilityTracker */
    interface a {
        boolean a(@Nullable View view, @Nullable View view2, int i, Object obj);
    }

    /* compiled from: VisibilityTracker */
    static class b implements Runnable {
        @NonNull
        private final ArrayList<View> a = new ArrayList<>();
        @NonNull
        private final ArrayList<View> b = new ArrayList<>();
        private WeakReference<ce> c;

        b(ce ceVar) {
            this.c = new WeakReference<>(ceVar);
        }

        public final void run() {
            ce ceVar = (ce) this.c.get();
            if (ceVar != null) {
                ceVar.j = false;
                for (Entry entry : ceVar.b.entrySet()) {
                    View view = (View) entry.getKey();
                    int i = ((d) entry.getValue()).a;
                    if (ceVar.g.a(((d) entry.getValue()).c, view, i, ((d) entry.getValue()).d)) {
                        this.a.add(view);
                    } else {
                        this.b.add(view);
                    }
                }
            }
            if (ceVar != null) {
                c d = ceVar.c;
                if (d != null) {
                    d.a(this.a, this.b);
                }
            }
            this.a.clear();
            this.b.clear();
            if (ceVar != null) {
                ceVar.b();
            }
        }
    }

    /* compiled from: VisibilityTracker */
    interface c {
        void a(List<View> list, List<View> list2);
    }

    /* compiled from: VisibilityTracker */
    static class d {
        int a;
        long b;
        View c;
        Object d;

        d() {
        }
    }

    /* access modifiers changed from: protected */
    public abstract int a();

    /* access modifiers changed from: protected */
    public abstract void b();

    ce(a aVar) {
        this(new WeakHashMap(10), aVar, new Handler(Looper.getMainLooper()));
    }

    private ce(@NonNull Map<View, d> map, @NonNull a aVar, @NonNull Handler handler) {
        this.f = 0;
        this.a = true;
        this.b = map;
        this.g = aVar;
        this.i = handler;
        this.h = new b(this);
        this.e = new ArrayList<>(50);
    }

    public void c() {
        this.h.run();
        this.i.removeCallbacksAndMessages(null);
        this.j = false;
        this.a = true;
    }

    public void d() {
        this.a = false;
        g();
    }

    /* access modifiers changed from: protected */
    public final void a(@NonNull View view) {
        if (((d) this.b.remove(view)) != null) {
            this.f--;
            if (this.b.size() == 0) {
                c();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void f() {
        this.b.clear();
        this.i.removeMessages(0);
        this.j = false;
    }

    /* access modifiers changed from: 0000 */
    public final View a(@Nullable Object obj) {
        View view = null;
        if (obj == null) {
            return null;
        }
        Iterator it = this.b.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (((d) entry.getValue()).d.equals(obj)) {
                view = (View) entry.getKey();
                break;
            }
        }
        if (view != null) {
            a(view);
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public void e() {
        f();
        this.c = null;
        this.a = true;
    }

    /* access modifiers changed from: 0000 */
    public final void g() {
        if (!this.j && !this.a) {
            this.j = true;
            this.i.postDelayed(this.h, (long) a());
        }
    }

    /* access modifiers changed from: protected */
    public final void a(@NonNull View view, @Nullable Object obj, int i2) {
        d dVar = (d) this.b.get(view);
        if (dVar == null) {
            dVar = new d();
            this.b.put(view, dVar);
            this.f++;
        }
        dVar.a = i2;
        long j2 = this.f;
        dVar.b = j2;
        dVar.c = view;
        dVar.d = obj;
        if (j2 % 50 == 0) {
            long j3 = j2 - 50;
            for (Entry entry : this.b.entrySet()) {
                if (((d) entry.getValue()).b < j3) {
                    this.e.add(entry.getKey());
                }
            }
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                a((View) it.next());
            }
            this.e.clear();
        }
        if (1 == this.b.size()) {
            d();
        }
    }
}
