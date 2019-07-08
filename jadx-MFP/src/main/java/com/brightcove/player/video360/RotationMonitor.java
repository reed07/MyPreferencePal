package com.brightcove.player.video360;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

public class RotationMonitor {
    private static final String TAG = "RotationMonitor";
    /* access modifiers changed from: private */
    public float[] accelerationVector;
    /* access modifiers changed from: private */
    public float[] gravityVector;
    private float lastAzimuth;
    private int lastOrientation;
    private float lastPitch;
    private float lastRoll;
    private Listener listener;
    /* access modifiers changed from: private */
    public float[] magneticVector;
    private boolean receiving;
    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 11) {
                float[] fArr = new float[16];
                SensorManager.getRotationMatrixFromVector(fArr, sensorEvent.values);
                RotationMonitor.this.onRotation(fArr);
            } else {
                int type = sensorEvent.sensor.getType();
                if (type != 9) {
                    switch (type) {
                        case 1:
                            RotationMonitor.this.accelerationVector = Arrays.copyOf(sensorEvent.values, sensorEvent.values.length);
                            break;
                        case 2:
                            RotationMonitor.this.magneticVector = Arrays.copyOf(sensorEvent.values, sensorEvent.values.length);
                            break;
                        default:
                            return;
                    }
                } else {
                    RotationMonitor.this.gravityVector = Arrays.copyOf(sensorEvent.values, sensorEvent.values.length);
                }
                float[] access$100 = RotationMonitor.this.gravityVector != null ? RotationMonitor.this.gravityVector : RotationMonitor.this.accelerationVector;
                if (!(access$100 == null || RotationMonitor.this.magneticVector == null)) {
                    float[] fArr2 = new float[16];
                    SensorManager.getRotationMatrix(fArr2, null, access$100, RotationMonitor.this.magneticVector);
                    RotationMonitor.this.onRotation(fArr2);
                }
            }
        }
    };
    private final SensorManager sensorManager;
    private final WindowManager windowManager;

    public interface Listener {
        void onChanged(int i, float f, float f2, float f3, float f4, float f5, float f6);

        void onDetected(int i, float f, float f2, float f3);
    }

    @Nullable
    public synchronized Listener getListener() {
        return this.listener;
    }

    public synchronized void setListener(@Nullable Listener listener2) {
        this.listener = listener2;
    }

    public RotationMonitor(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.lastOrientation = getRotation();
    }

    @Nullable
    private Sensor activateSensor(int i) {
        Sensor defaultSensor = this.sensorManager.getDefaultSensor(i);
        if (defaultSensor != null) {
            this.sensorManager.registerListener(this.sensorEventListener, defaultSensor, DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
        }
        return defaultSensor;
    }

    public void startTracking() {
        if (activateSensor(11) == null) {
            activateSensor(1);
            activateSensor(4);
            activateSensor(2);
        }
    }

    public void stopTracking() {
        this.sensorManager.unregisterListener(this.sensorEventListener);
        this.receiving = false;
    }

    /* access modifiers changed from: private */
    public void onRotation(float[] fArr) {
        float f;
        float f2;
        float f3;
        float[] orientation = SensorManager.getOrientation(getOrientedRotationMatrix(fArr), new float[3]);
        float degrees = (float) Math.toDegrees((double) orientation[0]);
        float degrees2 = (float) Math.toDegrees((double) orientation[1]);
        float degrees3 = (float) Math.toDegrees((double) orientation[2]);
        int rotation = getRotation();
        if (!this.receiving || rotation != this.lastOrientation) {
            this.lastAzimuth = degrees;
            this.lastPitch = degrees2;
            this.lastRoll = degrees3;
            this.receiving = true;
            this.lastOrientation = rotation;
            Listener listener2 = getListener();
            if (listener2 != null) {
                listener2.onDetected(rotation, degrees, degrees2, degrees3);
            }
            f3 = BitmapDescriptorFactory.HUE_RED;
            f2 = BitmapDescriptorFactory.HUE_RED;
            f = BitmapDescriptorFactory.HUE_RED;
        } else {
            float f4 = degrees - this.lastAzimuth;
            float f5 = degrees2 - this.lastPitch;
            if (Math.signum(degrees3) == Math.signum(this.lastRoll)) {
                f3 = f4;
                f2 = f5;
                f = degrees3 - this.lastRoll;
            } else {
                f3 = f4;
                f2 = f5;
                f = this.lastRoll + degrees3;
            }
        }
        if (Math.sqrt((double) ((f2 * f2) + (f * f) + (f3 * f3))) > 1.0d) {
            this.lastAzimuth = degrees;
            this.lastPitch = degrees2;
            this.lastRoll = degrees3;
            Listener listener3 = getListener();
            if (listener3 != null) {
                listener3.onChanged(rotation, degrees, degrees2, degrees3, f3, f2, f);
            }
        }
    }

    public int getRotation() {
        return this.windowManager.getDefaultDisplay().getRotation();
    }

    private float[] getOrientedRotationMatrix(float[] fArr) {
        int i = 130;
        int i2 = 129;
        switch (getRotation()) {
            case 1:
                i = 129;
                i2 = 130;
                break;
            case 2:
                break;
            case 3:
                i = 1;
                i2 = 2;
                break;
            default:
                i = 2;
                i2 = 1;
                break;
        }
        float[] fArr2 = new float[fArr.length];
        SensorManager.remapCoordinateSystem(fArr, i, i2, fArr2);
        return fArr2;
    }
}
