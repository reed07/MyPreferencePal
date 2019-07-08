package com.shinobicontrols.charts;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.View;
import com.shinobicontrols.charts.GLSurfaceView.EGLConfigChooser;
import com.shinobicontrols.charts.GLSurfaceView.Renderer;

class by extends GLSurfaceView implements bw, cl {
    private final x a;
    private int b;
    private float c;

    public View a() {
        return this;
    }

    by(Context context, v vVar) {
        super(context);
        this.a = new x(vVar, false, getResources());
        b_(2);
        a((EGLConfigChooser) new z());
        setZOrderOnTop(false);
        a((Renderer) this.a);
        b(0);
        getHolder().setFormat(-3);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a.a();
        super.surfaceDestroyed(surfaceHolder);
    }

    public void setBackgroundColor(int i) {
        this.a.a(i);
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(float f) {
        this.c = f;
    }

    public void e() {
        d_();
    }

    public void f() {
        this.a.b();
    }

    public void b_() {
        this.a.c();
    }
}
