package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.adapters.p.b;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.w.b.x;

public class d {
    private static final int a = ((int) (x.b * 200.0f));
    private static final int b = ((int) (x.b * 200.0f));
    private static final int c = ((int) (x.b * 50.0f));

    public static b a(@Nullable NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout == null) {
            return b.NO_NATIVE_AD_LAYOUT;
        }
        int width = nativeAdLayout.getWidth();
        int height = nativeAdLayout.getHeight();
        int i = a;
        return (width < i || height < i) && (width < b || height < c) ? b.TOO_SMALL : b.AVAILABLE;
    }

    @Nullable
    public static c a(Context context, c cVar, String str, @Nullable NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout == null) {
            return null;
        }
        int width = nativeAdLayout.getWidth();
        int height = nativeAdLayout.getHeight();
        int i = a;
        if (width >= i && height >= i) {
            k kVar = new k(context, cVar, str, width, height);
            return kVar;
        } else if (width < b || height < c) {
            return null;
        } else {
            h hVar = new h(context, cVar, str, width, height);
            return hVar;
        }
    }

    public static c a(Context context, c cVar, String str, a aVar, C0012a aVar2) {
        g gVar = new g(context, cVar, str, aVar, aVar2);
        return gVar;
    }
}
