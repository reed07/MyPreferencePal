package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.View;
import com.shinobicontrols.charts.GLTextureView.EGLConfigChooser;
import com.shinobicontrols.charts.GLTextureView.Renderer;

class bx extends GLTextureView implements bw, cl {
    private final x a;
    private int b;
    private float c;

    public View a() {
        return this;
    }

    public void setBackgroundColor(int i) {
    }

    @SuppressLint({"NewApi"})
    bx(Context context, v vVar) {
        super(context);
        this.a = new x(vVar, false, getResources());
        a_(2);
        a((EGLConfigChooser) new z());
        a((Renderer) this.a);
        b(0);
        setOpaque(false);
        this.a.a(0);
    }

    public void b(SurfaceTexture surfaceTexture) {
        this.a.a();
        super.b(surfaceTexture);
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(float f) {
        this.c = f;
    }

    public void e() {
        c_();
    }

    public void f() {
        this.a.b();
    }

    public void b_() {
        this.a.c();
    }
}
