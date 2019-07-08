package com.facebook.ads.internal.v.b;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.ads.internal.v.b.a.g;
import com.google.logging.type.LogSeverity;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class f {
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final c g;
    private boolean h;

    public static final class a {
        private File a;
        private com.facebook.ads.internal.v.b.a.c b = new com.facebook.ads.internal.v.b.a.f();
        private com.facebook.ads.internal.v.b.a.a c = new g(67108864);

        public a(Context context) {
            this.a = o.a(context);
        }

        static /* synthetic */ c a(a aVar) {
            return new c(aVar.a, aVar.b, aVar.c);
        }
    }

    private class b implements Callable<Boolean> {
        private b() {
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(f.this.c());
        }
    }

    private class c implements Callable<Boolean> {
        private final String b;

        public c(String str) {
            this.b = str;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(f.this.c(this.b));
        }
    }

    private final class d implements Runnable {
        private final Socket b;

        public d(Socket socket) {
            this.b = socket;
        }

        public void run() {
            f.a(f.this, this.b);
        }
    }

    private final class e implements Runnable {
        private final CountDownLatch b;

        public e(CountDownLatch countDownLatch) {
            this.b = countDownLatch;
        }

        public void run() {
            this.b.countDown();
            f.a(f.this);
        }
    }

    public f(Context context) {
        this(a.a(new a(context)));
    }

    private f(c cVar) {
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(8);
        this.c = new ConcurrentHashMap();
        this.g = (c) j.a(cVar);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f = new Thread(new e(countDownLatch));
            this.f.start();
            countDownLatch.await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            b();
        } catch (IOException | InterruptedException e2) {
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e2);
        }
    }

    static /* synthetic */ void a(f fVar) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = fVar.d.accept();
                StringBuilder sb = new StringBuilder();
                sb.append("Accept new socket ");
                sb.append(accept);
                Log.d("ProxyCache", sb.toString());
                fVar.b.submit(new d(accept));
            } catch (IOException e2) {
                fVar.a((Throwable) new l("Error during waiting connection", e2));
                return;
            }
        }
    }

    static /* synthetic */ void a(f fVar, Socket socket) {
        String str;
        StringBuilder sb;
        try {
            d a2 = d.a(socket.getInputStream());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Request to cache proxy:");
            sb2.append(a2);
            Log.i("ProxyCache", sb2.toString());
            String b2 = m.b(a2.a);
            if ("ping".equals(b2)) {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
                outputStream.write("ping ok".getBytes());
            } else {
                fVar.e(b2).a(a2, socket);
            }
            fVar.a(socket);
            str = "ProxyCache";
            sb = new StringBuilder();
        } catch (SocketException unused) {
            Log.d("ProxyCache", "Closing socket... Socket is closed by client.");
            fVar.a(socket);
            str = "ProxyCache";
            sb = new StringBuilder();
        } catch (l | IOException e2) {
            fVar.a((Throwable) new l("Error processing request", e2));
            fVar.a(socket);
            str = "ProxyCache";
            sb = new StringBuilder();
        } catch (Throwable th) {
            fVar.a(socket);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Opened connections: ");
            sb3.append(fVar.d());
            Log.d("ProxyCache", sb3.toString());
            throw th;
        }
        sb.append("Opened connections: ");
        sb.append(fVar.d());
        Log.d(str, sb.toString());
    }

    private void a(Throwable th) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", th);
    }

    private void a(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException unused) {
            Log.d("ProxyCache", "Releasing input stream... Socket is closed by client.");
        } catch (IOException e2) {
            a((Throwable) new l("Error closing socket input stream", e2));
        }
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e3) {
            a((Throwable) new l("Error closing socket output stream", e3));
        }
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e4) {
            a((Throwable) new l("Error closing socket", e4));
        }
    }

    private void b() {
        int i = LogSeverity.NOTICE_VALUE;
        int i2 = 0;
        while (i2 < 3) {
            try {
                Future submit = this.b.submit(new b());
                long j = (long) i;
                this.h = ((Boolean) submit.get(j, TimeUnit.MILLISECONDS)).booleanValue();
                if (!this.h) {
                    SystemClock.sleep(j);
                    i2++;
                    i *= 2;
                } else {
                    return;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error pinging server [attempt: ");
                sb.append(i2);
                sb.append(", timeout: ");
                sb.append(i);
                sb.append("]. ");
                Log.e("ProxyCache", sb.toString(), e2);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Shutdown server... Error pinging server [attempts: ");
        sb2.append(i2);
        sb2.append(", max timeout: ");
        sb2.append(i / 2);
        sb2.append("].");
        Log.e("ProxyCache", sb2.toString());
        a();
    }

    /* access modifiers changed from: private */
    public boolean c() {
        h hVar = new h(d("ping"));
        try {
            byte[] bytes = "ping ok".getBytes();
            hVar.a(0);
            byte[] bArr = new byte[bytes.length];
            hVar.a(bArr);
            boolean equals = Arrays.equals(bytes, bArr);
            StringBuilder sb = new StringBuilder();
            sb.append("Ping response: `");
            sb.append(new String(bArr));
            sb.append("`, pinged? ");
            sb.append(equals);
            Log.d("ProxyCache", sb.toString());
            return equals;
        } catch (l e2) {
            Log.e("ProxyCache", "Error reading ping response", e2);
            return false;
        } finally {
            hVar.b();
        }
    }

    /* access modifiers changed from: private */
    public boolean c(String str) {
        h hVar = new h(d(str));
        try {
            hVar.a(0);
            do {
            } while (hVar.a(new byte[8192]) != -1);
            hVar.b();
            return true;
        } catch (l e2) {
            Log.e("ProxyCache", "Error reading url", e2);
            hVar.b();
            return false;
        } catch (Throwable th) {
            hVar.b();
            throw th;
        }
    }

    private int d() {
        int i;
        synchronized (this.a) {
            i = 0;
            for (g b2 : this.c.values()) {
                i += b2.b();
            }
        }
        return i;
    }

    private String d(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.e), m.a(str)});
    }

    private g e(String str) {
        g gVar;
        synchronized (this.a) {
            gVar = (g) this.c.get(str);
            if (gVar == null) {
                gVar = new g(str, this.g);
                this.c.put(str, gVar);
            }
        }
        return gVar;
    }

    public void a() {
        Log.i("ProxyCache", "Shutdown proxy server");
        synchronized (this.a) {
            for (g a2 : this.c.values()) {
                a2.a();
            }
            this.c.clear();
        }
        this.f.interrupt();
        try {
            if (!this.d.isClosed()) {
                this.d.close();
            }
        } catch (IOException e2) {
            a((Throwable) new l("Error shutting down proxy server", e2));
        }
    }

    public boolean a(String str) {
        int i = 0;
        int i2 = LogSeverity.NOTICE_VALUE;
        while (i < 3) {
            try {
                if (((Boolean) this.b.submit(new c(str)).get()).booleanValue()) {
                    return true;
                }
                SystemClock.sleep((long) i2);
                i++;
                i2 *= 2;
            } catch (InterruptedException | ExecutionException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error precaching url [attempt: ");
                sb.append(i);
                sb.append(", url: ");
                sb.append(str);
                sb.append("]. ");
                Log.e("ProxyCache", sb.toString(), e2);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Shutdown server... Error precaching url [attempts: ");
        sb2.append(i);
        sb2.append(", url: ");
        sb2.append(str);
        sb2.append("].");
        Log.e("ProxyCache", sb2.toString());
        a();
        return false;
    }

    public String b(String str) {
        if (!this.h) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        return this.h ? d(str) : str;
    }
}
