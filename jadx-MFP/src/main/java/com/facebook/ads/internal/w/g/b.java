package com.facebook.ads.internal.w.g;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class b implements SensorEventListener {
    private final Context a;
    @Nullable
    private SensorManager b;
    private int c = 0;
    private long d;
    private long e;
    private long f;
    private float g = -1.0f;
    private float h = -1.0f;
    private float i = -1.0f;
    private final Set<a> j = new CopyOnWriteArraySet();

    public interface a {
        void a();
    }

    public b(Context context) {
        this.a = context;
    }

    public void a(a aVar) {
        if (this.j.isEmpty()) {
            this.b = (SensorManager) this.a.getSystemService("sensor");
            if (this.b == null) {
                Toast.makeText(this.a, "Sensors not supported", 1).show();
            }
            boolean z = false;
            try {
                z = this.b.registerListener(this, this.b.getDefaultSensor(1), 3);
            } catch (Exception unused) {
                Toast.makeText(this.a, "Shaking not supported", 1).show();
            }
            if (!z) {
                SensorManager sensorManager = this.b;
                if (sensorManager != null) {
                    sensorManager.unregisterListener(this);
                }
            }
        } else if (this.j.contains(aVar)) {
            return;
        }
        this.j.add(aVar);
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.f > 500) {
                this.c = 0;
            }
            long j2 = this.d;
            if (elapsedRealtime - j2 > 100) {
                if ((Math.abs(((((sensorEvent.values[0] + sensorEvent.values[1]) + sensorEvent.values[2]) - this.g) - this.h) - this.i) / ((float) (elapsedRealtime - j2))) * 10000.0f > 800.0f) {
                    int i2 = this.c + 1;
                    this.c = i2;
                    if (i2 >= 3 && elapsedRealtime - this.e > 1000) {
                        this.e = elapsedRealtime;
                        this.c = 0;
                        for (a a2 : this.j) {
                            a2.a();
                        }
                    }
                    this.f = elapsedRealtime;
                }
                this.d = elapsedRealtime;
                this.g = sensorEvent.values[0];
                this.h = sensorEvent.values[1];
                this.i = sensorEvent.values[2];
            }
        }
    }
}
