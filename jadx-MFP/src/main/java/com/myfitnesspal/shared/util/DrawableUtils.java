package com.myfitnesspal.shared.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/shared/util/DrawableUtils;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: DrawableUtils.kt */
public final class DrawableUtils {
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/shared/util/DrawableUtils$Companion;", "", "()V", "getSquareDrawable", "Landroid/graphics/drawable/Drawable;", "size", "", "color", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: DrawableUtils.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Drawable getSquareDrawable(int i, int i2) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(i2);
            gradientDrawable.setSize(i, i);
            return gradientDrawable;
        }
    }

    @JvmStatic
    @NotNull
    public static final Drawable getSquareDrawable(int i, int i2) {
        return Companion.getSquareDrawable(i, i2);
    }
}
