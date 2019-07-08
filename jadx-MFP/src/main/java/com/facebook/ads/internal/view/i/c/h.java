package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.n;

public class h extends c implements OnTouchListener {
    private final n a;
    private final j b;
    private final l c;
    private final d d;
    /* access modifiers changed from: private */
    public final m e;

    public h(Context context) {
        this(context, null);
    }

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new n() {
            public void a(m mVar) {
                h.this.setVisibility(0);
            }
        };
        this.b = new j() {
            public void a(i iVar) {
                h.this.e.setChecked(true);
            }
        };
        this.c = new l() {
            public void a(k kVar) {
                h.this.e.setChecked(false);
            }
        };
        this.d = new d() {
            public void a(com.facebook.ads.internal.view.i.b.c cVar) {
                h.this.e.setChecked(true);
            }
        };
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.e = new m(context);
        this.e.setChecked(true);
        LayoutParams layoutParams = new LayoutParams((int) (displayMetrics.density * 25.0f), (int) (displayMetrics.density * 25.0f));
        setVisibility(8);
        addView(this.e, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.e.setOnTouchListener(this);
        setOnTouchListener(this);
        if (getVideoView() != null) {
            getVideoView().getEventBus().a((T[]) new f[]{this.a, this.d, this.b, this.c});
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b((T[]) new f[]{this.c, this.b, this.d, this.a});
        }
        setOnTouchListener(null);
        this.e.setOnTouchListener(null);
        super.b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        a videoView = getVideoView();
        if (videoView == null) {
            return false;
        }
        if (videoView.getState() == com.facebook.ads.internal.view.i.d.d.PREPARED || videoView.getState() == com.facebook.ads.internal.view.i.d.d.PAUSED || videoView.getState() == com.facebook.ads.internal.view.i.d.d.PLAYBACK_COMPLETED) {
            videoView.a(com.facebook.ads.internal.view.i.a.a.USER_STARTED);
            return true;
        }
        if (videoView.getState() == com.facebook.ads.internal.view.i.d.d.STARTED) {
            videoView.a(true);
        }
        return false;
    }
}
