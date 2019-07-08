package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

abstract class Animation {
    private float a = BitmapDescriptorFactory.HUE_RED;
    private float b = 0.016666668f;
    private boolean c;

    Animation() {
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        return this.a / this.b;
    }

    public float getDuration() {
        return this.b;
    }

    public void setDuration(float f) {
        this.b = f;
    }

    /* access modifiers changed from: 0000 */
    public void a(float f) {
        if (this.c) {
            this.a -= f;
        } else {
            this.a += f;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.c = z;
        if (z) {
            this.a = this.b;
        } else {
            this.a = BitmapDescriptorFactory.HUE_RED;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        if (this.c) {
            if (this.a <= BitmapDescriptorFactory.HUE_RED) {
                return true;
            }
        } else if (this.a >= this.b) {
            return true;
        }
        return false;
    }
}
