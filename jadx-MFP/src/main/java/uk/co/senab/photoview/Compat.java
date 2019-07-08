package uk.co.senab.photoview;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

public class Compat {
    @TargetApi(5)
    private static int getPointerIndexEclair(int i) {
        return (i & 65280) >> 8;
    }

    @TargetApi(11)
    private static int getPointerIndexHoneyComb(int i) {
        return (i & 65280) >> 8;
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimationJellyBean(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    @TargetApi(16)
    private static void postOnAnimationJellyBean(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static int getPointerIndex(int i) {
        if (VERSION.SDK_INT >= 11) {
            return getPointerIndexHoneyComb(i);
        }
        return getPointerIndexEclair(i);
    }
}
