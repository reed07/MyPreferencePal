package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.w.b.l;
import com.facebook.ads.internal.w.b.x;

public class NativeBannerAdView {

    public enum Type {
        HEIGHT_50(k.HEIGHT_50),
        HEIGHT_100(k.HEIGHT_100),
        HEIGHT_120(k.HEIGHT_120);
        
        private final k a;

        private Type(k kVar) {
            this.a = kVar;
        }

        static Type a(k kVar) {
            if (kVar == k.HEIGHT_50) {
                return HEIGHT_50;
            }
            if (kVar == k.HEIGHT_100) {
                return HEIGHT_100;
            }
            if (kVar == k.HEIGHT_120) {
                return HEIGHT_120;
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

    public static View render(Context context, NativeBannerAd nativeBannerAd, Type type) {
        return render(context, nativeBannerAd, type, null);
    }

    public static View render(Context context, NativeBannerAd nativeBannerAd, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        l.a(context, "context must be not null");
        l.a(nativeBannerAd, "nativeBannerAd must be not null");
        l.a(type, "type must be not null");
        if (nativeBannerAd.isNativeConfigEnabled()) {
            nativeAdViewAttributes = nativeBannerAd.getAdViewAttributes();
        }
        if (nativeAdViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeBannerAd.a(type);
        BannerTemplateLayout bannerTemplateLayout = new BannerTemplateLayout(context, nativeBannerAd, nativeAdViewAttributes.a());
        bannerTemplateLayout.setLayoutParams(new LayoutParams(-1, (int) (x.b * ((float) type.getHeight()))));
        return bannerTemplateLayout;
    }
}
