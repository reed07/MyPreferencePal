package android.support.constraint.solver.widgets;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class ResolutionDimension extends ResolutionNode {
    float value = BitmapDescriptorFactory.HUE_RED;

    public void reset() {
        super.reset();
        this.value = BitmapDescriptorFactory.HUE_RED;
    }

    public void resolve(int i) {
        if (this.state == 0 || this.value != ((float) i)) {
            this.value = (float) i;
            if (this.state == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    public void remove() {
        this.state = 2;
    }
}
