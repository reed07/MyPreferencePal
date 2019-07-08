package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.ExceptionBuilder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzco;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

@VisibleForTesting
public class ExceptionReporter implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler zzrg;
    private final Tracker zzrh;
    private final Context zzri;
    private ExceptionParser zzrj;
    private GoogleAnalytics zzrk;

    public ExceptionReporter(Tracker tracker, UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        String str;
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context != null) {
            this.zzrg = uncaughtExceptionHandler;
            this.zzrh = tracker;
            this.zzrj = new StandardExceptionParser(context, new ArrayList());
            this.zzri = context.getApplicationContext();
            String str2 = "ExceptionReporter created, original handler is ";
            if (uncaughtExceptionHandler == null) {
                str = "null";
            } else {
                str = uncaughtExceptionHandler.getClass().getName();
            }
            String valueOf = String.valueOf(str);
            zzco.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            throw new NullPointerException("context cannot be null");
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.zzrj;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzrj = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str = "UncaughtException";
        if (this.zzrj != null) {
            str = this.zzrj.getDescription(thread != null ? thread.getName() : null, th);
        }
        String str2 = "Reporting uncaught exception: ";
        String valueOf = String.valueOf(str);
        zzco.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzrh.send(new ExceptionBuilder().setDescription(str).setFatal(true).build());
        if (this.zzrk == null) {
            this.zzrk = GoogleAnalytics.getInstance(this.zzri);
        }
        GoogleAnalytics googleAnalytics = this.zzrk;
        googleAnalytics.dispatchLocalHits();
        googleAnalytics.zzl().zzcc().zzbt();
        if (this.zzrg != null) {
            zzco.v("Passing exception to the original handler");
            this.zzrg.uncaughtException(thread, th);
        }
    }

    /* access modifiers changed from: 0000 */
    public final UncaughtExceptionHandler zzp() {
        return this.zzrg;
    }
}
