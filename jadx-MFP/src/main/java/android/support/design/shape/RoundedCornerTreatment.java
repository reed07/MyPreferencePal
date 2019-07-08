package android.support.design.shape;

import android.support.design.internal.Experimental;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@Experimental("The shapes API is currently experimental and subject to change")
public class RoundedCornerTreatment extends CornerTreatment {
    private final float radius;

    public RoundedCornerTreatment(float f) {
        this.radius = f;
    }

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        shapePath.reset(BitmapDescriptorFactory.HUE_RED, this.radius * f2);
        float f3 = this.radius;
        shapePath.addArc(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f3 * 2.0f * f2, f3 * 2.0f * f2, f + 180.0f, 90.0f);
    }
}
