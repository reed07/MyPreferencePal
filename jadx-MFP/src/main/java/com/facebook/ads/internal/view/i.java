package com.facebook.ads.internal.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.m;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.component.CircularProgressView;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.p;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.e.g;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class i extends LinearLayout implements com.facebook.ads.internal.view.i.a.b {
    public static final int a = ((int) (x.b * 56.0f));
    private static final float d = Resources.getSystem().getDisplayMetrics().density;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final int j;
    /* access modifiers changed from: private */
    public final p b = new p() {
        public void a(o oVar) {
            if (i.this.u != null && i.this.v != 0 && i.this.o.isShown()) {
                float currentPositionInMillis = ((float) i.this.u.getCurrentPositionInMillis()) / Math.min(((float) i.this.v) * 1000.0f, (float) i.this.u.getDuration());
                i.this.setProgress(100.0f * currentPositionInMillis);
                if (currentPositionInMillis >= 1.0f) {
                    i.this.a(true);
                    i.this.u.getEventBus().b((T[]) new f[]{i.this.b, i.this.c});
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final d c = new d() {
        public void a(c cVar) {
            if (i.this.u != null && i.this.v != 0 && i.this.o.isShown() && !i.this.x) {
                i.this.a(true);
                i.this.u.getEventBus().b((T[]) new f[]{i.this.b, i.this.c});
            }
        }
    };
    /* access modifiers changed from: private */
    public final C0012a k;
    private final ImageView l;
    private final FrameLayout m;
    private final ImageView n;
    /* access modifiers changed from: private */
    public final CircularProgressView o;
    private final com.facebook.ads.internal.view.e.c p;
    private final RelativeLayout q;
    /* access modifiers changed from: private */
    public final PopupMenu r;
    @Nullable
    private ImageView s;
    /* access modifiers changed from: private */
    @Nullable
    public b t;
    /* access modifiers changed from: private */
    @Nullable
    public a u;
    /* access modifiers changed from: private */
    public int v = 0;
    /* access modifiers changed from: private */
    public boolean w = false;
    /* access modifiers changed from: private */
    public boolean x = false;
    private OnDismissListener y;

    public enum a {
        CROSS,
        ARROWS,
        DOWN_ARROW
    }

    public interface b {
        void a();
    }

    static {
        float f2 = d;
        e = (int) (40.0f * f2);
        f = (int) (44.0f * f2);
        g = (int) (10.0f * f2);
        h = (int) (f2 * 16.0f);
        int i2 = h;
        int i3 = g;
        i = i2 - i3;
        j = (i2 * 2) - i3;
    }

    public i(Context context, C0012a aVar, a aVar2) {
        super(context);
        this.k = aVar;
        setGravity(16);
        if (VERSION.SDK_INT >= 14) {
            this.y = new OnDismissListener() {
                public void onDismiss(PopupMenu popupMenu) {
                    i.this.w = false;
                }
            };
        }
        this.n = new ImageView(context);
        ImageView imageView = this.n;
        int i2 = g;
        imageView.setPadding(i2, i2, i2, i2);
        this.n.setScaleType(ScaleType.FIT_CENTER);
        this.n.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.this.t != null && i.this.x) {
                    i.this.t.a();
                }
            }
        });
        setCloseButtonStyle(aVar2);
        this.o = new CircularProgressView(context);
        CircularProgressView circularProgressView = this.o;
        int i3 = g;
        circularProgressView.setPadding(i3, i3, i3, i3);
        this.o.setProgress(BitmapDescriptorFactory.HUE_RED);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int i4 = i;
        layoutParams.setMargins(i4, i4, j, i4);
        int i5 = f;
        LayoutParams layoutParams2 = new LayoutParams(i5, i5);
        this.m = new FrameLayout(context);
        this.m.setLayoutTransition(new LayoutTransition());
        this.m.addView(this.n, layoutParams2);
        this.m.addView(this.o, layoutParams2);
        addView(this.m, layoutParams);
        this.q = new RelativeLayout(context);
        LayoutParams layoutParams3 = new LayoutParams(0, -2);
        layoutParams3.weight = 1.0f;
        this.p = new com.facebook.ads.internal.view.e.c(context);
        LayoutParams layoutParams4 = new LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        this.p.setLayoutParams(layoutParams4);
        this.q.addView(this.p);
        addView(this.q, layoutParams3);
        this.l = new ImageView(context);
        ImageView imageView2 = this.l;
        int i6 = g;
        imageView2.setPadding(i6, i6, i6, i6);
        this.l.setScaleType(ScaleType.FIT_CENTER);
        this.l.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.AD_CHOICES_ICON));
        this.l.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                i.this.r.show();
                i.this.w = true;
            }
        });
        this.r = new PopupMenu(context, this.l);
        this.r.getMenu().add("Ad Choices");
        int i7 = e;
        LayoutParams layoutParams5 = new LayoutParams(i7, i7);
        int i8 = h;
        layoutParams5.setMargins(0, i8 / 2, i8 / 2, i8 / 2);
        addView(this.l, layoutParams5);
    }

    public void a(h hVar, boolean z) {
        int a2 = hVar.a(z);
        this.p.a(hVar.g(z), a2);
        this.l.setColorFilter(a2);
        ImageView imageView = this.s;
        if (imageView != null) {
            imageView.setColorFilter(a2);
        }
        this.n.setColorFilter(a2);
        this.o.a(ColorUtils.setAlphaComponent(a2, 77), a2);
        if (z) {
            GradientDrawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(BitmapDescriptorFactory.HUE_RED);
            x.a((View) this, (Drawable) gradientDrawable);
            return;
        }
        x.a((View) this, 0);
    }

    public void a(final m mVar, final String str) {
        this.s = new ImageView(getContext());
        ImageView imageView = this.s;
        int i2 = g;
        imageView.setPadding(i2, i2, i2, i2);
        this.s.setScaleType(ScaleType.FIT_CENTER);
        this.s.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.INFO_ICON));
        this.s.setColorFilter(-1);
        int i3 = e;
        addView(this.s, getChildCount() - 1, new LayoutParams(i3, i3));
        this.s.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                i.this.k.a(str, true, null);
            }
        });
        this.l.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String m = !TextUtils.isEmpty(com.facebook.ads.internal.f.a.m(i.this.getContext())) ? com.facebook.ads.internal.f.a.m(i.this.getContext()) : mVar.c();
                if (!TextUtils.isEmpty(m)) {
                    g.a(new g(), i.this.getContext(), Uri.parse(m), str);
                }
            }
        });
    }

    public void a(final m mVar, final String str, int i2) {
        this.v = i2;
        this.p.setPageDetails(mVar);
        this.r.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                i.this.w = false;
                if (!TextUtils.isEmpty(mVar.c())) {
                    g.a(new g(), i.this.getContext(), Uri.parse(mVar.c()), str);
                }
                return true;
            }
        });
        if (VERSION.SDK_INT >= 14) {
            this.r.setOnDismissListener(this.y);
        }
        a(i2 <= 0);
    }

    public void a(a aVar) {
        this.u = aVar;
        this.u.getEventBus().a((T[]) new f[]{this.b, this.c});
    }

    public void a(boolean z) {
        this.x = z;
        this.m.setVisibility(0);
        int i2 = 8;
        this.o.setVisibility(z ? 8 : 0);
        ImageView imageView = this.n;
        if (z) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
        ((LayoutParams) this.q.getLayoutParams()).leftMargin = 0;
    }

    public boolean a() {
        return this.x;
    }

    public void b() {
        this.x = false;
        this.m.setVisibility(8);
        this.o.setVisibility(8);
        this.n.setVisibility(8);
        ((LayoutParams) this.q.getLayoutParams()).leftMargin = g;
    }

    public void b(a aVar) {
        a aVar2 = this.u;
        if (aVar2 != null) {
            aVar2.getEventBus().b((T[]) new f[]{this.b, this.c});
            this.u = null;
        }
    }

    public void b(boolean z) {
        this.l.setVisibility(z ? 0 : 8);
    }

    public void c() {
        x.b(this.p);
    }

    public void d() {
        if (VERSION.SDK_INT >= 14) {
            this.r.setOnDismissListener(null);
        }
        this.r.dismiss();
        if (VERSION.SDK_INT >= 14) {
            this.r.setOnDismissListener(this.y);
        }
    }

    public void e() {
        if (this.w && VERSION.SDK_INT >= 14) {
            this.r.show();
        }
    }

    public void setCloseButtonStyle(a aVar) {
        com.facebook.ads.internal.w.c.b bVar;
        if (this.n != null) {
            switch (aVar) {
                case ARROWS:
                    bVar = com.facebook.ads.internal.w.c.b.SKIP_ARROW;
                    break;
                case DOWN_ARROW:
                    bVar = com.facebook.ads.internal.w.c.b.MINIMIZE_ARROW;
                    break;
                default:
                    bVar = com.facebook.ads.internal.w.c.b.CROSS;
                    break;
            }
            this.n.setImageBitmap(com.facebook.ads.internal.w.c.c.a(bVar));
        }
    }

    public void setPageDetailsVisibility(int i2) {
        this.q.setVisibility(i2);
    }

    public void setProgress(float f2) {
        this.o.setProgressWithAnimation(f2);
    }

    public void setShowPageDetails(boolean z) {
        this.q.removeAllViews();
        if (z) {
            this.q.addView(this.p);
        }
    }

    public void setToolbarListener(b bVar) {
        this.t = bVar;
    }
}
