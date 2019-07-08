package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: IMASDK */
public class act implements BaseDisplayContainer {
    private static int f;
    private ViewGroup a;
    private Collection<CompanionAdSlot> b = Collections.emptyList();
    private Map<String, CompanionAdSlot> c = null;
    private final Set<View> d = new HashSet();
    private acu e = null;

    public ViewGroup getAdContainer() {
        return this.a;
    }

    public void setAdContainer(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public Collection<CompanionAdSlot> getCompanionSlots() {
        return this.b;
    }

    public void setCompanionSlots(Collection<CompanionAdSlot> collection) {
        this.b = collection;
    }

    public final Map<String, CompanionAdSlot> a() {
        if (this.c == null) {
            agj agj = new agj();
            for (CompanionAdSlot companionAdSlot : this.b) {
                if (companionAdSlot != null) {
                    int i = f;
                    f = i + 1;
                    StringBuilder sb = new StringBuilder(20);
                    sb.append("compSlot_");
                    sb.append(i);
                    agj.b(sb.toString(), companionAdSlot);
                }
            }
            this.c = agj.a();
        }
        return this.c;
    }

    public void registerVideoControlsOverlay(View view) {
        if (view != null && !this.d.contains(view)) {
            this.d.add(view);
            acu acu = this.e;
            if (acu != null) {
                acu.a(view);
            }
        }
    }

    public void unregisterAllVideoControlsOverlays() {
        this.d.clear();
        acu acu = this.e;
        if (acu != null) {
            acu.a();
        }
    }

    public void destroy() {
        ViewGroup viewGroup = this.a;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.a = null;
        }
    }

    public final Set<View> b() {
        return new HashSet(this.d);
    }

    public final void a(acu acu) {
        this.e = acu;
    }
}
