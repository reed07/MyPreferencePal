package com.brightcove.player.video360;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import com.brightcove.player.video360.RotationMonitor.Listener;
import com.brightcove.player.view.RenderView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.Locale;

@TargetApi(17)
@RequiresApi
public class RenderThread extends HandlerThread {
    private static final int CAMERA_COUNT = 3;
    private static final float DRAG_FRICTION = 0.1f;
    public static final float FOVY = 70.0f;
    private static final float INITIAL_PITCH_DEGREES = 90.0f;
    private static final float INITIAL_ROLL_DEGREES = 90.0f;
    private static final int MATRIX_SIZE = 16;
    private static final float MAX_LATITUDE = 90.0f;
    private static final float MAX_LATITUDE_LIMIT = 89.0f;
    private static final float MIN_LATITUDE_LIMIT = -89.0f;
    public static final int MSG_FRAME_AVAILABLE = 4;
    public static final int MSG_ON_SCROLL = 6;
    public static final int MSG_SET_POSITION = 7;
    public static final int MSG_SURFACE_AVAILABLE = 1;
    public static final int MSG_SURFACE_CHANGED = 2;
    public static final int MSG_SURFACE_DESTROYED = 3;
    public static final int MSG_VSYNC = 5;
    private static final String RENDER_THREAD_NAME = "360RenderThread";
    /* access modifiers changed from: private */
    public static final String TAG = "RenderThread";
    public static final float Z_FAR = 1000.0f;
    public static final float Z_NEAR = 1.0f;
    private final OnAttachStateChangeListener attachStateChangeListener = new OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View view) {
            RenderThread.this.startTracking(view);
        }

        public void onViewDetachedFromWindow(View view) {
            RenderThread.this.stopTracking(view);
        }
    };
    private final float[] camera = new float[3];
    private final SimpleOnGestureListener dragListener = new SimpleOnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            int round = Math.round(f);
            int round2 = Math.round(f2);
            Log.v(RenderThread.TAG, String.format(Locale.getDefault(), "Scroll Detected: distanceX[%d] distanceY[%d]", new Object[]{Integer.valueOf(round), Integer.valueOf(round2)}));
            RenderThread.this.moveCameraByPixels((float) round, (float) round2);
            return super.onScroll(motionEvent, motionEvent2, f, f2);
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            RenderView access$300 = RenderThread.this.getRenderView();
            if (access$300 != null) {
                ViewParent parent = access$300.getParent();
                if (parent instanceof View) {
                    ((View) parent).onTouchEvent(motionEvent);
                }
            }
            return super.onSingleTapUp(motionEvent);
        }
    };
    private final GlRenderTarget eglRenderTarget;
    private boolean frameAvailable;
    private final FrameCallback frameCallback = new ChoreographerCallback();
    /* access modifiers changed from: private */
    public final GestureDetector gestureDetector;
    /* access modifiers changed from: private */
    public Handler handler;
    private float latitude;
    private float longitude = 90.0f;
    private final float[] modelMatrix = new float[16];
    private OnFrameAvailableListener onFrameAvailableListener;
    private boolean pendingCameraUpdate;
    private final float[] projectionMatrix = new float[16];
    private final WeakReference<RenderView> renderViewReference;
    private SphericalSceneRenderer renderer;
    private final RotationMonitor rotationMonitor;
    private final OnTouchListener touchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return RenderThread.this.gestureDetector.onTouchEvent(motionEvent);
        }
    };
    private int videoDecodeTextureId = -1;
    private SurfaceTexture videoSurfaceTexture;
    private final float[] videoTextureMatrix = new float[16];
    private final float[] viewMatrix = new float[16];

    private class ChoreographerCallback implements FrameCallback {
        private ChoreographerCallback() {
        }

        public void doFrame(long j) {
            RenderThread.this.handler.sendEmptyMessage(5);
        }
    }

    @Nullable
    private View getView() {
        RenderView renderView = getRenderView();
        if (renderView instanceof View) {
            return (View) renderView;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void startTracking(@Nullable View view) {
        this.rotationMonitor.startTracking();
        if (view == null) {
            view = getView();
        }
        if (view instanceof View) {
            view.setOnTouchListener(this.touchListener);
        }
    }

    /* access modifiers changed from: private */
    public void stopTracking(@Nullable View view) {
        this.rotationMonitor.stopTracking();
        if (view == null) {
            view = getView();
        }
        if (view instanceof View) {
            view.setOnTouchListener(null);
        }
    }

    public RenderThread(@NonNull RenderView renderView) {
        super(RENDER_THREAD_NAME);
        this.renderViewReference = new WeakReference<>(renderView);
        this.eglRenderTarget = new GlRenderTarget();
        Context context = renderView.getContext();
        this.gestureDetector = new GestureDetector(context, this.dragListener);
        this.rotationMonitor = new RotationMonitor(context);
        this.rotationMonitor.setListener(new Listener() {
            public void onDetected(int i, float f, float f2, float f3) {
                boolean z = false;
                Log.v(RenderThread.TAG, String.format(Locale.getDefault(), "Initial position: orientation[%d], azimuth[%f] pitch[%f] roll[%f]", new Object[]{Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)}));
                float f4 = f3 < BitmapDescriptorFactory.HUE_RED ? f3 + 90.0f : 90.0f - f3;
                if (i == 1 || i == 3) {
                    z = true;
                }
                RenderThread renderThread = RenderThread.this;
                if (z) {
                    f4 = -f4;
                }
                renderThread.setCameraByDegrees(f, f4);
            }

            public void onChanged(int i, float f, float f2, float f3, float f4, float f5, float f6) {
                if (Math.sqrt((double) (f5 * f5)) < 1.0d) {
                    RenderThread.this.moveCameraByDegrees(f4, f6);
                }
            }
        });
        if (renderView instanceof View) {
            ((View) renderView).addOnAttachStateChangeListener(this.attachStateChangeListener);
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public RenderView getRenderView() {
        WeakReference<RenderView> weakReference = this.renderViewReference;
        if (weakReference == null) {
            return null;
        }
        return (RenderView) weakReference.get();
    }

    /* access modifiers changed from: private */
    public void moveCameraByPixels(float f, float f2) {
        Message obtain = Message.obtain();
        obtain.what = 6;
        obtain.obj = new PointF((-f) * 0.1f, (-f2) * 0.1f);
        this.handler.sendMessage(obtain);
    }

    /* access modifiers changed from: private */
    public void setCameraByDegrees(float f, float f2) {
        Message obtain = Message.obtain();
        obtain.what = 7;
        obtain.obj = new PointF(90.0f, f2);
        this.handler.sendMessage(obtain);
    }

    /* access modifiers changed from: private */
    public void moveCameraByDegrees(float f, float f2) {
        Message obtain = Message.obtain();
        obtain.what = 6;
        obtain.obj = new PointF(-f, f2);
        this.handler.sendMessage(obtain);
    }

    public boolean isVrMode() {
        SphericalSceneRenderer sphericalSceneRenderer = this.renderer;
        return sphericalSceneRenderer != null && sphericalSceneRenderer.isVrMode();
    }

    public void setVrMode(boolean z) {
        SphericalSceneRenderer sphericalSceneRenderer = this.renderer;
        if (sphericalSceneRenderer != null) {
            sphericalSceneRenderer.setVrMode(z);
        }
    }

    public void notifySurfaceAvailable(Object obj, int i, int i2) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = obj;
        obtain.arg1 = i;
        obtain.arg2 = i2;
        this.handler.sendMessage(obtain);
    }

    public void notifySurfaceChanged(Surface surface, int i, int i2) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = surface;
        obtain.arg1 = i;
        obtain.arg2 = i2;
        this.handler.sendMessage(obtain);
    }

    public void notifySurfaceDestroyed() {
        this.handler.sendEmptyMessage(3);
    }

    public synchronized void start() {
        super.start();
        startTracking(null);
        this.handler = new Handler(getLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        RenderThread.this.onSurfaceAvailable(message.obj, message.arg1, message.arg2);
                        return;
                    case 2:
                        RenderThread.this.onSurfaceChanged(message.obj, message.arg1, message.arg2);
                        return;
                    case 3:
                        RenderThread.this.onSurfaceDestroyed();
                        return;
                    case 4:
                        RenderThread.this.onFrameAvailable();
                        return;
                    case 5:
                        RenderThread.this.onVSync();
                        return;
                    case 6:
                        RenderThread.this.onScroll((PointF) message.obj);
                        return;
                    case 7:
                        RenderThread.this.onSetPosition((PointF) message.obj);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public Surface getVideoDecodeSurface() {
        if (this.eglRenderTarget.hasValidContext()) {
            this.videoDecodeTextureId = GlUtil.generateExternalTexture();
            this.videoSurfaceTexture = new SurfaceTexture(this.videoDecodeTextureId);
            this.videoSurfaceTexture.setOnFrameAvailableListener(new OnFrameAvailableListener() {
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    RenderThread.this.handler.sendEmptyMessage(4);
                }
            });
            return new Surface(this.videoSurfaceTexture);
        }
        throw new IllegalStateException("Cannot get video decode surface without GL context");
    }

    /* access modifiers changed from: private */
    public void onSurfaceAvailable(Object obj, int i, int i2) {
        String str = RENDER_THREAD_NAME;
        StringBuilder sb = new StringBuilder();
        sb.append("onSurfaceAvailable w: ");
        sb.append(i);
        sb.append(" h: ");
        sb.append(i2);
        Log.d(str, sb.toString());
        RenderView renderView = getRenderView();
        if (renderView != null && !this.eglRenderTarget.hasValidSurface()) {
            this.eglRenderTarget.createRenderSurface(obj);
            Choreographer.getInstance().postFrameCallback(this.frameCallback);
            GLES20.glViewport(0, 0, i, i2);
            GlUtil.checkGlError("glViewport");
            Matrix.perspectiveM(this.projectionMatrix, 0, 70.0f, ((float) i) / ((float) i2), 1.0f, 1000.0f);
            Matrix.setIdentityM(this.viewMatrix, 0);
            Matrix.setRotateM(this.modelMatrix, 0, 90.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f);
            this.renderer = new SphericalSceneRenderer(renderView.getContext());
            renderView.setSurface(getVideoDecodeSurface());
            OnFrameAvailableListener onFrameAvailableListener2 = this.onFrameAvailableListener;
            if (onFrameAvailableListener2 != null) {
                SurfaceTexture surfaceTexture = this.videoSurfaceTexture;
                if (surfaceTexture != null) {
                    onFrameAvailableListener2.onFrameAvailable(surfaceTexture);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onSurfaceChanged(Object obj, int i, int i2) {
        String str = RENDER_THREAD_NAME;
        StringBuilder sb = new StringBuilder();
        sb.append("onSurfaceChanged w: ");
        sb.append(i);
        sb.append(" h: ");
        sb.append(i2);
        Log.d(str, sb.toString());
        GLES20.glViewport(0, 0, i, i2);
        GlUtil.checkGlError("glViewport");
        Matrix.perspectiveM(this.projectionMatrix, 0, 70.0f, ((float) i) / ((float) i2), 1.0f, 1000.0f);
    }

    /* access modifiers changed from: private */
    public void onSurfaceDestroyed() {
        stopTracking(null);
        RenderView renderView = getRenderView();
        if (renderView != null) {
            renderView.release();
        }
        int i = this.videoDecodeTextureId;
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.videoDecodeTextureId = -1;
        }
        SurfaceTexture surfaceTexture = this.videoSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.videoSurfaceTexture = null;
            this.frameAvailable = false;
        }
        this.pendingCameraUpdate = false;
        this.eglRenderTarget.release();
        SphericalSceneRenderer sphericalSceneRenderer = this.renderer;
        if (sphericalSceneRenderer != null) {
            sphericalSceneRenderer.release();
            this.renderer = null;
        }
    }

    /* access modifiers changed from: private */
    public void onVSync() {
        if (this.eglRenderTarget.hasValidContext() && this.renderer != null) {
            Choreographer.getInstance().postFrameCallback(this.frameCallback);
            if (this.eglRenderTarget.hasValidContext() && (this.frameAvailable || this.pendingCameraUpdate)) {
                this.eglRenderTarget.makeCurrent();
                this.videoSurfaceTexture.updateTexImage();
                this.videoSurfaceTexture.getTransformMatrix(this.videoTextureMatrix);
                updateCamera();
                this.renderer.onDrawFrame(this.videoDecodeTextureId, this.videoTextureMatrix, this.modelMatrix, this.viewMatrix, this.projectionMatrix);
                this.eglRenderTarget.swapBuffers();
                if (this.frameAvailable) {
                    this.frameAvailable = false;
                }
                if (this.pendingCameraUpdate) {
                    this.pendingCameraUpdate = false;
                }
            }
        }
    }

    private void updateCamera() {
        this.latitude = Math.max(MIN_LATITUDE_LIMIT, Math.min(MAX_LATITUDE_LIMIT, this.latitude));
        double radians = (double) ((float) Math.toRadians((double) (90.0f - this.latitude)));
        double radians2 = (double) ((float) Math.toRadians((double) this.longitude));
        this.camera[0] = (float) (Math.sin(radians) * 100.0d * Math.cos(radians2));
        this.camera[1] = (float) (Math.cos(radians) * 100.0d);
        this.camera[2] = (float) (Math.sin(radians) * 100.0d * Math.sin(radians2));
        float[] fArr = this.viewMatrix;
        float[] fArr2 = this.camera;
        Matrix.setLookAtM(fArr, 0, fArr2[0], fArr2[1], fArr2[2], BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED);
    }

    /* access modifiers changed from: private */
    public void onFrameAvailable() {
        this.frameAvailable = true;
    }

    /* access modifiers changed from: private */
    public void onSetPosition(PointF pointF) {
        Log.v(TAG, String.format(Locale.getDefault(), "Setting camera position:azimuth[%f] roll[%f]", new Object[]{Float.valueOf(pointF.x), Float.valueOf(pointF.y)}));
        this.longitude = pointF.x;
        this.latitude = pointF.y;
        this.pendingCameraUpdate = true;
    }

    /* access modifiers changed from: private */
    public void onScroll(PointF pointF) {
        this.longitude += pointF.x;
        this.latitude += pointF.y;
        this.pendingCameraUpdate = true;
    }

    public void setOnFrameAvailableListener(OnFrameAvailableListener onFrameAvailableListener2) {
        this.onFrameAvailableListener = onFrameAvailableListener2;
    }
}
