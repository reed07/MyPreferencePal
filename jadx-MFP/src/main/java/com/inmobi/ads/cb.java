package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.rendering.RenderView;

/* compiled from: ViewableHtmlAd */
public final class cb extends ca {
    @NonNull
    private final RenderView d;

    public final void a(int i) {
    }

    public final void a(Context context, int i) {
    }

    public final void a(@Nullable View... viewArr) {
    }

    public final void d() {
    }

    public cb(@NonNull RenderView renderView) {
        super(renderView);
        this.d = renderView;
    }

    public final View a() {
        a((View) this.d);
        return this.d;
    }

    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return a();
    }

    @NonNull
    public final c c() {
        return this.d.getAdConfig();
    }
}
