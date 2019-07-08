package com.myfitnesspal.shared.util;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ShakeDetector implements SensorEventListener {
    private static final int SHAKE_SLOP_TIME_MS = 1000;
    private static final float SHAKE_THRESHOLD_GRAVITY = 3.7f;
    private OnShakeListener shakeListener;
    private long shakeTimestamp;

    public interface OnShakeListener {
        void onShake();
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        this.shakeListener = onShakeListener;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.shakeListener != null) {
            float f = sensorEvent.values[0];
            float f2 = f / 9.80665f;
            float f3 = sensorEvent.values[1] / 9.80665f;
            float f4 = sensorEvent.values[2] / 9.80665f;
            if (Math.sqrt((double) ((f2 * f2) + (f3 * f3) + (f4 * f4))) > 3.700000047683716d) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.shakeTimestamp + 1000 <= currentTimeMillis) {
                    this.shakeTimestamp = currentTimeMillis;
                    this.shakeListener.onShake();
                }
            }
        }
    }
}
