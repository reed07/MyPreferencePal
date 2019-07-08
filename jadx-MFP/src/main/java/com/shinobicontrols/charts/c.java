package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

class c extends Animation {
    private final List<Animation> a = new ArrayList();
    private Animation[] b;

    c() {
    }

    /* access modifiers changed from: 0000 */
    public void a(Animation animation) {
        this.a.add(animation);
    }

    public float getDuration() {
        int size = this.a.size();
        a(size);
        Animation[] animationArr = (Animation[]) this.a.toArray(this.b);
        float f = BitmapDescriptorFactory.HUE_RED;
        for (int i = 0; i < size; i++) {
            float duration = animationArr[i].getDuration();
            if (duration > f) {
                f = duration;
            }
        }
        return f;
    }

    /* access modifiers changed from: 0000 */
    public void a(float f) {
        int size = this.a.size();
        a(size);
        Animation[] animationArr = (Animation[]) this.a.toArray(this.b);
        for (int i = 0; i < size; i++) {
            animationArr[i].a(f);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        int size = this.a.size();
        a(size);
        Animation[] animationArr = (Animation[]) this.a.toArray(this.b);
        boolean z = true;
        for (int i = 0; i < size; i++) {
            z = z && animationArr[i].c();
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        Animation[] animationArr = this.b;
        if (animationArr == null || animationArr.length != i) {
            this.b = new Animation[i];
        }
    }
}
