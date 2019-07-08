package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;

public final class DrawableDecoderCompat {
    private static volatile boolean shouldCallAppCompatResources = true;

    private DrawableDecoderCompat() {
    }

    public static Drawable getDrawable(Context context, Context context2, @DrawableRes int i) {
        return getDrawable(context, context2, i, null);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int i, @Nullable Theme theme) {
        return getDrawable(context, context, i, theme);
    }

    private static Drawable getDrawable(Context context, Context context2, @DrawableRes int i, @Nullable Theme theme) {
        try {
            if (shouldCallAppCompatResources) {
                return loadDrawableV7(context2, i, theme);
            }
        } catch (NoClassDefFoundError unused) {
            shouldCallAppCompatResources = false;
        } catch (IllegalStateException e) {
            if (!context.getPackageName().equals(context2.getPackageName())) {
                return ContextCompat.getDrawable(context2, i);
            }
            throw e;
        } catch (NotFoundException unused2) {
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return loadDrawableV4(context2, i, theme);
    }

    private static Drawable loadDrawableV7(Context context, @DrawableRes int i, @Nullable Theme theme) {
        if (theme != null) {
            context = new ContextThemeWrapper(context, theme);
        }
        return AppCompatResources.getDrawable(context, i);
    }

    private static Drawable loadDrawableV4(Context context, @DrawableRes int i, @Nullable Theme theme) {
        return ResourcesCompat.getDrawable(context.getResources(), i, theme);
    }
}
