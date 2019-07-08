package com.facebook.ads.internal.v.a;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class h extends AsyncTask<l, Void, n> implements c {
    private static Executor a;
    private a b;
    private b c;
    private Exception d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        a = threadPoolExecutor;
    }

    public h(a aVar, b bVar) {
        this.b = aVar;
        this.c = bVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public n doInBackground(l... lVarArr) {
        if (lVarArr != null) {
            try {
                if (lVarArr.length > 0) {
                    return this.b.a(lVarArr[0]);
                }
            } catch (Exception e) {
                this.d = e;
                cancel(true);
                return null;
            }
        }
        throw new IllegalArgumentException("DoHttpRequestTask takes exactly one argument of type HttpRequest");
    }

    public void a(l lVar) {
        super.executeOnExecutor(a, new l[]{lVar});
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(n nVar) {
        this.c.a(nVar);
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        this.c.a(this.d);
    }
}
