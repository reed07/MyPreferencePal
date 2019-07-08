package com.facebook.ads.internal.view.e.a;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.view.View;
import com.facebook.ads.internal.view.component.a.a.b;
import com.facebook.ads.internal.view.component.a.a.b.c;
import com.facebook.ads.internal.view.component.a.a.b.d;
import com.facebook.ads.internal.view.component.a.a.b.e;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a extends OnScrollListener {
    private final LinearLayoutManager a;
    private final int b;
    private final SmoothScroller c;
    private final Set<Integer> d = new HashSet();
    private List<b> e;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.x.a f;
    private boolean g = true;
    @Nullable
    private com.facebook.ads.internal.view.e.a.c.a h;
    private boolean i = true;
    /* access modifiers changed from: private */
    public boolean j = true;
    /* access modifiers changed from: private */
    public boolean k;
    private int l = -1;
    /* access modifiers changed from: private */
    public float m = BitmapDescriptorFactory.HUE_RED;
    private final e n = new e() {
        public float a() {
            return a.this.m;
        }

        public void a(float f) {
            a.this.m = f;
        }
    };
    private final c o = new c() {
        public void a(int i) {
            a.this.a(i, true);
            if (a.this.g()) {
                a.c(a.this);
            } else {
                a.a(a.this, i);
            }
        }
    };
    private final d p = new d() {
        public void a(View view) {
            b bVar = (b) view;
            bVar.j();
            if (a.this.k) {
                a.this.j = true;
            }
            if (a.this.f.b() && ((Integer) bVar.getTag(-1593835536)).intValue() == 0) {
                a.this.f.a();
            }
        }

        public void b(View view) {
            if (a.this.k) {
                a.this.j = false;
            }
        }
    };

    a(com.facebook.ads.internal.view.d dVar, int i2, List<b> list, com.facebook.ads.internal.x.a aVar, @Nullable Bundle bundle) {
        this.a = dVar.getLayoutManager();
        this.b = i2;
        this.e = list;
        this.f = aVar;
        this.c = new LinearSmoothScroller(dVar.getContext());
        dVar.addOnScrollListener(this);
        if (bundle != null) {
            this.m = bundle.getFloat("VOLUME_LEVEL_PARAM", BitmapDescriptorFactory.HUE_RED);
            this.j = bundle.getBoolean("AUTO_PLAY_ENABLED_PARAM", true);
            this.g = bundle.getBoolean("IS_FIRST_VIDEO_PARAM", true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        if ((((int) (r2.getX() + ((float) r2.getWidth()))) <= ((int) (((float) r2.getWidth()) * 1.3f))) != false) goto L_0x004d;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.ads.internal.view.component.a.a.b a(int r9, int r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            if (r9 > r10) goto L_0x005c
            android.support.v7.widget.LinearLayoutManager r2 = r8.a
            android.view.View r2 = r2.findViewByPosition(r9)
            com.facebook.ads.internal.view.component.a.a.b r2 = (com.facebook.ads.internal.view.component.a.a.b) r2
            boolean r3 = r2.g()
            if (r3 == 0) goto L_0x0013
            return r0
        L_0x0013:
            boolean r3 = a(r2)
            r4 = 0
            if (r1 != 0) goto L_0x004e
            boolean r5 = r2.f()
            if (r5 == 0) goto L_0x004e
            if (r3 == 0) goto L_0x004e
            java.util.Set<java.lang.Integer> r5 = r8.d
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)
            boolean r5 = r5.contains(r6)
            if (r5 != 0) goto L_0x004e
            if (r11 == 0) goto L_0x004d
            int r5 = r2.getWidth()
            float r5 = (float) r5
            r6 = 1067869798(0x3fa66666, float:1.3)
            float r5 = r5 * r6
            int r5 = (int) r5
            float r6 = r2.getX()
            int r7 = r2.getWidth()
            float r7 = (float) r7
            float r6 = r6 + r7
            int r6 = (int) r6
            if (r6 > r5) goto L_0x004a
            r5 = 1
            goto L_0x004b
        L_0x004a:
            r5 = 0
        L_0x004b:
            if (r5 == 0) goto L_0x004e
        L_0x004d:
            r1 = r2
        L_0x004e:
            boolean r2 = r2.f()
            if (r2 == 0) goto L_0x0059
            if (r3 != 0) goto L_0x0059
            r8.a(r9, r4)
        L_0x0059:
            int r9 = r9 + 1
            goto L_0x0002
        L_0x005c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.e.a.a.a(int, int, boolean):com.facebook.ads.internal.view.component.a.a.b");
    }

    private void a(int i2) {
        this.c.setTargetPosition(i2);
        this.a.startSmoothScroll(this.c);
    }

    /* access modifiers changed from: private */
    public void a(int i2, boolean z) {
        if (z) {
            this.d.add(Integer.valueOf(i2));
        } else {
            this.d.remove(Integer.valueOf(i2));
        }
    }

    private void a(b bVar, boolean z) {
        if (g()) {
            bVar.setAlpha(z ? 1.0f : 0.5f);
        }
        if (!z && bVar.g()) {
            bVar.i();
        }
    }

    static /* synthetic */ void a(a aVar, int i2) {
        b a2 = aVar.a(i2 + 1, aVar.a.findLastVisibleItemPosition(), false);
        if (a2 != null) {
            a2.h();
            aVar.a(((Integer) a2.getTag(-1593835536)).intValue());
        }
    }

    private static boolean a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return ((float) rect.width()) / ((float) view.getWidth()) >= 0.15f;
    }

    private void b(int i2) {
        b bVar = (b) this.a.findViewByPosition(i2);
        if (!a((View) bVar)) {
            a(bVar, false);
        }
    }

    static /* synthetic */ void c(a aVar) {
        int findFirstCompletelyVisibleItemPosition = aVar.a.findFirstCompletelyVisibleItemPosition();
        if (findFirstCompletelyVisibleItemPosition != -1 && findFirstCompletelyVisibleItemPosition < aVar.e.size() - 1) {
            aVar.a(findFirstCompletelyVisibleItemPosition + 1);
        }
    }

    private void f() {
        if (this.j) {
            b a2 = a(this.a.findFirstVisibleItemPosition(), this.a.findLastVisibleItemPosition(), true);
            if (a2 != null) {
                a2.h();
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean g() {
        return this.b == 1;
    }

    public void a() {
        this.l = -1;
        int findFirstVisibleItemPosition = this.a.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.a.findLastVisibleItemPosition();
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition && findFirstVisibleItemPosition >= 0) {
            b bVar = (b) this.a.findViewByPosition(findFirstVisibleItemPosition);
            if (bVar == null || !bVar.g()) {
                findFirstVisibleItemPosition++;
            } else {
                this.l = findFirstVisibleItemPosition;
                bVar.i();
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Bundle bundle) {
        bundle.putFloat("VOLUME_LEVEL_PARAM", this.m);
        bundle.putBoolean("AUTO_PLAY_ENABLED_PARAM", this.j);
        bundle.putBoolean("IS_FIRST_VIDEO_PARAM", this.g);
    }

    /* access modifiers changed from: 0000 */
    public void a(com.facebook.ads.internal.view.e.a.c.a aVar) {
        this.h = aVar;
    }

    public void b() {
        b bVar = (b) this.a.findViewByPosition(this.l);
        if (this.l >= 0) {
            bVar.h();
        }
    }

    public e c() {
        return this.n;
    }

    public c d() {
        return this.o;
    }

    public d e() {
        return this.p;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        super.onScrollStateChanged(recyclerView, i2);
        if (i2 == 0) {
            this.k = true;
            f();
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
        boolean z;
        super.onScrolled(recyclerView, i2, i3);
        this.k = false;
        if (this.i) {
            this.k = true;
            f();
            this.i = false;
        }
        int findFirstVisibleItemPosition = this.a.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.a.findLastVisibleItemPosition();
        b(findFirstVisibleItemPosition);
        b(findLastVisibleItemPosition);
        for (int i4 = findFirstVisibleItemPosition; i4 <= findLastVisibleItemPosition; i4++) {
            b bVar = (b) this.a.findViewByPosition(i4);
            if (a((View) bVar)) {
                a(bVar, true);
            }
            if (!this.g || !bVar.f()) {
                z = false;
            } else {
                this.g = false;
                z = true;
            }
            if (z) {
                this.n.a(((b) this.e.get(((Integer) bVar.getTag(-1593835536)).intValue())).c().c().f() ? BitmapDescriptorFactory.HUE_RED : 1.0f);
            }
        }
        if (g() && this.h != null) {
            int findFirstCompletelyVisibleItemPosition = this.a.findFirstCompletelyVisibleItemPosition();
            if (findFirstCompletelyVisibleItemPosition == -1) {
                findFirstCompletelyVisibleItemPosition = i2 < 0 ? findFirstVisibleItemPosition : findLastVisibleItemPosition;
            }
            this.h.a(findFirstCompletelyVisibleItemPosition);
        }
    }
}
