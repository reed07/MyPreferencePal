package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Animatable2.AnimationCallback;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import com.inmobi.ads.t.a;
import java.io.File;
import java.io.IOException;

/* compiled from: AnimatedDrawable */
public final class l implements t {
    AnimatedImageDrawable a;
    private a b;

    public final void a(boolean z) {
    }

    public final void e() {
    }

    @SuppressLint({"NewApi"})
    public l(String str) throws IOException {
        this.a = (AnimatedImageDrawable) ImageDecoder.decodeDrawable(ImageDecoder.createSource(new File(str)));
    }

    @SuppressLint({"NewApi"})
    public final void a() {
        this.a.registerAnimationCallback(new AnimationCallback() {
            public final void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
            }

            public final void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                l.this.a.start();
            }
        });
        this.a.start();
    }

    public final int b() {
        return this.a.getIntrinsicWidth();
    }

    public final int c() {
        return this.a.getIntrinsicHeight();
    }

    public final void a(Canvas canvas, float f, float f2) {
        this.a.draw(canvas);
    }

    public final boolean d() {
        return this.a.isRunning();
    }

    public final void a(a aVar) {
        this.b = aVar;
    }
}
