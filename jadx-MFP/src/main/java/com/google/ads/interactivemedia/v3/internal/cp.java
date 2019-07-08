package com.google.ads.interactivemedia.v3.internal;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.TextureView.SurfaceTextureListener;
import java.util.Iterator;

/* compiled from: IMASDK */
final class cp implements Callback, SurfaceTextureListener, dh, dl, vu {
    private final /* synthetic */ cn a;

    private cp(cn cnVar) {
        this.a = cnVar;
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public final void a(ew ewVar) {
        Iterator it = this.a.i.iterator();
        while (it.hasNext()) {
            ((vu) it.next()).a(ewVar);
        }
    }

    public final void a(String str, long j, long j2) {
        Iterator it = this.a.i.iterator();
        while (it.hasNext()) {
            ((vu) it.next()).a(str, j, j2);
        }
    }

    public final void a(bs bsVar) {
        Iterator it = this.a.i.iterator();
        while (it.hasNext()) {
            ((vu) it.next()).a(bsVar);
        }
    }

    public final void a(int i, long j) {
        Iterator it = this.a.i.iterator();
        while (it.hasNext()) {
            ((vu) it.next()).a(i, j);
        }
    }

    public final void a(int i, int i2, int i3, float f) {
        Iterator it = this.a.f.iterator();
        while (it.hasNext()) {
            vt vtVar = (vt) it.next();
            if (!this.a.i.contains(vtVar)) {
                vtVar.a(i, i2, i3, f);
            }
        }
        Iterator it2 = this.a.i.iterator();
        while (it2.hasNext()) {
            ((vu) it2.next()).a(i, i2, i3, f);
        }
    }

    public final void a(Surface surface) {
        if (this.a.n == surface) {
            Iterator it = this.a.f.iterator();
            while (it.hasNext()) {
                ((vt) it.next()).d();
            }
        }
        Iterator it2 = this.a.i.iterator();
        while (it2.hasNext()) {
            ((vu) it2.next()).a(surface);
        }
    }

    public final void b(ew ewVar) {
        Iterator it = this.a.i.iterator();
        while (it.hasNext()) {
            ((vu) it.next()).b(ewVar);
        }
    }

    public final void c(ew ewVar) {
        Iterator it = this.a.j.iterator();
        while (it.hasNext()) {
            ((dl) it.next()).c(ewVar);
        }
    }

    public final void a(int i) {
        if (this.a.s != i) {
            this.a.s = i;
            Iterator it = this.a.g.iterator();
            while (it.hasNext()) {
                di diVar = (di) it.next();
                if (!this.a.j.contains(diVar)) {
                    diVar.a(i);
                }
            }
            Iterator it2 = this.a.j.iterator();
            while (it2.hasNext()) {
                ((dl) it2.next()).a(i);
            }
        }
    }

    public final void b(String str, long j, long j2) {
        Iterator it = this.a.j.iterator();
        while (it.hasNext()) {
            ((dl) it.next()).b(str, j, j2);
        }
    }

    public final void b(bs bsVar) {
        Iterator it = this.a.j.iterator();
        while (it.hasNext()) {
            ((dl) it.next()).b(bsVar);
        }
    }

    public final void a(int i, long j, long j2) {
        Iterator it = this.a.j.iterator();
        while (it.hasNext()) {
            ((dl) it.next()).a(i, j, j2);
        }
    }

    public final void d(ew ewVar) {
        Iterator it = this.a.j.iterator();
        while (it.hasNext()) {
            ((dl) it.next()).d(ewVar);
        }
        this.a.s = 0;
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a.a(surfaceHolder.getSurface(), false);
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.a.a(i2, i3);
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a.a((Surface) null, false);
        this.a.a(0, 0);
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.a.a(new Surface(surfaceTexture), true);
        this.a.a(i, i2);
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.a.a(i, i2);
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.a.a((Surface) null, true);
        this.a.a(0, 0);
        return true;
    }

    public final void a() {
        this.a.m();
    }

    public final void b(int i) {
        cn cnVar = this.a;
        cnVar.a(cnVar.b(), i);
    }

    /* synthetic */ cp(cn cnVar, byte b) {
        this(cnVar);
    }
}
