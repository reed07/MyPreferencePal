package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.ads.interactivemedia.v3.impl.data.a;
import com.google.ads.interactivemedia.v3.impl.data.v;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
public class adt {
    /* access modifiers changed from: private */
    public final aeb a;
    /* access modifiers changed from: private */
    public String b;
    private View c;
    private aca d;
    /* access modifiers changed from: private */
    public abz e;
    /* access modifiers changed from: private */
    public Activity f;
    private boolean g;

    public void a(String str, String str2) {
        this.a.b(new ado(adq.activityMonitor, adr.viewability, this.b, a(str, str2, "", "")));
    }

    public void a(String str, String str2, String str3) {
        this.a.b(new ado(adq.activityMonitor, adr.viewability, this.b, a(str, str2, str3, "")));
    }

    private static int a(int i, float f2) {
        return (int) Math.ceil((double) (((float) i) / f2));
    }

    private static v a(v vVar, float f2) {
        return v.builder().left(a(vVar.left(), f2)).top(a(vVar.top(), f2)).height(a(vVar.height(), f2)).width(a(vVar.width(), f2)).build();
    }

    private DisplayMetrics i() {
        return this.c.getContext().getResources().getDisplayMetrics();
    }

    public adt(String str, aeb aeb, View view) {
        this(str, aeb, view, new aca(0));
    }

    protected adt(String str, aeb aeb, View view, aca aca) {
        this.b = str;
        this.a = aeb;
        this.c = view;
        this.d = aca;
        this.f = null;
        this.e = null;
        this.g = false;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.g = z;
    }

    /* access modifiers changed from: private */
    public Application j() {
        Context applicationContext = this.c.getContext().getApplicationContext();
        if (applicationContext instanceof Application) {
            return (Application) applicationContext;
        }
        return null;
    }

    public void a() {
        this.a.a(this, this.b);
    }

    public void b() {
        this.a.a(this.b);
    }

    @TargetApi(14)
    public void c() {
        if (VERSION.SDK_INT >= 14 && this.g) {
            Application j = j();
            if (j != null) {
                this.e = new abz(this);
                j.registerActivityLifecycleCallbacks(this.e);
            }
        }
    }

    @TargetApi(14)
    public void d() {
        if (VERSION.SDK_INT >= 14) {
            Application j = j();
            if (j != null) {
                abz abz = this.e;
                if (abz != null) {
                    j.unregisterActivityLifecycleCallbacks(abz);
                }
            }
        }
    }

    public double e() {
        AudioManager audioManager = (AudioManager) this.c.getContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            return 0.0d;
        }
        return ((double) audioManager.getStreamVolume(3)) / ((double) audioManager.getStreamMaxVolume(3));
    }

    public boolean f() {
        return !this.c.getGlobalVisibleRect(new Rect()) || !this.c.isShown();
    }

    public v g() {
        return a(v.builder().locationOnScreenOfView(this.c).build(), i().density);
    }

    public v h() {
        Rect rect = new Rect();
        boolean globalVisibleRect = this.c.getGlobalVisibleRect(rect);
        boolean z = this.c.getWindowToken() != null;
        if (!globalVisibleRect || !z || !this.c.isShown()) {
            rect.set(0, 0, 0, 0);
        }
        return a(v.builder().left(rect.left).top(rect.top).height(rect.height()).width(rect.width()).build(), i().density);
    }

    public a a(String str, String str2, String str3, String str4) {
        v g2 = g();
        v h = h();
        boolean isAttachedToWindow = ViewCompat.isAttachedToWindow(this.c);
        return a.builder().queryId(str).eventId(str2).vastEvent(str3).appState(str4).nativeTime(this.d.a()).nativeVolume(e()).nativeViewAttached(isAttachedToWindow).nativeViewHidden(f()).nativeViewBounds(g2).nativeViewVisibleBounds(h).build();
    }
}
