package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.NativeBannerAdView.Type;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.t.f;
import java.util.List;

public class NativeBannerAd extends NativeAdBase {
    public NativeBannerAd(Context context, String str) {
        super(context, str);
        a(e.NATIVE_BANNER);
    }

    /* access modifiers changed from: 0000 */
    public Type a() {
        if (f().x() == null) {
            return null;
        }
        return Type.a(f().x());
    }

    /* access modifiers changed from: 0000 */
    public void a(Type type) {
        f().a(type.a());
    }

    public void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.a((NativeAdBase) this, true);
        }
        if (list != null) {
            f().a(view, (f) mediaView, list);
        } else {
            f().a(view, (f) mediaView);
        }
    }
}
