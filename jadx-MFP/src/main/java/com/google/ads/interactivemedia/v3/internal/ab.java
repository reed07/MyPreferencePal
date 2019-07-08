package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: IMASDK */
public final class ab {
    private final HashMap<View, String> a = new HashMap<>();
    private final HashMap<View, ArrayList<String>> b = new HashMap<>();
    private final HashSet<View> c = new HashSet<>();
    private final HashSet<String> d = new HashSet<>();
    private final HashSet<String> e = new HashSet<>();
    private boolean f;

    public final HashSet<String> a() {
        return this.d;
    }

    public final HashSet<String> b() {
        return this.e;
    }

    public final void c() {
        j a2 = j.a();
        if (a2 != null) {
            for (e eVar : a2.c()) {
                View g = eVar.g();
                if (eVar.h()) {
                    if (g != null) {
                        boolean z = false;
                        if (g.hasWindowFocus()) {
                            HashSet hashSet = new HashSet();
                            View view = g;
                            while (true) {
                                if (view != null) {
                                    if (!ho.d(view)) {
                                        break;
                                    }
                                    hashSet.add(view);
                                    ViewParent parent = view.getParent();
                                    view = parent instanceof View ? (View) parent : null;
                                } else {
                                    this.c.addAll(hashSet);
                                    z = true;
                                    break;
                                }
                            }
                        }
                        if (z) {
                            this.d.add(eVar.f());
                            this.a.put(g, eVar.f());
                            for (ap apVar : eVar.d()) {
                                View view2 = (View) apVar.get();
                                if (view2 != null) {
                                    ArrayList arrayList = (ArrayList) this.b.get(view2);
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                        this.b.put(view2, arrayList);
                                    }
                                    arrayList.add(eVar.f());
                                }
                            }
                        }
                    }
                    this.e.add(eVar.f());
                }
            }
        }
    }

    public final void d() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f = false;
    }

    public final void e() {
        this.f = true;
    }

    public final String a(View view) {
        if (this.a.size() == 0) {
            return null;
        }
        String str = (String) this.a.get(view);
        if (str != null) {
            this.a.remove(view);
        }
        return str;
    }

    public final ArrayList<String> b(View view) {
        if (this.b.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = (ArrayList) this.b.get(view);
        if (arrayList != null) {
            this.b.remove(view);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public final ah c(View view) {
        if (this.c.contains(view)) {
            return ah.PARENT_VIEW;
        }
        return this.f ? ah.OBSTRUCTION_VIEW : ah.UNDERLYING_VIEW;
    }
}
