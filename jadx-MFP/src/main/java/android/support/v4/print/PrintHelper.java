package android.support.v4.print;

import android.os.Build.VERSION;

public final class PrintHelper {
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = (VERSION.SDK_INT < 20 || VERSION.SDK_INT > 23);

    public interface OnPrintFinishCallback {
    }

    static {
        boolean z = false;
        if (VERSION.SDK_INT != 23) {
            z = true;
        }
        IS_MIN_MARGINS_HANDLING_CORRECT = z;
    }
}
