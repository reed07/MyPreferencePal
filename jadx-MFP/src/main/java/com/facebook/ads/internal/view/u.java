package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.a.l;

public class u extends RelativeLayout {
    /* access modifiers changed from: private */
    public l a;
    /* access modifiers changed from: private */
    public ViewDragHelper b = ViewDragHelper.create(this, 1.0f, new b());
    @Nullable
    private a c;
    /* access modifiers changed from: private */
    public boolean d = true;
    /* access modifiers changed from: private */
    public int e = 0;
    private int f = 0;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public int i;

    public interface a {
        void a();

        void b();
    }

    private class b extends Callback {
        private b() {
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            int paddingTop = u.this.getPaddingTop();
            return Math.min(Math.max(i, paddingTop), u.this.g);
        }

        public int getViewVerticalDragRange(View view) {
            return u.this.g;
        }

        public void onViewDragStateChanged(int i) {
            if (i != u.this.e) {
                if (i == 0 && (u.this.e == 1 || u.this.e == 2)) {
                    if (u.this.h == u.this.i) {
                        u.d(u.this);
                    } else if (u.this.h == u.this.g) {
                        u.this.d();
                    }
                }
                u.this.e = i;
            }
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            u.this.h = i2;
        }

        public void onViewReleased(View view, float f, float f2) {
            if (u.this.h == u.this.i) {
                u.this.d = false;
                return;
            }
            boolean z = true;
            if (u.this.h == u.this.g) {
                u.this.d = true;
                return;
            }
            double d = (double) f2;
            if (d <= 800.0d) {
                if (d >= -800.0d) {
                    if (u.this.h <= u.this.g / 2) {
                        int b = u.this.h;
                        int e = u.this.g / 2;
                    }
                }
                z = false;
            }
            if (u.this.b.settleCapturedViewAt(0, z ? u.this.g : u.this.i)) {
                ViewCompat.postInvalidateOnAnimation(u.this);
            }
        }

        public boolean tryCaptureView(View view, int i) {
            return view == u.this.a;
        }
    }

    public u(Context context, l lVar, int i2, int i3) {
        super(context);
        this.a = lVar;
        this.i = i3;
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        this.g = i2;
        int i4 = this.g;
        this.h = i4;
        this.a.offsetTopAndBottom(i4);
        this.f = this.g;
        addView(this.a);
        setBackgroundColor(0);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.d = true;
        a aVar = this.c;
        if (aVar != null) {
            aVar.a();
        }
    }

    static /* synthetic */ void d(u uVar) {
        uVar.d = false;
        a aVar = uVar.c;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void a() {
        this.a.offsetTopAndBottom(this.g);
        this.f = this.g;
        d();
    }

    public void b() {
        this.a.offsetTopAndBottom(this.i);
        this.f = this.i;
    }

    public boolean c() {
        return this.d;
    }

    public void computeScroll() {
        if (this.b.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            this.f = this.a.getTop();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d && this.b.isViewUnder(this.a, (int) motionEvent.getX(), (int) motionEvent.getY()) && this.a.getScrollY() == 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.a.offsetTopAndBottom(this.f);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        this.a.a(motionEvent);
        if (!this.b.isViewUnder(this.a, x, y)) {
            return super.onTouchEvent(motionEvent);
        }
        this.b.processTouchEvent(motionEvent);
        return true;
    }

    public void setDragListener(a aVar) {
        this.c = aVar;
    }

    public void setDragRange(int i2) {
        this.g = i2;
        this.b.smoothSlideViewTo(this.a, 0, this.g);
    }
}
