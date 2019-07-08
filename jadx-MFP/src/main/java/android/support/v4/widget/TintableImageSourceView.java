package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

@RestrictTo
public interface TintableImageSourceView {
    @Nullable
    ColorStateList getSupportImageTintList();

    @Nullable
    Mode getSupportImageTintMode();

    void setSupportImageTintList(@Nullable ColorStateList colorStateList);

    void setSupportImageTintMode(@Nullable Mode mode);
}
