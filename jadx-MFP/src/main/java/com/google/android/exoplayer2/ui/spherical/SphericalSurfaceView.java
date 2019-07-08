package com.google.android.exoplayer2.ui.spherical;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import com.google.android.exoplayer2.Player.VideoComponent;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(15)
public final class SphericalSurfaceView extends GLSurfaceView {
    private static final int FIELD_OF_VIEW_DEGREES = 90;
    private static final float PX_PER_DEGREES = 25.0f;
    static final float UPRIGHT_ROLL = 3.1415927f;
    private static final float Z_FAR = 100.0f;
    private static final float Z_NEAR = 0.1f;
    private final Handler mainHandler;
    @Nullable
    private final Sensor orientationSensor;
    private final PhoneOrientationListener phoneOrientationListener;
    private final Renderer renderer;
    private final SceneRenderer scene;
    private final SensorManager sensorManager;
    @Nullable
    private Surface surface;
    @Nullable
    private SurfaceListener surfaceListener;
    @Nullable
    private SurfaceTexture surfaceTexture;
    private final TouchTracker touchTracker;
    @Nullable
    private VideoComponent videoComponent;

    private static class PhoneOrientationListener implements SensorEventListener {
        private final float[] angles = new float[3];
        private final Display display;
        private final float[] phoneInWorldSpaceMatrix = new float[16];
        private final float[] remappedPhoneMatrix = new float[16];
        private final Renderer renderer;
        private final TouchTracker touchTracker;

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public PhoneOrientationListener(Display display2, TouchTracker touchTracker2, Renderer renderer2) {
            this.display = display2;
            this.touchTracker = touchTracker2;
            this.renderer = renderer2;
        }

        @BinderThread
        public void onSensorChanged(SensorEvent sensorEvent) {
            SensorManager.getRotationMatrixFromVector(this.remappedPhoneMatrix, sensorEvent.values);
            int i = 130;
            int i2 = 129;
            switch (this.display.getRotation()) {
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 129;
                    i2 = 130;
                    break;
                case 3:
                    i2 = 1;
                    break;
                default:
                    i = 1;
                    i2 = 2;
                    break;
            }
            SensorManager.remapCoordinateSystem(this.remappedPhoneMatrix, i, i2, this.phoneInWorldSpaceMatrix);
            SensorManager.remapCoordinateSystem(this.phoneInWorldSpaceMatrix, 1, PacketTypes.RetrieveDiaryDayForOtherUser, this.remappedPhoneMatrix);
            SensorManager.getOrientation(this.remappedPhoneMatrix, this.angles);
            float f = this.angles[2];
            this.touchTracker.setRoll(f);
            Matrix.rotateM(this.phoneInWorldSpaceMatrix, 0, 90.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            this.renderer.setDeviceOrientation(this.phoneInWorldSpaceMatrix, f);
        }
    }

    class Renderer implements android.opengl.GLSurfaceView.Renderer, Listener {
        private final float[] deviceOrientationMatrix = new float[16];
        private float deviceRoll;
        private final float[] projectionMatrix = new float[16];
        private final SceneRenderer scene;
        private final float[] tempMatrix = new float[16];
        private float touchPitch;
        private final float[] touchPitchMatrix = new float[16];
        private final float[] touchYawMatrix = new float[16];
        private final float[] viewMatrix = new float[16];
        private final float[] viewProjectionMatrix = new float[16];

        public Renderer(SceneRenderer sceneRenderer) {
            this.scene = sceneRenderer;
            Matrix.setIdentityM(this.deviceOrientationMatrix, 0);
            Matrix.setIdentityM(this.touchPitchMatrix, 0);
            Matrix.setIdentityM(this.touchYawMatrix, 0);
            this.deviceRoll = SphericalSurfaceView.UPRIGHT_ROLL;
        }

        public synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            SphericalSurfaceView.this.onSurfaceTextureAvailable(this.scene.init());
        }

        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
            GLES20.glViewport(0, 0, i, i2);
            float f = ((float) i) / ((float) i2);
            Matrix.perspectiveM(this.projectionMatrix, 0, calculateFieldOfViewInYDirection(f), f, 0.1f, 100.0f);
        }

        public void onDrawFrame(GL10 gl10) {
            synchronized (this) {
                Matrix.multiplyMM(this.tempMatrix, 0, this.deviceOrientationMatrix, 0, this.touchYawMatrix, 0);
                Matrix.multiplyMM(this.viewMatrix, 0, this.touchPitchMatrix, 0, this.tempMatrix, 0);
            }
            Matrix.multiplyMM(this.viewProjectionMatrix, 0, this.projectionMatrix, 0, this.viewMatrix, 0);
            this.scene.drawFrame(this.viewProjectionMatrix, 0);
        }

        @BinderThread
        public synchronized void setDeviceOrientation(float[] fArr, float f) {
            System.arraycopy(fArr, 0, this.deviceOrientationMatrix, 0, this.deviceOrientationMatrix.length);
            this.deviceRoll = -f;
            updatePitchMatrix();
        }

        @AnyThread
        private void updatePitchMatrix() {
            Matrix.setRotateM(this.touchPitchMatrix, 0, -this.touchPitch, (float) Math.cos((double) this.deviceRoll), (float) Math.sin((double) this.deviceRoll), BitmapDescriptorFactory.HUE_RED);
        }

