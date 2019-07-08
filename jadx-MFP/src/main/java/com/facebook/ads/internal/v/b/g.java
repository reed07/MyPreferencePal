package com.facebook.ads.internal.v.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.ads.internal.v.b.a.b;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

final class g {
    private final AtomicInteger a = new AtomicInteger(0);
    private final String b;
    private volatile e c;
    private final List<b> d = new CopyOnWriteArrayList();
    private final b e;
    private final c f;

    private static final class a extends Handler implements b {
        private final String a;
        private final List<b> b;

        public a(String str, List<b> list) {
            super(Looper.getMainLooper());
            this.a = str;
            this.b = list;
        }

        public void a(File file, String str, int i) {
            Message obtainMessage = obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.obj = file;
            sendMessage(obtainMessage);
        }

        public void handleMessage(Message message) {
            for (b a2 : this.b) {
                a2.a((File) message.obj, this.a, message.arg1);
            }
        }
    }

    public g(String str, c cVar) {
        this.b = (String) j.a(str);
        this.f = (c) j.a(cVar);
        this.e = new a(str, this.d);
    }

    private synchronized void c() {
        e eVar;
        if (this.c == null) {
            eVar = new e(new h(this.b), new b(this.f.a(this.b), this.f.c));
            eVar.a(this.e);
        } else {
            eVar = this.c;
        }
        this.c = eVar;
    }

    private synchronized void d() {
        if (this.a.decrementAndGet() <= 0) {
            this.c.a();
            this.c = null;
        }
    }

    public void a() {
        this.d.clear();
        if (this.c != null) {
            this.c.a((b) null);
            this.c.a();
            this.c = null;
        }
        this.a.set(0);
    }

    public void a(d dVar, Socket socket) {
        c();
        try {
            this.a.incrementAndGet();
            this.c.a(dVar, socket);
        } finally {
            d();
        }
    }

    public int b() {
        return this.a.get();
    }
}
