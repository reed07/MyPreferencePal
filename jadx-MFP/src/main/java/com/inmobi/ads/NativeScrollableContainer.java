package com.inmobi.ads;

import android.content.Context;
import android.widget.FrameLayout;

public abstract class NativeScrollableContainer extends FrameLayout {
    private final int a;

    interface a {
        int a(int i);
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(am amVar, ax axVar, int i, int i2, a aVar);

    public NativeScrollableContainer(Context context) {
        super(context);
        this.a = 0;
    }

    public NativeScrollableContainer(Context context, int i) {
        super(context);
        this.a = i;
    }

    public final int getType() {
        return this.a;
    }
}
