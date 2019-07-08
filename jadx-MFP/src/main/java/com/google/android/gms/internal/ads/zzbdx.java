package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzark
@TargetApi(14)
public final class zzbdx extends Thread implements OnFrameAvailableListener, zzbdw {
    private static final float[] zzesl = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private final float[] zzesi;
    private final zzbdu zzesm;
    private final float[] zzesn;
    private final float[] zzeso;
    private final float[] zzesp;
    private final float[] zzesq;
    private final float[] zzesr;
    private final float[] zzess;
    private float zzest;
    private float zzesu;
    private float zzesv;
    private SurfaceTexture zzesw;
    private SurfaceTexture zzesx;
    private int zzesy;
    private int zzesz;
    private int zzeta;
    private FloatBuffer zzetb = ByteBuffer.allocateDirect(zzesl.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final CountDownLatch zzetc;
    private final Object zzetd;
    private EGL10 zzete;
    private EGLDisplay zzetf;
    private EGLContext zzetg;
    private EGLSurface zzeth;
    private volatile boolean zzeti;
    private volatile boolean zzetj;
    private int zzvt;
    private int zzvu;

    public zzbdx(Context context) {
        super("SphericalVideoProcessor");
        this.zzetb.put(zzesl).position(0);
        this.zzesi = new float[9];
        this.zzesn = new float[9];
        this.zzeso = new float[9];
        this.zzesp = new float[9];
        this.zzesq = new float[9];
        this.zzesr = new float[9];
        this.zzess = new float[9];
        this.zzest = Float.NaN;
        this.zzesm = new zzbdu(context);
        this.zzesm.zza((zzbdw) this);
        this.zzetc = new CountDownLatch(1);
        this.zzetd = new Object();
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzvt = i;
        this.zzvu = i2;
        this.zzesx = surfaceTexture;
    }

    public final void zzo(int i, int i2) {
        synchronized (this.zzetd) {
            this.zzvt = i;
            this.zzvu = i2;
            this.zzeti = true;
            this.zzetd.notifyAll();
        }
    }

    public final void zzabq() {
        synchronized (this.zzetd) {
            this.zzetj = true;
            this.zzesx = null;
            this.zzetd.notifyAll();
        }
    }

    public final SurfaceTexture zzabr() {
        if (this.zzesx == null) {
            return null;
        }
        try {
            this.zzetc.await();
        } catch (InterruptedException unused) {
        }
        return this.zzesw;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzeta++;
        synchronized (this.zzetd) {
            this.zzetd.notifyAll();
        }
    }

    public final void zzvu() {
        synchronized (this.zzetd) {
            this.zzetd.notifyAll();
        }
    }

    public final void run() {
        boolean z;
        int i;
        if (this.zzesx == null) {
            zzaxz.e("SphericalVideoProcessor started with no output texture.");
            this.zzetc.countDown();
            return;
        }
        this.zzete = (EGL10) EGLContext.getEGL();
        this.zzetf = this.zzete.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.zzetf == EGL10.EGL_NO_DISPLAY) {
            z = false;
        } else {
            if (!this.zzete.eglInitialize(this.zzetf, new int[2])) {
                z = false;
            } else {
                int[] iArr = new int[1];
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                EGLConfig eGLConfig = (!this.zzete.eglChooseConfig(this.zzetf, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) || iArr[0] <= 0) ? null : eGLConfigArr[0];
                if (eGLConfig == null) {
                    z = false;
                } else {
                    this.zzetg = this.zzete.eglCreateContext(this.zzetf, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                    EGLContext eGLContext = this.zzetg;
                    if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                        z = false;
                    } else {
                        this.zzeth = this.zzete.eglCreateWindowSurface(this.zzetf, eGLConfig, this.zzesx, null);
                        EGLSurface eGLSurface = this.zzeth;
                        if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                            z = false;
                        } else {
                            EGL10 egl10 = this.zzete;
                            EGLDisplay eGLDisplay = this.zzetf;
                            EGLSurface eGLSurface2 = this.zzeth;
                            z = egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.zzetg);
                        }
                    }
                }
            }
        }
        zzaac<String> zzaac = zzaan.zzcsq;
        int zze = zze(35633, !((String) zzwu.zzpz().zzd(zzaac)).equals(zzaac.zzqv()) ? (String) zzwu.zzpz().zzd(zzaac) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}");
        if (zze == 0) {
            i = 0;
        } else {
            zzaac<String> zzaac2 = zzaan.zzcsr;
            int zze2 = zze(35632, !((String) zzwu.zzpz().zzd(zzaac2)).equals(zzaac2.zzqv()) ? (String) zzwu.zzpz().zzd(zzaac2) : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}");
            if (zze2 == 0) {
                i = 0;
            } else {
                i = GLES20.glCreateProgram();
                zzes("createProgram");
                if (i != 0) {
                    GLES20.glAttachShader(i, zze);
                    zzes("attachShader");
                    GLES20.glAttachShader(i, zze2);
                    zzes("attachShader");
                    GLES20.glLinkProgram(i);
                    zzes("linkProgram");
                    int[] iArr2 = new int[1];
                    GLES20.glGetProgramiv(i, 35714, iArr2, 0);
                    zzes("getProgramiv");
                    if (iArr2[0] != 1) {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(i));
                        GLES20.glDeleteProgram(i);
                        zzes("deleteProgram");
                        i = 0;
                    } else {
                        GLES20.glValidateProgram(i);
                        zzes("validateProgram");
                    }
                }
            }
        }
        this.zzesy = i;
        GLES20.glUseProgram(this.zzesy);
        zzes("useProgram");
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzesy, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, this.zzetb);
        zzes("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        zzes("enableVertexAttribArray");
        int[] iArr3 = new int[1];
        GLES20.glGenTextures(1, iArr3, 0);
        zzes("genTextures");
        int i2 = iArr3[0];
        GLES20.glBindTexture(36197, i2);
        zzes("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        zzes("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzes("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzes("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzes("texParameteri");
        this.zzesz = GLES20.glGetUniformLocation(this.zzesy, "uVMat");
        GLES20.glUniformMatrix3fv(this.zzesz, 1, false, new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f}, 0);
        boolean z2 = this.zzesy != 0;
        if (!z || !z2) {
            String str = "EGL initialization failed: ";
            String valueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzete.eglGetError()));
            String concat = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            zzaxz.e(concat);
            zzbv.zzlj().zza(new Throwable(concat), "SphericalVideoProcessor.run.1");
            zzabs();
            this.zzetc.countDown();
            return;
        }
        this.zzesw = new SurfaceTexture(i2);
        this.zzesw.setOnFrameAvailableListener(this);
        this.zzetc.countDown();
        this.zzesm.start();
        try {
            this.zzeti = true;
            while (!this.zzetj) {
                while (this.zzeta > 0) {
                    this.zzesw.updateTexImage();
                    this.zzeta--;
                }
                if (this.zzesm.zza(this.zzesi)) {
                    if (Float.isNaN(this.zzest)) {
                        float[] fArr = this.zzesi;
                        float[] fArr2 = {0.0f, 1.0f, 0.0f};
                        float[] fArr3 = {(fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1]) + (fArr[2] * fArr2[2]), (fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[5] * fArr2[2]), (fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[8] * fArr2[2])};
                        this.zzest = -(((float) Math.atan2((double) fArr3[1], (double) fArr3[0])) - 1.5707964f);
                    }
                    zzb(this.zzesr, this.zzest + this.zzesu);
                } else {
                    zza(this.zzesi, -1.5707964f);
                    zzb(this.zzesr, this.zzesu);
                }
                zza(this.zzesn, 1.5707964f);
                zza(this.zzeso, this.zzesr, this.zzesn);
                zza(this.zzesp, this.zzesi, this.zzeso);
                zza(this.zzesq, this.zzesv);
                zza(this.zzess, this.zzesq, this.zzesp);
                GLES20.glUniformMatrix3fv(this.zzesz, 1, false, this.zzess, 0);
                GLES20.glDrawArrays(5, 0, 4);
                zzes("drawArrays");
                GLES20.glFinish();
                this.zzete.eglSwapBuffers(this.zzetf, this.zzeth);
                if (this.zzeti) {
                    GLES20.glViewport(0, 0, this.zzvt, this.zzvu);
                    zzes("viewport");
                    int glGetUniformLocation = GLES20.glGetUniformLocation(this.zzesy, "uFOVx");
                    int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzesy, "uFOVy");
                    if (this.zzvt > this.zzvu) {
                        GLES20.glUniform1f(glGetUniformLocation, 0.87266463f);
                        GLES20.glUniform1f(glGetUniformLocation2, (((float) this.zzvu) * 0.87266463f) / ((float) this.zzvt));
                    } else {
                        GLES20.glUniform1f(glGetUniformLocation, (((float) this.zzvt) * 0.87266463f) / ((float) this.zzvu));
                        GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f);
                    }
                    this.zzeti = false;
                }
                try {
                    synchronized (this.zzetd) {
                        if (!this.zzetj && !this.zzeti && this.zzeta == 0) {
                            this.zzetd.wait();
                        }
                    }
                } catch (InterruptedException unused) {
                }
            }
        } catch (IllegalStateException unused2) {
            zzaxz.zzeo("SphericalVideoProcessor halted unexpectedly.");
        } catch (Throwable th) {
            zzaxz.zzb("SphericalVideoProcessor died.", th);
            zzbv.zzlj().zza(th, "SphericalVideoProcessor.run.2");
        } finally {
            this.zzesm.stop();
            this.zzesw.setOnFrameAvailableListener(null);
            this.zzesw = null;
            zzabs();
        }
    }

    public final void zzb(float f, float f2) {
        float f3;
        float f4;
        int i = this.zzvt;
        int i2 = this.zzvu;
        if (i > i2) {
            f4 = (f * 1.7453293f) / ((float) i);
            f3 = (f2 * 1.7453293f) / ((float) i);
        } else {
            f4 = (f * 1.7453293f) / ((float) i2);
            f3 = (f2 * 1.7453293f) / ((float) i2);
        }
        this.zzesu -= f4;
        this.zzesv -= f3;
        if (this.zzesv < -1.5707964f) {
            this.zzesv = -1.5707964f;
        }
        if (this.zzesv > 1.5707964f) {
            this.zzesv = 1.5707964f;
        }
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3]) + (fArr2[2] * fArr3[6]);
        fArr[1] = (fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4]) + (fArr2[2] * fArr3[7]);
        fArr[2] = (fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5]) + (fArr2[2] * fArr3[8]);
        fArr[3] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3]) + (fArr2[5] * fArr3[6]);
        fArr[4] = (fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4]) + (fArr2[5] * fArr3[7]);
        fArr[5] = (fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5]) + (fArr2[5] * fArr3[8]);
        fArr[6] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3]) + (fArr2[8] * fArr3[6]);
        fArr[7] = (fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4]) + (fArr2[8] * fArr3[7]);
        fArr[8] = (fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5]) + (fArr2[8] * fArr3[8]);
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = (double) f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zzb(float[] fArr, float f) {
        double d = (double) f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zze(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzes("createShader");
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, str);
        zzes("shaderSource");
        GLES20.glCompileShader(glCreateShader);
        zzes("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        zzes("getShaderiv");
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Could not compile shader ");
        sb.append(i);
        sb.append(":");
        Log.e("SphericalVideoRenderer", sb.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        zzes("deleteShader");
        return 0;
    }

    @VisibleForTesting
    private final boolean zzabs() {
        EGLSurface eGLSurface = this.zzeth;
        boolean z = false;
        if (!(eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE)) {
            z = this.zzete.eglDestroySurface(this.zzetf, this.zzeth) | this.zzete.eglMakeCurrent(this.zzetf, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false;
            this.zzeth = null;
        }
        EGLContext eGLContext = this.zzetg;
        if (eGLContext != null) {
            z |= this.zzete.eglDestroyContext(this.zzetf, eGLContext);
            this.zzetg = null;
        }
        EGLDisplay eGLDisplay = this.zzetf;
        if (eGLDisplay == null) {
            return z;
        }
        boolean eglTerminate = z | this.zzete.eglTerminate(eGLDisplay);
        this.zzetf = null;
        return eglTerminate;
    }

    private static void zzes(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21);
            sb.append(str);
            sb.append(": glError ");
            sb.append(glGetError);
            Log.e("SphericalVideoRenderer", sb.toString());
        }
    }
}
