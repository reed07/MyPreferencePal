package com.inmobi.ads;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.c.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* compiled from: ImpressionTracker */
class y {
    private static final String b = "y";
    /* access modifiers changed from: 0000 */
    @NonNull
    public final Map<View, b> a;
    @NonNull
    private final ce c;
    /* access modifiers changed from: private */
    @NonNull
    public final Map<View, b> d;
    @NonNull
    private final Handler e;
    @NonNull
    private final c f;
    private final long g;
    @Nullable
    private c h;
    /* access modifiers changed from: private */
    @NonNull
    public a i;

    /* compiled from: ImpressionTracker */
    interface a {
        void a(View view, Object obj);
    }

    /* compiled from: ImpressionTracker */
    private static class b {
        Object a;
        int b;
        int c;
        long d = Long.MAX_VALUE;

        b(Object obj, int i, int i2) {
            this.a = obj;
            this.b = i;
            this.c = i2;
        }
    }

    /* compiled from: ImpressionTracker */
    static class c implements Runnable {
        @NonNull
        private final ArrayList<View> a = new ArrayList<>();
        private WeakReference<y> b;

        c(y yVar) {
            this.b = new WeakReference<>(yVar);
        }

        public final void run() {
            y yVar = (y) this.b.get();
            if (yVar != null) {
                for (Entry entry : yVar.d.entrySet()) {
                    View view = (View) entry.getKey();
                    b bVar = (b) entry.getValue();
                    if (y.a(bVar.d, bVar.c) && this.b.get() != null) {
                        yVar.i.a(view, bVar.a);
                        this.a.add(view);
                    }
                }
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    yVar.a((View) it.next());
                }
                this.a.clear();
                if (!yVar.d.isEmpty()) {
                    yVar.d();
                }
            }
        }
    }

    y(l lVar, @NonNull ce ceVar, @NonNull a aVar) {
        this(new WeakHashMap(), new WeakHashMap(), ceVar, new Handler(), lVar, aVar);
    }

    private y(@NonNull Map<View, b> map, @NonNull Map<View, b> map2, @NonNull ce ceVar, @NonNull Handler handler, @NonNull l lVar, @NonNull a aVar) {
        this.a = map;
        this.d = map2;
        this.c = ceVar;
        this.g = (long) lVar.d;
        this.h = new c() {
            public final void a(@NonNull List<View> list, @NonNull List<View> list2) {
                for (View view : list) {
                    b bVar = (b) y.this.a.get(view);
                    if (bVar == null) {
                        y.this.a(view);
                    } else {
                        b bVar2 = (b) y.this.d.get(view);
                        if (bVar2 == null || !bVar.a.equals(bVar2.a)) {
                            bVar.d = SystemClock.uptimeMillis();
                            y.this.d.put(view, bVar);
                        }
                    }
                }
                for (View remove : list2) {
                    y.this.d.remove(remove);
                }
                y.this.d();
            }
        };
        this.c.c = this.h;
        this.e = handler;
        this.f = new c(this);
        this.i = aVar;
    }

    /* access modifiers changed from: 0000 */
    public final void a(View view, @NonNull Object obj, int i2, int i3) {
        b bVar = (b) this.a.get(view);
        if (bVar == null || !bVar.a.equals(obj)) {
            a(view);
            b bVar2 = new b(obj, i2, i3);
            this.a.put(view, bVar2);
            this.c.a(view, obj, bVar2.b);
        }
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        this.a.remove(view);
        this.d.remove(view);
        this.c.a(view);
    }

    /* access modifiers changed from: 0000 */
    public final View a(@NonNull Object obj) {
        View view;
        Iterator it = this.a.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                view = null;
                break;
            }
            Entry entry = (Entry) it.next();
            if (((b) entry.getValue()).a.equals(obj)) {
                view = (View) entry.getKey();
                break;
            }
        }
        if (view != null) {
            a(view);
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        this.c.f();
        this.e.removeCallbacksAndMessages(null);
        this.d.clear();
    }

    /* access modifiers changed from: 0000 */
    public final void b() {
        for (Entry entry : this.a.entrySet()) {
            this.c.a((View) entry.getKey(), ((b) entry.getValue()).a, ((b) entry.getValue()).b);
        }
        d();
        this.c.d();
    }

    /* access modifiers changed from: private */
    public void d() {
        if (!this.e.hasMessages(0)) {
            this.e.postDelayed(this.f, this.g);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        this.a.clear();
        this.d.clear();
        this.c.f();
        this.e.removeMessages(0);
        this.c.e();
        this.h = null;
    }

    static /* synthetic */ boolean a(long j, int i2) {
        return SystemClock.uptimeMillis() - j >= ((long) i2);
    }
}
