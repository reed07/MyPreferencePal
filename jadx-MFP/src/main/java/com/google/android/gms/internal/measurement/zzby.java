package com.google.android.gms.internal.measurement;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

final class zzby implements Logger {
    private boolean zzrr;
    private int zzyn = 2;

    zzby() {
    }

    public final void error(Exception exc) {
    }

    public final void error(String str) {
    }

    public final void info(String str) {
    }

    public final void verbose(String str) {
    }

    public final void warn(String str) {
    }

    public final void setLogLevel(int i) {
        this.zzyn = i;
        if (!this.zzrr) {
            String str = (String) zzcf.zzyx.get();
            String str2 = (String) zzcf.zzyx.get();
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 91);
            sb.append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
            sb.append(str2);
            sb.append(" DEBUG");
            Log.i(str, sb.toString());
            this.zzrr = true;
        }
    }

    public final int getLogLevel() {
        return this.zzyn;
    }
}
