package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;
import java.nio.IntBuffer;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class x implements ak {
    static final Object a = new Object();
    private ah b = new ah(0);
    private final v c;
    private final AnimationManager d = new AnimationManager();
    private SChartGLDrawer e = null;
    private final SChartGLErrorHandler f = new SChartGLErrorHandler();
    private final boolean g;
    private final Resources h;
    private boolean i = false;
    private boolean j;
    private int k;
    private int l;
    private final ai m = new ai();

    x(v vVar, boolean z, Resources resources) {
        this.c = vVar;
        this.g = z;
        this.h = resources;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        synchronized (a) {
            if (this.e != null) {
                this.e.a();
            }
            this.e = new SChartGLDrawer(this.g, this.f);
            this.i = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        synchronized (a) {
            if (this.e != null) {
                this.e.a();
                this.e = null;
            }
            this.m.e();
        }
    }

    @SuppressLint({"WrongCall"})
    public void onDrawFrame(GL10 gl10) {
        synchronized (a) {
            try {
                List<Series<?>> list = this.c.c;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    Series series = (Series) list.get(i2);
                    if (!this.i) {
                        this.i = series.p.b();
                    }
                }
                this.e.setPerformCalculations(this.i);
                if (this.i) {
                    for (cr a2 : this.c.d.a()) {
                        a2.a();
                    }
                }
                this.i = false;
                this.e.beginRender(true);
                for (int i3 = 0; i3 < list.size(); i3++) {
                    Series series2 = (Series) list.get(i3);
                    if (!series2.y) {
                        series2.p.a(this.m, this.e);
                    }
                    series2.p.c();
                }
                GLES20.glClearColor(this.b.b, this.b.c, this.b.d, this.b.e);
                GLES20.glClear(17664);
                this.e.endRender(this.d);
                if (this.j) {
                    this.c.a(a(this.k, this.l, gl10));
                    this.j = false;
                }
            } catch (RuntimeException e2) {
                cx.c(this.c.getContext().getString(R.string.ChartRendererExceptionInGL));
                throw e2;
            }
        }
    }

    private static Bitmap a(int i2, int i3, GL10 gl10) {
        int i4 = i2 * i3;
        int[] iArr = new int[i4];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        gl10.glReadPixels(0, 0, i2, i3, 6408, 5121, wrap);
        int[] iArr2 = new int[i4];
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = iArr[(i5 * i2) + i6];
                iArr2[(((i3 - i5) - 1) * i2) + i6] = (i7 & -16711936) | ((i7 << 16) & 16711680) | ((i7 >> 16) & 255);
            }
        }
        return Bitmap.createBitmap(iArr2, i2, i3, Config.ARGB_8888);
    }

    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        synchronized (a) {
            this.k = i2;
            this.l = i3;
            GLES20.glViewport(0, 0, i2, i3);
            this.m.a(2.0f / ((float) i2), 2.0f / ((float) i3), this.h.getDisplayMetrics());
            this.e.setFrameBufferSize(i2, i3);
            this.i = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        synchronized (a) {
            this.b = new ah(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.i = true;
    }

    public void c() {
        this.j = true;
    }
}
