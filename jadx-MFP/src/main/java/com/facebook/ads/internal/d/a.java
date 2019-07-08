package com.facebook.ads.internal.d;

import android.os.Bundle;
import android.view.View;
import com.facebook.ads.internal.w.b.r;
import java.util.ArrayList;
import java.util.List;

public final class a implements r<Bundle> {
    private final View a;
    private final List<d> b;
    private c c;

    public a(View view, List<b> list) {
        this.a = view;
        this.b = new ArrayList(list.size());
        for (b dVar : list) {
            this.b.add(new d(dVar));
        }
        this.c = new c();
    }

    public a(View view, List<b> list, Bundle bundle) {
        this.a = view;
        this.b = new ArrayList(list.size());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("TESTS");
        for (int i = 0; i < list.size(); i++) {
            this.b.add(new d((b) list.get(i), (Bundle) parcelableArrayList.get(i)));
        }
        this.c = (c) bundle.getSerializable("STATISTICS");
    }

    public void a() {
        this.c.a();
    }

    public void a(double d, double d2) {
        if (d2 >= 0.0d) {
            this.c.b(d, d2);
        }
        double c2 = (double) com.facebook.ads.internal.x.a.a(this.a, 0).c();
        this.c.a(d, c2);
        for (d a2 : this.b) {
            a2.a(d, c2);
        }
    }

    public void b() {
        this.c.b();
        for (d a2 : this.b) {
            a2.a();
        }
    }

    public c c() {
        return this.c;
    }

    public Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("STATISTICS", this.c);
        ArrayList arrayList = new ArrayList(this.b.size());
        for (d g : this.b) {
            arrayList.add(g.g());
        }
        bundle.putParcelableArrayList("TESTS", arrayList);
        return bundle;
    }
}
