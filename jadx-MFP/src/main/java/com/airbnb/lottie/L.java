package com.airbnb.lottie;

import android.support.annotation.RestrictTo;
import android.support.v4.os.TraceCompat;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashSet;
import java.util.Set;

@RestrictTo
public class L {
    public static boolean DBG = false;
    private static int depthPastMaxDepth = 0;
    private static final Set<String> loggedMessages = new HashSet();
    private static String[] sections;
    private static long[] startTimeNs;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    public static void debug(String str) {
        if (DBG) {
            Log.d("LOTTIE", str);
        }
    }

    public static void warn(String str) {
        if (!loggedMessages.contains(str)) {
            Log.w("LOTTIE", str);
            loggedMessages.add(str);
        }
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            int i = traceDepth;
            if (i == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i] = str;
            startTimeNs[i] = System.nanoTime();
            TraceCompat.beginSection(str);
            traceDepth++;
        }
    }

    public static float endSection(String str) {
        int i = depthPastMaxDepth;
        if (i > 0) {
            depthPastMaxDepth = i - 1;
            return BitmapDescriptorFactory.HUE_RED;
        } else if (!traceEnabled) {
            return BitmapDescriptorFactory.HUE_RED;
        } else {
            traceDepth--;
            int i2 = traceDepth;
            if (i2 == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            } else if (str.equals(sections[i2])) {
                TraceCompat.endSection();
                return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unbalanced trace call ");
                sb.append(str);
                sb.append(". Expected ");
                sb.append(sections[traceDepth]);
                sb.append(".");
                throw new IllegalStateException(sb.toString());
            }
        }
    }
}
