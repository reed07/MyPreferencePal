package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import javax.annotation.concurrent.GuardedBy;

@zzark
final class zzbdu implements SensorEventListener {
    private final SensorManager zzesd;
    private final Object zzese = new Object();
    private final Display zzesf;
    private final float[] zzesg = new float[9];
    private final float[] zzesh = new float[9];
    @GuardedBy("mSensorThreadLock")
    private float[] zzesi;
    private Handler zzesj;
    private zzbdw zzesk;

    zzbdu(Context context) {
        this.zzesd = (SensorManager) context.getSystemService("sensor");
        this.zzesf = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* access modifiers changed from: 0000 */
    public final void start() {
        if (this.zzesj == null) {
            Sensor defaultSensor = this.zzesd.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzaxz.e("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            this.zzesj = new Handler(handlerThread.getLooper());
            if (!this.zzesd.registerListener(this, defaultSensor, 0, this.zzesj)) {
                zzaxz.e("SensorManager.registerListener failed.");
                stop();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void stop() {
        if (this.zzesj != null) {
            this.zzesd.unregisterListener(this);
            this.zzesj.post(new zzbdv(this));
            this.zzesj = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbdw zzbdw) {
        this.zzesk = zzbdw;
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr[0] != BitmapDescriptorFactory.HUE_RED || fArr[1] != BitmapDescriptorFactory.HUE_RED || fArr[2] != BitmapDescriptorFactory.HUE_RED) {
            synchronized (this.zzese) {
                if (this.zzesi == null) {
                    this.zzesi = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzesg, fArr);
            switch (this.zzesf.getRotation()) {
                case 1:
                    SensorManager.remapCoordinateSystem(this.zzesg, 2, 129, this.zzesh);
                    break;
                case 2:
                    SensorManager.remapCoordinateSystem(this.zzesg, 129, 130, this.zzesh);
                    break;
                case 3:
                    SensorManager.remapCoordinateSystem(this.zzesg, 130, 1, this.zzesh);
                    break;
                default:
                    System.arraycopy(this.zzesg, 0, this.zzesh, 0, 9);
                    break;
            }
            zzn(1, 3);
            zzn(2, 6);
            zzn(5, 7);
            synchronized (this.zzese) {
                System.arraycopy(this.zzesh, 0, this.zzesi, 0, 9);
            }
            zzbdw zzbdw = this.zzesk;
            if (zzbdw != null) {
                zzbdw.zzvu();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(float[] fArr) {
        synchronized (this.zzese) {
            if (this.zzesi == null) {
                return false;
            }
            System.arraycopy(this.zzesi, 0, fArr, 0, this.zzesi.length);
            return true;
        }
    }

    private final void zzn(int i, int i2) {
        float[] fArr = this.zzesh;
        float f = fArr[i];
        fArr[i] = fArr[i2];
        fArr[i2] = f;
    }
}
