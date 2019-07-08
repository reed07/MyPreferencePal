package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.t.c;
import com.facebook.ads.internal.t.e;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;

public final class DefaultMediaViewVideoRenderer extends MediaViewVideoRenderer {
    @Nullable
    private c d;

    private static class a implements com.facebook.ads.internal.t.c.a {
        private WeakReference<e> a;

        a(e eVar) {
            this.a = new WeakReference<>(eVar);
        }

        public void a(boolean z) {
            if (this.a.get() != null) {
                ((e) this.a.get()).a(z, false);
            }
        }
    }

    public DefaultMediaViewVideoRenderer(Context context) {
        super(context);
        this.d = new c(context, this);
        setVolume(BitmapDescriptorFactory.HUE_RED);
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new c(context, this);
        setVolume(BitmapDescriptorFactory.HUE_RED);
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new c(context, this);
        setVolume(BitmapDescriptorFactory.HUE_RED);
    }

    @TargetApi(21)
    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d = new c(context, this);
        setVolume(BitmapDescriptorFactory.HUE_RED);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        c cVar = this.d;
        if (cVar != null) {
            cVar.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        c cVar = this.d;
        if (cVar != null) {
            cVar.c();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        c cVar = this.d;
        if (cVar != null) {
            cVar.d();
        }
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        c cVar = this.d;
        if (cVar != null) {
            cVar.b();
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        c cVar = this.d;
        if (cVar != null) {
            cVar.e();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        c cVar = this.d;
        if (cVar != null) {
            cVar.f();
        }
    }

    /* access modifiers changed from: protected */
    public void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(nativeAd.f(), (com.facebook.ads.internal.t.c.a) new a(nativeAd.f()));
        }
    }
}
