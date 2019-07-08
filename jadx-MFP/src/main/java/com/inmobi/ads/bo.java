package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;

@SuppressLint({"ViewConstructor"})
/* compiled from: ScrollableDeckFreeContainer */
class bo extends NativeScrollableContainer {
    private RecyclerView a;
    private boolean b = false;

    public bo(Context context) {
        super(context, 1);
    }

    public final void a(@NonNull am amVar, ax axVar, int i, int i2, a aVar) {
        LayoutParams layoutParams = (LayoutParams) NativeViewFactory.a(amVar.a(0), (ViewGroup) this);
        if (VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(20);
            layoutParams.setMarginEnd(20);
        } else {
            layoutParams.setMargins(20, 0, 20, 0);
        }
        layoutParams.gravity = i2;
        this.a = new RecyclerView(getContext());
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        this.a.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        addView(this.a);
        this.a.setAdapter((NativeRecyclerViewAdapter) axVar);
    }
}
