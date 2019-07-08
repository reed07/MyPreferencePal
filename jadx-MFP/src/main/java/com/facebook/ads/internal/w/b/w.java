package com.facebook.ads.internal.w.b;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.internal.r.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class w {
    private static final String a = "w";
    private boolean b;
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private long g = -1;
    private int h = -1;
    private long i = -1;
    private long j = -1;
    private int k = -1;
    private int l = -1;
    private int m = -1;
    private int n = -1;
    private float o = -1.0f;
    private float p = -1.0f;
    private float q = -1.0f;
    @Nullable
    private View r;
    @Nullable
    private View s;

    public void a() {
        this.g = System.currentTimeMillis();
    }

    public void a(MotionEvent motionEvent, View view, View view2) {
        if (!this.b) {
            this.b = true;
            InputDevice device = motionEvent.getDevice();
            if (device != null) {
                MotionRange motionRange = device.getMotionRange(0);
                MotionRange motionRange2 = device.getMotionRange(1);
                if (!(motionRange == null || motionRange2 == null)) {
                    this.q = Math.min(motionRange.getRange(), motionRange2.getRange());
                }
            }
            if (this.q <= BitmapDescriptorFactory.HUE_RED) {
                this.q = (float) Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        switch (motionEvent.getAction()) {
            case 0:
                this.c = (int) (((float) iArr[0]) / x.b);
                this.d = (int) (((float) iArr[1]) / x.b);
                this.e = (int) (((float) view.getWidth()) / x.b);
                this.f = (int) (((float) view.getHeight()) / x.b);
                this.h = 1;
                this.i = System.currentTimeMillis();
                this.k = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / x.b);
                this.l = (int) (((float) ((((int) (motionEvent.getY() + 0.5f)) + iArr2[1]) - iArr[1])) / x.b);
                this.o = motionEvent.getPressure();
                this.p = motionEvent.getSize();
                this.r = view2;
                return;
            case 1:
            case 3:
                this.j = System.currentTimeMillis();
                this.m = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / x.b);
                this.n = (int) (((float) ((((int) (motionEvent.getY() + 0.5f)) + iArr2[1]) - iArr[1])) / x.b);
                this.s = view2;
                return;
            case 2:
                float f2 = this.o;
                this.o = f2 - (f2 / ((float) this.h));
                float f3 = this.o;
                float pressure = motionEvent.getPressure();
                int i2 = this.h;
                this.o = f3 + (pressure / ((float) i2));
                float f4 = this.p;
                this.p = f4 - (f4 / ((float) i2));
                float f5 = this.p;
                float size = motionEvent.getSize();
                int i3 = this.h;
                this.p = f5 + (size / ((float) i3));
                this.h = i3 + 1;
                return;
            default:
                return;
        }
    }

    public boolean a(Context context) {
        int d2 = a.d(context);
        return d2 >= 0 && c() < ((long) d2);
    }

    public boolean b() {
        return this.g != -1;
    }

    public long c() {
        if (b()) {
            return System.currentTimeMillis() - this.g;
        }
        return -1;
    }

    public boolean d() {
        return this.b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> e() {
        /*
            r6 = this;
            boolean r0 = r6.b
            if (r0 != 0) goto L_0x0006
            r0 = 0
            return r0
        L_0x0006:
            float r0 = r6.p
            float r1 = r6.q
            float r0 = r0 * r1
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            java.lang.String r0 = java.lang.String.valueOf(r0)
            long r1 = r6.g
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0023
            long r3 = r6.j
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 <= 0) goto L_0x0023
            long r3 = r3 - r1
            goto L_0x0025
        L_0x0023:
            r3 = -1
        L_0x0025:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.String r2 = "adPositionX"
            int r5 = r6.c
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r1.put(r2, r5)
            java.lang.String r2 = "adPositionY"
            int r5 = r6.d
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r1.put(r2, r5)
            java.lang.String r2 = "width"
            int r5 = r6.e
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r1.put(r2, r5)
            java.lang.String r2 = "height"
            int r5 = r6.f
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r1.put(r2, r5)
            java.lang.String r2 = "clickDelayTime"
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "startTime"
            long r3 = r6.i
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "endTime"
            long r3 = r6.j
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "startX"
            int r3 = r6.k
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "startY"
            int r3 = r6.l
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "clickX"
            int r3 = r6.m
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "clickY"
            int r3 = r6.n
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "endX"
            int r3 = r6.m
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "endY"
            int r3 = r6.n
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "force"
            float r3 = r6.o
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r1.put(r2, r3)
            java.lang.String r2 = "radiusX"
            r1.put(r2, r0)
            java.lang.String r2 = "radiusY"
            r1.put(r2, r0)
            java.lang.String r0 = "clickedViewTag"
            android.view.View r2 = r6.r
            if (r2 == 0) goto L_0x00fb
            android.view.View r3 = r6.s
            if (r3 != 0) goto L_0x00d7
            goto L_0x00fb
        L_0x00d7:
            if (r2 == r3) goto L_0x00dc
            com.facebook.ads.internal.w.b.j r2 = com.facebook.ads.internal.w.b.j.INTERNAL_NO_CLICK
            goto L_0x00fd
        L_0x00dc:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 4
            if (r2 >= r3) goto L_0x00e4
            com.facebook.ads.internal.w.b.j r2 = com.facebook.ads.internal.w.b.j.INTERNAL_API_TOO_LOW
            goto L_0x00fd
        L_0x00e4:
            android.view.View r2 = r6.r
            int r3 = com.facebook.ads.internal.w.b.j.p
            java.lang.Object r2 = r2.getTag(r3)
            if (r2 != 0) goto L_0x00f1
            com.facebook.ads.internal.w.b.j r2 = com.facebook.ads.internal.w.b.j.INTERNAL_NO_TAG
            goto L_0x00fd
        L_0x00f1:
            boolean r3 = r2 instanceof com.facebook.ads.internal.w.b.j
            if (r3 != 0) goto L_0x00f8
            com.facebook.ads.internal.w.b.j r2 = com.facebook.ads.internal.w.b.j.INTERNAL_WRONG_TAG_CLASS
            goto L_0x00fd
        L_0x00f8:
            com.facebook.ads.internal.w.b.j r2 = (com.facebook.ads.internal.w.b.j) r2
            goto L_0x00fd
        L_0x00fb:
            com.facebook.ads.internal.w.b.j r2 = com.facebook.ads.internal.w.b.j.INTERNAL_NULL_VIEW
        L_0x00fd:
            int r2 = r2.a()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r1.put(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.w.b.w.e():java.util.Map");
    }
}
