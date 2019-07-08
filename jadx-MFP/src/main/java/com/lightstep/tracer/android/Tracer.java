package com.lightstep.tracer.android;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.lightstep.tracer.shared.AbstractTracer;
import com.lightstep.tracer.shared.Options;
import com.lightstep.tracer.shared.SimpleFuture;

public class Tracer extends AbstractTracer {
    private final Context ctx;

    private class AsyncFlush extends AsyncTask<Void, Void, Void> {
        private boolean explicitRequest;
        private SimpleFuture<Boolean> future;

        AsyncFlush(SimpleFuture<Boolean> simpleFuture, boolean z) {
            this.future = simpleFuture;
            this.explicitRequest = z;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            this.future.set(Boolean.valueOf(Tracer.this.sendReport(this.explicitRequest)));
            return null;
        }
    }

    public Tracer(Context context, Options options) {
        super(options.setDefaultReportingIntervalMillis(30000).disableResetClient());
        this.ctx = context;
        addStandardTracerTags();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.lightstep.tracer.shared.SimpleFuture<java.lang.Boolean> flushInternal(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mutex
            monitor-enter(r0)
            boolean r1 = r5.isDisabled()     // Catch:{ all -> 0x004d }
            r2 = 0
            if (r1 != 0) goto L_0x0042
            android.content.Context r1 = r5.ctx     // Catch:{ all -> 0x004d }
            if (r1 != 0) goto L_0x000f
            goto L_0x0042
        L_0x000f:
            com.lightstep.tracer.shared.SimpleFuture r1 = new com.lightstep.tracer.shared.SimpleFuture     // Catch:{ all -> 0x004d }
            r1.<init>()     // Catch:{ all -> 0x004d }
            android.content.Context r3 = r5.ctx     // Catch:{ all -> 0x004d }
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x004d }
            java.lang.String r4 = "connectivity"
            java.lang.Object r3 = r3.getSystemService(r4)     // Catch:{ all -> 0x004d }
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3     // Catch:{ all -> 0x004d }
            android.net.NetworkInfo r3 = r3.getActiveNetworkInfo()     // Catch:{ all -> 0x004d }
            if (r3 == 0) goto L_0x0039
            boolean r3 = r3.isConnected()     // Catch:{ all -> 0x004d }
            if (r3 == 0) goto L_0x0039
            com.lightstep.tracer.android.Tracer$AsyncFlush r3 = new com.lightstep.tracer.android.Tracer$AsyncFlush     // Catch:{ all -> 0x004d }
            r3.<init>(r1, r6)     // Catch:{ all -> 0x004d }
            java.lang.Void[] r6 = new java.lang.Void[r2]     // Catch:{ all -> 0x004d }
            r3.execute(r6)     // Catch:{ all -> 0x004d }
            goto L_0x0040
        L_0x0039:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x004d }
            r1.set(r6)     // Catch:{ all -> 0x004d }
        L_0x0040:
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return r1
        L_0x0042:
            com.lightstep.tracer.shared.SimpleFuture r6 = new com.lightstep.tracer.shared.SimpleFuture     // Catch:{ all -> 0x004d }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x004d }
            r6.<init>(r1)     // Catch:{ all -> 0x004d }
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return r6
        L_0x004d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.android.Tracer.flushInternal(boolean):com.lightstep.tracer.shared.SimpleFuture");
    }

    private void addStandardTracerTags() {
        addTracerTag("lightstep.tracer_platform", "android");
        addTracerTag("lightstep.tracer_platform_version", String.valueOf(VERSION.SDK_INT));
        addTracerTag("lightstep.tracer_version", "0.14.8");
    }

    /* access modifiers changed from: protected */
    public void printLogToConsole(InternalLogLevel internalLogLevel, String str, Object obj) {
        if (obj != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            sb.append(obj.toString());
            str = sb.toString();
        }
        switch (internalLogLevel) {
            case DEBUG:
                Log.d("Tracer", str);
                return;
            case INFO:
                Log.i("Tracer", str);
                return;
            case WARN:
                Log.w("Tracer", str);
                return;
            case ERROR:
                Log.e("Tracer", str);
                return;
            default:
                Log.e("Tracer", str);
                return;
        }
    }
}
