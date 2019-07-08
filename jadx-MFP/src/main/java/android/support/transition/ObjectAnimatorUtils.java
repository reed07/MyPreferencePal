package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.util.Property;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class ObjectAnimatorUtils {
    static <T> ObjectAnimator ofPointF(T t, Property<T, PointF> property, Path path) {
        if (VERSION.SDK_INT >= 21) {
            return ObjectAnimator.ofObject(t, property, null, path);
        }
        return ObjectAnimator.ofFloat(t, new PathProperty(property, path), new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
    }

    private ObjectAnimatorUtils() {
    }
}
