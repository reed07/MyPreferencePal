package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.m.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.t.f;
import java.util.ArrayList;
import java.util.List;

public class NativeAd extends NativeAdBase {

    public enum AdCreativeType {
        IMAGE,
        VIDEO,
        CAROUSEL,
        UNKNOWN
    }

    protected NativeAd(Context context, i iVar, d dVar) {
        super(context, iVar, dVar);
        a(e.NATIVE_UNKNOWN);
    }

    public NativeAd(Context context, String str) {
        super(context, str);
        a(e.NATIVE_UNKNOWN);
    }

    NativeAd(NativeAdBase nativeAdBase) {
        super(nativeAdBase);
    }

    NativeAd(com.facebook.ads.internal.t.e eVar) {
        super(eVar);
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return f().r();
    }

    /* access modifiers changed from: 0000 */
    public void a(Type type) {
        f().a(type.a());
    }

    /* access modifiers changed from: 0000 */
    public String b() {
        return f().s();
    }

    /* access modifiers changed from: 0000 */
    public VideoAutoplayBehavior c() {
        return VideoAutoplayBehavior.fromInternalAutoplayBehavior(f().t());
    }

    /* access modifiers changed from: 0000 */
    public List<NativeAd> d() {
        if (f().u() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.facebook.ads.internal.t.e nativeAd : f().u()) {
            arrayList.add(new NativeAd(nativeAd));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public Type e() {
        if (f().x() == null) {
            return null;
        }
        return Type.a(f().x());
    }

    public AdCreativeType getAdCreativeType() {
        return !TextUtils.isEmpty(f().r()) ? AdCreativeType.VIDEO : (f().u() == null || f().u().isEmpty()) ? (f().j() == null || TextUtils.isEmpty(f().j().a())) ? AdCreativeType.UNKNOWN : AdCreativeType.IMAGE : AdCreativeType.CAROUSEL;
    }

    public void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, (AdIconView) null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView) {
        registerViewForInteraction(view, mediaView, imageView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView, @Nullable List<View> list) {
        if (imageView != null) {
            com.facebook.ads.internal.t.e.a(f().i(), imageView);
        }
        registerViewForInteraction(view, mediaView, (MediaView) null, list);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable AdIconView adIconView) {
        registerViewForInteraction(view, mediaView, (MediaView) adIconView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable MediaView mediaView2, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.setNativeAd(this);
        }
        if (mediaView2 != null) {
            mediaView2.a((NativeAdBase) this, false);
        }
        if (list != null) {
            f().a(view, (f) mediaView, list);
        } else {
            f().a(view, (f) mediaView);
        }
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        registerViewForInteraction(view, mediaView, (MediaView) null, list);
    }
}
