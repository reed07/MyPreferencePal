package com.google.ads.interactivemedia.v3.internal;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public final class acw implements CompanionAdSlot {
    private int a;
    private int b;
    private ViewGroup c;
    private String d;
    private final List<ClickListener> e = new ArrayList(1);

    public final boolean isFilled() {
        return this.c.findViewWithTag(this.d) != null;
    }

    public final int getWidth() {
        return this.a;
    }

    public final int getHeight() {
        return this.b;
    }

    public final void setSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final ViewGroup getContainer() {
        return this.c;
    }

    public final void setContainer(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public final void addClickListener(ClickListener clickListener) {
        this.e.add(clickListener);
    }

    public final void removeClickListener(ClickListener clickListener) {
        this.e.remove(clickListener);
    }

    public final List<ClickListener> a() {
        return this.e;
    }
}
