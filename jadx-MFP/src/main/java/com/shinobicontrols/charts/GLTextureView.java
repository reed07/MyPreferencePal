package com.shinobicontrols.charts;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(14)
class GLTextureView extends TextureView implements SurfaceTextureListener {
    /* access modifiers changed from: private */
    public static final g a = new g();
    private final WeakReference<GLTextureView> b = new WeakReference<>(this);
    private f c;
    /* access modifiers changed from: private */
    public Renderer d;
    private boolean e;
    /* access modifiers changed from: private */
    public EGLConfigChooser f;
    /* access modifiers changed from: private */
    public EGLContextFactory g;
    /* access modifiers changed from: private */
    public EGLWindowSurfaceFactory h;
    /* access modifiers changed from: private */
    public GLWrapper i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public boolean l;

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface EGLContextFactory {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface EGLWindowSurfaceFactory {
        EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public interface GLWrapper {
        GL wrap(GL gl);
    }

    public interface Renderer {
        void onDrawFrame(GL10 gl10);

        void onSurfaceChanged(GL10 gl10, int i, int i2);

        void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig);
    }

    private abstract class a implements EGLConfigChooser {
        protected int[] a;

        /* access modifiers changed from: 0000 */
        public abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public a(int[] iArr) {
            this.a = a(iArr);
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.a, null, 0, iArr)) {
                int i = iArr[0];
                if (i > 0) {
                    EGLConfig[] eGLConfigArr = new EGLConfig[i];
                    if (egl10.eglChooseConfig(eGLDisplay, this.a, eGLConfigArr, i, iArr)) {
                        EGLConfig a2 = a(egl10, eGLDisplay, eGLConfigArr);
                        if (a2 != null) {
                            return a2;
                        }
                        throw new IllegalArgumentException("No config chosen");
                    }
                    throw new IllegalArgumentException("eglChooseConfig#2 failed");
                }
                throw new IllegalArgumentException("No configs match configSpec");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        private int[] a(int[] iArr) {
            if (GLTextureView.this.k != 2) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[(length + 2)];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            iArr2[length] = 4;
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    private class b extends a {
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        private final int[] j = new int[1];

        public b(int i2, int i3, int i4, int i5, int i6, int i7) {
            super(new int[]{12324, i2, 12323, i3, 12322, i4, 12321, i5, 12325, i6, 12326, i7, 12344});
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
            this.h = i7;
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                EGL10 egl102 = egl10;
                EGLDisplay eGLDisplay2 = eGLDisplay;
                EGLConfig eGLConfig2 = eGLConfig;
                int a = a(egl102, eGLDisplay2, eGLConfig2, 12325, 0);
                int a2 = a(egl102, eGLDisplay2, eGLConfig2, 12326, 0);
                if (a >= this.g && a2 >= this.h) {
                    EGL10 egl103 = egl10;
                    EGLDisplay eGLDisplay3 = eGLDisplay;
                    EGLConfig eGLConfig3 = eGLConfig;
                    int a3 = a(egl103, eGLDisplay3, eGLConfig3, 12324, 0);
                    int a4 = a(egl103, eGLDisplay3, eGLConfig3, 12323, 0);
                    int a5 = a(egl103, eGLDisplay3, eGLConfig3, 12322, 0);
                    int a6 = a(egl103, eGLDisplay3, eGLConfig3, 12321, 0);
                    if (a3 == this.c && a4 == this.d && a5 == this.e && a6 == this.f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i2, int i3) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, this.j) ? this.j[0] : i3;
        }
    }

    private class c implements EGLContextFactory {
        private final int b;

        private c() {
            this.b = 12440;
        }

        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {12440, GLTextureView.this.k, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (GLTextureView.this.k == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                StringBuilder sb = new StringBuilder();
                sb.append("display:");
                sb.append(eGLDisplay);
                sb.append(" context: ");
                sb.append(eGLContext);
                Log.e("DefaultContextFactory", sb.toString());
                e.a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    private static class d implements EGLWindowSurfaceFactory {
        private d() {
        }

        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e("GLTextureView", "eglCreateWindowSurface", e);
                return null;
            }
        }

        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    private static class e {
        EGL10 a;
        EGLDisplay b;
        EGLSurface c;
        EGLConfig d;
        EGLContext e;
        private final WeakReference<GLTextureView> f;

        public e(WeakReference<GLTextureView> weakReference) {
            this.f = weakReference;
        }

        public void a() {
            this.a = (EGL10) EGLContext.getEGL();
            this.b = this.a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.b != EGL10.EGL_NO_DISPLAY) {
                if (this.a.eglInitialize(this.b, new int[2])) {
                    GLTextureView gLTextureView = (GLTextureView) this.f.get();
                    if (gLTextureView == null) {
                        this.d = null;
                        this.e = null;
                    } else {
                        this.d = gLTextureView.f.chooseConfig(this.a, this.b);
                        this.e = gLTextureView.g.createContext(this.a, this.b, this.d);
                    }
                    EGLContext eGLContext = this.e;
                    if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                        this.e = null;
                        a("createContext");
                    }
                    this.c = null;
                    return;
                }
                throw new RuntimeException("eglInitialize failed");
            }
            throw new RuntimeException("eglGetDisplay failed");
        }

        public boolean b() {
            if (this.a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.d != null) {
                g();
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView != null) {
                    this.c = gLTextureView.h.createWindowSurface(this.a, this.b, this.d, gLTextureView.getSurfaceTexture());
                } else {
                    this.c = null;
                }
                EGLSurface eGLSurface = this.c;
                if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                    if (this.a.eglGetError() == 12299) {
                        Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                    }
                    return false;
                }
                EGL10 egl10 = this.a;
                EGLDisplay eGLDisplay = this.b;
                EGLSurface eGLSurface2 = this.c;
                if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.e)) {
                    return true;
                }
                a("EGLHelper", "eglMakeCurrent", this.a.eglGetError());
                return false;
            } else {
                throw new RuntimeException("mEglConfig not initialized");
            }
        }