        @UiThread
        public synchronized void onScrollChange(PointF pointF) {
            this.touchPitch = pointF.y;
            updatePitchMatrix();
            Matrix.setRotateM(this.touchYawMatrix, 0, -pointF.x, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED);
        }

        private float calculateFieldOfViewInYDirection(float f) {
            if (f > 1.0f) {
                return (float) (Math.toDegrees(Math.atan(Math.tan(Math.toRadians(45.0d)) / ((double) f))) * 2.0d);
            }
            return 90.0f;
        }
    }

    public interface SurfaceListener {
        void surfaceChanged(@Nullable Surface surface);
    }

    public SphericalSurfaceView(Context context) {
        this(context, null);
    }

    public SphericalSurfaceView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.sensorManager = (SensorManager) Assertions.checkNotNull(context.getSystemService("sensor"));
        Sensor defaultSensor = Util.SDK_INT >= 18 ? this.sensorManager.getDefaultSensor(15) : null;
        if (defaultSensor == null) {
            defaultSensor = this.sensorManager.getDefaultSensor(11);
        }
        this.orientationSensor = defaultSensor;
        this.scene = new SceneRenderer();
        this.renderer = new Renderer(this.scene);
        this.touchTracker = new TouchTracker(context, this.renderer, PX_PER_DEGREES);
        this.phoneOrientationListener = new PhoneOrientationListener(((WindowManager) Assertions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay(), this.touchTracker, this.renderer);
        setEGLContextClientVersion(2);
        setRenderer(this.renderer);
        setOnTouchListener(this.touchTracker);
    }

    public void setDefaultStereoMode(int i) {
        this.scene.setDefaultStereoMode(i);
    }

    public void setVideoComponent(@Nullable VideoComponent videoComponent2) {
        VideoComponent videoComponent3 = this.videoComponent;
        if (videoComponent2 != videoComponent3) {
            if (videoComponent3 != null) {
                Surface surface2 = this.surface;
                if (surface2 != null) {
                    videoComponent3.clearVideoSurface(surface2);
                }
                this.videoComponent.clearVideoFrameMetadataListener(this.scene);
                this.videoComponent.clearCameraMotionListener(this.scene);
            }
            this.videoComponent = videoComponent2;
            VideoComponent videoComponent4 = this.videoComponent;
            if (videoComponent4 != null) {
                videoComponent4.setVideoFrameMetadataListener(this.scene);
                this.videoComponent.setCameraMotionListener(this.scene);
                this.videoComponent.setVideoSurface(this.surface);
            }
        }
    }

    public void setSurfaceListener(@Nullable SurfaceListener surfaceListener2) {
        this.surfaceListener = surfaceListener2;
    }

    public void setSingleTapListener(@Nullable SingleTapListener singleTapListener) {
        this.touchTracker.setSingleTapListener(singleTapListener);
    }

    public void onResume() {
        super.onResume();
        Sensor sensor = this.orientationSensor;
        if (sensor != null) {
            this.sensorManager.registerListener(this.phoneOrientationListener, sensor, 0);
        }
    }

    public void onPause() {
        if (this.orientationSensor != null) {
            this.sensorManager.unregisterListener(this.phoneOrientationListener);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mainHandler.post(new Runnable() {
            public final void run() {
                SphericalSurfaceView.lambda$onDetachedFromWindow$0(SphericalSurfaceView.this);
            }
        });
    }

    public static /* synthetic */ void lambda$onDetachedFromWindow$0(SphericalSurfaceView sphericalSurfaceView) {
        if (sphericalSurfaceView.surface != null) {
            SurfaceListener surfaceListener2 = sphericalSurfaceView.surfaceListener;
            if (surfaceListener2 != null) {
                surfaceListener2.surfaceChanged(null);
            }
            releaseSurface(sphericalSurfaceView.surfaceTexture, sphericalSurfaceView.surface);
            sphericalSurfaceView.surfaceTexture = null;
            sphericalSurfaceView.surface = null;
        }
    }

    /* access modifiers changed from: private */
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture2) {
        this.mainHandler.post(new Runnable(surfaceTexture2) {
            private final /* synthetic */ SurfaceTexture f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SphericalSurfaceView.lambda$onSurfaceTextureAvailable$1(SphericalSurfaceView.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$onSurfaceTextureAvailable$1(SphericalSurfaceView sphericalSurfaceView, SurfaceTexture surfaceTexture2) {
        SurfaceTexture surfaceTexture3 = sphericalSurfaceView.surfaceTexture;
        Surface surface2 = sphericalSurfaceView.surface;
        sphericalSurfaceView.surfaceTexture = surfaceTexture2;
        sphericalSurfaceView.surface = new Surface(surfaceTexture2);
        SurfaceListener surfaceListener2 = sphericalSurfaceView.surfaceListener;
        if (surfaceListener2 != null) {
            surfaceListener2.surfaceChanged(sphericalSurfaceView.surface);
        }
        releaseSurface(surfaceTexture3, surface2);
    }

    private static void releaseSurface(@Nullable SurfaceTexture surfaceTexture2, @Nullable Surface surface2) {
        if (surfaceTexture2 != null) {
            surfaceTexture2.release();
        }
        if (surface2 != null) {
            surface2.release();
        }
    }
}
