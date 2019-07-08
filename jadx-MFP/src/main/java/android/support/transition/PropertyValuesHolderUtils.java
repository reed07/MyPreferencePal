package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.util.Property;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class PropertyValuesHolderUtils {
    static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        if (VERSION.SDK_INT >= 21) {
            return PropertyValuesHolder.ofObject(property, null, path);
        }
        return PropertyValuesHolder.ofFloat(new PathProperty(property, path), new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
    }

    private PropertyValuesHolderUtils() {
    }
}