        /* access modifiers changed from: 0000 */
        public GL c() {
            GL gl = this.e.getGL();
            GLTextureView gLTextureView = (GLTextureView) this.f.get();
            if (gLTextureView == null) {
                return gl;
            }
            if (gLTextureView.i != null) {
                gl = gLTextureView.i.wrap(gl);
            }
            if ((gLTextureView.j & 3) == 0) {
                return gl;
            }
            int i = 0;
            h hVar = null;
            if ((gLTextureView.j & 1) != 0) {
                i = 1;
            }
            if ((gLTextureView.j & 2) != 0) {
                hVar = new h();
            }
            return GLDebugHelper.wrap(gl, i, hVar);
        }

        public int d() {
            if (!this.a.eglSwapBuffers(this.b, this.c)) {
                return this.a.eglGetError();
            }
            return 12288;
        }

        public void e() {
            g();
        }

        private void g() {
            EGLSurface eGLSurface = this.c;
            if (eGLSurface != null && eGLSurface != EGL10.EGL_NO_SURFACE) {
                this.a.eglMakeCurrent(this.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView != null) {
                    gLTextureView.h.destroySurface(this.a, this.b, this.c);
                }
                this.c = null;
            }
        }

        public void f() {
            if (this.e != null) {
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView != null) {
                    gLTextureView.g.destroyContext(this.a, this.b, this.e);
                }
                this.e = null;
            }
            EGLDisplay eGLDisplay = this.b;
            if (eGLDisplay != null) {
                this.a.eglTerminate(eGLDisplay);
                this.b = null;
            }
        }

        private void a(String str) {
            a(str, this.a.eglGetError());
        }

        public static void a(String str, int i) {
            throw new RuntimeException(b(str, i));
        }

        public static void a(String str, String str2, int i) {
            Log.w(str, b(str2, i));
        }

        public static String b(String str, int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" failed: ");
            return sb.toString();
        }
    }

    static class f extends Thread {
        private boolean a;
        /* access modifiers changed from: private */
        public boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private int l = 0;
        private int m = 0;
        private int n = 1;
        private boolean o = true;
        private boolean p;
        private final ArrayList<Runnable> q = new ArrayList<>();
        private boolean r = true;
        private e s;
        private final WeakReference<GLTextureView> t;

        f(WeakReference<GLTextureView> weakReference) {
            this.t = weakReference;
        }

        public void run() {
            StringBuilder sb = new StringBuilder();
            sb.append("GLThread ");
            sb.append(getId());
            setName(sb.toString());
            try {
                l();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                GLTextureView.a.a(this);
                throw th;
            }
            GLTextureView.a.a(this);
        }

        private void j() {
            if (this.i) {
                this.i = false;
                this.s.e();
            }
        }

        private void k() {
            if (this.h) {
                this.s.f();
                this.h = false;
                GLTextureView.a.c(this);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:100:0x0166, code lost:
            if (r1.s.b() == false) goto L_0x017d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:101:0x0168, code lost:
            r10 = com.shinobicontrols.charts.GLTextureView.d();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x016c, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
            r1.j = true;
            com.shinobicontrols.charts.GLTextureView.d().notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x0177, code lost:
            monitor-exit(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x0178, code lost:
            r10 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x017d, code lost:
            r15 = com.shinobicontrols.charts.GLTextureView.d();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x0181, code lost:
            monitor-enter(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
            r1.j = true;
            r1.f = true;
            com.shinobicontrols.charts.GLTextureView.d().notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x018e, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:123:0x0195, code lost:
            if (r11 == false) goto L_0x01a8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x0197, code lost:
            r0 = (javax.microedition.khronos.opengles.GL10) r1.s.c();
            com.shinobicontrols.charts.GLTextureView.d().a(r0);
            r6 = r0;
            r11 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:125:0x01a8, code lost:
            if (r9 == false) goto L_0x01c0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:126:0x01aa, code lost:
            r0 = (com.shinobicontrols.charts.GLTextureView) r1.t.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:0x01b2, code lost:
            if (r0 == null) goto L_0x01bf;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x01b4, code lost:
            com.shinobicontrols.charts.GLTextureView.h(r0).onSurfaceCreated(r6, r1.s.d);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:129:0x01bf, code lost:
            r9 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:130:0x01c0, code lost:
            if (r12 == false) goto L_0x01d4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:131:0x01c2, code lost:
            r0 = (com.shinobicontrols.charts.GLTextureView) r1.t.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:132:0x01ca, code lost:
            if (r0 == null) goto L_0x01d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x01cc, code lost:
            com.shinobicontrols.charts.GLTextureView.h(r0).onSurfaceChanged(r6, r7, r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x01d3, code lost:
            r12 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x01d4, code lost:
            r0 = (com.shinobicontrols.charts.GLTextureView) r1.t.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x01dc, code lost:
            if (r0 == null) goto L_0x01e5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:137:0x01de, code lost:
            com.shinobicontrols.charts.GLTextureView.h(r0).onDrawFrame(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:138:0x01e5, code lost:
            r0 = r1.s.d();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:139:0x01ed, code lost:
            if (r0 == 12288) goto L_0x0211;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x01f1, code lost:
            if (r0 == 12302) goto L_0x020e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:142:0x01f3, code lost:
            com.shinobicontrols.charts.GLTextureView.e.a("GLThread", "eglSwapBuffers", r0);
            r2 = com.shinobicontrols.charts.GLTextureView.d();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:143:0x01fe, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:146:?, code lost:
            r1.f = true;
            com.shinobicontrols.charts.GLTextureView.d().notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x0209, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x020e, code lost:
            r3 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x0212, code lost:
            if (r13 == false) goto L_0x0215;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:156:0x0214, code lost:
            r4 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x0155, code lost:
            if (r14 == null) goto L_0x015e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
            r14.run();
            r14 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x015e, code lost:
            if (r10 == false) goto L_0x0195;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void l() throws java.lang.InterruptedException {
            /*
                r16 = this;
                r1 = r16
                com.shinobicontrols.charts.GLTextureView$e r0 = new com.shinobicontrols.charts.GLTextureView$e
                java.lang.ref.WeakReference<com.shinobicontrols.charts.GLTextureView> r2 = r1.t
                r0.<init>(r2)
                r1.s = r0
                r0 = 0
                r1.h = r0
                r1.i = r0
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
            L_0x001c:
                com.shinobicontrols.charts.GLTextureView$g r15 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0226 }
                monitor-enter(r15)     // Catch:{ all -> 0x0226 }
            L_0x0021:
                boolean r2 = r1.a     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x0036
                monitor-exit(r15)     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r2)
                r16.j()     // Catch:{ all -> 0x0033 }
                r16.k()     // Catch:{ all -> 0x0033 }
                monitor-exit(r2)     // Catch:{ all -> 0x0033 }
                return
            L_0x0033:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0033 }
                throw r0
            L_0x0036:
                java.util.ArrayList<java.lang.Runnable> r2 = r1.q     // Catch:{ all -> 0x0223 }
                boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0223 }
                if (r2 != 0) goto L_0x004b
                java.util.ArrayList<java.lang.Runnable> r2 = r1.q     // Catch:{ all -> 0x0223 }
                r14 = 0
                java.lang.Object r2 = r2.remove(r14)     // Catch:{ all -> 0x0223 }
                java.lang.Runnable r2 = (java.lang.Runnable) r2     // Catch:{ all -> 0x0223 }
                r14 = r2
                r2 = 0
                goto L_0x0154
            L_0x004b:
                boolean r2 = r1.d     // Catch:{ all -> 0x0223 }
                boolean r0 = r1.c     // Catch:{ all -> 0x0223 }
                if (r2 == r0) goto L_0x005f
                boolean r0 = r1.c     // Catch:{ all -> 0x0223 }
                boolean r2 = r1.c     // Catch:{ all -> 0x0223 }
                r1.d = r2     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r2.notifyAll()     // Catch:{ all -> 0x0223 }
                goto L_0x0060
            L_0x005f:
                r0 = 0
            L_0x0060:
                boolean r2 = r1.k     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x006e
                r16.j()     // Catch:{ all -> 0x0223 }
                r16.k()     // Catch:{ all -> 0x0223 }
                r2 = 0
                r1.k = r2     // Catch:{ all -> 0x0223 }
                r5 = 1
            L_0x006e:
                if (r3 == 0) goto L_0x0077
                r16.j()     // Catch:{ all -> 0x0223 }
                r16.k()     // Catch:{ all -> 0x0223 }
                r3 = 0
            L_0x0077:
                if (r0 == 0) goto L_0x0080
                boolean r2 = r1.i     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x0080
                r16.j()     // Catch:{ all -> 0x0223 }
            L_0x0080:
                if (r0 == 0) goto L_0x00a5
                boolean r2 = r1.h     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x00a5
                java.lang.ref.WeakReference<com.shinobicontrols.charts.GLTextureView> r2 = r1.t     // Catch:{ all -> 0x0223 }
                java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView r2 = (com.shinobicontrols.charts.GLTextureView) r2     // Catch:{ all -> 0x0223 }
                if (r2 != 0) goto L_0x0092
                r2 = 0
                goto L_0x0096
            L_0x0092:
                boolean r2 = r2.l     // Catch:{ all -> 0x0223 }
            L_0x0096:
                if (r2 == 0) goto L_0x00a2
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                boolean r2 = r2.a()     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x00a5
            L_0x00a2:
                r16.k()     // Catch:{ all -> 0x0223 }
            L_0x00a5:
                if (r0 == 0) goto L_0x00b6
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                boolean r0 = r0.b()     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x00b6
                com.shinobicontrols.charts.GLTextureView$e r0 = r1.s     // Catch:{ all -> 0x0223 }
                r0.f()     // Catch:{ all -> 0x0223 }
            L_0x00b6:
                boolean r0 = r1.e     // Catch:{ all -> 0x0223 }
                if (r0 != 0) goto L_0x00d4
                boolean r0 = r1.g     // Catch:{ all -> 0x0223 }
                if (r0 != 0) goto L_0x00d4
                boolean r0 = r1.i     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x00c7
                r16.j()     // Catch:{ all -> 0x0223 }
                r0 = 1
                goto L_0x00c8
            L_0x00c7:
                r0 = 1
            L_0x00c8:
                r1.g = r0     // Catch:{ all -> 0x0223 }
                r0 = 0
                r1.f = r0     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r0.notifyAll()     // Catch:{ all -> 0x0223 }
            L_0x00d4:
                boolean r0 = r1.e     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x00e6
                boolean r0 = r1.g     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x00e6
                r0 = 0
                r1.g = r0     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r0.notifyAll()     // Catch:{ all -> 0x0223 }
            L_0x00e6:
                if (r4 == 0) goto L_0x00f4
                r0 = 1
                r1.p = r0     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r0.notifyAll()     // Catch:{ all -> 0x0223 }
                r4 = 0
                r13 = 0
            L_0x00f4:
                boolean r0 = r16.m()     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x0219
                boolean r0 = r1.h     // Catch:{ all -> 0x0223 }
                if (r0 != 0) goto L_0x0126
                if (r5 == 0) goto L_0x0102
                r5 = 0
                goto L_0x0126
            L_0x0102:
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                boolean r0 = r0.b(r1)     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x0126
                com.shinobicontrols.charts.GLTextureView$e r0 = r1.s     // Catch:{ RuntimeException -> 0x011d }
                r0.a()     // Catch:{ RuntimeException -> 0x011d }
                r0 = 1
                r1.h = r0     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r0.notifyAll()     // Catch:{ all -> 0x0223 }
                r9 = 1
                goto L_0x0126
            L_0x011d:
                r0 = move-exception
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r2.c(r1)     // Catch:{ all -> 0x0223 }
                throw r0     // Catch:{ all -> 0x0223 }
            L_0x0126:
                boolean r0 = r1.h     // Catch:{ all -> 0x0223 }
                if (r0 == 0) goto L_0x0135
                boolean r0 = r1.i     // Catch:{ all -> 0x0223 }
                if (r0 != 0) goto L_0x0135
                r0 = 1
                r1.i = r0     // Catch:{ all -> 0x0223 }
                r0 = 1
                r11 = 1
                r12 = 1
                goto L_0x0136
            L_0x0135:
                r0 = r10
            L_0x0136:
                boolean r2 = r1.i     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x0218
                boolean r2 = r1.r     // Catch:{ all -> 0x0223 }
                if (r2 == 0) goto L_0x0149
                int r7 = r1.l     // Catch:{ all -> 0x0223 }
                int r8 = r1.m     // Catch:{ all -> 0x0223 }
                r2 = 0
                r1.r = r2     // Catch:{ all -> 0x0223 }
                r0 = 1
                r12 = 1
                r13 = 1
                goto L_0x014a
            L_0x0149:
                r2 = 0
            L_0x014a:
                r1.o = r2     // Catch:{ all -> 0x0223 }
                com.shinobicontrols.charts.GLTextureView$g r10 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r10.notifyAll()     // Catch:{ all -> 0x0223 }
                r10 = r0
            L_0x0154:
                monitor-exit(r15)     // Catch:{ all -> 0x0223 }
                if (r14 == 0) goto L_0x015e
                r14.run()     // Catch:{ all -> 0x0226 }
                r0 = 0
                r14 = 0
                goto L_0x001c
            L_0x015e:
                if (r10 == 0) goto L_0x0195
                com.shinobicontrols.charts.GLTextureView$e r0 = r1.s     // Catch:{ all -> 0x0226 }
                boolean r0 = r0.b()     // Catch:{ all -> 0x0226 }
                if (r0 == 0) goto L_0x017d
                com.shinobicontrols.charts.GLTextureView$g r10 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0226 }
                monitor-enter(r10)     // Catch:{ all -> 0x0226 }
                r0 = 1
                r1.j = r0     // Catch:{ all -> 0x017a }
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x017a }
                r0.notifyAll()     // Catch:{ all -> 0x017a }
                monitor-exit(r10)     // Catch:{ all -> 0x017a }
                r10 = 0
                goto L_0x0195
            L_0x017a:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x017a }
                throw r0     // Catch:{ all -> 0x0226 }
            L_0x017d:
                com.shinobicontrols.charts.GLTextureView$g r15 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0226 }
                monitor-enter(r15)     // Catch:{ all -> 0x0226 }
                r0 = 1
                r1.j = r0     // Catch:{ all -> 0x0192 }
                r1.f = r0     // Catch:{ all -> 0x0192 }
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0192 }
                r0.notifyAll()     // Catch:{ all -> 0x0192 }
                monitor-exit(r15)     // Catch:{ all -> 0x0192 }
                r0 = 0
                goto L_0x001c
            L_0x0192:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x0192 }
                throw r0     // Catch:{ all -> 0x0226 }
            L_0x0195:
                if (r11 == 0) goto L_0x01a8
                com.shinobicontrols.charts.GLTextureView$e r0 = r1.s     // Catch:{ all -> 0x0226 }
                javax.microedition.khronos.opengles.GL r0 = r0.c()     // Catch:{ all -> 0x0226 }
                javax.microedition.khronos.opengles.GL10 r0 = (javax.microedition.khronos.opengles.GL10) r0     // Catch:{ all -> 0x0226 }
                com.shinobicontrols.charts.GLTextureView$g r6 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0226 }
                r6.a(r0)     // Catch:{ all -> 0x0226 }
                r6 = r0
                r11 = 0
            L_0x01a8:
                if (r9 == 0) goto L_0x01c0
                java.lang.ref.WeakReference<com.shinobicontrols.charts.GLTextureView> r0 = r1.t     // Catch:{ all -> 0x0226 }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0226 }
                com.shinobicontrols.charts.GLTextureView r0 = (com.shinobicontrols.charts.GLTextureView) r0     // Catch:{ all -> 0x0226 }
                if (r0 == 0) goto L_0x01bf
                com.shinobicontrols.charts.GLTextureView$Renderer r0 = r0.d     // Catch:{ all -> 0x0226 }
                com.shinobicontrols.charts.GLTextureView$e r9 = r1.s     // Catch:{ all -> 0x0226 }
                javax.microedition.khronos.egl.EGLConfig r9 = r9.d     // Catch:{ all -> 0x0226 }
                r0.onSurfaceCreated(r6, r9)     // Catch:{ all -> 0x0226 }
            L_0x01bf:
                r9 = 0
            L_0x01c0:
                if (r12 == 0) goto L_0x01d4
                java.lang.ref.WeakReference<com.shinobicontrols.charts.GLTextureView> r0 = r1.t     // Catch:{ all -> 0x0226 }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0226 }
                com.shinobicontrols.charts.GLTextureView r0 = (com.shinobicontrols.charts.GLTextureView) r0     // Catch:{ all -> 0x0226 }
                if (r0 == 0) goto L_0x01d3
                com.shinobicontrols.charts.GLTextureView$Renderer r0 = r0.d     // Catch:{ all -> 0x0226 }
                r0.onSurfaceChanged(r6, r7, r8)     // Catch:{ all -> 0x0226 }
            L_0x01d3:
                r12 = 0
            L_0x01d4:
                java.lang.ref.WeakReference<com.shinobicontrols.charts.GLTextureView> r0 = r1.t     // Catch:{ all -> 0x0226 }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0226 }
                com.shinobicontrols.charts.GLTextureView r0 = (com.shinobicontrols.charts.GLTextureView) r0     // Catch:{ all -> 0x0226 }
                if (r0 == 0) goto L_0x01e5
                com.shinobicontrols.charts.GLTextureView$Renderer r0 = r0.d     // Catch:{ all -> 0x0226 }
                r0.onDrawFrame(r6)     // Catch:{ all -> 0x0226 }
            L_0x01e5:
                com.shinobicontrols.charts.GLTextureView$e r0 = r1.s     // Catch:{ all -> 0x0226 }
                int r0 = r0.d()     // Catch:{ all -> 0x0226 }
                r15 = 12288(0x3000, float:1.7219E-41)
                if (r0 == r15) goto L_0x0211
                r15 = 12302(0x300e, float:1.7239E-41)
                if (r0 == r15) goto L_0x020e
                java.lang.String r15 = "GLThread"
                java.lang.String r2 = "eglSwapBuffers"
                com.shinobicontrols.charts.GLTextureView.e.a(r15, r2, r0)     // Catch:{ all -> 0x0226 }
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0226 }
                monitor-enter(r2)     // Catch:{ all -> 0x0226 }
                r0 = 1
                r1.f = r0     // Catch:{ all -> 0x020b }
                com.shinobicontrols.charts.GLTextureView$g r15 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x020b }
                r15.notifyAll()     // Catch:{ all -> 0x020b }
                monitor-exit(r2)     // Catch:{ all -> 0x020b }
                goto L_0x0212
            L_0x020b:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x020b }
                throw r0     // Catch:{ all -> 0x0226 }
            L_0x020e:
                r0 = 1
                r3 = 1
                goto L_0x0212
            L_0x0211:
                r0 = 1
            L_0x0212:
                if (r13 == 0) goto L_0x0215
                r4 = 1
            L_0x0215:
                r0 = 0
                goto L_0x001c
            L_0x0218:
                r10 = r0
            L_0x0219:
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0223 }
                r0.wait()     // Catch:{ all -> 0x0223 }
                r0 = 0
                goto L_0x0021
            L_0x0223:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x0223 }
                throw r0     // Catch:{ all -> 0x0226 }
            L_0x0226:
                r0 = move-exception
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r2)
                r16.j()     // Catch:{ all -> 0x0234 }
                r16.k()     // Catch:{ all -> 0x0234 }
                monitor-exit(r2)     // Catch:{ all -> 0x0234 }
                throw r0
            L_0x0234:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0234 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.l():void");
        }

        public boolean a() {
            return this.h && this.i && m();
        }

        private boolean m() {
            return !this.d && this.e && !this.f && this.l > 0 && this.m > 0 && (this.o || this.n == 1);
        }

        public void a(int i2) {
            if (i2 < 0 || i2 > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (GLTextureView.a) {
                this.n = i2;
                GLTextureView.a.notifyAll();
            }
        }

        public int b() {
            int i2;
            synchronized (GLTextureView.a) {
                i2 = this.n;
            }
            return i2;
        }

        public void c() {
            synchronized (GLTextureView.a) {
                this.o = true;
                GLTextureView.a.notifyAll();
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|14|25|20|5) */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0012, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0026 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void d() {
            /*
                r2 = this;
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r0)
                r1 = 1
                r2.e = r1     // Catch:{ all -> 0x0030 }
                r1 = 0
                r2.j = r1     // Catch:{ all -> 0x0030 }
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0030 }
                r1.notifyAll()     // Catch:{ all -> 0x0030 }
            L_0x0012:
                boolean r1 = r2.g     // Catch:{ all -> 0x0030 }
                if (r1 == 0) goto L_0x002e
                boolean r1 = r2.j     // Catch:{ all -> 0x0030 }
                if (r1 != 0) goto L_0x002e
                boolean r1 = r2.b     // Catch:{ all -> 0x0030 }
                if (r1 != 0) goto L_0x002e
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ InterruptedException -> 0x0026 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0026 }
                goto L_0x0012
            L_0x0026:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0030 }
                r1.interrupt()     // Catch:{ all -> 0x0030 }
                goto L_0x0012
            L_0x002e:
                monitor-exit(r0)     // Catch:{ all -> 0x0030 }
                return
            L_0x0030:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0030 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.d():void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:9|10|11|12|22|18|5) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x000f, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001f */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void e() {
            /*
                r2 = this;
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r0)
                r1 = 0
                r2.e = r1     // Catch:{ all -> 0x0029 }
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0029 }
                r1.notifyAll()     // Catch:{ all -> 0x0029 }
            L_0x000f:
                boolean r1 = r2.g     // Catch:{ all -> 0x0029 }
                if (r1 != 0) goto L_0x0027
                boolean r1 = r2.b     // Catch:{ all -> 0x0029 }
                if (r1 != 0) goto L_0x0027
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ InterruptedException -> 0x001f }
                r1.wait()     // Catch:{ InterruptedException -> 0x001f }
                goto L_0x000f
            L_0x001f:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0029 }
                r1.interrupt()     // Catch:{ all -> 0x0029 }
                goto L_0x000f
            L_0x0027:
                monitor-exit(r0)     // Catch:{ all -> 0x0029 }
                return
            L_0x0029:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0029 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.e():void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:9|10|11|12|22|18|5) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x000f, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001f */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void f() {
            /*
                r2 = this;
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r0)
                r1 = 1
                r2.c = r1     // Catch:{ all -> 0x0029 }
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0029 }
                r1.notifyAll()     // Catch:{ all -> 0x0029 }
            L_0x000f:
                boolean r1 = r2.b     // Catch:{ all -> 0x0029 }
                if (r1 != 0) goto L_0x0027
                boolean r1 = r2.d     // Catch:{ all -> 0x0029 }
                if (r1 != 0) goto L_0x0027
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ InterruptedException -> 0x001f }
                r1.wait()     // Catch:{ InterruptedException -> 0x001f }
                goto L_0x000f
            L_0x001f:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0029 }
                r1.interrupt()     // Catch:{ all -> 0x0029 }
                goto L_0x000f
            L_0x0027:
                monitor-exit(r0)     // Catch:{ all -> 0x0029 }
                return
            L_0x0029:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0029 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.f():void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|14|25|20|5) */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0014, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0028 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void g() {
            /*
                r3 = this;
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r0)
                r1 = 0
                r3.c = r1     // Catch:{ all -> 0x0032 }
                r2 = 1
                r3.o = r2     // Catch:{ all -> 0x0032 }
                r3.p = r1     // Catch:{ all -> 0x0032 }
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0032 }
                r1.notifyAll()     // Catch:{ all -> 0x0032 }
            L_0x0014:
                boolean r1 = r3.b     // Catch:{ all -> 0x0032 }
                if (r1 != 0) goto L_0x0030
                boolean r1 = r3.d     // Catch:{ all -> 0x0032 }
                if (r1 == 0) goto L_0x0030
                boolean r1 = r3.p     // Catch:{ all -> 0x0032 }
                if (r1 != 0) goto L_0x0030
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ InterruptedException -> 0x0028 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0028 }
                goto L_0x0014
            L_0x0028:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0032 }
                r1.interrupt()     // Catch:{ all -> 0x0032 }
                goto L_0x0014
            L_0x0030:
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                return
            L_0x0032:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.g():void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:12|13|14|15|27|21|4) */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0018, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0032 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(int r2, int r3) {
            /*
                r1 = this;
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r0)
                r1.l = r2     // Catch:{ all -> 0x003c }
                r1.m = r3     // Catch:{ all -> 0x003c }
                r2 = 1
                r1.r = r2     // Catch:{ all -> 0x003c }
                r1.o = r2     // Catch:{ all -> 0x003c }
                r2 = 0
                r1.p = r2     // Catch:{ all -> 0x003c }
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x003c }
                r2.notifyAll()     // Catch:{ all -> 0x003c }
            L_0x0018:
                boolean r2 = r1.b     // Catch:{ all -> 0x003c }
                if (r2 != 0) goto L_0x003a
                boolean r2 = r1.d     // Catch:{ all -> 0x003c }
                if (r2 != 0) goto L_0x003a
                boolean r2 = r1.p     // Catch:{ all -> 0x003c }
                if (r2 != 0) goto L_0x003a
                boolean r2 = r1.a()     // Catch:{ all -> 0x003c }
                if (r2 == 0) goto L_0x003a
                com.shinobicontrols.charts.GLTextureView$g r2 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ InterruptedException -> 0x0032 }
                r2.wait()     // Catch:{ InterruptedException -> 0x0032 }
                goto L_0x0018
            L_0x0032:
                java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x003c }
                r2.interrupt()     // Catch:{ all -> 0x003c }
                goto L_0x0018
            L_0x003a:
                monitor-exit(r0)     // Catch:{ all -> 0x003c }
                return
            L_0x003c:
                r2 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x003c }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.a(int, int):void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:7|8|9|10|19|16|5) */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x000f, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void h() {
            /*
                r2 = this;
                com.shinobicontrols.charts.GLTextureView$g r0 = com.shinobicontrols.charts.GLTextureView.a
                monitor-enter(r0)
                r1 = 1
                r2.a = r1     // Catch:{ all -> 0x0025 }
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ all -> 0x0025 }
                r1.notifyAll()     // Catch:{ all -> 0x0025 }
            L_0x000f:
                boolean r1 = r2.b     // Catch:{ all -> 0x0025 }
                if (r1 != 0) goto L_0x0023
                com.shinobicontrols.charts.GLTextureView$g r1 = com.shinobicontrols.charts.GLTextureView.a     // Catch:{ InterruptedException -> 0x001b }
                r1.wait()     // Catch:{ InterruptedException -> 0x001b }
                goto L_0x000f
            L_0x001b:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0025 }
                r1.interrupt()     // Catch:{ all -> 0x0025 }
                goto L_0x000f
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                return
            L_0x0025:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.GLTextureView.f.h():void");
        }

        public void i() {
            this.k = true;
            GLTextureView.a.notifyAll();
        }
    }

    private static class g {
        private static String a = "GLThreadManager";
        private boolean b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private f g;

        private g() {
        }

        public synchronized void a(f fVar) {
            fVar.b = true;
            if (this.g == fVar) {
                this.g = null;
            }
            notifyAll();
        }

        public boolean b(f fVar) {
            f fVar2 = this.g;
            if (fVar2 == fVar || fVar2 == null) {
                this.g = fVar;
                notifyAll();
                return true;
            }
            c();
            if (this.e) {
                return true;
            }
            f fVar3 = this.g;
            if (fVar3 != null) {
                fVar3.i();
            }
            return false;
        }

        public void c(f fVar) {
            if (this.g == fVar) {
                this.g = null;
            }
            notifyAll();
        }

        public synchronized boolean a() {
            return this.f;
        }

        public synchronized boolean b() {
            c();
            return !this.e;
        }

        public synchronized void a(GL10 gl10) {
            if (!this.d) {
                c();
                String glGetString = gl10.glGetString(7937);
                boolean z = false;
                if (this.c < 131072) {
                    this.e = !glGetString.startsWith("Q3Dimension MSM7500 ");
                    notifyAll();
                }
                if (!this.e) {
                    z = true;
                }
                this.f = z;
                this.d = true;
            }
        }

        private void c() {
            if (!this.b) {
                this.b = true;
            }
        }
    }

    static class h extends Writer {
        private final StringBuilder a = new StringBuilder();

        h() {
        }

        public void close() {
            a();
        }

        public void flush() {
            a();
        }

        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == 10) {
                    a();
                } else {
                    this.a.append(c);
                }
            }
        }

        private void a() {
            if (this.a.length() > 0) {
                Log.v("GLSurfaceView", this.a.toString());
                StringBuilder sb = this.a;
                sb.delete(0, sb.length());
            }
        }
    }

    private class i extends b {
        public i(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public GLTextureView(Context context) {
        super(context);
        e();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.c != null) {
                this.c.h();
            }
        } finally {
            super.finalize();
        }
    }

    private void e() {
        setSurfaceTextureListener(this);
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                GLTextureView gLTextureView = GLTextureView.this;
                gLTextureView.a(gLTextureView.getSurfaceTexture(), 0, i3 - i, i4 - i2);
            }
        });
    }

    public void a(Renderer renderer) {
        f();
        if (this.f == null) {
            this.f = new i(true);
        }
        if (this.g == null) {
            this.g = new c();
        }
        if (this.h == null) {
            this.h = new d();
        }
        this.d = renderer;
        this.c = new f(this.b);
        this.c.start();
    }

    public void a(EGLConfigChooser eGLConfigChooser) {
        f();
        this.f = eGLConfigChooser;
    }

    public void a_(int i2) {
        f();
        this.k = i2;
    }

    public void b(int i2) {
        this.c.a(i2);
    }

    public void c_() {
        this.c.c();
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.c.d();
    }

    public void b(SurfaceTexture surfaceTexture) {
        this.c.e();
    }

    public void a(SurfaceTexture surfaceTexture, int i2, int i3, int i4) {
        this.c.a(i3, i4);
    }

    public void b() {
        this.c.f();
    }

    public void c() {
        this.c.g();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.e && this.d != null) {
            f fVar = this.c;
            int b2 = fVar != null ? fVar.b() : 1;
            this.c = new f(this.b);
            if (b2 != 1) {
                this.c.a(b2);
            }
            this.c.start();
        }
        this.e = false;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        f fVar = this.c;
        if (fVar != null) {
            fVar.h();
        }
        this.e = true;
        super.onDetachedFromWindow();
    }

    private void f() {
        if (this.c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        a(surfaceTexture);
        a(surfaceTexture, 0, i2, i3);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        a(surfaceTexture, 0, i2, i3);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        b(surfaceTexture);
        return true;
    }
}
