package com.facebook.ads.internal.v.b;

import android.text.TextUtils;
import com.facebook.ads.internal.v.b.a.b;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

class e extends k {
    private final h a;
    private final b b;
    private b c;

    public e(h hVar, b bVar) {
        super(hVar, bVar);
        this.b = bVar;
        this.a = hVar;
    }

    private void a(OutputStream outputStream, long j) {
        byte[] bArr = new byte[8192];
        while (true) {
            int a2 = a(bArr, j, bArr.length);
            if (a2 != -1) {
                outputStream.write(bArr, 0, a2);
                j += (long) a2;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private void b(OutputStream outputStream, long j) {
        try {
            h hVar = new h(this.a);
            hVar.a((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int a2 = hVar.a(bArr);
                if (a2 != -1) {
                    outputStream.write(bArr, 0, a2);
                } else {
                    outputStream.flush();
                    return;
                }
            }
        } finally {
            this.a.b();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        b bVar = this.c;
        if (bVar != null) {
            bVar.a(this.b.a, this.a.a, i);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(d dVar, Socket socket) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        String c2 = this.a.c();
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(c2);
        int a2 = this.b.d() ? this.b.a() : this.a.a();
        boolean z3 = a2 >= 0;
        long j = dVar.c ? ((long) a2) - dVar.b : (long) a2;
        boolean z4 = z3 && dVar.c;
        StringBuilder sb = new StringBuilder();
        sb.append(dVar.c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n");
        sb.append("Accept-Ranges: bytes\n");
        sb.append(z3 ? String.format(Locale.US, "Content-Length: %d\n", new Object[]{Long.valueOf(j)}) : "");
        sb.append(z4 ? String.format(Locale.US, "Content-Range: bytes %d-%d/%d\n", new Object[]{Long.valueOf(dVar.b), Integer.valueOf(a2 - 1), Integer.valueOf(a2)}) : "");
        sb.append(z2 ? String.format(Locale.US, "Content-Type: %s\n", new Object[]{c2}) : "");
        sb.append("\n");
        bufferedOutputStream.write(sb.toString().getBytes("UTF-8"));
        long j2 = dVar.b;
        int a3 = this.a.a();
        boolean z5 = a3 > 0;
        int a4 = this.b.a();
        if (z5 && dVar.c && ((float) dVar.b) > ((float) a4) + (((float) a3) * 0.2f)) {
            z = false;
        }
        if (z) {
            a((OutputStream) bufferedOutputStream, j2);
        } else {
            b(bufferedOutputStream, j2);
        }
    }
}
