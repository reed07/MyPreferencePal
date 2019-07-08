package com.facebook.ads.internal.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.a.c;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.c.f;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.b.u;
import com.facebook.ads.internal.view.i.c.a.C0020a;
import com.facebook.ads.internal.view.i.c.d;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.c.j;
import com.facebook.ads.internal.view.i.c.k;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.view.i.c.n;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.appevents.UserDataStore;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;
import org.json.JSONObject;

public class h extends f implements OnTouchListener, a {
    static final /* synthetic */ boolean i = (!h.class.desiredAssertionStatus());
    private static final String j = h.class.getSimpleName();
    private int A = -10525069;
    private int B = -12286980;
    private boolean C = false;
    @Nullable
    private com.facebook.ads.internal.view.i.a.a D;
    final int f = 64;
    final int g = 64;
    final int h = 16;
    @Nullable
    private C0012a k;
    /* access modifiers changed from: private */
    @Nullable
    public Activity l;
    private BackButtonInterceptor m = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            if (h.this.y == null) {
                return false;
            }
            if (!h.this.y.a()) {
                return true;
            }
            if (!(h.this.y.getSkipSeconds() == 0 || h.this.b == null)) {
                h.this.b.f();
            }
            if (h.this.b != null) {
                h.this.b.g();
            }
            return false;
        }
    };
    private final OnTouchListener n = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return true;
            }
            if (h.this.y != null) {
                if (!h.this.y.a()) {
                    return true;
                }
                if (!(h.this.y.getSkipSeconds() == 0 || h.this.b == null)) {
                    h.this.b.f();
                }
                if (h.this.b != null) {
                    h.this.b.g();
                }
            }
            h.this.l.finish();
            return true;
        }
    };
    private f o = f.UNSPECIFIED;
    private final w p = new w();
    private com.facebook.ads.internal.view.e.a q;
    private TextView r;
    private TextView s;
    private ImageView t;
    @Nullable
    private C0020a u;
    private n v;
    private ViewGroup w;
    private d x;
    /* access modifiers changed from: private */
    public j y;
    private int z = -1;

    /* JADX WARNING: Removed duplicated region for block: B:137:0x0618  */
    /* JADX WARNING: Removed duplicated region for block: B:139:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            float r2 = com.facebook.ads.internal.w.b.x.b
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r4 = 1113587712(0x42600000, float:56.0)
            float r4 = r4 * r2
            int r4 = (int) r4
            r3.<init>(r4, r4)
            r5 = 10
            r3.addRule(r5)
            r6 = 11
            r3.addRule(r6)
            r6 = 1098907648(0x41800000, float:16.0)
            float r6 = r6 * r2
            int r6 = (int) r6
            com.facebook.ads.internal.view.i.c.j r7 = r0.y
            r7.setPadding(r6, r6, r6, r6)
            com.facebook.ads.internal.view.i.c.j r7 = r0.y
            r7.setLayoutParams(r3)
            boolean r3 = r21.h()
            if (r3 == 0) goto L_0x0032
            com.facebook.ads.internal.view.i.c.d$a r3 = com.facebook.ads.internal.view.i.c.d.a.FADE_OUT_ON_PLAY
            goto L_0x0034
        L_0x0032:
            com.facebook.ads.internal.view.i.c.d$a r3 = com.facebook.ads.internal.view.i.c.d.a.VISIBLE
        L_0x0034:
            com.facebook.ads.internal.view.i.a r7 = r0.b
            int r7 = r7.getId()
            r9 = 0
            r10 = 2
            r13 = 16
            r14 = -2
            r15 = 12
            r12 = 1
            r11 = -1
            if (r1 != r12) goto L_0x01f0
            com.facebook.ads.internal.view.i.a r12 = r0.b
            int r12 = r12.getVideoHeight()
            if (r12 <= 0) goto L_0x005d
            com.facebook.ads.internal.view.i.a r12 = r0.b
            int r12 = r12.getVideoWidth()
            float r12 = (float) r12
            com.facebook.ads.internal.view.i.a r8 = r0.b
            int r8 = r8.getVideoHeight()
            float r8 = (float) r8
            float r12 = r12 / r8
            goto L_0x005f
        L_0x005d:
            r12 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x005f:
            r16 = r6
            double r5 = (double) r12
            r17 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            int r12 = (r5 > r17 ? 1 : (r5 == r17 ? 0 : -1))
            if (r12 > 0) goto L_0x006d
            r5 = 1
            goto L_0x006e
        L_0x006d:
            r5 = 0
        L_0x006e:
            if (r5 != 0) goto L_0x007b
            boolean r5 = r21.k()
            if (r5 == 0) goto L_0x0077
            goto L_0x007b
        L_0x0077:
            r6 = r16
            goto L_0x01f0
        L_0x007b:
            android.graphics.drawable.GradientDrawable r1 = new android.graphics.drawable.GradientDrawable
            android.graphics.drawable.GradientDrawable$Orientation r4 = android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM
            int[] r5 = new int[r10]
            r5 = {0, -15658735} // fill-array
            r1.<init>(r4, r5)
            r1.setCornerRadius(r9)
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r11, r14)
            r5 = 10
            r4.addRule(r5)
            com.facebook.ads.internal.view.i.a r5 = r0.b
            r5.setLayoutParams(r4)
            com.facebook.ads.internal.view.i.a r4 = r0.b
            r0.a(r4)
            com.facebook.ads.internal.view.i.c.j r4 = r0.y
            r0.a(r4)
            com.facebook.ads.internal.view.i.c.a$a r4 = r0.u
            if (r4 == 0) goto L_0x00aa
            r0.a(r4)
        L_0x00aa:
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            com.facebook.ads.internal.view.e.a r5 = r0.q
            if (r5 == 0) goto L_0x00b3
            r8 = 64
            goto L_0x00b4
        L_0x00b3:
            r8 = 0
        L_0x00b4:
            int r8 = r8 + 60
            int r8 = r8 + r13
            int r8 = r8 + r13
            int r8 = r8 + r13
            float r5 = (float) r8
            float r5 = r5 * r2
            int r5 = (int) r5
            r4.<init>(r11, r5)
            r4.addRule(r15)
            android.widget.RelativeLayout r5 = new android.widget.RelativeLayout
            android.content.Context r6 = r0.d
            r5.<init>(r6)
            com.facebook.ads.internal.w.b.x.a(r5, r1)
            r5.setLayoutParams(r4)
            com.facebook.ads.internal.view.e.a r1 = r0.q
            if (r1 == 0) goto L_0x00d7
            r8 = 64
            goto L_0x00d8
        L_0x00d7:
            r8 = 0
        L_0x00d8:
            int r8 = r8 + r13
            int r8 = r8 + r13
            float r1 = (float) r8
            float r1 = r1 * r2
            int r1 = (int) r1
            r6 = r16
            r4 = 0
            r5.setPadding(r6, r4, r6, r1)
            r0.w = r5
            boolean r1 = r0.C
            if (r1 != 0) goto L_0x00ef
            com.facebook.ads.internal.view.i.c.d r1 = r0.x
            r1.a(r5, r3)
        L_0x00ef:
            r0.a(r5)
            com.facebook.ads.internal.view.i.c.n r1 = r0.v
            if (r1 == 0) goto L_0x0114
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1086324736(0x40c00000, float:6.0)
            float r8 = r2 * r3
            int r3 = (int) r8
            r1.<init>(r11, r3)
            r1.addRule(r15)
            r3 = -1061158912(0xffffffffc0c00000, float:-6.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.topMargin = r3
            com.facebook.ads.internal.view.i.c.n r3 = r0.v
            r3.setLayoutParams(r1)
            com.facebook.ads.internal.view.i.c.n r1 = r0.v
            r0.a(r1)
        L_0x0114:
            com.facebook.ads.internal.view.e.a r1 = r0.q
            if (r1 == 0) goto L_0x0139
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1115684864(0x42800000, float:64.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.<init>(r11, r3)
            r1.bottomMargin = r6
            r1.leftMargin = r6
            r1.rightMargin = r6
            r3 = 1
            r1.addRule(r3)
            r1.addRule(r15)
            com.facebook.ads.internal.view.e.a r3 = r0.q
            r3.setLayoutParams(r1)
            com.facebook.ads.internal.view.e.a r1 = r0.q
            r0.a(r1)
        L_0x0139:
            android.widget.ImageView r1 = r0.t
            if (r1 == 0) goto L_0x0159
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1114636288(0x42700000, float:60.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.<init>(r3, r3)
            r1.addRule(r15)
            r3 = 9
            r1.addRule(r3)
            android.widget.ImageView r3 = r0.t
            r3.setLayoutParams(r1)
            android.widget.ImageView r1 = r0.t
            r0.a(r5, r1)
        L_0x0159:
            android.widget.TextView r1 = r0.r
            if (r1 == 0) goto L_0x01a7
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r14)
            r3 = 1108344832(0x42100000, float:36.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.bottomMargin = r3
            r1.addRule(r15)
            r3 = 9
            r1.addRule(r3)
            android.widget.TextView r3 = r0.r
            android.text.TextUtils$TruncateAt r4 = android.text.TextUtils.TruncateAt.END
            r3.setEllipsize(r4)
            android.widget.TextView r3 = r0.r
            r4 = 8388611(0x800003, float:1.1754948E-38)
            r3.setGravity(r4)
            android.widget.TextView r3 = r0.r
            r3.setLayoutParams(r1)
            android.widget.TextView r1 = r0.r
            r3 = 1
            r1.setMaxLines(r3)
            android.widget.TextView r1 = r0.r
            r3 = 1116733440(0x42900000, float:72.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r4 = 0
            r1.setPadding(r3, r4, r4, r4)
            android.widget.TextView r1 = r0.r
            r1.setTextColor(r11)
            android.widget.TextView r1 = r0.r
            r3 = 1099956224(0x41900000, float:18.0)
            r1.setTextSize(r3)
            android.widget.TextView r1 = r0.r
            r0.a(r5, r1)
        L_0x01a7:
            android.widget.TextView r1 = r0.s
            if (r1 == 0) goto L_0x0604
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r14)
            r1.addRule(r15)
            r3 = 9
            r1.addRule(r3)
            r3 = 1082130432(0x40800000, float:4.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.bottomMargin = r3
            android.widget.TextView r3 = r0.s
            android.text.TextUtils$TruncateAt r4 = android.text.TextUtils.TruncateAt.END
            r3.setEllipsize(r4)
            android.widget.TextView r3 = r0.s
            r4 = 8388611(0x800003, float:1.1754948E-38)
            r3.setGravity(r4)
            android.widget.TextView r3 = r0.s
            r3.setLayoutParams(r1)
            android.widget.TextView r1 = r0.s
            r3 = 1
            r1.setMaxLines(r3)
            android.widget.TextView r1 = r0.s
            r3 = 1116733440(0x42900000, float:72.0)
            float r2 = r2 * r3
            int r2 = (int) r2
            r3 = 0
            r1.setPadding(r2, r3, r3, r3)
            android.widget.TextView r1 = r0.s
            r1.setTextColor(r11)
            android.widget.TextView r1 = r0.s
            r0.a(r5, r1)
            goto L_0x0604
        L_0x01f0:
            r5 = 1073741824(0x40000000, float:2.0)
            r12 = 1117782016(0x42a00000, float:80.0)
            r8 = 17
            r13 = 1
            if (r1 != r13) goto L_0x0335
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r14)
            r3 = 10
            r1.addRule(r3)
            com.facebook.ads.internal.view.i.a r3 = r0.b
            r3.setLayoutParams(r1)
            com.facebook.ads.internal.view.i.a r1 = r0.b
            r0.a(r1)
            com.facebook.ads.internal.view.i.c.j r1 = r0.y
            r0.a(r1)
            com.facebook.ads.internal.view.i.c.a$a r1 = r0.u
            if (r1 == 0) goto L_0x0219
            r0.a(r1)
        L_0x0219:
            android.widget.LinearLayout r1 = new android.widget.LinearLayout
            android.content.Context r3 = r0.d
            r1.<init>(r3)
            r0.w = r1
            r3 = 112(0x70, float:1.57E-43)
            r1.setGravity(r3)
            r3 = 1
            r1.setOrientation(r3)
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r3.<init>(r11, r11)
            r4 = 1107558400(0x42040000, float:33.0)
            float r4 = r4 * r2
            int r4 = (int) r4
            r3.leftMargin = r4
            r3.rightMargin = r4
            r13 = 1090519040(0x41000000, float:8.0)
            float r13 = r13 * r2
            int r13 = (int) r13
            r3.topMargin = r13
            com.facebook.ads.internal.view.e.a r13 = r0.q
            if (r13 != 0) goto L_0x0247
            r3.bottomMargin = r6
            goto L_0x024c
        L_0x0247:
            float r12 = r12 * r2
            int r12 = (int) r12
            r3.bottomMargin = r12
        L_0x024c:
            r12 = 3
            r3.addRule(r12, r7)
            r1.setLayoutParams(r3)
            r0.a(r1)
            com.facebook.ads.internal.view.e.a r3 = r0.q
            if (r3 == 0) goto L_0x027b
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r12 = 1115684864(0x42800000, float:64.0)
            float r13 = r2 * r12
            int r12 = (int) r13
            r3.<init>(r11, r12)
            r3.bottomMargin = r6
            r3.leftMargin = r4
            r3.rightMargin = r4
            r4 = 1
            r3.addRule(r4)
            r3.addRule(r15)
            com.facebook.ads.internal.view.e.a r4 = r0.q
            r4.setLayoutParams(r3)
            com.facebook.ads.internal.view.e.a r3 = r0.q
            r0.a(r3)
        L_0x027b:
            android.widget.TextView r3 = r0.r
            if (r3 == 0) goto L_0x02b7
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r3.<init>(r14, r14)
            r3.weight = r5
            r3.gravity = r8
            android.widget.TextView r4 = r0.r
            android.text.TextUtils$TruncateAt r6 = android.text.TextUtils.TruncateAt.END
            r4.setEllipsize(r6)
            android.widget.TextView r4 = r0.r
            r4.setGravity(r8)
            android.widget.TextView r4 = r0.r
            r4.setLayoutParams(r3)
            android.widget.TextView r3 = r0.r
            r3.setMaxLines(r10)
            android.widget.TextView r3 = r0.r
            r4 = 0
            r3.setPadding(r4, r4, r4, r4)
            android.widget.TextView r3 = r0.r
            int r4 = r0.A
            r3.setTextColor(r4)
            android.widget.TextView r3 = r0.r
            r4 = 1103101952(0x41c00000, float:24.0)
            r3.setTextSize(r4)
            android.widget.TextView r3 = r0.r
            r0.a(r1, r3)
        L_0x02b7:
            android.widget.ImageView r3 = r0.t
            if (r3 == 0) goto L_0x02d3
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r4 = 1115684864(0x42800000, float:64.0)
            float r4 = r4 * r2
            int r4 = (int) r4
            r3.<init>(r4, r4)
            r3.weight = r9
            r3.gravity = r8
            android.widget.ImageView r4 = r0.t
            r4.setLayoutParams(r3)
            android.widget.ImageView r3 = r0.t
            r0.a(r1, r3)
        L_0x02d3:
            android.widget.TextView r3 = r0.s
            if (r3 == 0) goto L_0x030a
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r3.<init>(r11, r14)
            r3.weight = r5
            r4 = 16
            r3.gravity = r4
            android.widget.TextView r5 = r0.s
            android.text.TextUtils$TruncateAt r6 = android.text.TextUtils.TruncateAt.END
            r5.setEllipsize(r6)
            android.widget.TextView r5 = r0.s
            r5.setGravity(r4)
            android.widget.TextView r4 = r0.s
            r4.setLayoutParams(r3)
            android.widget.TextView r3 = r0.s
            r3.setMaxLines(r10)
            android.widget.TextView r3 = r0.s
            r4 = 0
            r3.setPadding(r4, r4, r4, r4)
            android.widget.TextView r3 = r0.s
            int r4 = r0.A
            r3.setTextColor(r4)
            android.widget.TextView r3 = r0.s
            r0.a(r1, r3)
        L_0x030a:
            com.facebook.ads.internal.view.i.c.n r1 = r0.v
            if (r1 == 0) goto L_0x0326
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1086324736(0x40c00000, float:6.0)
            float r2 = r2 * r3
            int r2 = (int) r2
            r1.<init>(r11, r2)
            r2 = 3
            r1.addRule(r2, r7)
            com.facebook.ads.internal.view.i.c.n r2 = r0.v
            r2.setLayoutParams(r1)
            com.facebook.ads.internal.view.i.c.n r1 = r0.v
        L_0x0323:
            r0.a(r1)
        L_0x0326:
            com.facebook.ads.internal.view.i.a r1 = r0.b
            android.view.ViewParent r1 = r1.getParent()
            android.view.View r1 = (android.view.View) r1
            int r2 = r0.z
        L_0x0330:
            com.facebook.ads.internal.w.b.x.a(r1, r2)
            goto L_0x0610
        L_0x0335:
            com.facebook.ads.internal.view.i.a r13 = r0.b
            int r13 = r13.getVideoHeight()
            if (r13 <= 0) goto L_0x034d
            com.facebook.ads.internal.view.i.a r13 = r0.b
            int r13 = r13.getVideoWidth()
            float r13 = (float) r13
            com.facebook.ads.internal.view.i.a r1 = r0.b
            int r1 = r1.getVideoHeight()
            float r1 = (float) r1
            float r13 = r13 / r1
            goto L_0x034f
        L_0x034d:
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x034f:
            double r9 = (double) r13
            r19 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            int r13 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1))
            if (r13 <= 0) goto L_0x0364
            r19 = 4607632778762754458(0x3ff199999999999a, double:1.1)
            int r13 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1))
            if (r13 >= 0) goto L_0x0364
            r9 = 1
            goto L_0x0365
        L_0x0364:
            r9 = 0
        L_0x0365:
            if (r9 == 0) goto L_0x04a4
            boolean r9 = r21.k()
            if (r9 != 0) goto L_0x04a4
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r3.<init>(r14, r11)
            r4 = 9
            r3.addRule(r4)
            com.facebook.ads.internal.view.i.a r4 = r0.b
            r4.setLayoutParams(r3)
            com.facebook.ads.internal.view.i.a r3 = r0.b
            r0.a(r3)
            com.facebook.ads.internal.view.i.c.j r3 = r0.y
            r0.a(r3)
            com.facebook.ads.internal.view.i.c.a$a r3 = r0.u
            if (r3 == 0) goto L_0x038d
            r0.a(r3)
        L_0x038d:
            android.widget.LinearLayout r3 = new android.widget.LinearLayout
            android.content.Context r4 = r0.d
            r3.<init>(r4)
            r0.w = r3
            r4 = 112(0x70, float:1.57E-43)
            r3.setGravity(r4)
            r4 = 1
            r3.setOrientation(r4)
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r11, r11)
            r4.leftMargin = r6
            r4.rightMargin = r6
            r9 = 1090519040(0x41000000, float:8.0)
            float r9 = r9 * r2
            int r9 = (int) r9
            r4.topMargin = r9
            float r12 = r12 * r2
            int r9 = (int) r12
            r4.bottomMargin = r9
            r9 = 1
            r4.addRule(r9, r7)
            r3.setLayoutParams(r4)
            r0.a(r3)
            com.facebook.ads.internal.view.i.c.n r4 = r0.v
            if (r4 == 0) goto L_0x03e9
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r9 = 1086324736(0x40c00000, float:6.0)
            float r9 = r9 * r2
            int r9 = (int) r9
            r4.<init>(r11, r9)
            r9 = 5
            r4.addRule(r9, r7)
            r9 = 7
            r4.addRule(r9, r7)
            r9 = 3
            r4.addRule(r9, r7)
            r9 = -1061158912(0xffffffffc0c00000, float:-6.0)
            float r9 = r9 * r2
            int r9 = (int) r9
            r4.topMargin = r9
            com.facebook.ads.internal.view.i.c.n r9 = r0.v
            r9.setLayoutParams(r4)
            com.facebook.ads.internal.view.i.c.n r4 = r0.v
            r0.a(r4)
        L_0x03e9:
            android.widget.TextView r4 = r0.r
            if (r4 == 0) goto L_0x0427
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            r4.<init>(r14, r14)
            r4.weight = r5
            r4.gravity = r8
            android.widget.TextView r9 = r0.r
            android.text.TextUtils$TruncateAt r10 = android.text.TextUtils.TruncateAt.END
            r9.setEllipsize(r10)
            android.widget.TextView r9 = r0.r
            r9.setGravity(r8)
            android.widget.TextView r9 = r0.r
            r9.setLayoutParams(r4)
            android.widget.TextView r4 = r0.r
            r9 = 10
            r4.setMaxLines(r9)
            android.widget.TextView r9 = r0.r
            r10 = 0
            r9.setPadding(r10, r10, r10, r10)
            android.widget.TextView r9 = r0.r
            int r10 = r0.A
            r9.setTextColor(r10)
            android.widget.TextView r9 = r0.r
            r10 = 1103101952(0x41c00000, float:24.0)
            r9.setTextSize(r10)
            android.widget.TextView r9 = r0.r
            r0.a(r3, r9)
        L_0x0427:
            android.widget.ImageView r9 = r0.t
            if (r9 == 0) goto L_0x0444
            android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
            r10 = 1115684864(0x42800000, float:64.0)
            float r12 = r2 * r10
            int r10 = (int) r12
            r9.<init>(r10, r10)
            r1 = 0
            r9.weight = r1
            r9.gravity = r8
            android.widget.ImageView r1 = r0.t
            r1.setLayoutParams(r9)
            android.widget.ImageView r1 = r0.t
            r0.a(r3, r1)
        L_0x0444:
            android.widget.TextView r1 = r0.s
            if (r1 == 0) goto L_0x047d
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            r1.<init>(r11, r14)
            r1.weight = r5
            r5 = 16
            r1.gravity = r5
            android.widget.TextView r5 = r0.s
            android.text.TextUtils$TruncateAt r9 = android.text.TextUtils.TruncateAt.END
            r5.setEllipsize(r9)
            android.widget.TextView r5 = r0.s
            r5.setGravity(r8)
            android.widget.TextView r5 = r0.s
            r5.setLayoutParams(r1)
            android.widget.TextView r1 = r0.s
            r4 = 10
            r1.setMaxLines(r4)
            android.widget.TextView r1 = r0.s
            r4 = 0
            r1.setPadding(r4, r4, r4, r4)
            android.widget.TextView r1 = r0.s
            int r4 = r0.A
            r1.setTextColor(r4)
            android.widget.TextView r1 = r0.s
            r0.a(r3, r1)
        L_0x047d:
            com.facebook.ads.internal.view.e.a r1 = r0.q
            if (r1 == 0) goto L_0x0326
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1115684864(0x42800000, float:64.0)
            float r2 = r2 * r3
            int r2 = (int) r2
            r1.<init>(r11, r2)
            r1.bottomMargin = r6
            r1.leftMargin = r6
            r1.rightMargin = r6
            r2 = 1
            r1.addRule(r2)
            r1.addRule(r15)
            r1.addRule(r2, r7)
            com.facebook.ads.internal.view.e.a r2 = r0.q
            r2.setLayoutParams(r1)
            com.facebook.ads.internal.view.e.a r1 = r0.q
            goto L_0x0323
        L_0x04a4:
            android.graphics.drawable.GradientDrawable r5 = new android.graphics.drawable.GradientDrawable
            android.graphics.drawable.GradientDrawable$Orientation r7 = android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM
            r8 = 2
            int[] r9 = new int[r8]
            r9 = {0, -15658735} // fill-array
            r5.<init>(r7, r9)
            r1 = 0
            r5.setCornerRadius(r1)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r11)
            com.facebook.ads.internal.view.i.a r7 = r0.b
            r7.setLayoutParams(r1)
            com.facebook.ads.internal.view.i.a r1 = r0.b
            r0.a(r1)
            com.facebook.ads.internal.view.i.c.j r1 = r0.y
            r0.a(r1)
            com.facebook.ads.internal.view.i.c.a$a r1 = r0.u
            if (r1 == 0) goto L_0x04d0
            r0.a(r1)
        L_0x04d0:
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r7 = 1123549184(0x42f80000, float:124.0)
            float r7 = r7 * r2
            int r7 = (int) r7
            r1.<init>(r11, r7)
            r1.addRule(r15)
            android.widget.RelativeLayout r7 = new android.widget.RelativeLayout
            android.content.Context r8 = r0.d
            r7.<init>(r8)
            com.facebook.ads.internal.w.b.x.a(r7, r5)
            r7.setLayoutParams(r1)
            r1 = 0
            r7.setPadding(r6, r1, r6, r6)
            r0.w = r7
            boolean r1 = r0.C
            if (r1 != 0) goto L_0x04f9
            com.facebook.ads.internal.view.i.c.d r1 = r0.x
            r1.a(r7, r3)
        L_0x04f9:
            r0.a(r7)
            com.facebook.ads.internal.view.e.a r1 = r0.q
            if (r1 == 0) goto L_0x0520
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1121714176(0x42dc0000, float:110.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.<init>(r3, r4)
            r1.rightMargin = r6
            r1.bottomMargin = r6
            r1.addRule(r15)
            r3 = 11
            r1.addRule(r3)
            com.facebook.ads.internal.view.e.a r3 = r0.q
            r3.setLayoutParams(r1)
            com.facebook.ads.internal.view.e.a r1 = r0.q
            r0.a(r1)
        L_0x0520:
            android.widget.ImageView r1 = r0.t
            if (r1 == 0) goto L_0x0547
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1115684864(0x42800000, float:64.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.<init>(r3, r3)
            r1.addRule(r15)
            r3 = 9
            r1.addRule(r3)
            r3 = 1090519040(0x41000000, float:8.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.bottomMargin = r3
            android.widget.ImageView r3 = r0.t
            r3.setLayoutParams(r1)
            android.widget.ImageView r1 = r0.t
            r0.a(r7, r1)
        L_0x0547:
            android.widget.TextView r1 = r0.r
            if (r1 == 0) goto L_0x059f
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r14)
            r3 = 1111490560(0x42400000, float:48.0)
            float r3 = r3 * r2
            int r3 = (int) r3
            r1.bottomMargin = r3
            r1.addRule(r15)
            r3 = 9
            r1.addRule(r3)
            android.widget.TextView r3 = r0.r
            android.text.TextUtils$TruncateAt r4 = android.text.TextUtils.TruncateAt.END
            r3.setEllipsize(r4)
            android.widget.TextView r3 = r0.r
            r4 = 8388611(0x800003, float:1.1754948E-38)
            r3.setGravity(r4)
            android.widget.TextView r3 = r0.r
            r3.setLayoutParams(r1)
            android.widget.TextView r1 = r0.r
            r3 = 1
            r1.setMaxLines(r3)
            android.widget.TextView r1 = r0.r
            float r3 = r2 * r12
            int r3 = (int) r3
            com.facebook.ads.internal.view.e.a r4 = r0.q
            if (r4 == 0) goto L_0x0589
            r4 = 1123811328(0x42fc0000, float:126.0)
            float r4 = r4 * r2
            int r8 = (int) r4
            r4 = 0
            goto L_0x058b
        L_0x0589:
            r4 = 0
            r8 = 0
        L_0x058b:
            r1.setPadding(r3, r4, r8, r4)
            android.widget.TextView r1 = r0.r
            r1.setTextColor(r11)
            android.widget.TextView r1 = r0.r
            r3 = 1103101952(0x41c00000, float:24.0)
            r1.setTextSize(r3)
            android.widget.TextView r1 = r0.r
            r0.a(r7, r1)
        L_0x059f:
            android.widget.TextView r1 = r0.s
            if (r1 == 0) goto L_0x05e9
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r14)
            r1.addRule(r15)
            r3 = 9
            r1.addRule(r3)
            android.widget.TextView r3 = r0.s
            android.text.TextUtils$TruncateAt r4 = android.text.TextUtils.TruncateAt.END
            r3.setEllipsize(r4)
            android.widget.TextView r3 = r0.s
            r4 = 8388611(0x800003, float:1.1754948E-38)
            r3.setGravity(r4)
            android.widget.TextView r3 = r0.s
            r3.setLayoutParams(r1)
            android.widget.TextView r1 = r0.s
            r3 = 2
            r1.setMaxLines(r3)
            android.widget.TextView r1 = r0.s
            r1.setTextColor(r11)
            android.widget.TextView r1 = r0.s
            float r12 = r12 * r2
            int r3 = (int) r12
            com.facebook.ads.internal.view.e.a r4 = r0.q
            if (r4 == 0) goto L_0x05df
            r4 = 1123811328(0x42fc0000, float:126.0)
            float r4 = r4 * r2
            int r8 = (int) r4
            r4 = 0
            goto L_0x05e1
        L_0x05df:
            r4 = 0
            r8 = 0
        L_0x05e1:
            r1.setPadding(r3, r4, r8, r4)
            android.widget.TextView r1 = r0.s
            r0.a(r7, r1)
        L_0x05e9:
            com.facebook.ads.internal.view.i.c.n r1 = r0.v
            if (r1 == 0) goto L_0x0604
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = 1086324736(0x40c00000, float:6.0)
            float r2 = r2 * r3
            int r2 = (int) r2
            r1.<init>(r11, r2)
            r1.addRule(r15)
            com.facebook.ads.internal.view.i.c.n r2 = r0.v
            r2.setLayoutParams(r1)
            com.facebook.ads.internal.view.i.c.n r1 = r0.v
            r0.a(r1)
        L_0x0604:
            com.facebook.ads.internal.view.i.a r1 = r0.b
            android.view.ViewParent r1 = r1.getParent()
            android.view.View r1 = (android.view.View) r1
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            goto L_0x0330
        L_0x0610:
            com.facebook.ads.internal.view.i.a r1 = r0.b
            android.view.View r1 = r1.getRootView()
            if (r1 == 0) goto L_0x061b
            r1.setOnTouchListener(r0)
        L_0x061b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.h.a(int):void");
    }

    private void a(View view) {
        C0012a aVar = this.k;
        if (aVar != null) {
            aVar.a(view);
        }
    }

    private void a(@Nullable ViewGroup viewGroup, @Nullable View view) {
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    private void b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    private boolean k() {
        boolean z2 = false;
        if (this.b.getVideoHeight() <= 0) {
            return false;
        }
        Rect rect = new Rect();
        this.l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (rect.width() > rect.height()) {
            if (((float) (rect.width() - ((rect.height() * this.b.getVideoWidth()) / this.b.getVideoHeight()))) - (x.b * 192.0f) < BitmapDescriptorFactory.HUE_RED) {
                z2 = true;
            }
            return z2;
        }
        if (((((float) (rect.height() - ((rect.width() * this.b.getVideoHeight()) / this.b.getVideoWidth()))) - (x.b * 64.0f)) - (x.b * 64.0f)) - (x.b * 40.0f) < BitmapDescriptorFactory.HUE_RED) {
            z2 = true;
        }
        return z2;
    }

    private void l() {
        b((View) this.b);
        b((View) this.q);
        b((View) this.r);
        b((View) this.s);
        b((View) this.t);
        b((View) this.v);
        b((View) this.w);
        b((View) this.y);
        C0020a aVar = this.u;
        if (aVar != null) {
            b((View) aVar);
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.c == null) {
            Log.e(j, "Unable to add UI without a valid ad response.");
            return;
        }
        String string = this.c.getString(UserDataStore.CITY);
        String optString = this.c.getJSONObject("context").optString("orientation");
        if (!optString.isEmpty()) {
            this.o = f.a(Integer.parseInt(optString));
        }
        if (this.c.has("layout") && !this.c.isNull("layout")) {
            JSONObject jSONObject = this.c.getJSONObject("layout");
            this.z = (int) jSONObject.optLong("bgColor", (long) this.z);
            this.A = (int) jSONObject.optLong("textColor", (long) this.A);
            this.B = (int) jSONObject.optLong("accentColor", (long) this.B);
            this.C = jSONObject.optBoolean("persistentAdDetails", this.C);
        }
        JSONObject jSONObject2 = this.c.getJSONObject("text");
        this.b.setId(VERSION.SDK_INT >= 17 ? View.generateViewId() : x.a());
        int c = c();
        Context context = this.d;
        if (c < 0) {
            c = 0;
        }
        this.y = new j(context, c, this.B);
        this.y.setOnTouchListener(this.n);
        this.b.a((b) this.y);
        if (this.c.has(InMobiNetworkValues.CTA) && !this.c.isNull(InMobiNetworkValues.CTA)) {
            JSONObject jSONObject3 = this.c.getJSONObject(InMobiNetworkValues.CTA);
            com.facebook.ads.internal.view.e.a aVar = new com.facebook.ads.internal.view.e.a(this.d, this.p, jSONObject3.getString("url"), jSONObject3.getString("text"), this.B, this.b, this.a, string);
            this.q = aVar;
            c.a(this.d, this.a, string, Uri.parse(jSONObject3.getString("url")), new HashMap());
        }
        if (this.c.has(InMobiNetworkValues.ICON) && !this.c.isNull(InMobiNetworkValues.ICON)) {
            JSONObject jSONObject4 = this.c.getJSONObject(InMobiNetworkValues.ICON);
            this.t = new ImageView(this.d);
            new com.facebook.ads.internal.view.c.d(this.t).a((int) (x.b * 64.0f), (int) (x.b * 64.0f)).a(jSONObject4.getString("url"));
        }
        if (this.c.has("image") && !this.c.isNull("image")) {
            JSONObject jSONObject5 = this.c.getJSONObject("image");
            g gVar = new g(this.d);
            this.b.a((b) gVar);
            gVar.setImage(jSONObject5.getString("url"));
        }
        String optString2 = jSONObject2.optString("title");
        if (!optString2.isEmpty()) {
            this.r = new TextView(this.d);
            this.r.setText(optString2);
            this.r.setTypeface(Typeface.defaultFromStyle(1));
        }
        String optString3 = jSONObject2.optString("subtitle");
        if (!optString3.isEmpty()) {
            this.s = new TextView(this.d);
            this.s.setText(optString3);
            this.s.setTextSize(16.0f);
        }
        this.v = new n(this.d);
        this.b.a((b) this.v);
        String d = d();
        if (!TextUtils.isEmpty(d)) {
            C0020a aVar2 = new C0020a(this.d, "AdChoices", d, new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 8.0f, BitmapDescriptorFactory.HUE_RED}, string);
            this.u = aVar2;
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.u.setLayoutParams(layoutParams);
        }
        this.b.a((b) new k(this.d));
        l lVar = new l(this.d);
        this.b.a((b) lVar);
        d.a aVar3 = h() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
        this.b.a((b) new d(lVar, aVar3));
        this.x = new d(new RelativeLayout(this.d), aVar3);
        this.b.a((b) this.x);
    }

    @TargetApi(17)
    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        this.l = audienceNetworkActivity;
        if (i || this.k != null) {
            audienceNetworkActivity.addBackButtonInterceptor(this.m);
            l();
            a(this.l.getResources().getConfiguration().orientation);
            if (h()) {
                e();
            } else {
                f();
            }
        } else {
            throw new AssertionError();
        }
    }

    public void a(Configuration configuration) {
        l();
        a(configuration.orientation);
    }

    public void a(Bundle bundle) {
    }

    public void a_(boolean z2) {
        if (this.b != null && this.b.getState() == com.facebook.ads.internal.view.i.d.d.STARTED) {
            this.D = this.b.getVideoStartReason();
            this.b.a(false);
        }
    }

    public void b(boolean z2) {
        if (this.b != null && this.D != null) {
            this.b.a(this.D);
        }
    }

    /* access modifiers changed from: protected */
    public boolean h() {
        if (i || this.c != null) {
            try {
                return this.c.getJSONObject("video").getBoolean(AudienceNetworkActivity.AUTOPLAY);
            } catch (Exception e) {
                Log.w(String.valueOf(h.class), "Invalid JSON", e);
                return true;
            }
        } else {
            throw new AssertionError();
        }
    }

    public f i() {
        return this.o;
    }

    public void j() {
        Activity activity = this.l;
        if (activity != null) {
            activity.finish();
        }
    }

    public void onDestroy() {
        if (!(this.c == null || this.a == null)) {
            String optString = this.c.optString(UserDataStore.CITY);
            if (!TextUtils.isEmpty(optString)) {
                this.a.l(optString, new HashMap());
            }
        }
        if (this.b != null) {
            this.b.g();
        }
        g.a((a) this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.p.a(motionEvent, view.getRootView(), view);
        if (this.b != null) {
            this.b.getEventBus().a(new u(view, motionEvent));
        }
        return true;
    }

    public void setListener(C0012a aVar) {
        this.k = aVar;
    }
}
