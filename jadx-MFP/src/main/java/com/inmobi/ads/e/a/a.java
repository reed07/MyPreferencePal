package com.inmobi.ads.e.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.bz;
import com.inmobi.ads.c;
import com.inmobi.ads.ca;

/* compiled from: MMATrackedNativeV2DisplayAd */
public final class a extends bz {
    @NonNull
    private ca d;

    public a(@NonNull ca caVar) {
        this.d = caVar;
    }

    @Nullable
    public final View a() {
        return this.d.a();
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.d.a(view, viewGroup, z);
    }

    @Nullable
    public final View b() {
        return this.d.b();
    }

    public final a f() {
        return this.d.f();
    }

    @NonNull
    public final c c() {
        return this.d.c();
    }

    public final void a(@Nullable View... viewArr) {
        this.d.a(viewArr);
    }

    public final void d() {
        this.d.d();
    }

    public final void a(int i) {
        this.d.a(i);
    }

    public final void a(Context context, int i) {
        this.d.a(context, i);
    }
}
