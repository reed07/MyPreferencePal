package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;

/* compiled from: ViewableNativeV2DisplayAd */
final class cc extends ca {
    @NonNull
    private final ah d;
    private boolean e = false;
    @Nullable
    private RenderView f;

    public final void a(int i) {
    }

    public final void a(Context context, int i) {
    }

    public final void a(@Nullable View... viewArr) {
    }

    public final void d() {
    }

    cc(@NonNull ah ahVar, @Nullable RenderView renderView) {
        super(ahVar);
        this.d = ahVar;
        this.f = renderView;
    }

    @NonNull
    public final c c() {
        return this.d.c;
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        if (this.e) {
            return null;
        }
        Context j = this.d.j();
        if (j == null) {
            return null;
        }
        c cVar = this.d.c;
        ah ahVar = this.d;
        this.b = new at(j, cVar, ahVar, ahVar.h());
        Logger.a(InternalLogLevel.DEBUG, "InMobi", "Ad markup loaded into the container will be inflated into a View.");
        View a = this.b.a(view, viewGroup, z, this.f);
        a(a);
        this.d.t();
        return a;
    }

    public final void e() {
        if (!this.e) {
            this.e = true;
            if (this.b != null) {
                this.b.a();
            }
            RenderView renderView = this.f;
            if (renderView != null) {
                renderView.destroy();
                this.f = null;
            }
            super.e();
        }
    }
}
