package com.facebook.ads.internal.a;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.s.c;

public abstract class b {
    protected final Context a;
    protected final c b;
    protected final String c;

    public b(Context context, c cVar, String str) {
        this.a = context;
        this.b = cVar;
        this.c = str;
    }

    public abstract void a();

    @Nullable
    public a b() {
        return null;
    }
}
