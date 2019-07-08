package com.facebook.ads.internal;

import android.util.Log;

public class DisplayAdController {
    private static final String a = "DisplayAdController";
    private static boolean b = false;

    @Deprecated
    protected static synchronized void setMainThreadForced(boolean z) {
        synchronized (DisplayAdController.class) {
            String str = a;
            StringBuilder sb = new StringBuilder();
            sb.append("BaseAdController changed main thread forced from ");
            sb.append(b);
            sb.append(" to ");
            sb.append(z);
            Log.d(str, sb.toString());
            b = z;
        }
    }
}
