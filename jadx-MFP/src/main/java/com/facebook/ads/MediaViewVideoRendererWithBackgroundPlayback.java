package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.t.c;

public final class MediaViewVideoRendererWithBackgroundPlayback extends MediaViewVideoRenderer {
    private c d;

    public MediaViewVideoRendererWithBackgroundPlayback(Context context) {
        super(context);
        this.d = new c(context, this);
        setVolume(1.0f);
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new c(context, this);
        setVolume(1.0f);
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new c(context, this);
        setVolume(1.0f);
    }

    @TargetApi(21)
    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d = new c(context, this);
        setVolume(1.0f);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.d.a();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d.c();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.d.d();
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.d.b();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.d.e();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.d.f();
    }

    /* access modifiers changed from: protected */
    public void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        this.d.a(nativeAd.f());
    }

    public boolean shouldAllowBackgroundPlayback() {
        return true;
    }
}
