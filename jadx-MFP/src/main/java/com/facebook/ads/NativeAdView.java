package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.w.b.l;
import com.facebook.ads.internal.w.b.x;

public class NativeAdView {

    public enum Type {
        HEIGHT_300(k.HEIGHT_300),
        HEIGHT_400(k.HEIGHT_400);
        
        private final k a;

        private Type(k kVar) {
            this.a = kVar;
        }

        static Type a(k kVar) {
            if (kVar == k.HEIGHT_300) {
                return HEIGHT_300;
            }
            if (kVar == k.HEIGHT_400) {
                return HEIGHT_400;
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public k a() {
            return this.a;
        }

        public int getHeight() {
            return this.a.b();
        }

        public int getValue() {
            return this.a.b();
        }

        public int getWidth() {
            return this.a.a();
        }
    }

    public static View render(Context context, NativeAd nativeAd, Type type) {
        return render(context, nativeAd, type, null);
    }

    public static View render(Context context, NativeAd nativeAd, Type type, @Nullable NativeAdViewAttributes nativeAdViewAttributes) {
        l.a(context, "context must be not null");
        l.a(nativeAd, "nativeAd must be not null");
        l.a(type, "type must be not null");
        if (nativeAd.isNativeConfigEnabled()) {
            nativeAdViewAttributes = nativeAd.getAdViewAttributes();
        }
        if (nativeAdViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeAd.a(type);
        MediumRectTemplateLayout mediumRectTemplateLayout = new MediumRectTemplateLayout(context, nativeAd, nativeAdViewAttributes.a());
        mediumRectTemplateLayout.setLayoutParams(new LayoutParams(-1, (int) (x.b * ((float) type.getHeight()))));
        return mediumRectTemplateLayout;
    }
}
