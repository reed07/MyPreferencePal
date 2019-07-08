package com.crashlytics.android.core;

import android.os.AsyncTask;

public class CrashTest {

    /* renamed from: com.crashlytics.android.core.CrashTest$1 reason: invalid class name */
    class AnonymousClass1 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ CrashTest this$0;
        final /* synthetic */ long val$delayMs;

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                Thread.sleep(this.val$delayMs);
            } catch (InterruptedException unused) {
            }
            this.this$0.throwRuntimeException("Background thread crash");
            return null;
        }
    }

    public void throwRuntimeException(String str) {
        throw new RuntimeException(str);
    }
}
