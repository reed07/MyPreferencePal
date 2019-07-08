package com.facebook.ads.internal.view.i;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.s;
import com.facebook.ads.internal.view.i.b.t;
import com.facebook.ads.internal.view.i.b.u;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.view.i.b.y;
import com.facebook.ads.internal.view.i.b.z;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.d.c;
import com.facebook.ads.internal.view.i.d.e;
import com.facebook.ads.internal.w.b.x;
import java.util.ArrayList;
import java.util.List;

public class a extends RelativeLayout implements com.facebook.ads.internal.view.i.c.a, e {
    /* access modifiers changed from: private */
    public static final m b = new m();
    /* access modifiers changed from: private */
    public static final com.facebook.ads.internal.view.i.b.e c = new com.facebook.ads.internal.view.i.b.e();
    /* access modifiers changed from: private */
    public static final s d = new s();
    /* access modifiers changed from: private */
    public static final t e = new t();
    /* access modifiers changed from: private */
    public static final k f = new k();
    private static final w g = new w();
    private static final z h = new z();
    private static final y i = new y();
    protected final c a;
    private d j;
    private final List<b> k = new ArrayList();
    /* access modifiers changed from: private */
    public final Handler l = new Handler();
    private final Handler m = new Handler();
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.o.e<f, d> n = new com.facebook.ads.internal.o.e<>();
    /* access modifiers changed from: private */
    public boolean o;
    private boolean p;
    private boolean q = false;
    /* access modifiers changed from: private */
    public int r = 200;
    private final OnTouchListener s = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            a.this.n.a(new u(view, motionEvent));
            return false;
        }
    };

    public a(Context context) {
        super(context);
        this.a = com.facebook.ads.internal.r.a.a(context) ? new com.facebook.ads.internal.view.i.d.a(context) : new com.facebook.ads.internal.view.i.d.b(context);
        a();
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = com.facebook.ads.internal.r.a.a(context) ? new com.facebook.ads.internal.view.i.d.a(context, attributeSet) : new com.facebook.ads.internal.view.i.d.b(context, attributeSet);
        a();
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = com.facebook.ads.internal.r.a.a(context) ? new com.facebook.ads.internal.view.i.d.a(context, attributeSet, i2) : new com.facebook.ads.internal.view.i.d.b(context, attributeSet, i2);
        a();
    }

    @TargetApi(21)
    public a(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.a = com.facebook.ads.internal.r.a.a(context) ? new com.facebook.ads.internal.view.i.d.a(context, attributeSet, i2, i3) : new com.facebook.ads.internal.view.i.d.b(context, attributeSet, i2, i3);
        a();
    }

    private void a() {
        if (h()) {
            c cVar = this.a;
            if (cVar instanceof com.facebook.ads.internal.view.i.d.a) {
                ((com.facebook.ads.internal.view.i.d.a) cVar).setTestMode(AdInternalSettings.isTestMode(getContext()));
            }
        }
        this.a.setRequestedVolume(1.0f);
        this.a.setVideoStateChangeListener(this);
        this.j = new d(getContext(), this.a);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.j, layoutParams);
        setOnTouchListener(this.s);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.l.postDelayed(new Runnable() {
            public void run() {
                if (!a.this.o) {
                    a.this.n.a(new o(a.this.getCurrentPositionInMillis()));
                    a.this.l.postDelayed(this, (long) a.this.r);
                }
            }
        }, (long) this.r);
    }

    public void a(int i2) {
        this.l.removeCallbacksAndMessages(null);
        this.a.a(i2);
    }

    public void a(final int i2, final int i3) {
        this.m.post(new Runnable() {
            public void run() {
                a.this.n.a(new q(i2, i3));
            }
        });
        b();
    }

    public void a(com.facebook.ads.internal.view.i.a.a aVar) {
        if (this.o && this.a.getState() == com.facebook.ads.internal.view.i.d.d.PLAYBACK_COMPLETED) {
            this.o = false;
        }
        this.a.a(aVar);
    }

    public void a(b bVar) {
        this.k.add(bVar);
    }

    public void a(final com.facebook.ads.internal.view.i.d.d dVar) {
        final int currentPositionInMillis = getCurrentPositionInMillis();
        final int duration = getDuration();
        this.m.post(new Runnable() {
            public void run() {
                com.facebook.ads.internal.o.e b2;
                d r;
                com.facebook.ads.internal.o.e b3;
                d cVar;
                if (dVar == com.facebook.ads.internal.view.i.d.d.PREPARED) {
                    b3 = a.this.n;
                    cVar = a.b;
                } else if (dVar == com.facebook.ads.internal.view.i.d.d.ERROR) {
                    a.this.o = true;
                    b3 = a.this.n;
                    cVar = a.c;
                } else if (dVar == com.facebook.ads.internal.view.i.d.d.PLAYBACK_COMPLETED) {
                    a.this.o = true;
                    a.this.l.removeCallbacksAndMessages(null);
                    b3 = a.this.n;
                    cVar = new com.facebook.ads.internal.view.i.b.c(currentPositionInMillis, duration);
                } else if (dVar == com.facebook.ads.internal.view.i.d.d.STARTED) {
                    a.this.n.a(a.f);
                    a.this.l.removeCallbacksAndMessages(null);
                    a.this.b();
                    return;
                } else {
                    if (dVar == com.facebook.ads.internal.view.i.d.d.PAUSED) {
                        b2 = a.this.n;
                        r = new i(currentPositionInMillis);
                    } else if (dVar == com.facebook.ads.internal.view.i.d.d.IDLE) {
                        b2 = a.this.n;
                        r = a.e;
                    } else {
                        return;
                    }
                    b2.a(r);
                    a.this.l.removeCallbacksAndMessages(null);
                    return;
                }
                b3.a(cVar);
            }
        });
    }

    public void a(boolean z) {
        if (!m()) {
            this.a.a(z);
            this.q = z;
        }
    }

    public void c() {
        for (b bVar : this.k) {
            if (bVar instanceof com.facebook.ads.internal.view.i.a.c) {
                com.facebook.ads.internal.view.i.a.c cVar = (com.facebook.ads.internal.view.i.a.c) bVar;
                if (cVar.getParent() == null) {
                    if (cVar instanceof g) {
                        this.j.a(cVar);
                    } else {
                        addView(cVar);
                    }
                }
            }
            bVar.a(this);
        }
    }

    public void d() {
        for (b bVar : this.k) {
            if (bVar instanceof com.facebook.ads.internal.view.i.a.c) {
                com.facebook.ads.internal.view.i.a.c cVar = (com.facebook.ads.internal.view.i.a.c) bVar;
                if (cVar instanceof g) {
                    this.j.b(cVar);
                } else {
                    x.b(cVar);
                }
            }
            bVar.b(this);
        }
    }

    public void e() {
        if (!m()) {
            this.a.a();
        }
    }

    public void f() {
        this.m.post(new Runnable() {
            public void run() {
                a.this.getEventBus().a(a.d);
            }
        });
        this.a.b();
    }

    public void g() {
        this.a.c();
    }

    public int getCurrentPositionInMillis() {
        return this.a.getCurrentPosition();
    }

    public int getDuration() {
        return this.a.getDuration();
    }

    @NonNull
    public com.facebook.ads.internal.o.e<f, d> getEventBus() {
        return this.n;
    }

    public long getInitialBufferTime() {
        return this.a.getInitialBufferTime();
    }

    public com.facebook.ads.internal.view.i.d.d getState() {
        return this.a.getState();
    }

    /* access modifiers changed from: protected */
    public Handler getStateHandler() {
        return this.m;
    }

    public TextureView getTextureView() {
        return (TextureView) this.a;
    }

    public int getVideoHeight() {
        return this.a.getVideoHeight();
    }

    public int getVideoProgressReportIntervalMs() {
        return this.r;
    }

    public com.facebook.ads.internal.view.i.a.a getVideoStartReason() {
        return this.a.getStartReason();
    }

    public View getVideoView() {
        return this.j;
    }

    public int getVideoWidth() {
        return this.a.getVideoWidth();
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.a.getVolume();
    }

    public boolean h() {
        return com.facebook.ads.internal.r.a.a(getContext());
    }

    public boolean i() {
        return this.p;
    }

    public boolean j() {
        return getState() == com.facebook.ads.internal.view.i.d.d.STARTED;
    }

    public boolean k() {
        return this.a.d();
    }

    public void l() {
        this.a.setVideoStateChangeListener(null);
        this.a.e();
    }

    public boolean m() {
        return getState() == com.facebook.ads.internal.view.i.d.d.PAUSED;
    }

    public boolean n() {
        return m() && this.q;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        this.n.a(i);
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.n.a(h);
        super.onDetachedFromWindow();
    }

    public void setControlsAnchorView(View view) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.setControlsAnchorView(view);
        }
    }

    public void setIsFullScreen(boolean z) {
        this.p = z;
        this.a.setFullScreen(z);
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setVideoMPD(@Nullable String str) {
        this.a.setVideoMPD(str);
    }

    public void setVideoProgressReportIntervalMs(int i2) {
        this.r = i2;
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            d();
        } else {
            c();
            this.a.setup(uri);
        }
        this.o = false;
    }

    public void setVideoURI(@Nullable String str) {
        setVideoURI(str != null ? Uri.parse(str) : null);
    }

    public void setVolume(float f2) {
        this.a.setRequestedVolume(f2);
        getEventBus().a(g);
    }
}
