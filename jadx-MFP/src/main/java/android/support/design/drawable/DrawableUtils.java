package android.support.design.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

@RestrictTo
public class DrawableUtils {
    private DrawableUtils() {
    }

    @Nullable
    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, @Nullable ColorStateList colorStateList, @Nullable Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
    }
}
